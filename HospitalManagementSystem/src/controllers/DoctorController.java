package controllers;

import entities.Doctor;
import entities.User;

public class DoctorController {
	private Doctor user;
	
	public DoctorController(User user) {
		this.user = (Doctor) user;
	}

}
