package entities;

import entities.Appointment.AppointmentStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an appointment in the hospital management system.
 * An appointment is associated with a doctor and a patient, and it tracks the status, date, time, type, prescribed medications, and consultation notes.
 */

public class Appointment {

    /**
     * Enum representing the various statuses of an appointment.
     */
	
    public enum AppointmentStatus {
        /**
         * Both doctor and patient have confirmed the appointment.
         */
        SCHEDULED,          
        
        /**
         * Represents an empty or unassigned appointment.
         */
        NULL,              
        
        /**
         * Indicates the appointment has been canceled by the doctor due to unavailability.
         */
        CANCELLED,
        
        /**
         * The appointment has been completed, with consultation notes provided.
         */
        COMPLETED,          
        
        /**
         * The patient has confirmed, but the doctor has yet to confirm.
         */
        PENDING,            
        
        /**
         * The doctor has marked the slot as available.
         */
        AVAILABLE          
    }

    // Attributes
    private String appointmentID;
    private String patientId;
    private String doctorId;
    private AppointmentStatus status;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentType;
    private List<PrescribedMedication> prescribedMedications; // Medications prescribed during the appointment
    private String consultationNotes;

    /**
     * Constructs an appointment with initial availability, primarily set by a doctor.
     *
     * @param appointmentID   Unique identifier for the appointment.
     * @param patientId       Patient's ID (may be set later).
     * @param doctorId        Doctor's ID.
     * @param appointmentDate Date of the appointment.
     * @param appointmentTime Time of the appointment.
     * @param appointmentType Type of the appointment.
     */
    public Appointment(String appointmentID, String patientId, String doctorId, String appointmentDate, String appointmentTime, String appointmentType) {
        this.appointmentID = appointmentID;
        this.patientId = patientId;   // To be filled in later
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;   
        this.appointmentType = appointmentType; // To be filled in later 
        this.status = AppointmentStatus.AVAILABLE; // Doctor sets the appointment as available first 
        this.prescribedMedications = new ArrayList<>(); // Pass empty array 
    }


    /**
     * Constructs an appointment with specified details.
     *
     * @param appointmentID           Unique identifier for the appointment.
     * @param patientId               Patient's ID.
     * @param doctorId                Doctor's ID.
     * @param status                  Status of the appointment.
     * @param appointmentDate         Date of the appointment.
     * @param appointmentTime         Time of the appointment.
     * @param appointmentType         Type of the appointment.
     * @param prescribedMedications   List of prescribed medications.
     * @param consultationNotes       Consultation notes from the doctor.
     */
    public Appointment(String appointmentID, String patientId, String doctorId, AppointmentStatus status, String appointmentDate, String appointmentTime, String appointmentType, List<PrescribedMedication> prescribedMedications2, String consultationNotes) {
        this.appointmentID = appointmentID;
        this.patientId = patientId;   // To be filled in later
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;   
        this.appointmentType = appointmentType; // To be filled in later 
        this.status = status; // Doctor sets the appointment as available first 
        this.prescribedMedications = prescribedMedications2; // Pass empty array 
        this.consultationNotes = consultationNotes;
    }

    /**
     * Marks the appointment as completed and sets consultation notes.
     *
     * @param consultationNotes The doctor's consultation notes.
     */
    public void completeAppointment(String consultationNotes) {
        this.consultationNotes = consultationNotes;
        this.status = AppointmentStatus.COMPLETED;
    }

    // Getter for appointment status
    public AppointmentStatus getStatus() {
        return this.status;
    }
    
    // Setter for appointment status
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT DETAILS METHODS /////////////////////////////////////////////////////////////////

    // Getter for appointment ID
    public String getAppointmentID() {
        return this.appointmentID;
    }

    // Setter for appointment ID
    public boolean setAppointmentID(String appointmentId) {
        this.appointmentID = appointmentId; 
        return true;
    }

    // Getter for patient ID
    public String getPatientId() {
        return this.patientId;
    }

    // Setter for patient ID
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // Getter for doctor ID
    public String getDoctorId() {
        return this.doctorId;
    }

    // Setter for doctor ID
    public boolean setDoctorId(String doctorId) {
        this.doctorId = doctorId;
        return true;
    }

    // Getter for appointment date
    public String getAppointmentDate() {
        return this.appointmentDate;
    }

    // Setter for appointment date 
    public boolean setAppointmentDate(String date) {  
        this.appointmentDate = date; 
        return true; 
    }

    // Getter for appointment time
    public String getAppointmentTime() {
        return this.appointmentTime;
    }

    // Setter for appointment time
    public boolean setAppointmentTime(String time) {
        this.appointmentTime = time;
        return true; 
    }

    // Getter for appointment type
    public String getAppointmentType() {
        return this.appointmentType;
    }

    // Setter for appointment type
    public boolean setAppointmentType(String type) {
        this.appointmentType = type;
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// CONSULTATION NOTES METHODS //////////////////////////////////////////////////////////////////

    // Getter for consultation notes
    public String getConsultationNotes() {
        return this.consultationNotes;
    }

    // Setter for consultation notes
    public boolean setConsultationNotes(String notes) {
        this.consultationNotes = notes;
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// PRESCRIBED MEDICATION METHODS ///////////////////////////////////////////////////////////////

    // Getter for prescribed medications
    public List<PrescribedMedication> getPrescribedMedications() {
        return this.prescribedMedications;
    }

    // Setter for prescribed medication
    public void addPrescribedMedication(String medicationName, int medicineQuantity) {
        prescribedMedications.add(new PrescribedMedication(medicationName, medicineQuantity)); // Uses a List.add() method to append to the list 
    }

    /**
     * Represents a medication prescribed during an appointment.
     */
    public static class PrescribedMedication {
        private String medicationName;
        private String status;
        private int medicineQuantity;

        /**
         * Constructs a prescribed medication with the specified name and quantity.
         *
         * @param medicationName  The name of the medication.
         * @param medicineQuantity The quantity of the medication prescribed.
         */
        public PrescribedMedication(String medicationName, int medicineQuantity) {
            this.medicationName = medicationName;
            this.medicineQuantity = medicineQuantity;
            this.status = "Pending"; // Default status
        }

        public String getMedicationName() {
            return medicationName;
        }

        public String getMedicineStatus() {
            return status;
        }
        
        public int getMedicineQuantity() {
            return this.medicineQuantity;
        }

        public void setMedicineStatus(String status) {
            this.status = status;
        }

        /**
         * Returns a string representation of the prescribed medication.
         *
         * @return A string containing the medication name and its status.
         */
        @Override
        public String toString() {
            return medicationName + " (Status: " + status + ")";
        }
    }
}