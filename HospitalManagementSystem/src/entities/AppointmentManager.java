package entities;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    private List<Appointment> appointments; // List of all appointments

    // Constructor initializes the list of appointments
    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    // Method to check if an hourly slot is available for a doctor on a specific date
    public boolean isSlotAvailable(String doctorId, String date, String time) {
        for (Appointment appointment : appointments) {
            // Checks for an existing appointment at the same date, time, and doctor
            if (appointment.getDoctorId().equals(doctorId) && 
                appointment.getAppointmentDate().equals(date) && 
                appointment.getAppointmentTime().equals(time) && 
                !appointment.getStatus().equals("Cancelled")) {
                return false; // Slot is already booked
            }
        }
        return true; // Slot is available
    }

    // Method to schedule a new appointment if the slot is available
    public boolean scheduleAppointment(Appointment appointment) {
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            appointments.add(appointment); // Adds appointment if available
            return true; // Successfully scheduled
        } else {
            return false; // Slot not available
        }
    }

    // Method to retrieve all upcoming appointments for a specific doctor
    public List<Appointment> getUpcomingAppointments(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Confirmed")) {
                result.add(appointment); // Adds confirmed appointments to the list
            }
        }
        return result;
    }
}
