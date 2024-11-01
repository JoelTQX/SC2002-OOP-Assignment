package controllers;

public class AppointmentController {

    // Reference to the AppointmentManager to handle the logic for managing appointments
    private AppointmentManager appointmentManager;

    // Constructor to initialize AppointmentManager
    public AppointmentController(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    // Private helper method to check the role of the user before allowing operation
    private void validateRole(String role, String requiredRole) {
        // If the user's role does not match the required role, throw a security exception
        if (!role.equals(requiredRole)) {
            throw new SecurityException("Access denied. Only " + requiredRole + " can perform this operation.");
        }
    }

    // Patient Operations

    // Method for a patient to schedule an appointment
    public void scheduleAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient"); // Ensure the user has "Patient" role
        appointmentManager.addAppointment(appointment); // Delegate to appointmentManager
    }

    // Method for a patient to reschedule an appointment with a new date and time
    public void rescheduleAppointment(String role, Appointment appointment, String newDate, String newTime) {
        validateRole(role, "Patient"); // Ensure the user has "Patient" role
        appointmentManager.updateAppointment(appointment, newDate, newTime); // Update appointment details
    }

    // Method for a patient to cancel an appointment
    public void cancelAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient"); // Ensure the user has "Patient" role
        appointmentManager.cancelAppointment(appointment); // Cancel the appointment
    }

    // Method for a patient to view their scheduled appointments
    public void viewScheduledAppointments(String role, String patientId, AppointmentView view) {
        validateRole(role, "Patient"); // Ensure the user has "Patient" role
        view.displayAppointments(appointmentManager.getScheduledAppointments(patientId)); // Display appointments
    }

    // Method for a patient to view past appointment outcome records
    public void viewPastAppointmentOutcomeRecords(String role, String patientId, AppointmentView view) {
        validateRole(role, "Patient"); // Ensure the user has "Patient" role
        view.displayAppointments(appointmentManager.getCompletedAppointments(patientId)); // Display completed appointments
    }

    // Doctor Operations

    // Method for a doctor to set their availability for appointments
    public void setAvailabilityForAppointments(String role, String doctorId, String date, String time) {
        validateRole(role, "Doctor"); // Ensure the user has "Doctor" role
        appointmentManager.addAvailability(doctorId, date, time); // Add availability
    }

    // Method for a doctor to accept or decline an appointment request
    public void acceptOrDeclineAppointmentRequest(String role, Appointment appointment, boolean accept) {
        validateRole(role, "Doctor"); // Ensure the user has "Doctor" role
        appointmentManager.acceptAppointment(appointment, accept); // Accept or decline appointment
    }

    // Method for a doctor to record the outcome of an appointment
    public void recordAppointmentOutcome(String role, Appointment appointment, String notes) {
        validateRole(role, "Doctor"); // Ensure the user has "Doctor" role
        appointmentManager.completeAppointment(appointment, notes); // Record outcome notes
    }

    // Method for a doctor to view their upcoming appointments
    public void viewUpcomingAppointments(String role, String doctorId, AppointmentView view) {
        validateRole(role, "Doctor"); // Ensure the user has "Doctor" role
        view.displayAppointments(appointmentManager.getUpcomingAppointments(doctorId)); // Display upcoming appointments
    }
}
