import entity.*;
import enums.SpotType;
import enums.VehicleType;
import exception.NoSpotAvailableException;
import factory.VehicleFactory;
import interfaces.PricingStrategy;
import interfaces.SlotAllocationStrategy;
import observer.EmailNotificationObserver;
import observer.SMSNotificationObserver;
import panels.EntryPanel;
import panels.ExitPanel;
import payment.CardPayment;
import payment.CashPayment;
import payment.PaymentStrategy;
import strategy.FirstFreeSlotStrategy;
import strategy.HourlyPricingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class demonstrating the Parking Lot System.
 * This implementation follows SOLID principles and implements multiple design patterns:
 * 
 * SOLID Principles:
 * 1. Single Responsibility Principle (SRP): Each class has one responsibility
 *    - Vehicle handles vehicle data only
 *    - ParkingSpot manages spot state only
 *    - EntryPanel handles entry operations only
 *    - ExitPanel handles exit operations only
 * 
 * 2. Open/Closed Principle (OCP): Open for extension, closed for modification
 *    - Can add new vehicle types without modifying Vehicle class
 *    - Can add new pricing strategies without modifying ExitPanel
 *    - Can add new slot allocation strategies without modifying EntryPanel
 * 
 * 3. Liskov Substitution Principle (LSP): Subtypes are substitutable for their base types
 *    - Car, Bike, Truck can be used wherever Vehicle is expected
 *    - Different payment strategies can be substituted
 * 
 * 4. Interface Segregation Principle (ISP): Clients shouldn't depend on interfaces they don't use
 *    - Separate interfaces for PricingStrategy, SlotAllocationStrategy, PaymentStrategy
 *    - Each interface has focused, minimal methods
 * 
 * 5. Dependency Inversion Principle (DIP): Depend on abstractions, not concretions
 *    - EntryPanel depends on SlotAllocationStrategy interface
 *    - ExitPanel depends on PricingStrategy interface
 *    - Panels work with Vehicle abstraction, not concrete classes
 * 
 * Design Patterns Implemented:
 * 1. Strategy Pattern: PricingStrategy, SlotAllocationStrategy, PaymentStrategy
 * 2. Factory Pattern: VehicleFactory for creating vehicles
 * 3. Observer Pattern: ParkingObserver for notifications
 * 4. Singleton Pattern: Can be applied to ParkingLot (not implemented to keep flexibility)
 */
public class ParkingLotSystem {
    public static void main(String[] args) {
        System.out.println("=== Parking Lot Management System ===\n");
        
        // Create parking spots for different floors
        List<ParkingSpot> floor1Spots = createSpots(1, 10);
        List<ParkingSpot> floor2Spots = createSpots(11, 10);
        
        // Create parking floors
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(new ParkingFloor("Floor-1", floor1Spots));
        floors.add(new ParkingFloor("Floor-2", floor2Spots));
        
        // Create strategies
        SlotAllocationStrategy slotStrategy = new FirstFreeSlotStrategy();
        PricingStrategy pricingStrategy = new HourlyPricingStrategy();
        
        // Create panels
        List<EntryPanel> entryPanels = new ArrayList<>();
        entryPanels.add(new EntryPanel("Entry-1", slotStrategy));
        
        List<ExitPanel> exitPanels = new ArrayList<>();
        exitPanels.add(new ExitPanel("Exit-1", pricingStrategy));
        
        // Create parking lot
        ParkingLot parkingLot = new ParkingLot("City Center Parking", floors, entryPanels, exitPanels);
        
        // Add observers (Observer Pattern)
        parkingLot.addObserver(new EmailNotificationObserver("admin@parking.com"));
        parkingLot.addObserver(new SMSNotificationObserver("+1234567890"));
        
        System.out.println("Parking Lot: " + parkingLot.getName());
        System.out.println("Total available spots: " + parkingLot.getAvailableSpotCount());
        System.out.println();
        
        // Demonstrate vehicle entry and exit
        demonstrateVehicleFlow(parkingLot, entryPanels.get(0), exitPanels.get(0));
        
        System.out.println("\n=== System Demonstration Complete ===");
    }
    
    /**
     * Demonstrates the complete flow of vehicles entering and exiting.
     */
    private static void demonstrateVehicleFlow(ParkingLot parkingLot, 
                                               EntryPanel entryPanel, 
                                               ExitPanel exitPanel) {
        // Create vehicles using Factory Pattern
        Vehicle car1 = VehicleFactory.createVehicle("ABC-123", VehicleType.CAR);
        Vehicle bike1 = VehicleFactory.createVehicle("XYZ-789", VehicleType.BIKE);
        Vehicle truck1 = VehicleFactory.createVehicle("TRK-456", VehicleType.TRUCK);
        
        System.out.println("--- Vehicle Entry ---");
        
        // Vehicle 1: Car entry
        try {
            Ticket ticket1 = entryPanel.issueTicket(car1, parkingLot.getParkingFloors());
            System.out.println("✓ Car parked: " + car1.getLicensePlate() + 
                             " at Spot ID: " + ticket1.getParkingSpot().getSpotId());
            parkingLot.notifyVehicleParked(car1.getLicensePlate(), ticket1.getParkingSpot().getSpotId());
            
            // Simulate some time passing
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Vehicle 1: Car exit with payment
            System.out.println("\n--- Vehicle Exit ---");
            PaymentStrategy cashPayment = new CashPayment();
            boolean paymentSuccess = exitPanel.processExitWithPayment(ticket1, cashPayment);
            if (paymentSuccess) {
                System.out.println("✓ Car exited: " + car1.getLicensePlate());
                double fare = HourlyPricingStrategy.getHourlyRate(); // Minimum 1 hour
                parkingLot.notifyVehicleExit(car1.getLicensePlate(), fare);
            }
            
        } catch (NoSpotAvailableException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        System.out.println();
        
        // Vehicle 2: Bike entry
        try {
            Ticket ticket2 = entryPanel.issueTicket(bike1, parkingLot.getParkingFloors());
            System.out.println("✓ Bike parked: " + bike1.getLicensePlate() + 
                             " at Spot ID: " + ticket2.getParkingSpot().getSpotId());
            parkingLot.notifyVehicleParked(bike1.getLicensePlate(), ticket2.getParkingSpot().getSpotId());
            
            // Bike exit with card payment
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            PaymentStrategy cardPayment = new CardPayment("1234-5678-9012-3456");
            exitPanel.processExitWithPayment(ticket2, cardPayment);
            System.out.println("✓ Bike exited: " + bike1.getLicensePlate());
            
        } catch (NoSpotAvailableException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        System.out.println();
        
        // Vehicle 3: Truck entry
        try {
            Ticket ticket3 = entryPanel.issueTicket(truck1, parkingLot.getParkingFloors());
            System.out.println("✓ Truck parked: " + truck1.getLicensePlate() + 
                             " at Spot ID: " + ticket3.getParkingSpot().getSpotId());
            parkingLot.notifyVehicleParked(truck1.getLicensePlate(), ticket3.getParkingSpot().getSpotId());
            
        } catch (NoSpotAvailableException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        System.out.println("\nRemaining available spots: " + parkingLot.getAvailableSpotCount());
    }
    
    /**
     * Helper method to create parking spots.
     * Creates a mix of SMALL, MEDIUM, and LARGE spots.
     */
    private static List<ParkingSpot> createSpots(int startId, int count) {
        List<ParkingSpot> spots = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SpotType type;
            if (i % 3 == 0) {
                type = SpotType.LARGE;
            } else if (i % 3 == 1) {
                type = SpotType.MEDIUM;
            } else {
                type = SpotType.SMALL;
            }
            spots.add(new ParkingSpot(startId + i, type));
        }
        return spots;
    }
}
