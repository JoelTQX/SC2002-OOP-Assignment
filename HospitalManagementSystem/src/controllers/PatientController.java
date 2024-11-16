package controllers;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// PATIENT INFORMATION GETTERS /////////////////////////////////////////////////////////////////

    // Getters for patient information
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// PATIENT INFORMATION SETTERS /////////////////////////////////////////////////////////////////

    // Update Patient Contact Information
    public void setPatientContactInfo(String contactInfo) {
        ((Patient) user).setPatientContactInfo(contactInfo);
    }

    public void setPatientContactNumber(String contactNumber) {
        ((Patient) user).setPatientContactNumber(contactNumber);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT MANAGEMENT METHODS //////////////////////////////////////////////////////////////

    // Get available slots for a specific date from AppointmentRecords
    public List<Appointment> getAvailableSlots() {
        return appointmentRecords.getALLSlots();
    }

    // Schedule a new appointment with a doctor
    public String scheduleAppointment(String doctorId, String date, String time) {
        // Get all slots for the specified date
        List<Appointment> Slots = appointmentRecords.getALLSlots();

        // Check if the desired time slot is already booked with a status other than AVAILABLE
        for (Appointment appointment : Slots) {
            // since get all slots returns only available slots, we can check if the time and date are the same
            if (appointment.getAppointmentTime().equals(time) && appointment.getAppointmentDate().equals(date)) {
                //update the patient ID and status of the appointment
                appointmentController.setPatientId(appointment, getUserID());
                appointmentController.setStatus(appointment, Appointment.AppointmentStatus.PENDING);
                return appointment.getAppointmentID();
            }
        }

        return null; // Return null if the slot is already booked
    }

    // Reschedule an existing appointment
    public String rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        // Retrieve the appointment by ID
        //perform appointment swap 
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        List<Appointment> Slots = appointmentRecords.getSlotsForDate(newDate);
        // Check if appointment exists and belongs to the current patient
        if (appointment == null || !appointment.getPatientId().equals(getUserID())) {
            return null; // Return false if the appointment doesn't exist or doesn't belong to the patient
        }

        // Check if the new time slot is available
        for (Appointment appointment1 : Slots) {
            if (appointment1.getAppointmentTime().equals(newTime) && appointment1.getStatus().equals(Appointment.AppointmentStatus.AVAILABLE)) { // slot is available
                // write the data to the new appointment slot 
                String newAppointmentID = appointment1.getAppointmentID();

                // Update appointment details with new date and time
                appointmentController.setPatientId(appointment1, getUserID()); // Update the patient ID
                appointmentController.setStatus(appointment1, Appointment.AppointmentStatus.PENDING); // Mark as pending for reconfirmation by doctor

                // reset the old appointment
                appointmentController.setStatus(appointment, Appointment.AppointmentStatus.AVAILABLE); // Mark old slot as available
                appointmentController.setPatientId(appointment, null);
                return appointment1.getAppointmentID(); // Return true if rescheduling was successful
            }
        }

        return null; // Return false if the new time slot is already booked
    }

    // Cancel an appointment
    public boolean cancelAppointment(String appointmentId) {
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment != null && appointment.getPatientId().equals(getUserID())) {
            appointmentController.setStatus(appointment, AppointmentStatus.CANCELLED);
            //create a new appointment with the same details as the cancelled appointment
            // calls appointment constructor used in doctor controller
            String doctorId = appointment.getDoctorId();
            String date = appointment.getAppointmentDate();
            String time = appointment.getAppointmentTime();
            Appointment newAppointment = new Appointment(
                appointmentController.generateAppointmentID(),
                null, // No patient yet
                doctorId,
                date,
                time,
                null // No specific type yet
            );
            newAppointment.setStatus(AppointmentStatus.AVAILABLE);
            newAppointment.setAppointmentDate(date);
            appointmentRecords.addAppointment(newAppointment); // Add new availability slot to records

            return true;
        }
        return false;
    }

    // Retrieve scheduled appointments for the patient
    public List<Appointment> getScheduledAppointments() {
        return appointmentRecords.getPatientAppointments(getUserID(), AppointmentStatus.SCHEDULED);
    }

    // Retrieve completed appointments for the patient
    public List<Appointment> getCompletedAppointments() {
        return appointmentRecords.getPatientAppointments(getUserID(), AppointmentStatus.COMPLETED);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// HELPER METHODS //////////////////////////////////////////////////////////////////////////////

    // Display appointment details
    public String displayDetails(Appointment appointment) {
        return appointmentController.displayDetails(appointment);
    }
}