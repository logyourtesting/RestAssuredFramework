package airlines;

import airlines.POJO.AirLine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcomTest extends AirlinesAPIs {

//    @Test
//    public void CreateAirLineAndVerifyResponse() throws JsonProcessingException {
//        AirLine payload = AirLine.generateRandomData();
//        Response response = createAirLine(payload);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonResponse = objectMapper.readTree(response.getBody().asString());
//
//        if (jsonResponse.has("booking")) {
//            JsonNode bookingNode = jsonResponse.get("booking");
//            AirLine createAirLineResponse = objectMapper.treeToValue(bookingNode, AirLine.class);
//
//            // ✅ Compare total price with tolerance (floating-point precision fix)
//            double expectedPrice = payload.getTotalprice();
//            double actualPrice = createAirLineResponse.getTotalprice();
//            Assert.assertTrue(Math.abs(expectedPrice - actualPrice) < 1.0,
//                    "❌ Total Price Mismatch: Expected [" + expectedPrice + "], but found [" + actualPrice + "]");
//
//            // ✅ Other assertions remain unchanged
//            Assert.assertEquals(createAirLineResponse.getFirstname(), payload.getFirstname(), "❌ Firstname Mismatch");
//            Assert.assertEquals(createAirLineResponse.getLastname(), payload.getLastname(), "❌ Lastname Mismatch");
//            Assert.assertEquals(createAirLineResponse.isDepositpaid(), payload.isDepositpaid(), "❌ Deposit Paid Mismatch");
//            Assert.assertEquals(createAirLineResponse.getBookingdates(), payload.getBookingdates(), "❌ Booking Dates Mismatch");
//            Assert.assertEquals(createAirLineResponse.getAdditionalneeds(), payload.getAdditionalneeds(), "❌ Additional Needs Mismatch");
//        } else {
//            Assert.fail("❌ Response does NOT contain 'booking' object!");
//        }
//    }

    @Test
    public void CreateAirLineAndVerifyResponse() throws JsonProcessingException {
        AirLine payload = AirLine.generateRandomData();
        Response response = createAirLine(payload);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response.getBody().asString());

        if (jsonResponse.has("booking")) {
            JsonNode bookingNode = jsonResponse.get("booking");
            AirLine createAirLineResponse = objectMapper.treeToValue(bookingNode, AirLine.class);

            // ✅ Compare the entire POJO using `Assert.assertEquals()`
            Assert.assertEquals(createAirLineResponse, payload, "❌ API response does not match request payload!");

        } else {
            Assert.fail("❌ Response does NOT contain 'booking' object!");
        }
    }



}
