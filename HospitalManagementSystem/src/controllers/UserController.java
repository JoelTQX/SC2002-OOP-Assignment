package controllers;

import java.security.NoSuchAlgorithmException;

import datastorage.Authenticator;
import datastorage.DataStorage;
import entities.User;
import datastorage.Password;

public class UserController {
	private DataStorage dataStorage;
	private boolean isLoggedIn;
	private User user;
	
	public UserController(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
		this.isLoggedIn = false;
		this.user = null;
	}
	
	public boolean userLogin(String userID, String userPass) {
		Authenticator auth = new Authenticator(dataStorage);
		this.user = auth.authenticate(userID, userPass);
		if(this.user != null) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}
	
	public void changePassword(String newUserPass) {
		try {
			user.changePassword(Password.hashPassword(newUserPass));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Password has been updated.");
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void logOut() {
		this.isLoggedIn = false;
		this.user = null;
	}
	
	public boolean isFirstLogin() {
		return this.user.isFirstLogin();
	}

	public User getLoggedUser() {
		return this.user;
	}
}
