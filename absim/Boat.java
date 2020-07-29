package edu.neu.csye6200.absim;

class Boat {
	//Declaring variables for Boat class
	String boatName, direction;
	int posX, posY,heading, batteryCapacity;
	double speed, loadCapacity;
	
	Boat(String boatName, int posX,int posY,int heading, String direction, double speed,double loadCapacity, int batteryCapacity ){
		setBoatName(boatName);
		setPosX(posX);
		setPosY(posY);
		setHeading(heading);
		setDirection(direction);
		setSpeed(speed);
		setLoadCapacity(loadCapacity);
		setBatteryCapacity(batteryCapacity);
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	//moveTo method with new Positions as parameters. Prints out points that Boat drives through to reach the end position. 
	public void moveTo(int newPosX, int newPosY){
		//variables to calculate steps taken in moveTo method
		int stepsX = 0;
		int stepsY = 0;
		
		//Using a Cartesian coordinate frame of reference
		System.out.println("Moving to Position : ("+newPosX+", "+newPosY+")");
		if(newPosX > posX) {
			stepsX+=newPosX- posX;
			System.out.println("Moving "+stepsX+" steps in Direction : 'N'");
			for(int i =0; i< newPosX- posX; i++) {
				System.out.print("("+(posX+i+1)+","+posY+"), ");
			}
			
			posX = newPosX;	
		}
		else if (newPosX < posX) {
			stepsX+=posX-newPosX;
			System.out.println("Moving "+stepsX+" steps in Direction : 'S'");
			for(int i =0; i < posX - newPosX; i++) {
				System.out.print("("+(posX-i-1)+","+posY+"), ");
			}
			
			posX = newPosX;	
		}
		System.out.println();
		if(newPosY > posY) {
			stepsY+=newPosY- posY;
			System.out.println("Moving "+stepsY+" steps in Direction : 'E'");
			for(int i =0; i< newPosY- posY; i++) {
				System.out.print("("+posX+","+(posY+i+1)+"), ");
			}
			
			posY = newPosY;		
		}
		else if (newPosY < posY) {
			stepsY+=posY-newPosY;
			System.out.println("Moving "+stepsY+" steps in Direction : 'W'");
			for(int i =0; i < posY - newPosY; i++) {
				System.out.print("("+posX+","+(posY-i-1)+"), ");
			}
			posY = newPosY;	
		}
		System.out.println();System.out.println();
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



