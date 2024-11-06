package controllers;

import datastorage.DataStorage;
import datastorage.StaffRecords;
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

}
