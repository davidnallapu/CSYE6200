/*
Assignment 3: Cleaning Boat IO
Name: David Nallapu
NUID : 001530978
*/

class Boat {
	//Declaring variables for Boat class
	String boatName;
	int posX, posY,heading, batteryCapacity;
	double speed, loadCapacity;
	
	Boat(String boatName, int posX,int posY,int heading, double speed,double loadCapacity, int batteryCapacity ){
		setBoatName(boatName);
		setPosX(posX);
		setPosY(posY);
		setHeading(heading);
		setSpeed(speed);
		setLoadCapacity(loadCapacity);
		setBatteryCapacity(batteryCapacity);
	}
	//moveTo method with new Positions as parameters. Prints out points that Boat drives through to reach the end position. 
	public void moveTo(int newPosX, int newPosY){
		//variables to calculate steps taken in moveTo method
		int stepsX = 0;
		int stepsY = 0;
		
		//Using a Cartesian coordinate frame of reference
		System.out.println("\nOriginal Position : ("+posX+", "+posY+")");
		System.out.println("Testing moveTo() method with params = ("+newPosX+","+newPosY+")\n");
		if(newPosX > posX) {
			System.out.println("Moving Direction : 'N'");
			for(int i =0; i< newPosX- posX; i++) {
				System.out.print("("+(posX+i+1)+","+posY+"), ");
			}
			stepsX+=newPosX- posX;
			posX = newPosX;	
		}
		else if (newPosX < posX) {
			System.out.println("Moving Direction : 'S'");
			for(int i =0; i < posX - newPosX; i++) {
				System.out.print("("+(posX-i-1)+","+posY+"), ");
			}
			stepsX+=posX - newPosX;
			posX = newPosX;	
		}
		System.out.println();
		if(newPosY > posY) {
			System.out.println("Moving Direction : 'E'");
			for(int i =0; i< newPosY- posY; i++) {
				System.out.print("("+posX+","+(posY+i+1)+"), ");
			}
			stepsY+=newPosY- posY;
			posY = newPosY;		
		}
		else if (newPosY < posY) {
			System.out.println("Moving Direction : 'W'");
			for(int i =0; i < posY - newPosY; i++) {
				System.out.print("("+posX+","+(posY-i-1)+"), ");
			}
			stepsY+=posY - newPosY;
			posY = newPosY;	
		}
		
		System.out.println("\n\nFinal Position : ("+posX+", "+posY+")");
		System.out.println("Total steps on X axis : "+stepsX);
		System.out.println("Total steps on Y axis : "+stepsY);
		System.out.println();
		}
	
	
	public String getBoatName() {
		return boatName;
	}

	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getLoadCapacity() {
		return loadCapacity;
	}

	public void setLoadCapacity(double loadCapacity) {
		this.loadCapacity = loadCapacity;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	//Method to print formatted Boat Instance Details 
	public String toFormattedString() {
		String st = String.format("%1$7s %2$5d %3$6d %4$8s° %5$10s %6$18s %7$20s ", getBoatName(),getPosX(),getPosY(),getHeading(),getSpeed(),getLoadCapacity(),getBatteryCapacity() );
		return st;
	}
	
	
}



