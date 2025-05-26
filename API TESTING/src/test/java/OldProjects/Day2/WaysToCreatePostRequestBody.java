package OldProjects.Day1.Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

/*Ways to create request body
----------------------------
1- HashMap
2- Using org.json
3- Using POJO (Plain old java project)
4- Using an external json file */

public class WaysToCreatePostRequestBody {

    String id;

    //1- Post Request Body Using HashMap

    //@Test
    void testPostUsingHashMap() {

        HashMap data = new HashMap();

        data.put("name", "Tarik");
        data.put("location", "Canada");
        data.put("phone", "4167222716");

        String courseArr[] = {"C", "C++"};
        data.put("course", courseArr);

        id = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .jsonPath().getString("id");
//                .then()
//                    .statusCode(201)
//                    .body("name",equalTo("Tarik"))
//                    .body("location",equalTo("Canada"))
//                    .body("phone", equalTo("4167222716"))
//                    .body("course[0]", equalTo("C"))
//                    .body("course[1]", equalTo("C++"))
//                    .header("Content-Type","application/json");
//    }
    }
    //@Test(priority = 1)
    void testDelete(){
        given()
                .when()
                .delete("http://localhost:3000/students/"+id)
                .then()
                .statusCode(200);

        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //2- Post Request Body Using org.json

    //@Test
    void testPostUsingJsonLibrary() {
        // we need to go to pom.xml and add org.json dependency

        //We need to create json object
        JSONObject data=new JSONObject();
        data.put("name","Tarik");
        data.put("location","Turkey");
        data.put("phone","123456789");
        data.put("id","123");
        String coursesArr[]={"C","C++"};

        data.put("course",coursesArr);


        given()
                .contentType("application/json")
                //we can't use data object directly in body when we are using json library, we need to convert to string
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                    .statusCode(201)
                    .body("name",equalTo("Tarik"))
                    .body("location",equalTo("Turkey"))
                    .body("phone", equalTo("123456789"))
                    .body("course[0]", equalTo("C"))
                    .body("course[1]", equalTo("C++"))
                    .header("Content-Type","application/json");
    }

    //@Test
    void testDeleteORGJSON(){
        given()
                .when()
                .delete("http://localhost:3000/students/123")
                .then()
                .statusCode(200);

    }

    ////////////////////////////////////////////////////////////////////////////////////////
    //USING POJO IS THE MOST IMPORTANT ONE _> WE WILL USE ENCAPSULATION
    ////////////////////////////////////////////////////////////////////////////////////////
    //@Test
    void testPostUsingPOJOclass() {
        // we need to go to pom.xml and add org.json dependency

        //We need to create json object
        Pojo_PostRequest data=new Pojo_PostRequest();
        data.setName("Tarik");
        data.setLocation("Los Angeles");
        data.setPhone("123456789");
        data.setID("123");
        String coursesArr[]={"C","C++"};

        data.setCourse(coursesArr);


        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Tarik"))
                .body("location",equalTo("Los Angeles"))
                .body("phone", equalTo("123456789"))
                .body("course[0]", equalTo("C"))
                .body("course[1]", equalTo("C++"))
                .header("Content-Type","application/json");
    }

    //@Test
    void testDeletePOJO(){
        given()
                .when()
                .delete("http://localhost:3000/students/123")
                .then()
                .statusCode(200);

    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //USING EXTERNAL JSON FILE
    ////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testPostUsingEXTERNALJSON() throws FileNotFoundException {

        File f= new File(".\\body.json");
        FileReader fr= new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject data =new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Tarik"))
                .body("location",equalTo("Macedonia"))
                .body("phone", equalTo("123456789"))
                .body("course[0]", equalTo("C"))
                .body("course[1]", equalTo("C++"))
                .header("Content-Type","application/json")
                .log().all();
    }

    @Test
    void testDeleteEXTERNALJSON(){
        given()
                .when()
                .delete("http://localhost:3000/students/123")
                .then()
                .statusCode(200);

    }
}
