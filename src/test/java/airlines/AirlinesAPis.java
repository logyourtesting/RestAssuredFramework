package airlines;

import airlines.POJO.AirLine;
import io.restassured.response.Response;
import restUtils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class AirlinesAPis {
    public  Response createAirLien(Map<String,Object> CreateAirLLinePayload)
    {
        String endPoint=(String) Base.data.get("baseUri");
        return RestUtils.performPost(endPoint, CreateAirLLinePayload, new HashMap<>());
    }

    public  Response createAirLien(AirLine CreateAirLLinePayload)
    {
        String endPoint=(String) Base.data.get("baseUri");
        return RestUtils.performPost(endPoint, CreateAirLLinePayload, new HashMap<>());
    }
}
