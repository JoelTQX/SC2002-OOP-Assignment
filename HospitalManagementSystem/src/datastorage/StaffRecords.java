package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Staff;
import entities.User;

/**
 * Manages the collection of staff records within the system.
 * Provides methods to add, view, and retrieve staff members based on user ID.
 */
public class StaffRecords {
    /**
     * A list that stores the staff records.
     */
	private List<Staff> staffRecords;
	
    /**
     * Constructs a `StaffRecords` instance, initializing an empty list of staff records.
     */
	public StaffRecords() {
		this.staffRecords = new ArrayList<Staff>();
	}
	
    /**
     * Retrieves a staff member by their user ID.
     * 
     * @param userID The ID of the staff member to retrieve.
     * @return The `Staff` member associated with the given ID, or `null` if not found.
     */
	public User getStaffByID(String userID) {
		for(Staff staff : staffRecords) {
			if(staff.getUserID().equals(userID)) return staff;
		}
		return null;
	}
	
    /**
     * Displays the information of all staff members in the records.
     */
	public void viewStaff() {
		for(Staff staff : staffRecords) {
			System.out.println("UserID: " + staff.getUserID() + " Name: " + staff.getUserName() + " Role: "+staff.getRole()  +" Gender: "+ staff.getUserGender()+" Age: "+ staff.getAge());
		}
	}
	
    /**
     * Adds a staff member to the records.
     * 
     * @param staff The `Staff` member to add to the list of records.
     */
	public void addStaff(Staff staff) {
		this.staffRecords.add(staff);
	}

    /**
     * Retrieves the list of all staff members in the records.
     * 
     * @return A list of all `Staff` members.
     */
	public List<Staff> getStaffList() {
		return this.staffRecords;
	}
}
