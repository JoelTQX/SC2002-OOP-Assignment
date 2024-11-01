package DataReadWrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import entities.Patient;
import entities.User;

import datastorage.DataStorage;
import entities.Patient;
import datastorage.PatientRecords;

public class PatientListReader implements DataReader {
	
	String csvFile = "dataFiles/Patient_List.csv";
    String line;
	
	public void populateData(DataStorage dataStorage) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			 String header = reader.readLine();
			 while ((line = reader.readLine()) != null) {
	                String[] cells = line.split(",");
	                String patientid = cells[0];
	                String password=cells[1];
	                String name=cells[3];
	                String dob=cells[4];
	                String gender=cells[5];
	                String bloodtype=cells[6];
	                String contactinfo=cells[7];
	                boolean firstlogin=Boolean.parseBoolean(cells[2]);
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
	                
	                Patient patient = new Patient(patientid,name,password, firstlogin,dob,bloodtype,contactinfo,gender);
	                dataStorage.getPatientRecords().addPatient(patient);
			 }
		 }catch (Exception e) {
            e.printStackTrace();
		  }
	}
}

