package panels;

import entity.*;
import interfaces.SlotAllocationStrategy;

import java.util.List;

public class EntryPanel {
    private final String panelId;
    private final SlotAllocationStrategy slotAllocationStrategy;

    public EntryPanel(String panelId, SlotAllocationStrategy slotAllocationStrategy) {
        this.panelId = panelId;
        this.slotAllocationStrategy = slotAllocationStrategy;
    }

    public synchronized Ticket issueTicket(Vehicle vehicle, List<ParkingFloor> floors) {
        ParkingSpot spot = slotAllocationStrategy.allocateSpot(floors, vehicle);
        if (spot == null) {
            throw new RuntimeException("No spot available");
        }
        spot.parkVehicle(vehicle);
        return new Ticket(vehicle, spot);
    }
}
