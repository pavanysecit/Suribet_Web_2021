package Web_VirtualKeno;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_ReplayForSinglebet_ReplayforMultipleDraw extends Web_VirtualKeno_URL_CardLogin{
WebDriver driver;

	public Web_VirtualKeno_ReplayForSinglebet_ReplayforMultipleDraw() throws Exception {
		driver = Web_VirtualKeno_URL_CardLogin();
	}

	@Given("^launch the chrome browser, Enter the website url and login to online account$")
	public void launch_the_chrome_browser_Enter_the_website_url_and_login_to_online_account() throws Throwable 
	{
		
	}

	@When("^Place the single bet, Place one more bet to Cancel bet, then click on replay button and verify replay valid bet details$")
	public void place_the_single_bet_Place_one_more_bet_to_Cancel_bet_then_click_on_replay_button_and_verify_replay_valid_bet_details() throws Throwable 
	{	
		System.out.println("<-----------------------Replay Scenario's------------------------------->");
	
		//Get the Main balance
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");
		System.out.println("Main Balance After Login:" + MainBal);
		double BefPlaceBetBal = Double.parseDouble(MainBal);
		System.out.println("Before placing Bet Account Balance is:" + BefPlaceBetBal);
		Thread.sleep(2000); 
		
		//Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		//Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][3]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][5]")).click();
		Thread.sleep(1000);
	
		//Verify Confirm button is Enabled or Not	
		boolean ConfirmBtnEnabled = driver.findElement(By.xpath("//div[@class='confirm-btn']")).isEnabled();	
		Assert.assertTrue(ConfirmBtnEnabled);
		System.out.println("Verified Confirm button is Enabled");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(1000);  
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		
		//Get Total Stake Amount
		WebElement TotalStake=driver.findElement(By.xpath("//section[@ng-show='vrKenoCtrl.rightTabId == 1']/section[1]/div/table/tbody/tr[2]/td[3]"));
		String stake=TotalStake.getText();
		double BetAmount=Double.parseDouble(stake); 	
		
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ExpectedMsg, ActualMsg);
		System.out.println("First SingleBet:" + ActualMsg);
		Thread.sleep(1000);
		
		boolean cancelslip = driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).isDisplayed();
		Assert.assertTrue(cancelslip);
		Thread.sleep(1000);
		 
		 
		//Calculate Balance deduction and compare with main balance
		double Actbalance = BefPlaceBetBal - BetAmount;
		System.out.println("After placing Bet Account Balance is:" + Actbalance);
		Thread.sleep(1000);

		String BalAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String expBalanc = BalAmount.replaceAll("[^0-9.]", "");
		double ExpectedMainBalance = Double.parseDouble(expBalanc);

		Assert.assertEquals(ExpectedMainBalance, Actbalance, 0.00);
		System.out.println("Main Balance verified after place bet: success");
		Thread.sleep(2000);
		 
		driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);  
		 
		String getbetdata=driver.findElement(By.xpath("//table[@class='bet-data']/tbody/tr/td[2]")).getText();
		String getbetdataNumbers=getbetdata.replaceAll("[^0-9]", "");
		System.out.println("Previously Placed Betting Numbers are : "+getbetdataNumbers );
		    
		driver.findElement(By.xpath("//span[@ng-click='popupCtr.cancel()']")).click();
		Thread.sleep(3000);
		 
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(1000);
		 
		//Now placed one more bet successfully and cancel the bet (to verify valid replay bet details)  
		
		//Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][10]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][50]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][80]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(1000);  
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsg1=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg1 = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg1, ExpectedMsg1);
		System.out.println("Second Bet:" + ActualMsg);
		Thread.sleep(1000);
		
		boolean cancelslip1 = driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).isDisplayed();
		Assert.assertTrue(cancelslip1);
		Thread.sleep(1000);	
		 
		driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);  
		 
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(2000);	
		String Actualresult=driver.findElement(By.xpath("//*[@id='modal-success']/form/div[1]/b")).getText();
		String ExpectedResult="Slip Successfully Cancelled";
		Assert.assertEquals(ExpectedResult, Actualresult);
		System.out.println("Second Bet: " + Actualresult);
		Thread.sleep(2000);
			  
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(2000);	
			 	 
		 
		//Replay button.
	    driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Replay()']")).click();
	    Thread.sleep(3000);
	    
		//compare previously placed bet numbers and current replay bet numbers.   
	    String getReplayNumbers1=driver.findElement(By.xpath("//div[@class='d-flex justify-content-around py-2']")).getText();
	    String getReplayNumbers=getReplayNumbers1.replaceAll("[^0-9]", "");
	    System.out.println("Replay Betting Numbers are : "+getReplayNumbers );
		Assert.assertEquals(getbetdataNumbers, getReplayNumbers);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsg2=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg2 = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg2, ExpectedMsg2);
		System.out.println("Replay Bet Success:" + ActualMsg2);
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(1000);
	}

	@Then("^PlaceBetforMultipleDraw and verify the Replay button\\.$")
	public void placebetformultipledraw_and_verify_the_Replay_button() throws Throwable 
	{
		System.out.println("<-----------------PlaceBetforMultipleDraw and verify the Replay button------------>");
		
		//Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[2]")).click();
		Thread.sleep(1000);
		//Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][2]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(1000);
				
		// Select the 2nd upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[3]")).click();
		Thread.sleep(1000);
		// Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][8]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][9]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][7]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(1000); 
				
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println("Validation message is: " + ActualMsg);
		Thread.sleep(2000); 
				
		boolean cancelslip = driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).isDisplayed();
	    Assert.assertTrue(cancelslip);
	    Thread.sleep(1000);	
	    
	    driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
	    Thread.sleep(3000); 
	    
	    String getmultipledrawdata=driver.findElement(By.xpath("//div[2][@class='slip-list']/div[1]/table[2]/tbody/tr/td[2]")).getText();
	    String getmultipledrawdata1=getmultipledrawdata.replaceAll("[^0-9]", "");
	    System.out.println("Multiple draw last slip Betting Numbers are : "+getmultipledrawdata1 );
	    
	    driver.findElement(By.xpath("//span[@ng-click='popupCtr.cancel()']")).click();
	    Thread.sleep(3000);
				
		//Replay button.
	    driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Replay()']")).click();
	    Thread.sleep(3000);
		//compare previously placed bet numbers and current replay bet numbers.   
	    String getMultipledrawReplayNumbers=driver.findElement(By.xpath("//div[@class='d-flex justify-content-around py-2']")).getText();
	    String getReplayNumbers2=getMultipledrawReplayNumbers.replaceAll("[^0-9]", "");
		Assert.assertEquals(getmultipledrawdata1, getReplayNumbers2);
		System.out.println("Multiple bet Replay Betting Numbers are : "+getReplayNumbers2 );
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsgs=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsgs = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsgs, ExpectedMsgs);
		System.out.println("Replay for Multiple Draw-Success:" + ActualMsgs);
		Thread.sleep(2000); 
	}

	@Then("^Logout and quite the browser$")
	public void logout_and_quite_the_browser() throws Throwable 
	{
	   driver.quit();
	}
}