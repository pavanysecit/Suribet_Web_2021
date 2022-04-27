package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features/Web_Suribet_VirtualBaccarat_AllUsecases.feature", 
glue={"Web_VirtualBaccarat"},
plugin={"com.cucumber.listener.ExtentCucumberFormatter:"} )	

public class Web_Suribet_VirtualBaccarat_runner {

} 