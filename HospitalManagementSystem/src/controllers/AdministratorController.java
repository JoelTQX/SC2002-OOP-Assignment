package controllers;

import java.util.ArrayList;
import java.util.List;

import datareadwrite.StaffListWriter;
import datastorage.DataStorage;
import datastorage.Inventory;
import datastorage.StaffRecords;
import entities.Replenishment;
import entities.Staff;
import entities.User;


public class AdministratorController implements ControllerInterface{
	private Staff staffUser;
	private DataStorage dataStorage;
	private StaffController staffControl;
	
	public AdministratorController(User user, DataStorage dataStorage) {
		this.staffUser = (Staff) user;
		this.dataStorage = dataStorage;
		this.staffControl = new StaffController(dataStorage.getStaffRecords().getStaffList());
	}
	
	public String getUserID() {
		return staffUser.getUserID();
	}
	
	public void addStaff(Staff staff) {
		this.staffControl.addStaff(staff);
	}
	
	public void updateStaff(Staff staff, Staff updatedStaff) {
		this.staffControl.updateStaff(staff, updatedStaff);
	}
	
	public void removeStaffByID(String staffID) {
		Staff staffToRemove = (Staff) this.dataStorage.getStaffRecords().getStaffByID(staffID);
		if(staffUser == staffToRemove) {
			System.out.println("You cannot remove yourself");
			return;
		}
		staffControl.removeStaff(staffToRemove);
	}
	
	public StaffRecords getStaffRecords() {
		return this.dataStorage.getStaffRecords();
	}

	public Staff getStaffByID(String userID) {
		return (Staff) this.dataStorage.getStaffRecords().getStaffByID(userID);
	}
	
	public void updatedb() {
		/*StaffListWriter write=new StaffListWriter();
		write.rewrite(dataStorage.getStaffRecords().getStaffList());*/
	}

	public List<Replenishment> getReplenishmentRecords() {
		// TODO Auto-generated method stub
		return this.dataStorage.getReplenishmentRecords().getReplenishmentRecords();
	}
	
	//Filter Replenishment Requests and Return All Pendings
	public List<Replenishment> getPendingRequests() {
		// TODO Auto-generated method stub
		List<Replenishment> pendingRequests = new ArrayList<Replenishment>();
		for(Replenishment replenishment : dataStorage.getReplenishmentRecords().getReplenishmentRecords()) {
			if(replenishment.getStatus() == 0) pendingRequests.add(replenishment);
		}
		return pendingRequests;
	}

	public void approveReplenishment(Replenishment replenishment) {
		// TODO Auto-generated method stub
		ReplenishmentController replenishControl = new ReplenishmentController(this.dataStorage);
		replenishControl.approveReplenishment(replenishment);
	}

	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return dataStorage.getInventory();
	}
	
	public void addStock(String medicineName, int quantity) {
	    // Add stock to the specified medicine
	    this.adminControl.getInventory().addStock(medicineName, quantity);
	}

	public void updateLowStockAlert(String medicineName, int newThreshold) {
	    // Update the low stock alert threshold for the specified medicine
	    this.adminControl.getInventory().updateLowStockAlert(medicineName, newThreshold);
	}

	public void removeStock(String medicineName, int quantity) {
	    // Remove stock from the specified medicine
	    if (quantity < 0) {
	        System.out.println("Quantity must be positive.");
	        return;
	    }
	    boolean success = this.adminControl.getInventory().removeStock(medicineName, quantity);
	    if (!success) {
	        System.out.println("Error: Insufficient stock or invalid medicine name.");
	    }
	}

}
