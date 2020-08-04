package ABSimulation;

import javax.swing.JFrame;


public class ABSimulation {
	
	public static void main (String args[]) {
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
			counter--;
		}
		og.boatTravel(2, 5);
		og.boatTravel(0, 0);
		System.out.println("Simulation has ended");
	}
}
