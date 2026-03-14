package API_Recap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Authentication {
    @Test
    public String getToken(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res=given()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("/oauthapi/oauth2/resourceOwner/token")
                .then()
                .extract().response().asString();
        JsonPath js=new JsonPath(res);
        String token=js.getString("access_token");
        System.out.println(res);
        System.out.println("Token is = "+token);
        return token;
    }
    @Test
    public void getCourses(){
        String token=getToken();
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res2=given()
                .queryParam("access_token",token)
                .when().get("/oauthapi/getCourseDetails")
                .then()
                .extract().response().asString();

        System.out.println(res2);
    }
}
