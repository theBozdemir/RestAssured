package BDD.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/BDD/features", glue = {"BDD.StepDefinitions"})
public class TestRunner {
}
