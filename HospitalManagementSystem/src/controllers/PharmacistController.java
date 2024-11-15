package controllers;

import java.util.List;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import datastorage.Inventory;
import entities.Appointment;
import entities.Appointment.PrescribedMedication;
import entities.Medicine;
import entities.Pharmacist;
import entities.Replenishment;
import entities.User;

public class PharmacistController {
	
	private User user;
	private DataStorage dataStorage; 
	
	public PharmacistController(User user, DataStorage dataStorage) {
		this.user = (Pharmacist) user;
		this.dataStorage = dataStorage; 
	}
	public Inventory getInventory() {
		return this.dataStorage.getInventory();
	}
	
	//Update the status of a prescription
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
			medicationToDispense.setStatus("Dispensed");
		}
		
		System.out.println("Medicine for " + appointmentID + " has been successfully dispensed.");
		return;
		
	}
	
	//Method to create Replenishment Request
	public void createReplenishmentRequest(int medicineChoice, int medicineQuantity) {
		// TODO Auto-generated method stub
		String medicineName = dataStorage.getInventory().getMedicineRecords().get(medicineChoice).getMedicineName();
		Replenishment replenishment = new Replenishment( medicineName, medicineQuantity);
		
		ReplenishmentController replenishControl = new ReplenishmentController(dataStorage);
		replenishControl.addReplenishment(replenishment);
	}
	public List<Appointment> getCompletedAppointments() {
		// TODO Auto-generated method stub
		AppointmentController appointmentControl = new AppointmentController(this.dataStorage);
		return appointmentControl.getCompletedAppointments();
	}
}
