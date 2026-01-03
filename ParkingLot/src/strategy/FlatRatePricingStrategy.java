package strategy;

import entity.Ticket;
import interfaces.PricingStrategy;

/**
 * Flat rate pricing strategy - charges a fixed amount regardless of duration.
 * Another implementation of Strategy Pattern.
 * Demonstrates Open/Closed Principle - new strategy added without modifying existing code.
 */
public class FlatRatePricingStrategy implements PricingStrategy {

    private static final double FLAT_RATE = 50.0;

    /**
     * Calculate parking fee using flat rate.
     * 
     * @param ticket The parking ticket
     * @param exitTime The time when vehicle exits (in milliseconds)
     * @return The flat rate fee
     */
    @Override
    public double calculatePrice(Ticket ticket, long exitTime) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        return FLAT_RATE;
    }

    public static double getFlatRate() {
        return FLAT_RATE;
    }
}
