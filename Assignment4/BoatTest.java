
/*
Assignment 4: Cleaning Boat Simulation
Name: David Nallapu
NUID : 001530978
*/
package edu.neu.csye6200.boat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class BoatTest {

	public void run() {
		System.out.println("--------------------------------------- Boat Test Starting(Updated with BoatSimulation class and Logging) ---------------------------------------\n");
		//Arrays to hold Boat instance values before initializing 
		String[] boatNames = new String[]{"Boat 1","Boat 2","Boat 3","Boat 4","Boat 5"};
		int [] boatPosX = new int[]{0,1,2,3,4};
		int [] boatPosY = new int[]{0,2,3,4,5};
		int [] boatHeading = new int[]{90,45,180,270,90};
		double [] boatSpeed = new double[]{10.0,12.0,14.0,16.0,18.0};
		double [] boatLoadCapacity = new double[]{20.0,30.0,40.0,50.0,25.0};
		int [] boatBatteryCapacity = new int[]{10,20,30,40,50};
		
		//Initializing Boat instances with values from arrays
		Boat[] boatList = new Boat[5];
		for (int i=0; i <boatList.length; i++) {
			boatList[i] = new Boat (boatNames[i],boatPosX[i], boatPosY[i], boatHeading[i], boatSpeed[i], boatLoadCapacity[i], boatBatteryCapacity[i]);
		}
		
		//Printing Boat instance details with formatted strings 
		System.out.println(String.format("%1$6s %2$6s %3$6s %4$8s %5$11s %6$18s %7$20s ","Name","Pos(X)","Pos(Y)","Heading","Speed(MPH)","Load Capacity(lb)","Battery Capacity(%)"));
		for (Boat boat : boatList) {

			System.out.println(boat.toFormattedString());
		}
		// Testing moveTo method with (8,10) as parameters
//		boatList[0].moveTo(8,10);
		// Testing moveTo method with (-8,-10) as parameters
//		boatList[0].moveTo(-8,-10);
		
		System.out.println("--------------------------------------- Testing BoatScheduler and BoatTask class ---------------------------------------\n");
		//Test BoatTask instances intialized 
		BoatTask t1 = new BoatTask(3,"Task 3", "Clean up Area 3", new DateTime(2020,10,11,12,1,1), boatList[0]);
		BoatTask t2 = new BoatTask(10,"Task 10", "Clean up Area 6", new DateTime(2019,10,12,12,1,1), boatList[1]);
		BoatTask t3 = new BoatTask(9,"Task 9", "Clean up Area 9", new DateTime(2020,10,13,12,1,1), boatList[2]);
		BoatTask t4 = new BoatTask(4,"Task 4", "Clean up Area 4", new DateTime(2019,10,14,12,1,1), boatList[3]);
		BoatTask t5 = new BoatTask(8,"Task 8", "Clean up Area 8", new DateTime(2020,10,15,12,1,1), boatList[4]);
		BoatTask t6 = new BoatTask(2,"Task 2", "Clean up Area 2", new DateTime(2019,10,16,12,1,1), boatList[0]);
		BoatTask t7 = new BoatTask(1,"Task 1", "Clean up Area 1", new DateTime(2020,10,17,12,1,1), boatList[1]);
		BoatTask t8 = new BoatTask(5,"Task 5", "Clean up Area 4", new DateTime(2019,10,18,12,1,1), boatList[2]);
		BoatTask t9 = new BoatTask(6,"Task 6", "Clean up Area 10", new DateTime(2020,10,19,12,1,1), boatList[3]);
		BoatTask t10 = new BoatTask(7,"Task 7", "Clean up Area 5", new DateTime(2019,20,12,12,1,1), boatList[4]);
		
		
		//Adding Logger and routing all log messages to a disk file
		try {
			Handler handler = new FileHandler("BoatServer.log");
			Logger.getLogger("").addHandler(handler);			
		} catch (SecurityException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Logger File 'BoatServer.log' created.");
		
		//Creating a singleton copy of BoatScheduler class
		BoatScheduler bs = BoatScheduler.instance();
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding 10 Tasks to ArrayList and HashMap");
		System.out.println("--------------------------------------------------------------------------");
		bs.addTask(t1);
		bs.addTask(t2);
		bs.addTask(t3);
		bs.addTask(t4);
		bs.addTask(t5);
		bs.addTask(t6);
		bs.addTask(t7);
		bs.addTask(t8);
		bs.addTask(t9);
		bs.addTask(t10);
		bs.sortTasks();

//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("Retrieving Task 2 from ArrayList");
//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("Printing from ArrayList ...");
//		BoatTask t = bs.getTask(t2.getId()); //bs.getTask(2); would also work as we know the Task ID of the object we want to retrieve 
//		System.out.println(String.format("%1$6s %2$7s %3$16s %4$s %5$15s ","Task ID","Name","Objective","Start Date","Assigned Boat"));
//		System.out.println(String.format("%1$6s %2$8s %3$16s %4$2d-%5$2d-%6$4d %7$15s \n", t.getId(), t.taskName,t.Objective,t.startDate.month,t.startDate.day,t.startDate.year,t.assignedBoat.boatName));
//
//		
//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("Retrieving Boat 1 from HashMap ");
//		System.out.println("--------------------------------------------------------------------------");
//		BoatTask fromMap = bs.retrieveFromTaskMap(boatList[0]); // Boat ID here is the Boat Instance itself 
//		System.out.println("Printing from HashMap ...");
//		System.out.println("Boat ID : "+fromMap.assignedBoat+" , Boat Name : "+fromMap.assignedBoat.boatName+" , Task ID : "+fromMap.taskName+"\n");
//		
//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("Removing Task 1 with Boat 1 from ArrayList and HashMap");
//		System.out.println("--------------------------------------------------------------------------");
//		bs.removeTask(t1.getId()); //bs.removeTask(1); would also work as we know the Task ID of the object we want to remove 
//		bs.printTasks();
		
		System.out.println("--------------------------------------- Testing TranspoartBoat and BoatIO ---------------------------------------\n");
		//Creating a TranspoartBoat Instance
		TransportBoat tb1 =  new TransportBoat("T"+boatList[0].boatName, boatList[0].posX, boatList[0].posY, boatList[0].heading, boatList[0].speed, boatList[0].loadCapacity, boatList[0].batteryCapacity, 10, 20, 30);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Initializing TranspoartBoat object with CARGO calculated");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(String.format("%1$7s %2$6s %3$6s %4$8s %5$11s %6$18s %7$20s %8$5s","Name","Pos(X)","Pos(Y)","Heading","Speed(MPH)","Load Capacity(lb)","Battery Capacity(%)","CARGO"));
		System.out.println(tb1.toFormattedString());//Printing CARGO with super and updated method
		
		//Using Date and time as file name.
		LocalDateTime fileName = LocalDateTime.now(); 
		System.out.println("\n--------------------------------------------------------------------------");
		System.out.printf("Saving 5 Boats and 1 Transpoart Boat to '%0$s.txt' \n\n",fileName.toString());		
		
		//Creating a Boat Array to store the previous instances and a new TranspoartBoat 
		Boat[] boatListUpdated = new Boat[6];
		for (int i=0; i <boatList.length; i++) {
			boatListUpdated[i] = boatList[i];
		} 
		boatListUpdated[5] =tb1;

		//Saving Updated Boat Array in a txt file with BoatIO method
		BoatIO bio = new BoatIO();
		bio.saveBoat(boatListUpdated, fileName.toString()+".txt");	
		System.out.println("--------------------------------------------------------------------------\n");

		// Loading Boat Objects into boatListLoaded Array to display on console
		System.out.println("--------------------------------------------------------------------------");
		System.out.printf("Loading from '%0$s.txt' and displaying 5 Boat objects and 1 TransportBoat object\n",fileName.toString());
		System.out.println("--------------------------------------------------------------------------\n");
		
		Boat[] boatListLoaded = new Boat[6];
		bio.loadBoat(boatListLoaded, fileName.toString()+".txt");
		//This will generate 5 new Boat Instances and 1 new TranspoartBoat Instance. The benefit is that the data will be the same in the new instances. 
		
		System.out.println("--------------------------------------- TESTING BoatSimulation ---------------------------------------\n");
		BoatSimulation bsim = new BoatSimulation();
		
		for(Boat b: boatListLoaded){
			System.out.println("================================Starting Simulation with "+b.boatName+"================================");
			bsim.run(b);
		}
		
		System.out.println("--------------------------------------- End of Simulation ---------------------------------------\n");
		System.out.println("--------------------------------------- Boat Test End ---------------------------------------");
	
	}
	
	public static void main(String[] args) {		
		BoatTest bt = new BoatTest();
		bt.run();
	}

}


