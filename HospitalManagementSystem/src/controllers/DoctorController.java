package controllers;

import datastorage.DataStorage;
import datastorage.AppointmentRecords;
import datastorage.PatientRecords;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Appointment.PrescribedMedication;
import entities.Patient;
import entities.Doctor;
import java.util.List;
import java.util.ArrayList;
import entities.User;

public class DoctorController {
    private User user; // The logged-in doctor
    private DataStorage dataStorage; // Main data storage
    private AppointmentController appointmentController; // Controller for handling individual appointments
    private AppointmentRecords appointmentRecords; // For managing appointment records

    // Constructor
    public DoctorController(User user, DataStorage dataStorage) {
        this.user = (Doctor)user;
        this.dataStorage = dataStorage;
        this.appointmentController = new AppointmentController(); // Instantiating AppointmentController
        this.appointmentRecords = dataStorage.getAppointmentRecords(); // Retrieve AppointmentRecords from dataStorage
    }

    // Fetches patient records from data storage
    public PatientRecords getPatientsRecords() {
        return dataStorage.getPatientRecords();
    }

    // Sets availability by creating empty slots (appointments) for specific dates and times
    public boolean setAvailability(String date, String time) {
        String doctorId = user.getUserID();
        Appointment newAppointment = new Appointment(
            appointmentController.generateAppointmentID(),
            null, // No patient yet
            doctorId,
            date,
            time,
            null // No specific type yet
        );
        newAppointment.setStatus(AppointmentStatus.AVAILABLE);
        appointmentRecords.addAppointment(newAppointment); // Add new availability slot to records
        return true;
    }

    // Retrieves empty slots for a specific date, so the doctor can view availability options
    public List<String> getEmptySlots(String date) {
        List<String> emptySlots = new ArrayList<>();
        List<Appointment> appointments = appointmentRecords.getAppointmentsForDate(date);
        
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == AppointmentStatus.AVAILABLE) {
                emptySlots.add(appointment.getAppointmentTime());
            }
        }
        return emptySlots;
    }

    // Retrieves all pending appointment requests for the logged-in doctor
	// doctor is to confirm 

    public List<Appointment> getAppointmentRequests() {
        return appointmentRecords.getAppointments(user.getUserID(), AppointmentStatus.PENDING);
    }

    // Handles accepting or declining an appointment request
    public boolean handleAppointmentRequest(String appointmentId, boolean isAccepted) {
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment != null && appointment.getDoctorId().equals(user.getUserID())) {
            AppointmentStatus newStatus = isAccepted ? AppointmentStatus.SCHEDULED : AppointmentStatus.CANCELLED;
            appointmentController.setStatus(appointment, newStatus);
            return true;
        }
        return false;
    }

    // Retrieves a list of upcoming appointments for the doctor
    public List<Appointment> getUpcomingAppointments() {
        return appointmentRecords.getAppointments(user.getUserID(), AppointmentStatus.SCHEDULED);
    }

    // Records the outcome of an appointment, including prescribed medications and notes
    public boolean recordAppointmentOutcome(
            String appointmentId,
            String date,
            String serviceType,
            List<String> medications,
            List<Integer> medicationQTY,
            String notes
    ) {
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment != null && appointment.getDoctorId().equals(user.getUserID()) && 
            appointment.getStatus() == AppointmentStatus.SCHEDULED) {

            // Update appointment details
            appointmentController.setAppointmentDate(appointment, date);
            appointmentController.setAppointmentType(appointment, serviceType);
            appointmentController.setConsultationNotes(appointment, notes);
            appointmentController.setStatus(appointment, AppointmentStatus.COMPLETED);

            // Add prescribed medications
            for (int i = 0; i < medications.size(); i++) {
                String medicationName = medications.get(i);
                int quantity = i < medicationQTY.size() ? medicationQTY.get(i) : 1; // Default quantity if not specified
                appointmentController.addPrescribedMedication(appointment, medicationName, quantity);
            }
            return true;
        }
        return false;
    }
    
    // Updates the patient's records with new diagnoses, prescriptions, and treatment plans
    public boolean updatePatientRecords(String patientId, String newDiagnoses, String prescriptions, String treatmentPlan) {
        Patient patient = dataStorage.findPatientById(patientId);
        if (patient != null) {
            patient.addDiagnosis(newDiagnoses);
            patient.addPrescription(prescriptions);
            patient.setTreatmentPlan(treatmentPlan);
            dataStorage.updatePatientRecord(patient);
            return true;
        }
        return false;
    }
}
