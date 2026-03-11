package API_Recap.BDD_Cucumber.StepDefinitions;

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
    Response response;
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {


        res=given().spec(requestSpecification())
                .body(TestDataBuild.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response=res.when().post("/maps/api/place/add/json")
                .then().spec(resSpec).log().all().assertThat().extract().response();
    }
    @Then("the API call is success with status code")
    public void the_api_call_is_success_with_status_code() {

        assertEquals(response.statusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyVal, String expectedVal) {
        String responseStr=response.asString();
        JsonPath js=new JsonPath(responseStr);
        String comparingVal=js.getString(keyVal);
        assertEquals(comparingVal,expectedVal);

    }
}
