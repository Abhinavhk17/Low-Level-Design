package strategy;

import entity.Ticket;
import interfaces.PricingStrategy;

/**
 * Hourly pricing strategy for parking fees.
 * Implements Strategy Pattern for pricing calculation.
 * Follows Open/Closed Principle - can add new pricing strategies without modifying existing code.
 */
public class HourlyPricingStrategy implements PricingStrategy {

    private static final int HOURLY_RATE = 20;

    /**
     * Calculate parking fee based on hourly rate.
     * 
     * @param ticket The parking ticket
     * @param exitTime The time when vehicle exits (in milliseconds)
     * @return The calculated parking fee
     */
    @Override
    public double calculatePrice(Ticket ticket, long exitTime) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        
        long durationInMillis = exitTime - ticket.getEntryTime();
        if (durationInMillis < 0) {
            throw new IllegalArgumentException("Exit time cannot be before entry time");
        }
        
        // Calculate hours, rounding up (minimum charge for any fraction of an hour)
        long durationInHours = (durationInMillis + (1000 * 60 * 60 - 1)) / (1000 * 60 * 60);
        return durationInHours * HOURLY_RATE;
    }

    public static int getHourlyRate() {
        return HOURLY_RATE;
    }
}
