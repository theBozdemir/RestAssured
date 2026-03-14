package API_Recap.WeatherAPI.Utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class Utils {
    static RequestSpecification req;
    public static RequestSpecification getCities(String city) throws IOException {
        req=new RequestSpecBuilder().setBaseUri(getGlobalKey("url"))
                .addQueryParam("access_key",getGlobalKey("access_key"))
                .addQueryParam("query",city).build();
        return req;
    }
    public static String getGlobalKey(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/test/java/API_Recap/WeatherAPI/Utils/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
