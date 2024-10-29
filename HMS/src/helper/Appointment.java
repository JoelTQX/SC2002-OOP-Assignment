package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Appointment class, with list of consultations in order to record the outcome of mutliple past consultations 
class Appointment {
    private Doctor doctor;
    private Date date;
    private List<Consultation> consultations;

    // Constructor
    public Appointment(Doctor doctor, Date date) 
    {
        this.doctor = doctor;
        this.date = date;
        this.consultations = new ArrayList<>();
    }

    // Method to add a consultation to the appointment
    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);

    }

    // Accessor methods
    public Doctor getDoctor() 
    {
        return doctor;
    }

    public Date getDate() 
    {
        return date;
    }

    public List<Consultation> getConsultations()    // Implmented as a list to allow for multiple 
    {
        return consultations;
    }
}


// Consultation class to represent each individual past consultation 
class Consultation {
    private String typeOfService;           // e.g., "Consultation", "Follow-up"
    private String consultationNotes;
    private String status;                  // e.g., "Pending", "Completed"
    private List<MedicationDispensed> medications;

    // Constructor methods 
    public Consultation(String typeOfService, String status) {
        this.typeOfService = typeOfService;
        this.status = status;
        this.medications = new ArrayList<>();
    }

    // Mutator methods
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addMedication(MedicationDispensed medication) {
        this.medications.add(medication);
    }

    // Accessor methods
    public String getTypeOfService() {
        return typeOfService;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public String getStatus() {
        return status;
    }

    public List<MedicationDispensed> getMedications() {
        return medications;
    }
}

// MedicationDispensed class to store medication details for each consultation
class MedicationDispensed {
    private String name;
    private String status; // e.g., "Pending", "Dispensed"

    // Constructor
    public MedicationDispensed(String name) {
        this.name = name;
        this.status = "Pending"; // Default status
    }

    // Accessor methods
    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    // Mutator method to update medication status
    public void setStatus(String status) {
        this.status = status;
    }
}