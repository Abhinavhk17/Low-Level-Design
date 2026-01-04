import entity.*;
import enums.SpotType;
import enums.VehicleType;
import interfaces.PricingStrategy;
import interfaces.SlotAllocationStrategy;
import panels.EntryPanel;
import panels.ExitPanel;
import strategy.FirstFreeSlotStrategy;
import strategy.HourlyPricingStrategy;

import java.util. ArrayList;
import java.util. List;

public class ParkingLotSystem {
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System Started ===\n");

        // Initialize Parking Spots for Floor 1
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new ParkingSpot(101, SpotType. SMALL));
        floor1Spots.add(new ParkingSpot(102, SpotType.SMALL));
        floor1Spots. add(new ParkingSpot(103, SpotType. MEDIUM));
        floor1Spots. add(new ParkingSpot(104, SpotType. MEDIUM));
        floor1Spots.add(new ParkingSpot(105, SpotType. LARGE));

        // Initialize Parking Spots for Floor 2
        List<ParkingSpot> floor2Spots = new ArrayList<>();
        floor2Spots. add(new ParkingSpot(201, SpotType. SMALL));
        floor2Spots.add(new ParkingSpot(202, SpotType. MEDIUM));
        floor2Spots. add(new ParkingSpot(203, SpotType. LARGE));
        floor2Spots.add(new ParkingSpot(204, SpotType.LARGE));

        // Create Parking Floors
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        parkingFloors.add(new ParkingFloor(floor1Spots, "Floor-1"));
        parkingFloors.add(new ParkingFloor(floor2Spots, "Floor-2"));

        // Create Strategies
        SlotAllocationStrategy slotStrategy = new FirstFreeSlotStrategy();
        PricingStrategy pricingStrategy = new HourlyPricingStrategy();

        // Create Entry and Exit Panels
        List<EntryPanel> entryPanels = new ArrayList<>();
        entryPanels.add(new EntryPanel("Entry-1", slotStrategy));
        entryPanels.add(new EntryPanel("Entry-2", slotStrategy));

        List<ExitPanel> exitPanels = new ArrayList<>();
        exitPanels.add(new ExitPanel("Exit-1", pricingStrategy));
        exitPanels. add(new ExitPanel("Exit-2", pricingStrategy));

        // Initialize Parking Lot (Singleton)
        ParkingLot parkingLot = ParkingLot.getInstance(parkingFloors, entryPanels, exitPanels);
        System.out.println("Parking Lot initialized with " + parkingFloors.size() + " floors");
        System.out.println("Total spots: " + (floor1Spots.size() + floor2Spots.size()) + "\n");

        // Simulate Vehicle Entry and Exit

        // Vehicle 1: Bike
        System.out.println("--- Vehicle 1: Bike ---");
        Vehicle bike = new Vehicle("BIKE-001", VehicleType.BIKE);
        EntryPanel entryPanel1 = parkingLot.getEntryPanels().get(0);
        Ticket ticket1 = entryPanel1.issueTicket(bike, parkingLot.getParkingFloors());
        System.out.println("Ticket ID:  " + ticket1.getTicketId());
        System.out. println("Parked at Spot: " + ticket1.getParkingSpot().getSpotId());
        System.out.println("Spot Type: " + ticket1.getParkingSpot().getSpotType());
        System.out.println();

        // Vehicle 2: Car
        System.out.println("--- Vehicle 2: Car ---");
        Vehicle car = new Vehicle("CAR-001", VehicleType.CAR);
        Ticket ticket2 = entryPanel1.issueTicket(car, parkingLot.getParkingFloors());
        System.out.println("Ticket ID:  " + ticket2.getTicketId());
        System.out. println("Parked at Spot: " + ticket2.getParkingSpot().getSpotId());
        System.out.println("Spot Type: " + ticket2.getParkingSpot().getSpotType());
        System.out.println();

        // Vehicle 3: Truck
        System.out.println("--- Vehicle 3: Truck ---");
        Vehicle truck = new Vehicle("TRUCK-001", VehicleType.TRUCK);
        Ticket ticket3 = entryPanel1.issueTicket(truck, parkingLot.getParkingFloors());
        System.out.println("Ticket ID: " + ticket3.getTicketId());
        System.out.println("Parked at Spot:  " + ticket3.getParkingSpot().getSpotId());
        System.out.println("Spot Type: " + ticket3.getParkingSpot().getSpotType());
        System.out.println();

        // Simulate some time passing (2 seconds)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Vehicle Exit
        System.out.println("--- Exit Processing ---");
        ExitPanel exitPanel1 = parkingLot.getExitPanels().get(0);

        double fare1 = exitPanel1.processExit(ticket1);
        System.out. println("Bike (License: " + bike.getLicensePlate() + ") - Fare: ₹" + fare1);

        double fare2 = exitPanel1.processExit(ticket2);
        System.out.println("Car (License: " + car.getLicensePlate() + ") - Fare: ₹" + fare2);

        double fare3 = exitPanel1.processExit(ticket3);
        System.out.println("Truck (License: " + truck.getLicensePlate() + ") - Fare: ₹" + fare3);

        System.out.println("\n=== Parking Lot System Demo Completed ===");

        // Verify spots are now free
        System.out.println("\nSpot Status After Exit:");
        System.out.println("Spot 101 is free:  " + floor1Spots.get(0).isFree());
        System.out.println("Spot 103 is free: " + floor1Spots.get(2).isFree());
        System.out.println("Spot 105 is free: " + floor1Spots.get(4).isFree());
    }
}