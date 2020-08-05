package edu.neu.csye6200.absim;

public class ABRule implements Runnable {
    public static boolean done = false;
    public static Boat bt = new Boat("Cleaner", 0, 0, 90, "E", 30, 10, 100,0);
    public static int loopi = 0;
    public static int loopj = 0;
    public static int flag = 0;
    public void boatRule1() {
        if (done) {
            if (flag == 1) {
                fillOil();
            } else {
                if (bt.getPosX() == OceanGrid.gridHeight / 2 && bt.getPosY() == OceanGrid.gridWidth / 2 && OceanGrid.borderOil.size() != 0) {
                    flag = 1;
                } else {
                    int oldPosX = bt.getPosX();
                    int oldPosY = bt.getPosY();
                    int gridH = OceanGrid.gridHeight - bt.getPosX();
                    int gridW = OceanGrid.gridWidth - bt.getPosY();
                    
                    //Moving East
                    while (bt.getPosY() < gridH - 1 && done) {
                        if (MySimulation.paused)
                        	pausedBoat();
                        else {
                        	cleanOil(bt.getPosX(), bt.getPosY());
                            bt.setPosY(bt.getPosY()+1);
                        }
                    }
                    //Moving South
                    while (bt.getPosX() < gridW - 1 && done) {
                        if (MySimulation.paused)
                        	pausedBoat();
                        else {
                        	cleanOil(bt.getPosX(), bt.getPosY());
                            bt.setPosX(bt.getPosX()+1);
                        }
                    }
                    //Moving West
                    while (bt.getPosY() > oldPosX && done) {
                        if (MySimulation.paused)
                        	pausedBoat();
                        else {
                        	cleanOil(bt.getPosX(), bt.getPosY());
                        	bt.setPosY(bt.getPosY()-1);
                        }
                    }
                    //Moving North
                    while (bt.getPosX() > oldPosY && done) {
                        if (MySimulation.paused)
                        	pausedBoat();
                        else {
                        	cleanOil(bt.getPosX(), bt.getPosY());
                        	bt.setPosX(bt.getPosX()-1);
                        }
                    }
                    bt.setBatteryCapacity(bt.getBatteryCapacity() - 10);
                    bt.moveTo(bt.getPosX() + 1, bt.getPosY() + 1);
                }
            }
        }

    }

    public void boatRule2() {
        if (done) {
            if (flag == 1) {
                fillOil();
            } else {
                if (bt.getPosX() == 0 && bt.getPosY() == OceanGrid.gridWidth && OceanGrid.borderOil.size() != 0) {
                    flag = 1;
                    System.out.println("uncalled");
                } else {
                    loopi = bt.getPosX();
                    loopj = bt.getPosY();

                    int gridW = OceanGrid.gridWidth;

                    while (loopi < gridW - 1 && done) {
                        if (MySimulation.paused)
                            pausedBoat();
                        else {
                        	cleanOil(bt.getPosX(), bt.getPosY());
                            loopi++;
                        }
                    }
                    loopj++;
                    bt.setPosY(bt.getPosY() + 1);
                    while (loopi > bt.getPosX() && done) {
                        if (MySimulation.paused)
                        	pausedBoat();
                        else {
                        	cleanOil(bt.getPosX(), bt.getPosY());
                            loopi--;
                        }
                    }
                    bt.setBatteryCapacity(bt.getBatteryCapacity() - 10);
                    bt.moveTo(0, bt.getPosY() + 1);
                }
            }
        }
    }

    public static int flagLoop = 1;
    public void boatRule3() {
    	if (done) {
            if (flag == 1) {
                fillOil();
            } else {
                if (bt.getPosX() == 0 && bt.getPosY() == OceanGrid.gridWidth-1 && OceanGrid.borderOil.size() != 0) {
                    flag = 1;
                    System.out.println("uncalled");
                } else {
    	loopi = bt.getPosX();
        loopj = bt.getPosY();
    	if(flagLoop%2 == 1) {
    		if (MySimulation.paused)
                pausedBoat();
    		loopi++;
    		cleanOil(bt.getPosX(), bt.getPosY());
    		for(int i=0; i<flagLoop;i++) {
    			if (MySimulation.paused)
                    pausedBoat();
    			loopj++;
    			cleanOil(bt.getPosX(), bt.getPosY());
    		}
    		for(int i=0; i<flagLoop;i++) {
    			if (MySimulation.paused)
                    pausedBoat();
    			loopi--;
    			cleanOil(bt.getPosX(), bt.getPosY());
    		}
    	}
    	else {
    		if (MySimulation.paused)
                pausedBoat();
    		loopj++;
    		MyAppUI.canvas.repaint();
    		timeSpeedDelay();
    		for(int i=0; i<flagLoop;i++) {
    			if (MySimulation.paused)
                    pausedBoat();
    			loopi++;
    			cleanOil(bt.getPosX(), bt.getPosY());
    		}
    		for(int i=0; i<flagLoop;i++) {
    			if (MySimulation.paused)
                    pausedBoat();
    			loopj--;
    			cleanOil(bt.getPosX(), bt.getPosY());
    		}
    	}
    	flagLoop++;
    	bt.setPosX(loopi);
    	bt.setPosY(loopj);
    	}}}}
    
    public void cleanOil(int newPosX, int newPosY) {
    	if (OceanGrid.gridData[newPosX][newPosY].oilSpread > 0) {
    		System.out.println(" uo "+OceanGrid.gridData[newPosX][newPosY].oilSpread);
    		bt.setTotalOil(bt.getTotalOil()+OceanGrid.gridData[newPosX][newPosY].oilSpread);
            OceanGrid.gridData[newPosX][newPosY].oilSpread = 0;
            OceanGrid.borderOil.remove(OceanGrid.gridData[newPosX][newPosY]);
            
        }
        if (OceanGrid.gridData[newPosX][newPosY].oilSpread == -2) {
            OceanGrid.gridData[newPosX][newPosY].oilSpread = -1;
            OceanGrid.borderOil.remove(OceanGrid.gridData[newPosX][newPosY]);
        }
        
        System.out.println(bt.getTotalOil());
        MyAppUI.southPanel.repaint();
        MyAppUI.canvas.repaint();
        timeSpeedDelay();
    }

    public void timeSpeedDelay() {
    	try {
            Thread.sleep((int)(1000 / bt.getSpeed()));
        } catch (Exception e) {};
    }
    
    public void pausedBoat() {
    	try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public void fillOil() {
        while (bt.getPosX() > 0) {
            try {
                Thread.sleep((int)(1000 / bt.getSpeed()));
            } catch (Exception e) {};
            bt.setPosX(bt.getPosX()-1);
            MyAppUI.canvas.repaint();
        }
        while (bt.getPosY() > 0) {
            try {
                Thread.sleep((int)(1000 / bt.getSpeed()));
            } catch (Exception e) {};
            bt.setPosY(bt.getPosY()-1);
            MyAppUI.canvas.repaint();
        }
        flag = 0;
        if(flagLoop>1) flagLoop=1;
        try {
            Thread.sleep(2000);
        } catch (Exception e) {};
        bt.setBatteryCapacity(100);
    }

    public void run() {
        while (done) {
            if (MySimulation.paused)
            	pausedBoat();
            else {
            	MyAppUI.southPanel.repaint();
                boatRule1();
            }
        }
    }
}
//public void boatTravelBackward() {
//if(done) {
//if(bt.getPosX()==0 && bt.getPosY()==0) {
//	flag=0;
//	boatTravelForward();
//}
//
//else {
//	loopi = bt.getPosX();
//	loopj = bt.getPosY();
//	int gridH=OceanGrid.gridHeight-bt.getPosX();	  
//	int gridW=OceanGrid.gridWidth-bt.getPosY();
//	
//	while(loopi<gridH-1 && done ) {
//		if (MySimulation.paused)
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		else{
//		bt.setDirection("N");
//		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
//			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
//			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
////			
//			}
//		MyAppUI.canvas.repaint();
//		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
//		loopi++;}
////		og.getOilSpread();
//	}
//	
//	while(loopj<gridW-1 && done ) {
//		if (MySimulation.paused)
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		else {
//		bt.setDirection("E");
//		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
//			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
//			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
////			
//			}
//			
//		MyAppUI.canvas.repaint();
//		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
//		loopj++;}
////		og.getOilSpread();
//	}
//	
//	while(loopi>bt.getPosX() && done ) {
//		if (MySimulation.paused)
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		else {
//		bt.setDirection("S");
//		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
//			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
//			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
//			}
//			
//		MyAppUI.canvas.repaint();
//		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
//		loopi--;}
////		og.getOilSpread();
//	}
//	
//	while(loopj>bt.getPosY() && done ) {
//		if (MySimulation.paused)
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		else {
//		bt.setDirection("W");
//
//		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
//			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
//			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
//			
//			}
//		MyAppUI.canvas.repaint();
//		
//		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
//		loopj--;}
////		og.getOilSpread();
//		}
//	
//
//	bt.setBatteryCapacity(bt.getBatteryCapacity()-10);
//	bt.moveTo(bt.getPosX()-1, bt.getPosY()-1);
//	}
//}
//}


//private void boatTravelBackward2() {
//if(done) {
//	if(bt.getPosX()==0 && bt.getPosY()==0) {
//		flag=0;
//		boatTravelForward();
//	}
//	else {
//    	loopi = bt.getPosX();
//    	loopj = bt.getPosY();
//    	loopj--;
//    	System.out.println(loopi);
//    	System.out.println(loopj);
//    	int gridH=OceanGrid.gridHeight-bt.getPosX();	  
//    	int gridW=OceanGrid.gridWidth-bt.getPosY();
//    	while(loopi<gridH-1 && done ) {
//    		if (MySimulation.paused)
//				try {
//					Thread.sleep(1000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//    		else{
//    		bt.setDirection("N");
//    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
//    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
//    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
////    			
//    			}
//    		MyAppUI.canvas.repaint();
//    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
//    		loopi++;}
////    		og.getOilSpread();
//    	}
//    	loopj--;
//    	bt.setPosY(bt.getPosY()-1);
//    	
//    	while(loopi>bt.getPosX() && done ) {
//    		if (MySimulation.paused)
//				try {
//					Thread.sleep(1000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//    		else {
//    		bt.setDirection("S");
//    		if(OceanGrid.gridData[loopi][loopj].oilSpread>0)
//    			{OceanGrid.gridData[loopi][loopj].oilSpread=0;
//    			OceanGrid.borderOil.remove(OceanGrid.gridData[loopi][loopj]);
//    			}
//    			
//    		MyAppUI.canvas.repaint();
//    		try{ Thread.sleep((int)(1000/bt.getSpeed())); } catch(Exception e){};
//    		loopi--;}
////    		og.getOilSpread();
//    	}
//    	bt.setBatteryCapacity(bt.getBatteryCapacity()-10);
//    	bt.moveTo(0, bt.getPosY()-1);
//    
//	}	
//}	
//}
