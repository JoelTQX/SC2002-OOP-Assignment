package controllers

public class AppointmentController {

    private AppointmentManager appointmentManager;

    public AppointmentController(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    private void validateRole(String role, String requiredRole) {
        if (!role.equals(requiredRole)) {
            throw new SecurityException("Access denied. Only " + requiredRole + " can perform this operation.");
        }
    }

    // Patient Operations

    public void scheduleAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient");
        appointmentManager.addAppointment(appointment);
    }

    public void rescheduleAppointment(String role, Appointment appointment, String newDate, String newTime) {
        validateRole(role, "Patient");
        appointmentManager.updateAppointment(appointment, newDate, newTime);
    }

    public void cancelAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient");
        appointmentManager.cancelAppointment(appointment);
    }

    public void viewScheduledAppointments(String role, String patientId, AppointmentView view) {
        validateRole(role, "Patient");
        view.displayAppointments(appointmentManager.getScheduledAppointments(patientId));
    }

    public void viewPastAppointmentOutcomeRecords(String role, String patientId, AppointmentView view) {
        validateRole(role, "Patient");
        view.displayAppointments(appointmentManager.getCompletedAppointments(patientId));
    }

    // Doctor Operations

    public void setAvailabilityForAppointments(String role, String doctorId, String date, String time) {
        validateRole(role, "Doctor");
        appointmentManager.addAvailability(doctorId, date, time);
    }

    public void acceptOrDeclineAppointmentRequest(String role, Appointment appointment, boolean accept) {
        validateRole(role, "Doctor");
        appointmentManager.acceptAppointment(appointment, accept);
    }

    public void recordAppointmentOutcome(String role, Appointment appointment, String notes) {
        validateRole(role, "Doctor");
        appointmentManager.completeAppointment(appointment, notes);
    }

    public void viewUpcomingAppointments(String role, String doctorId, AppointmentView view) {
        validateRole(role, "Doctor");
        view.displayAppointments(appointmentManager.getUpcomingAppointments(doctorId));
    }
}
