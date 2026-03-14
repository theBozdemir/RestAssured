package OldProjects.Day1.Day7;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Authentications {

    Properties envVars = new Properties();

    @BeforeClass
    void setupEnv() {
        try {
            FileInputStream fis = new FileInputStream(".env");
            envVars.load(fis);
        } catch (IOException e) {
            System.out.println("Could not load .env file: " + e.getMessage());
        }
    }

    //@Test
    void testBasicAuth() {
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .auth().basic("postman", "password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    //@Test
    void DigestAuth() {
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .auth().digest("postman", "password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    //@Test
    void PreempiveAuth() {
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    //@Test
    void bearerToken() {
        String token = envVars.getProperty("API_KEY");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    //@Test
    void oauth1() {
        given()
                .auth().oauth(
                        envVars.getProperty("OAUTH_CONSUMER_KEY"),
                        envVars.getProperty("OAUTH_CONSUMER_SECRET"),
                        envVars.getProperty("OAUTH_ACCESS_TOKEN"),
                        envVars.getProperty("OAUTH_TOKEN_SECRET")
                )
                .when()
                .get("url")
                .then()
                .statusCode(200)
                .log().all();
    }

    //@Test
    void oauth2Authentication() {
        given()
                .auth().oauth2(envVars.getProperty("API_KEY"))
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void testAPIKeyAuth() {
        given()
                .queryParam("appid", envVars.getProperty("WEATHER_API_KEY"))
                .when()
                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=London&cnt=3")
                .then()
                .statusCode(200)
                .log().all();
    }
}
