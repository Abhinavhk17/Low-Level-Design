package entity;

import enums.VehicleType;

/**
 * Represents a Car vehicle type in the parking lot system.
 */
public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}
