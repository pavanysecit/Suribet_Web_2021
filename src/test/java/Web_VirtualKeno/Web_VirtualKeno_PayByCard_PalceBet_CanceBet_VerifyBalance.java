package Web_VirtualKeno;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_PayByCard_PalceBet_CanceBet_VerifyBalance extends Web_VirtualKeno_URL {
WebDriver driver;


	public Web_VirtualKeno_PayByCard_PalceBet_CanceBet_VerifyBalance() throws Exception {
		driver = Web_VirtualKeno_URL();
	}

	@Given("^Login: Launch chrome browser and Enter valid Url$")
	public void login_Launch_chrome_browser_and_Enter_valid_Url() throws Throwable 
	{
	
	}

	@When("^PlaceBet: Select the DrawID, Choose the Betting Numbers, Confirm the betting numbers, Enter valid stake$")
	public void placebet_Select_the_DrawID_Choose_the_Betting_Numbers_Confirm_the_betting_numbers_Enter_valid_stake() throws Throwable 
	{
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement NextDraw = driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]"));
		wait.until(ExpectedConditions.visibilityOf(NextDraw));
		//Select the upcoming draw#
		NextDraw.click();
		Thread.sleep(2000);
		//Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][58]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][60]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][69]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][78]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][80]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(2000);
		
	}

	@Then("^Then Select the PayByCard payment option and enter the card details proceed and Confirm bet$")
	public void then_Select_the_PayByCard_payment_option_and_enter_the_card_details_proceed_and_Confirm_bet() throws Throwable 
	{
		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_submit(true)']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//input[@name='CustmerCardNumber']")).sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='CustmerCardCardPin']")).sendKeys("1125");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(6000);
		
		
		WebElement PayByCardBalance = driver.findElement(By.xpath("//label[@class='col-form-label-lg text-warning col-sm-12 px-0 mx-0 border-bottom border-secondary ng-binding']"));
		String CardBalance=PayByCardBalance.getText();
		String BeforePlaceBetCardBalance = CardBalance.replaceAll("SRD", "");
		System.out.println("Before PlaceBet Card Balance is: "+ BeforePlaceBetCardBalance);
		
		driver.findElement(By.xpath("//button[@ng-click='VirtualKenoCustomercardSubmitFn()']")).click();
		Thread.sleep(2000);

		String ActualMsg = driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println("Validation message:" + ActualMsg);
		Thread.sleep(1000);
		
		// cancel the bet 	
		driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='CustmerCardNumber']")).sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='CustmerCardCardPin']")).sendKeys("1125");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(5000);

		String actual = driver.findElement(By.xpath("//*[@class='EmptyValidation_W_JS ng-pristine ng-valid']/div")).getText();
		String expected = "Slip Successfully Cancelled";
		Assert.assertEquals(actual, expected);
		System.out.println("Cancel Bet Validation: " + actual);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK' and text()='OK']")).click();
		Thread.sleep(3000);

		//Compare the card balance after cancel the bet
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();   //select Quick pick
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_submit(true)']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//input[@name='CustmerCardNumber']")).sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='CustmerCardCardPin']")).sendKeys("1125");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']")).click();
		Thread.sleep(5000);
	
		WebElement UpdatedCardBal = driver.findElement(By.xpath("//label[@class='col-form-label-lg text-warning col-sm-12 px-0 mx-0 border-bottom border-secondary ng-binding']"));
		String AftercancelCardBalance1=UpdatedCardBal.getText();
		driver.findElement(By.xpath("//button[@class='btn btn-orange text-white shadow-none w-100']")).click();
		Thread.sleep(1000);
		String AftercancelBetCardBalance = AftercancelCardBalance1.replaceAll("SRD", "");
		System.out.println("After Cancel the bet Card Balance: "+ AftercancelBetCardBalance);
		Assert.assertEquals(BeforePlaceBetCardBalance, AftercancelBetCardBalance);
		Thread.sleep(2000);
		System.out.println("Before place bet & After Cancel bet card balance is same");
					
	}

	@Then("^Cancel Bet: click on Cancel Slip button and enter the valid card details to cancel the bet successfully$")
	public void cancel_Bet_click_on_Cancel_Slip_button_and_enter_the_valid_card_details_to_cancel_the_bet_successfully() throws Throwable
	{
		driver.quit();
	}
}