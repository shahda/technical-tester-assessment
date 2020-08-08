package assesment.steps;

import assesment.responses.GetFixtureId;
import assesment.utils.ReadData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import assesment.base.BaseAPI;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class TechnicalTestSteps {

    private static Logger logger = LoggerFactory.getLogger(TechnicalTestSteps.class);

    @Given("^the app is up and running$")
    public void the_app_is_up_and_running() throws Throwable {

    }

    @When("^the request is performed using fixture id \"([^\"]*)\"$")
    public void the_request_is_performed_using_fixture_id(int id) throws Throwable {
        String endpoint = ReadData.readDataFromPropertyFile( "getfixture.endpoint") + id ;
        BaseAPI.getRequest(endpoint);
    }

    @And ("the response includes the correct response \"([^\"]*)\" and status code$")
    public void responseByFixtureId(String id) throws IOException
    {
            GetFixtureId.assertFixtureIdResponse(id);
    }

    @When("the request is performed to get all fixtures$")
    public void getAllFixtures() throws Throwable {
        String endpoint = ReadData.readDataFromPropertyFile( "getfixtures.endpoint") ;
        BaseAPI.getRequest(endpoint);
    }

    @Then("^response should be (\\d+)$")
    public void responseShouldBe(int statusCode) {
            BaseAPI.assertResponseStatus(statusCode);
    }
    @And("the response includes the correct fixture size (\\d+)$")
    public void responseAllFixtures(int fixtureArraySize) throws IOException
    {
        GetFixtureId.assertAllFixtures(fixtureArraySize);
    }

    @And("each fixture has fixtureid value$")
    public void responseFixturesById() throws IOException
    {
        GetFixtureId.assertFixturesById();
    }

}