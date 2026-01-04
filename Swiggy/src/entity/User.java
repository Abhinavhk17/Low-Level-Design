package entity;

public class User {
    private final String id;

    public String getName() {
        return name;
    }

    private final String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
