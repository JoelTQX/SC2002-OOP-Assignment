package entities;

/**
 * Represents an administrator in the hospital management system.
 * An administrator is a specialized type of `Staff` who performs administrative tasks 
 * such as managing staff, inventory, appointments, and other hospital operations.
 */

public class Administrator extends Staff{

    /**
     * Constructs a new Administrator object with the specified attributes.
     *
     * @param userID     The unique identifier for the administrator.
     * @param userName   The name of the administrator.
     * @param userPass   The hashed password of the administrator.
     * @param firstLogin Indicates whether the administrator is logging in for the first time.
     * @param userRole   The role of the administrator.
     * @param gender     The gender of the administrator.
     * @param age        The age of the administrator.
     */
	
	public Administrator(String userID, String userName,String userPass, boolean firstLogin, String userRole, String gender, int age) {
		super(userID,userName, userPass, firstLogin, userRole,gender,age);
	}
}

