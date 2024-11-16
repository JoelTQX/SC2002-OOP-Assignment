package datastorage;

import java.security.NoSuchAlgorithmException;
import entities.User;

/**
 * The `Authenticator` class provides methods to authenticate users
 * based on their user ID and password. It interacts with the `DataStorage`
 * system to retrieve user records and validate credentials.
 */
public class Authenticator {
    /** The data storage instance used to access user records. */
    private DataStorage dataStorage;

    /**
     * Constructs an `Authenticator` object with a specified `DataStorage`.
     *
     * @param dataStorage The data storage instance for accessing user records.
     */
    public Authenticator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Authenticates a user by verifying their user ID and password.
     * Searches both patient and staff records for the user ID and validates the 
     * provided password if a matching user is found.
     *
     * @param userID the unique identification of the user.
     * @param userPass the password provided by the user.
     * @return the authenticated `User` object if authentication is successful;
     *         `null` if authentication fails or the user is not found.
     */
    public User authenticate(String userID, String userPass) {
        // Retrieve user from patient records
        User user = dataStorage.getPatientRecords().getPatientByID(userID);

        // If not found in patient records, check staff records
        if (user == null) {
            user = dataStorage.getStaffRecords().getStaffByID(userID);
        }

        // Validate the user's password if the user exists
        if (user != null) {
            try {
                if (user.validatePassword(Password.hashPassword(userPass))) {
                    return user;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        // Return null if authentication fails
        return null;
    }
}
