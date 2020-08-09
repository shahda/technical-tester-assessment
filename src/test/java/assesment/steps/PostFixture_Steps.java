package assesment.steps;

import assesment.requests.StoreFixture_Request;
import assesment.responses.FixtureResponse;
import assesment.utils.ReadData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import assesment.base.BaseAPI;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.lang.Boolean.TRUE;

public class PostFixture_Steps {

    private static Logger logger = LoggerFactory.getLogger(PostFixture_Steps.class);

    @When("^the request is performed to store new fixture with using \"([^\"]*)\" and fixture ID \"([^\"]*)\"$")
    public void storeFixtures(String testReference, String id) throws Throwable {
        //Find whether it has Unique ID
        String endpoint = ReadData.readDataFromPropertyFile( "getfixture.endpoint") + id ;
        BaseAPI.getRequest(endpoint);
        if(BaseAPI.responseGet.getBody().asString().equals("Fixture not found"))
        {
            logger.info ("\n Fixture not found");
            StoreFixture_Request.createNewFixtureRequest(testReference,id);
        }
         else {
            //Fixture already exist and hence stop the scennario and try new fixture
            Assert.assertFalse("\n Fixture id " +id+ " already exist \n",TRUE);
        }
    }

    @And ("Fixture should be added successfully with message \"([^\"]*)\"$")
    public void responseBodyFixtureId(String message) throws IOException {
        FixtureResponse.postResponseMessage(message);
//        Assert.assertEquals(BaseAPI.postResponse.getBody().asString(),message);
    }

    @When("^the request is performed using new fixture id \"([^\"]*)\"$")
    public void the_request_is_performed_using_new_fixture_id(String id) throws Throwable {
        String endpoint = ReadData.readDataFromPropertyFile( "getfixture.endpoint") + id ;
        BaseAPI.getRequest(endpoint);
    }

    @Then("^delete the new  fixture \"([^\"]*)\"$")
    public void delete_the_new_fixture(String id) throws Throwable {
        String endpoint = ReadData.readDataFromPropertyFile( "deletefixturebyid.endpoint") + id ;
        BaseAPI.deleteRequest(endpoint);
    }

    @Then("^the correct response message \"([^\"]*)\" should be received$")
    public void responseMessage(String message) throws Throwable {
        FixtureResponse.deleteResponseMessage(message);
    }

    @When("^the response should be \"([^\"]*)\" and Status code (\\d+)$")
    public void the_response_should_be_and_Status_code(String message, int statuscode) throws Throwable {

    }

    @And("^verify team having \"([^\"]*)\" as first item in teamId$")
    public void verifyTeam(String teamId) throws IOException {
     FixtureResponse.teamArrayValidation(teamId);
    }

}
