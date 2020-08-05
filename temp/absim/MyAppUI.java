package edu.neu.csye6200.absim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyAppUI extends ABApp{

	private Logger log = Logger.getLogger(MyAppUI.class.getName());
	private JPanel northPanel;
	public static JPanel southPanel;
	private JButton startBtn;
	private JButton stopBtn;
	private JButton pauseBtn;
	
	
	private JComboBox<String> comboBox;
	public static MyCanvas canvas;

	private MySimulation mySim = null;
	
	/**
	 * Constructor
	 */
	public MyAppUI() {
		log.info("MyAppUI started");
		
		frame.setSize(600,500);
		frame.setTitle("Autonomous Boat Simulation");
		
		menuMgr.createDefaultActions(); // Set up default menu items
		
		initSim(); // Initialize the sim
//		initGUI();
		showUI(); // Initialize the GUI
	}
	
	
	/*
	 * Initialize the simulation
	 */
	private void initSim() {
		mySim = new MySimulation();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  new MyAppUI();
      System.out.println("MyAppIO is exiting !!!!!!!!!!!!!!");
	}

	@Override
	public JPanel getNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		
		startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start pressed");
				mySim.startSim();
			}
		});
		
		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pause pressed");
				mySim.pauseSim();
			}
		});
		
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("stop pressed");
				mySim.stopSim();
			}
		});
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Rule 1");
		comboBox.addItem("Rule 2");
		comboBox.addItem("Rule 3");
		
		northPanel.add(startBtn);
		northPanel.add(pauseBtn);
		northPanel.add(stopBtn);
		
		northPanel.add(new JLabel("Rule:"));
		northPanel.add(comboBox);
		
		northPanel.setBackground(new Color(76, 119, 153));
		return northPanel;
	}


	@Override
	public JPanel getCenterPanel() {
		canvas = new MyCanvas();
		return canvas;
	}
	
	@Override
	public JPanel getSouthPanel() {
		
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(new StatGraphics());
		
//		southPanel.add("hi", )
		southPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
		southPanel.setBackground(new Color(76, 119, 153));
		return southPanel;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public class StatGraphics extends JComponent {

		StatGraphics() {
			setPreferredSize(new Dimension(600,50));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("Oil Spread(%): "+Double.toString(OceanGrid.getOilSpread()),10, 10);
            g.drawString("Oil CleanUp(%): "+Double.toString(ABRule.bt.getTotalOil()*100/10000),210, 10);
            g.drawString("Boat Details: ",10, 27);
            g.drawString("Boat Speed: "+Double.toString(ABRule.bt.getSpeed()),10, 45);
            g.drawString("Boat Oil: "+Double.toString(ABRule.bt.getLoadCapacity()),210, 45);
            g.drawString("Boat Battery: "+Double.toString(ABRule.bt.getBatteryCapacity()),410, 45);
 
        }
	}

	
}





