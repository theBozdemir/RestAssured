package OldProjects.Day1.Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Path_QueryParameters {

    @Test
    void testQueryAndPathParameters(){

        given()
                .pathParam("mypath","users") //first key is parameter name, second key is value we want to assign
                .queryParam("page",2)// first query parameter
                .queryParam("id",5)

                .when()
                    .get("https://reqres.in/api/{mypath}") // we dont need to type query parameters in here they will be gone along with the request
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
