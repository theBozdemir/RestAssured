package API_Recap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson{

@DataProvider(name="booksData")
public Object[][] getData(){
    return new Object[][] {
            {"alpha123", "1001"},
            {"beta456", "1002"},
            {"gamma789", "1003"},
            {"delta321", "1004"},
            {"epsilon654", "1005"},
            {"zeta987", "1006"},
            {"theta741", "1007"},
            {"lambda852", "1008"},
            {"omega963", "1009"},
            {"sigma159", "1010"}
    };
}
    @Test(dataProvider = "booksData")
    public void addBook(String aisle, String isbn){
    RestAssured.baseURI="http://216.10.245.166";
    String res=given()
            .body(Payload.addBook(aisle,isbn))
            .header("Content-Type","application/json")
            .when().post("Library/Addbook.php")
            .then()
            .assertThat().statusCode(200)
            .extract().response().asString();
    JsonPath js=new JsonPath(res);
    String id= js.getString("ID");
    System.out.println("ID = "+id);

}

}
