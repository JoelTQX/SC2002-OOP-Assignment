package controllers;

import entities.Staff;
import entities.User;


public class AdministratorController implements ControllerInterface{
	private Staff staffUser;
	
	public AdministratorController(User user) {
		this.staffUser = (Staff) user;
	}
	
	public String getUserID() {
		return staffUser.getUserID();
	}
	
	public void addStaff(User user) {
		this.staffUser.add(staffUser);
	}
	
	public void updateStaff(User user, User updatedUser) {
		
	}
	
	public void removeStaff(User user) {
		if(user == null) {
			System.out.println("Staff cannot be null");
			return;
		}
		
		int indexOfStaff = this.staffUser.indexOf(user);
		if (indexOfStaff != -1) {
	        this.staffUser.remove(indexOfStaff);
	    } else {
	        // Optionally handle the case where the staff is not found
	        System.out.println("Staff not found in records.");
	    }
	}

}
