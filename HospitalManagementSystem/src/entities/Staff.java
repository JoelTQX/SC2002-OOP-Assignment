package entities;

/**
 * Represents an abstract staff member in the hospital management system.
 * This class extends the `User` class and provides additional attributes and methods
 * specific to staff members.
 */
public abstract class Staff extends User{
	
    /**
     * The role of the staff member (e.g., Doctor, Pharmacist).
     */
	protected String role;
	
    /**
     * The age of the staff member.
     */
	protected int age;
	
    /**
     * Constructs a `Staff` object with the specified details.
     *
     * @param userID     The unique identifier for the staff member.
     * @param userName   The name of the staff member.
     * @param userPass   The password for the staff member.
     * @param firstLogin A boolean indicating if this is the staff member's first login.
     * @param userRole   The role of the staff member.
     * @param gender     The gender of the staff member.
     * @param age        The age of the staff member.
     */
	public Staff(String userID,String userName, String userPass, boolean firstLogin, String userRole,String gender,int age) {
		super(userID,userName, userPass, firstLogin,gender);
		this.role = userRole;
		this.age=age;
	}
	
    /**
     * Gets the role of the staff member.
     *
     * @return The role of the staff member as a string.
     */
	public String getRole() {
		return this.role;
		
	}
	
    /**
     * Gets the age of the staff member.
     *
     * @return The age of the staff member.
     */
	public int getAge() {
		return this.age;
		
	}

    /**
     * Adds a staff member to the system.
     * This method should be overridden by subclasses if required.
     *
     * @param staffUser The staff member to be added.
     */
	public void add(Staff staffUser) {
		// TODO Auto-generated method stub
		
	}

    /**
     * Removes a staff member from the system by index.
     * This method should be overridden by subclasses if required.
     *
     * @param indexOfStaff The index of the staff member to be removed.
     */
	public void remove(int indexOfStaff) {
		// TODO Auto-generated method stub
		
	}
}
