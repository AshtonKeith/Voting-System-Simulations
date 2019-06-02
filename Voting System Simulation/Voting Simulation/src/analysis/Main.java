package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	double 	uav = 0, hpav = 0, rpav = 0,
			hrav = 0, srav = 0, rrav = 0,
			hbav = 0, rbav = 0, hcav = 0,
			scav = 0, hhav = 0, hlav = 0;
	
	private Main() throws IOException {
		//Replace "Results_8.txt" with the name on the file you want to analyze
		mean("Results_8.txt");
		error("Results_8.txt");
	}
	
	private void mean(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		int n = 0;

		double 	usum = 0, hpsum = 0, rpsum = 0,
				hrsum = 0, srsum = 0, rrsum = 0,
				hbsum = 0, rbsum = 0, hcsum = 0,
				scsum = 0, hhsum = 0, hlsum = 0;
		
		String line = "";
		while((line = br.readLine()) != null) {
			String electionType = line.split(" ")[4];
			n = Integer.parseInt(line.split(" ")[3]);

			String result;
			if(electionType.compareTo("\\x\\") != 0 && electionType.compareTo("\\y\\") != 0)
				result = line.split(" ")[8+n];
			else continue;

			if(electionType.compareTo("\\u\\") == 0)
				usum+=Double.parseDouble(result);
			if(electionType.compareTo("\\hp\\") == 0)
				hpsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\rp\\") == 0)
				rpsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\hr\\") == 0)
				hrsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\sr\\") == 0)
				srsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\rr\\") == 0)
				rrsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\hb\\") == 0)
				hbsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\rb\\") == 0)
				rbsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\hc\\") == 0)
				hcsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\sc\\") == 0)
				scsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\hh\\") == 0)
				hhsum+=Double.parseDouble(result);
			if(electionType.compareTo("\\hl\\") == 0)
				hlsum+=Double.parseDouble(result);
		}
		br.close();
		
		double t = 0;
		
		switch(n) {
			case 3: t=1000000d; break;
			case 4: t=1000000d; break;
			case 5: t=500000d; break;
			case 7: t=350000d; break;
			case 8: t=300000d; break;
			case 10: t=250000d; break;
			case 12: t=200000d; break;
			case 15: t=200000d; break;
			case 20: t=120000d; break;
			case 25: t=100000d; break;
		}

		System.out.println("\\u\\ " + n + "\\x\\" + (uav=usum/t));
		System.out.println("\\hp\\ " + n + "\\x\\" + (hpav=hpsum/t));
		System.out.println("\\rp\\ " + n + "\\x\\" + (rpav=rpsum/t));
		System.out.println("\\hr\\ " + n + "\\x\\" + (hrav=hrsum/t));
		System.out.println("\\sr\\ " + n + "\\x\\" + (srav=srsum/t));
		System.out.println("\\rr\\ " + n + "\\x\\" + (rrav=rrsum/t));
		System.out.println("\\hb\\ " + n + "\\x\\" + (hbav=hbsum/t));
		System.out.println("\\rb\\ " + n + "\\x\\" + (rbav=rbsum/t));
		System.out.println("\\hc\\ " + n + "\\x\\" + (hcav=hcsum/t));
		System.out.println("\\sc\\ " + n + "\\x\\" + (scav=scsum/t));
		System.out.println("\\hh\\ " + n + "\\x\\" + (hhav=hhsum/t));
		System.out.println("\\hl\\ " + n + "\\x\\" + (hlav=hlsum/t));
		
	}
	
	private void error(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		int n = 0;

		double 	usum = 0, hpsum = 0, rpsum = 0,
				hrsum = 0, srsum = 0, rrsum = 0,
				hbsum = 0, rbsum = 0, hcsum = 0,
				scsum = 0, hhsum = 0, hlsum = 0;
		
		String line = "";
		while((line = br.readLine()) != null) {
			String electionType = line.split(" ")[4];
			n = Integer.parseInt(line.split(" ")[3]);

			String result;
			if(electionType.compareTo("\\x\\") != 0 && electionType.compareTo("\\y\\") != 0)
				result = line.split(" ")[8+n];
			else continue;

			if(electionType.compareTo("\\u\\") == 0)
				usum+=(Double.parseDouble(result)-uav)*(Double.parseDouble(result)-uav);
			if(electionType.compareTo("\\hp\\") == 0)
				hpsum+=(Double.parseDouble(result)-hpav)*(Double.parseDouble(result)-hpav);
			if(electionType.compareTo("\\rp\\") == 0)
				rpsum+=(Double.parseDouble(result)-rpav)*(Double.parseDouble(result)-rpav);
			if(electionType.compareTo("\\hr\\") == 0)
				hrsum+=(Double.parseDouble(result)-hrav)*(Double.parseDouble(result)-hrav);
			if(electionType.compareTo("\\sr\\") == 0)
				srsum+=(Double.parseDouble(result)-srav)*(Double.parseDouble(result)-srav);
			if(electionType.compareTo("\\rr\\") == 0)
				rrsum+=(Double.parseDouble(result)-rrav)*(Double.parseDouble(result)-rrav);
			if(electionType.compareTo("\\hb\\") == 0)
				hbsum+=(Double.parseDouble(result)-hbav)*(Double.parseDouble(result)-hbav);
			if(electionType.compareTo("\\rb\\") == 0)
				rbsum+=(Double.parseDouble(result)-rbav)*(Double.parseDouble(result)-rbav);
			if(electionType.compareTo("\\hc\\") == 0)
				hcsum+=(Double.parseDouble(result)-hcav)*(Double.parseDouble(result)-hcav);
			if(electionType.compareTo("\\sc\\") == 0)
				scsum+=(Double.parseDouble(result)-scav)*(Double.parseDouble(result)-scav);
			if(electionType.compareTo("\\hh\\") == 0)
				hhsum+=(Double.parseDouble(result)-hhav)*(Double.parseDouble(result)-hhav);
			if(electionType.compareTo("\\hl\\") == 0)
				hlsum+=(Double.parseDouble(result)-hlav)*(Double.parseDouble(result)-hlav);
		}
		br.close();
		
		double t = 0;
		
		switch(n) {
			case 3: t=1000000;	break;
			case 4: t=1000000;	break;
			case 5: t=500000;	break;
			case 7: t=350000;	break;
			case 8: t=300000;	break;
			case 10: t=250000;	break;
			case 12: t=200000;	break;
			case 15: t=200000;	break;
			case 20: t=120000;	break;
			case 25: t=100000;	break;
		}

		System.out.println("\\u\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(usum)/t);
		System.out.println("\\hp\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(hpsum)/t);
		System.out.println("\\rp\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(rpsum)/t);
		System.out.println("\\hr\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(hrsum)/t);
		System.out.println("\\sr\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(srsum)/t);
		System.out.println("\\rr\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(rrsum)/t);
		System.out.println("\\hb\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(hbsum)/t);
		System.out.println("\\rb\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(rbsum)/t);
		System.out.println("\\hc\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(hcsum)/t);
		System.out.println("\\sc\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(scsum)/t);
		System.out.println("\\hh\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(hhsum)/t);
		System.out.println("\\hl\\ " + n + "\\e\\" + 2.5758d*Math.sqrt(hlsum)/t);
		
	}	
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
