package entities;

/**
 * Represents a pharmacist in the hospital management system.
 * Inherits from the `Staff` class and stores information specific to pharmacists.
 */
public class Pharmacist extends Staff{
	
    /**
     * Constructs a `Pharmacist` object with the specified attributes.
     *
     * @param userID     The unique ID of the pharmacist.
     * @param userName   The name of the pharmacist.
     * @param userPass   The password for the pharmacist's account.
     * @param firstLogin Indicates if it is the pharmacist's first login.
     * @param userRole   The role of the pharmacist.
     * @param gender     The gender of the pharmacist.
     * @param age        The age of the pharmacist.
     */
	public Pharmacist(String userID,String userName, String userPass, boolean firstLogin, String userRole, String gender, int age) {
		super(userID, userName,userPass, firstLogin, userRole,gender,age);
	}

}
