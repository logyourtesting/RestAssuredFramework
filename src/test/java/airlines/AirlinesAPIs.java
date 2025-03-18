package airlines;

import airlines.POJO.AirLine;
import io.restassured.response.Response;
import restUtils.RestUtils;

import java.util.Map;

public class AirlinesAPIs {

    // ✅ Creates an airline using a Map payload
    public Response createAirLine(Map<String, Object> createAirLinePayload) {
        String endPoint = getBaseUri();
        return RestUtils.post(endPoint, createAirLinePayload, Map.of());
    }

    // ✅ Creates an airline using an AirLine POJO
    public Response createAirLine(AirLine createAirLinePayload) {
        String endPoint = getBaseUri();
        return RestUtils.post(endPoint, createAirLinePayload, Map.of());
    }

    // ✅ Retrieves the base URL safely
    private String getBaseUri() {
        Object uri = (Base.data != null) ? Base.data.get("baseUri") : null;
        return (uri != null) ? uri.toString() : "https://default-api-url.com"; // 🔹 Replace with actual default API URL
    }
}
