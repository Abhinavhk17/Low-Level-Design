package service;

import model.ClassType;
import model.FitnessClass;
import model.User;
import repository.FitnessClassRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FitnessBookingService {
    private static final AtomicInteger CLASS_SEQUENCE = new AtomicInteger(1);
    private final FitnessClassRepository repository;

    public FitnessBookingService(FitnessClassRepository repository) {
        this.repository = repository;
    }

    public FitnessClass scheduleClass(ClassType type, LocalDateTime startTime, int capacity) {
        String classId = type.name() + "-" + CLASS_SEQUENCE.getAndIncrement();
        FitnessClass fitnessClass = new FitnessClass(classId, type, startTime, capacity);
        repository.save(fitnessClass);
        return fitnessClass;
    }

    public List<FitnessClass> findClasses(ClassType type, LocalDate date) {
        List<FitnessClass> result = new ArrayList<>();
        for (FitnessClass fitnessClass : repository.findAll()) {
            if (fitnessClass.getType() == type && fitnessClass.getStartTime().toLocalDate().equals(date)) {
                result.add(fitnessClass);
            }
        }
        return result;
    }

    public String bookSession(User user, ClassType type, LocalDate date, LocalTime time) {
        FitnessClass fitnessClass = findClass(type, date, time);
        if (fitnessClass == null) {
            return "No " + type + " session found for " + date + " at " + time + ".";
        }
        return bookIntoSession(fitnessClass, user);
    }

    public String cancelSessionBooking(User user, ClassType type, LocalDate date, LocalTime time, LocalDateTime currentTime) {
        FitnessClass fitnessClass = findClass(type, date, time);
        if (fitnessClass == null) {
            return "No " + type + " session found for " + date + " at " + time + ".";
        }
        return cancelBooking(fitnessClass.getId(), user, currentTime);
    }

    private String cancelBooking(String classId, User user, LocalDateTime currentTime) {
        FitnessClass fitnessClass = repository.findById(classId);
        if (fitnessClass == null) {
            return "Class not found.";
        }

        if (!fitnessClass.isBooked(user.getId()) && !fitnessClass.isWaitlisted(user.getId())) {
            return user.getName() + " does not have a booking for " + classId + ".";
        }

        if (fitnessClass.isWaitlisted(user.getId())) {
            fitnessClass.removeFromWaitList(user.getId());
            return user.getName() + " removed from waiting list for " + classId + ".";
        }

        long minutesBeforeClass = Duration.between(currentTime, fitnessClass.getStartTime()).toMinutes();
        if (minutesBeforeClass < 30) {
            return "Cancellation not allowed within 30 minutes of class start.";
        }

        fitnessClass.cancelBooking(user.getId());
        User promotedUser = fitnessClass.promoteFromWaitList();
        return promotedUser != null
                ? user.getName() + " cancelled " + classId + ". " + promotedUser.getName() + " moved from waiting list to confirmed booking."
                : user.getName() + " cancelled " + classId + ".";
    }

    public Collection<FitnessClass> getAllClasses() {
        return repository.findAll();
    }

    private String bookIntoSession(FitnessClass fitnessClass, User user) {
        if (fitnessClass.isBooked(user.getId()) || fitnessClass.isWaitlisted(user.getId())) {
            return user.getName() + " is already registered for " + fitnessClass.getId() + ".";
        }

        if (fitnessClass.hasSpace()) {
            fitnessClass.addBooking(user);
            return user.getName() + " booked in " + fitnessClass.getId() + " on " + fitnessClass.getStartTime() + ".";
        }

        fitnessClass.addBooking(user);
        return user.getName() + " added to waiting list for " + fitnessClass.getId() + " on " + fitnessClass.getStartTime() + ".";
    }

    private FitnessClass findClass(ClassType type, LocalDate date, LocalTime time) {
        LocalDateTime startTime = LocalDateTime.of(date, time);
        for (FitnessClass fitnessClass : repository.findAll()) {
            if (fitnessClass.getType() == type && fitnessClass.getStartTime().equals(startTime)) {
                return fitnessClass;
            }
        }
        return null;
    }
}

