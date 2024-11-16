package controllers;

import datastorage.DataStorage;
import datastorage.ReplenishmentRecords;
import entities.Replenishment;

public class ReplenishmentController {
	private ReplenishmentRecords replenishmentRecords;
	private MedicineController medicineControl;
	public ReplenishmentController(DataStorage dataStorage) {
		this.replenishmentRecords = dataStorage.getReplenishmentRecords();
		this.medicineControl = new MedicineController(dataStorage.getInventory());
	}
	
	public void addReplenishment(Replenishment replenishment) {
		if(replenishment == null) {
			System.out.println("Error adding replenishment request...");
		}
		this.replenishmentRecords.getReplenishmentRecords().add(replenishment);
	}

	public void approveReplenishment(Replenishment replenishment) {
		// TODO Auto-generated method stub
		medicineControl.replenishStock(replenishment.getMedicineName(), replenishment.getQuantity());
		replenishment.setStatus("Approved");
	}
}
