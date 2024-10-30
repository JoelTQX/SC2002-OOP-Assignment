package datastorage;
import java.util.ArrayList;
import java.util.List;

import controllers.PatientController;
import entities.Medicine;
import entities.Patient;
import entities.User;

public class PatientRecords {
	private List<Patient> patientRecords;
	
	public PatientRecords() {
		this.patientRecords = new ArrayList<Patient>();
	}
	
	public void populateRecords() {
		Patient testP1 = new Patient("P0001", "password", true);
		Patient testP2 = new Patient("P0002", "password", true);
		this.patientRecords.add(testP1);
		this.patientRecords.add(testP2);
		viewPatients();
	}
	
	public Patient getPatientByID(String patientID) {
		for(Patient patient : patientRecords) {
			if(patient.getUserID().equals(patientID)) {
				return patient;
			}
		}
		
		return null; //Patient Not Found
	}
	
	public void viewPatients() {
		for(Patient patient : patientRecords) {
			System.out.println("UserID: " + patient.getUserID());
		}
	}
	
	public void addPatient(Patient patient) {
		this.patientRecords.add(patient);
	}
}