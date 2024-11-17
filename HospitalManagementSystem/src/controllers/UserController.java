package controllers;

import java.security.NoSuchAlgorithmException;

import datastorage.Authenticator;
import datastorage.DataStorage;
import entities.User;
import datastorage.Password;

/**
 * The UserController class manages user authentication and user session management in the system. 
 * It provides methods for logging in, changing passwords, checking login status, and logging out.
 */
public class UserController {
	private DataStorage dataStorage;
	private boolean isLoggedIn;
	private User user;
	
    /**
     * Constructs a UserController instance.
     * 
     * @param dataStorage The DataStorage object responsible for storing user data.
     */
	public UserController(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
		this.isLoggedIn = false;
		this.user = null;
	}
	
    /**
     * Authenticates the user with the given user ID and password. 
     * If successful, the user is logged in.
     * 
     * @param userID The user ID of the user trying to log in.
     * @param userPass The password of the user.
     * @return true if the login is successful, false otherwise.
     */
	public boolean userLogin(String userID, String userPass) {
		Authenticator auth = new Authenticator(dataStorage);
		this.user = auth.authenticate(userID, userPass);
		if(this.user != null) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}
	
    /**
     * Changes the password of the logged-in user.
     * 
     * @param newUserPass The new password to set for the user.
     */
	public void changePassword(String newUserPass) {
		try {
			user.changePassword(Password.hashPassword(newUserPass));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Password has been updated.");
	}
	
    /**
     * Returns the login status of the user.
     * 
     * @return true if the user is logged in, false otherwise.
     */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
    /**
     * Logs out the user, saving all records and clearing the session data.
     */
	public void logOut() {
		dataStorage.saveRecords();
		this.isLoggedIn = false;
		this.user = null;
	}
	
    /**
     * Checks if the logged-in user is logging in for the first time.
     * 
     * @return true if it's the user's first login, false otherwise.
     */
	public boolean isFirstLogin() {
		return this.user.isFirstLogin();
	}

    /**
     * Retrieves the logged-in user.
     * 
     * @return The logged-in user.
     */
	public User getLoggedUser() {
		return this.user;
	}
}
