package API_Recap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static API_Recap.Payload.payload;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceRecap {
    public static void main(String[] args) throws IOException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().
                queryParam("key","qaclick123")
                .header("Content-Type","Application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\tbozdemir\\Desktop\\jsonFiles\\addPlace.json"))))
                .when().post("/maps/api/place/add/json")
                .then()
                //.log().all()
                .assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();
        System.out.println(response);
        //Here we are teaching Json path to our response in order to get what we need
        JsonPath js=new JsonPath(response);

        //Here we are extracting the value
        String placeID=js.getString("place_id");
        System.out.println("Place ID : "+placeID);

        //updating the place
        String newAddress="70 Summer walk, USA";
        String res2= given()
                .body("{\n" +
                        "  \"place_id\": \"" + placeID + "\",\n" +
                        "  \"address\": \""+newAddress+"\",\n" +
                        "  \"key\": \"qaclick123\"\n" +
                        "}")
                .queryParam("key","qaclick123")
                .contentType("Application/Json")
                .when().put("/maps/api/place/update/json")
                .then()
                .assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"))
                .extract().response().asString();
        System.out.println(res2);

        //Retrieving the latest Get place
        String res3=given()
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("/maps/api/place/get/json")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js2=new JsonPath(res3);
        String addressUpdated=js2.getString("address");
        Assert .assertEquals(newAddress,addressUpdated);
        System.out.println("Updated address is :"+addressUpdated);
    }
}
