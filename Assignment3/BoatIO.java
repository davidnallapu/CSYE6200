

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BoatIO {

	private FileWriter writer;
	private String fileReader;

	private void save(Boat b, File f) {
		try {
			writer = new FileWriter(f, true);
			String boatObject = new String();
			boatObject+=b.boatName+"|"+b.posX+"|"+b.posY+"|"+b.heading+"|"+b.speed+"|"+b.loadCapacity+"|"+b.batteryCapacity+"|";
			if(b instanceof TransportBoat) {
				boatObject+=((TransportBoat) b).height+"|"+((TransportBoat) b).width+"|"+((TransportBoat) b).length+"|";
			}
			writer.write(boatObject);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		
	}
	

	public void loadBoat(Boat[] boatList, String fileName) {
		File file = new File(fileName);
		try {
			FileReader reader = new FileReader(file);
			int inVal = reader.read();
			fileReader = new String();
			while(inVal>=0) {
				fileReader+=(char)inVal;
				inVal = reader.read();
			}
			
			String boats[] = fileReader.split("\n");
			for(int i =0; i<boats.length;i++) {
				String temp[] = boats[i].split("\\|");
				if(temp.length <=7) {
					boatList[i] = new Boat(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), 
							Double.parseDouble(temp[4]), Double.parseDouble(temp[5]), Integer.parseInt(temp[6]));
					System.out.println(boatList[i].toFormattedString());				}
				else {
					boatList[i] = new TransportBoat(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),Double.parseDouble(temp[4]), Double.parseDouble(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]),Integer.parseInt(temp[8]),Integer.parseInt(temp[9]));
					System.out.println(boatList[i].toFormattedString());
				}
			}
			System.out.println();
			reader.close();
//			System.out.println("Deleting File");
//			file.delete();
//			System.out.println("Deleted");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
		
		
	
	
	public void saveBoat(Boat[] boatList, String fileName) {
			File file = new File(fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			for(Boat b : boatList) {
				save(b, file);
			}
			System.out.println("Location Saved : "+file.getAbsolutePath());
		
	
}}
