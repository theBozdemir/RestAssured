package BDD.StepDefinitions;

import BDD.resources.APIResources;
import BDD.resources.TestDataBuild;
import BDD.resources.utils;
import NewProjects.Serialization_and_Deserialization.Serialization.Address;
import NewProjects.Serialization_and_Deserialization.Serialization.Location;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
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
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class StepDefinition extends utils {
    RequestSpecification res;
    Response response;
    TestDataBuild data=new TestDataBuild();
    JsonPath js;

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String address, String language) throws FileNotFoundException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        res=
                given()
                        .spec(requestSpecification())
                        .body(data.addPlacePayload(name, address, language));
    }
    @When("user call {string} with {string} http request")
    public void userCallWithHttpRequest(String str, String requestType) throws IOException {
        APIResources resource =APIResources.valueOf(str);
        System.out.println(resource.getResource());
        if(requestType.equalsIgnoreCase("POST")){
            response=res.when().post(resource.getResource())
                    .then().spec(responseSpecification()).extract().response();
        }
        else if(requestType.equalsIgnoreCase("GET")){
            response=res.when().get(resource.getResource())
                    .then().spec(responseSpecification()).extract().response();
        }
        System.out.println("userCallWithHttpRequest method "+response.getStatusCode());

             }


    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int success) {
        assertEquals(success,response.getStatusCode());

    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String val) {
        System.out.println(response.getBody().asPrettyString());
        String status=getJsonPath(response,key);
        assertEquals(status,val);


    }


    @And("verify place id created maps to {string} using {string}")
    public void verifyPlaceIdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        String placeId=getJsonPath(response,"place_id");

        res=
                given()
                        .spec(requestSpecification().queryParam("place_id",placeId));
        userCallWithHttpRequest(resource,"GET");
        String actualName=getJsonPath(response,"name");
        System.out.println("Expected name:"+expectedName+" Actual name:"+actualName);
        assertEquals(expectedName,actualName);


    }
}
