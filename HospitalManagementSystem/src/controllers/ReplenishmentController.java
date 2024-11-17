package controllers;

import datastorage.DataStorage;
import datastorage.ReplenishmentRecords;
import entities.Replenishment;

/**
 * The ReplenishmentController class manages replenishment requests for the inventory.
 * It allows for adding new replenishment requests and approving them, which triggers 
 * the replenishment of stock via the MedicineController.
 */
public class ReplenishmentController {
	private ReplenishmentRecords replenishmentRecords;
	private MedicineController medicineControl;
	
    /**
     * Constructs a ReplenishmentController instance that manages replenishment requests.
     * 
     * @param dataStorage The DataStorage instance that contains the replenishment records
     *                    and inventory data.
     */
	public ReplenishmentController(DataStorage dataStorage) {
		this.replenishmentRecords = dataStorage.getReplenishmentRecords();
		this.medicineControl = new MedicineController(dataStorage.getInventory());
	}
	
    /**
     * Adds a replenishment request to the list of replenishment records.
     * 
     * @param replenishment The replenishment request to add.
     * @throws IllegalArgumentException if the replenishment request is null.
     */
	public void addReplenishment(Replenishment replenishment) {
		if(replenishment == null) {
			System.out.println("Error adding replenishment request...");
		}
		this.replenishmentRecords.getReplenishmentRecords().add(replenishment);
	}

    /**
     * Approves a replenishment request and updates the medicine stock accordingly.
     * The replenishment status is updated to "Approved".
     * 
     * @param replenishment The replenishment request to approve.
     */
	public void approveReplenishment(Replenishment replenishment) {
		// Trigger stock replenishment in the inventory
		medicineControl.replenishStock(replenishment.getMedicineName(), replenishment.getQuantity());
		
		// Update the status of the replenishment request
		replenishment.setStatus("Approved");
	}
}
