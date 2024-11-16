package entities;

import entities.Appointment.AppointmentStatus;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    // Enum for appointment status
    public enum AppointmentStatus {
        SCHEDULED,          // doctor confirmed, patient confirmed 
        NULL,               // For empty appointments
        CANCELLED,          // Doctor canceled because not free
        COMPLETED,          // Marked done by doctor after consult notes added 
        PENDING,            // Patient confirmed, doctor yet to confirm 
        AVAILABLE           // Doctor indicates availability, use to indicate free slots by the patient too
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

    // Constructor
    // To be called by doctor after setting availability
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT STATUS METHODS //////////////////////////////////////////////////////////////////

    // Method to update consultation notes and mark the appointment as completed
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// NESTED CLASS FOR PRESCRIBED MEDICATION //////////////////////////////////////////////////////

    // Nested class to represent a prescribed medication
    public static class PrescribedMedication {
        private String medicationName;
        private String status;
        private int medicineQuantity;

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

        @Override
        public String toString() {
            return medicationName + " (Status: " + status + ")";
        }
    }
}