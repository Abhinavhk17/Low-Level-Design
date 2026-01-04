package strategy;

import entity.User;
import interfaces.SplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Strategy for splitting expenses equally among all participants.
 */
public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public Map<User, Double> split(double amount, List<User> users) {
        double sharePerUser = amount / users.size();

        Map<User, Double> shares = new HashMap<>();
        for (User user : users) {
            shares.put(user, sharePerUser);
        }
        return shares;
    }
}
