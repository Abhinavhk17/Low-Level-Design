package strategy;

import entity.ParkingFloor;
import entity.ParkingSpot;
import entity.Vehicle;
import enums.SpotType;
import enums.VehicleType;
import interfaces.SlotAllocationStrategy;

import java.util.List;

/**
 * Slot allocation strategy that finds the first available free spot.
 * Implements Strategy Pattern for slot allocation.
 * Follows Open/Closed Principle - can be extended without modification.
 */
public class FirstFreeSlotStrategy implements SlotAllocationStrategy {

    /**
     * Allocate a parking spot for the vehicle.
     * Searches through all floors and spots to find the first available spot
     * that can accommodate the vehicle type.
     * 
     * @param parkingFloors List of parking floors to search
     * @param vehicle The vehicle that needs a spot
     * @return A suitable parking spot, or null if none available
     */
    @Override
    public ParkingSpot allocateSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        if (parkingFloors == null || vehicle == null) {
            return null;
        }
        
        for (ParkingFloor floor : parkingFloors) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (spot.isFree() && canFitVehicle(vehicle, spot)) {
                    return spot;
                }
            }
        }
        return null;
    }

    /**
     * Check if a vehicle can fit in a parking spot.
     * Business logic:
     * - BIKE can fit in any spot (SMALL, MEDIUM, LARGE)
     * - CAR can fit in MEDIUM or LARGE spots
     * - TRUCK can only fit in LARGE spots
     * 
     * @param vehicle The vehicle to check
     * @param spot The parking spot to check
     * @return true if vehicle can fit, false otherwise
     */
    private boolean canFitVehicle(Vehicle vehicle, ParkingSpot spot) {
        VehicleType vehicleType = vehicle.getType();
        SpotType spotType = spot.getSpotType();
        
        if (vehicleType == VehicleType.BIKE) {
            return true; // Bikes can fit anywhere
        }
        if (vehicleType == VehicleType.CAR) {
            return spotType != SpotType.SMALL; // Cars need MEDIUM or LARGE
        }
        return spotType == SpotType.LARGE; // Trucks need LARGE
    }
}
