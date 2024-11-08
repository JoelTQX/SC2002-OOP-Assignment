package controllers;

import datastorage.Inventory;
import entities.Medicine;

public class MedicineController {
	
	private Inventory inventory;
	
	public MedicineController(Inventory inventory) {
		// TODO Auto-generated constructor stub
		this.inventory = inventory;
	}

	//Adjust the name of the medicine
	public void adjustName(String medicineName, String newName) {
		Medicine medicine = inventory.getMedicineByName(medicineName);
		if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
		else if(newName == null || newName.isBlank()) {
			throw new IllegalArgumentException("New Name cannot be NULL or BLANK");
		}
		
		String oldName = medicine.getMedicineName();
        medicine.setMedicineName(newName);
        System.out.println(oldName + " name adjusted to " + newName);
    }

    // Adjust the stock of the medicine
    public void adjustStock(String medicineName, int newAmount) {
    	Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
        medicine.setMedicineStock(newAmount);
        System.out.println(medicine.getMedicineName() + " stock adjusted to " + newAmount);
    }

    // Adjust the stock alert level of the medicine
    public void adjustStockAlert(String medicineName, int newStockAlert) {
    	Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
        medicine.setMedicineStockAlert(newStockAlert);
        System.out.println(medicine.getMedicineName() + " stock alert adjusted to " + newStockAlert);
    }
    
    // Replenish Medicine Stock
	public void replenishStock(String medicineName, int quantity) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
        medicine.setMedicineStock((medicine.getMedicineStock()+quantity));
        System.out.println(medicine.getMedicineName() + " stock replenished to " + medicine.getMedicineStock());
    }
	
	// Dispense Medicine to Patient
	public void dispenseMedicine(String medicineName, int quantityToDispense) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
    	// Check for sufficient Medicine to dispense
    	if(quantityToDispense > medicine.getMedicineStock()) {
    		System.out.println("Medicine has insufficient stock... Please replenish first...");
    		return;
    	}
        medicine.setMedicineStock((medicine.getMedicineStock()-quantityToDispense));
        System.out.println(medicine.getMedicineName() + " remaining stock :  " + medicine.getMedicineStock());
	}
}
