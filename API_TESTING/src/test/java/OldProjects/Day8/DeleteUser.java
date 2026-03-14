package OldProjects.Day1.Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void deleteUser(ITestContext context){
        int id = (Integer) context.getSuite().getAttribute("user_id");

        String bearerToken= "1e66ebc9ca72b7eb6853007e8bbbd2e1e1a956796d30f59a90278d3554313c8b";
        given()
                .headers("Authorization", "Bearer "+bearerToken)
                .pathParam("id",id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }
}
