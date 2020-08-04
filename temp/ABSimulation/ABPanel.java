package ABSimulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ABPanel extends JPanel{
	public static final Color[]    colors = {new Color(0, 0, 0), new Color(0, 50, 200), new Color(0, 200, 0)};
	public static final int SCALE = 10;
	
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
            	if(grid[i][j].oilSpread==100)
            		g.setColor(new Color(0, 0, 0));
            	else if(grid[i][j].oilSpread==50)
            		g.setColor(new Color(114,133,165));
            	else
                	g.setColor(new Color(0, 119, 190));
            	g.fillRect(j*SCALE, i*SCALE, SCALE, SCALE);
            }
    	}
    }
}
