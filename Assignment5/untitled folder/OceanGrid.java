package edu.neu.csye6200.absim;

import java.util.ArrayList;


class OceanGrid implements Runnable {
    int R;
    int C;
    int oilSpread;
    public static boolean done = false;
    public static int totalOil = 10000;

    //Default constructor 
    OceanGrid() {

    }

    //For OceanGrid box with oil Spread
    OceanGrid(int R, int C, int oilSpread) {
        this.R = R;
        this.C = C;
        this.oilSpread = oilSpread;
    }

    public static int gridWidth = 20;
    public static int gridHeight = 20;
    public static OceanGrid gridData[][] = new OceanGrid[gridHeight][gridWidth];
    public static ArrayList < OceanGrid > borderOil = new ArrayList < OceanGrid > ();

    public void makeGrid() {
    	
        for (int i = 0; i < gridHeight; i++) {
            OceanGrid gridRow[] = new OceanGrid[gridHeight];
            for (int j = 0; j < gridWidth; j++) {
                gridRow[j] = new OceanGrid(i, j, 0);
            }
            gridData[i] = gridRow;
        }
    }


    public void spreadOil() {
        if (done = false) return;

        if (totalOil <= 0) return;

        for (OceanGrid gb: borderOil) {
            if (gb.oilSpread == -1 && totalOil >= 20) {
                {gb.oilSpread = -2;
                totalOil -= 20;  
                }
            } else if (gb.oilSpread < 100 && gb.oilSpread >= 0 && totalOil >= 20) {
                gb.oilSpread += 20;
                totalOil -= 20;
                if (gb.oilSpread == 100) {
                    updateGrid(gb);
                    borderOil.remove(gb);
                    break;
                }
            }
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {};
    }


    public void updateGrid(OceanGrid gb) {
        //Check W
        if (gb.R > -1 && gb.C - 1 > -1 && gb.R <= gridHeight - 1 && gb.C - 1 <= gridWidth - 1 && (gridData[gb.R][gb.C - 1].oilSpread == 0 || gridData[gb.R][gb.C - 1].oilSpread == -1))
            borderOil.add(gridData[gb.R][gb.C - 1]);

        //Check NW
        if (gb.R - 1 > -1 && gb.C - 1 > -1 && gb.R - 1 <= gridHeight - 1 && gb.C - 1 <= gridWidth - 1 && (gridData[gb.R - 1][gb.C - 1].oilSpread == 0 || gridData[gb.R - 1][gb.C - 1].oilSpread == -1))
            borderOil.add(gridData[gb.R - 1][gb.C - 1]);

        //Check N
        if (gb.R - 1 > -1 && gb.C > -1 && gb.R - 1 <= gridHeight - 1 && gb.C <= gridWidth - 1 && (gridData[gb.R - 1][gb.C].oilSpread == 0 || gridData[gb.R - 1][gb.C].oilSpread == -1))
            borderOil.add(gridData[gb.R - 1][gb.C]);

        //Check NE
        if (gb.R - 1 > -1 && gb.C + 1 > -1 && gb.R - 1 <= gridHeight - 1 && gb.C + 1 <= gridWidth - 1 && (gridData[gb.R - 1][gb.C + 1].oilSpread == 0 || gridData[gb.R - 1][gb.C + 1].oilSpread == -1))
            borderOil.add(gridData[gb.R - 1][gb.C + 1]);

        //Check E
        if (gb.R > -1 && gb.C + 1 > -1 && gb.R <= gridHeight - 1 && gb.C + 1 <= gridWidth - 1 && (gridData[gb.R][gb.C + 1].oilSpread == 0 || gridData[gb.R][gb.C + 1].oilSpread == -1))
            borderOil.add(gridData[gb.R][gb.C + 1]);

        //Check SE
        if (gb.R + 1 > -1 && gb.C + 1 > -1 && gb.R + 1 <= gridHeight - 1 && gb.C + 1 <= gridWidth - 1 && (gridData[gb.R + 1][gb.C + 1].oilSpread == 0 || gridData[gb.R + 1][gb.C + 1].oilSpread == -1))
            borderOil.add(gridData[gb.R + 1][gb.C + 1]);

        //Check S
        if (gb.R + 1 > -1 && gb.C > -1 && gb.R + 1 <= gridHeight - 1 && gb.C <= gridWidth - 1 && (gridData[gb.R + 1][gb.C].oilSpread == 0 || gridData[gb.R + 1][gb.C].oilSpread == -1))
            borderOil.add(gridData[gb.R + 1][gb.C]);

        //Check SW
        if (gb.R + 1 > -1 && gb.C - 1 > -1 && gb.R + 1 <= gridHeight - 1 && gb.C - 1 <= gridWidth - 1 && (gridData[gb.R + 1][gb.C - 1].oilSpread == 0 || gridData[gb.R + 1][gb.C - 1].oilSpread == -1))
            borderOil.add(gridData[gb.R + 1][gb.C - 1]);

    }

    public static double getOilSpread() {
        return 100 - (totalOil * 100 / 10000);
    }

    @Override
    public void run() {
        while (!done) {
            if (MySimulation.paused)
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            else {
                spreadOil();

            }
        }
    }


}