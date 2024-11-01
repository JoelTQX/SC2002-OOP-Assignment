package entities;

import datastorage.Inventory;

public class Pharmacist extends Staff{
	
	public Pharmacist(String userID,String userName, String userPass, boolean firstLogin, String userRole, String gender) {
		super(userID, userName,userPass, firstLogin, userRole,gender);
	}

}
