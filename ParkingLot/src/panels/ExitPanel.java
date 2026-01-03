package panels;

import entity.Ticket;
import exception.InvalidTicketException;
import interfaces.PricingStrategy;
import payment.PaymentStrategy;

/**
 * Exit panel for processing vehicle exits and payments.
 * Follows Single Responsibility Principle - handles vehicle exit and payment.
 * Thread-safe implementation.
 */
public class ExitPanel {
    private final String panelId;
    private final PricingStrategy pricingStrategy;

    public ExitPanel(String panelId, PricingStrategy pricingStrategy) {
        if (panelId == null || panelId.trim().isEmpty()) {
            throw new IllegalArgumentException("Panel ID cannot be null or empty");
        }
        if (pricingStrategy == null) {
            throw new IllegalArgumentException("Pricing strategy cannot be null");
        }
        this.panelId = panelId;
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Process vehicle exit and calculate fare.
     * Thread-safe method.
     * 
     * @param ticket The parking ticket
     * @return The calculated fare amount
     * @throws InvalidTicketException if ticket is null or invalid
     */
    public synchronized double processExit(Ticket ticket) {
        if (ticket == null) {
            throw new InvalidTicketException("Ticket cannot be null");
        }
        if (ticket.getParkingSpot() == null) {
            throw new InvalidTicketException("Invalid ticket: no parking spot assigned");
        }
        
        double fare = pricingStrategy.calculatePrice(ticket, System.currentTimeMillis());
        ticket.getParkingSpot().unparkVehicle();
        return fare;
    }

    /**
     * Process vehicle exit with payment.
     * 
     * @param ticket The parking ticket
     * @param paymentStrategy The payment method to use
     * @return true if payment successful, false otherwise
     * @throws InvalidTicketException if ticket is invalid
     */
    public synchronized boolean processExitWithPayment(Ticket ticket, PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        
        double fare = processExit(ticket);
        return paymentStrategy.processPayment(fare);
    }

    public String getPanelId() {
        return panelId;
    }
}
