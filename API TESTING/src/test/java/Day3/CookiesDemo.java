package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesDemo {

    //@Test(priority = 1)
    void testCookies(){
        given()



                .when()
                    .get("https://www.google.com")
                .then()
                .cookie("AEC","AVYB7cqq3sG9gXRyct9azVdyy1JNsF_SToSy64iu4BGUayS80OSSR_rIGfY")
                    .log().all();
    }
    @Test(priority = 2)
    void geCookiesInfo(){
        Response res = given()



                .when()
                .get("https://www.google.com"); //If we want to capture entire request from response we shouldn't specify then().
//        /// Get single cookie info
//        String cookieValue=res.getCookie("AEC"); //Cookie name will not be changed, value will be changed in each run -> This method will return value of the cookie
//        System.out.println("Value of cookie is = "+cookieValue);

        // Get all cookies information
        Map<String,String> cookies_values=res.getCookies(); // getCookies() method variable type is Map<String,String>
        System.out.println(cookies_values.keySet());
        for(String value:cookies_values.keySet()){   // for each loop we need to use to iterate values
            String cookieValue=res.getCookie(value);
            System.out.println(value+" value is : "+cookieValue);
        }
        //values of headers are always same


    }
}
