package entity;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final String entryTime;

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public ParkingTicket(String entryTime, ParkingSpot parkingSpot, Vehicle vehicle, String ticketId) {
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
        this.ticketId = ticketId;
    }
}
