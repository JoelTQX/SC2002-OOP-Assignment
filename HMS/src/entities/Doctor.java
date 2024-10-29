package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Doctor {

    // Attributes
    private String doctorID;
    private String name;
    private List<Date> personalSchedule;

    // Constructor
    public Doctor(String doctorID, String name) {
        this.doctorID = doctorID;
        this.name = name;
        this.personalSchedule = new ArrayList<>();
    }

    // Accessor methods
    public String getDoctorID() {
        return doctorID;
    }

    public String getName() {
        return name;
    }

    public List<Date> getPersonalSchedule() {
        return personalSchedule;
    }

    // Mutator methods
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void viewPersonalSchedule() {
        System.out.println("Personal Schedule for Doctor " + name + ":");
        for (Date date : personalSchedule) {
            System.out.println(date);
        }
    }

    public void setAvailability(Date date) {
        personalSchedule.add(date);
        System.out.println("Availability set for " + date);
    }

    public void acceptAppointment(boolean accepted) {
        if (accepted) {
            System.out.println("Appointment accepted.");
        } else {
            System.out.println("Appointment not accepted.");
        }
    }

    public void declineAppointment(boolean declined) {
        if (declined) {
            System.out.println("Appointment declined.");
        } else {
            System.out.println("Appointment not declined.");
        }
    }

    public void appointmentOutcomeRecord(String details) {
        System.out.println("Appointment Outcome Record: " + details);
    }

    public boolean isAvailable(Date date) {
        return !personalSchedule.contains(date);
    }
}
