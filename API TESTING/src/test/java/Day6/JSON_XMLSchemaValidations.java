package Day6;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JSON_XMLSchemaValidations {
    @Test
    void jsonSchemaValidation(){
        //Json to Json Schema converter -----> https://jsonformatter.org/json-to-jsonschema
        RestAssured.baseURI="http://localhost:3000";
                given()
                .when()
                    .get("/books")
                .then()
                    .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("booksJsonSchema.json")); // compares data type with body of json file and jsonSchema





    }
}
