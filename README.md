# Low-Level-Design

## Parking Lot Management System

This repository contains a comprehensive implementation of a **Parking Lot Management System** that demonstrates best practices in object-oriented design, following **SOLID principles** and implementing multiple **design patterns**.

## 📋 Table of Contents
- [Architecture Overview](#architecture-overview)
- [SOLID Principles](#solid-principles)
- [Design Patterns](#design-patterns)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Features](#features)
- [Code Quality](#code-quality)

## 🏗️ Architecture Overview

The Parking Lot System is designed to manage vehicle parking operations including:
- Vehicle entry and ticket issuance
- Parking spot allocation based on vehicle type
- Vehicle exit and fare calculation
- Multiple payment methods
- Real-time notifications for parking events

## 🎯 SOLID Principles

### 1. Single Responsibility Principle (SRP)
**Each class has a single, well-defined responsibility:**

- **`Vehicle`**: Manages vehicle data only
- **`ParkingSpot`**: Manages a single parking spot's state
- **`ParkingFloor`**: Manages parking spots on one floor
- **`ParkingLot`**: Coordinates floors and observers
- **`EntryPanel`**: Handles vehicle entry operations
- **`ExitPanel`**: Handles vehicle exit and payment
- **`Ticket`**: Represents parking ticket data

### 2. Open/Closed Principle (OCP)
**Classes are open for extension but closed for modification:**

- **Vehicle Types**: Can add new vehicle types (Bus, Motorcycle) by extending `Vehicle` without modifying existing code
- **Pricing Strategies**: Can add new pricing models (FlatRate, DynamicPricing) by implementing `PricingStrategy`
- **Slot Allocation**: Can implement new allocation strategies (NearestToExit, ByFloor) via `SlotAllocationStrategy`
- **Payment Methods**: New payment types can be added by implementing `PaymentStrategy`

### 3. Liskov Substitution Principle (LSP)
**Subtypes can substitute their base types:**

- `Car`, `Bike`, `Truck` can be used wherever `Vehicle` is expected
- Different `PricingStrategy` implementations are interchangeable
- Different `PaymentStrategy` implementations are interchangeable
- Different `SlotAllocationStrategy` implementations are interchangeable

### 4. Interface Segregation Principle (ISP)
**Clients depend only on interfaces they use:**

- **`PricingStrategy`**: Focused interface for pricing logic
- **`SlotAllocationStrategy`**: Dedicated interface for allocation logic
- **`PaymentStrategy`**: Specific interface for payment processing
- **`ParkingObserver`**: Notification interface for observers

Each interface has minimal, focused methods specific to its concern.

### 5. Dependency Inversion Principle (DIP)
**Depend on abstractions, not concretions:**

- **`EntryPanel`** depends on `SlotAllocationStrategy` interface, not concrete implementations
- **`ExitPanel`** depends on `PricingStrategy` interface, not concrete implementations
- **Panels** work with `Vehicle` abstraction, not concrete `Car`, `Bike`, or `Truck` classes
- **`ParkingLot`** depends on `ParkingObserver` interface for notifications

## 🎨 Design Patterns

### 1. Strategy Pattern
**Used in multiple areas for interchangeable algorithms:**

#### Pricing Strategy
```java
public interface PricingStrategy {
    double calculatePrice(Ticket ticket, long exitTime);
}
```
**Implementations:**
- `HourlyPricingStrategy`: Charges based on hourly rates
- `FlatRatePricingStrategy`: Fixed rate regardless of duration

#### Slot Allocation Strategy
```java
public interface SlotAllocationStrategy {
    ParkingSpot allocateSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}
```
**Implementations:**
- `FirstFreeSlotStrategy`: Allocates first available spot that fits vehicle

#### Payment Strategy
```java
public interface PaymentStrategy {
    boolean processPayment(double amount);
}
```
**Implementations:**
- `CashPayment`: Cash payment processing
- `CardPayment`: Credit/Debit card payment
- `DigitalWalletPayment`: Digital wallet (PayPal, Google Pay, etc.)

### 2. Factory Pattern
**Used for creating vehicle objects:**

```java
public class VehicleFactory {
    public static Vehicle createVehicle(String licensePlate, VehicleType type) {
        switch (type) {
            case BIKE: return new Bike(licensePlate);
            case CAR: return new Car(licensePlate);
            case TRUCK: return new Truck(licensePlate);
        }
    }
}
```

**Benefits:**
- Centralized vehicle creation logic
- Easy to add new vehicle types
- Client code doesn't need to know about concrete classes

### 3. Observer Pattern
**Used for event notifications:**

```java
public interface ParkingObserver {
    void onVehicleParked(String licensePlate, int spotId);
    void onVehicleExit(String licensePlate, double fare);
    void onParkingFull();
    void onParkingAvailable();
}
```

**Implementations:**
- `EmailNotificationObserver`: Sends email notifications
- `SMSNotificationObserver`: Sends SMS notifications

**Benefits:**
- Loose coupling between parking lot and notification systems
- Easy to add new notification channels
- Multiple observers can be notified simultaneously

## 📁 Project Structure

```
ParkingLot/
├── src/
│   ├── entity/                 # Domain entities
│   │   ├── Vehicle.java        # Abstract vehicle class
│   │   ├── Car.java
│   │   ├── Bike.java
│   │   ├── Truck.java
│   │   ├── ParkingSpot.java    # Individual parking spot
│   │   ├── ParkingFloor.java   # Floor containing multiple spots
│   │   ├── ParkingLot.java     # Main parking lot with observers
│   │   └── Ticket.java         # Parking ticket
│   │
│   ├── enums/                  # Enumerations
│   │   ├── VehicleType.java
│   │   └── SpotType.java
│   │
│   ├── interfaces/             # Strategy interfaces
│   │   ├── PricingStrategy.java
│   │   └── SlotAllocationStrategy.java
│   │
│   ├── strategy/               # Strategy implementations
│   │   ├── HourlyPricingStrategy.java
│   │   ├── FlatRatePricingStrategy.java
│   │   └── FirstFreeSlotStrategy.java
│   │
│   ├── factory/                # Factory pattern
│   │   └── VehicleFactory.java
│   │
│   ├── payment/                # Payment strategies
│   │   ├── PaymentStrategy.java
│   │   ├── CashPayment.java
│   │   ├── CardPayment.java
│   │   └── DigitalWalletPayment.java
│   │
│   ├── observer/               # Observer pattern
│   │   ├── ParkingObserver.java
│   │   ├── EmailNotificationObserver.java
│   │   └── SMSNotificationObserver.java
│   │
│   ├── exception/              # Custom exceptions
│   │   ├── NoSpotAvailableException.java
│   │   └── InvalidTicketException.java
│   │
│   ├── panels/                 # Entry/Exit panels
│   │   ├── EntryPanel.java
│   │   └── ExitPanel.java
│   │
│   └── ParkingLotSystem.java   # Main demonstration class
```

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compilation
```bash
cd ParkingLot/src
javac ParkingLotSystem.java
```

### Execution
```bash
java ParkingLotSystem
```

### Expected Output
The program demonstrates:
1. Parking lot initialization
2. Vehicle entry with ticket issuance
3. Observer notifications (email/SMS)
4. Vehicle exit with fare calculation
5. Multiple payment methods
6. Real-time available spot tracking

## ✨ Features

### Core Features
- ✅ **Multi-floor parking support**
- ✅ **Different spot sizes** (Small, Medium, Large)
- ✅ **Vehicle type matching** (Bike fits anywhere, Car needs Medium/Large, Truck needs Large)
- ✅ **Automatic ticket generation** with UUID
- ✅ **Flexible pricing strategies**
- ✅ **Multiple payment methods**
- ✅ **Real-time notifications**

### Thread Safety
- ✅ **Synchronized parking spot operations**
- ✅ **Thread-safe entry/exit panel operations**
- ✅ **Concurrent vehicle handling**

### Error Handling
- ✅ **Custom exceptions** for better error management
- ✅ **Input validation** for all critical operations
- ✅ **Defensive programming** with null checks

### Encapsulation
- ✅ **Immutable ticket objects**
- ✅ **Defensive copying** for collections
- ✅ **Unmodifiable list returns** to prevent external modification
- ✅ **Private fields** with proper getters

## 📊 Code Quality

### Documentation
- ✅ **Comprehensive JavaDoc** for all public classes and methods
- ✅ **Inline comments** explaining complex logic
- ✅ **Design pattern documentation** in class headers

### Best Practices
- ✅ **Meaningful variable/method names**
- ✅ **Small, focused methods**
- ✅ **Proper exception handling**
- ✅ **Validation of inputs**
- ✅ **toString() methods** for debugging

## 🔧 Extensibility

### Easy to Extend
1. **New Vehicle Types**: Create a class extending `Vehicle`
2. **New Pricing Models**: Implement `PricingStrategy` interface
3. **New Allocation Algorithms**: Implement `SlotAllocationStrategy` interface
4. **New Payment Methods**: Implement `PaymentStrategy` interface
5. **New Notification Channels**: Implement `ParkingObserver` interface

### Example: Adding a Bus Vehicle Type
```java
public class Bus extends Vehicle {
    public Bus(String licensePlate) {
        super(licensePlate, VehicleType.BUS);
    }
}
```

Then update the factory:
```java
case BUS:
    return new Bus(licensePlate);
```

## 📝 Summary of Improvements

### Original Code Issues Fixed:
1. ❌ **Inner classes in Vehicle** → ✅ **Proper separate class files**
2. ❌ **Duplicate Ticket classes** → ✅ **Single, well-designed Ticket class**
3. ❌ **Direct list exposure** → ✅ **Defensive copying and unmodifiable lists**
4. ❌ **Generic RuntimeException** → ✅ **Custom exception classes**
5. ❌ **No payment system** → ✅ **Full payment strategy implementation**
6. ❌ **Limited thread safety** → ✅ **Comprehensive synchronization**
7. ❌ **No validation** → ✅ **Input validation throughout**
8. ❌ **No documentation** → ✅ **Comprehensive JavaDoc**
9. ❌ **Empty main method** → ✅ **Full working demonstration**
10. ❌ **No observer pattern** → ✅ **Observer pattern for notifications**

---

**Author**: Parking Lot System Design Team  
**Version**: 2.0  
**Last Updated**: 2024
