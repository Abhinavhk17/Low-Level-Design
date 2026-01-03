package entity;

import enums.VehicleType;

/**
 * Abstract base class representing a vehicle in the parking lot system.
 * Follows the Open/Closed Principle - open for extension (new vehicle types),
 * closed for modification.
 */
public abstract class Vehicle {
    private final String licensePlate;
    private final VehicleType type;

    /**
     * Constructor for creating a vehicle.
     * 
     * @param licensePlate The unique license plate number
     * @param type The type of vehicle
     * @throws IllegalArgumentException if licensePlate is null or empty
     */
    public Vehicle(String licensePlate, VehicleType type) {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }
}
