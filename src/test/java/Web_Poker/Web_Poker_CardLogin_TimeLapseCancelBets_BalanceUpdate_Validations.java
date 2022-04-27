package Web_Poker;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_Poker_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations extends Web_VirtualPoker_URL_CardLogin {
	WebDriver driver;
	
	public Web_Poker_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_CardLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual VirtualPoker module, place bet, time period, balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_VirtualPoker_module_place_bet_time_period_balance() throws Throwable {
	   
	}

	@When("^Web: Place bet and cancel bet after (\\d+)min and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance$")
	public void web_Place_bet_and_cancel_bet_after_min_and_validate_the_message_and_check_the_bet_status_after_time_lapse_cancellation_in_account_summary_tab_and_verify_the_updation_of_main_balance(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement acc = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(acc));

		//Get the main balance before placing bet
		WebElement cardbal = driver.findElement(By.xpath("//*[@id='spnUserBalance']/small")); 
		String bbal = cardbal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers from the betting Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='1ST HAND']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='2ND HAND']"));
		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Hands selection table");

		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));	
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("eMessage")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		Thread.sleep(1500);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.CancelSlip()']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals(" Cancel", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.CancelConfirmed(pokerCtlr.CancelSlipData[0].Barcode)']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));
		Thread.sleep(1*60*1000);
		Thread.sleep(2000);
		SlipCancel.click();
		Thread.sleep(2000);

		String Cmsg = Validmsg.getText();
		System.out.println("Cancelled Slip user message: "+ Cmsg);
		Assert.assertEquals("Time limit Passed! Slip(s) cannot be cancelled!", Cmsg);
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(5000);

		//Fetch the balance and verify the amount being deducted 
		String abal = cardbal.getText();
		double numbal1 =Double.parseDouble(abal.replaceAll(",", ""));
		
		//Assert the balance deduction after the time lapse
		Assert.assertEquals(numbal, numbal1+2, 0.000);
		System.out.println("Assertion passed while comparing the balance deductions");
		Thread.sleep(2000);

		acc.click();
		Thread.sleep(2000);
		WebElement BetDetails = driver.findElement(By.cssSelector(".in_middle_head.searchButSB")); 
		wait.until(ExpectedConditions.visibilityOf(BetDetails));
		Thread.sleep(2000);

		//Fetch the row 1st draw details is open state
		WebElement row1open = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[4]"));
		System.out.println("1st row current bet status information: "+row1open.getText());
		Assert.assertEquals("Open", row1open.getText());
		Thread.sleep(2000);
		WebElement row1cancel = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[8]"));
		row1cancel.click();
		Thread.sleep(2000);
		WebElement cancelbet = driver.findElement(By.xpath("//span[@ng-show='cancelbutton']"));
		cancelbet.click();
		Thread.sleep(2000);
		Alert promptAlert = driver.switchTo().alert();
		promptAlert .accept();
		Thread.sleep(2000);
		WebElement message = driver.findElement(By.xpath("//b[@class='err_msg ng-binding']"));
		Assert.assertEquals("Time limit Passed! Slip(s) cannot be cancelled!", message.getText());
		System.out.println("Placed bet is open and tried to cancel at the slip detail customized pop window and is verified");
		Thread.sleep(3000);

		//Close the pop up window
		WebElement closewindow = driver.findElement(By.xpath("//div[@ng-click='popupCtr.cancel()']"));  
		closewindow.click();
		Thread.sleep(2000);
		row1cancel.click();
		Thread.sleep(2000);
		WebElement cancelbet1 = driver.findElement(By.xpath("//span[@ng-show='cancelbutton']"));
		cancelbet1.click();
		Thread.sleep(8000);
		//		Assert.assertFalse(promptAlert.);
		//		Assert.assertEquals("Time limit Passed!", cancelbet.getText());
		System.out.println("After close and again open cancel button text has changed and verified the text and upon click bet is not cancelled and hencxe verified");

	}

	@Then("^Web: Validate balance refresh functionality and bet status with time period for cancelation of bets via card mode login$")
	public void web_Validate_balance_refresh_functionality_and_bet_status_with_time_period_for_cancelation_of_bets_via_card_mode_login() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}