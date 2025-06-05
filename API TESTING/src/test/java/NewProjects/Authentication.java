package NewProjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Authentication {
    String token;
    static{
        RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi";
    }
    public String getAccessToken(){

        Response res=
                given()
                        .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                        .formParams("grant_type","client_credentials")
                        .formParams("scope","trust")
                        .when().post("/oauth2/resourceOwner/token");
        JsonPath js=res.jsonPath();
        token=js.getString("access_token");
        return token;
    }
    @Test
    public void getClassName(){
        String access_token=getAccessToken();
        System.out.println("Access token is:"+access_token);
        Response res=given()
                .queryParam("access_token",access_token)
                .when().get("/getCourseDetails");
        System.out.println("Response body is:"+res.getBody().asPrettyString());
    }

}
