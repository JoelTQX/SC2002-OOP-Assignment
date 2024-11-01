package controllers;

import entities.Patient;
import entities.User;

public class PatientController implements ControllerInterface{
	private Patient patientUser;
	
	public PatientController(User user) {
		this.patientUser = (Patient) user;
	}
	
	public String getUserID() {
		return patientUser.getUserID();
	}
	public String getUserName() {
		return patientUser.getUserName();
	}
	
	public String getUserGender() {
		return patientUser.getUserGender();
	}
	
	public String getUserDOB() {
		String dob=patientUser.getPatientDOB();
		return dob;
	}
	
	public String getUserContactInfo() {
		String ci=patientUser.getPatientContactInfo();
		return ci;
	}
	public String getUserBloodType() {
		String bt=patientUser.getPatientBloodType();
		return bt;
	}

	public void setPatientContactInfo(String email) {
		patientUser.setPatientContactInfo(email);
		
		
	}
}
