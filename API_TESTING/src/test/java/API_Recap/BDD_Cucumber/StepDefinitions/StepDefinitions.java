package API_Recap.BDD_Cucumber.StepDefinitions;

import API_Recap.BDD_Cucumber.resources.APIResources;
import API_Recap.BDD_Cucumber.resources.TestDataBuild;
import API_Recap.BDD_Cucumber.resources.Utils;
import API_Recap.Serialization.AddPlace;
import API_Recap.Serialization.LocationRecap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {
    RequestSpecification res;
    ResponseSpecification resSpec;
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {


        res=given().spec(requestSpecification())
                .body(TestDataBuild.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        response=httpRequest(resource, method, res);
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }
    @Then("the API call is success with status code")
    public void the_api_call_is_success_with_status_code() {

        assertEquals(response.statusCode(),200);
        System.out.println("status code within the_api_call_is_success_with_status_code method : "+response.statusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyVal, String expectedVal) {

        assertEquals(expectedVal,getJsonPath(response,keyVal));

    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        String method="GET";
        response=httpRequest(resource, method, res);
        String actualName=getJsonPath(response,"name");
        assertEquals(expectedName,actualName);

    }
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        res=given().spec(requestSpecification()).body(TestDataBuild.deletePlacePayload(place_id));
    }
}
