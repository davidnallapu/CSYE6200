package ABSimulation;

import java.util.ArrayList;

public class OceanGrid {
	static Integer gridData[][] = {{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			
			
	};
	
	public static int gridWidth = gridData[0].length;
    public static int gridHeight = gridData.length;
    public static ArrayList<Point> completeOil = new ArrayList<Point>();
    public static ArrayList<Point> borderOil = new ArrayList<Point>();

    
	public void showGrid() {
		System.out.println("___________________ Grid ___________________\n");
		for (int i =0;i<gridHeight; i++) {
			for (int j=0;j<gridWidth;j++) {
				System.out.print(String.format("%1$4s",gridData[i][j]));
			}
			System.out.println();
		}
		System.out.println("____________________________________________");
		
	}	
	
	public void spreadOil() {
		for(Point pt : borderOil) {
			if(gridData[pt.R][pt.C]<100) {
				increaseOil(pt.R,pt.C);
			}
			else {
				OceanGrid.completeOil.add(pt);
				OceanGrid.borderOil.remove(pt);
				if(pt.R-1>-1 && pt.C >-1 && pt.R-1 <= gridHeight-1 && pt.C <= gridWidth-1)
				OceanGrid.borderOil.add(new Point(pt.R-1,pt.C));
				if(pt.R+1>-1 && pt.C >-1 && pt.R+1 <= gridHeight-1 && pt.C <= gridWidth-1)
				OceanGrid.borderOil.add(new Point(pt.R+1,pt.C));
				if(pt.R>-1 && pt.C-1 >-1 && pt.R <= gridHeight-1 && pt.C-1 <= gridWidth-1)
				OceanGrid.borderOil.add(new Point(pt.R,pt.C-1));
				if(pt.R>-1 && pt.C+1 >-1 && pt.R <= gridHeight-1 && pt.C+1 <= gridWidth-1)
				OceanGrid.borderOil.add(new Point(pt.R,pt.C+1));
				break;
			}	
		}
	}
	
	public void increaseOil(int oilX, int oilY) {
			gridData[oilX][oilY]+=50;
			showGrid();
	}
	
	
}

class Point{
	int R;
	int C;
	Point(int R, int C){
		this.R=R;
		this.C=C;
	}
}
