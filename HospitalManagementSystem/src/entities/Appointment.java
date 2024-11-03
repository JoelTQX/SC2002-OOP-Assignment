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

    // Getters and display method for appointment details
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public AppointmentStatus getStatus() { return status; }
    public String getConsultationNotes() { return consultationNotes; }
    public List<PrescribedMedication> getPrescribedMedications() { return prescribedMedications; }

    public String displayDetails() {
        StringBuilder details = new StringBuilder("Appointment with Dr. " + doctorId + " on " + appointmentDate + " at " + appointmentTime);
        details.append(", Status: ").append(status).append("\n");
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
