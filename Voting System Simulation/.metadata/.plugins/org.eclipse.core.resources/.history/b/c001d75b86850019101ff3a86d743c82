package analysis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	
	double uav = 0;
	double hpav = 0;
	double rpav = 0;
	double hrav = 0;
	double srav = 0;
	double rrav = 0;
	double hbav = 0;
	double rbav = 0;
	double hcav = 0;
	double scav = 0;
	double hhav = 0;
	double hlav = 0;
	
	int[] uhist = new int[2000];
	int[] hphist = new int[2000];
	int[] rphist = new int[2000];
	int[] hrhist = new int[2000];
	int[] rrhist = new int[2000];
	int[] hbhist = new int[2000];
	int[] rbhist = new int[2000];
	int[] hchist = new int[2000];
	int[] schist = new int[2000];
	
	private Main() throws IOException {
		mean("Results_8.txt");
		error("Results_8.txt");
	}
	
	@SuppressWarnings("unused")
	private void mean(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		double start = System.nanoTime();
		int i = 0;
		int n = 0;

		double usum = 0;
		double hpsum = 0;
		double rpsum = 0;
		double hrsum = 0;
		double srsum = 0;
		double rrsum = 0;
		double hbsum = 0;
		double rbsum = 0;
		double hcsum = 0;
		double scsum = 0;
		double hhsum = 0;
		double hlsum = 0;
		
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
			
			i++;
			//if(i%10000 == 0)
				//System.out.println(i + " -- " + (System.nanoTime()-start)/1000.0/i);
		}
		
		double t = 0;
		
		switch(n) {
		case 3: t=1000000d;
			break;
		case 4: t=1000000d;
			break;
		case 5: t=500000d;
			break;
		case 7: t=350000d;
			break;
		case 8: t=300000d;
			break;
		case 10: t=250000d;
			break;
		case 12: t=200000d;
			break;
		case 15: t=200000d;
			break;
		case 20: t=120000d;
			break;
		case 25: t=100000d;
			break;
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
	
	@SuppressWarnings("unused")
	private void error(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		double start = System.nanoTime();
		int i = 0;
		int n = 0;
		
		double usum = 0;
		double hpsum = 0;
		double rpsum = 0;
		double hrsum = 0;
		double srsum = 0;
		double rrsum = 0;
		double hbsum = 0;
		double rbsum = 0;
		double hcsum = 0;
		double scsum = 0;
		double hhsum = 0;
		double hlsum = 0;
		
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
			
			i++;
			//if(i%10000 == 0)
				//System.out.println(i + " -- " + (System.nanoTime()-start)/1000.0/i);
		}
		
		double t = 0;
		
		switch(n) {
		case 3: t=1000000;
			break;
		case 4: t=1000000;
			break;
		case 5: t=500000;
			break;
		case 7: t=350000;
			break;
		case 8: t=300000;
			break;
		case 10: t=250000;
			break;
		case 12: t=200000;
			break;
		case 15: t=200000;
			break;
		case 20: t=120000;
			break;
		case 25: t=100000;
			break;
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
	
	private void graphs(String path) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		double start = System.nanoTime();
		int i = 0;
		int n = 0;
		
		String line = "";
		while((line = br.readLine()) != null) {
			String electionType = line.split(" ")[4];
			n = Integer.parseInt(line.split(" ")[3]);

			String result;
			if(electionType.compareTo("\\x\\") != 0 && electionType.compareTo("\\y\\") != 0)
				result = line.split(" ")[8+n];
			else continue;

			double ptemp = 0;
			double rtemp = 0;
			double btemp = 0;
			double ctemp = 0;
			if(electionType.compareTo("\\hp\\") == 0)
				ptemp = Double.parseDouble(result);
			if(electionType.compareTo("\\rp\\") == 0)
				rphist[(int) Math.floor(Double.parseDouble(result)*1000-ptemp*1000)+500]++;
			if(electionType.compareTo("\\hr\\") == 0)
				rtemp = Double.parseDouble(result);
			if(electionType.compareTo("\\rr\\") == 0)
				rrhist[(int) Math.floor(Double.parseDouble(result)*1000-rtemp*1000)+500]++;
			if(electionType.compareTo("\\hb\\") == 0)
				btemp = Double.parseDouble(result);
			if(electionType.compareTo("\\rb\\") == 0)
				rbhist[(int) Math.floor(Double.parseDouble(result)*1000-btemp*1000)+500]++;
			if(electionType.compareTo("\\hc\\") == 0)
				ctemp = Double.parseDouble(result);
			if(electionType.compareTo("\\sc\\") == 0)
				schist[(int) Math.floor(Double.parseDouble(result)*1000-ctemp*1000)+500]++;
			
			i++;
		}
		BufferedImage bi = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		
		new Drawer(rphist).paint(g);
		ImageIO.write(bi, "png", new File(path.split(".txt")[0] + "/dpGraph.png"));
		new Drawer(rrhist).paint(g);
		ImageIO.write(bi, "png", new File(path.split(".txt")[0] + "/drGraph.png"));
		new Drawer(rbhist).paint(g);
		ImageIO.write(bi, "png", new File(path.split(".txt")[0] + "/dbGraph.png"));
		new Drawer(schist).paint(g);
		ImageIO.write(bi, "png", new File(path.split(".txt")[0] + "/dcGraph.png"));
		
		System.out.println(path + " finished.");
	}
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Drawer extends Component{
		
		int[] dataset;
		
		private Drawer(int[] dataset) {
			this.dataset=dataset;
		}
		
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0,0,2000,1000);
			
			g.setColor(Color.BLACK);
			for(int i = 0; i < 2000; i++) {
				g.drawLine(i, 999, i, dataset[i]==0? 999 : 999-(int)(Math.log10(dataset[i])*1000.0/6.0));
			}
		}
		
	}
}
