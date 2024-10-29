package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import entities.Patient;
import entities.User;

import DataStorage.DataStorage;
import inventoryManagement.Medicine;

public class PatientListReader implements DataReader {
	
	String csvFile = "C:/Users/jingj/Desktop/Code/SC2002-OOP-Assignment/Patient_List.csv";
    String line;
	
	public void populateData(DataStorage dataStorage) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			 String header = reader.readLine();
			 while ((line = reader.readLine()) != null) {
	                String[] cells = line.split(",");
	                String patientid = cells[0];
	                String password=cells[1];
	                String name=cells[2];
	                String dob=cells[3];
	                String gender=cells[4];
	                String bloodtype=cells[5];
	                String contactinfo=cells[6];
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
	                
	                User patient = new Patient(patientid,password, name, dob,gender,bloodtype,contactinfo);
	                dataStorage.addPatient(patient);
			 }
		 }catch (Exception e) {
            e.printStackTrace();
		  }
	}
}

