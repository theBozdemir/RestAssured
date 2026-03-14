package OldProjects.Day1.Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadersDemo {
    // Content-Type, Content-Encoding and Server are mostly never change
    //@Test
    void testHeaders(){
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws")
                .log().all();
    }
    //how to capture information from headers
    @Test
    void getHeaders(){
        Response res =given()
                .when()
                .get("https://www.google.com");

        //get Single header info
        String header_value=res.getHeader("Content-type"); //This will return header value
        System.out.println("Content type of header is = "+header_value);

        //get multiple header info
        Headers all_headers=res.getHeaders();
        int i=1;
        for(Header header: all_headers){

            String header_name= header.getName();
            String header_val=res.getHeader(header_name);
            System.out.println(i+") My header name is ==> "+header_name+" <== and value is :"+header_val);
            i++;

        }

    }
}
