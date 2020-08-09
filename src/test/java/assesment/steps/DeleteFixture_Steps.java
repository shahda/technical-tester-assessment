package assesment.steps;

import assesment.base.BaseAPI;
import assesment.utils.ReadData;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteFixture_Steps {

    private static Logger logger = LoggerFactory.getLogger(DeleteFixture_Steps.class);

    @When("^the delete request is performed using fixture id \"([^\"]*)\"$")
    public void deleteFixtureById(String id) throws Throwable {
        String endpoint = ReadData.readDataFromPropertyFile( "deletefixturebyid.endpoint") + id ;
        BaseAPI.deleteRequest(endpoint);
    }

    @When("^the request is performed using deleted fixture id \"([^\"]*)\"$")
    public void requestByFixtureId(String id) throws Throwable {
        String endpoint = ReadData.readDataFromPropertyFile( "getfixture.endpoint") + id ;
        BaseAPI.getRequest(endpoint);
    }


}
