package datastorage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.Appointment;
// note: The records do not modify the controller 

public class AppointmentRecords {
	static int lastAppointmentID = 0;
	private List<Appointment> AppointmentRecords;					// holds a list of appointment
	
	public AppointmentRecords() {
		AppointmentRecords = new ArrayList<Appointment>();
	}
	
	public Appointment getAppointmentByID(String appointmentID) {
		for(Appointment appointment : AppointmentRecords) {
			if(appointment.getAppointmentID().equals(appointmentID)) {
				return appointment;
			}
		}
		return null; //Patient Not Found
	}
	
	public int getLastAppointmentID() {
		return lastAppointmentID;
	}
	
	public void addAppointment(Appointment appointment) {
		AppointmentRecords.add(appointment);
		lastAppointmentID++;
	}
	
	public void removeAppointment(Appointment appointment) {
		AppointmentRecords.remove(appointment);
	}

// METHOD TO MODIFY THE LISTS

	  // Method to get available appointment slots for a specific day
    // used by the doc to CREATE THE SLOT
    // THIS IS WHERE THE SLOT CREATION HAPPENS
public List<String> getEmptySlots(String date) {
    // List of all possible hourly slots from 09:00 to 17:00
    List<String> allSlots = List.of(
        "09:00",
        "10:00",
        "11:00",
        "12:00",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00"
    );

    // Start with all possible slots for the day
    List<String> emptySlots = new ArrayList<>(allSlots);

    // Iterate through each appointment in AppointmentRecords
    for (Appointment appointment : AppointmentRecords) {
        // Check if the appointment is on the specified date and has a valid status
        if (appointment.getAppointmentDate().equals(date) && appointment.getStatus() != null) {
            // Get the time portion of the appointment (e.g., "09:00")
            String bookedTime = appointment.getAppointmentTime();
            
            // Remove the booked time from emptySlots if it exists
            emptySlots.remove(bookedTime);
        }
    }

    // Return the list of slots that remain unbooked
    return emptySlots;
}



	// HELPER METHODS

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
   
    //  helper function to return the appointment based on status 

    public List<Appointment> getDocAppointments(String userId, Appointment.AppointmentStatus status ) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getDoctorId().equals(userId) && appointment.getStatus() == status)  // is doctor requesting 
            {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    //  helper function to return the appointment based on status 

    public List<Appointment> getPatientAppointments(String userId, Appointment.AppointmentStatus status ) {
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(userId) && appointment.getStatus() == status)  // is patient requesting 
            {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

	   // GENERAL Method to get appointments for a specific date

	   public List<Appointment> getAppointmentsForDate(String date) {

        List<Appointment> appointmentsForDate = new ArrayList<>();

        for (Appointment appointment : AppointmentRecords) {

            if (appointment.getAppointmentDate().equals(date)) {

                appointmentsForDate.add(appointment);

            }

        }

        return appointmentsForDate;
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



	// PATIENT SPECIFIC 


	 // Method to get available appointment slots for a specific day WHERE SLOTS ARE SET TO AVAIL 
    // used by the paitent TO SEE THE DOC SLOTS
    // contingent on the doctor already having indicated AVAIL
public List<String> getSlots(String date) {
    List<String> availableSlots = new ArrayList<>();

    // Filter out slots that are already booked for the specified date
    for (Appointment appointment : AppointmentRecords) {
        if (appointment.getAppointmentDate().equals(date) &&
            appointment.getStatus() == Appointment.AppointmentStatus.AVAILABLE) {
            availableSlots.add(appointment.getAppointmentTime()); // Remove only the time part
        }
    }

    return availableSlots;
}


	 // Method to retrieve scheduled appointments for a patient
	 public List<Appointment> getScheduledAppointments(String patientId) {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == Appointment.AppointmentStatus.SCHEDULED) {
                scheduledAppointments.add(appointment);
            }
        }
        return scheduledAppointments;
    }

    // Method to retrieve completed appointments for a patient
    public List<Appointment> getCompletedAppointments(String patientId) {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : AppointmentRecords) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }





}
