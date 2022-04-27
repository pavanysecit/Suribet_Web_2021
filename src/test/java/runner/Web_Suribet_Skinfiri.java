package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features="features/Web_Suribet_Skinfiri_AllUsecases.feature", 
glue={"Web_Skinfiri"} ,
plugin={"com.cucumber.listener.ExtentCucumberFormatter:"} 
)	

public class Web_Suribet_Skinfiri {
 
}