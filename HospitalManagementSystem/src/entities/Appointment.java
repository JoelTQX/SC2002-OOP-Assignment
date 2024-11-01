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
        this.medicinesDispensed = new ArrayList<>();
    }

    // Getters and Setters
    // (other getters and setters remain the same)

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
        details.append("Medicines Dispensed:\n");
        for (MedicineDispensed medicine : medicinesDispensed) {
            details.append(medicine.toString()).append("\n");
        }
        return details.toString();
    }
}
