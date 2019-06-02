package simulation;

import java.util.Random;

import simulation.Method.*;

public class Election {
	
	public int SEED;
	public Random r;
	
	public Voter[] voters;
	public Candidate[] candidates;
	
	public HonestPlurality honest_plurality;
	public RationalPlurality rational_plurality;
	public HonestRange honest_range;
	public StrategicRange strategic_range;
	public RationalRange rational_range;
	public HonestBorda honest_borda;
	public RationalBorda rational_borda;
	public HonestCopeland honest_copeland;
	public StrategicCopeland strategic_copeland;
	public Hare hare;
	public Lottery lottery;
	
//The views of the voters and candidates in an election are assigned randomly.
//To make the results replicable, each election is assigned a seed that is used to generate the random numbers.
	public Election(int SEED) {
		this.SEED = SEED;
		r = new Random(SEED);
		setupElection();
	};
	
	private void setupElection() {
	//Arrays are made which contain all the voters and candidates in the election.
		voters = new Voter[Main.VOTER_NUM];
		candidates = new Candidate[Main.CAND_NUM];
		for(int i = 0; i < Main.VOTER_NUM; i++)
			voters[i] = new Voter();
		for(int i = 0; i < Main.CAND_NUM; i++)
			candidates[i] = new Candidate();

	//Each voting system is represented by a class that takes in an election object.
	//The voting system classes will know the voter and candidate views from this class and themselves calculated the results.
	//This is done so that each an election under each voting system will be run using the same voters and candidates.
		honest_plurality = new HonestPlurality(this);
		rational_plurality = new RationalPlurality(this);
		honest_range = new HonestRange(this);
		strategic_range = new StrategicRange(this);
		rational_range = new RationalRange(this);
		honest_borda = new HonestBorda(this);
		rational_borda = new RationalBorda(this);
		honest_copeland = new HonestCopeland(this);
		strategic_copeland = new StrategicCopeland(this);
		hare = new Hare(this);
		lottery = new Lottery(this);
	}
	
	public void runElection() {
		honest_plurality.runElection();
		rational_plurality.runElection();
		honest_range.runElection();
		strategic_range.runElection();
		rational_range.runElection();
		honest_borda.runElection();
		rational_borda.runElection();
		honest_copeland.runElection();
		strategic_copeland.runElection();
		hare.runElection();
		lottery.runElection();
	}
	
//This is will print out the views of each of the candidates.
	public String printViews(int view) {
		String out = "\\e\\ " + SEED + " \\n\\ " + Main.CAND_NUM + (view==0 ? " \\x\\ " : " \\y\\ ");
		for(int i = 0; i < Main.CAND_NUM; i++) {
			Candidate c = candidates[i];
			out += c.getViews(view) + " ";
		}
		out += "\\a\\ " + average()[view];
		return out;
	}
	
//This will calculate the average of the views of the voters so that a best candidate can be determined.
	public double[] average(){
		double[] av = new double[] {0.0,0.0};
		for(Voter v : voters) {
			av[0]+=v.getViews(0);
			av[1]+=v.getViews(1);
		}
		av[0]/=Main.VOTER_NUM;
		av[1]/=Main.VOTER_NUM;
		return av;
	}
	
//Each voter has a 2D vector representing their views, randomly assigned according to a Gaussian distribution with SD 0.2.
	public class Voter{
		private double[] views;
		
		private Voter() {
			this.views = new double[] {r.nextGaussian()/5, r.nextGaussian()/5};
		}
		
		public double[] getViews() {
			return views;
		}
		
		public double getViews(int index) {
			return views[index];
		}
		
	//The utility each candidate has towards a voter is equal to the negative squared distance between the views of the voter and the candidate.
		public double utility(Candidate c) {
			double disappointment = 0;
			for(int i = 0; i < views.length; i++) {
				disappointment += (views[i]-c.getViews(i))*(views[i]-c.getViews(i));
			}
			return -disappointment;
		}
		
	}
	
//Each candidate has a 2D vector representing their views, randomly assigned according to a Gaussian distribution with SD 0.2.
	public class Candidate{
		private double[] views;
		
		private Candidate() {
			this.views = new double[] {r.nextGaussian()/5, r.nextGaussian()/5};
		}
		
		public double[] getViews() {
			return views;
		}
		
		public double getViews(int index) {
			return views[index];
		}
		
	}

}
