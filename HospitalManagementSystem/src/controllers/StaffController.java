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
     * Updates the information of an existing staff member in the list. 
     * This method currently does not perform any updates, and needs to be implemented.
     * 
     * @param staff The staff member to be updated.
     * @param updatedStaff The new staff information to replace the existing staff information.
     */
	public void updateStaff(Staff staff, Staff updatedStaff) {
		
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
}
