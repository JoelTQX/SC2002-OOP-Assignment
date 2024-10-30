package controllers;
import java.util.List;

import entities.Staff;

public class StaffController {
	private List<Staff> staffRecords;
	
	public StaffController(List<Staff> staffRecords) {
		this.staffRecords = staffRecords;
	}
	
	public void addStaff(Staff staff) {
		this.staffRecords.add(staff);
	}
	
	public void updateStaff(Staff staff, Staff updatedStaff) {
		
	}
	
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
