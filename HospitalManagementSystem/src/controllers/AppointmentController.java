package controllers;

import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Appointment.PrescribedMedication;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    // calls the next lower level 
    private List<Appointment> appointments;     // hold a list of all the appointments 

  
    public AppointmentController() {
        appointments = new ArrayList<>();  // creates a new appt cont 
    }

    // Method to get available appointment slots for a specific day
    // used by the paitent TO SEE THE DOC SLOTS
    public List<String> getSlots(String date) {
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

    // have to modify here apt list is priv

     // Method to get available appointment slots for a specific day
    // used by the doc to CREATE THE SLOT
    // THIS IS WHERE THE SLOT CREATION HAPPENS
    public List<String> getDocSlots(String date) {
        List<String> availableDocSlots = generateHourlySlots(date, "09:00", "17:00");
        // gen a list of slots by the hour 
        // Filter out slots that are already booked
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == null && appointment.getAppointmentDate().equals(date)) {                        // slot not created HENCE NULL
                String bookedSlot = appointment.getAppointmentDate() + " " + appointment.getAppointmentTime();
                availableDocSlots.remove(bookedSlot); // Remove the booked slot
            }
        }

        return availableDocSlots;
    }

    // Method for doc to Create the slots 
    public boolean setAvailability(String doctorId, String date, String time) {
        // call the appointment constructor 
       // Check if the slot is already occupied
       if (isSlotAvailable(doctorId, date, time)) {
        // Generate a unique ID for the available slot
        String appointmentID = generateAppointmentID();
        
        // Create a new Appointment with status AVAILABLE
        Appointment availableSlot = new Appointment(appointmentID, null, doctorId, date, time, null);
        availableSlot.setStatus(Appointment.AppointmentStatus.AVAILABLE);
        
        // Add the available slot to the appointments list
        appointments.add(availableSlot);
        System.out.println("Availability set for Dr. " + doctorId + " on " + date + " at " + time);
        return true;
    } else {
        return false;
    }  

    }


    
    // general helper function to return the appointment based on status 

    public List<Appointment> getAppointments(String userId, Appointment.AppointmentStatus status ) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(userId) && appointment.getStatus() == status)  // is doctor requesting 
            {
                pendingAppointments.add(appointment);
            }
            else if (appointment.getPatientId().equals(userId) && appointment.getStatus() == status)  // is patient requesting 
            {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }



    // Method to schedule an appointment // THIS HAPPENS AFTER THE PATIENT INDICATES PENDING 
    // used by the doc
    // FINAL STEP BEFORE APPOINTMENT IS SET A SCHDULED 
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


     // Method to add prescribed medications to an appointment
     public boolean addPrescribedMedication(String appointmentId, List<Appointment.PrescribedMedication> medications) {
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null && appointment.getStatus() == Appointment.AppointmentStatus.SCHEDULED) {
            for (Appointment.PrescribedMedication medication : medications) {
                appointment.addPrescribedMedication(medication.getMedicationName(), medication.getMedicineQuantity());
            }
            return true;
        }
        return false;
    }


    

    // DOCTOR METHODS 


    // NEW: Record the outcome of an appointment
// RECORD THE APPOINTMENT OUTCOMES 
    public boolean recordAppointmentOutcome(String appointmentId, String userID, String date, String serviceType, List<String> medications, List<Integer> medicationQTY, String notes) {
        // Find the appointment by ID and ensure it exists and is scheduled
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null && appointment.getDoctorId().equals(userID) && 
            appointment.getStatus() == AppointmentStatus.SCHEDULED) {		// only scheduled appointments can be modified 
            
            // Set appointment details using AppointmentController methods
            appointment.setAppointmentDate(date);
            appointment.setAppointmentType(serviceType);
            appointment.setConsultationNotes(notes);
            appointment.setStatus( AppointmentStatus.COMPLETED);
    
            // Convert medications and quantities to a list of PrescribedMedication objects
            List<PrescribedMedication> prescribedMedications = new ArrayList<>();
            for (int i = 0; i < medications.size(); i++) {
                String medicationName = medications.get(i);
                Integer quantity = (i < medicationQTY.size()) ? medicationQTY.get(i) : 1; // Default to 1 if not specified
                prescribedMedications.add(new PrescribedMedication(medicationName, quantity));
            }
    
            // Add prescribed medications to the appointment
            appointment.addPrescribedMedications( prescribedMedications);
    
            return true;
        }
    
        return false;
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

