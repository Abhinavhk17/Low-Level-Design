package entity;
import panels.EntryPanel;
import panels.ExitPanel;

import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingFloor> parkingFloors;
    private final List<EntryPanel> entryPanels;
    private final List<ExitPanel> exitPanels;

    // Private constructor for Singleton
    private ParkingLot(List<ParkingFloor> parkingFloors,
                       List<EntryPanel> entryPanels,
                       List<ExitPanel> exitPanels) {
        this.parkingFloors = parkingFloors;
        this. entryPanels = entryPanels;
        this. exitPanels = exitPanels;
    }

    // Thread-safe Singleton instance getter
    public static synchronized ParkingLot getInstance(List<ParkingFloor> parkingFloors,
                                                      List<EntryPanel> entryPanels,
                                                      List<ExitPanel> exitPanels) {
        if (instance == null) {
            instance = new ParkingLot(parkingFloors, entryPanels, exitPanels);
        }
        return instance;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public List<EntryPanel> getEntryPanels() {
        return entryPanels;
    }

    public List<ExitPanel> getExitPanels() {
        return exitPanels;
    }
}