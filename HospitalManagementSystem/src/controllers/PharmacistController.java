package controllers;

import datastorage.DataStorage;
import datastorage.Inventory;
import entities.Medicine;
import entities.Pharmacist;
import entities.User;

public class PharmacistController {
	
	private User user;
	private MedicineController medicineControl;
	private DataStorage dataStorage;
	
	public PharmacistController(User user, DataStorage dataStorage) {
		this.user = (Pharmacist) user;
		this.dataStorage = dataStorage;
		this.medicineControl = new MedicineController();
	}
	public Inventory getInventory() {
		return this.dataStorage.getInventory();
	}

	public void getOutcomeRecords() {
		
		
	}
	//Take in Medicine Name & Appointment Outcome and adjust the stock and change to dispensed.
	public void updatePresciptionStatus(String medicineName) {
		// TODO Auto-generated method stub
		Medicine medicineToAdjust = dataStorage.getInventory().getMedicineByName(medicineName);
		int quantityToDispense = 0;
		medicineControl.adjustStock(medicineToAdjust, quantityToDispense);
		//Set Status to dispensed...
	}
	
	
}
