package datastorage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;

public class AppointmentRecords {
    static int lastAppointmentID = 0;
    private List<Appointment> AppointmentRecords; // holds a list of appointments

    public AppointmentRecords() {
        AppointmentRecords = new ArrayList<Appointment>();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT RETRIEVAL METHODS ///////////////////////////////////////////////////////////////

    // Method to get an appointment by its ID
    public Appointment getAppointmentByID(String appointmentID) {
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                return appointment;
            }
        }
        return null; // Appointment Not Found
    }

    // Method to get the last appointment ID
    public int getLastAppointmentID() {
        return lastAppointmentID;
    }

    // Method to get the full list of appointments
    public List<Appointment> getFullAppointmentList() {
        return AppointmentRecords;
    }

    // Method to get appointments for a specific date
    public List<Appointment> getAppointmentsForDate(String date) {
        List<Appointment> appointmentsForDate = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getAppointmentDate().equals(date)) {
                appointmentsForDate.add(appointment);
            }
        }
        return appointmentsForDate;
    }

    // Method to get available appointment slots for a specific day
    public List<String> getEmptySlots(String date) {
        List<String> allSlots = List.of(
            "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"
        );
        List<String> emptySlots = new ArrayList<>(allSlots);
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getAppointmentDate().equals(date) && appointment.getStatus() != null) {
                String bookedTime = appointment.getAppointmentTime();
                emptySlots.remove(bookedTime);
            }
        }
        return emptySlots;
    }

    // Method to get all available slots
    public List<Appointment> getALLSlots() {
        List<Appointment> availableSlots = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getStatus() == AppointmentStatus.AVAILABLE) {
                availableSlots.add(appointment);
            }
        }
        return availableSlots;
    }

    // Method to get available slots for a specific date
    public List<Appointment> getSlotsForDate(String date) {
        List<Appointment> availableSlots = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getAppointmentDate().equals(date) &&
                appointment.getStatus() == AppointmentStatus.AVAILABLE) {
                availableSlots.add(appointment);
            }
        }
        return availableSlots;
    }

    // Method to retrieve scheduled appointments for a patient
    public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == AppointmentStatus.SCHEDULED) {
                scheduledAppointments.add(appointment);
            }
        }
        return scheduledAppointments;
    }

    // Method to retrieve completed appointments for a patient
    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == AppointmentStatus.COMPLETED) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }

    // Method to find all appointments for a specific patient ID
    public List<Appointment> findAppointmentsByPatientId(String patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId() != null && appointment.getPatientId().equals(patientId)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    // Method to get appointments for a doctor based on status
    public List<Appointment> getDocAppointments(String userId, AppointmentStatus status) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getDoctorId().equals(userId) && appointment.getStatus() == status) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    // Method to get appointments for a patient based on status
    public List<Appointment> getPatientAppointments(String userId, AppointmentStatus status) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(userId) && appointment.getStatus() == status) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    // Method to get all appointment records
    public List<Appointment> getAppointmentRecords() {
        return this.AppointmentRecords;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT MODIFICATION METHODS ///////////////////////////////////////////////////////////

    // Method to add an appointment
    public void addAppointment(Appointment appointment) {
        AppointmentRecords.add(appointment);
        lastAppointmentID++;
    }

    // Method to remove an appointment
    public void removeAppointment(Appointment appointment) {
        AppointmentRecords.remove(appointment);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// HELPER METHODS //////////////////////////////////////////////////////////////////////////////

    // Helper method to generate hourly slots from startTime to endTime for a specific date
    private List<String> generateHourlySlots(String date, String startTime, String endTime) {
        List<String> slots = new ArrayList<>();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(startTime, timeFormatter);
        LocalTime end = LocalTime.parse(endTime, timeFormatter);
        while (!start.isAfter(end)) {
            slots.add(date + " " + start.format(timeFormatter));
            start = start.plusHours(1); // Increment by one hour
        }
        return slots;
    }
}