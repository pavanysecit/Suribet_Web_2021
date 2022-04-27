package Web_VirtualSkinfiri;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_OnlineLogin_TimeLapseCancelBets_BalanceUpdate_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSkinfiri_OnlineLogin_TimeLapseCancelBets_BalanceUpdate_Validations() throws Exception {
		driver =Web_VirtualSkinfiri_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual skinfiri module,login via online method, place bet, time period, balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_skinfiri_module_login_via_online_method_place_bet_time_period_balance() throws Throwable {
	    
	}

	@When("^Web: Place bet and cancel bet after '(\\d+)min' and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance$")
	public void web_Place_bet_and_cancel_bet_after_min_and_validate_the_message_and_check_the_bet_status_after_time_lapse_cancellation_in_account_summary_tab_and_verify_the_updation_of_main_balance(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		String mbal = Mbal.getText().replaceAll("[^0-9]", "");
		System.out.println("Main balance after login to the skinfiri: "+ mbal);
		double d =Double.parseDouble(mbal.replaceAll(",", ""));

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[6]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[4]"));
		WebElement Pick6 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[6]"));

		Pick3.click();
		Thread.sleep(1000);
		Pick6.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the number table");

		//Input the stake amount 
		WebElement stkfield = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeTotalFun()']")); 
		stkfield.click();
		Thread.sleep(1000);
		stkfield.sendKeys("1");
		Thread.sleep(2000);

		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal );

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.PlaceBet(false)']"));
		Thread.sleep(2000);
		Forward.click();
		Thread.sleep(2000);
		
		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		boolean Vmsg = Validmsg.getText().contains("Bet has been Placed Successfully");
		System.out.println("Placed bet successful user message displayed to user: "+ Vmsg);
		Assert.assertTrue(Vmsg);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.VDGCancelSlipDetails(VDGCtr.Cancel_SlipId)']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//button[@class='CancelSlipBtn ng-scope']"));
		wait1.until(ExpectedConditions.visibilityOf(SlipCancel));
		Thread.sleep(1*60*1000);
		Thread.sleep(2000);
		SlipCancel.click();
		Thread.sleep(2000);

		WebElement vmsg = driver.findElement(By.xpath("//*[@class='id_date_wrapper cancelSlip_popup']/div/span[3]"));
		String Cmsg = vmsg.getText();
		System.out.println("Cancelled Slip user message: "+ Cmsg);
		Assert.assertEquals("Time limit Passed! Slip(s) can not be cancelled!", Cmsg);
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("//span[@class='SCAN_close']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(5000);

		//Fetch the balance and verify the amount being deducted 
		String abal = Mbal.getText().replaceAll("[^0-9]", "");
		double numbal1 =Double.parseDouble(abal.replaceAll(",", ""));
		//Assert the balance deduction after the time lapse
		Assert.assertEquals(d, numbal1+2, 0.000);
		System.out.println("Assertion passed while comparing the balance deductions");
		Thread.sleep(2000);

		WebElement acc = driver.findElement(By.xpath("//*[@class='ac_id']")); 
		acc.click();
		Thread.sleep(2000);
		WebElement BetDetails = driver.findElement(By.cssSelector(".in_middle_head.searchButSB")); 
		wait.until(ExpectedConditions.visibilityOf(BetDetails));

		//Fetch the row 1st draw details is open state
		WebElement row1open = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[4]"));
		System.out.println("1st row current bet status information: "+row1open.getText());
		Assert.assertEquals("Open", row1open.getText());
		Thread.sleep(2000);
		WebElement row1cancel = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[8]"));
		Assert.assertEquals(row1cancel.getAttribute("class"), "ng-scope sb_cart_disable");
		System.out.println("After close and again open cancel button text has changed and verified the text and upon click bet is not cancelled and hencxe verified");
	}

	@Then("^Web: Validate balance refresh functionality and bet status with time period lapse for cancelation of bets via online login mode of transcations$")
	public void web_Validate_balance_refresh_functionality_and_bet_status_with_time_period_lapse_for_cancelation_of_bets_via_online_login_mode_of_transcations() throws Throwable {
	    Thread.sleep(3000);
	    driver.quit();
	}
}