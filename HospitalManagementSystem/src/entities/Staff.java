package entities;

public abstract class Staff extends User{
	protected String role;
	
	public Staff(String userID,String userName, String userPass, boolean firstLogin, String userRole) {
		super(userID,userName, userPass, firstLogin);
		this.role = userRole;
	}
}
