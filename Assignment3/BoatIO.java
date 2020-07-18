package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BoatIO {
	
	private void save(Boat b, File f) {
		
	}
	
	public void loadBoat(Boat[] boatList, String fileName) {
		
		for (int i = 0; i< boatList.length; i++) {
		try {
			FileReader fr = new FileReader(fileName);
			int b = fr.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}}
		
		
	}
	
	public void saveBoat(Boat[] boatList, String fileName) {
		//FileWriter fw = new FileWriter(fileName);
		for (Boat b : boatList) {
			//save(b,f)
		}
	}

}
