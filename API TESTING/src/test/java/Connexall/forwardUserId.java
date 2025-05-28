package Connexall;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class forwardUserId {
    private static String sessionCookie;

    static {
        RestAssured.baseURI = "https://localhost:7777";
    }

    // Users with different forwarding setup
    //Each user will be returned
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {"doctor1", "1234", "881982228"},
                {"doctor2", "1234", "-1"},
                {"doctor3", "1234", "-1"}
        };
    }

    // We need to ignore SSL
    @BeforeClass
    public void skipSSLValidation() {
        // Relax SSL certificate validation globally before tests run
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(dataProvider = "userData")
    void login(String username, String password, String forwardId) {
        Response res = given()
                .body("{\n" +
                        "    \"resource\": \"auth\",\n" +
                        "    \"action\": \"login\",\n" +
                        "    \"data\": {\n" +
                        "        \"username\": \"" + username + "\",\n" +
                        "        \"password\": \"" + password + "\"\n" +
                        "    }\n" +
                        "}")
                .when().post("/cws/v2/auth");

        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("Response Body" + res.getBody().asPrettyString());
        sessionCookie = res.getCookie("CNXL_SID_8_0_25");
        assert res.statusCode() == 200 : "Login failed for " + username;
        System.out.println("Session Cookie: " + sessionCookie);

        // Profile API
        Response res1 = given()
                .header("Content-Type", "application/json")
                .cookie("CNXL_SID_8_0_25", sessionCookie)
                .body("{\n" +
                        "    \"resource\": \"my\",\n" +
                        "    \"action\": \"profile\",\n" +
                        "    \"appId\": 1,\n" +
                        "    \"filter\": {},\n" +
                        "    \"limit\": 0,\n" +
                        "    \"offset\": 0\n" +
                        "}")
                .when().post("/cws/v2/my");

        System.out.println("Response for " + username + ": " + res1.getBody().asPrettyString());
        System.out.println("Status code: " + res1.statusCode());
        sessionCookie = res1.getCookie("CNXL_SID_8_0_25");
        System.out.println("Session Cookie second phase: " + sessionCookie);
    }
}
