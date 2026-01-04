package entity;

public class Restaurant {
    private final String id;
    private final String name;
    private final Menu menu;

    public Restaurant(String id, String name, Menu menu) {
        this.id = id;
        this.name = name;
        this.menu  = menu;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }
}
