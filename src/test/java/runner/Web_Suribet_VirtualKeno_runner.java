package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features/Web_Suribet_VirtualKeno_AllUsecases.feature",
glue={"Web_VirtualKeno"}, 
plugin={"com.cucumber.listener.ExtentCucumberFormatter:"} )

public class Web_Suribet_VirtualKeno_runner {

}