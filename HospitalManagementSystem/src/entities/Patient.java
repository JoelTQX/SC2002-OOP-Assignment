package entities;

public class Patient extends User{
	public Patient(String userID, String userName,String userPass, boolean firstLogin) {
		super(userID,userName, userPass, firstLogin);
	}
}
