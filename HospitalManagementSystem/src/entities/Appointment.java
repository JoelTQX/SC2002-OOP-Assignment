package entities;

import java.util.ArrayList;
import java.util.List;

public class Appointment {

    // Attributes to hold basic appointment details
    private String patientId;
    private String doctorId;
    private AppointmentStatus status; // Using AppointmentStatus enum for status
    private String appointmentDate;
    private String appointmentTime; // Now in hourly format (e.g., "10:00", "15:00")
    private String appointmentType;
    private String consultationNotes;
    private boolean doctorAccepted; // Indicates if the doctor has accepted the appointment

    // List to track multiple medicines dispensed during this appointment
    private List<MedicineDispensed> medicinesDispensed;

    // Constructor to initialize a new appointment with essential details
    public Appointment(String patientId, String doctorId, String appointmentDate, String appointmentTime, String appointmentType) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime; // Hourly appointment time
        this.appointmentType = appointmentType;
        this.status = AppointmentStatus.SCHEDULED; // Default status is SCHEDULED
        this.doctorAccepted = false; // Default to not accepted by doctor
        this.medicinesDispensed = new ArrayList<>(); // Initializes empty list for medicines
    }

    // Getter and setter methods for each attribute, allowing controlled access

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

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
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
        this.appointmentTime = appointmentTime; // Allows updating of the hourly time
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

    // Adds a new medicine to the list of dispensed medicines for this appointment
    public void addMedicineDispensed(String medicineName, String dosage) {
        this.medicinesDispensed.add(new MedicineDispensed(medicineName, dosage));
    }

    // Marks all medicines in the list as dispensed (fulfilled by pharmacist)
    public void markAllMedicinesAsDispensed() {
        for (MedicineDispensed medicine : medicinesDispensed) {
            medicine.markAsDispensed();
        }
    }

    // Displays appointment details in a formatted string for easy viewing
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
