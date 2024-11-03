package entities;

import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private List<Appointment> appointments; // List of all appointments in the system

    public AppointmentController() {
        this.appointments = new ArrayList<>();
    }

    // Method to schedule an appointment
    public boolean scheduleAppointment(String role, Appointment appointment) {
        if (!role.equals("Patient")) {
            System.out.println("Only patients can schedule appointments.");
            return false;
        }
        // Check slot availability (dummy check for simplicity)
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            appointment.confirm();
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.");
            return true;
        } else {
            System.out.println("Selected slot is unavailable.");
            return false;
        }
    }

    // Method to reschedule an existing appointment
    public boolean rescheduleAppointment(String role, Appointment appointment, String newDate, String newTime) {
        if (!role.equals("Patient")) {
            System.out.println("Only patients can reschedule appointments.");
            return false;
        }
        if (appointment.reschedule(newDate, newTime)) {
            System.out.println("Appointment rescheduled successfully.");
            return true;
        } else {
            System.out.println("Cannot reschedule completed or canceled appointments.");
            return false;
        }
    }

    // Method to cancel an appointment if allowed by role
    public void cancelAppointment(String role, Appointment appointment) {
        if (appointment.canBeCancelledBy(role)) {
            appointment.cancel();
            System.out.println("Appointment cancelled successfully.");
        } else {
            System.out.println("Cancellation not allowed.");
        }
    }

    // Method to check if an hourly slot is available for a doctor on a specific date
    private boolean isSlotAvailable(String doctorId, String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && 
                appointment.getAppointmentDate().equals(date) &&
                appointment.getAppointmentTime().equals(time) &&
                appointment.getStatus() != Appointment.AppointmentStatus.CANCELLED) {
                return false; // Slot is already booked
            }
        }
        return true; // Slot is available
    }

    // Method to view all appointments for a given patient
    public void viewAppointments(String role, String patientId) {
        if (!role.equals("Patient")) {
            System.out.println("Only patients can view their appointments.");
            return;
        }
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                System.out.println(appointment.displayDetails());
            }
        }
    }
}
