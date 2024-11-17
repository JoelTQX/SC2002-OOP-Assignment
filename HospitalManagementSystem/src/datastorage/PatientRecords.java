package datastorage;
import java.util.ArrayList;
import java.util.List;

import entities.Patient;

/**
 * Manages the collection of patient records within the system.
 * Provides methods to add patients, view patient details, and retrieve patient records by ID.
 */
public class PatientRecords {
    /**
     * A list that stores the patient records.
     */
	private List<Patient> patientRecords;
	
    /**
     * Constructs a `PatientRecords` instance, initializing an empty list of patient records.
     */
	public PatientRecords() {
		this.patientRecords = new ArrayList<Patient>();
	}
	
    /**
     * Retrieves a patient from the records by their user ID.
     * 
     * @param patientID The unique ID of the patient to retrieve.
     * @return The `Patient` object corresponding to the given patient ID, or `null` if no patient with that ID is found.
     */
	public Patient getPatientByID(String patientID) {
		for(Patient patient : patientRecords) {
			if(patient.getUserID().equals(patientID)) {
				return patient;
			}
		}
		
		return null; //Patient Not Found
	}
	
    /**
     * Prints the user ID of all patients in the records to the console.
     */
	public void viewPatients() {
		for(Patient patient : patientRecords) {
			System.out.println("UserID: " + patient.getUserID());
		}
	}
	
    /**
     * Adds a new patient to the patient records.
     * 
     * @param patient The `Patient` to add to the records.
     */
	public void addPatient(Patient patient) {
		this.patientRecords.add(patient);
	}
	
    /**
     * Retrieves the list of all patient records.
     * 
     * @return A list of all `Patient` records.
     */
	public List<Patient> getPatientList() {
		return this.patientRecords;
	}
}
