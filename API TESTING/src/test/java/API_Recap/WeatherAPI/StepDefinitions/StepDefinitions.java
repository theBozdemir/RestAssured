package API_Recap.WeatherAPI.StepDefinitions;

import API_Recap.WeatherAPI.Utils.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class StepDefinitions {
    RequestSpecification res;

    @Given("Weather API payload with {string}")
    public void weather_api_payload_with(String city) throws IOException {
        res=given().spec(Utils.getCities(city));

    }
    @When("User calls {string} with Get http request")
    public void user_calls_with_get_http_request(String string) {
        ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).build();
        Response response=res.when().get("/current").then().spec(resspec).log().all().extract().response();
    }
}
