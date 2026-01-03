package panels;

import entity.ParkingLot;
import entity.Ticket;
import interfaces.PricingStrategy;

public class ExitPanel {
    private final String panelId;
    private final PricingStrategy pricingStrategy;

    public ExitPanel(String panelId, PricingStrategy pricingStrategy) {
        this.panelId = panelId;
        this.pricingStrategy = pricingStrategy;
    }

    public double processExit(Ticket ticket) {
        double fare = pricingStrategy.calculatePrice(ticket, System.currentTimeMillis());
        ticket.getParkingSpot().unparkVehicle(ticket.getVehicle());
        return fare;
    }
}
