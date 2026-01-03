package observer;

/**
 * SMS notification observer implementation.
 * Implements Observer Pattern.
 */
public class SMSNotificationObserver implements ParkingObserver {
    private final String phoneNumber;
    
    public SMSNotificationObserver(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void onVehicleParked(String licensePlate, int spotId) {
        sendSMS("Vehicle " + licensePlate + " parked at spot " + spotId);
    }
    
    @Override
    public void onVehicleExit(String licensePlate, double fare) {
        sendSMS("Vehicle " + licensePlate + " exited. Total fare: $" + fare);
    }
    
    @Override
    public void onParkingFull() {
        sendSMS("ALERT: Parking lot is now full!");
    }
    
    @Override
    public void onParkingAvailable() {
        sendSMS("Parking spots are now available.");
    }
    
    private void sendSMS(String message) {
        // In real implementation, this would send actual SMS
        System.out.println("SMS to " + phoneNumber + ": " + message);
    }
}
