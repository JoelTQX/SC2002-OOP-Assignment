package controllers;

import datastorage.Inventory;
import entities.Pharmacist;
import entities.User;

public class PharmacistController {
	
	private UserController userControl;
	private MedicineController medicineControl;
	
	public PharmacistController(UserController userControl) {
		this.userControl = userControl;
		this.medicineControl = new MedicineController();
	}
	/*public Inventory getInventory() {
		return this.userControl.getInventory();
	}*/
	
	
}
