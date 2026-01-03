package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a floor in the parking lot.
 * Follows Single Responsibility Principle - manages parking spots on a floor.
 * Implements proper encapsulation.
 */
public class ParkingFloor {
    private final String floorId;
    private final List<ParkingSpot> spots;

    public ParkingFloor(String floorId, List<ParkingSpot> spots) {
        if (floorId == null || floorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Floor ID cannot be null or empty");
        }
        if (spots == null || spots.isEmpty()) {
            throw new IllegalArgumentException("Spots list cannot be null or empty");
        }
        this.floorId = floorId;
        this.spots = new ArrayList<>(spots); // Defensive copy
    }

    public String getFloorId() {
        return floorId;
    }

    /**
     * Returns an unmodifiable view of parking spots.
     * Follows encapsulation principle.
     */
    public List<ParkingSpot> getSpots() {
        return Collections.unmodifiableList(spots);
    }

    /**
     * Check if this floor has any available spot.
     */
    public boolean hasAvailableSpot() {
        for (ParkingSpot spot : spots) {
            if (spot.isFree()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get count of available spots on this floor.
     */
    public int getAvailableSpotCount() {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (spot.isFree()) {
                count++;
            }
        }
        return count;
    }
}
