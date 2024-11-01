package entities;

import java.util.ArrayList;
import java.util.List;

public class Appointment {

    // Attributes
    private String patientId;
    private String doctorId;
    private String status;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentType;
    private String consultationNotes;
    private boolean doctorAccepted; // New attribute to track doctor acceptance status

    // List to hold multiple medicines dispensed
    private List<MedicineDispensed> medicinesDispensed;

    // Constructor
    public Appointment(String patientId, String doctorId, String appointmentDate, String appointmentTime, String appointmentType) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentType = appointmentType;
        this.status = "Scheduled";
        this.doctorAccepted = false; // Default to false
        this.medicinesDispensed = new ArrayList<>();
    }

    // Getters and Setters
    public boolean isDoctorAccepted() {
        return doctorAccepted;
    }

    public void setDoctorAccepted(boolean doctorAccepted) {
        this.doctorAccepted = doctorAccepted;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public List<MedicineDispensed> getMedicinesDispensed() {
        return medicinesDispensed;
    }

    // Method to add a medicine to the list
    public void addMedicineDispensed(String medicineName, String dosage) {
        this.medicinesDispensed.add(new MedicineDispensed(medicineName, dosage));
    }

    // Method to update the status of all dispensed medicines
    public void markAllMedicinesAsDispensed() {
        for (MedicineDispensed medicine : medicinesDispensed) {
            medicine.markAsDispensed();
        }
    }

    // Utility method to display appointment and medication details
    public String displayAppointmentDetails() {
        StringBuilder details = new StringBuilder(String.format("Appointment with Dr. %s on %s at %s, Status: %s\n",
                doctorId, appointmentDate, appointmentTime, status));
        details.append("Doctor Accepted: ").append(doctorAccepted ? "Yes" : "No").append("\n");
        details.append("Medicines Dispensed:\n");
        for (MedicineDispensed medicine : medicinesDispensed) {
            details.append(medicine.toString()).append("\n");
        }
        return details.toString();
    }
}
