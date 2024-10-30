package controllers;

import datastorage.Authenticator;
import datastorage.DataStorage;
import entities.User;
import datastorage.Password;

public class UserController {
	private DataStorage dataStorage;
	
	public UserController(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}
	
	public User userLogin(String userID, String userPass) {
		Authenticator auth = new Authenticator(dataStorage);
		return auth.authenticate(userID, userPass);
	}
	
	public void changePassword(User user, String newUserPass) {
		user.changePassword(newUserPass);
		System.out.println("Password has been updated.");
	}
}
