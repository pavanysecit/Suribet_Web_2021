package Web_VirtualSicbo;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_OnlineLogin_TimeLapseCancelBets_BalanceUpdate_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSicbo_OnlineLogin_TimeLapseCancelBets_BalanceUpdate_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module,login via online method, place bet, time period, balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_login_via_online_method_place_bet_time_period_balance() throws Throwable {
	    
	}

	@When("^Web: Place bet and cancel bet after \"(.*?)\" and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance$")
	public void web_Place_bet_and_cancel_bet_after_and_validate_the_message_and_check_the_bet_status_after_time_lapse_cancellation_in_account_summary_tab_and_verify_the_updation_of_main_balance(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement acc = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(acc));

		//Get the main balance before placing bet
		WebElement cardbal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']")); 
		String bbal = cardbal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[7]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers from the Sicbo Board top place the bet 
		WebElement betType1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		WebElement betType2 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		WebElement betType3 = driver.findElement(By.xpath("//*[ text() = ' Odd ']"));
		WebElement betType4 = driver.findElement(By.xpath("//*[ text() = ' Even ']"));
		
		betType1.click();
		Thread.sleep(1000);
		betType2.click();
		Thread.sleep(1000);
		betType3.click();
		Thread.sleep(1000);
		betType4.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Roulette table");

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUAL SICBO
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
		Thread.sleep(1500);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-click='globalsicBoCtlr.CancelSlip()']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]"));
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
		driver.quit();
	}
}