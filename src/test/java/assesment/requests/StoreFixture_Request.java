package assesment.requests;

import assesment.base.BaseAPI;
import assesment.utils.ReadData;
import groovy.beans.PropertyReader;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class StoreFixture_Request extends BaseAPI {

    private static Logger logger = LoggerFactory.getLogger(StoreFixture_Request.class);

    public static void createNewFixtureRequest(String testReference,String id) throws IOException {
        JSONObject request = new JSONObject();
        String fixtureJsonFile = "src/test/resources/testDataResources/StoreFixture.json";
        InputStream inputStream = new FileInputStream(fixtureJsonFile);
        String jsonArrayFileData = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        JSONArray jsonArrayData = new JSONArray(jsonArrayFileData);
        boolean testScenarioMatch = false;

        for (int i = 0; i < jsonArrayData.length(); i++)
        {
            JSONObject testScenario = jsonArrayData.getJSONObject(i);
            if (testScenario.get("testReference").equals(testReference))
            {
                testScenarioMatch = true;
                request.put("fixtureId", id);
                request.put("fixtureStatus", testScenario.get("fixtureStatus"));
                request.put("footballFullState", testScenario.get("footballFullState"));
                break;
            }
        }

        if (testScenarioMatch) {
            logger.info("\n Store Fixture request is " + request);
            postRequest(request, ReadData.readDataFromPropertyFile("storefixture.endpoint"));
        } else
            logger.info("Test Reference not present in JSON file");
    }
}
