package simulation;

import simulation.Election.Voter;

public abstract class Method {
	
	public Election election;
	public String name;
	
	public double[] results = new double[Main.CAND_NUM];
	public void runElection() {
		for(int i = 0; i < Main.VOTER_NUM; i++) {
			double[] b = vote(election.voters[i]);
			for(int j = 0; j < b.length; j++)
				results[j]+=b[j];
		}
	}
	public abstract double[] vote(Voter v);

	public int declareWinner() {
		int top = 0;
		double most = results[0];
		for(int i = 1; i < results.length; i++)
			if(results[i]>most) {
				top = i;
				most = results[i];
			}
		return top;
	}
	public String printResults() {
		String out = "";
		out+="\\e\\ " + election.SEED + " ";
		out+="\\n\\ " + Main.CAND_NUM + " ";
		out+="\\" + name + "\\ ";
		for(int i = 0; i < results.length; i++)
			out+=results[i] + " ";
		out+="\\w\\ " + declareWinner() + " ";
		out+="\\d\\ " + disappointment();
		return out;
	}
	
	public double disappointment() {
		double d0 = election.candidates[declareWinner()].getViews(0)-election.average()[0];
		double d1 = election.candidates[declareWinner()].getViews(1)-election.average()[1];
		return d0*d0+d1*d1;
	}
	
	public static class PopulationUtility extends Method{
		
		public PopulationUtility(Election election) {
			name = "u";
			this.election = election;
		}

		@Override
		public double[] vote(Voter v) {
			double[] ballot = new double[results.length];
			double max = -10;
			double min = 0;
			for(int i = 0; i < ballot.length; i++) {
				ballot[i] = v.utility(election.candidates[i]);
				if(ballot[i] < min) min = ballot[i];
				if(ballot[i] > max) max = ballot[i];
			}
			return ballot;
		}
		
	}
	public static class HonestPlurality extends Method{
		
		public HonestPlurality(Election election) {
			name = "hp";
			this.election = election;
		}
		
		@Override
		public double[] vote(Voter v) {
			int favorite = 0;
			double favoritism = -10;
			for(int i = 0; i<results.length; i++) {
				double t = v.utility(election.candidates[i]);
				if(t>favoritism) {
					favorite = i;
					favoritism = t;
				}
			}
			
			double[] ballot = new double[results.length];
			for(int i = 0; i < ballot.length; i++)
				ballot[i] = i==favorite ? 1 : 0;
			
			return ballot;
		}
		
	}
	public static class RationalPlurality extends Method{
		
		private boolean[] considerable = new boolean[Main.CAND_NUM];
		
		public RationalPlurality(Election election) {
			name = "rp";
			this.election = election;
		}
			
		@Override
		public void runElection() {
			double[] poll = election.honest_plurality.results;
			int first = 0;
			int second = 0;
			double firstn = 0;
			double secondn = 0;
			for(int i = 0 ; i < considerable.length; i++) {
				if(poll[i]>firstn) {
					second = first;
					secondn = firstn;
					first = i;
					firstn = poll[i];
				}else if(poll[i]>secondn) {
					second = i;
					secondn = poll[i];
				}
			}
			for(int i = 0; i < considerable.length; i++)
				considerable[i] = (i==first)||(i==second);

			for(int i = 0; i < Main.VOTER_NUM; i++) {
				double[] b = vote(election.voters[i]);
				for(int j = 0; j < b.length; j++)
					results[j]+=b[j];
			}
		}
		
		@Override
		public double[] vote(Voter v) {
			int favorite = 0;
			double favoritism = -10;
			for(int i = 0; i<results.length; i++) {
				double t = v.utility(election.candidates[i]);
				if(t>favoritism && considerable[i]) {
					favorite = i;
					favoritism = t;
				}
			}
			
			double[] ballot = new double[results.length];
			for(int i = 0; i < ballot.length; i++)
				ballot[i] = i==favorite ? 1 : 0;
			
			return ballot;
		}
		
	}
	public static class HonestRange extends Method{
		
		public HonestRange(Election election) {
			name = "hr";
			this.election = election;
		}

		@Override
		public double[] vote(Voter v) {
			double[] ballot = new double[results.length];
			double max = -10;
			double min = 0;
			for(int i = 0; i < ballot.length; i++) {
				ballot[i] = v.utility(election.candidates[i]);
				if(ballot[i] < min) min = ballot[i];
				if(ballot[i] > max) max = ballot[i];
			}
			double range = max-min;
			for(int i = 0; i < ballot.length; i++) {
				ballot[i] -= min;
				ballot[i] /= range;
			}
			return ballot;
		}
		
	}
	public static class StrategicRange extends Method{
		
		public StrategicRange(Election election) {
			name = "sr";
			this.election = election;
		}

		@Override
		public double[] vote(Voter v) {
			double[] ballot = new double[results.length];
			double average = 0;
			for(int i = 0; i < ballot.length; i++)
				average += v.utility(election.candidates[i]);
			average/=ballot.length;
			for(int i = 0; i < ballot.length; i++)
				ballot[i] += (v.utility(election.candidates[i])>average ? 1 : 0);
			return ballot;
		}
		
	}
	public static class RationalRange extends Method{
		
		private int[] order = new int[Main.CAND_NUM];
		
		public RationalRange(Election election) {
			name = "rr";
			this.election = election;
		}

		@Override
		public void runElection() {
			double[] poll = election.honest_range.results;
			int[] place = new int[poll.length];
			for(int i = 0; i < poll.length; i++) {
			for(int j = i+1; j < poll.length; j++)
				if(poll[i]>poll[j]) place[i]++;
				else place[j]++;
			}
			for(int i = 0; i < place.length; i++)
				order[Main.CAND_NUM-place[i]-1]=i;

			for(int i = 0; i < Main.VOTER_NUM; i++) {
				double[] b = vote(election.voters[i]);
				for(int j = 0; j < b.length; j++)
					results[j]+=b[j];
			}
		}
		
		@Override
		public double[] vote(Voter v) {
			double[] ballot = new double[Main.CAND_NUM];
			
			double average = v.utility(election.candidates[order[0]])+v.utility(election.candidates[order[1]]);
			if(v.utility(election.candidates[order[0]])>average/2.0) {
				ballot[order[0]]=1.0;
				ballot[order[1]]=0.0;
			}else{
				ballot[order[0]]=0.0;
				ballot[order[1]]=1.0;
			}
			for(int i = 2; i < ballot.length; i++) {
				if(v.utility(election.candidates[order[i]])>average/(double)i) {
					ballot[order[i]]=1.0;
				}else{
					ballot[order[i]]=0.0;
				}
				average += v.utility(election.candidates[order[i]]);
			}
			return ballot;
		}
		
	}
	public static class HonestBorda extends Method{
		
		public HonestBorda(Election election) {
			name = "hb";
			this.election = election;
		}

		@Override
		public double[] vote(Voter v) {
			double[] ballot1 = new double[results.length];
			for(int i = 0; i < ballot1.length; i++)
				ballot1[i] = v.utility(election.candidates[i]);
			
			double[] ballot = new double[results.length];
			for(int i = 0; i < ballot.length; i++)
				for(int j = i+1; j < ballot.length; j++)
					ballot[ballot1[i]>ballot1[j] ? i : j]++;
			
			return ballot;
		}
		
	}
	public static class RationalBorda extends Method{
		
		private int[] order = new int[Main.CAND_NUM];
		
		public RationalBorda(Election election) {
			name = "rb";
			this.election = election;
		}

		@Override
		public void runElection() {
			double[] poll = election.honest_borda.results;
			int[] place = new int[poll.length];
			for(int i = 0; i < poll.length; i++) {
			for(int j = i+1; j < poll.length; j++)
				if(poll[i]>poll[j]) place[i]++;
				else place[j]++;
			}
			for(int i = 0; i < place.length; i++)
				order[Main.CAND_NUM-place[i]-1]=i;

			for(int i = 0; i < Main.VOTER_NUM; i++) {
				double[] b = vote(election.voters[i]);
				for(int j = 0; j < b.length; j++)
					results[j]+=b[j];
			}
		}
		
		@Override
		public double[] vote(Voter v) {
			double[] ballot = new double[Main.CAND_NUM];
			int right = Main.CAND_NUM-2;
			int left = 1;
			
			double average = v.utility(election.candidates[order[0]])+v.utility(election.candidates[order[1]]);
			if(v.utility(election.candidates[order[0]])>average/2.0) {
				ballot[order[0]]=Main.CAND_NUM-1;
				ballot[order[1]]=0.0;
			}else{
				ballot[order[0]]=0.0;
				ballot[order[1]]=Main.CAND_NUM-1;
			}
			for(int i = 2; i < ballot.length; i++) {
				if(v.utility(election.candidates[order[i]])>average/(double)i) {
					ballot[order[i]]=right;
					right--;
				}else{
					ballot[order[i]]=left;
					left++;
				}
				average += v.utility(election.candidates[order[i]]);
			}
			if(v.equals(election.voters[0])) {
				//System.out.println(ballot[0] + " " + ballot[1] + " "+ ballot[2] + " "+ ballot[3] + " "+ ballot[4]);
			}
			return ballot;
		}
		
	}
	public static class HonestCopeland extends Method{
		
		public HonestCopeland(Election election) {
			name = "hc";
			this.election = election;
		}
		
		@Override
		public void runElection() {
			double[][] b = new double[Main.CAND_NUM][Main.CAND_NUM];
			for(int n = 0; n < Main.VOTER_NUM; n++) {
				double[] v = vote(election.voters[n]);
				for(int i = 0; i < b.length; i++)
				for(int j = i+1; j < b.length; j++) {
					if(v[i]>v[j]) b[i][j]++;
					else b[i][j]--;
				}
			}
			for(int i = 0; i < b.length; i++)
			for(int j = i+1; j < b.length; j++) {
				if(b[i][j]>0) results[i]++;
				else results[j]++;
			}
		}
		
		@Override
		public double[] vote(Voter v) {
			double[] ballot1 = new double[results.length];
			for(int i = 0; i < ballot1.length; i++)
				ballot1[i] = v.utility(election.candidates[i]);
			
			double[] ballot = new double[results.length];
			for(int i = 0; i < ballot.length; i++)
				for(int j = i+1; j < ballot.length; j++)
					ballot[ballot1[i]>ballot1[j] ? i : j]++;
			
			return ballot;
		}
		
	}
	public static class StrategicCopeland extends Method{
		
		private int[] order = new int[Main.CAND_NUM];
		
		public StrategicCopeland(Election election) {
			name = "sc";
			this.election = election;
		}
		
		@Override
		public void runElection() {
			double[] poll = election.honest_copeland.results;
			int[] place = new int[poll.length];
			for(int i = 0; i < poll.length; i++) {
			for(int j = i+1; j < poll.length; j++)
				if(poll[i]>poll[j]) place[i]++;
				else place[j]++;
			}
			for(int i = 0; i < place.length; i++)
				order[Main.CAND_NUM-place[i]-1]=i;
			
			double[][] b = new double[Main.CAND_NUM][Main.CAND_NUM];
			for(int n = 0; n < Main.VOTER_NUM; n++) {
				double[] v = vote(election.voters[n]);
				for(int i = 0; i < b.length; i++)
				for(int j = i+1; j < b.length; j++) {
					if(v[i]>v[j]) b[i][j]++;
					else b[i][j]--;
				}
			}
			for(int i = 0; i < b.length; i++)
			for(int j = i+1; j < b.length; j++) {
				if(b[i][j]>0) results[i]++;
				else results[j]++;
			}
		}
		
		@Override
		public double[] vote(Voter v) {
			double[] ballot = new double[Main.CAND_NUM];
			int right = Main.CAND_NUM-2;
			int left = 1;
			
			double average = v.utility(election.candidates[order[0]])+v.utility(election.candidates[order[1]]);
			if(v.utility(election.candidates[order[0]])>average/2.0) {
				ballot[order[0]]=Main.CAND_NUM-1;
				ballot[order[1]]=0.0;
			}else{
				ballot[order[0]]=0.0;
				ballot[order[1]]=Main.CAND_NUM-1;
			}
			for(int i = 2; i < ballot.length; i++) {
				if(v.utility(election.candidates[order[i]])>average/(double)i) {
					ballot[order[i]]=right;
					right--;
				}else{
					ballot[order[i]]=left;
					left++;
				}
				average += v.utility(election.candidates[order[i]]);
			}
			if(v.equals(election.voters[0])) {
				//System.out.println(ballot[0] + " " + ballot[1] + " "+ ballot[2] + " "+ ballot[3] + " "+ ballot[4]);
			}
			return ballot;
		}
		
	}
	public static class Hale extends Method{
		
		boolean[] running = new boolean[Main.CAND_NUM];
		
		public Hale(Election election) {
			name = "hh";
			this.election = election;
			for(int i = 0; i < running.length; i++) running[i] = true;
		}
		
		@Override
		public void runElection() {
			for(int i = 0; i < running.length; i++) {
				double[] round = new double[Main.CAND_NUM];
				for(Voter v : election.voters) {
					double[] b = vote(v);
					for(int j = 0; j < b.length; j++)
						round[j]+=b[j];
				}
				int min = 0;
				double minnum = Main.VOTER_NUM;
				for(int j = 0; j < round.length; j++) {
					if(round[j]<minnum && running[j]) {
						min = j;
						minnum = round[j];
					}
					if(running[j])
						results[j]++;
				}
				running[min]=false;
			}
		}
		
		@Override
		public double[] vote(Voter v) {
			int favorite = 0;
			double favoritism = -100;
			for(int i = 0; i<results.length; i++) {
				double t = v.utility(election.candidates[i]);
				if(!running[i]) continue;
				if(t>favoritism) {
					favorite = i;
					favoritism = t;
				}
			}
			
			double[] ballot = new double[results.length];
			for(int i = 0; i < ballot.length; i++)
				ballot[i] = i==favorite ? 1 : 0;
			
			return ballot;
		}
		
	}
	public static class Lottery extends Method{
		
		public Lottery(Election election) {
			name = "hl";
			this.election = election;
		}
		
		@Override
		public void runElection() {
			double[] b = vote(election.voters[election.r.nextInt(Main.VOTER_NUM)]);
			for(int j = 0; j < b.length; j++)
				results[j]+=b[j];
		}
		
		@Override
		public double[] vote(Voter v) {
			int favorite = 0;
			double favoritism = -10;
			for(int i = 0; i<results.length; i++) {
				double t = v.utility(election.candidates[i]);
				if(t>favoritism) {
					favorite = i;
					favoritism = t;
				}
			}
			
			double[] ballot = new double[results.length];
			for(int i = 0; i < ballot.length; i++)
				ballot[i] = i==favorite ? 1 : 0;
			
			return ballot;
		}
		
	}

}
