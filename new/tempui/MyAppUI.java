package edu.neu.cyse6200.tempui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
public class MyAppUI implements ActionListener{

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
		mySim = new MySimulation("Sim");
		initGUI(); // Initialize the sim
	}

	/*
	 * Initialize the simulation
	 */
	private void initGUI() {
		frame = new JFrame();
		frame.setSize(600,660);
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
		startBtn.addActionListener(this);
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(this);
		
		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(this);
		
		mainPanel.add(startBtn);
		mainPanel.add(pauseBtn);
		mainPanel.add(stopBtn);

		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("hi");
		if(e.getActionCommand().equalsIgnoreCase("Start")) {
			mySim.start();
			
		}
		if(e.getActionCommand().equalsIgnoreCase("Pause")) {
			mySim.pause();
			
		}

		if(e.getActionCommand().equalsIgnoreCase("Stop")) {
			mySim.stop();
		}
	}
	
}





