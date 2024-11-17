package controllers;

import datastorage.Inventory;
import entities.Medicine;

/**
 * The MedicineController class is responsible for managing the operations related to medicines in the inventory. 
 * It provides methods for adjusting medicine names, stock, stock alerts, replenishing stock, dispensing medicine, 
 * and checking stock levels.
 */
public class MedicineController {
	
	private Inventory inventory;
	
    /**
     * Constructs a MedicineController for managing medicine-related operations.
     * 
     * @param inventory The inventory that contains the list of medicines.
     */
	public MedicineController(Inventory inventory) {
		// TODO Auto-generated constructor stub
		this.inventory = inventory;
	}

    /**
     * Adjusts the name of a specific medicine in the inventory.
     * 
     * @param medicineName The current name of the medicine.
     * @param newName The new name to assign to the medicine.
     */
	public void adjustName(String medicineName, String newName) {
		Medicine medicine = inventory.getMedicineByName(medicineName);
		if(medicine == null) {
			System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
		else if(newName == null || newName.isBlank()) {
			System.out.println("New Medicine Name cannot be empty...");
    		return;
		}
		
		String oldName = medicine.getMedicineName();
        medicine.setMedicineName(newName);
        System.out.println(oldName + " name adjusted to " + newName);
    }

    /**
     * Adjusts the stock quantity of a specific medicine in the inventory.
     * 
     * @param medicineName The name of the medicine whose stock is to be adjusted.
     * @param newAmount The new stock amount to set for the medicine.
     */
    public void adjustStock(String medicineName, int newAmount) {
    	Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
    		System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
        medicine.setMedicineStock(newAmount);
        System.out.println(medicine.getMedicineName() + " stock adjusted to " + newAmount);
    }

    /**
     * Adjusts the stock alert level for a specific medicine.
     * 
     * @param medicineName The name of the medicine whose stock alert level is to be adjusted.
     * @param newStockAlert The new stock alert level for the medicine.
     */
    public void adjustStockAlert(String medicineName, int newStockAlert) {
    	Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
    		System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
        medicine.setMedicineStockAlert(newStockAlert);
        System.out.println(medicine.getMedicineName() + " stock alert adjusted to " + newStockAlert);
    }
    
    /**
     * Replenishes the stock of a specific medicine by a given quantity.
     * 
     * @param medicineName The name of the medicine to replenish.
     * @param quantity The quantity to add to the medicine's stock.
     */
	public void replenishStock(String medicineName, int quantity) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
    		System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
        medicine.setMedicineStock((medicine.getMedicineStock()+quantity));
        System.out.println(medicine.getMedicineName() + " stock replenished to " + medicine.getMedicineStock());
    }
	
    /**
     * Dispenses a specified quantity of medicine to a patient, reducing the available stock.
     * 
     * @param medicineName The name of the medicine to dispense.
     * @param quantityToDispense The quantity of the medicine to dispense.
     */
	public void dispenseMedicine(String medicineName, int quantityToDispense) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
    		System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
    	// Check for sufficient Medicine to dispense
    	if(quantityToDispense > medicine.getMedicineStock()) {
    		System.out.println("Medicine has insufficient stock... Please replenish first...");
    		return;
    	}
        medicine.setMedicineStock((medicine.getMedicineStock()-quantityToDispense));
        System.out.println(medicine.getMedicineName() + " remaining stock :  " + medicine.getMedicineStock());
	}
	
    /**
     * Adds a specified quantity of medicine to the inventory.
     * 
     * @param medicineName The name of the medicine to add stock to.
     * @param quantity The quantity of the medicine to add.
     */
	public void addStock(String medicineName, int quantity) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
    		System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
    	int updatedAmount = medicine.getMedicineStock() + quantity;
        medicine.setMedicineStock(updatedAmount);
        System.out.println(medicine.getMedicineName() + " stock adjusted to " + updatedAmount);
	}
	
    /**
     * Removes a specified quantity of medicine from the inventory.
     * 
     * @param medicineName The name of the medicine to remove stock from.
     * @param quantity The quantity of the medicine to remove.
     */
	public void removeStock(String medicineName, int quantity) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
    	if(medicine == null) {
    		System.out.println("Medicine Not Found... Please Check The Name");
    		return;
		}
    	if(medicine.getMedicineStock() < quantity) {
    		System.out.println("Error: Insufficient stock...");
    		return;
    	}
		int updatedAmount = medicine.getMedicineStock() - quantity;
		medicine.setMedicineStock(updatedAmount);
		System.out.println(medicine.getMedicineName() + " stock adjusted to " + updatedAmount);
	}

    /**
     * Checks if there is sufficient stock of a specific medicine to dispense a given quantity.
     * 
     * @param medicineName The name of the medicine to check stock for.
     * @param medicineQuantity The quantity of the medicine to check.
     * @return True if there is sufficient stock, false otherwise.
     */
	public boolean isSufficient(String medicineName, int medicineQuantity) {
		// TODO Auto-generated method stub
		Medicine medicine = inventory.getMedicineByName(medicineName);
		if(medicine == null) {
    		System.out.println("Error: Medicine Not Found... Please Check The Name");
    		return false;
		}
		if(medicine.getMedicineStock() < medicineQuantity) {
    		System.out.println("Error: Insufficient stock for " + medicineName + "...");
    		return false;
    	}
		return true;
	}
}
