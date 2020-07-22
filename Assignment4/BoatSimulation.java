package edu.neu.csye6200.boat;

import java.util.Random;
public class BoatSimulation {
	
	private void update(Boat b, int newPosX, int newPosY) {
		b.moveTo(newPosX, newPosY);
	}
	public void run(Boat b) {
		int counter = 5;
		System.out.println("\nOriginal Position : ("+b.posX+", "+b.posY+")\n");
		while (counter>0){
			Random random = new Random(); 
			int newPosX = random.nextInt(20);
			int newPosY = random.nextInt(20);;
			update(b, newPosX,newPosY);
			counter--;
		}
		System.out.println("Final Position : ("+b.posX+", "+b.posY+")\n");
	}
}
