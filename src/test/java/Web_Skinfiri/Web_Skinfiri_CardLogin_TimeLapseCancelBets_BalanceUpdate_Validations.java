package Web_Skinfiri;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Web_SportBetting.Web_SportBetting_URL_CardLogin;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Skinfiri_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations extends Web_Skinfiri_URL_CardLogin {
	WebDriver driver;

	
	public Web_Skinfiri_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations() throws Exception {
		driver = Web_Skinfiri_URL_CardLogin();
		Thread.sleep(5000);
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, Skinfiri module, place bet, time period, balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_Skinfiri_module_place_bet_time_period_balance() throws Throwable {
	   
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
		
		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='DailyGameCtrl.changeAllStack()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("2");
		Thread.sleep(2000); 

		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivDgCartAlert")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		Thread.sleep(1500);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()='Cancel Slip ']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//div[@ng-if='cancelbutton']"));
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
		WebElement cls = driver.findElement(By.xpath("//div[@class='fa fa-times close_popup_JS']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(5000);

		//Fetch the balance and verify the amount being deducted 
		String abal = cardbal.getText();
		double numbal1 =Double.parseDouble(abal.replaceAll(",", ""));
		//Assert the balance deduction after the time lapse
		Assert.assertEquals(numbal, numbal1+6, 0.000);
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
		
		//class 'ng-scope sb_cart_disable' will be enabled if the cancel button is disabled. check class is enabled for first row in the account page
		WebElement row1cancel = driver.findElement(By.xpath("(//li[@class='ng-scope sb_cart_disable']/parent::ul[1]/li[8])[1]"));
		if (row1cancel.isEnabled()) {
			
			System.out.println("Cancel Buton Disabled in account page:" +row1cancel.isEnabled());
			System.out.println("Cancel button is not enabled in account page. Hence testcase is failed");
		} else
		{
			System.out.println("Cancel Buton Disabled in account page:" +row1cancel.isEnabled());
			System.out.println("Testcase failed as Cancel button is enabled in account page");
		}
	}

	@Then("^Web: Validate balance refresh functionality and bet status with time period for cancellation of bets via card login$")
	public void web_Validate_balance_refresh_functionality_and_bet_status_with_time_period_for_cancellation_of_bets_via_card_login() throws Throwable {
		driver.quit();
	}
}