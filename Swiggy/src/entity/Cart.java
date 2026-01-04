package entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final User user;
    private final Map<MenuItem, Integer> items = new HashMap<>();

    public Cart(User user) {
        this.user = user;
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }
}
