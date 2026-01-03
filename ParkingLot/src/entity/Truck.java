package entity;

import enums.VehicleType;

/**
 * Represents a Truck vehicle type in the parking lot system.
 */
public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}
