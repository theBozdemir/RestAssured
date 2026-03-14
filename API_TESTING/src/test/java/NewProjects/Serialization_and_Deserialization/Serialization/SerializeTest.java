package NewProjects.Serialization_and_Deserialization.Serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    static{
        RestAssured.baseURI="https://rahulshettyacademy.com";
    }
    @Test
    public void serialization(){
        Address a=new Address();
        a.setAccuracy(50);
        a.setName("Frontline house");
        a.setPhone_number("(+91) 983 893 3937");
        a.setAddress("29, side layout, cohen 09");

        a.setLanguage("French-IN");
        a.setWebsite("http://google.com");
        List<String> myList=new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        a.setLocation(l);
        a.setTypes(myList);

        Response res=
                given()
                        .queryParam("key","qaclick123")
                        .body(a)
                        .when().post("/maps/api/place/add/json")
                        .then().assertThat().statusCode(200).extract().response();
        System.out.println(res.getBody().asPrettyString());

    }

}
