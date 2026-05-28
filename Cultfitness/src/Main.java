import model.ClassType;
import model.FitnessClass;
import model.User;
import repository.FitnessClassRepository;
import service.FitnessBookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static void main(String[] args) {
		FitnessClassRepository repository = new FitnessClassRepository();
		FitnessBookingService bookingService = new FitnessBookingService(repository);

		LocalDate yogaDay = LocalDate.now().plusDays(1);
		LocalDate gymDay = LocalDate.now().plusDays(2);

		bookingService.scheduleClass(ClassType.YOGA, LocalDateTime.of(yogaDay, LocalTime.of(7, 0)), 1);
		bookingService.scheduleClass(ClassType.YOGA, LocalDateTime.of(yogaDay, LocalTime.of(8, 0)), 1);
		bookingService.scheduleClass(ClassType.GYM, LocalDateTime.of(gymDay, LocalTime.of(9, 0)), 2);
		bookingService.scheduleClass(ClassType.DANCE, LocalDateTime.of(gymDay, LocalTime.of(18, 0)), 2);

		User alice = new User("U1", "Alice");
		User bob = new User("U2", "Bob");
		User charlie = new User("U3", "Charlie");

		System.out.println("Yoga sessions on " + yogaDay + ":");
		for (FitnessClass fitnessClass : bookingService.findClasses(ClassType.YOGA, yogaDay)) {
			printClass(fitnessClass);
		}
		System.out.println();

		System.out.println(bookingService.bookSession(alice, ClassType.YOGA, yogaDay, LocalTime.of(7, 0)));
		System.out.println(bookingService.bookSession(bob, ClassType.YOGA, yogaDay, LocalTime.of(7, 0)));
		System.out.println(bookingService.bookSession(charlie, ClassType.YOGA, yogaDay, LocalTime.of(7, 0)));
		System.out.println(bookingService.bookSession(charlie, ClassType.YOGA, yogaDay, LocalTime.of(8, 0)));
		System.out.println(bookingService.cancelSessionBooking(bob, ClassType.YOGA, yogaDay, LocalTime.of(7, 0), LocalDateTime.now()));

		System.out.println();

		System.out.println("All scheduled sessions:");
		for (FitnessClass fitnessClass : bookingService.getAllClasses()) {
			printClass(fitnessClass);
		}
	}

	private static void printClass(FitnessClass fitnessClass) {
		System.out.println("Class " + fitnessClass.getId()
				+ " | " + fitnessClass.getType()
				+ " | " + fitnessClass.getStartTime().format(FORMATTER)
				+ " | capacity=" + fitnessClass.getCapacity()
				+ " | booked=" + fitnessClass.getBookedUsers().size()
				+ " | waitlist=" + fitnessClass.getWaitList().size());
	}
}
