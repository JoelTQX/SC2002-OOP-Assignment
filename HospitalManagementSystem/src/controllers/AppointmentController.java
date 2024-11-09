package controllers;

import java.util.List;

import datastorage.DataStorage;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Appointment.PrescribedMedication;
import entities.Patient;
import entities.User;
import javax.security.auth.login.AppConfigurationEntry;

public class AppointmentController {
    private User user; 
    private DataStorage dataStorage;
    // is reposnbile for all modification of the appointment 
    // handles the modifcaiton of a SINGLE appointment 
    
    public AppointmentController() {

    
    }
       // Method to update consultation notes and mark the appointment as completed
    public void completeAppointment(Appointment appointment, String consultationNotes) {
        if (appointment != null) {
            appointment.completeAppointment(consultationNotes);
        }
    }
    
    // Getter for appointment ID
    public String getAppointmentID(Appointment appointment) {       // pass the appointment in 
        return appointment.getAppointmentID(); 
    }   
    
    // setter for appointment ID
    public String setAppointmentID(Appointment appointment) {       // calls the generate appointment ID helper 
        
        String appointmentId = generateAppointmentID(); 
        if (appointment.setAppointmentID(appointmentId) == true )
        {
            return appointmentId; // appointment ID set properly 
        }
        else
        return "NULL";     // empty if ID canot be set

        
    }

    
    
    // Getter for patient ID
    public String getPatientId(Appointment appointment) {
        return appointment.getPatientId();
    }

    // Setter for patient ID
    public void setPatientId(Appointment appointment, String patientId) {
        appointment.setPatientId(patientId);;
    }
    

    // Getter for doctor ID
    public String getDoctorId(Appointment appointment) {
        return appointment.getDoctorId();
    }

 // setter for doctor ID
    public boolean setDoctorId( Appointment appointment, String doctorId) {
    return appointment.setDoctorId(doctorId);
   }           

    // Getter for appointment status
    public AppointmentStatus getStatus(Appointment appointment) {
        return appointment.getStatus();
    }
    
    // Setter for appointment status
    public void setStatus(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
    }


    // Getter for appointment date
    public String getAppointmentDate(Appointment appointment) {
        return appointment.getAppointmentDate();
    }

    // setter for appointment date 
    public boolean setAppointmentDate(Appointment appointment , String date)
    {  
        if(appointment != null)
        {
            appointment.setAppointmentDate(date); 
            return true;
        }
        else 
        return false; 
       
    }

    // Getter for appointment time
    public String getAppointmentTime( Appointment appointment) {
        return appointment.getAppointmentTime();
    }

    // Setter for appointment time
    public Boolean SetAppointmentTime( Appointment appointment, String time) {
        return appointment.setAppointmentTime(time);
    }    
    

    // Getter for appointment type
    public String getAppointmentType(Appointment appointment) {
        return appointment.getAppointmentType();
    }

     // setter for appointment type
     public boolean setAppointmentType( Appointment appointment, String Type) {
       return appointment.setAppointmentType(Type) ;
    
    }
    

    // Getter for consultation notes
    public String getConsultationNotes(Appointment appointment) {
        return appointment.getConsultationNotes();
    }


    // Setter for consulatoin NOTES 
    public boolean setConsultationNotes(Appointment appointment, String notes) {
        if (appointment != null) {
            appointment.setConsultationNotes(notes);
            return true; 
        }
        else
        {
            return false;
        }

        
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// METHODS FOR NESTED MEDICIATION CLASS/////////////////////////////////////////////////

    // Getter for prescribed medications
    public List<PrescribedMedication> getPrescribedMedications(Appointment appointment) {
        return appointment.getPrescribedMedications();
    }

    // setter for prescribed medication
    // Add a prescribed medication to an individual appointment
    public void addPrescribedMedication(Appointment appointment, String medicationName, int quantity) {
        if (appointment != null) {
            appointment.prescribedMedications.add(new PrescribedMedication(medicationName, quantity));  // uses a List.add() method to append to the list 
        }
    }


  
    // have to modify here apt list is priv

   






  
    

    // // Method to reschedule an appointment
    // public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
    //     Appointment appointment = findAppointmentById(appointmentId);
    //     if (appointment != null && isSlotAvailable(appointment.getDoctorId(), newDate, newTime)) {
    //         appointment.reschedule(newDate, newTime);
    //         // System.out.println("Appointment rescheduled successfully.");
    //         return true;
    //     }
    //     // System.out.println("Failed to reschedule. Check details or slot availability.");
    //     return false;
    // }

    // // Method to cancel an appointment
    // public boolean cancelAppointment(String appointmentId) {
    //     Appointment appointment = findAppointmentById(appointmentId);
    //     if (appointment != null) {
    //         appointment.cancel();
    //         // System.out.println("Appointment canceled successfully.");
    //         return true;
    //     }
    //     // System.out.println("Failed to cancel. Appointment not found.");
    //     return false;
    // }

   

  

    // // looks for the appointmentin the list 
    // public Appointment findAppointmentById(String appointmentId) {
    //     for (Appointment appointment : appointments) {
    //         if (appointment.getAppointmentID().equals(appointmentId)) {
    //             return appointment;
    //         }
    //     }
    //     return null;
    // }


    //  // Method to add prescribed medications to an appointment
    //  public boolean addPrescribedMedication(String appointmentId, List<Appointment.PrescribedMedication> medications) {
    //     Appointment appointment = findAppointmentById(appointmentId);
    //     if (appointment != null && appointment.getStatus() == Appointment.AppointmentStatus.SCHEDULED) {
    //         for (Appointment.PrescribedMedication medication : medications) {
    //             appointment.addPrescribedMedication(medication.getMedicationName(), medication.getMedicineQuantity());
    //         }
    //         return true;
    //     }
    //     return false;
    // }


    



    // HELPER METHODS 



    // Helper method for generating a unique appointment ID 
    // USED WHEN CREATING THE APPOINTMENT 

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

