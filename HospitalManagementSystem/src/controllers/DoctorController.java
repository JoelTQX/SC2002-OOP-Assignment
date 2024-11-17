package controllers;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import datastorage.PatientRecords;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Doctor;
import entities.Patient;
import entities.User;
import java.util.List;

/**
 * The DoctorController class manages the operations of a doctor within the healthcare system. 
 * It provides functionalities for handling patient records and managing appointments.
 * It includes methods for managing patient diagnoses, prescriptions, treatment plans,
 * and setting up appointment availability and handling appointment requests.
 */
public class DoctorController {
    private User user; // The logged-in doctor
    private DataStorage dataStorage; // Main data storage
    private AppointmentController appointmentController; // Controller for handling individual appointments
    private AppointmentRecords appointmentRecords; // For managing appointment records

    /**
     * Constructor to initialize the DoctorController with the logged-in user and data storage.
     * 
     * @param user The logged-in doctor
     * @param dataStorage The data storage for the system
     */
    public DoctorController(User user, DataStorage dataStorage) {
        this.user = (Doctor)user;
        this.dataStorage = dataStorage;
        this.appointmentController = new AppointmentController(dataStorage); // Instantiating AppointmentController
        this.appointmentRecords = dataStorage.getAppointmentRecords(); // Retrieve AppointmentRecords from dataStorage
    }

    /**
     * Retrieves the list of patients assigned to the doctor based on their appointments.
     * 
     * @return A PatientRecords object containing the list of patients
     */
    public PatientRecords getPatientsRecords() {
    	
    	PatientRecords doctorPatients = new PatientRecords();
    	PatientRecords allPatients = dataStorage.getPatientRecords();
    	for(Appointment appointment : dataStorage.getAppointmentRecords().getAppointmentRecords()) {
    		if(appointment.getDoctorId().equals(user.getUserID())) {
    			Patient patient = allPatients.getPatientByID(appointment.getPatientId());
    			//Check if Patient Exist
    			if(patient == null) continue;
    			//Check if Patient already added into doctorPatients
    			if(doctorPatients.getPatientByID(patient.getUserID()) != null) continue;
    			doctorPatients.addPatient(allPatients.getPatientByID(appointment.getPatientId()));
    		}
    	}
        return doctorPatients;
    }


    /**
     * Updates the patient's records with new diagnoses, prescriptions, and treatment plans.
     * 
     * @param patientId The ID of the patient
     * @param newDiagnoses The new diagnoses to be added
     * @param prescriptions The prescriptions to be added
     * @param treatmentPlan The treatment plan to be added
     * @return true if the update is successful, false otherwise
     */
    public boolean updatePatientRecords(String patientId, String newDiagnoses, String prescriptions, String treatmentPlan) {
        Patient patient = dataStorage.getPatientRecords().getPatientByID(patientId);
        if (patient != null) {
            patient.addPatientdiagnoses(newDiagnoses);
            //patient.addPrescription(prescriptions);
            patient.addPatienttreatment(treatmentPlan);
            //dataStorage.updatePatientRecord(patient);
            return true;
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /// APPOINTMENT MANAGEMENT METHODS //////////////////////////////////////////////////////////////

    /**
     * Sets availability for the doctor by creating empty appointment slots for specific dates and times.
     * 
     * @param date The date for the appointment slot
     * @param time The time for the appointment slot
     * @return true if the availability is successfully set, false if an appointment already exists at the same time
     */
public boolean setAvailability(String date, String time) {
    String doctorId = user.getUserID();

    // Check for existing slot for the same doctor at the same time and date
    List<Appointment> existingAppointments = appointmentRecords.getAppointmentsForDate(date);
    for (Appointment appointment : existingAppointments) {
        if (appointment.getDoctorId().equals(doctorId) && appointment.getAppointmentTime().equals(time)) {
            System.out.println("An appointment slot already exists for the same doctor at the same time and date.");
            return false;
        }
    }

    // Create new appointment slot
    Appointment newAppointment = new Appointment(
        appointmentController.generateAppointmentID(),
        null, // No patient yet
        doctorId,
        date,
        time,
        null // No specific type yet
    );
    newAppointment.setStatus(AppointmentStatus.AVAILABLE);
    newAppointment.setAppointmentDate(date);
    appointmentRecords.addAppointment(newAppointment); // Add new availability slot to records
    return true;
}

	/**
	 * Retrieves the list of empty appointment slots for a specific date.
	 * 
	 * @param date The date for which empty slots are being retrieved
	 * @return A list of empty appointment slots available for the given date
	 */
    public List<String> getEmptySlots(String date) {
        return appointmentRecords.getEmptySlots(date);
    }

    /**
     * Retrieves all pending appointment requests for the logged-in doctor.
     * 
     * @return A list of pending appointments for the logged-in doctor
     */
    public List<Appointment> getAppointmentRequests() {
        return appointmentRecords.getDocAppointments(user.getUserID(), AppointmentStatus.PENDING);
    }

    /**
     * Handles the acceptance or decline of an appointment request.
     * 
     * @param appointmentId The ID of the appointment to handle
     * @param isAccepted true if the appointment is accepted, false if declined
     * @return true if the appointment status is successfully updated, false otherwise
     */
    public boolean handleAppointmentRequest(String appointmentId, boolean isAccepted) {
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment != null && appointment.getDoctorId().equals(user.getUserID())) {
            AppointmentStatus newStatus = isAccepted ? AppointmentStatus.SCHEDULED : AppointmentStatus.CANCELLED;
            appointmentController.setStatus(appointment, newStatus);
            return true;
        }
        return false;
    }

    /**
     * Retrieves a list of upcoming appointments for the logged-in doctor.
     * 
     * @return A list of upcoming appointments for the logged-in doctor
     */
    public List<Appointment> getUpcomingAppointments() {
        return appointmentRecords.getDocAppointments(user.getUserID(), AppointmentStatus.SCHEDULED);
    }

    /**
     * Records the outcome of an appointment, including prescribed medications and notes.
     * 
     * @param appointmentId The ID of the appointment
     * @param date The date of the appointment
     * @param serviceType The type of service for the appointment
     * @param medications A list of prescribed medications
     * @param medicationQTY A list of medication quantities
     * @param notes Additional notes for the appointment
     * @return true if the outcome is successfully recorded, false otherwise
     */
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
}