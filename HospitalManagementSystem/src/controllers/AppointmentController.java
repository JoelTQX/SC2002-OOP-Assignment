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

    // Patient Operation: View available slots for a specific doctor on a specific date
    public void viewAvailableSlots(String role, String doctorId, String date) {
        validateRole(role, "Patient"); // Checks if user is a patient
        List<String> availableSlots = appointmentManager.getAvailableSlots(doctorId, date);
        displaySlots(availableSlots);
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

    // Patient Operation: Reschedules an appointment to a new date and time
    public void rescheduleAppointment(String role, Appointment appointment, String newDate, String newTime) {
        validateRole(role, "Patient"); // Checks if user is a patient
        boolean success = appointmentManager.rescheduleAppointment(appointment, newDate, newTime);
        if (success) {
            displayMessage("Appointment rescheduled successfully.");
        } else {
            displayMessage("The selected slot is not available for rescheduling.");
        }
    }

    // Patient Operation: Cancels an existing appointment
    public void cancelAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient"); // Checks if user is a patient
        appointmentManager.cancelAppointment(appointment);
        displayMessage("Appointment cancelled successfully.");
    }

    // Patient Operation: View the status of scheduled appointments
    public void viewScheduledAppointments(String role, String patientId) {
        validateRole(role, "Patient"); // Checks if user is a patient
        List<Appointment> appointments = appointmentManager.getScheduledAppointments(patientId);
        displayAppointments(appointments);
    }

    // Patient Operation: View outcome records of past appointments
    public void viewPastAppointments(String role, String patientId) {
        validateRole(role, "Patient"); // Checks if user is a patient
        List<Appointment> appointments = appointmentManager.getCompletedAppointments(patientId);
        displayAppointments(appointments);
    }

    // Doctor Operation: Accept or decline an appointment request
    public void acceptOrDeclineAppointment(String role, Appointment appointment, boolean accept) {
        validateRole(role, "Doctor"); // Checks if user is a doctor
        appointmentManager.acceptAppointment(appointment, accept);
        displayMessage("Appointment " + (accept ? "accepted." : "declined."));
    }

    // Doctor Operation: View all upcoming appointments for the doctor
    public void viewUpcomingAppointments(String role, String doctorId) {
        validateRole(role, "Doctor"); // Checks if user is a doctor
        List<Appointment> appointments = appointmentManager.getUpcomingAppointments(doctorId);
        displayAppointments(appointments);
    }

    // Helper method to display available slots for a doctor on a specific date
    private void displaySlots(List<String> slots) {
        if (slots.isEmpty()) {
            System.out.println("No available slots.");
        } else {
            System.out.println("Available slots:");
            for (String slot : slots) {
                System.out.println(slot);
            }
        }
    }

    // Helper method to display appointments
    private void displayAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No appointments to display.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    // Helper method to display custom messages
    private void displayMessage(String message) {
        System.out.println(message);
    }
}
