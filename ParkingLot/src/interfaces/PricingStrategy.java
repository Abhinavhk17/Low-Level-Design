package interfaces;

import entity.Ticket;

public interface PricingStrategy {
    double calculatePrice(Ticket ticket, long exitTime);
}
