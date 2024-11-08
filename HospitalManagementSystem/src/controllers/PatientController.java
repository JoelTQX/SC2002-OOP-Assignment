package controllers;

import entities.Appointment;
import entities.Patient;
import entities.User;
import java.util.List;

import datastorage.DataStorage;

public class PatientController implements ControllerInterface{
	private Patient patientUser;
	private AppointmentController appointmentController;
	
	// NEW: Added appointment controller as a parameter to the construtor 
	public PatientController(User user, DataStorage dataStorage) {
		this.patientUser = (Patient) user;
		this.appointmentController = new AppointmentController(); // NEW
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

	  // NEW: Get available appointment slots via AppointmentController
    public List<String> getAvailableSlots(String date ) {
        return appointmentController.getAvailableSlots(date);
    }

    // NEW: Schedule an appointment via AppointmentController

	// change flow
	// the paitient is presented with a list of dates that they can choose from 
	//and then they agree to the DOC who then cfm / cancel the req 
	    public boolean scheduleAppointment(String doctorId, String date, String time) {
        Appointment newAppointment = new Appointment(generateAppointmentID(), getUserID(), doctorId, date, time, "General Checkup");
        return appointmentController.scheduleAppointment(newAppointment);
    }

    // NEW: Reschedule an appointment via AppointmentController
    public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        return appointmentController.rescheduleAppointment(appointmentId, newDate, newTime);
    }

    // NEW: Cancel an appointment via AppointmentController
    public boolean cancelAppointment(String appointmentId) {
        return appointmentController.cancelAppointment(appointmentId);
    }

    // NEW: Get scheduled appointments via AppointmentController
    public List<Appointment> getScheduledAppointments() {
        return appointmentController.getScheduledAppointments(getUserID());
    }

    // NEW: Get completed appointments via AppointmentController
    public List<Appointment> getCompletedAppointments() {
        return appointmentController.getCompletedAppointments(getUserID());
    }


}

