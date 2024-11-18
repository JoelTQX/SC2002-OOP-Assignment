package entities;

import datastorage.Password;

/**
 * Represents a generic user in the hospital management system.
 * This abstract class serves as the base class for all user types.
 */
public abstract class User {
	
    /**
     * The unique identifier for the user.
     */
	private String userID;
	
    /**
     * The user's password.
     */
	private String userPass;
	
    /**
     * Indicates whether this is the user's first login.
     */
	private boolean firstLogin;
	
    /**
     * The user's name.
     */
	private String userName;
	
    /**
     * The user's gender.
     */
	private String gender;
	
	
    /**
     * Constructs a `User` object with the specified details.
     *
     * @param userID     The unique identifier for the user.
     * @param userName   The name of the user.
     * @param userPass   The password for the user.
     * @param firstLogin A boolean indicating if this is the user's first login.
     * @param gender     The gender of the user.
     */
	public User(String userID, String userName,String userPass, boolean firstLogin,String gender) {
		this.userID = userID;
		this.userName=userName;
		this.userPass = userPass;
		this.firstLogin = firstLogin;
		this.gender=gender;
	}
	
    /**
     * Gets the unique identifier of the user.
     *
     * @return The user ID.
     */
	public String getUserID() {
		return this.userID;
	}
	
    /**
     * Gets the name of the user.
     *
     * @return The user name.
     */
	public String getUserName() {
		return this.userName;
	}
	
    /**
     * Gets the gender of the user.
     *
     * @return The user's gender.
     */
	public String getUserGender() {
		return this.gender;
	}
	
    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
	public String getPassword() {
		return this.userPass;
	}
	
    /**
     * Validates the user's password.
     *
     * @param userPass The password to validate.
     * @return `true` if the provided password matches the user's password;
     *         `false` otherwise.
     */
	public boolean validatePassword(String userPass) {
		if(this.userPass.equals(userPass)) return true;
		return false;
	}
	
    /**
     * Checks if this is the user's first login.
     *
     * @return `true` if this is the user's first login; `false` otherwise.
     */
	public boolean isFirstLogin() {
		return this.firstLogin;
	}
	
    /**
     * Changes the user's password and marks the first login as complete.
     *
     * @param newUserPass The new password to set.
     */
	public void changePassword(String newUserPass) {
		this.firstLogin = false;
		this.userPass = newUserPass;
	}
	
	/**
     * Changes the user's name.
     *
     * @param newName The new name to set.
     */
	public void setName(String newName) {
		// TODO Auto-generated method stub
		this.userName = newName;
	}
	
	/**
     * Changes the user's gender.
     *
     * @param newGender The new gender to set.
     */
	public void setGender(String newGender) {
		// TODO Auto-generated method stub
		this.gender = newGender;
	}
	
    /**
     * Resets the user's password to the default value and marks the user for first login.
     */
	public void resetPassword() {
		this.firstLogin = true;
		try {
			this.userPass = Password.hashPassword("password");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error resetting password");
		}
		
	}
}
