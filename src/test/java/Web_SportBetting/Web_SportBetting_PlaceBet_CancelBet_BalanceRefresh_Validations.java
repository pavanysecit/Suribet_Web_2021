package Web_SportBetting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_SportBetting_PlaceBet_CancelBet_BalanceRefresh_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_PlaceBet_CancelBet_BalanceRefresh_Validations() throws Exception {
		driver =Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, Roulette table, stake amount, place bet, balance, cancel bet and print slip$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_Roulette_table_stake_amount_place_bet_balance_cancel_bet_and_print_slip() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on SportsBetting module link, place bet with selecting the stake amount$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_SportsBetting_module_link_place_bet_with_selecting_the_stake_amount() throws Throwable {

		// Fetch the account balance 
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualRoulette: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("//*[@class='bet-wrapper']/ul[2]/li[2]")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal );

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);

		//Fetch the main balance after placing bet
		String Bbal1 = bal.getText();
		String beforebal1 = Bbal1.replaceAll(",", "");
		double d11=Double.parseDouble(beforebal1); 
		Assert.assertEquals(rbal, d11, 0.00);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()='Cancel Slip']"));
		wait.until(ExpectedConditions.visibilityOf(Cancel));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

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
		System.out.println("");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("");
	}

	@Then("^Web:  Balance has to dedcuted respective with bet placed, and print slip has to be geneated$")
	public void web_Balance_has_to_dedcuted_respective_with_bet_placed_and_print_slip_has_to_be_geneated() throws Throwable {


		//Logout the account and place the bet and verify the validation message is displaed to user and placing bet is not allowed 
		System.out.println("Log Off and try to place bet");
		WebElement logoff = driver.findElement(By.cssSelector(".log_off.click_effect_JS"));
		logoff.click();
		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		wait.until(ExpectedConditions.visibilityOf(submit));
		Thread.sleep(5000);

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(4000); 

		//Place the bet with the default stake
		WebElement e_ticket = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		Assert.assertTrue(e_ticket.isDisplayed());
		System.out.println("Forward button is not displayed to user when logout and tried to place bet");
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