/*
Assignment 4: Cleaning Boat Simulation
Name: David Nallapu
NUID : 001530978
*/
package edu.neu.csye6200.boat;

import java.util.Random;
public class BoatSimulation {
	
	private void update(Boat b, int newPosX, int newPosY) {
		b.moveTo(newPosX, newPosY);
	}
	
	public void run(Boat b) {
		//Counter to exit the while loop
		int counter = 5;
		System.out.println("\nOriginal Position : ("+b.posX+", "+b.posY+")\n");
		while (counter>0){
			Random random = new Random(); 
			//Setting random numbers to move the boat by calling update method which calls moveTo method.
			int newPosX = random.nextInt(20);
			int newPosY = random.nextInt(20);;
			update(b, newPosX,newPosY);
			counter--;
		}
	
		System.out.println("Final Position : ("+b.posX+", "+b.posY+")\n");
	}
}
