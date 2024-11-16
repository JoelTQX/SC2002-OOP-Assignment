package entities;

import entities.Appointment.AppointmentStatus;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    // Enum for appointment status
    public enum AppointmentStatus {
        SCHEDULED,          // doctor cfm , patient confirmed 
        CANCELLED,          //  DOCTOR CANCELED CUZ NOT FREE
        COMPLETED,          // Marked done by doctor after consult notes added 
        PENDING,             // patient cfm , doctor yet to cfm 
        AVAILABLE           // doctor indicates availbilty, USE TO INDICATE FREE SLOTS BY THE PATIENT TOO
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
    // TO BE CALLED BY DOCTOR AFTER SET AVAIL 
    public Appointment(String appointmentID, String patientId, String doctorId, String appointmentDate, String appointmentTime, String appointmentType) {
        this.appointmentID = appointmentID;
    	this.patientId = patientId;   // TO BE FILLED IN LTR
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;   
        this.appointmentType = appointmentType; // TO BE FILLED IN LTR 
        this.status = AppointmentStatus.AVAILABLE;          // DOC SETS THE APT AVAIL FIRST 
        this.prescribedMedications = new ArrayList<>(); // PASS EMPTY ARRAY 
    }

    public Appointment(String appointmentID, String patientId, String doctorId, AppointmentStatus status, String appointmentDate, String appointmentTime, String appointmentType,List<PrescribedMedication> prescribedMedications2,String consultationNotes) {
        this.appointmentID = appointmentID;
    	this.patientId = patientId;   // TO BE FILLED IN LTR
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;   
        this.appointmentType = appointmentType; // TO BE FILLED IN LTR 
        this.status = status;         // DOC SETS THE APT AVAIL FIRST 
        this.prescribedMedications = prescribedMedications2; // PASS EMPTY ARRAY 
        this.consultationNotes=consultationNotes;
    }

   
    // Method to update consultation notes and mark the appointment as completed
    public void completeAppointment(String consultationNotes) {
        this.consultationNotes = consultationNotes;
        this.status = AppointmentStatus.COMPLETED;
    }
    
    // Getter for appointment ID
    public String getAppointmentID() {
    	return this.appointmentID;
    }

    // setter for appointment ID
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

  // setter for doctor ID
    public boolean setDoctorId( String doctorId) {
     this.doctorId = doctorId;
     return true;
    }       

 

    // Getter for appointment status
    public AppointmentStatus getStatus() {
        return this.status;
    }
    
    // Setter for appointment status
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    // Getter for consultation notes
    public String getConsultationNotes() {
        return this.consultationNotes;
    }

    // Setter for appointment type
     public boolean setConsultationNotes(String notes) {
        this.consultationNotes = notes;
        return true;
    }

    // Getter for prescribed medications
    public List<PrescribedMedication> getPrescribedMedications() {
        return this.prescribedMedications;
    }

    // setter for prescribed medication
    public void addPrescribedMedication(String medicationName, int medicineQuantity) {
        prescribedMedications.add(new PrescribedMedication(medicationName, medicineQuantity));  // uses a List.add() method to append to the list 
    }

    // Getter for appointment date
    public String getAppointmentDate() {
        return this.appointmentDate;
    }

    // setter for appointment date 
    public boolean setAppointmentDate( String date)
    {  
        this.appointmentDate = date; 
        return true; 
    }

    // Getter for appointment time
    public String getAppointmentTime() {
        return this.appointmentTime;
    }

     // setter for appointment time
     public boolean setAppointmentTime(String time) {
            this.appointmentTime = time;
            return true; 
          
    }

    // Getter for appointment type
    public String getAppointmentType() {
        return this.appointmentType;
    }

     // setter for appointment type
     public boolean setAppointmentType(String Type) {
        this.appointmentType = Type ;
        return true;
    }

    


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
