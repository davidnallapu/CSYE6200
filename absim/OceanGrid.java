package edu.neu.csye6200.absim;

import java.util.ArrayList;


public class OceanGrid implements Runnable{
	int R;
	int C;
	int oilSpread;
	private boolean done = false;
	//Default constructor 
	OceanGrid(){}
	
	public static ABPanel abPanel;
	
	//For OceanGrid box with oil Spread
	OceanGrid(int R, int C, int oilSpread){
		this.R=R;
		this.C=C;
		this.oilSpread=oilSpread;
	}
	
	public static int gridWidth = 20;
    public static int gridHeight = 20;
    public static OceanGrid gridData[][] = new OceanGrid[gridHeight][gridWidth];
    public static ArrayList<OceanGrid> borderOil = new ArrayList<OceanGrid>();
    
    public void makeGrid() {
    	for(int i =0; i < gridHeight;i++) {
    		OceanGrid gridRow[] = new OceanGrid[gridHeight];
    		for(int j=0; j< gridWidth; j++) {
    			gridRow[j]=new OceanGrid(i,j,0);
    		}
    		gridData[i]=gridRow;
    	}    	
    }
    
    public void printGrid(){
    	System.out.println("___________________ Grid ___________________\n");
    	for(int i =0; i < gridHeight;i++) {
    		for(int j=0; j<gridWidth;j++) {
    		System.out.print(String.format("%1$4s",gridData[i][j].oilSpread));
    	}
    		System.out.println();}
    	System.out.println("____________________________________________");
    }    
    
    public void spreadOil(){
    	if(borderOil.size()==0) {
    		done =true;
    	}
    	for(OceanGrid gb: borderOil) {
    		if(gb.oilSpread<100) {
    			gb.oilSpread+=20;
//    			printGrid();
    			if(gb.oilSpread == 100) {
	    			updateGrid(gb);
	    			borderOil.remove(gb);
	    			break;}
    		}
    	}
    	abPanel.repaint();
    	try{ Thread.sleep(10); } catch(Exception e){};
    }
	
    public void updateGrid(OceanGrid gb) {
    	//Check W
    	if(gb.R>-1 && gb.C-1 >-1 && gb.R <= gridHeight-1 && gb.C-1 <= gridWidth-1 && gridData[gb.R][gb.C-1].oilSpread!=100)
			borderOil.add(gridData[gb.R][gb.C-1]);
    	
		//Check NW
		if(gb.R-1>-1 && gb.C-1 >-1 && gb.R-1 <= gridHeight-1 && gb.C-1 <= gridWidth-1 && gridData[gb.R-1][gb.C-1].oilSpread!=100)
			borderOil.add(gridData[gb.R-1][gb.C-1]);

		//Check N
		if(gb.R-1>-1 && gb.C >-1 && gb.R-1 <= gridHeight-1 && gb.C <= gridWidth-1 && gridData[gb.R-1][gb.C].oilSpread!=100)
			borderOil.add(gridData[gb.R-1][gb.C]);

		//Check NE
		if(gb.R-1>-1 && gb.C+1 >-1 && gb.R -1<= gridHeight-1 && gb.C+1 <= gridWidth-1 && gridData[gb.R-1][gb.C+1].oilSpread!=100)
			borderOil.add(gridData[gb.R-1][gb.C+1]);

		//Check E
		if(gb.R>-1 && gb.C+1 >-1 && gb.R <= gridHeight-1 && gb.C+1 <= gridWidth-1 && gridData[gb.R][gb.C+1].oilSpread!=100)
			borderOil.add(gridData[gb.R][gb.C+1]);
			
		//Check SE
		if(gb.R+1>-1 && gb.C+1 >-1 && gb.R+1 <= gridHeight-1 && gb.C+1 <= gridWidth-1 && gridData[gb.R+1][gb.C+1].oilSpread!=100)
			borderOil.add(gridData[gb.R+1][gb.C+1]);
			
		//Check S
		if(gb.R+1>-1 && gb.C >-1 && gb.R+1 <= gridHeight-1 && gb.C <= gridWidth-1 && gridData[gb.R+1][gb.C].oilSpread!=100)
			borderOil.add(gridData[gb.R+1][gb.C]);
		
		//Check SW
		if(gb.R+1>-1 && gb.C-1 >-1 && gb.R+1 <= gridHeight-1 && gb.C-1 <= gridWidth-1 && gridData[gb.R+1][gb.C-1].oilSpread!=100)
			borderOil.add(gridData[gb.R+1][gb.C-1]);
    
    }
    
    public static double oilSpreadPerc;
    public void getOilSpread() {
    	oilSpreadPerc=0;
    	for(int i =0; i < gridHeight;i++) {
    		for(int j=0; j<gridWidth;j++) {
    			if(gridData[i][j].oilSpread == 100) {
    				oilSpreadPerc+=1;
    			}
    		}
    	}
    	oilSpreadPerc=oilSpreadPerc*100/(gridHeight*gridWidth);
    }

	@Override
	public void run() {
		while(!done )
		spreadOil();
	}
    
    
}

