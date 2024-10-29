package helper;


import entities.Password;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import DataStorage.DataStorage;
import boundaries.Authenticator;
import inventoryManagement.InventoryDisplay;
import inventoryManagement.SimpleConsoleDisplay;
import entities.User;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		DataStorage dataStorage = new DataStorage();
		InventoryDisplay inventoryDisplay = new SimpleConsoleDisplay(dataStorage.inventory);
		inventoryDisplay.displayInventory();
		
		/*
		DataPrinter reading=new MedicineListPrinter();
		reading.print();
		DataPrinter readpatientlist=new PatientListPrinter();
		readpatientlist.print();
		DataWriter writing= new PatientListWriter();
		// (row, column, message)
		writing.write(1, 1, "");
		readpatientlist.print();
		*/
		
		for(User patient : dataStorage.patientRecords) {
			System.out.println("Name: " + patient.name + " | Password: " + patient.Password + " | User ID:  " + patient.userID);
		}
		
		Authenticator testAuth = new Authenticator();
		
		String hashedpword=Password.hashPassword("password");
		System.out.println(hashedpword);
		testAuth.testLogin("P1002", hashedpword, dataStorage.patientRecords);
;	}

}
