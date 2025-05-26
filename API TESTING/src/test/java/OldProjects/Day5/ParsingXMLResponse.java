package OldProjects.Day1.Day5;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ParsingXMLResponse {

    //@Test
    void  testXMLResponse(){

//APPROACH-1
//        given()
//                .when()
//                .get("https://mocktarget.apigee.net/xml")
//                .then()
//                        .statusCode(200)
//                        .header("Content-Type", "application/xml; charset=utf-8")
//                        .body("root.city", equalTo("San Jose"))
//                        .body("root.firstName", equalTo("John"));
        //APPROACH-2
        RestAssured.baseURI="https://mocktarget.apigee.net";
        Response res = given()
                .when()
                .get("/xml");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
        Assert.assertEquals(res.xmlPath().get("root.city").toString(),"San Jose");
        Assert.assertEquals(res.xmlPath().get("root.firstName").toString(), "John");
    }
    @Test
    void  testXMLResponseBody(){

        //APPROACH-2
        Response res = given()
                .when()
                .get("https://mocktarget.apigee.net/xml");



// To Use additional verification
        XmlPath xmlObj=new XmlPath(res.asString()); // converting data to string -> toString() method
                                                    // converting entire response to string -> asString() method
        List<String> travelers =xmlObj.getList("root"); // This will find all root tags
        Assert.assertEquals(travelers.size(),1);
        //Validate is there specific data exist
        boolean flag = false;
        List<String> travelerNames=xmlObj.getList("root.firstName");
        for(String travellerName: travelerNames){
            if(travellerName.equals("John")){
                System.out.println(travellerName+" is equal to search criteria.");
                flag= true;
                break;
            }
            else{
                System.out.println("Searched name is not exist in the travellers list");
            }
        }







    }

}
