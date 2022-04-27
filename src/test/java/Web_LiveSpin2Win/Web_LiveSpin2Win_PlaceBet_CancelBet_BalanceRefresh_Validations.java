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

public class Web_LiveSpin2Win_PlaceBet_CancelBet_BalanceRefresh_Validations extends Web_LiveSpin2Win_URL_OnlineLogin{
WebDriver driver;

	public Web_LiveSpin2Win_PlaceBet_CancelBet_BalanceRefresh_Validations() throws Exception {
		driver =Web_LiveSpin2Win_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, LiveSpin(\\d+)Win table, stake amount, place bet, balance, cancel bet and print slip$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_LiveSpin_Win_table_stake_amount_place_bet_balance_cancel_bet_and_print_slip(int arg1) throws Throwable {
	    
	}

	@When("^Web: Login to suribet website with valid login details, Click on LiveSpin(\\d+)Win module link, place bet with selecting the stake amount$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_LiveSpin_Win_module_link_place_bet_with_selecting_the_stake_amount(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 240);

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the LiveRoulette: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//*[@ng-if='!PosActive']/span[2]"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "").replaceAll("[^0-9]", "");
		double d=Double.parseDouble(beforebal); 

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);


		//Select the Numbers from the live roulette Board top place the bet 
		WebElement Pick6 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='6']"));

		Pick6.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the live Roulette table");

		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("//*[@class='bet-chip mx-2 chip-0 active']")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal ); 
		Assert.assertEquals(rbal, d, 0.00);
		System.out.println("Main balance has been verified after placing bet "+ rbal ); 
	}

	@Then("^Web:  Balance has to deducted respective with bet placed$")
	public void web_Balance_has_to_deducted_respective_with_bet_placed() throws Throwable {
		WebDriverWait wait1 = new WebDriverWait(driver, 240);

		//For next iteration cancel update verification
		wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//*[@ng-if='!PosActive']/span[2]"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "").replaceAll("[^0-9]", "");
		double d=Double.parseDouble(beforebal);

		//Select the Numbers from the live roulette Board top place the bet 
		WebElement Pick8 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_2']/div[text() ='8']"));

		Pick8.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the live Roulette table");

		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("//*[@class='bet-chip mx-2 chip-0 active']")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal ); 
		Assert.assertEquals(rbal, d, 0.00);
		System.out.println("Main balance has been verified after placing bet "+ rbal ); 

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)']"));
		Cancel.click();
		Thread.sleep(2000);

		//Fetch the main balance after canceling bet
		WebElement Mbal1 = driver.findElement(By.xpath("//*[@ng-if='!PosActive']/span[2]"));
		System.out.println("Main balance after cancelling the placed bet: "+ Mbal1.getText());
		String Abal = Mbal1.getText();
		String afterbal = Abal.replaceAll(",", "").replaceAll("[^0-9]", "");
		double d1=Double.parseDouble(afterbal);
		Assert.assertEquals(d, d1, 0.00);
		System.out.println("Before and after cancelling the bet and the main balance is same and verified");
	}

	@Then("^Web: Cancel the bet placed and check with the same amount being updated to the main balance$")
	public void web_Cancel_the_bet_placed_and_check_with_the_same_amount_being_updated_to_the_main_balance() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}