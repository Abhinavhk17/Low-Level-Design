package service;

import entity.Cart;
import entity.Order;
import entity.OrderItem;
import entity.User;
import enums.OrderStatus;
import enums.PaymentMode;
import factory.PaymentFactory;
import interfaces.PaymentStrategy;
import java.util.List;
import java.util.UUID;

public class OrderService {

    public Order placeOrder(User user, Cart cart, PaymentMode mode) {

        List<OrderItem> items = cart.getItems()
                .entrySet()
                .stream()
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .toList();

        Order order = new Order(UUID.randomUUID().toString(), user, items);

        PaymentStrategy payment = PaymentFactory.getPaymentMethod(mode);
        boolean success = payment.pay(order.totalAmount());

        if(!success){
            throw new RuntimeException("Payment failed, order not placed.");
        }

        order.updateStatus(OrderStatus.CONFIRMED);
        return order;
    }
}
