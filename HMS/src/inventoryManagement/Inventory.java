package inventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Medicine> inventory;
	private MedicineController medicineControl;
	
	//Constructor
	public Inventory() {
		this.inventory = new ArrayList<Medicine>();
		this.medicineControl = new MedicineController();
	}
	
	//To add Medicine to Inventory
	public void addMedicine(Medicine medicine) {
		this.inventory.add(medicine);
	}
	
	//To remove Medicine from Inventory
	public void removeMedicine(Medicine medicine) {
		this.inventory.remove(medicine);
	}
	
	//Return inventory
	public List<Medicine> getMedicines(){
		return inventory;
	}
	
	//Get Medicine by Medicine Name
	public Medicine getMedicineByName(String medicineName) {
		for(int i = 0; i < this.inventory.size(); i++) {
			Medicine medicine = this.inventory.get(i);
			if(medicine.getMedicineName().equals(medicineName)) {
				return medicine;
			}
		}
		return null; //If Medicine does not exist in Inventory
	}
	
	//Adjust Medicine Values 
	public void adjustName(String medicineName, String newMedicineName) {
		Medicine medicine = this.getMedicineByName(medicineName);
		if(medicine == null) System.out.println("Medicine not found");
		else {
			try{
				medicineControl.adjustName(medicine, newMedicineName);
			}catch(Exception e) {
				System.out.println("Error Adjusting Medicine Name: " + e.getMessage());
			}
		}
	}

	public void adjustStock(String medicineName, int newAmount) {
		Medicine medicine = this.getMedicineByName(medicineName);
		if(medicine == null) System.out.println("Medicine not found");
		else {
			try{
				medicineControl.adjustStock(medicine, newAmount);
			}catch(Exception e) {
				System.out.println("Error Adjusting Stock: " + e.getMessage());
			}
		}
	}
	
	public void adjustStockAlert(String medicineName, int newStockAlert) {
		Medicine medicine = this.getMedicineByName(medicineName);
		if(medicine == null) System.out.println("Medicine not found");
		else {
			try{
				medicineControl.adjustStockAlert(medicine, newStockAlert);
			}catch(Exception e) {
				System.out.println("Error Adjusting Stock Alert: " + e.getMessage());
			}
		}
	}
}
