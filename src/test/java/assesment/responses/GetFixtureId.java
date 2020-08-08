package assesment.responses;

import com.sun.istack.NotNull;
import io.restassured.path.json.JsonPath;
import org.assertj.core.error.ShouldNotBeNull;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import assesment.base.BaseAPI;

import static java.lang.Boolean.TRUE;
import static java.sql.JDBCType.NULL;
import static org.hamcrest.core.Is.is;

public class GetFixtureId extends BaseAPI {

    private static Logger logger = LoggerFactory.getLogger(GetFixtureId.class);

    public static void assertSuccesfullFixtureId(String id) {
        assertJsonAttributeGetResponse("fixtureId",id);
    }

    public static void assertFixtureIdResponse(String id) {

        if((BaseAPI.responseGet.getBody().asString().equals("Fixture not found")))
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
        Assert.assertEquals(jsonPathEvaluator.getList("$").size(),fixtureArraySize);
        logger.info("\n The user list Array is of size " + fixtureArraySize);
    }

    public static void assertFixturesById() {
        JsonPath jsonPathEvaluator = responseGet.jsonPath();
        for(int i= 0;i <jsonPathEvaluator.getList("$").size(); i++) {
            Assert.assertNotNull(jsonPathEvaluator.getString("fixtureId["+i+"]"));
        }
    }

}
