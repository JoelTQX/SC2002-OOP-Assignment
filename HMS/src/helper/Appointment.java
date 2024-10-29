package helper;

// Appointment class
class Appointment {

    private Doctor doctor;
    private Date date;
    private String typeOfService;
    private String status;
    private List<Medication> medications;
    private String consultationNotes;

    //Constructor
    public Appointment(Doctor doctor, Date date, String typeOfService, String status) {
        this.doctor = doctor;
        this.date = date;
        this.typeOfService = typeOfService;
        this.status = status;
        this.medications = new ArrayList<>();
    }

    // mutators methods
   
    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }


    // accessor methods 
    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

}

// Medication class
class MedicationDispensed {

    private String name;
    private String status; // e.g., "Pending", "Dispensed"

    public MedicationDispensed(String name) {
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