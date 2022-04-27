package Web_VirtualSkinfiri;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualSkinfiri_PaybyCard_Place_Cancel_Bets_TimeLapseCancelBets_Validations extends Web_VirtualSkinfiri_URL{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual skinfiri module, place bet and pay the amount via card'PAY BY CARD' option$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_skinfiri_module_place_bet_and_pay_the_amount_via_card_PAY_BY_CARD_option() throws Throwable {
	    driver = Web_VirtualSkinfiri_URL();
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSkinfiri_module_link_and_place_bet() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[6]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers from the Skinfiri Board top place the bet 
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

		//Selecting the pay by card for placing the bet
		WebElement cardpay = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.PlaceBet(true)']"));
		Assert.assertEquals("PAY BY CARD", cardpay.getText());
		System.out.println("Pay by card text is validated :"+cardpay.getText());
		cardpay.click();
		Thread.sleep(2000);

		// fill the card details
		driver.findElement(By.name("CustmerCardNumber")).
		sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.name("CustmerCardCardPin")).sendKeys("1125");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(5000);

		WebElement paybycard = driver.findElement(By.xpath("//*[text()='Pay By Card']"));
		System.out.println("Pay by card pop-up is generated to confirm bet placing: "+paybycard.isDisplayed());
		Thread.sleep(2000);
		WebElement cardbal = driver.findElement(By.xpath("//label[@class='col-form-label-lg text-warning col-sm-12 px-0 mx-0 border-bottom border-secondary ng-binding']"));
		String bfbal= cardbal.getText();
		String Bfbal = cardbal.getText().replaceAll("SRD ", "");
		System.out.println("Card balance: "+ bfbal);

		WebElement confirmbet = driver.findElement(By.xpath("//*[text()='Confirm Bet']"));
		confirmbet.click();
		Thread.sleep(2500);


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

		// fill the card details
		driver.findElement(By.name("CustmerCardNumber")).
		sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.name("CustmerCardCardPin")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(4000);

		String Cmsg = Validmsg.getText();
		System.out.println("Cancelled Slip user message: "+ Cmsg);
		Assert.assertEquals("Successfully Cancelled", Cmsg);
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(2000);

		//Login via card details and validate the card balance before and after cancelling the bets
		wait.until(ExpectedConditions.visibilityOf(UN));
		Thread.sleep(2000);
		UN.click();
		UN.sendKeys("2111649489988826");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("1125");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(10000);
		WebElement cardbalance = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']")); 
		wait.until(ExpectedConditions.visibilityOf(cardbalance));
		System.out.println("After cancelling the bet and card balance is: "+ cardbalance.getText());
		//Fetch the card balance and assert the values 
		Assert.assertEquals(Bfbal, cardbalance.getText());
		driver.findElement(By.cssSelector(".log_off.click_effect_JS")).click();	
		Thread.sleep(5000);
	}

	@Then("^Web: Select 'PAY BY CARD' option and fill the card details and accept the pop-up$")
	public void web_Select_PAY_BY_CARD_option_and_fill_the_card_details_and_accept_the_pop_up() throws Throwable {
		//Login via card to load the card balance and logout 
		//Login via card details and validate the card balance before and after cancelling the bets
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);

		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));
		Thread.sleep(2000);
		UN.click();
		UN.sendKeys("2111649489988826");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("1125");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(10000);
		WebElement cardbalance = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']")); 
		wait.until(ExpectedConditions.visibilityOf(cardbalance));
		System.out.println("Fetch the card balance by login via card details: "+ cardbalance.getText());
		double numbal1 =Double.parseDouble(cardbalance.getText().replaceAll(",", ""));
		driver.findElement(By.cssSelector(".log_off.click_effect_JS")).click();
		Thread.sleep(7000);

		/*
		 * 1. Place bets and cancel the bet after 2 mins of time lapse
		 * 2. Verify the time lapse cancel bet validation
		 * 3. Verify in the account summary details and check the bets are been cancelled
		 * 4. Verify the amount after cancelling bet is being added back to card balance by login to virtual roulette via card details
		 */

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

		//Selecting the pay by card for placing the bet
		WebElement cardpay = driver.findElement(By.cssSelector(".slip_but_w.ng-scope"));
		Assert.assertEquals("PAY BY CARD", cardpay.getText());
		System.out.println("Pay by card text is validated :"+cardpay.getText());
		cardpay.click();
		Thread.sleep(2000);

		// fill the card details
		driver.findElement(By.name("CustmerCardNumber")).
		sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.name("CustmerCardCardPin")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(5000);

		WebElement paybycard = driver.findElement(By.xpath("//*[text()='Pay By Card']"));
		System.out.println("Pay by card pop-up is generated to confirm bet placing: "+paybycard.isDisplayed());
		Thread.sleep(2000);

		WebElement cardbal = driver.findElement(By.xpath("//label[@class='col-form-label-lg text-warning col-sm-12 px-0 mx-0 border-bottom border-secondary ng-binding']"));
		String bfbal= cardbal.getText();
		String Bfbal = cardbal.getText().replaceAll("SRD ", "");
		System.out.println("Card balance: "+ bfbal);

		WebElement confirmbet = driver.findElement(By.xpath("//*[text()='Confirm Bet']"));
		confirmbet.click();
		Thread.sleep(2500);

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		boolean Vmsg = Validmsg.getText().contains("Bet has been Placed Successfully");
		System.out.println("Placed bet successful user message displayed to user: "+ Vmsg);
		Assert.assertTrue(Vmsg);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-show='globalrouletteCtlr.CancelbtnShow']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//button[@class='CancelSlipBtn ng-scope']"));
		wait1.until(ExpectedConditions.visibilityOf(SlipCancel));
		Thread.sleep(2*60*1000);
		Thread.sleep(2000);
		SlipCancel.click();
		Thread.sleep(2000);

		// fill the card details
		driver.findElement(By.name("CustmerCardNumber")).
		sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.name("CustmerCardCardPin")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(4000);

		String Cmsg = Validmsg.getText();
		System.out.println("Cancelled Slip user message: "+ Cmsg);
		Assert.assertEquals("Time limit Passed! Slip(s) cannot be cancelled!", Cmsg);
		Thread.sleep(2000);


		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(5000);

		//Verify the card balance after time limit cancelling bet
		WebElement UN1 = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		Thread.sleep(2000);
		UN1.click();
		UN1.sendKeys("2111649489988826");
		Thread.sleep(1000);
		WebElement password1 = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password1.click();
		Thread.sleep(1000);
		password1.sendKeys("1125");
		Thread.sleep(1000);
		WebElement submit1 = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit1.click();
		Thread.sleep(10000);

		//Verify the balance deductions after time lapse
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']"));
		String bbal = bal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));
		System.out.println("Main balance after bet placed in previous bet: "+ bbal);

		//Assert the balance deduction after the time lapse
		Assert.assertEquals(numbal+2, numbal1, 0.000);
		System.out.println("Assertion passed while comparing the balance deductions");
	}

	@Then("^Web: Cancel the bet by click on the Cancel Slip button and fill the card details$")
	public void web_Cancel_the_bet_by_click_on_the_Cancel_Slip_button_and_fill_the_card_details() throws Throwable {
		//Verifying the recently placed bet is in open state in account summary tab section
		WebDriverWait wait = new WebDriverWait(driver, 120);

		WebElement acc = driver.findElement(By.className("ac_id"));
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

//		row1cancel.click();
//		Thread.sleep(2000);
//		WebElement cancelbet = driver.findElement(By.xpath("//span[@ng-show='cancelbutton']"));
//		Assert.assertFalse(cancelbet.isDisplayed());
//		Thread.sleep(2000);
//		cancelbet.click();
//		Thread.sleep(2000);
//		Alert promptAlert = driver.switchTo().alert();
//		promptAlert .accept();
//		Thread.sleep(2000);
//		WebElement message = driver.findElement(By.xpath("//b[@class='err_msg ng-binding']"));
//		Assert.assertEquals("Time limit Passed! Slip(s) cannot be cancelled!", message.getText());
//		System.out.println("Placed bet is open and tried to cancel at the slip detail customized pop window and is verified");
//		Thread.sleep(3000);
//
//		//Close the pop up window
//		WebElement closewindow = driver.findElement(By.xpath("//div[@ng-click='popupCtr.cancel()']"));  
//		closewindow.click();
//		Thread.sleep(2000);
//		row1cancel.click();
//		Thread.sleep(2000);
//		cancelbet.click();
//		Thread.sleep(8000);
//		//Assert.assertFalse(promptAlert.);
//		//Assert.assertEquals("Time limit Passed!", cancelbet.getText());
		System.out.println("After close and again open cancel button text has changed and verified the text and upon click bet is not cancelled and hencxe verified");
	}

	@Then("^Web: Successfull cancelling bet user message is displayed to user in virtual skinfiri module$")
	public void web_Successfull_cancelling_bet_user_message_is_displayed_to_user_in_virtual_skinfiri_module() throws Throwable {
	    
	}

	@Then("^Web: Again place bet and wait for (\\d+)mins and cancel the bets and check for 'time lapse' validation message and visibility of the cancel slip button$")
	public void web_Again_place_bet_and_wait_for_mins_and_cancel_the_bets_and_check_for_time_lapse_validation_message_and_visibility_of_the_cancel_slip_button(int arg1) throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}