package Web_SportBetting;

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

public class Web_SportBetting_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations extends Web_SportBetting_URL_CardLogin {
	WebDriver driver;
	
	public Web_SportBetting_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations() throws Exception {
		driver = Web_SportBetting_URL_CardLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, virtual SportsBetting module, place bet, time period, balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_SportsBetting_module_place_bet_time_period_balance() throws Throwable {

	}

	@When("^Web: Place bet and cancel bet after (\\d+)min and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance$")
	public void web_Place_bet_and_cancel_bet_after_min_and_validate_the_message_and_check_the_bet_status_after_time_lapse_cancellation_in_account_summary_tab_and_verify_the_updation_of_main_balance(int arg1) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACCID= driver.findElement(By.xpath("//a[@ng-bind='cardAccountId']"));
		wait.until(ExpectedConditions.visibilityOf(ACCID));

		Assert.assertEquals("4024953509", ACCID.getText());
		Thread.sleep(2000);
		System.out.println("Account login with assigned ACC-ID and verified: "+ ACCID.getText());
		System.out.println("Login via Card is active");

		//Get the main balance before placing bet
		WebElement cardbal = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']")); 
		String bbal = cardbal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));
		System.out.println("Account balance: "+ numbal);
		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		WebElement upmatch1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]"));

		upmatch.click();
		Thread.sleep(2000);
		upmatch1.click();
		Thread.sleep(2000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		WebElement Forward = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		Thread.sleep(1500);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()='Cancel Slip']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//*[text()='Cancel Slip']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));
		Thread.sleep(1*60*1000);
		Thread.sleep(2000);
		SlipCancel.click();
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		driver.switchTo().alert().accept();
		Thread.sleep(5000);

		WebElement validmsg1 = driver.findElement(By.xpath("//*[@ng-show='cancelbutton']"));
		String Cmsg = validmsg1.getText();
		System.out.println("Cancelled Slip user message: "+ Cmsg);
		Assert.assertEquals("Time limit Passed! Slip can not be cancelled!", Cmsg);
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("//div[@class='fa fa-times close_popup_JS ']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(5000);

		//Fetch the balance and verify the amount being deducted 
		String abal = cardbal.getText();
		double numbal1 =Double.parseDouble(abal.replaceAll(",", ""));
		//Assert the balance deduction after the time lapse
		Assert.assertEquals(numbal, numbal1+10, 0.000);
		System.out.println("Assertion passed while comparing the balance deductions");
		Thread.sleep(2000);

		ACCID.click();
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
		WebElement cancelbet = driver.findElement(By.xpath("//*[text()='Cancel Slip']"));
		cancelbet.click();
		Thread.sleep(2000);
		Alert promptAlert = driver.switchTo().alert();
		promptAlert .accept();
		Thread.sleep(3000);
		WebElement message = driver.findElement(By.xpath("//*[@ng-show='cancelbutton']"));
		Assert.assertEquals("Time limit Passed! Slip can not be cancelled!", message.getText());
		System.out.println("Placed bet is open and tried to cancel at the slip detail customized pop window and is verified");
		Thread.sleep(3000);
		//Close the pop up window
		WebElement closewindow = driver.findElement(By.xpath("//div[@class='fa fa-times close_popup_JS ']"));  
		closewindow.click();
		Thread.sleep(2000);

		System.out.println("Verified the cancel bet after time lapse in cancel slip page and account summary page and verified");
		System.out.println("Working as expected");

	}

	@Then("^Web: Validate balance refresh functionality and bet status with time period for cancelation of bets via card login$")
	public void web_Validate_balance_refresh_functionality_and_bet_status_with_time_period_for_cancelation_of_bets_via_card_login() throws Throwable {

		driver.quit();
	}
}
