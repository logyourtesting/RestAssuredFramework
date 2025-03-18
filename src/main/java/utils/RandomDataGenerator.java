package utils;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.Map;

public class RandomDataGenerator {

    private static final Faker faker = new Faker(); // ✅ Singleton Faker instance

    // ✅ Generate random first name
    public static String getFirstName() {
        return faker.name().firstName();
    }

    // ✅ Generate random last name
    public static String getLastName() {
        return faker.name().lastName();
    }

    // ✅ Generate random total price (50 - 5000)
    public static double getTotalPrice() {
        return faker.number().randomDouble(2, 50, 5000);
    }

    // ✅ Generate random deposit status (true/false)
    public static boolean isDepositPaid() {
        return faker.bool().bool();
    }

    // ✅ Generate random check-in and check-out dates
    public static String getCheckInDate() {
        return LocalDate.now().plusDays(faker.number().numberBetween(5, 15)).toString();
    }

    public static String getCheckOutDate() {
        return LocalDate.now().plusDays(faker.number().numberBetween(16, 25)).toString();
    }

    // ✅ Generate random additional needs
    public static String getAdditionalNeeds() {
        return faker.options().option("Breakfast", "Dinner", "Extra Bed", "Late Checkout");
    }

    // ✅ Generate a consistent random payload in one method
    public static Map<String, Object> generateRandomPayload() {
        return Map.of(
                "firstname", getFirstName(),
                "lastname", getLastName(),
                "totalprice", getTotalPrice(),
                "depositpaid", isDepositPaid(),
                "bookingdates", Map.of(
                        "checkin", getCheckInDate(),
                        "checkout", getCheckOutDate()
                ),
                "additionalneeds", getAdditionalNeeds()
        );
    }

}
