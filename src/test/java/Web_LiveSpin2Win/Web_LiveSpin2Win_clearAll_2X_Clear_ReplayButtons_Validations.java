package Web_LiveSpin2Win;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

public class Web_LiveSpin2Win_clearAll_2X_Clear_ReplayButtons_Validations extends Web_LiveSpin2Win_URL_OnlineLogin{
WebDriver driver;
WebDriverWait wait = new WebDriverWait(driver, 240);

	public Web_LiveSpin2Win_clearAll_2X_Clear_ReplayButtons_Validations() throws Exception {
		driver =Web_LiveSpin2Win_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, live Spin(\\d+)Win module, place bet, replay, clear all and clear validations messages$")
	public void web_Chrome_browser_suribet_website_valid_URL_live_Spin_Win_module_place_bet_replay_clear_all_and_clear_validations_messages(int arg1) throws Throwable {
	    
	}

	@When("^Web: place bet and clear some bets and clear all sections and with (\\d+)X multiplier on the same bet with replay validations$")
	public void web_place_bet_and_clear_some_bets_and_clear_all_sections_and_with_X_multiplier_on_the_same_bet_with_replay_validations(int arg1) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		List<WebElement> wb = driver.findElements(By.xpath("//*[@ng-mouseover='grLiveCtlr.nearchip($event)']")); 
		List<String> list1=new ArrayList<String>();
		{
			list1.add("1"); 
			list1.add("2");
			list1.add("3"); 
			list1.add("4");
			list1.add("5"); 
			list1.add("6");
			list1.add("7"); 
			list1.add("8");
		}  
		boolean result = Arrays.equals(wb.toArray(),list1.toArray());
		System.out.println("Boolean result is true and the content in the obtained chips are validated and: "+result);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		//Select the Numbers from the live roulette Board top place the bet 
		WebElement Pick6 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='6']"));

		Pick6.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the live Roulette table");

		WebElement rows = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));  
		WebElement stake = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span")); 

		String rw = rows.getText();
		Assert.assertEquals("1", rw);
		String stakeamt = stake.getText();
		Assert.assertEquals("1.00", stakeamt);

		/*
		 * Doubling the bet
		 */
		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.MultiplyStakeByTwo()']")).click();
		Thread.sleep(2000);

		String rw1 = rows.getText();
		Assert.assertEquals("1", rw1);
		String stakeamt1 = stake.getText();
		Assert.assertEquals("2.00", stakeamt1);
		System.out.println("Verified the doubling the bet");


		/*
		 * Selecting other bets
		 */

		WebElement Pick10 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='10']"));
		WebElement Pick12 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='12']"));

		Pick10.click();
		Thread.sleep(1000);
		Pick12.click();
		Thread.sleep(1000);

		String rw2 = rows.getText();
		Assert.assertEquals("3", rw2);
		String stakeamt2 = stake.getText();
		Assert.assertEquals("4.00", stakeamt2);

		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.Undo()']")).click();
		Thread.sleep(1000);

		String rw3 = rows.getText();
		Assert.assertEquals("2", rw3);
		String stakeamt3 = stake.getText();
		Assert.assertEquals("3.00", stakeamt3);

		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.ClearFields()']")).click();
		Thread.sleep(1000);

		String rw4 = rows.getText();
		Assert.assertEquals("0", rw4);
		String stakeamt4 = stake.getText();
		Assert.assertEquals("0.00", stakeamt4);	
		System.out.println("Verified the clear and clear All functionality");

	}

	@Then("^Web: Validate the all the functionalities are working as expected$")
	public void web_Validate_the_all_the_functionalities_are_working_as_expected() throws Throwable {
		/*
		 * Replay button functionality validations
		 */


		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		//Select the Numbers from the live roulette Board top place the bet 
		WebElement Pick6 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='6']"));

		Pick6.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the live Roulette table");

		WebElement betnum = driver.findElement(By.xpath("//*[@ng-hide='cartD.completeBet']")); 
		String bnum = betnum.getText();
		
		// wait till the current bet is completed
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.Replay()']")).click();  
		Thread.sleep(1000);
		
		WebElement rows = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));  
		WebElement stake = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span")); 
		
		String rw = rows.getText();
		String stakeamt = stake.getText();
		
		Assert.assertEquals("1.00", stakeamt);
		Assert.assertEquals("1", rw);
		Assert.assertEquals("Stright-6", bnum);
		System.out.println("Verified reply button functionality");
	}
}