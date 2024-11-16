package controllers;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import entities.Appointment;
import entities.Patient;
import entities.User;
import java.util.List;

public class PatientController {
    private User user;  // Reference to the logged-in patient
    private DataStorage dataStorage;  // Main data storage for access to all records
    private AppointmentController appointmentController;  // Controller for individual appointment operations
    private AppointmentRecords appointmentRecords;  // Record manager for all appointments

    // Constructor
    public PatientController(User user, DataStorage dataStorage) {
        this.user = (Patient) user;
        this.dataStorage = dataStorage;
        this.appointmentController = new AppointmentController(dataStorage); // Instantiate AppointmentController
        this.appointmentRecords = dataStorage.getAppointmentRecords(); // Retrieve AppointmentRecords from DataStorage
    }

    // Patient Information Getters
    public String getUserID() {
        return user.getUserID();
    }
    public String getUserName() {
        return user.getUserName();
    }
    public String getUserGender() {
        return user.getUserGender();
    }
    public String getUserDOB() {
        return ((Patient) user).getPatientDOB();
    }
    public String getUserContactInfo() {
        return ((Patient) user).getPatientContactInfo();
    }
    
    public String getUserContactNumber() {
        return ((Patient) user).getPatientContactNumber();
    }
    
    public String getUserDiagnoses() {
        return ((Patient) user).getPatientdiagnoses();
    }
    
    public String getUserTreatment() {
        return ((Patient) user).getPatienttreatment();
    }
    public String getUserBloodType() {
        return ((Patient) user).getPatientBloodType();
    }

    // Update Patient Contact Information
    public void setPatientContactInfo(String contactInfo) {
        ((Patient) user).setPatientContactInfo(contactInfo);
    }
    
    public void setPatientContactNumber(String contactNumber) {
        ((Patient) user).setPatientContactNumber(contactNumber);
    }

    // Get available slots for a specific date from AppointmentRecords
    public List<Appointment> getAvailableSlots() {
        return appointmentRecords.getALLSlots();
    }

// Schedule a new appointment with a doctor
public String scheduleAppointment(String doctorId, String date, String time) {
   // Get all slots for the specified date
   List<String> bookedSlots = appointmentRecords.getSlots(date);

   // Check if the desired time slot is already booked
   if (bookedSlots.contains(time)) {
       return null; // Deny scheduling
   }
    // Generate a unique ID for the new appointment
    String appointmentId = appointmentController.generateAppointmentID();
    Appointment newAppointment = new Appointment(
        appointmentId, getUserID(), doctorId, date, time, null // TO BE FILLED IN AT THE END OF THE APPOINTMENT
    );
    newAppointment.setStatus(Appointment.AppointmentStatus.PENDING); // Set status to pending until confirmed by the doctor

    // Add new appointment to records
    appointmentRecords.addAppointment(newAppointment);
    return appointmentId;
}

// Reschedule an existing appointment
public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
    // Retrieve the appointment by ID
    Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);

    // Check if appointment exists and belongs to the current patient
    if (appointment == null || !appointment.getPatientId().equals(getUserID())) {
        return false; // Return false if the appointment doesn't exist or doesn't belong to the patient
    }

    // Check if the new time slot is available
    List<String> availableSlots = appointmentRecords.getSlots(newDate);
    if (!availableSlots.contains(newTime)) {
        return false; // Return false if the new time slot is already booked
    }

    // Update appointment details with new date and time
    appointmentController.setAppointmentDate(appointment, newDate);
    appointmentController.SetAppointmentTime(appointment, newTime);
    appointmentController.setStatus(appointment, Appointment.AppointmentStatus.PENDING); // Mark as pending for reconfirmation by doctor
    
    return true; // Return true if rescheduling was successful
}


    // Cancel an appointment
    public boolean cancelAppointment(String appointmentId) {
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment != null && appointment.getPatientId().equals(getUserID())) {
            appointmentController.setStatus(appointment, Appointment.AppointmentStatus.CANCELLED);
            return true;
        }
        return false;
    }

    // Retrieve scheduled appointments for the patient
    public List<Appointment> getScheduledAppointments() {
        return appointmentRecords.getPatientAppointments(getUserID(), Appointment.AppointmentStatus.SCHEDULED);
    }

    // Retrieve completed appointments for the patient
    public List<Appointment> getCompletedAppointments() {
        return appointmentRecords.getPatientAppointments(getUserID(), Appointment.AppointmentStatus.COMPLETED);
    }

	// PatientController.java

	public String displayDetails(Appointment appointment) {
		return appointmentController.displayDetails(appointment);
	}
	
}
