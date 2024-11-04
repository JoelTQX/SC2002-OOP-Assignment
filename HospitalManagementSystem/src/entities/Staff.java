package entities;

public abstract class Staff extends User{
	protected String role;
	
	public Staff(String userID,String userName, String userPass, boolean firstLogin, String userRole,String gender) {
		super(userID,userName, userPass, firstLogin,gender);
		this.role = userRole;
	}

	public void add(Staff staffUser) {
		// TODO Auto-generated method stub
		
	}

	public void remove(int indexOfStaff) {
		// TODO Auto-generated method stub
		
	}
}
