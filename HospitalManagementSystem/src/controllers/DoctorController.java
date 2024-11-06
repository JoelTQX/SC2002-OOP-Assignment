package controllers;

import java.util.List;

import datastorage.DataStorage;
import datastorage.PatientRecords;
import entities.Doctor;
import entities.User;

public class DoctorController {
	private Doctor user;
	private DataStorage dataStorage;
	
	public DoctorController(User user, DataStorage dataStorage) {
		this.user = (Doctor) user;
		this.dataStorage = dataStorage;
	}
	
	public PatientRecords getPatientsRecords() {
		return dataStorage.getPatientRecords();
	}
}
