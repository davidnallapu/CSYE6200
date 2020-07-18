package assignment3;

public class TransportBoat extends Boat {

	int height;
	int width;
	int length; 
	
	TransportBoat(String boatName, int posX, int posY, int heading, double speed, double loadCapacity,
			int batteryCapacity, int height, int width, int length) {
		super(boatName, posX, posY, heading, speed, loadCapacity, batteryCapacity);
		this.height=height;
		this.length =length;
		this.width = width;
	}
	
	
	public String toFormattedString() {
		return (super.toFormattedString() + "  "+cargoArea());
	}
	public int cargoArea() {
		return this.height*this.width*this.length;
	}

}
