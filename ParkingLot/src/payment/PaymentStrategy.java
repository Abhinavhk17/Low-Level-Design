package payment;

/**
 * Interface for payment processing.
 * Part of the Strategy Pattern for payment methods.
 * Follows Interface Segregation Principle.
 */
public interface PaymentStrategy {
    /**
     * Process payment for the given amount.
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful, false otherwise
     */
    boolean processPayment(double amount);
    
    /**
     * Get the name of the payment method.
     * 
     * @return Payment method name
     */
    String getPaymentMethodName();
}
