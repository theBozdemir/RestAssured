package NewProjects;

import NewProjects.Serialization_and_Deserialization.Deserialization.Api;
import NewProjects.Serialization_and_Deserialization.Deserialization.GetCourse;
import NewProjects.Serialization_and_Deserialization.Deserialization.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

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
        GetCourse res=given()
                .queryParam("access_token",access_token)
                .when().log().all().get("/getCourseDetails").as(GetCourse.class);
        System.out.println(res.getInstructor());
        //Traversing inside ApiCourses
        List<Api> apiCourses=res.getCourses().getApi();
        for(int i=0;i<apiCourses.size();i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("soapui webservices testing")){
                System.out.println("API Course price is:"+apiCourses.get(i).getPrice());
                break;
            }
        }
        //Traversing inside webAutomation
        List<WebAutomation> webAutoCourses=res.getCourses().getWebAutomation();
        int i=0;
        for(WebAutomation webAutoCourse:webAutoCourses){
            System.out.println("Course name "+(i+1)+" "+webAutoCourse.getCourseTitle());
            i++;
        }
    }

}
