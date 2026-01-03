package entity;

import enums.SpotType;

/**
 * Represents a parking spot in the parking lot.
 * Follows Single Responsibility Principle - manages a single parking spot state.
 * Thread-safe implementation for concurrent access.
 */
public class ParkingSpot {
    private final int spotId;
    private final SpotType spotType;
    private Vehicle vehicle;

    public ParkingSpot(int spotId, SpotType spotType) {
        if (spotType == null) {
            throw new IllegalArgumentException("Spot type cannot be null");
        }
        this.spotId = spotId;
        this.spotType = spotType;
    }

    /**
     * Check if the spot is available.
     * Thread-safe method.
     */
    public synchronized boolean isFree() {
        return vehicle == null;
    }

    /**
     * Park a vehicle in this spot.
     * Thread-safe method.
     * 
     * @param vehicle The vehicle to park
     * @throws IllegalStateException if spot is already occupied
     * @throws IllegalArgumentException if vehicle is null
     */
    public synchronized void parkVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (this.vehicle != null) {
            throw new IllegalStateException("Spot is already occupied");
        }
        this.vehicle = vehicle;
    }

    /**
     * Remove vehicle from this spot.
     * Thread-safe method.
     * 
     * @throws IllegalStateException if spot is already free
     */
    public synchronized void unparkVehicle() {
        if (this.vehicle == null) {
            throw new IllegalStateException("Spot is already free");
        }
        this.vehicle = null;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public synchronized Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId=" + spotId +
                ", spotType=" + spotType +
                ", occupied=" + (vehicle != null) +
                '}';
    }
}
