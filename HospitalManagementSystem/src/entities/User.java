package entities;

public abstract class User {
	private String userID;
	private String userPass;
	private boolean firstLogin;
	
	public User(String userID, String userPass, boolean firstLogin) {
		this.userID = userID;
		this.userPass = userPass;
		this.firstLogin = firstLogin;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public boolean validatePassword(String userPass) {
		if(this.userPass.equals(userPass)) return true;
		return false;
	}
	
	public boolean isFirstLogin() {
		return this.firstLogin;
	}
	
	public void changePassword(String newUserPass) {
		this.firstLogin = false;
		this.userPass = newUserPass;
	}
	
	public void resetPassword() {
		this.firstLogin = true;
		this.userPass = "password";
	}
}
