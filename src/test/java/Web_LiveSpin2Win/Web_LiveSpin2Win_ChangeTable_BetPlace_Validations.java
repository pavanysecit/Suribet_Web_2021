package Web_LiveSpin2Win;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_LiveSpin2Win_ChangeTable_BetPlace_Validations extends Web_LiveSpin2Win_URL_OnlineLogin {
WebDriver driver;


	public Web_LiveSpin2Win_ChangeTable_BetPlace_Validations() throws Exception {
		driver =Web_LiveSpin2Win_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser suribet website valid URL LiveSpin(\\d+)Win module tropicana bet table and carnival table$")
	public void web_Chrome_browser_suribet_website_valid_URL_LiveSpin_Win_module_tropicana_bet_table_and_carnival_table(int arg1) throws Throwable {
	    
	}

	@When("^Web: login to tropicana table and change table to carnival and place bet and again redirect to tropicana table$")
	public void web_login_to_tropicana_table_and_change_table_to_carnival_and_place_bet_and_again_redirect_to_tropicana_table() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 240);

		WebElement Table = driver.findElement(By.xpath("//*[@class='lv-as-lf-roulette in']/div/h6"));
		wait.until(ExpectedConditions.visibilityOf(Table));
		String T = Table.getText();
		Assert.assertEquals(" Tropicana Draw Results", T);
		System.out.println("Verified the tropicana table is selected");


		/*
		 * Change the bet table and place the bet to verify the bets placed functionality is working fine
		 */

		WebElement ChangeTable = driver.findElement(By.xpath("//*[@class='change-table']")); 
		ChangeTable.click();
		Thread.sleep(2000);

		WebElement carnival = driver.findElement(By.xpath("//*[@src='//cdn2-5e15.kxcdn.com/betting/webportal/images/roulette/table-2.png']")); 
		carnival.click();
		Thread.sleep(2000); 

		wait.until(ExpectedConditions.visibilityOf(Table));
		String C = Table.getText();
		Assert.assertEquals(" Carnival Draw Results", C);
		System.out.println("Verified the Carnival table is selected");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		WebElement Pick20 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='20']"));

		Pick20.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the live Roulette Carnival table");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		ChangeTable.click();
		Thread.sleep(2000);

		WebElement tropicana = driver.findElement(By.xpath("//*[@src='//cdn2-5e15.kxcdn.com/betting/webportal/images/roulette/table-1.png']")); 
		tropicana.click();
		Thread.sleep(2000); 

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		WebElement Pick21 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='21']"));

		Pick21.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the live Roulette tropicana table");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);
	}

	@Then("^Web: Validate the appropriate table change is reflected to user and able to place bet$")
	public void web_Validate_the_appropriate_table_change_is_reflected_to_user_and_able_to_place_bet() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}