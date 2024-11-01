package entities;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    private List<Appointment> appointments;

    // Constructor
    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    // Role validation method
    private void validateRole(String role, String requiredRole) {
        if (!role.equals(requiredRole)) {
            throw new SecurityException("Access denied. Only " + requiredRole + " can perform this operation.");
        }
    }

    // Patient Functions

    public void viewMedicalRecord(String role, String patientId) {
        validateRole(role, "Patient");
        // Logic to retrieve and display patient's medical record
        System.out.println("Displaying medical record for patient ID: " + patientId);
    }


    public void viewAvailableAppointmentSlots(String role, String doctorId) {
        validateRole(role, "Patient");
        System.out.println("Available Appointment Slots with Dr. " + doctorId + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Available")) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    public void scheduleAppointment(String role, Appointment appointment) {
        validateRole(role, "Patient");
        appointment.setStatus("Scheduled");
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    public void rescheduleAppointment(String role, String patientId, String appointmentId, String newDate, String newTime) {
        validateRole(role, "Patient");
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals("Scheduled")) {
                appointment.setAppointmentDate(newDate);
                appointment.setAppointmentTime(newTime);
                System.out.println("Appointment rescheduled successfully.");
                return;
            }
        }
        System.out.println("No matching appointment found for rescheduling.");
    }

    public void cancelAppointment(String role, String patientId, String appointmentId) {
        validateRole(role, "Patient");
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals("Scheduled")) {
                appointment.setStatus("Cancelled");
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("No matching appointment found to cancel.");
    }

    public void viewScheduledAppointments(String role, String patientId) {
        validateRole(role, "Patient");
        System.out.println("Scheduled Appointments for patient ID: " + patientId);
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && !appointment.getStatus().equals("Completed")) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    public void viewPastAppointmentOutcomeRecords(String role, String patientId) {
        validateRole(role, "Patient");
        System.out.println("Past Appointment Outcome Records for patient ID: " + patientId);
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equals("Completed")) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    // Doctor Functions

    public void viewPatientMedicalRecords(String role, String doctorId) {
        validateRole(role, "Doctor");
        System.out.println("Viewing medical records for patients under Dr. " + doctorId);
        // Logic to view all medical records for patients under the care of the doctor
    }

    public void updatePatientMedicalRecords(String role, String patientId, String newDiagnosis, String newTreatment) {
        validateRole(role, "Doctor");
        System.out.println("Updating medical record for patient ID: " + patientId);
        // Logic to update a patient’s medical record with new diagnoses and treatments
    }

    public void viewPersonalSchedule(String role, String doctorId) {
        validateRole(role, "Doctor");
        System.out.println("Upcoming Appointments for Dr. " + doctorId);
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && !appointment.getStatus().equals("Cancelled")) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    public void setAvailabilityForAppointments(String role, String doctorId, String date, String time) {
        validateRole(role, "Doctor");
        Appointment newAvailableSlot = new Appointment(null, doctorId, date, time, "Available");
        appointments.add(newAvailableSlot);
        System.out.println("Availability slot added for Dr. " + doctorId);
    }

    public void acceptOrDeclineAppointmentRequest(String role, String doctorId, String appointmentId, boolean accept) {
        validateRole(role, "Doctor");
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Scheduled")) {
                appointment.setDoctorAccepted(accept);
                appointment.setStatus(accept ? "Confirmed" : "Cancelled");
                System.out.println("Appointment " + (accept ? "accepted." : "declined."));
                return;
            }
        }
        System.out.println("No matching appointment found.");
    }

    public void viewUpcomingAppointments(String role, String doctorId) {
        validateRole(role, "Doctor");
        System.out.println("Upcoming Appointments for Dr. " + doctorId);
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Confirmed")) {
                System.out.println(appointment.displayAppointmentDetails());
            }
        }
    }

    public void recordAppointmentOutcome(String role, String doctorId, String appointmentId, String notes) {
        validateRole(role, "Doctor");
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Confirmed")) {
                appointment.setConsultationNotes(notes);
                appointment.setStatus("Completed");
                System.out.println("Appointment outcome recorded.");
                return;
            }
        }
        System.out.println("No matching appointment found.");
    }
}
