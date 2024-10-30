package entities;

public abstract class Staff extends User{
	protected String role;
	
	public Staff(String userID, String userPass, boolean firstLogin, String userRole) {
		super(userID, userPass, firstLogin);
		this.role = userRole;
	}
}
