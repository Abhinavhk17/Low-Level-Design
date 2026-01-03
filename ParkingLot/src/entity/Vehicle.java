package entity;

import enums.VehicleType;

public abstract class Vehicle {
    private final String licensePlate;
    private final VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    class Car extends Vehicle {
        public Car(String licensePlate) {
            super(licensePlate, VehicleType.CAR);
        }
    }

    class Bike extends Vehicle {
        public Bike(String licensePlate) {
            super(licensePlate, VehicleType.BIKE);
        }
    }

    class Truck extends Vehicle {
        public Truck(String licensePlate) {
            super(licensePlate, VehicleType.TRUCK);
        }
    }
}
