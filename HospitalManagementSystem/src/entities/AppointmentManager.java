package entities;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    private List<Appointment> appointments;

    // Constructor
    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    // Patient Functions (No role validation or printing)

    public Appointment getAvailableAppointments(String doctorId) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Available")) {
                return appointment;
            }
        }
        return null;
    }

    public void addAppointment(Appointment appointment) {
        appointment.setStatus("Scheduled");
        appointments.add(appointment);
    }

    public void updateAppointment(Appointment appointment, String newDate, String newTime) {
        appointment.setAppointmentDate(newDate);
        appointment.setAppointmentTime(newTime);
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus("Cancelled");
    }

    public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && !appointment.getStatus().equals("Completed")) {
                result.add(appointment);
            }
        }
        return result;
    }

    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals("Completed")) {
                result.add(appointment);
            }
        }
        return result;
    }

    // Doctor Functions (No role validation or printing)

    public List<Appointment> getUpcomingAppointments(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Confirmed")) {
                result.add(appointment);
            }
        }
        return result;
    }

    public void addAvailability(String doctorId, String date, String time) {
        Appointment newAvailableSlot = new Appointment(null, doctorId, date, time, "Available");
        appointments.add(newAvailableSlot);
    }

    public void acceptAppointment(Appointment appointment, boolean accept) {
        appointment.setDoctorAccepted(accept);
        appointment.setStatus(accept ? "Confirmed" : "Cancelled");
    }

    public void completeAppointment(Appointment appointment, String notes) {
        appointment.setConsultationNotes(notes);
        appointment.setStatus("Completed");
    }
}
