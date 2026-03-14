package BDD.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/BDD/API_Recap.BDD_Cucumber.features", glue = {"BDD.StepDefinitions"})
public class TestRunner {
}
