package entity;

import enums.SplitType;
import factory.SplitFactory;
import interfaces.SplitStrategy;

import java.util.List;
import java.util.Map;

/**
 * Manages expenses and coordinates the splitting logic.
 */
public class ExpenseManager {

    private final BalanceSheet balanceSheet = new BalanceSheet();

    /**
     * Adds a new expense and updates the balance sheet.
     *
     * @param payer        the user who paid for the expense
     * @param amount       the total amount of the expense
     * @param participants the list of users involved in the expense
     * @param splitType    the type of split (EQUAL, EXACT, PERCENTAGE)
     */
    public void addExpense(User payer, double amount, List<User> participants, SplitType splitType) {
        SplitStrategy strategy = SplitFactory.getStrategy(splitType);
        Map<User, Double> shares = strategy.split(amount, participants);

        Expense expense = new Expense(payer, shares);
        balanceSheet.addExpense(expense);
    }

    /**
     * Displays the minimized balances with the fewest transactions.
     */
    public void showMinimizedBalances() {
        balanceSheet.showMinimizedBalances();
    }
}
