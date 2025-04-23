package Day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;


import static org.hamcrest.Matchers.* ;

public class HTTPRequests {

    //RestAssured by default support BDD style
    /*
    given() -> content type, set cookies, add auth, add param, set headers info

    when() -> get, post, put, delete

    then() -> validate status code, extract response, extract headers cookies, response body
    */
int id;

    @Test
    void getUsers(){
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();

    }
    @Test(priority = 1)
    void createUsers(){
        HashMap data= new HashMap( );
        data.put("name","Tarik");
        data.put("job", "QA Engineer");
        id= given()
                .contentType("application/json")
                .body(data)
                .when()
                    .post("https://reqres.in/api/users")  //when we sent post request it will send back us a response
                    .jsonPath().getInt("id");

                /*.then()
                    .statusCode(201)
                    .log().all(); */


    }
    @Test(priority = 2,dependsOnMethods ={"createUsers"})
    void updateUser(){
        HashMap data = new HashMap();
        data.put("name", "Tarik");
        data.put("job","driver");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                    .put("https://reqres.in/api/users/"+id)
                .then()
                    .statusCode(200)
                    .log().all();

}
@Test(priority = 4, dependsOnMethods = {"createUsers"})
    void deleteUser(){
        given()
                .when()
                    .delete("https://reqres.in/api/users/"+id)
                .then()
                    .statusCode(204)
                    .log().all();

}
}