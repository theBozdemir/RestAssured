package BDD.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class utils {
    static RequestSpecification req;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            return req;
        }
        return req;
    }
    public ResponseSpecification responseSpecification() throws IOException {
        getGlobalValue("baseUrl");
        ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();
        return resspec;

    }
    public static String getGlobalValue(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream file=new FileInputStream("C:\\Users\\tbozdemir\\Automation_Repo\\RestAssured\\API TESTING\\src\\test\\java\\BDD\\resources\\global.properties");
        prop.load(file);
        String val=prop.getProperty("key");
        return val;
    }
    public String getJsonPath(Response res, String key){
        JsonPath js=res.jsonPath();
        return js.getString(key);

    }
}
