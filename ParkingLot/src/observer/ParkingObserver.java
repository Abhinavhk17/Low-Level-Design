package observer;

/**
 * Observer interface for parking lot events.
 * Part of the Observer Design Pattern.
 * Follows Interface Segregation Principle.
 */
public interface ParkingObserver {
    /**
     * Called when a vehicle is parked.
     * 
     * @param licensePlate The license plate of the parked vehicle
     * @param spotId The ID of the parking spot
     */
    void onVehicleParked(String licensePlate, int spotId);
    
    /**
     * Called when a vehicle exits.
     * 
     * @param licensePlate The license plate of the exiting vehicle
     * @param fare The total fare paid
     */
    void onVehicleExit(String licensePlate, double fare);
    
    /**
     * Called when parking lot is full.
     */
    void onParkingFull();
    
    /**
     * Called when parking lot has available spots again.
     */
    void onParkingAvailable();
}
