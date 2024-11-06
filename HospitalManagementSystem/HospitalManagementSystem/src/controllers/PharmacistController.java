package controllers;

import datastorage.DataStorage;
import datastorage.Inventory;
import entities.Pharmacist;
import entities.User;

public class PharmacistController {
	
	private User userControl;
	private MedicineController medicineControl;
	private DataStorage dataStorage;
	
	public PharmacistController(User user, DataStorage dataStorage) {
		this.userControl = user;
		this.dataStorage = dataStorage;
		this.medicineControl = new MedicineController();
	}
	public Inventory getInventory() {
		return this.dataStorage.getInventory();
	}
	public void getOutcomeRecords() {
		
		
	}
	
	
}
