package controllers;
import java.util.List;

import entities.Staff;

/**
 * The StaffController class manages staff records, allowing for staff additions, updates, and removals.
 * It provides methods to add, update, and remove staff members from a list of staff records.
 */
public class StaffController {
	private List<Staff> staffRecords;
	
    /**
     * Constructs a StaffController instance with the given list of staff records.
     * 
     * @param staffRecords The list of staff records to manage.
     */
	public StaffController(List<Staff> staffRecords) {
		this.staffRecords = staffRecords;
	}
	
    /**
     * Adds a new staff member to the list of staff records.
     * 
     * @param staff The staff member to add.
     */
	public void addStaff(Staff staff) {
		this.staffRecords.add(staff);
	}
	
	
    /**
     * Removes a staff member from the list of staff records.
     * If the staff member is not found in the records, an appropriate message is displayed.
     * 
     * @param staff The staff member to remove.
     */
	public void removeStaff(Staff staff) {
		if(staff == null) {
			System.out.println("Staff cannot be null");
			return;
		}
		int indexOfStaff = this.staffRecords.indexOf(staff);
		if (indexOfStaff != -1) {
	        this.staffRecords.remove(indexOfStaff);
	    } else {
	        // Optionally handle the case where the staff is not found
	        System.out.println("Staff not found in records.");
	    }
	}
	
	/**
     * Update the name of the staff.
     * 
     * @param staff The staff to modify
     * @param newName The new name of the staff
     */
	public void updateStaffName(Staff staff, String newName) {
		// TODO Auto-generated method stub
		staff.setName(newName);
	}
	
	/**
     * Update the age of the staff.
     * 
     * @param staff The staff to modify
     * @param newAge The new age of the staff
     */
	public void updateStaffAge(Staff staff, int newAge) {
		staff.setAge(newAge);
	}
	
	/**
     * Update the age of the staff.
     * 
     * @param staff The staff to modify
     * @param newAge The new age of the staff
     */
	public void updateStaffGender(Staff staff, String newGender) {
		staff.setGender(newGender);
	}
	
	/**
     * Reset the password of the staff.
     * 
     * @param staff The staff to modify
     */
	public void resetStaffPassword(Staff staff) {
		staff.resetPassword();
	}
}
