package williamsadeho.parentalcontrol.exception;

/**
 * Exception to be thrown when a system error occurs.
 *
 * @author - Williams Adeho
 *
 */
public class TechnicalFailureException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This constructor is used to create a new exception from the message supplied.
     *
     * @param message the data access message
     */
    public TechnicalFailureException(String message) {
        super(message);
    }
}
