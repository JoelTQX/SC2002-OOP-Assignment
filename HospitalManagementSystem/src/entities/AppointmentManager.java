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

    // Method to schedule a new appointment if the slot is available
    public boolean scheduleAppointment(Appointment appointment) {
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            appointments.add(appointment); // Adds appointment if available
            return true; // Successfully scheduled
        } else {
            return false; // Slot not available
        }
    }

    // Method to reschedule an appointment to a new date and time if the slot is available
    public boolean rescheduleAppointment(Appointment appointment, String newDate, String newTime) {
        if (isSlotAvailable(appointment.getDoctorId(), newDate, newTime)) {
            appointment.setAppointmentDate(newDate); // Update to new date
            appointment.setAppointmentTime(newTime); // Update to new time
            return true; // Rescheduled successfully
        } else {
            return false; // New slot is not available
        }
    }

    // Method to cancel an appointment
    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus("Cancelled"); // Set status to cancelled
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

    // Method to retrieve all scheduled appointments for a patient
    public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && !appointment.getStatus().equals("Completed")) {
                result.add(appointment); // Adds scheduled (but not completed) appointments
            }
        }
        return result;
    }

    // Method to retrieve past completed appointments for a patient
    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals("Completed")) {
                result.add(appointment); // Adds completed appointments
            }
        }
        return result;
    }

    // Method to accept or decline an appointment request by the doctor
    public void acceptAppointment(Appointment appointment, boolean accept) {
        appointment.setDoctorAccepted(accept); // Set acceptance status
        appointment.setStatus(accept ? "Confirmed" : "Cancelled"); // Update status accordingly
    }
}
