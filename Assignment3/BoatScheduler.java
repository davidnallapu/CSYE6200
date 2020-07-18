package assignment3;
/*
Assignment 2AB: Cleaning Boat Scheduler
Name: David Nallapu
NUID : 001530978
*/

import java.util.ArrayList;
import java.util.HashMap;

public class BoatScheduler {

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



}
