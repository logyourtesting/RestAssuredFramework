package restUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportManager;

import java.util.Map;

public class RestUtils {

    // ✅ Generic Method for Creating a Request Specification
    private static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpec = RestAssured
                .given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON);

        if (requestPayload != null) {
            requestSpec.body(requestPayload);
        }

        return requestSpec;
    }

    // ✅ Logs Request Details
    private static void printRequestLogReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);

        ExtentReportManager.logInfoDetail("📌 **End Point:** " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetail("📌 **Method:** " + queryableRequestSpecification.getMethod());

        if (queryableRequestSpecification.getHeaders() != null) {
            ExtentReportManager.logInfoDetail("📌 **Headers:** ");
            ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        }

        if (queryableRequestSpecification.getBody() != null) {
            ExtentReportManager.logInfoDetail("📌 **Request Body:** ");
            ExtentReportManager.logJSON(queryableRequestSpecification.getBody());
        }
    }

    // ✅ Logs Response Details
    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetail("✅ **Response Status:** " + response.statusCode());

        if (response.getHeaders() != null) {
            ExtentReportManager.logInfoDetail("✅ **Response Headers:** ");
            ExtentReportManager.logHeaders(response.getHeaders().asList());
        }

        if (response.getBody() != null) {
            ExtentReportManager.logInfoDetail("✅ **Response Body:** ");
            ExtentReportManager.logJSON(response.getBody().prettyPrint());
        }
    }

    // ✅ Generic Method to Handle All HTTP Methods
    private static Response performRequest(String method, String endPoint, Object requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response;

        switch (method.toUpperCase()) {
            case "POST":
                response = requestSpecification.post();
                break;
            case "GET":
                response = requestSpecification.get();
                break;
            case "PUT":
                response = requestSpecification.put();
                break;
            case "DELETE":
                response = requestSpecification.delete();
                break;
            default:
                throw new IllegalArgumentException("❌ Invalid HTTP method: " + method);
        }

        printRequestLogReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    // ✅ Public Methods to Call APIs
    public static Response post(String endPoint, Object requestPayload, Map<String, String> headers) {
        return performRequest("POST", endPoint, requestPayload, headers);
    }

    public static Response get(String endPoint, Map<String, String> headers) {
        return performRequest("GET", endPoint, null, headers);
    }

    public static Response put(String endPoint, Object requestPayload, Map<String, String> headers) {
        return performRequest("PUT", endPoint, requestPayload, headers);
    }

    public static Response delete(String endPoint, Map<String, String> headers) {
        return performRequest("DELETE", endPoint, null, headers);
    }
}
