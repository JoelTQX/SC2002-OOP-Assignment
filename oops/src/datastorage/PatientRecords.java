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
	
	public List<Patient> getPatientList() {
		return this.patientRecords;
	}
}
