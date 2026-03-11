package API_Recap.WeatherAPI;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;



@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/API_Recap/WeatherAPI/Weather.feature",glue = "API_Recap.WeatherAPI.StepDefinitions")
public class TestRunner {


}
