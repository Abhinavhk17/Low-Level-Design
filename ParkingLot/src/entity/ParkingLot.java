package entity;

import observer.ParkingObserver;
import panels.EntryPanel;
import panels.ExitPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the parking lot system.
 * Follows Single Responsibility Principle - manages parking floors and observers.
 * Implements proper encapsulation with defensive copying.
 */
public class ParkingLot {
    private final List<ParkingFloor> parkingFloors;
    private final List<EntryPanel> entryPanels;
    private final List<ExitPanel> exitPanels;
    private final List<ParkingObserver> observers;
    private final String name;

    public ParkingLot(String name,
                      List<ParkingFloor> parkingFloors,
                      List<EntryPanel> entryPanels,
                      List<ExitPanel> exitPanels) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Parking lot name cannot be null or empty");
        }
        this.name = name;
        this.parkingFloors = new ArrayList<>(parkingFloors);
        this.entryPanels = new ArrayList<>(entryPanels);
        this.exitPanels = new ArrayList<>(exitPanels);
        this.observers = new ArrayList<>();
    }

    /**
     * Returns an unmodifiable view of parking floors.
     * Follows encapsulation principle - prevents external modification.
     */
    public List<ParkingFloor> getParkingFloors() {
        return Collections.unmodifiableList(parkingFloors);
    }

    public String getName() {
        return name;
    }

    /**
     * Add an observer to be notified of parking events.
     * 
     * @param observer The observer to add
     */
    public void addObserver(ParkingObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Remove an observer.
     * 
     * @param observer The observer to remove
     */
    public void removeObserver(ParkingObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers of a vehicle parked event.
     */
    public void notifyVehicleParked(String licensePlate, int spotId) {
        for (ParkingObserver observer : observers) {
            observer.onVehicleParked(licensePlate, spotId);
        }
    }

    /**
     * Notify all observers of a vehicle exit event.
     */
    public void notifyVehicleExit(String licensePlate, double fare) {
        for (ParkingObserver observer : observers) {
            observer.onVehicleExit(licensePlate, fare);
        }
    }

    /**
     * Notify all observers that parking is full.
     */
    public void notifyParkingFull() {
        for (ParkingObserver observer : observers) {
            observer.onParkingFull();
        }
    }

    /**
     * Notify all observers that parking is available.
     */
    public void notifyParkingAvailable() {
        for (ParkingObserver observer : observers) {
            observer.onParkingAvailable();
        }
    }

    /**
     * Check if parking lot is full.
     */
    public boolean isFull() {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.hasAvailableSpot()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get total available spots count.
     */
    public int getAvailableSpotCount() {
        int count = 0;
        for (ParkingFloor floor : parkingFloors) {
            count += floor.getAvailableSpotCount();
        }
        return count;
    }
}