package entity;

import enums.OrderStatus;

import java.util.List;

public class Order {
    private final String orderId;
    private final User user;

    public List<OrderItem> getItems() {
        return items;
    }

    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, User user, List<OrderItem> items) {
        this.orderId = orderId;
        this.user = user;
        this.items = items;
        this.status = OrderStatus.CREATED;
    }

    public double totalAmount() {
        return items.stream().mapToDouble(OrderItem::getCost).sum();
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

}
