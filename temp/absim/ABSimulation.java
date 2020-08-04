package edu.neu.csye6200.absim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ABSimulation {
	
	OceanGrid og = new OceanGrid();
	private JFrame frame = null;
	private JPanel mainPanel = null;
	private JButton startBtn = null;
	private JButton stopBtn = null;
	private JPanel statPanel = null;
	private JTextField stats = null;
	private Thread threadBoat = null;
	private Thread threadOil = null;
	
	private JPanel getMainPanel() {
		mainPanel= new JPanel();
		mainPanel.setLayout(new FlowLayout());
		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		mainPanel.add(startBtn);
		mainPanel.add(stopBtn);
		mainPanel.setBackground(Color.GREEN);
		return mainPanel;
		
		}
	
	private void getSimuPanel() {
		OceanGrid.abPanel = new ABPanel(OceanGrid.gridData);
		OceanGrid.abPanel.setLayout(new FlowLayout());
	}
	
	private JPanel getStatPanel() {
		statPanel= new JPanel();
		statPanel.setLayout(new FlowLayout());
		stats = new JTextField(Double.toString(OceanGrid.oilSpreadPerc));
		statPanel.setBackground(Color.GREEN);
		return statPanel;
		
	}
	
	public void run() {
		og.makeGrid();
		frame = new JFrame("Oil Spread Simulation");
		frame.setSize(400,500);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());//Our frame uses NSEW enter layout
		
		//Add the button Panel
		frame.add(getMainPanel(), BorderLayout.NORTH);
		
		//Adding the Simulation panel
		getSimuPanel();
		
        frame.add(OceanGrid.abPanel, BorderLayout.CENTER);
        
        frame.add(getStatPanel(),BorderLayout.SOUTH);
        
        
//        frame.pack(); // Makes size according to panel's preference
        frame.setVisible(true);
        
		OceanGrid.borderOil.add(OceanGrid.gridData[OceanGrid.gridWidth/2-1][OceanGrid.gridHeight/2-1]);
		
//		int counter=OceanGrid.gridHeight*OceanGrid.gridWidth+10;
//		while(counter>0) {
//			runOil();
//			counter--;
//		}
		
		
		ABRule abr = new ABRule();
		threadOil = new Thread(og);
		threadBoat =  new Thread(abr);
		threadBoat.start();
		threadOil.start();
		
//		counter =OceanGrid.gridWidth/2;//runs 10 times 
//		while(counter>0) {
//			runBoat();
//			counter--;
//			}
		
		System.out.println("Simulation has ended");
		
	}
	
	public void runOil() {
		OceanGrid og = new OceanGrid();
		og.spreadOil();
		og.getOilSpread();
		System.out.println("Oil Spread(%) : "+og.oilSpreadPerc);
	}
	
	public static void main (String args[]) {
		ABSimulation abs = new ABSimulation();
		abs.run();	
}
	}
