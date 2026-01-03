package factory;

import entity.Bike;
import entity.Car;
import entity.Truck;
import entity.Vehicle;
import enums.VehicleType;

/**
 * Factory class for creating different types of vehicles.
 * Implements the Factory Design Pattern.
 * This pattern follows the Dependency Inversion Principle - 
 * clients depend on the Vehicle abstraction, not concrete classes.
 */
public class VehicleFactory {
    
    /**
     * Creates a vehicle based on the specified type.
     * 
     * @param licensePlate The license plate of the vehicle
     * @param type The type of vehicle to create
     * @return A Vehicle instance of the specified type
     * @throws IllegalArgumentException if vehicle type is not supported
     */
    public static Vehicle createVehicle(String licensePlate, VehicleType type) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        
        switch (type) {
            case BIKE:
                return new Bike(licensePlate);
            case CAR:
                return new Car(licensePlate);
            case TRUCK:
                return new Truck(licensePlate);
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }
}
