package Objects;

import entities.Doctor;

// Appointment class
class Appointment {

    private Doctor doctor;
    private Patient patient;
    private Date date;
    private String typeOfService;
    private String status;
  
    //Constructor
    public Appointment(Doctor doctor, Date date, String typeOfService, String status) {
        this.doctor = doctor;
        this.date = date;
        this.patient = patient; 
        this.typeOfService = typeOfService;
        this.status = status;
    
    }

    // mutators methods
   
    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    // accessor methods 
    public Doctor getDoctor() {
        return doctor;
    }
    
    public Patient getPatient()
    {
        return patient; 
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

}

// Medication class
class Medication {

    private String name;
    private String status; // e.g., "Pending", "Dispensed"

    public Medication(String name) {
        this.name = name;
        this.status = "Pending"; // default status
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}