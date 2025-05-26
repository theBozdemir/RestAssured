package OldProjects.Day1.Day8;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    void test_createUser(ITestContext context){

        Faker faker=new Faker();

        JSONObject data =new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String bearerToken= "1e66ebc9ca72b7eb6853007e8bbbd2e1e1a956796d30f59a90278d3554313c8b";

//        int id = given()
//                .headers("Authorization", "Bearer "+bearerToken)
//                    .contentType("application/json")
//                    .body(data.toString())
//
//                .when()
//                    .post("https://gorest.co.in/public/v2/users")
//                    .jsonPath().getInt("id");
//        System.out.println("Generated id is :"+id);

        Response res= given()
                .headers("Authorization", "Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("https://gorest.co.in/public/v2/users");

        JSONObject jso = new JSONObject(res.asString());

        int id= jso.getInt("id");
        System.out.println("Generated id is :"+id);

        context.getSuite().setAttribute("user_id", id);
        System.out.println(jso);





    }
}
