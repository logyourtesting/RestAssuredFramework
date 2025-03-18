package airlines;

import airlines.POJO.AirLine;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.Map;

public class Payload {
    private static final Faker faker = new Faker(); // ✅ Singleton Faker instance

    // ✅ 1️⃣ Default Random Payload (No Parameters)
    public static Map<String, Object> generateAddProductPayload() {
        return generateAddProductPayload(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().randomDouble(2, 50, 5000),
                faker.bool().bool(),
                LocalDate.now().plusDays(10).toString(),
                LocalDate.now().plusDays(20).toString(),
                faker.options().option("Breakfast", "Dinner", "Extra Bed", "Late Checkout")
        );
    }

    // ✅ 2️⃣ Overloaded Method: Accepting Specific Values
    public static Map<String, Object> generateAddProductPayload(
            String firstname, String lastname, double totalprice, boolean depositpaid,
            String checkin, String checkout, String additionalneeds) {

        return Map.of(
                "firstname", firstname,
                "lastname", lastname,
                "totalprice", totalprice,
                "depositpaid", depositpaid,
                "bookingdates", Map.of(
                        "checkin", checkin,
                        "checkout", checkout
                ),
                "additionalneeds", additionalneeds
        );
    }

    // ✅ 3️⃣ Overloaded Method: Using POJO as Input
    public static Map<String, Object> generateAddProductPayload(AirLine airline) {
        return Map.of(
                "firstname", airline.getFirstname(),
                "lastname", airline.getLastname(),
                "totalprice", airline.getTotalprice(),
                "depositpaid", airline.isDepositpaid(),
                "bookingdates", airline.getBookingdates(),
                "additionalneeds", airline.getAdditionalneeds()
        );
    }

    // ✅ Generates POJO Test Data
    public static AirLine getPayloadFromPOJO() {
        return AirLine.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().randomDouble(2, 50, 5000))
                .depositpaid(faker.bool().bool())
                .bookingdates(generateBookingDates())
                .additionalneeds(faker.options().option("Breakfast", "Dinner", "Extra Bed", "Late Checkout"))
                .build();
    }

    // ✅ Generates Immutable Booking Dates
    private static Map<String, String> generateBookingDates() {
        return Map.of(
                "checkin", LocalDate.now().plusDays(10).toString(),
                "checkout", LocalDate.now().plusDays(20).toString()
        );
    }

    // ✅ Debugging Method
//    public static void main(String[] args) {
//        AirLine airline = getPayloadFromPOJO();
//
//        System.out.println("🔹 Default Random Payload:\n" + generateAddProductPayload());
//        System.out.println("\n🔹 Custom Payload:\n" +
//                generateAddProductPayload("John", "Doe", 299.99, true, "2025-04-01", "2025-04-10", "Dinner"));
//        System.out.println("\n🔹 Payload from POJO:\n" + generateAddProductPayload(airline));
//    }
}
