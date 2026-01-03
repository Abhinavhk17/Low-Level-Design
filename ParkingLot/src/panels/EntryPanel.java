package panels;

import entity.*;
import exception.NoSpotAvailableException;
import interfaces.SlotAllocationStrategy;

import java.util.List;

/**
 * Entry panel for issuing parking tickets.
 * Follows Single Responsibility Principle - handles vehicle entry only.
 * Thread-safe implementation.
 */
public class EntryPanel {
    private final String panelId;
    private final SlotAllocationStrategy slotAllocationStrategy;

    public EntryPanel(String panelId, SlotAllocationStrategy slotAllocationStrategy) {
        if (panelId == null || panelId.trim().isEmpty()) {
            throw new IllegalArgumentException("Panel ID cannot be null or empty");
        }
        if (slotAllocationStrategy == null) {
            throw new IllegalArgumentException("Slot allocation strategy cannot be null");
        }
        this.panelId = panelId;
        this.slotAllocationStrategy = slotAllocationStrategy;
    }

    /**
     * Issue a parking ticket for a vehicle.
     * Thread-safe method.
     * 
     * @param vehicle The vehicle entering the parking lot
     * @param floors List of parking floors
     * @return Ticket for the parked vehicle
     * @throws NoSpotAvailableException if no spot is available
     * @throws IllegalArgumentException if vehicle or floors is null
     */
    public synchronized Ticket issueTicket(Vehicle vehicle, List<ParkingFloor> floors) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (floors == null || floors.isEmpty()) {
            throw new IllegalArgumentException("Floors list cannot be null or empty");
        }
        
        ParkingSpot spot = slotAllocationStrategy.allocateSpot(floors, vehicle);
        if (spot == null) {
            throw new NoSpotAvailableException("No parking spot available for vehicle: " + 
                                              vehicle.getLicensePlate());
        }
        
        spot.parkVehicle(vehicle);
        return new Ticket(vehicle, spot);
    }

    public String getPanelId() {
        return panelId;
    }
}
