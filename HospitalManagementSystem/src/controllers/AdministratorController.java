package controllers;

import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import datastorage.Inventory;
import datastorage.StaffRecords;
import entities.Appointment;
import entities.Replenishment;
import entities.Staff;
import entities.User;

/**
 * The AdministratorController class provides methods for managing and controlling administrative 
 * tasks in the system, such as adding/removing staff, approving replenishments, managing stock levels, 
 * and viewing appointment records.
 * 
 * It interacts with various data storage classes, including staff records, inventory, and appointment records.
 */
public class AdministratorController {
	private Staff staffUser;
	private DataStorage dataStorage;
	private StaffController staffControl;
	
    /**
     * Constructs an AdministratorController for managing administrative operations.
     * 
     * @param user The logged-in administrator user
     * @param dataStorage The main data storage for the system
     */
	public AdministratorController(User user, DataStorage dataStorage) {
		this.staffUser = (Staff) user;
		this.dataStorage = dataStorage;
		this.staffControl = new StaffController(dataStorage.getStaffRecords().getStaffList());
	}
	
    /**
     * Retrieves the user ID of the logged-in administrator.
     * 
     * @return The user ID of the logged-in administrator
     */
	public String getUserID() {
		return staffUser.getUserID();
	}
	
    /**
     * Adds a new staff member to the system.
     * 
     * @param staff The staff member to add
     */
	public void addStaff(Staff staff) {
		this.staffControl.addStaff(staff);
	}
	
    /**
     * Removes a staff member from the system by their ID.
     * 
     * @param staffID The ID of the staff member to remove
     */
	public void removeStaffByID(String staffID) {
		Staff staffToRemove = (Staff) this.dataStorage.getStaffRecords().getStaffByID(staffID);
		if(staffUser == staffToRemove) {
			System.out.println("You cannot remove yourself");
			return;
		}
		staffControl.removeStaff(staffToRemove);
	}
	
    /**
     * Retrieves all staff records from the system.
     * 
     * @return The staff records in the system
     */
	public StaffRecords getStaffRecords() {
		return this.dataStorage.getStaffRecords();
	}

    /**
     * Retrieves a specific staff member by their user ID.
     * 
     * @param userID The ID of the staff member to retrieve
     * @return The staff member with the specified ID
     */
	public Staff getStaffByID(String userID) {
		return (Staff) this.dataStorage.getStaffRecords().getStaffByID(userID);
	}
	
	public void updatedb() {
		/*StaffListWriter write=new StaffListWriter();
		write.rewrite(dataStorage.getStaffRecords().getStaffList());*/
	}

    /**
     * Retrieves all replenishment records from the system.
     * 
     * @return A list of all replenishment records
     */
	public List<Replenishment> getReplenishmentRecords() {
		// TODO Auto-generated method stub
		return this.dataStorage.getReplenishmentRecords().getReplenishmentRecords();
	}
	
    /**
     * Retrieves all pending replenishment requests from the system.
     * 
     * @return A list of all pending replenishment requests
     */
	public List<Replenishment> getPendingRequests() {
		// TODO Auto-generated method stub
		List<Replenishment> pendingRequests = new ArrayList<Replenishment>();
		for(Replenishment replenishment : dataStorage.getReplenishmentRecords().getReplenishmentRecords()) {
			if(replenishment.getStatus().equals("PENDING")) pendingRequests.add(replenishment);
		}
		return pendingRequests;
	}

    /**
     * Approves a replenishment request.
     * 
     * @param replenishment The replenishment request to approve
     */
	public void approveReplenishment(Replenishment replenishment) {
		// TODO Auto-generated method stub
		ReplenishmentController replenishControl = new ReplenishmentController(this.dataStorage);
		replenishControl.approveReplenishment(replenishment);
	}

    /**
     * Retrieves the inventory from the data storage.
     * 
     * @return The inventory of medicines in the system
     */
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return dataStorage.getInventory();
	}
	
    /**
     * Adds stock to the specified medicine.
     * 
     * @param medicineName The name of the medicine to which stock should be added
     * @param quantity The quantity of stock to add
     */
	public void addStock(String medicineName, int quantity) {
	    // Add stock to the specified medicine
		MedicineController medicineControl = new MedicineController(this.dataStorage.getInventory());
		medicineControl.addStock(medicineName, quantity);
	}

    /**
     * Updates the low stock alert threshold for the specified medicine.
     * 
     * @param medicineName The name of the medicine to update the low stock alert for
     * @param newThreshold The new threshold for low stock alerts
     */
	public void updateLowStockAlert(String medicineName, int newThreshold) {
	    // Update the low stock alert threshold for the specified medicine
		MedicineController medicineControl = new MedicineController(this.dataStorage.getInventory());
		medicineControl.adjustStockAlert(medicineName, newThreshold);
	}

    /**
     * Removes stock from the specified medicine.
     * 
     * @param medicineName The name of the medicine to which stock should be removed
     * @param quantity The quantity of stock to remove
     */
	public void removeStock(String medicineName, int quantity) {
	    // Remove stock from the specified medicine
	    if (quantity < 0) {
	        System.out.println("Quantity must be positive.");
	        return;
	    }
	    MedicineController medicineControl = new MedicineController(this.dataStorage.getInventory());
	    medicineControl.removeStock(medicineName, quantity);
	}

    /**
     * Retrieves all appointment records from the system.
     * 
     * @return A list of all appointments
     */
	public List<Appointment> getAppointmentRecords() {
		AppointmentController appointmentControl = new AppointmentController(this.dataStorage);
		return appointmentControl.getAppointmentRecords();
	}
	
	/**
     * Update the name of the staff.
     * 
     * @param staff The staff to modify
     * @param newName The new name of the staff
     */
	public void updateStaffName(Staff staff, String newName) {
		// TODO Auto-generated method stub
		staffControl.updateStaffName(staff, newName);
	}
	
	/**
     * Update the age of the staff.
     * 
     * @param staff The staff to modify
     * @param newAge The new age of the staff
     */
	public void updateStaffAge(Staff staff, int newAge) {
		// TODO Auto-generated method stub
		staffControl.updateStaffAge(staff, newAge);
	}
	
	/**
     * Update the gender of the staff.
     * 
     * @param staff The staff to modify
     * @param newGender The new gender of the staff
     */
	public void updateStaffGender(Staff staff, String newGender) {
		// TODO Auto-generated method stub
		staffControl.updateStaffGender(staff, newGender);
	}
	
	/**
     * Reset the password of the staff.
     * 
     * @param staff The staff to modify
     */
	public void resetStaffPassword(Staff staff) {
		staffControl.resetStaffPassword(staff);
	}
}
