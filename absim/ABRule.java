package edu.neu.csye6200.absim;

public class ABRule implements Runnable{
	private boolean done = false;
	Boat bt = new Boat("Cleaner", 0, 0, 90,"E", 100, 10, 100);
	OceanGrid og = new OceanGrid();
	
	public void boatTravel() {
    	if(bt.getPosX()==OceanGrid.gridHeight/2 && bt.getPosY()==OceanGrid.gridWidth/2) {
    		done =true;
    	}
    	else {
    	int loopi =bt.getPosX();
    	int loopj = bt.getPosY();
    	int gridH=OceanGrid.gridHeight-bt.getPosX();
    	int gridW=OceanGrid.gridWidth-bt.getPosY();
    	while(loopj<gridH-1) {
    		bt.setDirection("W");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>=0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread-=100;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);}
    		OceanGrid.abPanel.repaint();
    		
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopj++;
    		og.getOilSpread();
    		
    		System.out.println("Boat Cleanup (% remaining) :"+OceanGrid.oilSpreadPerc+ "\nDirection : "+ bt.getDirection()+"\nSpeed : "+bt.getSpeed());
    	}
    	while(loopi<gridW-1) {
    		bt.setDirection("S");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>=0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread-=100;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);}
    			
    		OceanGrid.abPanel.repaint();
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopi++;
    		og.getOilSpread();
    		System.out.println("Boat Cleanup (% remaining) :"+OceanGrid.oilSpreadPerc+ "\nDirection : "+ bt.getDirection()+"\nSpeed : "+bt.getSpeed());
    	}
    	while(loopj>bt.getPosY()) {
    		bt.setDirection("E");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>=0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread-=100;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);}
    			
    		OceanGrid.abPanel.repaint();
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopj--;
    		og.getOilSpread();
    		System.out.println("Boat Cleanup (% remaining) :"+OceanGrid.oilSpreadPerc+ "\nDirection : "+ bt.getDirection()+"\nSpeed : "+bt.getSpeed());
    	}
    	while(loopi>bt.getPosX()) {
    		bt.setDirection("N");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>=0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread-=100;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);}
    		OceanGrid.abPanel.repaint();
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopi--;
    		og.getOilSpread();
    		System.out.println("Boat Cleanup (% remaining) :"+OceanGrid.oilSpreadPerc+ "\nDirection : "+ bt.getDirection()+"\nSpeed : "+bt.getSpeed());
    	}
    	bt.setBatteryCapacity(bt.getBatteryCapacity()-10);
    	bt.moveTo(bt.getPosX()+1, bt.getPosY()+1);
    	}
    	   	
}
	public void run() {
		while(!done) {
			boatTravel();
		}
	}


}
