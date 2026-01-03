package entity;

import enums.SpotType;

public class ParkingSpot {
    private final int spotId;
    private final SpotType spotType;
    private  Vehicle vehicle;

    public ParkingSpot(int spotId, SpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
    }

    boolean isFree() {
        return vehicle == null;
    }

    void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void unparkVehicle(Vehicle vehicle) {
        this.vehicle = null;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
