package NewProjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    static{
        RestAssured.baseURI="https://rahulshettyacademy.com";
    }
    @DataProvider(name="bookData")
    public Object[][] getData(){
        return new Object[][]{
                //{isbn_no, naisle_no}
                {"2321312","1265121"},
                {"6353454","4432312"}
        };
    }
    @Test(dataProvider = "bookData")
    public void addBook(String isbn, String aisle){
        String res=
        given()
                .header("Content-Type","application/json")
                .body(Payload.book(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js=new JsonPath(res);
        String id=js.get("ID");
        System.out.println("Response body is:"+res);
        System.out.println(id);
    }
}
