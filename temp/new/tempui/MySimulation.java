package edu.neu.csye6200.tempui;

import java.util.Observable;

public class MySimulation implements Runnable{

	public static boolean paused = false;
	public static boolean done = false; // set true to end the simulation loop
	private Thread thread = null;
	private Thread threadOil= null;
	private Thread threadBoat= null;
	private OceanGrid og = new OceanGrid();
	private ABRule abr = new ABRule();
	
	@Override
	public void run() {
		while(!done) {
    	if (paused)
    		sleepABit(1000L);
		else 
		{
			doWork();
			sleepABit(500L);
		}
		}
	}
	
	
	private void doWork() {
		abr.setDone(true);
		if(threadOil == null && threadBoat == null) {
			threadOil = new Thread(og);
			threadBoat = new Thread(abr);
		}
		if(!threadOil.isAlive()) {
			threadOil.start();
			threadBoat.start();
		}

		}


	private void sleepABit(long sleepLen) {
		try {
			Thread.sleep(sleepLen);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setDone(boolean done) {
		this.done=done;
		OceanGrid.done=true;
		ABRule.done =false;
	}
	
	public boolean isPaused() {
		return this.paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused=paused;
	}

	public void start() {
		if(done = false) return;
		if(thread == null)
			thread = new Thread(this);
		if(!thread.isAlive())
			thread.start();
	}
	
	public void pause() {
			setPaused(!paused);
		}
	
	public void stop() {
		setDone(true);
	}


}
