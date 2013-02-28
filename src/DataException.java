/**
 * Signals an attempt to load data from persistent store has failed.
 */
public class DataException extends Exception {
    /**
     * No argument constructor
     */
    public DataException() {
    }

    /**
     * Constructor
     *
     * @param message the detail message
     */
    public DataException(String message) {
        super(message);
    }

}
