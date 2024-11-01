package entities;

import java.util.List;

public class AppointmentController {

    private AppointmentManager appointmentManager;

    // Constructor receives an instance of AppointmentManager
    public AppointmentController(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    // Method to validate the user's role before performing actions
    private void validateRole(String role, String requiredRole) {
        if (!role.equals(requiredRole)) {
            throw new SecurityException("Access denied. Only " + requiredRole + " can perform this operation.");
        }
    }

    // Patient Operation: Schedules an appointment if slot is available
    public void scheduleAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient"); // Checks if user is a patient
        boolean success = appointmentManager.scheduleAppointment(appointment);
        if (success) {
            displayMessage("Appointment scheduled successfully.");
        } else {
            displayMessage("The selected slot is not available. Please choose another time.");
        }
    }

    // Doctor Operation: View all upcoming appointments for the doctor
    public void viewUpcomingAppointments(String role, String doctorId) {
        validateRole(role, "Doctor"); // Checks if user is a doctor
        List<Appointment> appointments = appointmentManager.getUpcomingAppointments(doctorId);
        displayAppointments(appointments);
    }

    // Helper method to display appointments, originally part of AppointmentView
    private void displayAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No appointments to display.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    // Helper method to display custom messages, originally part of AppointmentView
    private void displayMessage(String message) {
        System.out.println(message);
    }
}
