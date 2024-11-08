package controllers;

import java.util.List;
import controllers.AdministratorController; 
import controllers.AppointmentController;
import controllers.UserController; 
import datastorage.DataStorage;
import datastorage.PatientRecords;
import entities.Appointment;
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
public String getEmptySlots(String date) {
	return user.getSchedule( date);
}

// NEW: Set the doctor's availability for a specific date and time slots
public boolean setAvailability(String date, String timeSlots) {
	return user.(setAvailability(date, timeSlots));
}

// NEW: Retrieve a list of pending appointment requests for the doctor
public List<Appointment> getAppointmentRequests() {
	return appointmentController.getPendingAppointmentsForDoctor(user.getId());
}

// NEW: Accept or decline an appointment request
public boolean handleAppointmentRequest(String appointmentId, boolean isAccepted) {
	Appointment appointment = appointmentController.findAppointmentById(appointmentId);
	if (appointment != null && appointment.getDoctorId().equals(user.getId())) {
		appointment.setStatus(isAccepted ? Appointment.AppointmentStatus.CONFIRMED : Appointment.AppointmentStatus.CANCELLED);
		appointmentController.updateAppointment(appointment);
		return true;
	}
	return false;
}

// NEW: Retrieve a list of upcoming appointments for the doctor
public List<Appointment> getUpcomingAppointments() {
	return appointmentController.getUpcomingAppointmentsForDoctor(user.getId());
}

// NEW: Record the outcome of an appointment
public boolean recordAppointmentOutcome(String appointmentId, String date, String serviceType, List<String> medications, String notes) {
	Appointment appointment = appointmentController.findAppointmentById(appointmentId);
	if (appointment != null && appointment.getDoctorId().equals(user.getId())) {
		appointment.setDate(date);
		appointment.setServiceType(serviceType);
		appointment.setMedications(medications);
		appointment.setNotes(notes);
		appointment.setStatus(Appointment.AppointmentStatus.COMPLETED);
		appointmentController.updateAppointment(appointment);
		return true;
	}
	return false;
}


}