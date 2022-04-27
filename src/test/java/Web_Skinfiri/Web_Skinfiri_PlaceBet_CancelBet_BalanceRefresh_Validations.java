package Web_Skinfiri;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_Skinfiri_PlaceBet_CancelBet_BalanceRefresh_Validations extends Web_Skinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Skinfiri_PlaceBet_CancelBet_BalanceRefresh_Validations() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, Roulette table, stake amount, place bet, balance, cancel bet and print slip$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_Roulette_table_stake_amount_place_bet_balance_cancel_bet_and_print_slip() throws Throwable {
		
	}

	@When("^Web: Login to suribet website with valid login details, Click on Skinfiri module link, place bet with selecting the stake amount$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_Skinfiri_module_link_place_bet_with_selecting_the_stake_amount() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the Skinfiri: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//li[@ng-repeat='date in DailyGameCtrl._upcommingDraw'])[1]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers from the betting Board top place the bet 
		WebElement Pick1 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[1]"));
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_25  Soldout'])[1]"));
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_27  Soldout'])[1]"));

		Pick1.click();
		Thread.sleep(1000);
		Pick2.click();
		Thread.sleep(1000);
		Pick3.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the betting slip");

	
		
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_clear_all()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement back = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_clear_all()']"));

		
		
		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("//input[@ng-change='DailyGameCtrl.changeAllStack()']")); 
		stakeamount.sendKeys("1");
		
		WebElement stakeamount1 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div[3]/span[2]")); 
		String stakeamt = stakeamount1.getText();
		System.out.println("Stake Amount" +stakeamt);
//		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stakeamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal );
		
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(3000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivDgCartAlert")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);

		//Fetch the main balance after placing bet
		String Bbal1 = bal.getText();
		String beforebal1 = Bbal1.replaceAll(",", "");
		double d11=Double.parseDouble(beforebal1); 
		Assert.assertEquals(rbal, d11, 0.00);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()='Cancel Slip ']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//div[@ng-if='cancelbutton']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));

		SlipCancel.click();
		Thread.sleep(3000);

		//Clicking on Close icon on the pop-up
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		WebElement SlipCancelMessage = driver.findElement(By.xpath("//form[@class='EmptyValidation_W_JS ng-pristine ng-valid']/div/b"));
		System.out.println("Cancel Slipmessage  text is verified: "+ SlipCancelMessage.getText());
		Assert.assertEquals("Slip Successfully Cancelled.", SlipCancelMessage.getText());
		Thread.sleep(2000);

		WebElement custompopup = driver.findElement(By.cssSelector(".PrintSlipDet.MsgPop_OK"));
		custompopup.click();
		Thread.sleep(4000);
		

		//Fetch the main balance after cancelling bet
		WebElement Mbal1 = driver.findElement(By.id("sn_after_login_info_Amount"));
		System.out.println("Main balance after cancelling the placed bet: "+ Mbal1.getText());
		Assert.assertEquals(Mbal1.getText(), mbal);
		System.out.println("Before and after cancelling the bet and the main balance is same and verified");
	}

	@Then("^Web:  Balance has to dedcuted respective with bet placed, and print slip has to be geneated$")
	public void web_Balance_has_to_dedcuted_respective_with_bet_placed_and_print_slip_has_to_be_geneated() throws Throwable {

	}

	@Then("^Web: Cancel the bet placed and check with the same amount being updated to the main balance$")
	public void web_Cancel_the_bet_placed_and_check_with_the_same_amount_being_updated_to_the_main_balance() throws Throwable {
	    
	}

	@Then("^Web: Validation successful message for placing bet should be generated$")
	public void web_Validation_successful_message_for_placing_bet_should_be_generated() throws Throwable {
	    
	}

	@Then("^Web: After cancelling the placed bet validation message has to be generated and stake balance has be added to main account balance$")
	public void web_After_cancelling_the_placed_bet_validation_message_has_to_be_generated_and_stake_balance_has_be_added_to_main_account_balance() throws Throwable {
	    
	}

	@Then("^Web: Logout and place the bet and check whether bets can be placed after logout from the suribet and verify the validation message$")
	public void web_Logout_and_place_the_bet_and_check_whether_bets_can_be_placed_after_logout_from_the_suribet_and_verify_the_validation_message() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}