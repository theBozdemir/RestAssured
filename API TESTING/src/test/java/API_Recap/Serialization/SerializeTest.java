package API_Recap.Serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class SerializeTest {
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

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res=given()
                .body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(res);

    }
}
