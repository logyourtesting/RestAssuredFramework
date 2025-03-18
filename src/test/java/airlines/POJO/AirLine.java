package airlines.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// ✅ Lombok Annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AirLine {

    private static final Faker faker = new Faker(); // ✅ Singleton Faker instance

    @JsonProperty("firstname")  // ✅ Maps JSON field "firstname"
    private String firstname;

    @JsonProperty("lastname")  // ✅ Maps JSON field "lastname"
    private String lastname;

    @JsonProperty("totalprice")  // ✅ Maps JSON field "totalprice"
    private double totalprice;

    @JsonProperty("depositpaid")  // ✅ Maps JSON field "depositpaid"
    private boolean depositpaid;

    @JsonProperty("bookingdates")  // ✅ Maps JSON field "bookingdates"
    private Map<String, String> bookingdates;

    @JsonProperty("additionalneeds")  // ✅ Maps JSON field "additionalneeds"
    private String additionalneeds;

    // ✅ Static Factory Method to Generate Test Data
    public static AirLine generateRandomData() {
        return AirLine.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().randomDouble(2, 50, 5000)) // Generates random price
                .depositpaid(faker.bool().bool()) // Generates random boolean
                .bookingdates(generateBookingDates()) // Generates booking dates
                .additionalneeds(faker.options().option("Breakfast", "Dinner", "Extra Bed", "Late Checkout"))
                .build();
    }

    // ✅ Generates booking dates
    private static Map<String, String> generateBookingDates() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", LocalDate.now().plusDays(10).toString());
        bookingdates.put("checkout", LocalDate.now().plusDays(20).toString());
        return bookingdates;
    }

    // ✅ Normalize booking dates (to avoid formatting mismatches)
    public void normalizeDates() {
        if (bookingdates != null) {
            bookingdates.put("checkin", LocalDate.parse(bookingdates.get("checkin")).toString());
            bookingdates.put("checkout", LocalDate.parse(bookingdates.get("checkout")).toString());
        }
    }
}
