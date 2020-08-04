/**
 * 
 */
package edu.neu.csye6200.tempui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyCanvas extends JPanel implements Observer{
	private OceanGrid og = new OceanGrid();
	private OceanGrid[][] grid;
	private int SCALE = 20;
	private BufferedImage img;
	private ABRule abRule;
	
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
		g.setColor(Color.BLACK);
		drawCanvas(g);
	}

	// Draw the contents of the panel
	private void drawCanvas(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		g2d.fillRect(0, 0, size.width, size.height);
		int height = size.width/20;
		int width = size.height/20;
		drawOceanGrid(g2d, height, width);
	}
	
	private void drawOceanGrid(Graphics2D g2d, int height, int width) {
		for (int i=0; i<grid.length; i++) {
			boolean odd = (i & 0x01) > 0;
            for (int j=0; j<grid[0].length; j++) {
            	int startx = j*height;
            	if (odd)
					startx += height/2;
            	if(grid[i][j].R == ABRule.loopi && grid[i][j].C == ABRule.loopj && OceanGrid.borderOil.size()>0) {
            		g2d.setColor(new Color(42,167,172));
            		g2d.fillRect(startx, i*width, height-1, width-1);
            		g2d.drawImage(img, startx, i*width, height-2, width-2, this);
            	}
            	else {
			if(grid[i][j].oilSpread==100){
	    		g2d.setColor(new Color(0, 0, 0));
	    		g2d.fillRect(startx, i*width, height-1, width-1);
	    		}
	    	else if(grid[i][j].oilSpread==80){
	    		g2d.setColor(new Color(42,167,172));
	    		g2d.fillRect(startx, i*width, height-1, width-1);
	    		g2d.setColor(Color.BLACK);
	    	    g2d.fillOval(startx, i*width, height, width);}
	    	else if(grid[i][j].oilSpread==60){
	    		g2d.setColor(new Color(42,167,172));
	    		g2d.fillRect(startx, i*width, height-1, width-1);
	    		g2d.setColor(Color.BLACK);
	    	    g2d.fillOval(startx+j/2, i*width+i/2, height/2, width/2);}
	    	else if(grid[i][j].oilSpread==40){
	    		g2d.setColor(new Color(42,167,172));
	    		g2d.fillRect(startx, i*width, height-1, width-1);
	    		g2d.setColor(Color.BLACK);
	    	    g2d.fillOval(startx+j/3, i*width+i/3, height/3, width/3);}
	    	else if(grid[i][j].oilSpread==20) {
	    		g2d.setColor(new Color(42,167,172));
	    		g2d.fillRect(startx, i*width, height-1, width-1);
	    		g2d.setColor(Color.BLACK);
	    	    g2d.fillOval(startx+j/4, i*width+i/4, height/4, width/4);}
	    	else {
	    		g2d.setColor(new Color(42,167,172));
	    		g2d.fillRect(startx, i*width, height-1, width-1);}}}
            
    }
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof ABRule) {
			abRule = (ABRule) arg;
		}
		repaint(); // Tell the GUI thread that it should schedule a paint() call		
	}

}
