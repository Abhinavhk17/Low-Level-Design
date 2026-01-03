package exception;

/**
 * Exception thrown when no parking spot is available for a vehicle.
 */
public class NoSpotAvailableException extends RuntimeException {
    public NoSpotAvailableException(String message) {
        super(message);
    }
}
