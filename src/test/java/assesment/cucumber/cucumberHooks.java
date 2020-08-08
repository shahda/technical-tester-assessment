package assesment.cucumber;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

import assesment.utils.ReadData;

public class cucumberHooks {

    public static String baseURL;
    private static Logger logger = LoggerFactory.getLogger(cucumberHooks.class);

    public cucumberHooks() throws IOException {

        String envType = ReadData.readDataFromPropertyFile("Environment").trim();
        String targetENV = System.getProperty("targetEnv", envType);
        logger.info("\n Test Environment is '" + targetENV + "'.");
        baseURL = ReadData.readDataFromPropertyFile(targetENV + ".url").trim();
        logger.info("\n Base URL is '" + baseURL + "'.");
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
         logger.info("\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Started Scenario: " + scenarioName + " %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
    @After
    public void afterScenario(Scenario scenario) {
         logger.info("\n %%%%%%%%%%%%%%%%%%% End of Scenario: " + scenario.getName() + " %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

    }

}
