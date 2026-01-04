import entity.*;
import service.OrderService;

import java.util.List;

public class SwiggyApp {
    public static void main(String[] args) {

        User user = new User("U1", "Abhi");

        MenuItem pizza = new MenuItem("M1", "Pizza", 250.0);
        MenuItem burger = new MenuItem("M2", "Burger", 150.0);


        Menu menu = new Menu(List.of(pizza, burger));

        Restaurant restaurant = new Restaurant("R1", "Foodies", menu);

        Cart cart = new Cart(user);
        cart.addItem(pizza, 2);
        cart.addItem(burger, 1);

        OrderService orderService = new OrderService();

        Order order = orderService.placeOrder(user, cart, enums.PaymentMode.UPI);

        // ✅ SINGLE CONSOLIDATED PRINT
        System.out.println("\n--- ORDER DETAILS ---");
        System.out.println("Customer   : " + user.getName());
        System.out.println("Restaurant : " + restaurant.getName());
        System.out.println("Items:");

        double total = 0;
        for (OrderItem item : order.getItems()) {
            double cost = item.getCost();
            total += cost;

            System.out.println(
                    "- " + item.getItem().getName() +
                            " x " + item.getQuantity() +
                            " = ₹" + cost
            );
        }

        System.out.println("Total Amount : ₹" + total);


    }
}
