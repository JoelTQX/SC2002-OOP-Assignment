package entities;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    // List to store all appointments
    private List<Appointment> appointments;

    // Constructor initializes the appointments list
    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    // Patient Functions (No role validation or printing)

    // Method to get the next available appointment for a specific doctor
    public Appointment getAvailableAppointments(String doctorId) {
        for (Appointment appointment : appointments) {
            // Check if the appointment is for the specified doctor and is available
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Available")) {
                return appointment;
            }
        }
        return null; // Return null if no available appointment is found
    }

    // Method to add a new appointment for a patient, setting its status as "Scheduled"
    public void addAppointment(Appointment appointment) {
        appointment.setStatus("Scheduled"); // Set appointment status to "Scheduled"
        appointments.add(appointment); // Add the appointment to the list
    }

    // Method to update the date and time of an existing appointment
    public void updateAppointment(Appointment appointment, String newDate, String newTime) {
        appointment.setAppointmentDate(newDate); // Update appointment date
        appointment.setAppointmentTime(newTime); // Update appointment time
    }

    // Method to cancel a patient's appointment, setting its status as "Cancelled"
    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus("Cancelled"); // Set appointment status to "Cancelled"
    }

    // Method to retrieve a list of all scheduled appointments for a specific patient
    public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            // Add appointments that are for the patient and not yet completed
            if (appointment.getPatientId().equals(patientId) && !appointment.getStatus().equals("Completed")) {
                result.add(appointment);
            }
        }
        return result; // Return the list of scheduled appointments
    }

    // Method to retrieve a list of completed appointments for a specific patient
    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            // Add appointments that are completed for the specified patient
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals("Completed")) {
                result.add(appointment);
            }
        }
        return result; // Return the list of completed appointments
    }

    // Doctor Functions (No role validation or printing)

    // Method to retrieve upcoming confirmed appointments for a specific doctor
    public List<Appointment> getUpcomingAppointments(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            // Add appointments that are confirmed and for the specified doctor
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Confirmed")) {
                result.add(appointment);
            }
        }
        return result; // Return the list of upcoming appointments
    }

    // Method to add a new availability slot for a doctor
    public void addAvailability(String doctorId, String date, String time) {
        // Create a new available appointment slot for the doctor
        Appointment newAvailableSlot = new Appointment(null, doctorId, date, time, "Available");
        appointments.add(newAvailableSlot); // Add the available slot to the list
    }

    // Method for a doctor to accept or decline an appointment request
    public void acceptAppointment(Appointment appointment, boolean accept) {
        appointment.setDoctorAccepted(accept); // Set acceptance status
        appointment.setStatus(accept ? "Confirmed" : "Cancelled"); // Update status based on acceptance
    }

    // Method for a doctor to complete an appointment, adding consultation notes
    public void completeAppointment(Appointment appointment, String notes) {
        appointment.setConsultationNotes(notes); // Set consultation notes
        appointment.setStatus("Completed"); // Mark the appointment as completed
    }
}
