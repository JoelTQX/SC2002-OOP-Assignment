package controllers;

/**
 * The ControllerInterface defines a contract for controllers that manage user-related actions. 
 * It provides a method for retrieving the user ID of the currently logged-in user.
 * Any controller that implements this interface should provide the implementation of the method 
 * to return the unique identifier of the user.
 */
public interface ControllerInterface {
	
    /**
     * Retrieves the user ID of the currently logged-in user.
     * 
     * @return The unique identifier of the user
     */
	public String getUserID();
}
