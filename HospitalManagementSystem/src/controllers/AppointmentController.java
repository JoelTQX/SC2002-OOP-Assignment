package entities;

import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private List<Appointment> appointments; // List of all appointments in the system

    // Constructor initializes the list of appointments
    public AppointmentController() {
        this.appointments = new ArrayList<>();
    }

    // Enum for appointment status within Appointment class
    public enum AppointmentStatus {
        SCHEDULED,   // Appointment has been scheduled but not yet confirmed by the doctor
        CONFIRMED,   // Doctor has accepted the appointment
        CANCELLED,   // Appointment has been cancelled by either the patient or doctor
        COMPLETED,   // Appointment has been completed
        PENDING      // Awaiting doctor’s response to confirm or decline
    }

    // Method to validate the user's role before performing specific actions
    private void validateRole(String role, String requiredRole) {
        if (!role.equals(requiredRole)) {
            throw new SecurityException("Access denied. Only " + requiredRole + " can perform this operation.");
        }
    }

    // Method to check if an hourly slot is available for a doctor on a specific date
    public boolean isSlotAvailable(String doctorId, String date, String time) {
        for (Appointment appointment : appointments) {
            // Checks for an existing appointment at the same date, time, and doctor
            if (appointment.getDoctorId().equals(doctorId) &&
                appointment.getAppointmentDate().equals(date) &&
                appointment.getAppointmentTime().equals(time) &&
                !appointment.getStatus().equals(AppointmentStatus.CANCELLED)) {
                return false; // Slot is already booked
            }
        }
        return true; // Slot is available
    }

    // Method to get a list of available slots for a doctor on a specific date
    public List<String> getAvailableSlots(String doctorId, String date) {
        List<String> availableSlots = new ArrayList<>();
        
        // Define working hours, e.g., 9 AM to 5 PM (Hourly slots)
        String[] workingHours = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        
        // Check each hour to see if it's available
        for (String hour : workingHours) {
            if (isSlotAvailable(doctorId, date, hour)) {
                availableSlots.add(hour); // Add available slot to the list
            }
        }
        return availableSlots; // Return all available slots for the specified date
    }

    // Patient Operation: Schedules an appointment if the selected slot is available
    public boolean scheduleAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient"); // Checks if the user is a patient
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            appointment.setStatus(AppointmentStatus.SCHEDULED); // Set status to SCHEDULED
            appointments.add(appointment); // Adds appointment if available
            displayMessage("Appointment scheduled successfully.");
            return true; // Successfully scheduled
        } else {
            displayMessage("The selected slot is not available. Please choose another time.");
            return false; // Slot not available
        }
    }

    // Patient Operation: Reschedules an appointment to a new date and time if the slot is available
    public boolean rescheduleAppointment(String role, Appointment appointment, String newDate, String newTime) {
        validateRole(role, "Patient"); // Checks if the user is a patient
        if (isSlotAvailable(appointment.getDoctorId(), newDate, newTime)) {
            appointment.setAppointmentDate(newDate); // Update to new date
            appointment.setAppointmentTime(newTime); // Update to new time
            appointment.setStatus(AppointmentStatus.SCHEDULED); // Set status back to SCHEDULED
            displayMessage("Appointment rescheduled successfully.");
            return true; // Rescheduled successfully
        } else {
            displayMessage("The selected slot is not available for rescheduling.");
            return false; // New slot is not available
        }
    }

    // Patient Operation: Cancels an existing appointment
    public void cancelAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient"); // Checks if the user is a patient
        appointment.setStatus(AppointmentStatus.CANCELLED); // Set status to CANCELLED
        displayMessage("Appointment cancelled successfully.");
    }

    // Patient Operation: View the status of scheduled appointments
    public void viewScheduledAppointments(String role, String patientId) {
        validateRole(role, "Patient"); // Checks if the user is a patient
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && !appointment.getStatus().equals(AppointmentStatus.COMPLETED)) {
                scheduledAppointments.add(appointment);
            }
        }
        displayAppointments(scheduledAppointments);
    }

    // Patient Operation: View outcome records of past appointments
    public void viewPastAppointments(String role, String patientId) {
        validateRole(role, "Patient"); // Checks if the user is a patient
        List<Appointment> pastAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals(AppointmentStatus.COMPLETED)) {
                pastAppointments.add(appointment);
            }
        }
        displayAppointments(pastAppointments);
    }

    // Doctor Operation: Accept or decline an appointment request
    public void acceptOrDeclineAppointment(String role, Appointment appointment, boolean accept) {
        validateRole(role, "Doctor"); // Checks if the user is a doctor
        appointment.setStatus(accept ? AppointmentStatus.CONFIRMED : AppointmentStatus.CANCELLED); // Update status accordingly
        displayMessage("Appointment " + (accept ? "accepted." : "declined."));
    }

    // Doctor Operation: View all upcoming appointments for the doctor
    public void viewUpcomingAppointments(String role, String doctorId) {
        validateRole(role, "Doctor"); // Checks if the user is a doctor
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals(AppointmentStatus.CONFIRMED)) {
                upcomingAppointments.add(appointment);
            }
        }
        displayAppointments(upcomingAppointments);
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
