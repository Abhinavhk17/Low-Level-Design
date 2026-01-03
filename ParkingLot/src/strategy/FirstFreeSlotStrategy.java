package strategy;

import entity.ParkingFloor;
import entity.ParkingSpot;
import entity.Vehicle;
import enums.SpotType;
import enums.VehicleType;
import interfaces.SlotAllocationStrategy;

import java.util.List;

public class FirstFreeSlotStrategy implements SlotAllocationStrategy {

    @Override
    public ParkingSpot allocateSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (spot.isFree() && canFitVehicle(vehicle, spot)) {
                    return spot;
                }
            }
        }
        return null;
    }

    private boolean canFitVehicle(Vehicle vehicle,ParkingSpot spot) {
        if(vehicle.getType() == VehicleType.BIKE) return true;
        if(vehicle.getType() == VehicleType.CAR ) return spot.getSpotType() != enums.SpotType.SMALL;
        return spot.getSpotType() == SpotType.LARGE;
    }
}
