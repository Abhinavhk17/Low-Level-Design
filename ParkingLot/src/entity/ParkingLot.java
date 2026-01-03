package entity;
import panels.EntryPanel;
import panels.ExitPanel;

import java.util.List;

public class ParkingLot {
    private final List<ParkingFloor> parkingFloors;
    private final List<EntryPanel> entryPanels;
    private final List<ExitPanel> exitPanels;

    public ParkingLot(List<ParkingFloor> parkingFloors,
                      List<EntryPanel> entryPanels,
                      List<ExitPanel> exitPanels) {
        this.parkingFloors = parkingFloors;
        this.entryPanels = entryPanels;
        this.exitPanels = exitPanels;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }
}