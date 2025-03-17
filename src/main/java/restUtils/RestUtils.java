package restUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentRepostManager;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload, Map<String, String> headers) {
        return RestAssured
                .given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload);
    }
    private static void printRequestLogReport(RequestSpecification  requestSpecification)
    {
        QueryableRequestSpecification  queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
        ExtentRepostManager.logInfoDetail("End point is "+queryableRequestSpecification.getBaseUri());
        ExtentRepostManager.logInfoDetail("Method is "+queryableRequestSpecification.getMethod());
        ExtentRepostManager.logInfoDetail("Headers is ");
        ExtentRepostManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentRepostManager.logInfoDetail("Request Body is ");
        ExtentRepostManager.logJSON(queryableRequestSpecification.getBody());
    }

    private  static  void printResponseLogInReport(Response response)
    {
        ExtentRepostManager.logInfoDetail("Response status  is "+response.statusCode());
        ExtentRepostManager.logInfoDetail("Response Headers is ");
        ExtentRepostManager.logHeaders(response.getHeaders().asList());
        ExtentRepostManager.logInfoDetail("Response Body is ");
        ExtentRepostManager.logJSON(response.getBody().prettyPrint());
    }

    public static Response performPost(String endPoint, Map<String, Object> requestPayload, Map<String, String> headers) {

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);

        Response response = requestSpecification.post();

        printRequestLogReport(requestSpecification);
        printResponseLogInReport(response);
        return  response;
    }

    public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);

        Response response = requestSpecification.post();

        printRequestLogReport(requestSpecification);
        printResponseLogInReport(response);
        return  response;
    }

    public static Response performPost(String endPoint, Object requestPayloadPOJO, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayloadPOJO, headers);

        Response response = requestSpecification.post();

        printRequestLogReport(requestSpecification);
        printResponseLogInReport(response);
        return  response;
    }
}
