import entity.ExpenseManager;
import entity.User;
import enums.SplitType;

import java.util.List;

public class SplitwiseApp {

    public static void main(String[] args) {

        User a = new User("1", "A");
        User b = new User("2", "B");
        User c = new User("3", "C");
        User d = new User("4", "D");
        User e = new User("5", "E");

        ExpenseManager manager = new ExpenseManager();

        // Add expenses
        manager.addExpense(a, 23456.0, List.of(a, b, c, d, e), SplitType.EQUAL);
        manager.addExpense(d, 15000.0, List.of(a, b, c, d, e), SplitType.EQUAL);
        manager.addExpense(c, 20000.0, List.of(a, b, c, d, e), SplitType.EQUAL);
        manager.addExpense(e, 10000.0, List.of(a, b, c, d, e), SplitType.EQUAL);

        manager.showMinimizedBalances();
    }
}
