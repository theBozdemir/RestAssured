package NewProjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class restAssuredBasics {

    //given - all input details
    //when - Submit the API -> resource, http method
    //then - validate the response
    public static void main(String[]args){
        RestAssured.baseURI="https://rahulshettyacademy.com";


        String res= given().
                queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")

                .when()
                    .post("/maps/api/place/add/json")

                .then()
                .log().all().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();
        System.out.println(res);
        JsonPath js=new JsonPath(res); // for parsing json
        String placeId=js.getString("place_id");
        System.out.println("Place id is = "+placeId);
//----------------------------------------------------------------------------------------------------------------
        //Update Place
        String newAddress = "50 Grand, Canada";
        given()
                .log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")

                .when()
                .put("maps/api/place/update/json")

                .then()
                .assertThat().log().all().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

                //get place
        String getPLaceRes=given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId)


                .when()
                    .get("/maps/api/place/get/json")

                .then()
                .assertThat().log().all().statusCode(200)
                .extract().response().asString();
        //Calling String to json
        JsonPath js1=Reuseable_methods.rawToJson(getPLaceRes);
        String responseAddress=js1.getString("address");
        Assert.assertEquals(newAddress,responseAddress);

    }



}
