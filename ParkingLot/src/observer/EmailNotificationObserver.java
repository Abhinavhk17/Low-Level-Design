package observer;

/**
 * Email notification observer implementation.
 * Implements Observer Pattern.
 */
public class EmailNotificationObserver implements ParkingObserver {
    private final String emailAddress;
    
    public EmailNotificationObserver(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @Override
    public void onVehicleParked(String licensePlate, int spotId) {
        sendEmail("Vehicle " + licensePlate + " parked at spot " + spotId);
    }
    
    @Override
    public void onVehicleExit(String licensePlate, double fare) {
        sendEmail("Vehicle " + licensePlate + " exited. Total fare: $" + fare);
    }
    
    @Override
    public void onParkingFull() {
        sendEmail("ALERT: Parking lot is now full!");
    }
    
    @Override
    public void onParkingAvailable() {
        sendEmail("Parking spots are now available.");
    }
    
    private void sendEmail(String message) {
        // In real implementation, this would send actual email
        System.out.println("Email to " + emailAddress + ": " + message);
    }
}
