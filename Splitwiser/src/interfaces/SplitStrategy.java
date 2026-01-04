package interfaces;

import entity.User;

import java.util.List;
import java.util.Map;

/**
 * Strategy interface for different expense splitting methods.
 */
public interface SplitStrategy {
    /**
     * Splits an amount among users according to the strategy.
     *
     * @param amount the total amount to split
     * @param users  the list of users to split among
     * @return a map of user to their share amount
     */
    Map<User, Double> split(double amount, List<User> users);
}

