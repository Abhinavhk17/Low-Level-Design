package exception;

/**
 * Exception thrown when an invalid or expired ticket is presented.
 */
public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String message) {
        super(message);
    }
}
