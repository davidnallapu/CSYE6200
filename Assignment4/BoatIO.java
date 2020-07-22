package edu.neu.csye6200.boat;

/*
Assignment 4: Cleaning Boat Simulation
Name: David Nallapu
NUID : 001530978
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;


public class BoatIO {
	
	private static Logger log = Logger.getLogger(BoatScheduler.class.getName());
	
	private FileWriter writer;
	private String fileReader;

	//Method to save a Boat Instance in a File
	private void save(Boat b, File f) {
		try {
			log.info("Logging : save method() called with "+b+" and File "+f+" as parameters");
			writer = new FileWriter(f, true);
			String boatObject = new String();
			//Using '|' as a delimiter and '\n' after each Boat instance
			boatObject+=b.boatName+"|"+b.posX+"|"+b.posY+"|"+b.heading+"|"+b.speed+"|"+b.loadCapacity+"|"+b.batteryCapacity+"|";
			if(b instanceof TransportBoat) {
				boatObject+=((TransportBoat) b).height+"|"+((TransportBoat) b).width+"|"+((TransportBoat) b).length+"|";
			}
			writer.write(boatObject);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("Severe : IO Exception - save() method failed");
			
		} 
	}
	
	// Method to load Boat Instances from text file.
	public void loadBoat(Boat[] boatList, String fileName) {
		File file = new File(fileName);
		try {
			log.info("Logging : loadBethod mmethod() to load info from txt file");
			System.out.println(String.format("%1$7s %2$6s %3$6s %4$8s %5$11s %6$18s %7$20s %8$5s","Name","Pos(X)","Pos(Y)","Heading","Speed(MPH)","Load Capacity(lb)","Battery Capacity(%)","CARGO"));
			FileReader reader = new FileReader(file);
			int inVal = reader.read();
			fileReader = new String();
			while(inVal>=0) {
				fileReader+=(char)inVal;
				inVal = reader.read();
			}
			//First Splitting by '\n' to get Strings for wach instance 
			String boats[] = fileReader.split("\n");
			for(int i =0; i<boats.length;i++) {
				String temp[] = boats[i].split("\\|");//Second splitting by '|' delimiter to get variable values for each instance
				if(temp.length <=7) {//If condition to create a Boat instance
					boatList[i] = new Boat(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), 
							Double.parseDouble(temp[4]), Double.parseDouble(temp[5]), Integer.parseInt(temp[6]));
					System.out.println(boatList[i].toFormattedString());				}
				else {//else condition to create a TranspoartBoat instance
					boatList[i] = new TransportBoat(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),Double.parseDouble(temp[4]), Double.parseDouble(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]),Integer.parseInt(temp[8]),Integer.parseInt(temp[9]));
					System.out.println(boatList[i].toFormattedString());
				}
			}
			System.out.println();
			reader.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.severe("Severe : FileNotFoundException Exception - Couldn't find File "+fileName);
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("Severe : IO Exception - loadBoat() method failed");
		}

	}
		
		
	
	//Method to save the Boat Instances. It calls the save method which stores each Boat Instance. 
	public void saveBoat(Boat[] boatList, String fileName) {
			File file = new File(fileName);
			log.info("Logging : saveBoat method() called to save each Boat from boatList");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				log.severe("Severe : IO Exception - createNewFile() method failed");
			}
		
			for(Boat b : boatList) {
				save(b, file);//Calling save method to save each Boat Instance
			}
			System.out.println("Location Saved : "+file.getAbsolutePath());
}}
