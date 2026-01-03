package entity;

/**
 * Represents a parking ticket issued to a vehicle.
 * Immutable class following best practices.
 */
public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final long entryTime;

    /**
     * Creates a new parking ticket.
     * 
     * @param vehicle The vehicle for which the ticket is issued
     * @param parkingSpot The assigned parking spot
     * @throws IllegalArgumentException if vehicle or parkingSpot is null
     */
    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking spot cannot be null");
        }
        this.ticketId = java.util.UUID.randomUUID().toString();
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
        this.entryTime = System.currentTimeMillis();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle.getLicensePlate() +
                ", spotId=" + parkingSpot.getSpotId() +
                ", entryTime=" + entryTime +
                '}';
    }
}
