package payment;

/**
 * Credit/Debit card payment implementation.
 * Implements Strategy Pattern for payment processing.
 */
public class CardPayment implements PaymentStrategy {
    private final String cardNumber;
    
    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public boolean processPayment(double amount) {
        // In real implementation, this would interact with payment gateway
        System.out.println("Processing card payment of $" + amount + 
                         " using card ending in " + cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Card";
    }
}
