package viewers;

/**
 * The `ViewInterface` interface defines a contract for displaying a menu
 * in a user interface.
 */
public interface ViewInterface {

    /**
     * Displays a menu to the user.
     *
     * @return `true` if the menu was displayed successfully; 
     *         `false` otherwise.
     */
    public boolean displayMenu();
}