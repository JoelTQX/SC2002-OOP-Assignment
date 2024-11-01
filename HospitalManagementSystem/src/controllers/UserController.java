package controllers;

import java.security.NoSuchAlgorithmException;

import datastorage.Authenticator;
import datastorage.DataStorage;
import entities.User;
import datastorage.Inventory;
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
		try {
			user.changePassword(Password.hashPassword(newUserPass));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Password has been updated.");
	}
	
	public Inventory getInventory() {
		return dataStorage.getInventory();
	}
}
