package edu.neu.csye6200.absim;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyAppUI extends ABApp{

	private Logger log = Logger.getLogger(MyAppUI.class.getName());
	
	private JPanel northPanel;
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
		comboBox.addItem("Simple");
		comboBox.addItem("Complex");
		
		
		
		northPanel.add(startBtn);
		northPanel.add(pauseBtn);
		northPanel.add(stopBtn);
		
		northPanel.add(new JLabel("Rule:"));
		northPanel.add(comboBox);
		
		northPanel.setBackground(Color.BLUE);
		return northPanel;
	}


	@Override
	public JPanel getCenterPanel() {
		canvas = new MyCanvas();
		return canvas;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	
}





