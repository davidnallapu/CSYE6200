package edu.neu.csye6200.absim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

public class ABPanel extends JPanel{
	public static final Color[]   colors = {new Color(0, 0, 0), new Color(0, 50, 200), new Color(0, 200, 0)};
	public static final int SCALE = 20;
	private OceanGrid[][] grid;
    private int width;
    private int height;
    
    public ABPanel(OceanGrid[][] g) {
    	grid=g;
    	width= g[0].length;
    	height=g.length;
    	setPreferredSize(new Dimension(width*SCALE, height*SCALE));
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
            	if(grid[i][j].oilSpread==100){
            		g.setColor(new Color(0, 0, 0));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		}
            	else if(grid[i][j].oilSpread==80){
            		g.setColor(new Color(0,128,255));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		g.setColor(Color.BLACK);
            	    g.fillOval(j*SCALE, i*SCALE, SCALE-2, SCALE-2);}
            	else if(grid[i][j].oilSpread==60){
            		g.setColor(new Color(0,128,255));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		g.setColor(Color.BLACK);
            	    g.fillOval(j*SCALE, i*SCALE, SCALE-4, SCALE-4);}
            	else if(grid[i][j].oilSpread==60){
            		g.setColor(new Color(0,128,255));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		g.setColor(Color.BLACK);
            	    g.fillOval(j*SCALE, i*SCALE, SCALE-6, SCALE-6);}
            	else if(grid[i][j].oilSpread==40){
            		g.setColor(new Color(0,128,255));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		g.setColor(Color.BLACK);
            	    g.fillOval(j*SCALE, i*SCALE, SCALE-8, SCALE-8);}
            	else if(grid[i][j].oilSpread==20) {
            		g.setColor(new Color(0,128,255));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            		g.setColor(Color.BLACK);
            	    g.fillOval(j*SCALE, i*SCALE, SCALE-10, SCALE-10);}
            	else {
            		g.setColor(new Color(0,128,255));
            		g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);}
            }
    	}
    }
}
