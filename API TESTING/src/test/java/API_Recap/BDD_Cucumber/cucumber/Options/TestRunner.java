package API_Recap.BDD_Cucumber.cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/API_Recap/BDD_Cucumber/features",glue = "API_Recap.BDD_Cucumber.StepDefinitions",tags = "@DeletePlace")
public class TestRunner {

}
