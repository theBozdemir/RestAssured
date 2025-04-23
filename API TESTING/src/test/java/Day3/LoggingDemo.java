package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {
    @Test
    void testLogs(){
        given()
                .pathParam("mypath","users") //first key is parameter name, second key is value we want to assign
                .queryParam("page",2)// first query parameter
                .queryParam("id",5)

                .when()
                    .get("https://reqres.in/api/{mypath}")
                .then()
                    .log().body() //this will print only body of response
                    .log().cookies() // will print all cookies
                    .log().headers(); // will print all headers


    }
}
