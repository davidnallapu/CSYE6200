package edu.neu.csye6200.boat;

/*
Assignment 4: Cleaning Boat Simulation
Name: David Nallapu
NUID : 001530978
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class BoatScheduler {
	
	private static BoatScheduler instance = null;
	
	private BoatScheduler(){
		log.info("CONSTRUCTOR CALL : Singleton construction of BoatScheduler created. ");
	}// A private constructor 
	
	//call instance() to get a singleton copy of this class
	public static BoatScheduler instance() {
		if(instance==null) instance =new BoatScheduler();
		return instance;
	}
	//Using introspection to get class Name and creating a logger.
	private static Logger log = Logger.getLogger(BoatScheduler.class.getName());
	
	
	//Private ArrayList for storing BoatTask instance 
	private ArrayList<BoatTask> tasks =  new ArrayList<BoatTask>();

	//Private HashMap for storing BoatTask instance as a value and Boat instance as a key. There is a one-to-one relationship between a Boat and BoatTask in my approach. 
	private HashMap<Boat, BoatTask> taskMap = new HashMap<Boat, BoatTask>();
	
	//Method to get a BoatTask instance from ArrayList based on Task ID 
	public BoatTask getTask(int TaskID) {
		for (BoatTask t : tasks) {
		if (t.getId() == TaskID)
		 return t;}
			return null;
		}
	
	//Method to add a BoatTask instance to ArrayList and HashMap
	public void addTask(BoatTask task) {
		tasks.add(task);
		taskMap.put(task.assignedBoat, task);
	}
	
	//Method to remove a BoatTask instance from ArrayList and HashMap based on Task ID 
	public void removeTask(int TaskID) {
		for (BoatTask t : tasks) {
			if (t.getId() == TaskID)
			 tasks.remove(t);
			 taskMap.remove(t.assignedBoat);
			}
	}
	
	//Method to print all BoatTask objects in the ArrayList
	public void printTasks() {
		System.out.println("Printing from ArrayList ...");
		System.out.println(String.format("%1$6s %2$7s %3$16s %4$s %5$15s ","Task ID","Name","Objective","Start Date","Assigned Boat"));
		for (BoatTask t : tasks) {
			System.out.println(String.format("%1$6s %2$8s %3$16s %4$2d-%5$2d-%6$4d %7$15s", t.getId(), t.taskName,t.Objective,t.startDate.month,t.startDate.day,t.startDate.year,t.assignedBoat.boatName));
			
		}
		System.out.println();}
	
	//Method to retrieve BoatTask from HashMap with Boat instance as input parameter 
	public BoatTask retrieveFromTaskMap(Boat id){// Here the Boat ID is Boat object itself 
		return taskMap.get(id); 
	}
	
	//Method to sort tasks with Quick Sort ALgorithm
	public void sortTasks() {
		System.out.println("Original tasks ArrayList : ");
		System.out.println(String.format("%1$6s %2$7s %3$16s %4$s %5$15s ","Task ID","Name","Objective","Start Date","Assigned Boat"));
		for (BoatTask t : tasks) {
			System.out.println(String.format("%1$6s %2$8s %3$16s %4$2d-%5$2d-%6$4d %7$15s", t.getId(), t.taskName,t.Objective,t.startDate.month,t.startDate.day,t.startDate.year,t.assignedBoat.boatName));
			
		}
		System.out.println();
		//Getting integer array of all Task IDs
		int[] taskIDs = new int[tasks.size()];
		int j=0;
		for(BoatTask task : tasks) {
			taskIDs[j]=task.getId();
			j++;
		}
		
		Quicksort.qsort(taskIDs);//Call to QUick Sort method to run on the integer array
		ArrayList<BoatTask> sortedTasks =  new ArrayList<BoatTask>();
		
		System.out.println("Sorted tasks ArrayList : ");
		System.out.println(String.format("%1$6s %2$7s %3$16s %4$s %5$15s ","Task ID","Name","Objective","Start Date","Assigned Boat"));
		for(int i=0; i<taskIDs.length;i++) {
			for(BoatTask t : tasks) {
				if(t.getId() == taskIDs[i]) {
					sortedTasks.add(t);
				}
			}
		}
		//Replacing the unsorted tasks with sorted tasks 
		tasks = sortedTasks;
		for(BoatTask t : tasks) {
			System.out.println(String.format("%1$6s %2$8s %3$16s %4$2d-%5$2d-%6$4d %7$15s", t.getId(), t.taskName,t.Objective,t.startDate.month,t.startDate.day,t.startDate.year,t.assignedBoat.boatName));
		}
		
	}

}

//class for quick sort algorithm
class Quicksort{
	static void qsort(int[] items) {
		qs(items,0,items.length-1);
	}
	
	private static void qs(int[] items, int left, int right ) {
		int i,j;
		int x;
		int y;
		i=left; j=right;
		x=items[(left+right)/2];
		do {
			while((items[i] < x) && (i<right)) i++;
			while((x<items[j]) &&(j>left)) j--;
			
			if(i<=j) {
				y = items[i];
				items[i]=items[j];
				items[j]=y;
				i++;j--;
			}
		}while(i<=j);
		
		if(left<j) qs(items,left,j);
		if(i<right) qs(items,i, right);
	}
	
}
