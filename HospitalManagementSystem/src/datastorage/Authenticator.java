package datastorage;
import java.security.NoSuchAlgorithmException;

import datastorage.Password;
import entities.User;

public class Authenticator {
	private DataStorage dataStorage;
	
	public Authenticator(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}
	
	public User authenticate(String userID, String userPass){
		User user = dataStorage.getPatientRecords().getPatientByID(userID);
		if(user == null) user = dataStorage.getStaffRecords().getStaffByID(userID);
		if(user != null) { 
			try {
				if(user.validatePassword(Password.hashPassword(userPass))) return user;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
