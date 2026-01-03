package interfaces;

import entity.ParkingFloor;
import entity.ParkingSpot;
import entity.Vehicle;

import java.util.List;

public interface SlotAllocationStrategy {
    ParkingSpot allocateSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}
