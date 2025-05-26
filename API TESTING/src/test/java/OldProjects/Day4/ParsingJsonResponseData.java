package OldProjects.Day1.Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class ParsingJsonResponseData {

    //@Test(priority = 1)
    void testJsonResponse() {
//        Response response = given()
//                .header("Accept", "application/json")
//                .when()
//                .get("http://localhost:3000/book");  // Ensure this endpoint is correct
//
//        response.then()
//                .statusCode(200)
//                .header("Content-Type", "application/json");
//
//        // Log response body
//        System.out.println(response.getBody().asString());
//
//        // Perform JSON Path assertion for books[2].title
//        response.then()
//                .body("[2].title", equalTo("Harry Potter and the Sorcerer's Stone"));  // Validate title at index 2 //These validation comes from matchers library

        //Approach 2
        Response res = given()
                .contentType("ContentType.json")
                .when()
                .get("http://localhost:3000/book");

        Assert.assertEquals(res.getStatusCode(), 200); //Using TestNG framework
        Assert.assertEquals(res.getHeader("Content-Type"),"application/json");

        // This will return object format, to be able to validate we need to convert it to string
        String bookName= res.jsonPath().get("[2].title").toString();
        Assert.assertEquals(bookName,"Harry Potter and the Sorcerer's Stone");

    }
    @Test
    void testJsonResponseBodyData() {
        //Approach 2
//        Response res = given()
//                .contentType("ContentType.json")
//                .when()
//                .get("http://localhost:3000/book");
//
//        Assert.assertEquals(res.getStatusCode(), 200); //Using TestNG framework
//        Assert.assertEquals(res.getHeader("Content-Type"),"application/json");
//
//        // This will return object format, to be able to validate we need to convert it to string
//        String bookName= res.jsonPath().get("[2].title").toString();
//        Assert.assertEquals(bookName,"Harry Potter and the Sorcerer's Stone");

        //To be able to read all the book names we need to capture all titles and need to find between them
                Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/books");

        System.out.println(res.asString());

                //To parse json response we need to JSONOBJECT class
        //I created a json object
        //Get method return value in Response type, to be able to pass that value we should convert to String format
        JSONObject jo =new JSONObject(res.asString()); // converting response to json object type
        boolean bookFound=false;


        for(int i=0; i<jo.getJSONArray("books").length() ;i++){

            //first we are capturing whole book array, then we are iterating each element in each element we are capturing titles and converting them into string

            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            if(bookTitle.equals("Harry Potter and the Sorcerer's Stone")){
                bookFound=true;
                break;
            }
        }
        Assert.assertEquals(bookFound,true);

        //validate total price of the books
        double sum =0;
        for(int i=0; i<jo.getJSONArray("books").length() ;i++){
            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            double dPrice=Double.parseDouble(price);
            sum+=dPrice;

        }
        System.out.println("Total price is : "+sum);
        Assert.assertEquals(sum,1872);




    }
}
