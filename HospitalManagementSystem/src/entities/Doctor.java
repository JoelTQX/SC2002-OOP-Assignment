package entities;

/**
 * Represents a doctor in the hospital management system.
 * Inherits common attributes and methods from the `Staff` class.
 */
public class Doctor extends Staff{
	
    /**
     * Constructs a {@code Doctor} object with the specified attributes.
     *
     * @param userID      The unique ID of the doctor.
     * @param userName    The name of the doctor.
     * @param password    The doctor's password.
     * @param firstLogin  A flag indicating whether this is the doctor's first login.
     * @param userRole    The role of the user.
     * @param gender      The gender of the doctor.
     * @param age         The age of the doctor.
     */
	public Doctor(String userID, String userName, String password, boolean firstLogin, String userRole, String gender,int age) {
		super(userID, userName, password, firstLogin, userRole, gender,age);
	}
}
