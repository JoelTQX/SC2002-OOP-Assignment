package controllers;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Patient;
import entities.User;
import java.util.List;

/**
 * The PatientController class handles the operations for managing patient-related actions, 
 * such as retrieving and updating patient information, scheduling, rescheduling, and canceling appointments.
 * It also interacts with appointment records and manages appointment status updates for the logged-in patient.
 */
public class PatientController {
    private User user;  // Reference to the logged-in patient
    private AppointmentController appointmentController;  // Controller for individual appointment operations
    private AppointmentRecords appointmentRecords;  // Record manager for all appointments

    /**
     * Constructs a PatientController for managing patient-specific operations.
     * 
     * @param user The logged-in user, which should be a Patient.
     * @param dataStorage The DataStorage instance that contains all the data for managing appointments.
     */
    public PatientController(User user, DataStorage dataStorage) {
        this.user = (Patient) user;
        this.appointmentController = new AppointmentController(dataStorage); // Instantiate AppointmentController
        this.appointmentRecords = dataStorage.getAppointmentRecords(); // Retrieve AppointmentRecords from DataStorage
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// PATIENT INFORMATION GETTERS /////////////////////////////////////////////////////////////////

    /**
     * Retrieves the User ID of the logged-in patient.
     * 
     * @return The User ID.
     */
    public String getUserID() {
        return user.getUserID();
    }

    /**
     * Retrieves the User Name of the logged-in patient.
     * 
     * @return The User Name.
     */
    public String getUserName() {
        return user.getUserName();
    }

    /**
     * Retrieves the gender of the logged-in patient.
     * 
     * @return The gender of the patient.
     */
    public String getUserGender() {
        return user.getUserGender();
    }

    /**
     * Retrieves the date of birth of the logged-in patient.
     * 
     * @return The date of birth of the patient.
     */
    public String getUserDOB() {
        return ((Patient) user).getPatientDOB();
    }

    /**
     * Retrieves the contact information of the logged-in patient.
     * 
     * @return The contact information of the patient.
     */
    public String getUserContactInfo() {
        return ((Patient) user).getPatientContactInfo();
    }

    /**
     * Retrieves the contact number of the logged-in patient.
     * 
     * @return The contact number of the patient.
     */
    public String getUserContactNumber() {
        return ((Patient) user).getPatientContactNumber();
    }

    /**
     * Retrieves the diagnoses of the logged-in patient.
     * 
     * @return The diagnoses of the patient.
     */
    public String getUserDiagnoses() {
        return ((Patient) user).getPatientdiagnoses();
    }

    /**
     * Retrieves the treatment information of the logged-in patient.
     * 
     * @return The treatment information of the patient.
     */
    public String getUserTreatment() {
        return ((Patient) user).getPatienttreatment();
    }

    /**
     * Retrieves the blood type of the logged-in patient.
     * 
     * @return The blood type of the patient.
     */
    public String getUserBloodType() {
        return ((Patient) user).getPatientBloodType();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// PATIENT INFORMATION SETTERS /////////////////////////////////////////////////////////////////

    /**
     * Updates the contact information of the logged-in patient.
     * 
     * @param contactInfo The new contact information to set.
     */
    public void setPatientContactInfo(String contactInfo) {
        ((Patient) user).setPatientContactInfo(contactInfo);
    }

    /**
     * Updates the contact number of the logged-in patient.
     * 
     * @param contactNumber The new contact number to set.
     */
    public void setPatientContactNumber(String contactNumber) {
        ((Patient) user).setPatientContactNumber(contactNumber);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT MANAGEMENT METHODS //////////////////////////////////////////////////////////////

    /**
     * Retrieves all available appointment slots from the appointment records.
     * 
     * @return A list of available appointment slots.
     */
    public List<Appointment> getAvailableSlots() {
        return appointmentRecords.getALLSlots();
    }

    /**
     * Schedules a new appointment with a doctor.
     * 
     * @param doctorId The ID of the doctor for the appointment.
     * @param date The date for the appointment.
     * @param time The time for the appointment.
     * @return The appointment ID if the appointment is successfully scheduled; otherwise, null if the slot is unavailable.
     */
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

    /**
     * Reschedules an existing appointment to a new date and time.
     * 
     * @param appointmentId The ID of the appointment to reschedule.
     * @param newDate The new date for the appointment.
     * @param newTime The new time for the appointment.
     * @return The new appointment ID if successfully rescheduled, or null if the new time slot is unavailable.
     */
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

    /**
     * Cancels an existing appointment.
     * 
     * @param appointmentId The ID of the appointment to cancel.
     * @return True if the appointment is successfully canceled, or false if the appointment cannot be canceled.
     * @throws IllegalArgumentException If the appointment is not found or the status is not pending or scheduled.
     * @throws IllegalStateException If the appointment does not have a patient ID.
     */
// Cancel an appointment
public boolean cancelAppointment(String appointmentId) {
    try {
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        if (appointment.getStatus() != AppointmentStatus.PENDING && appointment.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new IllegalArgumentException("Appointment must be Pending or Scheduled to be cancelled.");
             // checks if the appointment is pending or scheduled in cases patient tries to cancel an appointment that is already completed or cancelled 
        }

        String patientId = appointment.getPatientId();
        if (patientId == null) {
            throw new IllegalStateException("Appointment does not have a patient ID.");
        }

        if (patientId.equals(getUserID())) {
            appointmentController.setStatus(appointment, AppointmentStatus.CANCELLED);
            // Create a new appointment with the same details as the cancelled appointment
            // Calls appointment constructor used in doctor controller
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
        } else {
            throw new IllegalArgumentException("You can only cancel your own appointments.");
        }
    } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}


	/**
	 * Retrieves a list of scheduled appointments for the logged-in patient.
	 * 
	 * @return A list of scheduled appointments for the patient.
	 */
    public List<Appointment> getScheduledAppointments() {
        return appointmentRecords.getPatientAppointments(getUserID(), AppointmentStatus.SCHEDULED);
    }

    /**
     * Retrieves a list of completed appointments for the logged-in patient.
     * 
     * @return A list of completed appointments for the patient.
     */
    // Retrieve completed appointments for the patient
    public List<Appointment> getCompletedAppointments() {
        return appointmentRecords.getPatientAppointments(getUserID(), AppointmentStatus.COMPLETED);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// HELPER METHODS //////////////////////////////////////////////////////////////////////////////

    /**
     * Displays the details of an appointment.
     * 
     * @param appointment The appointment whose details are to be displayed.
     * @return A string representation of the appointment's details.
     */
    public String displayDetails(Appointment appointment) {
        return appointmentController.displayDetails(appointment);
    }
}