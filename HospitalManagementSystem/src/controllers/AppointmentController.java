package controllers;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Appointment.PrescribedMedication;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController { 
    private AppointmentRecords appointmentRecords;
    
    /**
     * The AppointmentController class provides methods to manage and modify appointments,
     * including adding, updating, retrieving appointment details, and processing prescribed medications.
     */
    public AppointmentController(DataStorage dataStorage) {
        this.appointmentRecords = dataStorage.getAppointmentRecords();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT MODIFICATION METHODS ///////////////////////////////////////////////////////////
    /**
     * Completes the specified appointment by adding consultation notes and marking it as completed.
     * 
     * @param appointment The appointment to complete.
     * @param consultationNotes The consultation notes to add to the appointment.
     */
    public void completeAppointment(Appointment appointment, String consultationNotes) {
        if (appointment != null) {
            appointment.completeAppointment(consultationNotes);
        }
    }

    /**
     * Sets the appointment ID for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param appointmentId The new appointment ID.
     * @return The appointment ID if set successfully, otherwise "NULL".
     */
    public String setAppointmentID(Appointment appointment, String appointmentId) {
        if (appointment.setAppointmentID(appointmentId)) {
            return appointmentId; // appointment ID set properly 
        } else {
            return "NULL"; // empty if ID cannot be set
        }
    }

    /**
     * Sets the patient ID for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param patientId The new patient ID.
     */
    public void setPatientId(Appointment appointment, String patientId) {
        appointment.setPatientId(patientId);
    }

    /**
     * Sets the doctor ID for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param doctorId The new doctor ID.
     * @return True if the doctor ID was set successfully, otherwise false.
     */
    public boolean setDoctorId(Appointment appointment, String doctorId) {
        return appointment.setDoctorId(doctorId);
    }

    /**
     * Sets the status for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param status The new appointment status.
     */
    public void setStatus(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
    }

    /**
     * Sets the appointment date for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param date The new appointment date.
     * @return True if the date was set successfully, otherwise false.
     */
    public boolean setAppointmentDate(Appointment appointment, String date) {
        if (appointment != null) {
            appointment.setAppointmentDate(date); 
            return true;
        } else {
            return false; 
        }
    }

    /**
     * Sets the appointment time for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param time The new appointment time.
     * @return True if the time was set successfully, otherwise false.
     */
    public Boolean SetAppointmentTime(Appointment appointment, String time) {
        return appointment.setAppointmentTime(time);
    }

    /**
     * Sets the appointment type for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param Type The new appointment type.
     * @return True if the appointment type was set successfully, otherwise false.
     */
    public boolean setAppointmentType(Appointment appointment, String Type) {
        return appointment.setAppointmentType(Type);
    }

    /**
     * Sets the consultation notes for the specified appointment.
     * 
     * @param appointment The appointment to update.
     * @param notes The new consultation notes.
     * @return True if the notes were set successfully, otherwise false.
     */
    public boolean setConsultationNotes(Appointment appointment, String notes) {
        if (appointment != null) {
            appointment.setConsultationNotes(notes);
            return true; 
        } else {
            return false;
        }
    }

    /**
     * Adds a prescribed medication to the specified appointment.
     * 
     * @param appointment The appointment to add the medication to.
     * @param medicationName The name of the prescribed medication.
     * @param quantity The quantity of the prescribed medication.
     */
    public void addPrescribedMedication(Appointment appointment, String medicationName, int quantity) {
        if (appointment != null) {
            appointment.getPrescribedMedications().add(new PrescribedMedication(medicationName, quantity));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT RETRIEVAL METHODS ///////////////////////////////////////////////////////////////

    /**
     * Retrieves the appointment ID for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the ID from.
     * @return The appointment ID.
     */
    public String getAppointmentID(Appointment appointment) {
        return appointment.getAppointmentID();
    }

    /**
     * Retrieves the patient ID for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the patient ID from.
     * @return The patient ID.
     */
    public String getPatientId(Appointment appointment) {
        return appointment.getPatientId();
    }

    /**
     * Retrieves the doctor ID for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the doctor ID from.
     * @return The doctor ID.
     */
    public String getDoctorId(Appointment appointment) {
        return appointment.getDoctorId();
    }

    /**
     * Retrieves the status of the specified appointment.
     * 
     * @param appointment The appointment to retrieve the status from.
     * @return The appointment status.
     */
    public AppointmentStatus getStatus(Appointment appointment) {
        return appointment.getStatus();
    }

    /**
     * Retrieves the appointment date for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the date from.
     * @return The appointment date.
     */
    public String getAppointmentDate(Appointment appointment) {
        return appointment.getAppointmentDate();
    }

    /**
     * Retrieves the appointment time for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the time from.
     * @return The appointment time.
     */
    public String getAppointmentTime(Appointment appointment) {
        return appointment.getAppointmentTime();
    }

    /**
     * Retrieves the appointment type for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the type from.
     * @return The appointment type.
     */
    public String getAppointmentType(Appointment appointment) {
        return appointment.getAppointmentType();
    }

    /**
     * Retrieves the consultation notes for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the consultation notes from.
     * @return The consultation notes.
     */
    public String getConsultationNotes(Appointment appointment) {
        return appointment.getConsultationNotes();
    }

    /**
     * Retrieves the list of prescribed medications for the specified appointment.
     * 
     * @param appointment The appointment to retrieve the prescribed medications from.
     * @return The list of prescribed medications.
     */
    public List<PrescribedMedication> getPrescribedMedications(Appointment appointment) {
        return appointment.getPrescribedMedications();
    }
    
    /**
     * Retrieves an appointment by its ID.
     * 
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The appointment with the specified ID, or null if not found.
     */
    public Appointment getAppointmentByID(String appointmentID) {
        return this.appointmentRecords.getAppointmentByID(appointmentID);
    }

    /**
     * Retrieves all appointment records.
     * 
     * @return A list of all appointments.
     */
    public List<Appointment> getAppointmentRecords() {
        return this.appointmentRecords.getAppointmentRecords();
    }

    /**
     * Retrieves all completed appointments.
     * 
     * @return A list of completed appointments.
     */
    public List<Appointment> getCompletedAppointments() {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : this.appointmentRecords.getAppointmentRecords()) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// HELPER METHODS //////////////////////////////////////////////////////////////////////////////

    /**
     * Displays the details of an appointment.
     * 
     * @param appointment The appointment to display details for.
     * @return A string containing the details of the appointment.
     */
    public String displayDetails(Appointment appointment) {
        StringBuilder details = new StringBuilder();
        for (Appointment appointment1 : appointmentRecords.getAppointmentRecords()) {
            if (appointment1.getPatientId() == appointment.getPatientId()) {
                details.append("Appointment ID: ").append(appointment.getAppointmentID())
                       .append(", Date: ").append(getAppointmentDate(appointment))
                       .append(", Time: ").append(getAppointmentTime(appointment))
                       .append(", Status: ").append(getStatus(appointment))
                       .append(", Prescribed Medicine: ").append(formatMedicineList(appointment.getPrescribedMedications()))
                       .append(", Consultation Notes: ").append(appointment.getConsultationNotes());
            }
        }
        return details.toString();
    }

    /**
     * Formats the list of prescribed medications into a string.
     * 
     * @param medications The list of prescribed medications.
     * @return A formatted string representing the prescribed medications.
     */
    private String formatMedicineList(List<PrescribedMedication> medications) {
        if (medications == null || medications.isEmpty()) {
            return "None";
        }

        StringBuilder details = new StringBuilder();
        for (PrescribedMedication meds : medications) {
            if (details.length() > 0) {
                details.append(", "); // Add a comma and space before appending the next item
            }
            details.append(meds.getMedicationName()); // Replace `toString` with a specific method like `getName()` if necessary
            details.append(":");
            details.append(meds.getMedicineQuantity());
        }

        return details.toString();
    }

    /**
     * Generates a unique appointment ID based on the current timestamp and a counter.
     * 
     * @return A unique appointment ID.
     */
    private static int counter = 0;

    public String generateAppointmentID() {
        long timestamp = System.currentTimeMillis();
        // Use only the last 5 hex digits of the timestamp for a shorter ID
        String hexTimestamp = Long.toHexString(timestamp & 0xFFFFF); // Last 5 hex digits
        String hexCounter = Integer.toHexString(counter++ & 0xF); // Add 1 hex digit from counter
        counter = counter % 16; // Keep counter within 1 hex digit range (0-15)
        return "A" + hexTimestamp.toUpperCase() + hexCounter.toUpperCase();
    }
}