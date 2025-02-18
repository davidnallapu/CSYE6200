package edu.neu.csye6200.absim;

public class ABRule implements Runnable {
    public static boolean done = false;
    public static Boat bt = new Boat("Cleaner", 0, 0, 90, "READY", 5, 0, 100, 0);
    public static int loopi = 0;
    public static int loopj = 0;
    public static int flag = 0;
    public static int boatRule = 1;
    

    public void boatRule2() {
        if (done) {
            if (bt.getLoadCapacity() * 100 / 8000 > 99 || bt.getBatteryCapacity() < 5) {
                fillOil();
            } else {
                int oldPosX = bt.getPosX();
                int gridW = OceanGrid.gridWidth;

                while (bt.getPosX() < gridW - 1 && done) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        cleanOil(bt.getPosX(), bt.getPosY());
                        bt.setPosX(bt.getPosX() + 1);
                    }
                }
                cleanOil(bt.getPosX(), bt.getPosY());
                bt.setPosY(bt.getPosY() + 1);
                
                while (bt.getPosX() > oldPosX && done) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        cleanOil(bt.getPosX(), bt.getPosY());
                        bt.setPosX(bt.getPosX() - 1);
                    }
                }
                bt.setBatteryCapacity(bt.getBatteryCapacity() - 10);
                cleanOil(bt.getPosX(), bt.getPosY());
                bt.moveTo(0, bt.getPosY() + 1);
                
            }
        }

    }

    public static int flagLoop = 1;
    public void boatRule1() {
        if (done) {
            if (bt.getLoadCapacity() * 100 / 8000 > 99 || bt.getBatteryCapacity() < 5) {
                fillOil();
            } else {

                if (flagLoop % 2 == 1) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        bt.setPosX(bt.getPosX() + 1);
                        cleanOil(bt.getPosX(), bt.getPosY());
                        for (int i = 0; i < flagLoop; i++) {
                            if (MySimulation.paused)
                                pausedBoat();
                            else {
                                bt.setPosY(bt.getPosY() + 1);
                                cleanOil(bt.getPosX(), bt.getPosY());
                            }

                        }
                        for (int i = 0; i < flagLoop; i++) {
                            if (MySimulation.paused)
                                pausedBoat();
                            else {
                                bt.setPosX(bt.getPosX() - 1);
                                cleanOil(bt.getPosX(), bt.getPosY());
                            }
                        }
                        bt.setBatteryCapacity(bt.getBatteryCapacity() - 10);
                    }

                } else {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        bt.setPosY(bt.getPosY() + 1);
                        MyAppUI.canvas.repaint();
                        timeSpeedDelay();
                        for (int i = 0; i < flagLoop; i++) {
                            if (MySimulation.paused)
                                pausedBoat();
                            bt.setPosX(bt.getPosX() + 1);
                            cleanOil(bt.getPosX(), bt.getPosY());
                        }
                        for (int i = 0; i < flagLoop; i++) {
                            if (MySimulation.paused)
                                pausedBoat();
                            bt.setPosY(bt.getPosY() - 1);
                            cleanOil(bt.getPosX(), bt.getPosY());
                        }
                    }

                }
                flagLoop++;
            }
        }
    }


    public void cleanOil(int newPosX, int newPosY) {
        bt.setStatus("CLEANING");
        if (bt.getLoadCapacity() * 100 / 8000 < 100 ) {
            if (OceanGrid.gridData[newPosX][newPosY].oilSpread > 0 ) {
                bt.setTotalOil(bt.getTotalOil() + OceanGrid.gridData[newPosX][newPosY].oilSpread);
                bt.setLoadCapacity(bt.getLoadCapacity() + OceanGrid.gridData[newPosX][newPosY].oilSpread);
                OceanGrid.gridData[newPosX][newPosY].oilSpread = 0;
                OceanGrid.borderOil.remove(OceanGrid.gridData[newPosX][newPosY]);
            } 
            else if (OceanGrid.gridData[newPosX][newPosY].oilSpread == -2) {
                OceanGrid.gridData[newPosX][newPosY].oilSpread = -1;
                bt.setTotalOil(bt.getTotalOil() + 20);
                bt.setLoadCapacity(bt.getLoadCapacity() + 20);
                OceanGrid.borderOil.remove(OceanGrid.gridData[newPosX][newPosY]);
            }
        }
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
        bt.setStatus("PAUSED");
        MyAppUI.southPanel.repaint();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int boatRule3X =0;
    private static int boatRule3Y =0;
    public void boatRule3() {
        if (done) {
            if (bt.getLoadCapacity() * 100 / 8000 > 99 || bt.getBatteryCapacity() < 5) {
                fillOil();
            } else {
                int oldPosX = bt.getPosX();
                int oldPosY = bt.getPosY();
                boatRule3X = bt.getPosX();
                boatRule3Y = bt.getPosY();
                int gridH = OceanGrid.gridHeight - bt.getPosX();
                int gridW = OceanGrid.gridWidth - bt.getPosY();
                System.out.println(bt.getPosX()+" "+bt.getPosY());
                //Moving East
                while (bt.getPosY() < gridH - 1 && done) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        cleanOil(bt.getPosX(), bt.getPosY());
                        bt.setPosY(bt.getPosY() + 1);
                    }
                }
                //Moving South
                while (bt.getPosX() < gridW - 1 && done) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        cleanOil(bt.getPosX(), bt.getPosY());
                        bt.setPosX(bt.getPosX() + 1);
                    }
                }
                //Moving West
                while (bt.getPosY() > oldPosX && done) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        cleanOil(bt.getPosX(), bt.getPosY());
                        bt.setPosY(bt.getPosY() - 1);
                    }
                }
                //Moving North
                while (bt.getPosX() > oldPosY && done) {
                    if (MySimulation.paused)
                        pausedBoat();
                    else {
                        cleanOil(bt.getPosX(), bt.getPosY());
                        bt.setPosX(bt.getPosX() - 1);
                    }
                }
                bt.setBatteryCapacity(bt.getBatteryCapacity() - 10);
                if(bt.getPosX()==bt.getPosY())
                bt.moveTo(bt.getPosX() + 1, bt.getPosY() + 1);
                else {
                	bt.moveTo(boatRule3X , boatRule3Y );
                }
            }
        }
    }

    public void fillOil() {
        while (bt.getPosX() > 0) {
            try {
                Thread.sleep((int)(1000 / bt.getSpeed()));
            } catch (Exception e) {};
            bt.setPosX(bt.getPosX() - 1);
            MyAppUI.canvas.repaint();
        }
        while (bt.getPosY() > 0) {
            try {
                Thread.sleep((int)(1000 / bt.getSpeed()));
            } catch (Exception e) {};
            bt.setPosY(bt.getPosY() - 1);
            MyAppUI.canvas.repaint();
        }
        bt.setStatus("REFILL");
        MyAppUI.southPanel.repaint();
        flag = 0;
        if (flagLoop > 1) flagLoop = 1;
        try {
            Thread.sleep(2000);
        } catch (Exception e) {};
        System.out.println(bt.getLoadCapacity());
        bt.setBatteryCapacity(100);
        bt.setLoadCapacity(0);
    }

    public void run() {
        while (done) {
            if (MySimulation.paused)
                pausedBoat();
            else {
                if (boatRule == 1)
                    boatRule1();
                else if (boatRule == 2) {
                    boatRule2();
                } else if (boatRule == 3) {
                    boatRule3();
                }
            }
        }
    }
}