package simulation;

import java.util.Random;

import simulation.Method.*;

public class Election {
	
	public int SEED;
	public Random r;
	
	public Voter[] voters;
	public Candidate[] candidates;
	
	public PopulationUtility population_utility;
	public HonestPlurality honest_plurality;
	public RationalPlurality rational_plurality;
	public HonestRange honest_range;
	public StrategicRange strategic_range;
	public RationalRange rational_range;
	public HonestBorda honest_borda;
	public RationalBorda rational_borda;
	public HonestCopeland honest_copeland;
	public StrategicCopeland strategic_copeland;
	public Hale hale;
	public Lottery lottery;
	
	public Election(int SEED) {
		this.SEED = SEED;
		setupElection();
	};
	
	private void setupElection() {
		r = new Random(SEED);
		
		voters = new Voter[Main.VOTER_NUM];
		candidates = new Candidate[Main.CAND_NUM];
		for(int i = 0; i < Main.VOTER_NUM; i++)
			voters[i] = new Voter();
		for(int i = 0; i < Main.CAND_NUM; i++)
			candidates[i] = new Candidate();

		population_utility = new PopulationUtility(this);
		honest_plurality = new HonestPlurality(this);
		rational_plurality = new RationalPlurality(this);
		honest_range = new HonestRange(this);
		strategic_range = new StrategicRange(this);
		rational_range = new RationalRange(this);
		honest_borda = new HonestBorda(this);
		rational_borda = new RationalBorda(this);
		honest_copeland = new HonestCopeland(this);
		strategic_copeland = new StrategicCopeland(this);
		hale = new Hale(this);
		lottery = new Lottery(this);
	}
	
	public void runElection() {
		population_utility.runElection();
		honest_plurality.runElection();
		rational_plurality.runElection();
		honest_range.runElection();
		strategic_range.runElection();
		rational_range.runElection();
		honest_borda.runElection();
		rational_borda.runElection();
		honest_copeland.runElection();
		strategic_copeland.runElection();
		hale.runElection();
		lottery.runElection();
	}
	
	public String printViews(int view) {
		String out = "\\e\\ " + SEED + " \\n\\ " + Main.CAND_NUM + (view==0 ? " \\x\\ " : " \\y\\ ");
		for(int i = 0; i < Main.CAND_NUM; i++) {
			Candidate c = candidates[i];
			out += c.getViews(view) + " ";
		}
		out += "\\a\\ " + average()[view];
		return out;
	}
	
	public void printResults() {
		System.out.println(printViews(0));
		System.out.println(printViews(1));
		System.out.println(honest_plurality.printResults());
		System.out.println(rational_plurality.printResults());
		System.out.println(honest_range.printResults());
		System.out.println(strategic_range.printResults());
		System.out.println(rational_range.printResults());
		System.out.println(honest_borda.printResults());
		System.out.println(rational_borda.printResults());
		System.out.println(honest_copeland.printResults());
		System.out.println(strategic_copeland.printResults());
		System.out.println(hale.printResults());
		System.out.println(lottery.printResults());
	}
	
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
		
		public double utility(Candidate c) {
			double disappointment = 0;
			for(int i = 0; i < views.length; i++) {
				disappointment += (views[i]-c.getViews(i))*(views[i]-c.getViews(i));
			}
			return -disappointment;
		}
		
	}
	
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
