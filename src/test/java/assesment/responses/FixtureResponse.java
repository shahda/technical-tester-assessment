package assesment.responses;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import assesment.base.BaseAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.core.Is.is;

public class FixtureResponse extends BaseAPI {

    private static Logger logger = LoggerFactory.getLogger(FixtureResponse.class);

    public static void assertSuccesfullFixtureId(String id) {
        assertJsonAttributeGetResponse("fixtureId",id);
    }

    public static void assertFixtureIdResponse(String id) {
        if(BaseAPI.responseGet.getBody().asString().equals("Fixture not found"))
        {
            BaseAPI.assertResponseStatus(404);
            logger.info ("\n Fixture not found");
        }
        else if((BaseAPI.responseGet.statusCode())==200)
        {
            assertSuccesfullFixtureId(id);
            logger.info ("\n Fixture successfully found");
        }
        else {
            Assert.assertFalse("there is issue in API",TRUE);
        }
    }
    public static void assertAllFixtures(int fixtureArraySize) {
        JsonPath jsonPathEvaluator = responseGet.jsonPath();
        logger.info("\n The actual fixture is of size " + jsonPathEvaluator.getList("$").size());
        Assert.assertEquals(jsonPathEvaluator.getList("$").size(),fixtureArraySize);
    }

    public static void assertFixturesById() {
        JsonPath jsonPathEvaluator = responseGet.jsonPath();
        for(int i= 0;i <jsonPathEvaluator.getList("$").size(); i++) {
            logger.info("\n The fixture ID for "+(i+1)+ " element is " +jsonPathEvaluator.getString("fixtureId["+i+"]"));
            Assert.assertNotNull(jsonPathEvaluator.getString("fixtureId["+i+"]"));
            Assert.assertNotEquals("",jsonPathEvaluator.getString("fixtureId["+i+"]"));
            Assert.assertNotEquals(" ",jsonPathEvaluator.getString("fixtureId["+i+"]"));
        }
    }

    public static void postResponseMessage(String message) throws IOException {
        Assert.assertEquals(message,BaseAPI.postResponse.getBody().asString());
    }
    public static void getResponseMessage(String message) throws IOException {
        Assert.assertEquals(message,BaseAPI.responseGet.getBody().asString());
    }
    public static void deleteResponseMessage(String message) throws IOException {
        logger.info("The delete response message is "+BaseAPI.deleteResponse.getBody().asString());
        Assert.assertEquals(message,BaseAPI.deleteResponse.getBody().asString());
    }

    public static void teamArrayValidation(String teamId) throws IOException {
        JsonPath jsonPathEvaluator = responseGet.jsonPath();
        List<Map<String,String>> teams =  jsonPathEvaluator.get("footballFullState.teams");
        Assert.assertEquals(teamId,teams.get(0).get("teamId"));
        logger.info("The first item in teams is "+teams.get(0).get("teamId"));
    }
}
