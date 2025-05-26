package OldProjects.Day1.Day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void updateUser(ITestContext context){
        int id = (Integer) context.getSuite().getAttribute("user_id");
        Faker faker=new Faker();

        JSONObject data =new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Female");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        String bearerToken= "1e66ebc9ca72b7eb6853007e8bbbd2e1e1a956796d30f59a90278d3554313c8b";
                given()
                    .headers("Authorization","Bearer "+bearerToken)
                    .contentType("Application/json")
                    .pathParam("id",id)
                    .body(data.toString())
                .when()
                   .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                    .statusCode(200)
                    .log().all();


    }
}
