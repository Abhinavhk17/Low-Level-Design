package payment;

/**
 * Digital wallet payment implementation (e.g., PayPal, Google Pay, Apple Pay).
 * Implements Strategy Pattern for payment processing.
 */
public class DigitalWalletPayment implements PaymentStrategy {
    private final String walletId;
    
    public DigitalWalletPayment(String walletId) {
        this.walletId = walletId;
    }
    
    @Override
    public boolean processPayment(double amount) {
        // In real implementation, this would interact with wallet API
        System.out.println("Processing digital wallet payment of $" + amount + 
                         " using wallet ID: " + walletId);
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Digital Wallet";
    }
}
