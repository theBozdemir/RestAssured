package API_Recap.BDD_Cucumber.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification req;


    public RequestSpecification requestSpecification() throws IOException {
        //If block helps us to write to log without previous log erased.
        if(req==null){
            PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }

        return req;
    }
    public String getGlobalValue(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/test/java/API_Recap/BDD_Cucumber/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
