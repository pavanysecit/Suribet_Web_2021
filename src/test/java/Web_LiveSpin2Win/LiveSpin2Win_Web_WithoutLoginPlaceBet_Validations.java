package Web_LiveSpin2Win;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveSpin2Win_Web_WithoutLoginPlaceBet_Validations {
	WebDriver driver;
	
	@Given("^Web: Chrome browser suribet website valid URL roulette module place bet replay validations messages$")
	public void web_Chrome_browser_suribet_website_valid_URL_roulette_module_place_bet_replay_validations_messages() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/LiveSpin2Win/LiveSpin2Win");
		driver.manage().window().maximize();
		Thread.sleep(4000);
	}

	@When("^Web: Direct to the module and place bet in logout condition with and without bets and try to rebet under logout conditions$")
	public void web_Direct_to_the_module_and_place_bet_in_logout_condition_with_and_without_bets_and_try_to_rebet_under_logout_conditions() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		WebElement close = driver.findElement(By.xpath("//*[@class='sidebar_close']"));
		wait.until(ExpectedConditions.visibilityOf(close));
		TimeUnit.SECONDS.sleep(4);
		close.click();
		TimeUnit.SECONDS.sleep(3);

		/*
		 * 1. Forward and place bet without selecting single bet
		 * 2. select future bets and try to place bet
		 * 3. select the rebet option 
		 *    Validation message to be validated 
		 */

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);
		
		//Place the bet without any draw selection
		WebElement Pick10 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='10']"));
		Pick10.click();
		TimeUnit.SECONDS.sleep(1);
		
		WebElement Validmsg = driver.findElement(By.xpath("//*[@class='error-message ng-binding show']")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		Assert.assertTrue(Validmsg.isDisplayed());

		//Rebet button validations
		WebElement rebet = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.Replay()']")); 
		rebet.click();
		Thread.sleep(2000);

//		WebElement Validmsg1 = driver.findElement(By.xpath("//*[@class='error-message ng-binding show']")); 
//		wait.until(ExpectedConditions.visibilityOf(Validmsg1));
//		String V2msg = Validmsg1.getText();
//		System.out.println("Login prompt user message for rebet button selecting: "+ V2msg);
//		Assert.assertEquals("You must login before place a bet!", V2msg);
	}

	@Then("^Web: Validate the appropriate user message displayed bet placing under logout conditions$")
	public void web_Validate_the_appropriate_user_message_displayed_bet_placing_under_logout_conditions() throws Throwable {
	    driver.quit();
	}
}