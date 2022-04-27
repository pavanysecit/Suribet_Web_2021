package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features/Web_Suribet_Lotto_AllUsecases.feature",
glue={"Web_Lotto"}, 
plugin={"com.cucumber.listener.ExtentCucumberFormatter:"} )

public class Web_Suribet_Lotto_runner {

}