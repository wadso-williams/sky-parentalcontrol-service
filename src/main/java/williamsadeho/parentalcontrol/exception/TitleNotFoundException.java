package williamsadeho.parentalcontrol.exception;

/**
 * Exception to be thrown when a given movie could not be found.
 *
 * @author - Williams Adeho
 *
 */
public class TitleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This constructor is used to create a new exception from the message supplied.
     *
     * @param message the custom message
     */
    public TitleNotFoundException(String message) {
        super(message);
    }

}
