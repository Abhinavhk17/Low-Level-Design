package entity;

import enums.VehicleType;

/**
 * Represents a Bike vehicle type in the parking lot system.
 */
public class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate, VehicleType.BIKE);
    }
}
