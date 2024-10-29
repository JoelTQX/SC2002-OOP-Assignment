package helper;


import java.util.Date;

import entities.Doctor;

public class AppointmentManagement {

    // Attributes
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String typeOfService;
    private String status;

    // Constructor
    public AppointmentManagement(Patient patient, Doctor doctor, Date date, String typeOfService, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.typeOfService = typeOfService;
        this.status = status;
    }

    // mutator methods 
    public void setDate(Date date) {
        this.date = date;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //accessor methods
   
    public Date getDate() {
        return date;
    }
   
    public Doctor getDoctor() {
        return doctor;
    }

    public String getStatus() {
        return status;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public Patient getPatient() {
        return patient;
    }

}
