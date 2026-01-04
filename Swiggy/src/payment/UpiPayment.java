package payment;

import interfaces.PaymentStrategy;

public class UpiPayment implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {
        return true;
    }
}
