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
    
    // Constructor
    public AppointmentController(DataStorage dataStorage) {
        this.appointmentRecords = dataStorage.getAppointmentRecords();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT MODIFICATION METHODS ///////////////////////////////////////////////////////////

    // Method to update consultation notes and mark the appointment as completed
    public void completeAppointment(Appointment appointment, String consultationNotes) {
        if (appointment != null) {
            appointment.completeAppointment(consultationNotes);
        }
    }

    // Setter for appointment ID
    public String setAppointmentID(Appointment appointment, String appointmentId) {
        if (appointment.setAppointmentID(appointmentId)) {
            return appointmentId; // appointment ID set properly 
        } else {
            return "NULL"; // empty if ID cannot be set
        }
    }

    // Setter for patient ID
    public void setPatientId(Appointment appointment, String patientId) {
        appointment.setPatientId(patientId);
    }

    // Setter for doctor ID
    public boolean setDoctorId(Appointment appointment, String doctorId) {
        return appointment.setDoctorId(doctorId);
    }

    // Setter for appointment status
    public void setStatus(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
    }

    // Setter for appointment date
    public boolean setAppointmentDate(Appointment appointment, String date) {
        if (appointment != null) {
            appointment.setAppointmentDate(date); 
            return true;
        } else {
            return false; 
        }
    }

    // Setter for appointment time
    public Boolean SetAppointmentTime(Appointment appointment, String time) {
        return appointment.setAppointmentTime(time);
    }

    // Setter for appointment type
    public boolean setAppointmentType(Appointment appointment, String Type) {
        return appointment.setAppointmentType(Type);
    }

    // Setter for consultation notes
    public boolean setConsultationNotes(Appointment appointment, String notes) {
        if (appointment != null) {
            appointment.setConsultationNotes(notes);
            return true; 
        } else {
            return false;
        }
    }

    // Add a prescribed medication to an individual appointment
    public void addPrescribedMedication(Appointment appointment, String medicationName, int quantity) {
        if (appointment != null) {
            appointment.getPrescribedMedications().add(new PrescribedMedication(medicationName, quantity));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT RETRIEVAL METHODS ///////////////////////////////////////////////////////////////

    // Getter for appointment ID
    public String getAppointmentID(Appointment appointment) {
        return appointment.getAppointmentID();
    }

    // Getter for patient ID
    public String getPatientId(Appointment appointment) {
        return appointment.getPatientId();
    }

    // Getter for doctor ID
    public String getDoctorId(Appointment appointment) {
        return appointment.getDoctorId();
    }

    // Getter for appointment status
    public AppointmentStatus getStatus(Appointment appointment) {
        return appointment.getStatus();
    }

    // Getter for appointment date
    public String getAppointmentDate(Appointment appointment) {
        return appointment.getAppointmentDate();
    }

    // Getter for appointment time
    public String getAppointmentTime(Appointment appointment) {
        return appointment.getAppointmentTime();
    }

    // Getter for appointment type
    public String getAppointmentType(Appointment appointment) {
        return appointment.getAppointmentType();
    }

    // Getter for consultation notes
    public String getConsultationNotes(Appointment appointment) {
        return appointment.getConsultationNotes();
    }

    // Getter for prescribed medications
    public List<PrescribedMedication> getPrescribedMedications(Appointment appointment) {
        return appointment.getPrescribedMedications();
    }

    // Access Appointment Records to Retrieve desired Appointment
    public Appointment getAppointmentByID(String appointmentID) {
        return this.appointmentRecords.getAppointmentByID(appointmentID);
    }

    public List<Appointment> getAppointmentRecords() {
        return this.appointmentRecords.getAppointmentRecords();
    }

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

    // Method to display appointment details
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

    // Helper method to format the list of prescribed medications
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

    // Helper method for generating a unique appointment ID
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