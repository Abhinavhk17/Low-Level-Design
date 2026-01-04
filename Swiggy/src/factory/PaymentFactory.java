package factory;

import enums.PaymentMode;
import interfaces.PaymentStrategy;

public class PaymentFactory {
    public static PaymentStrategy getPaymentMethod(PaymentMode method) {
        switch (method) {
            case UPI:
                return new payment.UpiPayment();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + method);
        }
    }
}
