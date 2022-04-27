package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="features/Web_Suribet_LiveSpin2Win_AllUsecases.feature", 
glue={"Web_LiveSpin2Win"} ,
plugin={"com.cucumber.listener.ExtentCucumberFormatter:"} )	


public class Web_Suribet_LiveSpin2Win_runner {

}