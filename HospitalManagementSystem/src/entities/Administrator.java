package entities;

public class Administrator extends Staff{

	public Administrator(String userID, String userName,String userPass, boolean firstLogin, String userRole, String gender) {
		super(userID,userName, userPass, firstLogin, userRole,gender);
	}

}
