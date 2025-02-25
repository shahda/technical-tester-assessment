package assesment.base;

import assesment.utils.ReadData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import assesment.cucumber.cucumberHooks;

import java.io.IOException;
import java.util.HashMap;

public class BaseAPI {

    private static Logger logger = LoggerFactory.getLogger(BaseAPI.class);
    private static Integer statusCode = 0;
    private static String endpoint = null;

    public static Response postResponse;
    public static Response responseGet;
    public static Response deleteResponse;

    //Get Method
    public static void getRequest(String resource) {
        statusCode =0;
        responseGet = null;
        endpoint = resource;
        logger.info("\n URL: " + cucumberHooks.baseURL + endpoint + "\n");
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        try {
            RestAssured.useRelaxedHTTPSValidation();
            RestAssured.baseURI = cucumberHooks.baseURL;
            responseGet = RestAssured.given()
                    .headers(headerMap)
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
            statusCode = responseGet.getStatusCode();
        } catch (Exception e) {
            logger.error("\n Error: " + e.getMessage());
        }
    }

    //Post Method
    public static void postRequest(JSONObject requestBody, String endpoint) throws IOException {
        statusCode =0;
        postResponse = null;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put( "Content-Type", "application/json" );
        try {
            postResponse = RestAssured.given()
                    .headers( headerMap )
                    .body( requestBody.toString())
                    .post( cucumberHooks.baseURL + endpoint )
                    .then()
                    .extract()
                    .response();
            statusCode = postResponse.getStatusCode();
        } catch (Exception e) {
            logger.error( "Error: " + e.getMessage() );
        }
    }

    //Delete Method
    public static void deleteRequest(String endpoint) throws IOException {
        statusCode =0;
        deleteResponse = null;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put( "Content-Type", "application/json" );
        try {
            deleteResponse = RestAssured.given()
                    .headers( headerMap )
                    .delete( cucumberHooks.baseURL + endpoint )
                    .then()
                    .extract()
                    .response();
            statusCode = deleteResponse.getStatusCode();
        } catch (Exception e) {
            logger.error( "Error: " + e.getMessage() );
        }
    }


    //Common Assert methods
    public static void assertResponseStatus(Integer statusCode) {
        logger.info("\n Api resource path " + endpoint + " status: " + BaseAPI.statusCode + "\n");
        Assert.assertEquals(endpoint + " has failed, expected: ", statusCode, BaseAPI.statusCode);
    }

    public static void assertJsonAttributeGetResponse(String attribute, String expectedValue) {
        JsonPath jsonPathEvaluator = responseGet.jsonPath();
        String attributeValue = jsonPathEvaluator.get(attribute);
        logger.info("\n Expected value: " + expectedValue + ", Actual value: " + attributeValue);
        Assert.assertEquals(attributeValue,expectedValue);
    }

    public static void assertJsonIntegerAttributeGetResponse(String attribute, Integer expectedValue) {
        JsonPath jsonPathEvaluator = responseGet.jsonPath();
        Integer attributeValue = jsonPathEvaluator.get(attribute);
        logger.info("\n Expected value: " + expectedValue + ", Actual value: " + attributeValue);
        Assert.assertEquals(attributeValue,expectedValue);
    }
}

