package stepDefinations;

import static io.restassured.RestAssured.given;
import org.junit.runner.RunWith;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import static org.junit.Assert.*;

import java.io.IOException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.BuildTestPayload;
import resources.Utils;

@RunWith(Cucumber.class)
public class AddPlaceDefination extends Utils{

	RequestSpecification requestSpec, getRequest;
	Response response;
	Response responsethen;
	static String placeId;
	BuildTestPayload data = new BuildTestPayload();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	
		requestSpec = given().spec(setRequestSpecification()).body(data.TestPayload(name, language, address));
       
    }

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
		
		APIResources resourceUrl = APIResources.valueOf(resource);	
		System.out.println(resourceUrl.GetResource());
		
		if(method.equalsIgnoreCase("POST")) {
			response = requestSpec.when().post(resourceUrl.GetResource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			response = requestSpec.when().get(resourceUrl.GetResource());
		}
		else if(method.equalsIgnoreCase("PUT"))
		{
			response = requestSpec.when().put(resourceUrl.GetResource());
		}
				
	}

	@Then("Place {string} successfully with status code {string}")
	public void place_successfully_with_status_code(String string, String string2) throws Throwable {
    	
    	responsethen = response.then().spec(setResponseSpecification()).extract().response();
    	assertEquals(responsethen.getStatusCode(),200);
    }

    @And("Response body {string} is {string}")
    public void response_body_something_is_something(String key, String value) throws Throwable {
    	   	
    	assertEquals(GetJsonPath(response, key),value);    	
    }
    
    @And("Verify created place_id maps to {string} using {string}")
    public void verify_created_place_id_maps_to_using(String expectedName, String resource_string) throws Throwable {
        placeId = GetJsonPath(response, "place_id");
                      
        requestSpec = given().spec(setRequestSpecification()).queryParam("place_id", placeId);
        user_calls_with_http_request(resource_string,"GET");
        
        String actualName = GetJsonPath(response, "name");
        assertEquals(actualName, expectedName);
        
    }
    @Given("Give payload for DeletePlaceApi")
    public void give_payload_for_DeletePlaceApi() throws IOException {
    	requestSpec = given().spec(setRequestSpecification()).body(data.deletePlacePayload(placeId));
    	
    }
    
    @Given("Given the place_id and key update the address {string}")
    public void given_the_place_id_and_key_update_the_address(String address) throws IOException {
    	requestSpec = given().spec(setRequestSpecification()).body(data.UpdatePlacePayload(address, placeId));
    }

    @Then("Verify created Adress maps to the {string}")
    public void verify_created_Adress_maps_to_the(String address) throws Throwable {
    	requestSpec = given().spec(setRequestSpecification()).queryParam("place_id", placeId);
    	user_calls_with_http_request("GetPlaceApi","GET");
        
        String adressGot = GetJsonPath(response, "address");
        assertEquals(adressGot, address);
    }

    
}
