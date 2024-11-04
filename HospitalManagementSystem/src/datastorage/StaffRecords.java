package datastorage;

import java.util.ArrayList;
import java.util.List;

import controllers.StaffController;
import entities.Doctor;
import entities.Patient;
import entities.Staff;
import entities.User;

public class StaffRecords {
	private List<Staff> staffRecords;
	
	public StaffRecords() {
		this.staffRecords = new ArrayList<Staff>();
	}
	
	public User getStaffByID(String userID) {
		for(Staff staff : staffRecords) {
			if(staff.getUserID().equals(userID)) return staff;
		}
		return null;
	}
	
	public void viewStaff() {
		for(Staff staff : staffRecords) {
			System.out.println("UserID: " + staff.getUserID() + " Name: " + staff.getUserName() + " Role: "+staff.getRole()  +" Gender: "+ staff.getUserGender()+" Age: "+ staff.getAge());
		}
	}
	public void addStaff(Staff staff) {
		this.staffRecords.add(staff);
	}
	
}
