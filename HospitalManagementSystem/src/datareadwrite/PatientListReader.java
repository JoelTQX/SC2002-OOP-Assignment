package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Patient;
import entities.User;
import entities.Appointment.PrescribedMedication;
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
	                String contactnumber=cells[8];
	                String diagnosesString=cells[9];
	                String treatmentString=cells[10];
	                boolean firstlogin=Boolean.parseBoolean(cells[2]);
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
	                ArrayList<String> diagnoses = new ArrayList<>();
	                if (diagnosesString != null && !diagnosesString.isEmpty()) {
	                    String[] diagnosesArray = diagnosesString.split(";"); // Assuming ';' is the delimiter
	                    for (String diagnosis : diagnosesArray) {
	                        diagnoses.add(diagnosis.trim()); // Add each diagnosis after trimming whitespace
	                    }
	                }else {diagnoses=null;}

	                // Process treatment string into ArrayList<String>
	                ArrayList<String> treatments = new ArrayList<>();
	                if (treatmentString != null && !treatmentString.isEmpty()) {
	                    String[] treatmentArray = treatmentString.split(";"); // Assuming ';' is the delimiter
	                    for (String treatment : treatmentArray) {
	                        treatments.add(treatment.trim()); // Add each treatment after trimming whitespace
	                    }
	                }else {treatments=null;}
	                
	                Patient patient = new Patient(patientid,name,password, firstlogin,dob,bloodtype,contactinfo,gender,contactnumber,diagnoses,treatments);
	                dataStorage.getPatientRecords().addPatient(patient);
			 }
		 }catch (Exception e) {
            e.printStackTrace();
		  }
	}
}

