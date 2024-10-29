package boundaries;
import entities.Password;
import entities.User;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.List;
import enitities.Password;
import controllers.*;
import enitities.*;
import helpers.*;

/**
 * The Authenticator class is responsible for authenticating user login credentials.
 * It checks if the provided user ID, hashed password, and user type match any records
 * in the password list, permitting access if a match is found.
 */
public class Authenticator {

    /**
     * Flag indicating whether the user is permitted (true) or not (false).
     */
    private boolean permitted;

    /**
     * Constructs an Authenticator object with the initial permitted state set to false.
     */
    public Authenticator(){this.permitted = false;}


    /**
     * Sets the permitted flag to true if the provided user ID, hashed password,
     * and user type match any records in the given password list.
     *
     * @param userID          The user ID to be checked.
     * @param hashedPassword  The hashed password to be checked.
     * @param passwordList    The list of password records to compare against.
     * @param userType        The type of user (e.g., "STUDENT", "STAFF") to be checked.
     * @throws NoSuchAlgorithmException If an algorithm is requested but is not available in the environment.
     */
    public void setPermitted(String userID, Password hashedPassword, List<Map<String, String>> passwordList, String userType) throws NoSuchAlgorithmException {
        for (Map<String, String> record : passwordList) {
            if (record.get("NetworkID").equals(userID) && record.get("HashedPassword").equals(hashedPassword.getPW()) && record.get("UserType").equals(userType.toUpperCase())) {
                this.permitted = true;
                return;
            }
        }
    }
    public void testLogin(String userID, String password, List<User> patientRecords) {
    	for(User patient : patientRecords) {
    		if(userID.equals(patient.userID)) {
    			if(password.equals(patient.Password)) {
    				System.out.println("Successful Login for " + patient.name);
    				return;
    			}
    		}
    	}
    	System.out.println("Unsuccessful Login... Try Again...");
    }
    /**
     * Gets the current permitted state.
     *
     * @return True if the user is permitted, false otherwise.
     */
    public boolean getPermitted(){
        return this.permitted;
    }

}
