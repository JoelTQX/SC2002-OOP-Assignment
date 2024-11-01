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
}
