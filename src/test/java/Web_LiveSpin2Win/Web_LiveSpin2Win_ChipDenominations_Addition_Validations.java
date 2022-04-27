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


public class Web_LiveSpin2Win_ChipDenominations_Addition_Validations extends Web_LiveSpin2Win_URL_OnlineLogin{
WebDriver driver;

	public Web_LiveSpin2Win_ChipDenominations_Addition_Validations() throws Exception {
		driver = Web_LiveSpin2Win_URL_OnlineLogin();
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 240);

	
	@Given("^Web: Chrome browser, suribet website valid URL, LiveSpin(\\d+)Win module, place bet, chip denominations, lock state of chip denominations, bet table$")
	public void web_Chrome_browser_suribet_website_valid_URL_LiveSpin_Win_module_place_bet_chip_denominations_lock_state_of_chip_denominations_bet_table(int arg1) throws Throwable {
	    
	}

	@When("^Web: Login to suribet website with valid login details, select VirtualRoulette module link$")
	public void web_Login_to_suribet_website_with_valid_login_details_select_VirtualRoulette_module_link() throws Throwable {
		System.out.println(driver.getCurrentUrl());
		System.out.println("Browser title: "+ driver.getTitle());


		WebElement tamount = driver.findElement(By.xpath("//*[@ng-show='PayMode == 1']")); 
		String tm = tamount.getText().replaceAll("[^0-9.]", "");
		int tm1 = Integer.parseInt(tm);

		//Select the chip denominations as 2 from the available chips
		WebElement Chip2 = driver.findElement(By.xpath("//*[@class='bet-chip mx-2 chip-1']"));
		WebElement Chip1 = driver.findElement(By.xpath("//*[@class='bet-chip mx-2 chip-2']"));


		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		Chip1.click();
		Thread.sleep(1000);

		//Select the Numbers from the Roulette Board top place the bet 
		WebElement Pick6 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='6']"));
		WebElement Pick8 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='8']"));

		Pick6.click();
		Thread.sleep(1000);

		//Assert the bets in the betting tray
		WebElement num = driver.findElement(By.xpath("//*[@ng-hide='cartD.completeBet']"));
		WebElement mul = driver.findElement(By.xpath("//*[@ng-show='cartD.splits.length==0']"));


		Assert.assertEquals("Straight-6", num.getText());
		Assert.assertEquals("360.00", mul.getText());
		String aftm = tamount.getText().replaceAll("[^0-9.]", "");
		int tm2 = Integer.parseInt(aftm)+10; 
		Assert.assertEquals(tm2,tm1);	


		/*
		 * again placing bet for addition validations
		 */
		Pick6.click();
		Thread.sleep(1000);

		Assert.assertEquals("Straight-6", num.getText());
		Assert.assertEquals("720.00", mul.getText());
		String aftm1 = tamount.getText().replaceAll("[^0-9.]", "");
		int tm22 = Integer.parseInt(aftm1)+20; 
		Assert.assertEquals(tm22,tm1);
		System.out.println("Addition bet placing is verified");

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)']"));
		Cancel.click();
		Thread.sleep(2000);
	}

	@Then("^Web: Select some numbers on roulette table and add the same table num with more chip denominations and place bet$")
	public void web_Select_some_numbers_on_roulette_table_and_add_the_same_table_num_with_more_chip_denominations_and_place_bet() throws Throwable {
		//Select the chip denominations as 2 from the available chips
		WebElement Chip2 = driver.findElement(By.xpath("//*[@class='bet-chip mx-2 chip-1']"));
		WebElement Chip1 = driver.findElement(By.xpath("//*[@class='bet-chip mx-2 chip-2']"));

		//Select the Numbers from the Roulette Board top place the bet 
		WebElement Pick6 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='6']"));
		WebElement Pick8 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='8']"));


		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		Chip1.click();
		Thread.sleep(1000);
		Pick6.click();
		Thread.sleep(1000);
		Chip2.click();
		Thread.sleep(1000);
		Pick8.click();
		Thread.sleep(1000);


		//Assert the bets in the betting tray
		WebElement num = driver.findElement(By.xpath("//*[@ng-hide='cartD.completeBet']"));
		WebElement num1 = driver.findElement(By.xpath("(//*[@ng-hide='cartD.completeBet'])[2]")); 
		WebElement mul = driver.findElement(By.xpath("//*[@ng-show='cartD.splits.length==0']"));
		WebElement mul1 = driver.findElement(By.xpath("(//*[@ng-show='cartD.splits.length==0'])[2]"));
		WebElement bets = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));
		WebElement stake = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span"));
		WebElement cancel = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)']"));
		WebElement cancel1 = driver.findElement(By.xpath("(//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)'])[2]"));


		
		Assert.assertEquals("Straight-6", num.getText());
		Assert.assertEquals("Straight-8", num1.getText());
		Assert.assertEquals("36.00", mul.getText());
		Assert.assertEquals("36.00", mul1.getText());
		Assert.assertEquals("2", bets.getText());
		Assert.assertEquals("2.00", stake.getText());
		System.out.println("multiple bet placing is verified");
		
		cancel1.click();
		Thread.sleep(2000);
		cancel.click();
		Thread.sleep(2000);
	}

	@Then("^Web: After placed bet check for the chips are still in lock condition for selected bets$")
	public void web_After_placed_bet_check_for_the_chips_are_still_in_lock_condition_for_selected_bets() throws Throwable {
	    driver.quit();
	}
}