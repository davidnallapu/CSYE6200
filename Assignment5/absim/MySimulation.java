package edu.neu.csye6200.absim;

public class MySimulation implements Runnable{

	public static boolean paused = false;
	public static boolean done = false; // set true to end the simulation loop
	private Thread thread = null;
	private Thread threadOil= null;
	private Thread threadBoat= null;
	private long simDelay = 500L; // time adjustment to slow down the simulation loop
	private boolean running = false; // set true if the simulation is running
	
	private OceanGrid og = new OceanGrid();
	private ABRule abr = new ABRule();
	
	public void startSim() {
		
		if (thread != null) return; // A thread is already running
		thread = new Thread(this); // Create a worker thread
		running = true;
		paused = false;
		done = false; // reset the done flag.
		thread.start();
	
	}
	
	public void pauseSim() {
			setPaused(!paused);
		}
	
	public boolean isPaused() {
		return MySimulation.paused;
	}
	
	public boolean isPausable() {
		if (!running) return false;
		if (done) return false;
		return true;
	}
	
	/**
	 * Is this simulation currently running?
	 * @return true if the simulation is active
	 */
	public boolean isRunning() {
		return running;
	}
	
	public void stopSim() {	
		System.out.println("Stop the simulation");
		if (thread == null) return; // defensive coding in case the thread is null
		setDone(true);
	}
	
	/**
	 * The main run method for this simulation.
	 */
	@Override
	public void run() {
		runSimLoop();
		thread = null; // flag that the simulation thread is finished
		threadBoat = null;
		threadOil = null;
	}
	
	/**
	 * A simulation loop that continuously runs
	 */
    private void runSimLoop() {
    	running = true;
    	while(!done) {
    		// do some simulation work
    		doWork();
    		if (!paused)
    		sleep(simDelay); // A half second sleep is the default
    	}
    	running = false;
    }
	
    private void sleep(long millis) {
    	try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	private void doWork() {
		ABRule.done=true;
		OceanGrid.done=false;
		if(threadOil == null && threadBoat == null) {
			threadOil = new Thread(og);
			threadBoat = new Thread(abr);
		}
		if(!threadOil.isAlive() && !threadBoat.isAlive()) {
			threadOil.start();
			threadBoat.start();
		}

		}
	
	public void setDone(boolean done) {
		System.out.println(ABRule.bt.getPosX()+" wow "+ABRule.bt.getPosY());
		MySimulation.done=done;
		OceanGrid.done=true;
		ABRule.done=false;
		ABRule.bt.setStatus("STOPPED");
		MyAppUI.southPanel.repaint();
	}
	


	public void setPaused(boolean paused) {
		MySimulation.paused=paused;
	}

	


}
