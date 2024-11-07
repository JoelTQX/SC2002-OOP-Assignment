package controllers;

import java.util.List;
import controllers.AdministratorController; 
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
		this.appointmentController = appointmentController; 
	}
	
	public PatientRecords getPatientsRecords() {
		return dataStorage.getPatientRecords();
	}



    // NEW : Update patient records with new diagnoses, prescriptions, and treatment plans
    public boolean updatePatientRecords(int patientId, String newDiagnoses, String prescriptions, String treatmentPlan) {
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
    

	//NEW 	
	public boolean handleAppointmentRequest(int appointmentId, boolean isAccepted) {
		Appointment appointment = appointmentController.findAppointmentById(appointmentId);
		if (appointment != null && appointment.getDoctorId() == user.getId()) {
			if (isAccepted) {
				appointment.setStatus("Accepted");
			} else {
				appointment.setStatus("Declined");
			}
			appointmentController.updateAppointment(appointment);
			return true;
		}
		return false;
	}

	// NEW: Get a list of upcoming appointments for the doctor
	public List<Appointment> getUpcomingAppointments() {
		return appointmentController.getUpcomingAppointments(user.getId());
	}

	// NEW: Record the outcome of an appointment
	public boolean recordAppointmentOutcome(int appointmentId, String date, String serviceType, List<String> medications, String notes) {
		Appointment appointment = appointmentController.findAppointmentById(appointmentId);
		if (appointment != null && appointment.getDoctorId() == user.getId()) {
			appointment.setDate(date);
			appointment.setServiceType(serviceType);
			appointment.setMedications(medications);
			appointment.setNotes(notes);
			appointmentController.updateAppointment(appointment);
			return true;
		}
		return false;
	}
	// NEW: Get a list of pending appointment requests for the doctor
	public List<Appointment> getAppointmentRequests() {
		return appointmentController.getAvailableSlots(user.getId());
	}

	// NEW: Handle an appointment request by accepting or declining it
	public boolean handleAppointmentRequest(String appointmentId, boolean isAccepted) {
		Appointment appointment = appointmentController.findAppointmentById(appointmentId);
		if (appointment != null && appointment.getDoctorId().equals(user.getId())) {
			if (isAccepted) {
				appointment.setStatus(Appointment.AppointmentStatus.ACCEPTED);
			} else {
				appointment.setStatus(Appointment.AppointmentStatus.DECLINED);
			}
			appointmentController.updateAppointment(appointment);
			return true;
		}
		return false;
	}

	// NEW: Get a list of upcoming appointments for the doctor
	public List<Appointment> getUpcomingAppointments() {
		return appointmentController.getUpcomingAppointments(user.getId());
	}

	// NEW: Record the outcome of an appointment
	public boolean recordAppointmentOutcome(String appointmentId, String date, String serviceType, List<String> medications, String notes) {
		Appointment appointment = appointmentController.findAppointmentById(appointmentId);
		if (appointment != null && appointment.getDoctorId().equals(user.getId())) {
			appointment.setDate(date);
			appointment.setServiceType(serviceType);
			appointment.setMedications(medications);
			appointment.setNotes(notes);
			appointmentController.updateAppointment(appointment);
			return true;
		}
		return false;
	}



}
