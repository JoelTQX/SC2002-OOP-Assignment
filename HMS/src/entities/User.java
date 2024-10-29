package entities;

public abstract class User {
	public String userID;
	public String Password;
	public String name;
	private String gender;
	
	
	public User(String userID, String password,String name, String gender) {
		this.userID = userID;
		this.Password = password;
		this.name = name;
		this.gender = gender;
	}
	/*public abstract void login();
	public abstract void logout();
	public void changepassword(String newPassword){
		this.Password=newPassword;
	}*/
	
}
