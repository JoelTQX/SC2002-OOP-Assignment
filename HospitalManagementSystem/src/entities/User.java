package entities;

public abstract class User {
	private String userID;
	private String userPass;
	private boolean firstLogin;
	private String userName;
	private String gender;
	
	public User(String userID, String userName,String userPass, boolean firstLogin,String gender) {
		this.userID = userID;
		this.userName=userName;
		this.userPass = userPass;
		this.firstLogin = firstLogin;
		this.gender=gender;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getUserGender() {
		return this.gender;
	}
	
	public String getPassword() {
		return this.userPass;
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
