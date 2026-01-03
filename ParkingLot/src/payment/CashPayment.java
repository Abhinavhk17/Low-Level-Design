package payment;

/**
 * Cash payment implementation.
 * Implements Strategy Pattern for payment processing.
 */
public class CashPayment implements PaymentStrategy {
    
    @Override
    public boolean processPayment(double amount) {
        // In real implementation, this would interact with cash register
        System.out.println("Processing cash payment of $" + amount);
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Cash";
    }
}
