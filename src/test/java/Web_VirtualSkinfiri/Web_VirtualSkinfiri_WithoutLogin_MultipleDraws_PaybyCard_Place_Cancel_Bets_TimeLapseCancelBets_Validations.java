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


public class Web_VirtualSkinfiri_WithoutLogin_MultipleDraws_PaybyCard_Place_Cancel_Bets_TimeLapseCancelBets_Validations extends Web_VirtualSkinfiri_URL{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, Virtual Skinfiri module, place bet and pay the amount via card'PAY BY CARD' option, multiple draaws and timelapse user message$")
	public void web_Chrome_browser_suribet_website_valid_URL_Virtual_Skinfiri_module_place_bet_and_pay_the_amount_via_card_PAY_BY_CARD_option_multiple_draaws_and_timelapse_user_message() throws Throwable {
		driver = Web_VirtualSkinfiri_URL();
		Thread.sleep(2000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on Virtual Skinfiri module link, multiple draws, place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_Virtual_Skinfiri_module_link_multiple_draws_place_bet() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));

		//clicking on Draw details to place bet 
		WebElement DrawNO = driver.findElement(By.xpath("//*[@ng-repeat='a in VDGCtr.vsGetDraws'][5]"));
		DrawNO.click();
		Thread.sleep(2000);

		WebElement Dcomb1 = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][8]"));
		WebElement Dcomb2 = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][9]"));

		Dcomb1.click();
		Thread.sleep(1000);
		Dcomb2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		//clicking on Draw details to place bet 
		WebElement DrawNO1 = driver.findElement(By.xpath("//*[@ng-repeat='a in VDGCtr.vsGetDraws'][9]"));
		DrawNO1.click();
		Thread.sleep(2000);

		WebElement Dcomb11 = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][5]"));
		WebElement Dcomb22 = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][6]"));

		Dcomb11.click();
		Thread.sleep(1000);
		Dcomb22.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");


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
		WebElement Validmsg = driver.findElement(By.className("EMessage"));
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-if='!hideBtn && !VDGCtr.CancelbtnShow']"));
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//*[@ng-disabled='VDGCtr.IsCancelBtnDisabled']"));
		wait1.until(ExpectedConditions.visibilityOf(SlipCancel));

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

		/*
		 * Message not displayed to user need to report 
		 */
		WebElement status = driver.findElement(By.xpath("//*[@ng-class='slip.Status']"));
		String Cmsg = status.getText();
		System.out.println("Cancelled Slip user message: "+ Cmsg);
		Assert.assertEquals("Cancelled", Cmsg);
		Thread.sleep(2000);



		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.modalFun(null)']"));
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
		WebElement cardbalance = driver.findElement(By.xpath("//*[@id='spnUserBalance']")); 
		wait.until(ExpectedConditions.visibilityOf(cardbalance));
		System.out.println("After cancelling the bet and card balance is: "+ cardbalance.getText());

		//Fetch the card balance and assert the values 
		Assert.assertEquals(Bfbal, cardbalance.getText().replaceAll("[^0-9.]", ""));
		driver.findElement(By.cssSelector(".log_off.click_effect_JS")).click();	
		Thread.sleep(5000);
	}

	@Then("^Web: Cancel the single bet by click on the Cancel Slip button and fill the card details$")
	public void web_Cancel_the_single_bet_by_click_on_the_Cancel_Slip_button_and_fill_the_card_details() throws Throwable {
	    
	}

	@Then("^Web: Again place bet and wait for (\\d+)mins and cancel the bets and check for 'time lapse' validation message and visibility of the cancel slip button in the multiple draws selection$")
	public void web_Again_place_bet_and_wait_for_mins_and_cancel_the_bets_and_check_for_time_lapse_validation_message_and_visibility_of_the_cancel_slip_button_in_the_multiple_draws_selection(int arg1) throws Throwable {
	    driver.quit();
	}
}