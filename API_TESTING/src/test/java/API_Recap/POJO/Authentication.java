package API_Recap.POJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Authentication {
    int count=0;

    public String extractToken(){
        String token;
        RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi";
        String res=given()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("/oauth2/resourceOwner/token")
                .then().extract().response().asString();
        JsonPath js=new JsonPath(res);
        token=js.getString("access_token");
        System.out.println("extract token : "+token);
        return token;
    }
    @Test
    public void getCourseBody(){
        RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi";
        String token=extractToken();
        ArrayList <String> courseNames=new ArrayList<String>();
        String validateCourses[]={"Selenium Webdriver Java","Cypress","Protractor","Rest Assured Automation using Java","SoapUI Webservices testing"};
        System.out.println("Get course body: "+token);
        //In here we are storing our response as GetCourses class object
        //Because GetCourses class is our main json class
        GetCourses getCourses=given()
                .queryParam("access_token",token)
                .when().get("/getCourseDetails")
                .then().extract().response().as(GetCourses.class);
        System.out.println(getCourses.getLinkedIn());
        String searchedCourse="SoapUI Webservices testing";
        for(int i=0;i<getCourses.getCourses().getAPI().size();i++){
            String courseTitle= getCourses.getCourses().getAPI().get(i).getCourseTitle();

            if (courseTitle.equals(searchedCourse)){
                System.out.println(courseTitle+" price is : "+getCourses.getCourses().getAPI().get(i).getPrice());
            }
            courseNames.add(courseTitle);
        }
        for(int i=0; i<getCourses.getCourses().getWebAutomation().size();i++){
            String courseTitle= getCourses.getCourses().getWebAutomation().get(i).getCourseTitle();
            courseNames.add(courseTitle);
            System.out.println(i+1+". "+getCourses.getCourses().getWebAutomation().get(i).getCourseTitle());
        }
        for(int i=0;i<courseNames.size();i++){
            String courseName=courseNames.get(i);
            for(int j=0; j<validateCourses.length;j++){
                if(courseName.equals(validateCourses[j])){
                    this.count++;
                }
            }
        }
        if(this.count==5){
            System.out.println("All classes are validated");
        }
        else {
            System.out.println("There is missing class");
        }


    }
}
