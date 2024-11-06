package controllers;

import entities.Appointment;
import entities.Patient;
import entities.User;
import java.util.List;

public class PatientController implements ControllerInterface{
	private Patient patientUser;

	// NEW: Adding AppointmentController attribute
	private AppointmentController appointmentController;
	
	// NEW: Added appointment controller as a parameter to the construtor 
	public PatientController(User user, AppointmentController appointmentController) {
		this.patientUser = (Patient) user;
		this.appointmentController = appointmentController; // NEW
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

    // Helper method for generating a unique appointment ID

	private static int counter = 0; // lowkey have no idea how to put this

	//i noticed that using just system time was giving fking long IDs and I thought we could shorten them down 
    private String generateAppointmentID() {
		long timestamp = System.currentTimeMillis();
    
		// Use only the last 5 hex digits of the timestamp for a shorter ID
		String hexTimestamp = Long.toHexString(timestamp & 0xFFFFF); // Last 5 hex digits
		String hexCounter = Integer.toHexString(counter++ & 0xF); // Add 1 hex digit from counter
		counter = counter % 16; // Keep counter within 1 hex digit range (0-15)
	
		return "APT" + hexTimestamp.toUpperCase() + hexCounter.toUpperCase();
    }
}

