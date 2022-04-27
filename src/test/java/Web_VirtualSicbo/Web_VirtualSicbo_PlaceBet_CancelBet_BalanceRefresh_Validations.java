package Web_VirtualSicbo;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_PlaceBet_CancelBet_BalanceRefresh_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
WebDriver driver;
	
	public Web_VirtualSicbo_PlaceBet_CancelBet_BalanceRefresh_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	} 
	

	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, Sicbo table, stake amount, place bet, balance, cancel bet and print slip$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_Sicbo_table_stake_amount_place_bet_balance_cancel_bet_and_print_slip() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualSicbo: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]"));
		DrawNO.click();
		Thread.sleep(2000);
		
		//Selelct the Numbers from the Sicbo Board to place the bet 
		WebElement betType1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		WebElement betType2 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		
		betType1.click();
		Thread.sleep(1000);
		betType2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Sicbo table");

		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal );
		
		//Place the bet with the default stake

		//FORWARD AND PLACE BET WITH LOGIN TO THE VIRTUAL SICBO
		WebElement Forward = driver.findElement(By.xpath("//*[ text() = 'FORWARD']"));
		Forward.click();
		Thread.sleep(2000);
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.confirm()']"));
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been placed successfully", Vmsg);

		//Fetch the main balance after placing bet
		String Bbal1 = bal.getText();
		String beforebal1 = Bbal1.replaceAll(",", "");
		double d11=Double.parseDouble(beforebal1); 
		Assert.assertEquals(rbal, d11, 0.00);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-click='globalsicBoCtlr.CancelSlip()']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

		//Cancel alert popup generated with the slip details and cancel the bet
		driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]")).click();
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mgs.ng-binding")));		
		String Validmsg1 = driver.findElement(By.cssSelector(".mgs.ng-binding")).getText();
		String expectedMsg = "Successfully Cancelled";
		Assert.assertEquals(Validmsg1, expectedMsg);
		Thread.sleep(5000);

//		driver.findElement(By.id("/html/body/div[3]/div[1]/ui-view/section/section[2]/div[3]/div/h3/span[1]")).click(); 
		Thread.sleep(2000);

		//Fetch the main balance after cancelling bet
		WebElement Mbal1 = driver.findElement(By.id("sn_after_login_info_Amount"));
		System.out.println("Main balance after cancelling the placed bet: "+ Mbal1.getText());
		Assert.assertEquals(Mbal1.getText(), mbal);
		System.out.println("Before and after cancelling the bet and the main balance is same and verified");
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSicbo module link, place bet with selecting the stake amount$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSicbo_module_link_place_bet_with_selecting_the_stake_amount() throws Throwable {
	    
	}

	@Then("^Web:  Balance has to decuted respective with bet placed, and print slip has to be geneated$")
	public void web_Balance_has_to_decuted_respective_with_bet_placed_and_print_slip_has_to_be_geneated() throws Throwable {
	    
	}

	@Then("^Web: Cancel the bet placed and check with the same amount being updated to the main balance$")
	public void web_Cancel_the_bet_placed_and_check_with_the_same_amount_being_updated_to_the_main_balance() throws Throwable {
	    
	}

	@Then("^Web: Validation successful message for placing bet should be generated$")
	public void web_Validation_successful_message_for_placing_bet_should_be_generated() throws Throwable {
	    
	}

	@Then("^Web: After cancelling the placed bet validation message has to be generated and stake balance has be added to main account balance$")
	public void web_After_cancelling_the_placed_bet_validation_message_has_to_be_generated_and_stake_balance_has_be_added_to_main_account_balance() throws Throwable {
		Thread.sleep(2000);
	    driver.quit();
	}
}
