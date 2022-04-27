package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features/Web_Suribet_Poker_AllUsecases.feature", 
glue={"Web_Poker"},
plugin={"com.cucumber.listener.ExtentCucumberFormatter:"} )	

public class Web_Suribet_Poker_runner {


}
	
	