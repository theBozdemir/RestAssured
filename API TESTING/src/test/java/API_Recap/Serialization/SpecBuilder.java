package API_Recap.Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class SpecBuilder {
    @Test
    public void SerializeTest(){
        AddPlace addPlace=new AddPlace();
        LocationRecap loc=new LocationRecap();
        addPlace.setAccuracy(50);
        addPlace.setName("home");
        addPlace.setLanguage("Turkish");
        addPlace.setAddress("66 Thatcher drive");
        addPlace.setWebsite("www.connexall.com");
        ArrayList<String> types=new ArrayList<String>();
        types.add("shop");
        types.add("shoe park");
        addPlace.setTypes(types);
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        addPlace.setLocation(loc);
        ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
        RequestSpecification res=given().spec(req)
                .body(addPlace);
        Response response=res.when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();


    }
}
