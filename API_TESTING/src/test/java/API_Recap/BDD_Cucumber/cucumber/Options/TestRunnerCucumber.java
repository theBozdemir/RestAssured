package API_Recap.BDD_Cucumber.cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/API_Recap/BDD_Cucumber/features",
        glue = "API_Recap.BDD_Cucumber.StepDefinitions",
        //tags = "@AddPlaceRecap",
        plugin = "json:target/jsonReports/cucumber-report.json"
        
)
public class TestRunnerCucumber extends AbstractTestNGCucumberTests {
    //tags={"@DeletePlace"}
}
