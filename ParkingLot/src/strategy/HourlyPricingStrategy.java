package strategy;

import entity. Ticket;
import interfaces.PricingStrategy;

public class HourlyPricingStrategy implements PricingStrategy {

    private final static int HOURLY_RATE = 20;

    @Override
    public double calculatePrice(Ticket ticket, long exitTime) {
        long durationInMillis = exitTime - ticket.getEntryTime();
        long durationInHours = (durationInMillis / (1000 * 60 * 60));

        // Minimum 1 hour charge
        if (durationInHours == 0) {
            durationInHours = 1;
        }

        return durationInHours * HOURLY_RATE;
    }
}