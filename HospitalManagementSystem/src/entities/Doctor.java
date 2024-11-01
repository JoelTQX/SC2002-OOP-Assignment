package entities;

public class Doctor extends Staff{
	public Doctor(String userID, String userName, String password, boolean firstLogin, String userRole) {
		super(userID, userName, password, firstLogin, userRole);
	}
}
