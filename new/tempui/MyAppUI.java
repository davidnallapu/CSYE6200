package edu.neu.csye6200.tempui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * A simple application example that demonstrates inheritance from an abstract class
 * @author mgmunson
 */
public class MyAppUI {

	private Logger log = Logger.getLogger(MyAppUI.class.getName());
	
	private JFrame frame; 
	private JPanel mainPanel;
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
		mySim = new MySimulation();
		initGUI(); // Initialize the sim
	}

	/*
	 * Initialize the simulation
	 */
	private void initGUI() {
		frame = new JFrame();
		frame.setSize(500,400);
		frame.setTitle("app");
		frame.setResizable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		frame.add(getMainPanel(), BorderLayout.NORTH);
		
		canvas = new MyCanvas();
		frame.add(canvas, BorderLayout.CENTER);
		frame.setVisible(true);
		
		//
	}
	
	
	
	private JPanel getMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		
		startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start pressed");
				mySim.start();
			}
		});
		
		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pause pressed");
				mySim.pause();
			}
		});
		
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("stop pressed");
				mySim.stop();
			}
		});
		
		comboBox = new JComboBox();
		comboBox.addItem("Simple");
		comboBox.addItem("Complex");
		
		
		
		mainPanel.add(startBtn);
		mainPanel.add(pauseBtn);
		mainPanel.add(stopBtn);
		
		mainPanel.add(new JLabel("Rule:"));
		mainPanel.add(comboBox);
		
		mainPanel.setBackground(Color.BLUE);
		return mainPanel;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
         ///MyAppUI myApp = new MyAppUI();
	  new MyAppUI();
      System.out.println("MyAppIO is exiting !!!!!!!!!!!!!!");
	}
	
}





