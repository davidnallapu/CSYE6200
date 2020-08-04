package edu.neu.csye6200.absim;

public class ABRule implements Runnable{
	public static boolean done = false;
	public static int flag = 0;
	public static int flag2 = 0;
	public static Boat bt = new Boat("Cleaner", 0, 0, 90,"E", 30, 10, 100);
//	OceanGrid og = new OceanGrid();

	public static int loopi = 0;
	public Thread threadBoat = null;
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public static int loopj = 0;
	
	public void boatTravelForward() {
		if(done) {	
		if(flag2==1) {
			boatTravelBackward();
		}
		else {
    	if(bt.getPosX()==OceanGrid.gridHeight/2 && bt.getPosY()==OceanGrid.gridWidth/2 && OceanGrid.borderOil.size()!=0){
    		flag2=1;
    		System.out.println("uncalled");
    	}
    	else {
    	loopi =bt.getPosX();
    	loopj = bt.getPosY();
    	int gridH=OceanGrid.gridHeight-bt.getPosX();
    	int gridW=OceanGrid.gridWidth-bt.getPosY();
    	
    	while(loopj<gridH-1 && done ) {
    		if (MySimulation.paused)
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		else {
    		bt.setDirection("W");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
    			
    			flag=1;}
    		MyAppUI.canvas.repaint();
    		
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopj++;}
//    		og.getOilSpread();
       	}
    	
    	while(loopi<gridW-1 && done ) {
    		if (MySimulation.paused)
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		else {
    		bt.setDirection("S");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);flag=1;
    			}
    			
    		MyAppUI.canvas.repaint();
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopi++;}
//    		og.getOilSpread();
    	}
    	
    	while(loopj>bt.getPosY()&& done ) {
    		if (MySimulation.paused)
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		else {
    		bt.setDirection("E");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);flag=1;
    			}
    			
    		MyAppUI.canvas.repaint();
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopj--;}
//    		og.getOilSpread();
    	}
    	
    	while(loopi>bt.getPosX() && done ) {
    		if (MySimulation.paused)
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		else {
    		bt.setDirection("N");
    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);flag=1;
    			}
    		MyAppUI.canvas.repaint();
    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
    		loopi--;}
//    		og.getOilSpread();
    	}
    	
    	bt.setBatteryCapacity(bt.getBatteryCapacity()-10);
    	bt.moveTo(bt.getPosX()+1, bt.getPosY()+1);
    	}
    	}
    }
    	   	
}
	public void boatTravelBackward() {
		if(done) {
		if(bt.getPosX()==0 && bt.getPosY()==0) {
			flag2=0;
			boatTravelForward();
		}
		
		else {
	    	loopi = bt.getPosX();
	    	loopj = bt.getPosY();
	    	int gridH=OceanGrid.gridHeight-bt.getPosX();	  
	    	int gridW=OceanGrid.gridWidth-bt.getPosY();
	    	
	    	while(loopi<gridH-1 && done ) {
	    		if (MySimulation.paused)
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		else{
	    		bt.setDirection("N");
	    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
	    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
	    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
//	    			
	    			}
	    		MyAppUI.canvas.repaint();
	    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
	    		loopi++;}
//	    		og.getOilSpread();
	    	}
	    	
	    	while(loopj<gridW-1 && done ) {
	    		if (MySimulation.paused)
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		else {
	    		bt.setDirection("E");
	    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
	    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
	    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
//	    			
	    			}
	    			
	    		MyAppUI.canvas.repaint();
	    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
	    		loopj++;}
//	    		og.getOilSpread();
	    	}
	    	
	    	while(loopi>bt.getPosX() && done ) {
	    		if (MySimulation.paused)
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		else {
	    		bt.setDirection("S");
	    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
	    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
	    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
	    			}
	    			
	    		MyAppUI.canvas.repaint();
	    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
	    		loopi--;}
//	    		og.getOilSpread();
	    	}
	    	
	    	while(loopj>bt.getPosY() && done ) {
	    		if (MySimulation.paused)
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		else {
	    		bt.setDirection("W");

	    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
	    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
	    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
	    			
	    			}
	    		MyAppUI.canvas.repaint();
	    		
	    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
	    		loopj--;}
//	    		og.getOilSpread();
	    		}
	    	

	    	bt.setBatteryCapacity(bt.getBatteryCapacity()-10);
	    	bt.moveTo(bt.getPosX()-1, bt.getPosY()-1);
	    	}
		}
		
		
	}
	
	public void run() {
		while(done) {
			if (MySimulation.paused)
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			else {
				boatTravelForward();
			}
			
		}
	}


}
