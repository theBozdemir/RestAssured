package NewProjects.Serialization_and_Deserialization.Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder {
    static{

    }
    @Test
    public void serialization(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
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

        RequestSpecification req=new RequestSpecBuilder().addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
        ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        RequestSpecification res=
                given()
                        .spec(req)
                        .body(a);

                        Response response=res.when().post("/maps/api/place/add/json")
                        .then().spec(resspec).extract().response();
        System.out.println(response.getBody().asPrettyString());

    }

}
