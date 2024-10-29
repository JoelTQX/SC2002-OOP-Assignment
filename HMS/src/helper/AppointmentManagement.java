package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// AppointmentManagement class
public class AppointmentManagement {

    // List to hold all appointments
    private List<Appointment> appointments;

    // Constructor
    public AppointmentManagement() 
    {
        this.appointments = new ArrayList<>();  // Create the array list 
    }

    // Mutator Method
    // Method to add an appointment
    public void addAppointment(Appointment appointment) 
    {
        appointments.add(appointment);
    }

    // Method for doctor to record a new consultation outcome within an appointment
    public void recordConsultationOutcome(Appointment appointment, String serviceType, List<MedicationDispensed> medications, String notes) 
    {
        Consultation consultation = new Consultation(serviceType, "Completed");         //Mark consult as DONE
        consultation.setConsultationNotes(notes);   // Add consult notes
        
        // Add medications to the consult
        for (MedicationDispensed medication : medications) {
            consultation.addMedication(medication);
        }

        // Add the consultation to the appointment
        appointment.addConsultation(consultation);
        System.out.println("Consultation outcome recorded successfully for the appointment.");
    }

    // Accessor Methods
    // Method to view all upcoming appointments for a doctor
    public List<Appointment> getUpcomingAppointments(Doctor doctor) 
    {
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor) && appointment.getDate().after(new Date())) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }

    // Method to show ALL appointments 
    public List<Appointment> getAllAppointments()
    {
        return appointments;
    }
}
