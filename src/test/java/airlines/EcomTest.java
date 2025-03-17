package airlines;

import airlines.POJO.AirLine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcomTest extends AirlinesAPis {
    AirLine payload;
//    @Test
//    public void createAirLine() {
//
//        AirLine payload=new AirLine();
//        Response response = createAirLien(payload);
//        Assert.assertEquals(response.statusCode(), 200, "Code is not matched");
//
//    }

    @Test
    public void CreateAirLineAndVerifyResponse() throws JsonProcessingException {
         payload = new AirLine();
        Response response = createAirLien(payload);

        ObjectMapper objectMapper=new ObjectMapper();
        AirLine createAirLineResponse=objectMapper.readValue(response.getBody().asString(),AirLine.class);
        System.out.println(createAirLineResponse);
        System.out.println(payload);
        Assert.assertEquals(createAirLineResponse,payload);

    }
}
