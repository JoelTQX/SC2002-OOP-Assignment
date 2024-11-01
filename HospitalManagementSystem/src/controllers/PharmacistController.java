package controllers;

import datastorage.Inventory;
import entities.Pharmacist;
import entities.User;

public class PharmacistController {
	
	private User userControl;
	private MedicineController medicineControl;
	
	public PharmacistController(User user) {
		this.userControl = user;
		this.medicineControl = new MedicineController();
	}
	/*public Inventory getInventory() {
		return this.userControl.getInventory();
	}*/
	
	
}
