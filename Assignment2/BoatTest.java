/*
Assignment 2AB: Cleaning Boat Scheduler
Name: David Nallapu
NUID : 001530978
*/

public class BoatTest {

	public void run() {
		System.out.println("----------------------------- Boat Test Starting -----------------------------\n");
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
		String st = String.format("%1$6s %2$6s %3$6s %4$8s %5$11s %6$18s %7$20s ","Name","Pos(X)","Pos(Y)","Heading","Speed(MPH)","Load Capacity(lb)","Battery Capacity(%)");
		System.out.println(st);
		for (Boat boat : boatList) {

			System.out.println(boat.toFormattedString());
		}
		// Testing moveTo method with (8,10) as parameters
		boatList[0].moveTo(8,10);
		// Testing moveTo method with (-8,-10) as parameters
		boatList[0].moveTo(-8,-10);
		
		System.out.println("------------- Testing BoatScheduler and BoatTask class -------------\n");
		//Test BoatTask instances intialized 
		BoatTask t1 = new BoatTask("Task 1", "Clean up Area 1", new DateTime(2020,10,12,12,1,1), boatList[0]);
		BoatTask t2 = new BoatTask("Task 2", "Clean up Area 2", new DateTime(2019,10,12,12,1,1), boatList[1]);
		
		BoatScheduler bs = new BoatScheduler();
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding Task 1 with Boat 1 to ArrayList and HashMap");
		System.out.println("--------------------------------------------------------------------------");
		bs.addTask(t1);
		bs.printTasks();
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding Task 2 with Boat 2 to ArrayList and HashMap");
		System.out.println("--------------------------------------------------------------------------");
		bs.addTask(t2);
		bs.printTasks();

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Retrieving Task 2 from ArrayList");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Printing from ArrayList ...");
		BoatTask t = bs.getTask(t2.getId()); //bs.getTask(2); would also work as we know the Task ID of the object we want to retrieve 
		System.out.println(String.format("%1$6s %2$7s %3$16s %4$s %5$15s ","Task ID","Name","Objective","Start Date","Assigned Boat"));
		System.out.println(String.format("%1$6s %2$8s %3$16s %4$2d-%5$2d-%6$4d %7$15s \n", t.getId(), t.taskName,t.Objective,t.startDate.month,t.startDate.day,t.startDate.year,t.assignedBoat.boatName));

		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Retrieving Boat 1 from HashMap ");
		System.out.println("--------------------------------------------------------------------------");
		BoatTask fromMap = bs.retrieveFromTaskMap(boatList[0]); // Boat ID here is the Boat Instance itself 
		System.out.println("Printing from HashMap ...");
		System.out.println("Boat ID : "+fromMap.assignedBoat+" , Boat Name : "+fromMap.assignedBoat.boatName+" , Task ID : "+fromMap.taskName+"\n");
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Removing Task 1 with Boat 1 from ArrayList and HashMap");
		System.out.println("--------------------------------------------------------------------------");
		bs.removeTask(t1.getId()); //bs.removeTask(1); would also work as we know the Task ID of the object we want to remove 
		bs.printTasks();
		
		System.out.println("----------------------------- Boat Test End -------------------------------");
		
		
	}
	
	public static void main(String[] args) {		
		BoatTest bt = new BoatTest();
		bt.run();
	}

}
