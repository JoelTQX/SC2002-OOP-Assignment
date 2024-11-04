package entities;

import java.util.ArrayList;
import java.util.List;

public class Appointment {

    // Enum for appointment status
    public enum AppointmentStatus {
        SCHEDULED,
        CONFIRMED,
        CANCELLED,
        COMPLETED,
        PENDING
    }

    // Attributes
    private String patientId;
    private String doctorId;
    private AppointmentStatus status;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentType;
    private List<PrescribedMedication> prescribedMedications; // Medications prescribed during the appointment
    private String consultationNotes;

    // Constructor
    public Appointment(String patientId, String doctorId, String appointmentDate, String appointmentTime, String appointmentType) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentType = appointmentType;
        this.status = AppointmentStatus.PENDING;
        this.prescribedMedications = new ArrayList<>();
    }

    // Nested class to represent a prescribed medication
    public static class PrescribedMedication {
        private String medicationName;
        private String status;

        public PrescribedMedication(String medicationName) {
            this.medicationName = medicationName;
            this.status = "Pending"; // Default status
        }

        public String getMedicationName() {
            return medicationName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return medicationName + " (Status: " + status + ")";
        }
    }

    // Method to add a prescribed medication
    public void addPrescribedMedication(String medicationName) {
        prescribedMedications.add(new PrescribedMedication(medicationName));
    }

    // Method to update consultation notes and mark the appointment as completed
    public void completeAppointment(String consultationNotes) {
        this.consultationNotes = consultationNotes;
        this.status = AppointmentStatus.COMPLETED;
    }

    // Getter for patient ID
    public String getPatientId() {
        return patientId;
    }

    // Getter for doctor ID
    public String getDoctorId() {
        return doctorId;
    }

    // Getter for appointment status
    public AppointmentStatus getStatus() {
        return status;
    }

    // Setter for appointment status
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    // Getter for consultation notes
    public String getConsultationNotes() {
        return consultationNotes;
    }

    // Getter for prescribed medications
    public List<PrescribedMedication> getPrescribedMedications() {
        return prescribedMedications;
    }

    // Method to cancel the appointment
    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    // Method to reschedule the appointment by updating the date and time
    public void reschedule(String newDate, String newTime) {
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = AppointmentStatus.SCHEDULED; // Set status to SCHEDULED when rescheduled
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

    // Getter for appointment date
    public String getAppointmentDate() {
        return appointmentDate;
    }

    // Getter for appointment time
    public String getAppointmentTime() {
        return appointmentTime;
    }

    // Getter for appointment type
    public String getAppointmentType() {
        return appointmentType;
    }
}
