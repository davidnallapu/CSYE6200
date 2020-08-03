/**
 * 
 */
package edu.neu.cyse6200.tempui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyCanvas extends JPanel {
	private OceanGrid og = new OceanGrid();
	private OceanGrid[][] grid;
	private int SCALE = 20;
	private BufferedImage img;
	
	/**
	 * 
	 */
	public MyCanvas()  {
		try {
			img = ImageIO.read( new File("/Users/davidnallapu/eclipse-workspace/OOD/src/edu/neu/csye6200/absim/images/boat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		og.makeGrid();
		OceanGrid.gridData[OceanGrid.gridWidth/2-1][OceanGrid.gridHeight/2-1].oilSpread=60;
		OceanGrid.borderOil.add(OceanGrid.gridData[OceanGrid.gridWidth/2-1][OceanGrid.gridHeight/2-1]);
		grid=OceanGrid.gridData;
	}

	// Swing calls when a redraw is needed
	public void paint(Graphics g) {
		drawCanvas(g);
	}

	// Draw the contents of the panel
	private void drawCanvas(Graphics g) {
		Graphics2D g1 = (Graphics2D) g;
		Dimension size = getSize();
		for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
            	if(grid[i][j].R == ABRule.loopi && grid[i][j].C == ABRule.loopj && OceanGrid.borderOil.size()>0) {
//            		System.out.println("uo");
//            		System.out.println("loopi "+ABRule.loopi);
//            		System.out.println("loopj "+ABRule.loopj);
            		g1.setColor(new Color(42,167,172));
            		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		g1.drawImage(img, j*SCALE, i*SCALE, SCALE, SCALE, this);
            	}
            	else {
			if(grid[i][j].oilSpread==100){
	    		g1.setColor(new Color(0, 0, 0));
	    		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
	    		}
	    	else if(grid[i][j].oilSpread==80){
	    		g1.setColor(new Color(42,167,172));
	    		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
	    		g1.setColor(Color.BLACK);
	    	    g1.fillOval(j*SCALE, i*SCALE, SCALE, SCALE);}
	    	else if(grid[i][j].oilSpread==60){
	    		g1.setColor(new Color(42,167,172));
	    		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
	    		g1.setColor(Color.BLACK);
	    	    g1.fillOval(j*SCALE+j/2, i*SCALE+i/2, SCALE/2, SCALE/2);}
	    	else if(grid[i][j].oilSpread==40){
	    		g1.setColor(new Color(42,167,172));
	    		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
	    		g1.setColor(Color.BLACK);
	    	    g1.fillOval(j*SCALE+j/3, i*SCALE+i/3, SCALE/3, SCALE/3);}
	    	else if(grid[i][j].oilSpread==20) {
	    		g1.setColor(new Color(42,167,172));
	    		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
	    		g1.setColor(Color.BLACK);
	    	    g1.fillOval(j*SCALE+j/4, i*SCALE+i/4, SCALE/4, SCALE/4);}
	    	else {
	    		g1.setColor(new Color(42,167,172));
	    		g1.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);}}}
            
    }
	}

}
