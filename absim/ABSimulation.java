package edu.neu.csye6200.absim;

import javax.swing.JFrame;


public class ABSimulation {
	
	public void run() {
		OceanGrid og = new OceanGrid();
		og.makeGrid();
//		og.printGrid();
		
		JFrame frame = new JFrame("Oil Spread Simulation");
        frame.add(OceanGrid.abPanel = new ABPanel(OceanGrid.gridData));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Makes size according to panel's preference
        frame.setVisible(true);
        
		OceanGrid.borderOil.add(OceanGrid.gridData[OceanGrid.gridWidth/2-1][OceanGrid.gridHeight/2-1]);
		
		int counter=OceanGrid.gridHeight*OceanGrid.gridWidth+2;
		while(counter>0) {
			og.spreadOil();
			og.getOilSpread();
			System.out.println("Oil Spread(%) : "+og.oilSpreadPerc);
			counter--;
		}
		ABRule abr = new ABRule();
		Boat bt = new Boat("Cleaner", 0, 0, 90,"E", 100, 10, 100);
		abr.boatTravel( bt);
//		og.boatTravel(0, 0);
		System.out.println("Simulation has ended");
	}
	
	
	public static void main (String args[]) {
		ABSimulation abs = new ABSimulation();
		abs.run();	
}
	}
