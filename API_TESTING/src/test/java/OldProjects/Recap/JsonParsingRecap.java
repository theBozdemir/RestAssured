package OldProjects.Day1.Recap;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonParsingRecap {

@Test
    public void parsingJsonResponse(){
    Response res = given()
            .when()
            .get("http://localhost:3000/books");

    boolean bookFound = false;
    String searchedBook ="Harry Potter and the Sorcerer's Stone";

    //JSONObject jsonObj=new JSONObject(res.asString());
    JSONArray booksArray=new JSONArray(res.asString());

    for(int i=0; i<booksArray.length(); i++){
        String bookName= booksArray.getJSONObject(i).getString("title");

        if(bookName.equals(searchedBook)){
            System.out.println("Searched book title : "+searchedBook+" it's element number is : "+(i+1));
            bookFound=true;
            break;
        }
    }
    Assert.assertEquals(bookFound,true);
    for(int i=0;i<booksArray.length();i++){
        int price = booksArray.getJSONObject(i).getInt("price");
        String  bookName=booksArray.getJSONObject(i).getString("title");

        System.out.println(bookName+" price is : "+price);
    }

}


}
