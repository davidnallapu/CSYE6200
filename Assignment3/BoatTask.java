/*
Assignment 2AB: Cleaning Boat Scheduler
Name: David Nallapu
NUID : 001530978
*/

public class BoatTask {
	private int id;
	private static int idCounter = 1;
	String taskName;
	String Objective;
	DateTime startDate;
	// Using a Boat Instance as Boat ID. There is a one-to-one relationship between a Boat and BoatTask in my approach. 
	Boat assignedBoat;
	
	BoatTask(String taskName, String Objective, DateTime startDate, Boat assignedBoat){
		setId();
		this.taskName = taskName;
		this.Objective = Objective;
		this.startDate = startDate;
		this.assignedBoat = assignedBoat;
	}
	
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = idCounter++;
	}
	
	
}

//Custom class for DateTime object for startDate variable 
class DateTime{
	int year;
	int month;
	int day;
	int hour;
	int min;
	int sec;
	
	DateTime(int year,int month,int day,int hour,int min,int sec){
		this.year= year;
		this.month =month ;
		this.day= day;
		this.hour =hour;
		this.min =min;
		this.sec =sec;
	}
}