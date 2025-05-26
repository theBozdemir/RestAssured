package OldProjects.Day1.Recap;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class headersRecap {


    //@Test
    public void singleheadersRecap(){
        RestAssured.baseURI="https://www.google.com";

        Response res= given()
                .contentType("text/html; charset=ISO-8859-1")
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server","gws")
                .when()
                    .get(baseURI);

        String header = res.getHeader("Content-Type");

        Assert.assertEquals("text/html; charset=ISO-8859-1",header);




    }
    @Test
    public void allHeaders(){
        RestAssured.baseURI="https://www.google.com";

        Response res = given()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server", "gws")
                .when()
                .get(baseURI);

        Assert.assertEquals(res.getStatusCode(),200);

        Headers allHeaders= res.getHeaders();
        for(Header header : allHeaders){
            String headerName=header.getName();
            String headerVal=res.getHeader(headerName);
            if(headerName.equalsIgnoreCase("Content-Type")){
                Assert.assertEquals(headerVal,"text/html; charset=ISO-8859-1");
            }
            if(headerName.equalsIgnoreCase("Content-Encoding")){
                Assert.assertEquals(headerVal,"gzip");
            }
            if(headerName.equalsIgnoreCase("Server")){
                Assert.assertEquals(headerVal,"gws");
            }
        }

    }
}
