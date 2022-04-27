package Web_VirtualKeno;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_Online_PalceBet_CanceBet_VerifyBalance extends Web_VirtualKeno_URL_OnlineLogin{
WebDriver driver;

	public Web_VirtualKeno_Online_PalceBet_CanceBet_VerifyBalance() throws Exception {
		driver = Web_VirtualKeno_URL_OnlineLogin();
	}


	@Given("^Login: Launch chrome browser, Enter valid Url, Enter valid credential, PlaceBet, cancel Bet and verify balance update$")
	public void login_Launch_chrome_browser_Enter_valid_Url_Enter_valid_credential_PlaceBet_cancel_Bet_and_verify_balance_update() throws Throwable
	{
		//Get the Main balance
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");
		double BefPlaceBetBal = Double.parseDouble(MainBal);
		System.out.println("Before placing Bet Account Balance is:" + BefPlaceBetBal);
		Thread.sleep(2000); 
	
		//Place the bet
		//Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		// Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][3]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][5]")).click();
		Thread.sleep(2000);
		
		//verify confirm button is Enabled or not
		boolean ConfirmBtnEnabled = driver.findElement(By.xpath("//div[@class='confirm-btn']")).isEnabled();	
		Assert.assertTrue(ConfirmBtnEnabled);
		System.out.println("Verified Confirm button is Enabled");
		Thread.sleep(2000);
	
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(3000);
	
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);
		
		//Verify confirm button & Back Button is displayed or not
		WebElement Confirmbtn = driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_submit()']"));
		String ConfirmButton =Confirmbtn.getText();
		String actualConfirm = "CONFIRM";
		Assert.assertEquals(actualConfirm, ConfirmButton);
		System.out.println("Confirm Button is Displayed :"+ ConfirmButton);
		Thread.sleep(1000);
		
		WebElement back = driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']"));
		String backbtn =back.getText();
		String actualBack = "BACK";
		Assert.assertEquals(actualBack, backbtn);
		System.out.println("Back Button is Displayed :"+ backbtn);
		
		//Get Total Stake Amount
		WebElement TotalStake=driver.findElement(By.xpath("//section[@ng-show='vrKenoCtrl.rightTabId == 1']/section[1]/div/table/tbody/tr[2]/td[3]"));
		String stake=TotalStake.getText();
		double BetAmount=Double.parseDouble(stake); 	
		
		
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(4000);
		
		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println("Bet confirmation:" + ActualMsg );
		Thread.sleep(2000);
		
		boolean printslip = driver.findElement(By.xpath("//button[contains(text(), 'Print Slip')]")).isDisplayed();
	    Assert.assertTrue(printslip);
	    Thread.sleep(1000);	
	  
	    
	    //Calculate Balance deduction and compare with main balance
		double Actbalance = BefPlaceBetBal - BetAmount;
		System.out.println("After placing Bet Account Balance is:" + Actbalance);
		Thread.sleep(2000);
		
		String BalAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String expBalanc = BalAmount.replaceAll("[^0-9.]", "");	
		double ExpectedMainBalance=Double.parseDouble(expBalanc);
		Assert.assertEquals(ExpectedMainBalance, Actbalance, 0.00);
		System.out.println("Main Balance verified after place bet: success");

    
		//Cancel Slip
		driver.findElement(By.xpath("//span[@class='ac_id']")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id='tbodyBetDetials']/ul[1]/li[8]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(3000);
		String actual=driver.findElement(By.xpath("//div[@id='modal-success']/form/div/b")).getText();		
		String expected="Slip Successfully Cancelled";
		Assert.assertEquals(actual, expected);
		System.out.println("Cancel Bet Validation: " + actual);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='PrintSlipDet MsgPop_OK' and text()='OK']")).click();
		Thread.sleep(2000);
		
		//Verify Main Balance after cancel the bet		
		String MBalance = driver.findElement(By.id("sn_after_login_info_Amount")).getText();
		String ExpMainBalance = MBalance.replaceAll("[^0-9.]", "");
		Assert.assertEquals(ExpMainBalance, MainBal);
		System.out.println("Before PlaceBet & After cancelbet, Main Balance verified: Success");
	
	}
	

	@When("^PlaceBet: Select the DrawID, Choose the Betting Numbers, Confirm the betting numbers, Enter stake and Confirm bet, Verify Print slip and cancel slip buttons$")
	public void placebet_Select_the_DrawID_Choose_the_Betting_Numbers_Confirm_the_betting_numbers_Enter_stake_and_Confirm_bet_Verify_Print_slip_and_cancel_slip_buttons() throws Throwable
	{
		
	}

	@Then("^Cancel Bet: click on Cancel Slip button and cancel the bet successfully$")
	public void cancel_Bet_click_on_Cancel_Slip_button_and_cancel_the_bet_successfully() throws Throwable 
	{
	    
	}

	@Then("^Verify the Balance After bet has been cancelled$")
	public void verify_the_Balance_After_bet_has_been_cancelled() throws Throwable 
	{
	   
	}

	@Then("^Close the browser successfully$")
	public void close_the_browser_successfully() throws Throwable 
	{
	   driver.quit();
	}
}