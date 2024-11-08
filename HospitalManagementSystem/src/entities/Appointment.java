package entities;

import entities.Appointment.AppointmentStatus;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    // Enum for appointment status
    public enum AppointmentStatus {
        SCHEDULED,          // doctor cfm , patient confirmed 
//      CONFIRMED,          // FOR LATER USE 
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
    	//this.patientId = patientId;   // TO BE FILLED IN LTR
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;   
        //this.appointmentType = appointmentType; // TO BE FILLED IN LTR 
        this.status = AppointmentStatus.AVAILABLE;          // DOC SETS THE APT AVAIL FIRST 
        this.prescribedMedications = new ArrayList<>(); // PASS EMPTY ARRAY 
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

        public String getStatus() {
            return status;
        }
        
        public int getMedicineQuantity() {
        	return this.medicineQuantity;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return medicationName + " (Status: " + status + ")";
        }
    }


    // COMMENTED OUT FOR TEST 
    // Method to add a prescribed medication
    public void addPrescribedMedication(String medicationName, int medicineQuantity) {
        prescribedMedications.add(new PrescribedMedication(medicationName, medicineQuantity));  // uses a List.add() method to append to the list 
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
    
    // Getter for patient ID
    public String getPatientId() {
        return this.patientId;
    }

    // Getter for doctor ID
    public String getDoctorId() {
        return this.doctorId;
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


     // setter for prescribed medications
     // used by the doctor to complete the consult 
     public boolean addPrescribedMedications( List<PrescribedMedication> prescribedMedications) {
        this.prescribedMedications  = prescribedMedications;        // replace with the new list 
        return true; 
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

    // Getter for appointment type
    public String getAppointmentType() {
        return this.appointmentType;
    }

     // setter for appointment type
     public boolean setAppointmentType(String Type) {
        this.appointmentType = Type ;
        return true;
    }

    // Method to cancel the appointment BY THE PAITENT 
    public void cancel() {
        // CLEAR ALL THE PERSONAL DATA  
    	this.patientId = null;   // CLEAR DATA
        this.appointmentType = null; // CLEAR DATA 
        // WE ASSUME THE PATIENT CANCELS BEFORE THE ACT APT SO CONSULT NOTES AND MEDS NO NEED RESET 
        this.status = AppointmentStatus.AVAILABLE;      // either the user or doctor can cancel 
    }

    // Method to cancel the appointment BY THE DOCTOR
    public void docCancel() {
        this.status = AppointmentStatus.CANCELLED;      // either the user or doctor can cancel 
    }

    // Method to reschedule the appointment by updating the date and time
    public void reschedule(String newDate, String newTime) {
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = AppointmentStatus.PENDING; // Set status to PENDING when rescheduled SO DOCTOR CAN ACCEPT AGN 
    }


    // Method to confirm appointment by the doctor 
    
    public boolean confirmAppointment()
    {
        this.status = AppointmentStatus.SCHEDULED; // SET DATA TO SCHDULUED 
        return true;  
        
    }

    // Method to display appointment details
    public String displayDetails() {
        StringBuilder details = new StringBuilder("Appointment with Dr. " + doctorId + " on " + appointmentDate + " at " + appointmentTime);
        details.append(", Status: ").append(status).append("\n");

        // Display consultation notes and prescribed medications if appointment is completed
        if (status == AppointmentStatus.COMPLETED) {
            details.append("Consultation Notes: ").append(consultationNotes).append("\n");
            details.append("Prescribed Medications: \n");
            for (PrescribedMedication med : prescribedMedications) {
                details.append(" - ").append(med.toString()).append("\n");
            }
        }
        return details.toString();
    }


}
