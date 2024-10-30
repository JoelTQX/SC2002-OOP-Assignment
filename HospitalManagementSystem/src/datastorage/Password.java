package datastorage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// need change method names in UML

/**
 * The `Password` class represents a hashed password using the SHA-256 algorithm.
 * It provides methods for hashing a password and retrieving the hashed password.
 */

public class Password {
    /**
     * The hashed password stored as a string.
     */
    private String HashedPassword;

    /**
     * Constructs a `Password` instance with the given hashed password.
     *
     * @param hashedPassword The hashed password to set.
     */
    public Password(String hashedPassword) throws NoSuchAlgorithmException {

        this.HashedPassword = hashedPassword;

    }

    /**
     * Hashes the given password using the SHA-256 algorithm.
     *
     * @param password The password to be hashed.
     * @return The hashed password as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException { //package access
        // Create a MessageDigest instance for SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Perform the hash
        byte[] hashedBytes = md.digest(password.getBytes());

        // Convert the byte array to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }


    /**
     * Gets the hashed password.
     *
     * @return The hashed password.
     */
    public String getPW(){return this.HashedPassword;}

    /**
     * Sets a new hashed password for the instance.
     *
     * @param newPW The new password to be hashed and set.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public void setPW(String newPW) throws NoSuchAlgorithmException {this.HashedPassword = hashPassword(newPW);}
}
