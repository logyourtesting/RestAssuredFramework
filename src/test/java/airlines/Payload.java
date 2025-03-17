package airlines;

import airlines.POJO.AirLine;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Payload {
    static Faker faker = new Faker();

    // Method with parameters
    public static Map<String, Object> generateAddProductPayload(
            String firstname, String lastname, double totalprice, boolean depositpaid, String checkin,
            String checkout, String additionalneeds) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", firstname);
        payload.put("lastname", lastname);
        payload.put("totalprice", totalprice);
        payload.put("depositpaid", depositpaid);
        payload.put("additionalneeds", additionalneeds);

        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);  // ✅ Fix: Using actual values
        bookingdates.put("checkout", checkout);
        payload.put("bookingdates", bookingdates);

        return payload;
    }

    public static Map<String, Object> generateAddProductPayload() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", faker.name().firstName());
        payload.put("lastname", faker.name().lastName());
        payload.put("totalprice", faker.number().randomDouble(2, 50, 5000)); // Generates random price
        payload.put("depositpaid", faker.bool().bool()); // Generates true/false for depositPaid

        // ✅ Fix: Use LocalDate.now().plusDays() instead of Faker's future()
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", LocalDate.now().plusDays(10).toString());
        bookingdates.put("checkout", LocalDate.now().plusDays(20).toString());

        payload.put("bookingdates", bookingdates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }
    public static AirLine getPayloadFromPOJO() {
        return AirLine.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().randomDouble(2, 50, 5000))
                .depositpaid(faker.bool().bool())
                .bookingdates(generateBookingDates())
                .additionalneeds("Breakfast")
                .build();  // ✅ Fix: Builder needs `.build()`
    }

    // ✅ Generates booking dates
    private static Map<String, String> generateBookingDates() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", LocalDate.now().plusDays(10).toString());
        bookingdates.put("checkout", LocalDate.now().plusDays(20).toString());
        return bookingdates;
    }
}
