package controllers;

import java.util.List;

import datastorage.DataStorage;
import datastorage.Inventory;
import entities.Appointment;
import entities.Appointment.PrescribedMedication;
import entities.Pharmacist;
import entities.Replenishment;
import entities.User;

/**
 * The PharmacistController class handles the operations related to a pharmacist's responsibilities.
 * It includes managing prescriptions, dispensing medicines, and creating replenishment requests for the inventory.
 */
public class PharmacistController {
	
	private User user;
	private DataStorage dataStorage; 
	
    /**
     * Constructs a PharmacistController for managing pharmacist-specific operations.
     * 
     * @param user The user instance, which should be a Pharmacist.
     * @param dataStorage The DataStorage instance that contains all the data for managing appointments and inventory.
     */
	public PharmacistController(User user, DataStorage dataStorage) {
		this.user = (Pharmacist) user;
		this.dataStorage = dataStorage; 
	}
	
    /**
     * Gets the inventory of medicines from the data storage.
     * 
     * @return The Inventory object containing all medicine records.
     */
	public Inventory getInventory() {
		return this.dataStorage.getInventory();
	}
	
    /**
     * Updates the status of a prescription for a given appointment ID.
     * This includes checking the availability of medicines, dispensing the prescribed medications,
     * and updating the status of the prescribed medications.
     * 
     * @param appointmentID The ID of the appointment for which the prescription status needs to be updated.
     */
	public void updatePrescriptionStatus(String appointmentID) {
		AppointmentController appointmentControl = new AppointmentController(this.dataStorage);
		MedicineController medicineControl = new MedicineController(this.dataStorage.getInventory());
		Appointment appointment = appointmentControl.getAppointmentByID(appointmentID);
		
		//Check If Appointment Exist
		if(appointment == null) {
			System.out.println("Appointment Not Found... Please Try Again...");
			return;
		}
		
		//Check for Sufficient Medicines / Error In Name
		for( PrescribedMedication medicationToDispense : appointment.getPrescribedMedications()) {
			String medicineName = medicationToDispense.getMedicationName();
			int medicineQuantity = medicationToDispense.getMedicineQuantity();
			if(!medicineControl.isSufficient(medicineName, medicineQuantity)) {
				System.out.println("Error in dispensing medicine... Please Check Error");
				return;
			}
		}
		
		//Dispense Medicine
		for( PrescribedMedication medicationToDispense : appointment.getPrescribedMedications()) {
			String medicineName = medicationToDispense.getMedicationName();
			int medicineQuantity = medicationToDispense.getMedicineQuantity();
			medicineControl.dispenseMedicine(medicineName, medicineQuantity);
			medicationToDispense.setMedicineStatus("Dispensed");
		}
		
		System.out.println("Medicine for " + appointmentID + " has been successfully dispensed.");
		return;
		
	}
	
    /**
     * Creates a replenishment request for a specific medicine.
     * 
     * @param medicineChoice The index of the medicine in the inventory to be replenished.
     * @param medicineQuantity The quantity of the medicine to be replenished.
     */
	public void createReplenishmentRequest(int medicineChoice, int medicineQuantity) {
		// TODO Auto-generated method stub
		String medicineName = dataStorage.getInventory().getMedicineRecords().get(medicineChoice).getMedicineName();
		Replenishment replenishment = new Replenishment( medicineName, medicineQuantity);
		
		ReplenishmentController replenishControl = new ReplenishmentController(dataStorage);
		replenishControl.addReplenishment(replenishment);
	}
	
    /**
     * Gets the list of completed appointments.
     * 
     * @return A list of completed appointments.
     */
	public List<Appointment> getCompletedAppointments() {
		// TODO Auto-generated method stub
		AppointmentController appointmentControl = new AppointmentController(this.dataStorage);
		return appointmentControl.getCompletedAppointments();
	}
}
