package entities;

public class Pharmacist extends Staff{
	
	public Pharmacist(String userID,String userName, String userPass, boolean firstLogin, String userRole, String gender, int age) {
		super(userID, userName,userPass, firstLogin, userRole,gender,age);
	}

}
