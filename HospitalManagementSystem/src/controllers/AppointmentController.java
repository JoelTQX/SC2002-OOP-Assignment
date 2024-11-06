package controllers;

import entities.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private List<Appointment> appointments;

    public AppointmentController() {
        this.appointments = new ArrayList<>();
    }

    // Method for a doctor to confirm or decline an appointment
    public void acceptOrDeclineAppointment(Appointment appointment, boolean accept) {
        if (accept) {
            appointment.setStatus(Appointment.AppointmentStatus.CONFIRMED);
            System.out.println("Appointment confirmed.");
        } else {
            appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
            System.out.println("Appointment declined.");
        }
    }

    // Method for a doctor to complete an appointment, including setting consultation notes and prescribed medications
    public void recordAppointmentOutcome(Appointment appointment, String consultationNotes, List<String> medications) {
        for (String medication : medications) {
            appointment.addPrescribedMedication(medication, 1); // Assuming 1 as the default quantity
        }
        appointment.completeAppointment(consultationNotes);
        System.out.println("Appointment outcome recorded and marked as completed.");
    }

    // Pharmacist method to view prescribed medications and update their status
    public void updatePrescriptionStatus(Appointment appointment, String medicationName, String newStatus) {
        for (Appointment.PrescribedMedication medication : appointment.getPrescribedMedications()) {
            if (medication.getMedicationName().equals(medicationName)) {
                medication.setStatus(newStatus);
                System.out.println("Prescription status updated to " + newStatus + " for medication: " + medicationName);
                return;
            }
        }
        System.out.println("Medication not found in the appointment record.");
    }

    // Method for pharmacists to view the outcome records to fulfill prescription orders
    public void viewAppointmentOutcomeRecord(Appointment appointment) {
        if (appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
            System.out.println("Appointment Outcome Record:");
            System.out.println(appointment.displayDetails());
        } else {
            System.out.println("No outcome record available. Appointment is not completed.");
        }
    }

    // Patient-specific methods for scheduling, rescheduling, and canceling appointments
    public boolean scheduleAppointment(Appointment appointment) {
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.");
            return true;
        } else {
            System.out.println("Selected slot is unavailable.");
            return false;
        }
    }

    public boolean rescheduleAppointment(Appointment appointment, String newDate, String newTime) {
        if (isSlotAvailable(appointment.getDoctorId(), newDate, newTime)) {
            appointment.reschedule(newDate, newTime);
            System.out.println("Appointment rescheduled successfully.");
            return true;
        } else {
            System.out.println("Selected slot is unavailable for rescheduling.");
            return false;
        }
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.cancel();
        System.out.println("Appointment cancelled successfully.");
    }

    // Check if a slot is available for a given doctor, date, and time
    private boolean isSlotAvailable(String doctorId, String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) &&
                appointment.getAppointmentDate().equals(date) &&
                appointment.getAppointmentTime().equals(time) &&
                !appointment.getStatus().equals(Appointment.AppointmentStatus.CANCELLED)) {
                return false;
            }
        }
        return true;
    }
}
