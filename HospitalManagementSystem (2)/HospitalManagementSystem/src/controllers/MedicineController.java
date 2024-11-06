package controllers;

import entities.Medicine;

public class MedicineController {
	
	//Adjust the name of the medicine
	public void adjustName(Medicine medicine, String newName) {
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
    public void adjustStock(Medicine medicine, int newAmount) {
    	if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
        medicine.setMedicineStock(newAmount);
        System.out.println(medicine.getMedicineName() + " stock adjusted to " + newAmount);
    }

    // Adjust the stock alert level of the medicine
    public void adjustStockAlert(Medicine medicine, int newStockAlert) {
    	if(medicine == null) {
			throw new IllegalArgumentException("Medicine cannot be NULL");
		}
        medicine.setMedicineStockAlert(newStockAlert);
        System.out.println(medicine.getMedicineName() + " stock alert adjusted to " + newStockAlert);
    }
}
