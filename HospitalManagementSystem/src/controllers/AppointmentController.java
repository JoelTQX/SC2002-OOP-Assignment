package controllers;

import entities.Appointment;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private List<Appointment> appointments;     // hold a list of all the appointments 

  
    public AppointmentController() {
        this.appointments = new ArrayList<>();  // creates a new appt cont 
    }

    // Method to get available appointment slots for a specific day
    // used by both the doc and the paitent 
    public List<String> getAvailableSlots(String date) {
        List<String> availableSlots = generateHourlySlots(date, "09:00", "17:00");
        // gen a list of slots by the hour 
        // Filter out slots that are already booked
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() != Appointment.AppointmentStatus.CANCELLED && 
                appointment.getAppointmentDate().equals(date)) {
                String bookedSlot = appointment.getAppointmentDate() + " " + appointment.getAppointmentTime();
                availableSlots.remove(bookedSlot); // Remove the booked slot
            }
        }

        return availableSlots;
    }

    // Method to schedule an appointment
    // used by the doc
    public boolean scheduleAppointment(Appointment appointment) {
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);
            appointments.add(appointment);
            return true;
        } else {
            return false;
        }
    }

    // Method to reschedule an appointment
    public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null && isSlotAvailable(appointment.getDoctorId(), newDate, newTime)) {
            appointment.reschedule(newDate, newTime);
            // System.out.println("Appointment rescheduled successfully.");
            return true;
        }
        // System.out.println("Failed to reschedule. Check details or slot availability.");
        return false;
    }

    // Method to cancel an appointment
    public boolean cancelAppointment(String appointmentId) {
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null) {
            appointment.cancel();
            // System.out.println("Appointment canceled successfully.");
            return true;
        }
        // System.out.println("Failed to cancel. Appointment not found.");
        return false;
    }

    // Method to retrieve scheduled appointments for a patient
    public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == Appointment.AppointmentStatus.SCHEDULED) {
                scheduledAppointments.add(appointment);
            }
        }
        return scheduledAppointments;
    }

    // Method to retrieve completed appointments for a patient
    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
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


    // looks for the appointmentin the list 
    public Appointment findAppointmentById(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }



    // DOCTOR METHODS 

    // NEW: Method for the doctor to query their free slots 
    public String getSchedule(String Date)
    {
        String Dates =  ;
        return Dates;
    }

       // NEW: Retrieve pending appointment requests for a specific doctor
       public List<Appointment> getPendingAppointmentsForDoctor(String doctorId) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) &&
                appointment.getStatus() == Appointment.AppointmentStatus.PENDING) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    // NEW: Retrieve upcoming appointments for a specific doctor
    // ALSO DOUBLES AS THE SCHDULE 
    // ONLY SCHDEULED STATUS 
    public List<Appointment> getUpcomingAppointmentsForDoctor(String doctorId) {
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) &&
                (appointment.getStatus() == Appointment.AppointmentStatus.CONFIRMED ||      // 
                 appointment.getStatus() == Appointment.AppointmentStatus.SCHEDULED)) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }


    // NEW: Update an appointment in the system
    public void updateAppointment(Appointment appointment) {
        // In a real system, this would update the appointment in a database or persistent storage
        // Here, we assume `appointments` list is the current storage
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentID().equals(appointment.getAppointmentID())) {
                appointments.set(i, appointment); // Update the appointment in the list
                break;
            }
            
        }
    }

     // Helper method to generate hourly slots from startTime to endTime for a specific date
 private List<String> generateHourlySlots(String date, String startTime, String endTime) {
    List<String> slots = new ArrayList<>();
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    
    // Parse start and end times
    LocalTime start = LocalTime.parse(startTime, timeFormatter);
    LocalTime end = LocalTime.parse(endTime, timeFormatter);

    // Generate slots on the hour
    while (!start.isAfter(end)) {
        slots.add(date + " " + start.format(timeFormatter));
        start = start.plusHours(1); // Increment by one hour
    }

    return slots;
}



    // Helper method for generating a unique appointment ID

	private static int counter = 0; // lowkey have no idea how to put this

	//i noticed that using just system time was giving fking long IDs and I thought we could shorten them down 
    public String generateAppointmentID() {
		long timestamp = System.currentTimeMillis();
    
		// Use only the last 5 hex digits of the timestamp for a shorter ID
		String hexTimestamp = Long.toHexString(timestamp & 0xFFFFF); // Last 5 hex digits
		String hexCounter = Integer.toHexString(counter++ & 0xF); // Add 1 hex digit from counter
		counter = counter % 16; // Keep counter within 1 hex digit range (0-15)
	
		return "A" + hexTimestamp.toUpperCase() + hexCounter.toUpperCase();
    }

}

