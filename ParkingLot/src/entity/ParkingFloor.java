package entity;

import enums.SpotType;

import java.util.List;

public class ParkingFloor {
    private final String floorId;
    private final List<ParkingSpot> spots;

    public String getFloorId() {
        return floorId;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public ParkingFloor(List<ParkingSpot> spots, String floorId) {
        this.spots = spots;
        this.floorId = floorId;
    }
}
