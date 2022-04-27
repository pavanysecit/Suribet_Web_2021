package Web_VirtualKeno;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_SwitchToCard_PlaceBet_CancelBet_SwithToOnline_PlaceBetCanceBet extends Web_VirtualKeno_URL_OnlineLogin {
WebDriver driver;

	public Web_VirtualKeno_SwitchToCard_PlaceBet_CancelBet_SwithToOnline_PlaceBetCanceBet() throws Exception {
		driver = Web_VirtualKeno_URL_OnlineLogin();
	}

	@Given("^Open the chrome browser, Enter the website url and login to online account first$")
	public void open_the_chrome_browser_Enter_the_website_url_and_login_to_online_account_first() throws Throwable 
	{
		
	}

	@When("^SwitchToCard payment option, Place the bet and Cancel the bet$")
	public void switchtocard_payment_option_Place_the_bet_and_Cancel_the_bet() throws Throwable 
	{
		System.out.println("<--------------------SwitchToCard----------------->");
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down nav-dropbtn']")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//li[@ng-click='frm.PayModeFn(cardMode)']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@name='CustmerCardNumber']")).sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='CustmerCardCardPin']")).sendKeys("1125");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@ng-click='CustomerCard.$valid&&ValidateCard()']")).click();
		Thread.sleep(6000);

		//Once switch to card, Get the Main balance
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");	
		System.out.println("After Switched to card Main Balance:" + MainBal);
		double BefPlaceBetBal=Double.parseDouble(MainBal);
		System.out.println("Before placing Bet Card Balance is:" + BefPlaceBetBal);
		Thread.sleep(2000);
		
		// Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		// Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][58]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][59]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][60]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][69]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][79]")).click();
		Thread.sleep(2000);
		
		//Verify Confirm button is Enabled or Not
		boolean ConfirmBtnEnabled = driver.findElement(By.xpath("//div[@class='confirm-btn']")).isEnabled();	
		Assert.assertTrue(ConfirmBtnEnabled);
		System.out.println("Verified Confirm button is Enabled");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);
		
		//Get Total Stake Amount
		WebElement TotalStake=driver.findElement(By.xpath("//section[@ng-show='vrKenoCtrl.rightTabId == 1']/section[1]/div/table/tbody/tr[2]/td[3]"));
		String stake=TotalStake.getText();
		double BetAmount=Double.parseDouble(stake);
		
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ExpectedMsg, ActualMsg);
		System.out.println("SwitchToCard PlaceBet Status:" + ActualMsg);
		Thread.sleep(2000);
		
		//Calculate Balance deduction and compare with main balance
		double Actbalance = BefPlaceBetBal - BetAmount;
		System.out.println("After placing Bet Account Balance is:" + Actbalance);
		Thread.sleep(2000);

		String BalAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String expBalanc = BalAmount.replaceAll("[^0-9.]", "");
		double ExpectedMainBalance = Double.parseDouble(expBalanc);

		Assert.assertEquals(ExpectedMainBalance, Actbalance, 0.00);
		System.out.println("Main Balance verified after Switch to card & place bet: success");
		
		//Cancel Slip
	    driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(3000);
		String actual=driver.findElement(By.xpath("//*[@class='EmptyValidation_W_JS ng-pristine ng-valid']/div")).getText();		//Not working
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
		System.out.println("After cancel Bet Card main balace:" + ExpMainBalance);
		System.out.println("Before PlaceBet & After cancelbet, Card Main Balance verified: Success");
		
	}

	@Then("^SwitchToOnline Payment option, Place bet and cancel bet$")
	public void switchtoonline_Payment_option_Place_bet_and_cancel_bet() throws Throwable 
	{
		System.out.println("<--------------------SwitchToOnline----------------->");
		//Switch back to Online Account
	    driver.findElement(By.xpath("//i[@class='fa fa-angle-down nav-dropbtn']")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//li[@ng-click='frm.PayModeFn(OnlineMode)']")).click();  
	    Thread.sleep(2000);
	    
	    //Once switch Back to Online, Get the Main balance
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");
		System.out.println("After Switch Back to Online Account, Main Balance:" + MainBal);
		double BefPlaceBetBal = Double.parseDouble(MainBal);
		System.out.println("Before placing Bet Card Balance is:" + BefPlaceBetBal);
		Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
	    driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("5");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);
		
		//Get Total Stake Amount
		WebElement TotalStake=driver.findElement(By.xpath("//section[@ng-show='vrKenoCtrl.rightTabId == 1']/section[1]/div/table/tbody/tr[2]/td[3]"));
		String stake=TotalStake.getText();
		double BetAmount=Double.parseDouble(stake); 	

		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		//Thread.sleep(1000);
		//driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();     //////////////////Temprary
		Thread.sleep(2000);
		
		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ExpectedMsg, ActualMsg);
		System.out.println("SwitchToOnline Bet Status:" + ActualMsg);
		Thread.sleep(1000);
		
		//Calculate Balance deduction and compare with main balance
		double Actbalance = BefPlaceBetBal - BetAmount;
		System.out.println("After placing Bet Account Balance is:" + Actbalance);
		Thread.sleep(2000);

		String BalAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String expBalanc = BalAmount.replaceAll("[^0-9.]", "");
		double ExpectedMainBalance = Double.parseDouble(expBalanc);
		Assert.assertEquals(ExpectedMainBalance, Actbalance, 0.00);
		System.out.println("Online: Main Balance verified after place bet: success");
		Thread.sleep(2000);
		
		//Cancel Slip
	    driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(3000);
		String actual=driver.findElement(By.xpath("//*[@class='EmptyValidation_W_JS ng-pristine ng-valid']/div")).getText();		//Not working
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
		System.out.println("After cancel Bet Online main balace:" + ExpMainBalance);
		System.out.println("Before PlaceBet & After cancelbet, Online Account Main Balance verified: Success");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@ng-click='frm.Logout()']/a")).click();
		Thread.sleep(2000);
	    
	}

	@Then("^logout and close the browser window$")
	public void logout_and_close_the_browser_window() throws Throwable 
	{
	    driver.quit();
	}
}