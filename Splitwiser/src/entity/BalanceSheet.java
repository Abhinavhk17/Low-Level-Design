package entity;

import java.util.*;

public class BalanceSheet {

    // balances[A][B] => A owes B
    private final Map<User, Map<User, Double>> balances = new HashMap<>();
    private final Set<User> allUsers = new HashSet<>();

    public void addExpense(Expense expense) {
        User payer = expense.getPayer();
        allUsers.add(payer);

        for (Map.Entry<User, Double> entry : expense.getShares().entrySet()) {
            User user = entry.getKey();
            double amount = entry.getValue();

            allUsers.add(user);

            if (user.equals(payer)) continue;

            balances
                    .computeIfAbsent(user, _ -> new HashMap<>())
                    .merge(payer, amount, Double::sum);
        }
    }

    // Calculate net balance for a user (positive = owed money, negative = owes money)
    private double getNetBalance(User user) {
        double net = 0.0;

        // Money this user owes to others
        Map<User, Double> owes = balances.getOrDefault(user, Map.of());
        for (double amount : owes.values()) {
            net -= amount;
        }

        // Money others owe to this user
        for (Map.Entry<User, Map<User, Double>> entry : balances.entrySet()) {
            if (entry.getKey().equals(user)) continue;
            net += entry.getValue().getOrDefault(user, 0.0);
        }

        return net;
    }

    // Show truly simplified balances (minimum transactions)
    public void showMinimizedBalances() {
        // Calculate net balance for each user
        Map<User, Double> netBalances = new HashMap<>();
        for (User user : allUsers) {
            double net = getNetBalance(user);
            if (Math.abs(net) > 0.01) { // Ignore very small amounts due to floating point
                netBalances.put(user, net);
            }
        }

        // Separate into debtors and creditors
        List<Map.Entry<User, Double>> debtors = new ArrayList<>();
        List<Map.Entry<User, Double>> creditors = new ArrayList<>();

        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() < 0) {
                debtors.add(entry);
            } else if (entry.getValue() > 0) {
                creditors.add(entry);
            }
        }

        // Sort by amount (descending)
        debtors.sort(Comparator.comparingDouble(Map.Entry::getValue));
        creditors.sort(Comparator.comparingDouble(Map.Entry<User, Double>::getValue).reversed());

        // Match debtors with creditors using greedy algorithm
        int debtorIndex = 0;
        int creditorIndex = 0;

        while (debtorIndex < debtors.size() && creditorIndex < creditors.size()) {
            User debtor = debtors.get(debtorIndex).getKey();
            double debtAmount = -debtors.get(debtorIndex).getValue();

            User creditor = creditors.get(creditorIndex).getKey();
            double creditAmount = creditors.get(creditorIndex).getValue();

            double settleAmount = Math.min(debtAmount, creditAmount);

            System.out.printf("%s owes %s : %.2f%n",
                    debtor.getId(), creditor.getId(), settleAmount);

            debtors.get(debtorIndex).setValue(debtors.get(debtorIndex).getValue() + settleAmount);
            creditors.get(creditorIndex).setValue(creditors.get(creditorIndex).getValue() - settleAmount);

            if (Math.abs(debtors.get(debtorIndex).getValue()) < 0.01) debtorIndex++;
            if (Math.abs(creditors.get(creditorIndex).getValue()) < 0.01) creditorIndex++;
        }
    }
}
