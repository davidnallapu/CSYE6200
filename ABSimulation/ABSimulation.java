package ABSimulation;

public class ABSimulation {
	public static void main (String args[]) {
		OceanGrid og = new OceanGrid();
		og.showGrid();
		OceanGrid.borderOil.add(new Point(4,0));
		int counter =80000;
		while(counter>0) {
			og.spreadOil();
			counter--;
		}
		
		
	}
}
