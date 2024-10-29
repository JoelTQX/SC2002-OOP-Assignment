package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// AppointmentManagement class
public class AppointmentManagement {

    // List to hold all appointments
    private List<Appointment> appointments;

    // Constructor
    public AppointmentManagement() {
        this.appointments = new ArrayList<>();
    }

    // Mutator Method
    // Method to add an appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

     // Method for doctor to record appointment outcome
     public void recordAppointmentOutcome(Appointment appointment, String serviceType, 
     List<Medication> medications, String notes) {
     appointment.setTypeOfService(serviceType);
     appointment.setMedications(medications);
     appointment.setConsultationNotes(notes);
     appointment.setStatus("Completed");
     System.out.println("Appointment outcome recorded successfully.");
    }

    // Acessor Methods 
    // Method to view all upcoming appointments for a doctor
    public List<Appointment> getUpcomingAppointments(Doctor doctor) {
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor) && appointment.getDate().after(new Date())) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }

   
}
