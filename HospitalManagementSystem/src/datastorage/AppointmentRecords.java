package datastorage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;

/**
 * The `AppointmentRecords` class manages the list of appointments and provides various methods
 * to retrieve, modify, and manage appointments, such as getting available slots, retrieving appointments
 * by status, and adding or removing appointments.
 */
public class AppointmentRecords {
    /**
     * The last generated appointment ID. Used to assign a unique ID to each new appointment.
     */
    static int lastAppointmentID = 0;
    
    /**
     * List of all appointments.
     */
    private List<Appointment> AppointmentRecords; // holds a list of appointments

    /**
     * Constructs an empty list of appointment records.
     */
    public AppointmentRecords() {
        AppointmentRecords = new ArrayList<Appointment>();
    }

    /**
     * Retrieves an appointment by its unique appointment ID.
     *
     * @param appointmentID The appointment ID to search for.
     * @return The appointment matching the given ID, or null if not found.
     */
    public Appointment getAppointmentByID(String appointmentID) {
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                return appointment;
            }
        }
        return null; // Appointment Not Found
    }

    /**
     * Retrieves the last used appointment ID.
     *
     * @return The last appointment ID.
     */
    public int getLastAppointmentID() {
        return lastAppointmentID;
    }

    /**
     * Retrieves the full list of all appointments.
     *
     * @return A list of all appointments.
     */
    public List<Appointment> getFullAppointmentList() {
        return AppointmentRecords;
    }

    /**
     * Retrieves all appointments for a specific date.
     *
     * @param date The date for which to retrieve appointments.
     * @return A list of appointments on the given date.
     */
    public List<Appointment> getAppointmentsForDate(String date) {
        List<Appointment> appointmentsForDate = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getAppointmentDate().equals(date)) {
                appointmentsForDate.add(appointment);
            }
        }
        return appointmentsForDate;
    }
    /**
     * Retrieves a list of available appointment slots for a specific date.
     *
     * @param date The date for which to retrieve available slots.
     * @return A list of available appointment time slots.
     */
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

    /**
     * Retrieves all available appointment slots in the system.
     *
     * @return A list of available appointments with status AVAILABLE.
     */
    public List<Appointment> getALLSlots() {
        List<Appointment> availableSlots = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getStatus() == AppointmentStatus.AVAILABLE) {
                availableSlots.add(appointment);
            }
        }
        return availableSlots;
    }

    /**
     * Retrieves available appointment slots for a specific date.
     *
     * @param date The date for which to retrieve available slots.
     * @return A list of available appointments on the given date.
     */
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

    /**
     * Retrieves scheduled appointments for a specific patient based on their patient ID.
     *
     * @param patientId The patient ID for which to retrieve scheduled appointments.
     * @return A list of scheduled appointments for the given patient.
     */
    public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == AppointmentStatus.SCHEDULED) {
                scheduledAppointments.add(appointment);
            }
        }
        return scheduledAppointments;
    }

    /**
     * Retrieves completed appointments for a specific patient based on their patient ID.
     *
     * @param patientId The patient ID for which to retrieve completed appointments.
     * @return A list of completed appointments for the given patient.
     */
    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == AppointmentStatus.COMPLETED) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }

    /**
     * Retrieves appointments for a specific patient based on their patient ID.
     *
     * @param patientId The patient ID for which to retrieve appointments.
     * @return A list of appointments for the given patient.
     */
    public List<Appointment> findAppointmentsByPatientId(String patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId() != null && appointment.getPatientId().equals(patientId)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    /**
     * Retrieves doctor appointments based on the doctor's user ID and the appointment status.
     *
     * @param userId The doctor’s user ID.
     * @param status The appointment status.
     * @return A list of appointments for the doctor with the given status.
     */
    public List<Appointment> getDocAppointments(String userId, AppointmentStatus status) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getDoctorId().equals(userId) && appointment.getStatus() == status) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    /**
     * Retrieves patient appointments based on the patient’s user ID and the appointment status.
     *
     * @param userId The patient’s user ID.
     * @param status The appointment status.
     * @return A list of appointments for the patient with the given status.
     */
    public List<Appointment> getPatientAppointments(String userId, AppointmentStatus status) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId() != null && appointment.getPatientId().equals(userId) && appointment.getStatus() == status) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    /**
     * Retrieves the list of all appointment records.
     *
     * @return A list of all appointments.
     */
    public List<Appointment> getAppointmentRecords() {
        return this.AppointmentRecords;
    }

    /**
     * Adds a new appointment to the records and increments the last appointment ID.
     *
     * @param appointment The appointment to add to the records.
     */
    public void addAppointment(Appointment appointment) {
        AppointmentRecords.add(appointment);
        lastAppointmentID++;
    }

    /**
     * Removes a specific appointment from the records.
     *
     * @param appointment The appointment to remove from the records.
     */
    public void removeAppointment(Appointment appointment) {
        AppointmentRecords.remove(appointment);
    }



    /**
     * Generates hourly slots for a given date within a start and end time range.
     *
     * @param date The date for which to generate slots.
     * @param startTime The start time of the range (inclusive).
     * @param endTime The end time of the range (inclusive).
     * @return A list of time slots within the specified range for the given date.
     */
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