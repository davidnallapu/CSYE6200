package edu.neu.csye6200.absim;

public class ABRule {
	OceanGrid og = new OceanGrid();
	
	public void boatTravel(Boat bt) {
    	if(bt.posX==og.gridHeight/2 && bt.posY==og.gridWidth/2) {
    		return;
    	}
    	else {
    	int loopi =bt.posX;
    	int loopj = bt.posY;
    	int gridH=og.gridHeight-bt.posX;
    	int gridW=og.gridWidth-bt.posY;
    	while(loopj<gridH-1) {
    		bt.direction="W";
    		if(og.gridData[loopi][loopj].oilSpread>0)
    			og.gridData[loopi][loopj].oilSpread-=100;
    		og.abPanel.repaint();
    		
    		try{ Thread.sleep((int)(1000/bt.speed)); } catch(Exception e){};
    		loopj++;
    		og.getOilSpread();
    		
    		System.out.println("Boat Cleanup (% remaining) :"+og.oilSpreadPerc+ "\nDirection : "+ bt.direction+"\nSpeed : "+bt.speed);
    	}
    	while(loopi<gridW-1) {
    		bt.direction="S";
    		if(og.gridData[loopi][loopj].oilSpread>0)
    			og.gridData[loopi][loopj].oilSpread-=100;
    		og.abPanel.repaint();
    		try{ Thread.sleep((int)(1000/bt.speed)); } catch(Exception e){};
    		loopi++;
    		og.getOilSpread();
    		System.out.println("Boat Cleanup (% remaining) :"+og.oilSpreadPerc+ "\nDirection : "+ bt.direction+"\nSpeed : "+bt.speed);
    	}
    	while(loopj>bt.posY) {
    		bt.direction="E";
    		if(og.gridData[loopi][loopj].oilSpread>0)
    			og.gridData[loopi][loopj].oilSpread-=100;
    		og.abPanel.repaint();
    		try{ Thread.sleep((int)(1000/bt.speed)); } catch(Exception e){};
    		loopj--;
    		og.getOilSpread();
    		System.out.println("Boat Cleanup (% remaining) :"+og.oilSpreadPerc+ "\nDirection : "+ bt.direction+"\nSpeed : "+bt.speed);
    	}
    	while(loopi>bt.posX) {
    		bt.direction="N";
    		if(og.gridData[loopi][loopj].oilSpread>0)
    			og.gridData[loopi][loopj].oilSpread-=100;
    		og.abPanel.repaint();
    		try{ Thread.sleep((int)(1000/bt.speed)); } catch(Exception e){};
    		loopi--;
    		og.getOilSpread();
    		System.out.println("Boat Cleanup (% remaining) :"+og.oilSpreadPerc+ "\nDirection : "+ bt.direction+"\nSpeed : "+bt.speed);
    	}
    	bt.posX+=1;
    	bt.posY+=1;
    	boatTravel(bt);
    	
    	}
    	   	
}


}
