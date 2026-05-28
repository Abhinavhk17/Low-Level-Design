package model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FitnessClass {
    private final String id;
    private final ClassType type;
    private final LocalDateTime startTime;
    private final int capacity;
    private final Map<String, User> bookedUsers = new LinkedHashMap<>();
    private final Queue<User> waitList = new LinkedList<>();

    public FitnessClass(String id, ClassType type, LocalDateTime startTime, int capacity) {
        this.id = id;
        this.type = type;
        this.startTime = startTime;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public ClassType getType() {
        return type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isBooked(String userId) {
        return bookedUsers.containsKey(userId);
    }

    public boolean isWaitlisted(String userId) {
        for (User user : waitList) {
            if (user.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSpace() {
        return bookedUsers.size() < capacity;
    }

    public void addBooking(User user) {
        if (isBooked(user.getId()) || isWaitlisted(user.getId())) {
            return;
        }
        if (hasSpace()) {
            bookedUsers.put(user.getId(), user);
            return;
        }
        waitList.offer(user);
    }

    public void cancelBooking(String userId) {
        bookedUsers.remove(userId);
    }

    public void removeFromWaitList(String userId) {
        waitList.removeIf(user -> user.getId().equals(userId));
    }

    public User promoteFromWaitList() {
        User nextUser = waitList.poll();
        if (nextUser != null) {
            bookedUsers.put(nextUser.getId(), nextUser);
        }
        return nextUser;
    }

    public Collection<User> getBookedUsers() {
        return bookedUsers.values();
    }

    public Queue<User> getWaitList() {
        return waitList;
    }
}

