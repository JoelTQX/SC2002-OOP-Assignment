package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import controllers.AdministratorController; 
import controllers.AppointmentController;
import controllers.UserController; 
import datastorage.DataStorage;
import datastorage.PatientRecords;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Appointment.PrescribedMedication;
import entities.Doctor;
import entities.Patient;
import entities.User;

public class DoctorController {
	private Doctor user;
	private DataStorage dataStorage;
	private AppointmentController appointmentController;  	//NEW REFERNCE TO APPOINTMENT CONTROLLER 	
	
	public DoctorController(User user, DataStorage dataStorage, AppointmentController appointmentController) {
		this.user = (Doctor) user;
		this.dataStorage = dataStorage;
		this.appointmentController = appointmentController;  // object appointmentcontroller 
	}
	
	public PatientRecords getPatientsRecords() {
		return dataStorage.getPatientRecords();
	}


	//NOTE UNDER PATIENT 
    // NEW : Update patient records with new diagnoses, prescriptions, and treatment plans
    public boolean updatePatientRecords(String patientId, String newDiagnoses, String prescriptions, String treatmentPlan) {
        Patient patient = dataStorage.findPatientById(patientId);
        if (patient != null) {
            patient.addDiagnosis(newDiagnoses);
            patient.addPrescription(prescriptions);
            patient.setTreatmentPlan(treatmentPlan);
            dataStorage.updatePatientRecord(patient);
            return true;
        }
        return false;
	}
    
// Might not need this cuz appointment requests alr exist

// // NEW: Retrieve the doctor's empty schule 
// used when indicating availbiity 
public List <String> getEmptySlots(String date) {
	return appointmentController.getDocSlots(date);
}	
// will modify to filter out ALL ACTIVE STATUS ie status == NULL cuz this to indicate ALL EMPTY SLOTS

// NEW: Set the doctor's availability for a specific date and time slots
//BASICALLY CREATES A APPOINTMENT WITH CERTAIN BLANK FIELDS 
public boolean setAvailability( String date, String time) {
	// get the doctor ID from user.java 
	String doctorId = user.getUserID();  
	return appointmentController.setAvailability( doctorId, date, time);
}


// NEW: Retrieve a list of pending appointment requests for the doctor
// SO THAT DOC CAN CONFIRM 
public List<Appointment> getAppointmentRequests() {
	return appointmentController.getAppointments(user.getUserID(), Appointment.AppointmentStatus.PENDING);
}

// NEW: Accept or decline an appointment request
public boolean handleAppointmentRequest(String appointmentId, boolean isAccepted) {
	Appointment appointment = appointmentController.findAppointmentById(appointmentId);
	if (appointment != null && appointment.getDoctorId().equals(user.getUserID())) {
		appointment.setStatus(isAccepted ? Appointment.AppointmentStatus.SCHEDULED: Appointment.AppointmentStatus.CANCELLED);	// set to schduled if yes , cancelled i
		//appointmentController.updateAppointment(appointment);		// not needed as the status is alr updated 
		return true;
	}
	return false;
}


// NEW: Retrieve a list of upcoming appointments for the doctor
public List<Appointment> getUpcomingAppointments() {		// status schduled 
	return appointmentController.getAppointments(user.getUserID(), Appointment.AppointmentStatus.SCHEDULED);
}

// NEW: Record the outcome of an appointment
// RECORD THE APPOINTMENT OUTCOMES 
// does a passthrough of the appointment 
// converts the string of med qty to an array of integers
    public boolean recordAppointmentOutcome(String appointmentId, String date, String serviceType, List<String> medications, String medicationQTY, String notes) {
		String doctorID = user.getUserID(); 
		// performs the coversion of list <String> to list <Integer> 
		List.of(medicationQTY.split(",")); // This is List<String> for medication names
		List<Integer> medicationQTYIntegers = Arrays.stream(medicationQTY.split(","))
                                  .map(Integer::parseInt) // Convert each String to Integer
                                  .collect(Collectors.toList()); // This is List<Integer> for quantities
		return appointmentController.recordAppointmentOutcome(appointmentId, doctorID, date, serviceType, medications, medicationQTYIntegers, notes);
    }


}