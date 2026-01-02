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


}
