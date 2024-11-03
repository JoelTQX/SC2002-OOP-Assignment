package entities;

public class Appointment {

    public enum AppointmentStatus {
        SCHEDULED,   // Appointment has been scheduled but not yet confirmed by the doctor
        CONFIRMED,   // Doctor has accepted the appointment
        CANCELLED,   // Appointment has been cancelled by either the patient or doctor
        COMPLETED,   // Appointment has been completed
        PENDING      // Awaiting doctor’s response to confirm or decline
    }

    private String patientId;
    private String doctorId;
    private AppointmentStatus status;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentType;

    // Constructor to initialize an appointment with basic details
    public Appointment(String patientId, String doctorId, String appointmentDate, String appointmentTime, String appointmentType) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentType = appointmentType;
        this.status = AppointmentStatus.PENDING; // Default status when created
    }

    // Method to check if a specific user role can cancel the appointment
    public boolean canBeCancelledBy(String role) {
        return (role.equals("Patient") && this.status != AppointmentStatus.COMPLETED);
    }

    // Method to cancel the appointment
    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    // Method to confirm the appointment (only a doctor can confirm it)
    public void confirm() {
        this.status = AppointmentStatus.CONFIRMED;
    }

    // Method to reschedule the appointment
    public boolean reschedule(String newDate, String newTime) {
        if (this.status == AppointmentStatus.SCHEDULED || this.status == AppointmentStatus.PENDING) {
            this.appointmentDate = newDate;
            this.appointmentTime = newTime;
            return true;
        }
        return false;
    }

    // Method to display appointment details
    public String displayDetails() {
        return String.format("Appointment with Dr. %s on %s at %s, Status: %s", doctorId, appointmentDate, appointmentTime, status);
    }

    // Additional getters and setters for patientId, doctorId, etc., could go here
}
