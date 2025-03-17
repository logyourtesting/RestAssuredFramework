package airlines.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// from Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AirLine {
    private static final Faker faker = new Faker(new java.util.Random(1234)); // ✅ Fixed Seed

    private String firstname=faker.name().firstName();
    private String lastname=faker.name().lastName();
    private double totalprice=faker.number().randomDouble(2, 50, 5000);
    private boolean depositpaid=faker.bool().bool();
    private Map<String, String> bookingdates=generateBookingDates();
    private String additionalneeds="Breakfast";


    // ✅ Generates booking dates
    private static Map<String, String> generateBookingDates() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", LocalDate.now().plusDays(10).toString());
        bookingdates.put("checkout", LocalDate.now().plusDays(20).toString());
        return bookingdates;
    }
}

