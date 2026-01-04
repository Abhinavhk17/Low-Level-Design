package entity;

import java.util.Map;

/**
 * Represents an expense in the Splitwise application.
 */
public class Expense {
    private final User payer;
    private final Map<User, Double> shares;

    public Expense(User payer, Map<User, Double> shares) {
        this.payer = payer;
        this.shares = shares;
    }

    public User getPayer() {
        return payer;
    }

    public Map<User, Double> getShares() {
        return shares;
    }
}
