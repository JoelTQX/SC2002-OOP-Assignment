package controllers;

import datastorage.DataStorage;
import datastorage.Inventory;
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

	public void getOutcomeRecords() {
		
		
	}
	//Take in Medicine Name & Appointment Outcome and adjust the stock and change to dispensed.
	public void updatePresciptionStatus(String medicineName) {
		// TODO Auto-generated method stub
		MedicineController medicineControl = new MedicineController(dataStorage.getInventory());
		Medicine medicineToAdjust = dataStorage.getInventory().getMedicineByName(medicineName);
		int quantityToDispense = 0;
		medicineControl.dispenseMedicine(medicineToAdjust, quantityToDispense);
		//Set Status to dispensed...
	}
	
	//Method to create Replenishment Request
	public void createReplenishmentRequest(int medicineChoice, int medicineQuantity) {
		// TODO Auto-generated method stub
		String medicineName = dataStorage.getInventory().getMedicineRecords().get(medicineChoice).getMedicineName();
		Replenishment replenishment = new Replenishment( medicineName, medicineQuantity);
		
		ReplenishmentController replenishControl = new ReplenishmentController(dataStorage);
		replenishControl.addReplenishment(replenishment);
	}
	
	
}
