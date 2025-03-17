package utils;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RandomDataGenerator {

    private static final Faker faker = new Faker(); // Singleton Faker instance

    // Generate random first name
    public static String getFirstName() {
        return faker.name().firstName();
    }

    // Generate random last name
    public static String getLastName() {
        return faker.name().lastName();
    }

    // Generate random price
    public static double getTotalPrice() {
        return faker.number().randomDouble(2, 50, 5000); // Price between 50 and 5000
    }

    // Generate random deposit status
    public static boolean isDepositPaid() {
        return faker.bool().bool();
    }

    // Generate check-in date (10 days from now)
    public static String getCheckInDate() {
        return LocalDate.now().plusDays(10).toString();
    }

    // Generate check-out date (20 days from now)
    public static String getCheckOutDate() {
        return LocalDate.now().plusDays(20).toString();
    }

    // Generate random additional needs (e.g., Breakfast)
    public static String getAdditionalNeeds() {
        return "Breakfast";
    }

    // Combine all random fields into a single payload
    public static Map<String, Object> generateRandomPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", getFirstName());
        payload.put("lastname", getLastName());
        payload.put("totalprice", getTotalPrice());
        payload.put("depositpaid", isDepositPaid());

        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", getCheckInDate());
        bookingdates.put("checkout", getCheckOutDate());
        payload.put("bookingdates", bookingdates);

        payload.put("additionalneeds", getAdditionalNeeds());

        return payload;
    }

}