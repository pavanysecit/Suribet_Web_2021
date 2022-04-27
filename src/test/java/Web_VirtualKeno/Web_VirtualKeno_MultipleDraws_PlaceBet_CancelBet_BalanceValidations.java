package Web_VirtualKeno;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_MultipleDraws_PlaceBet_CancelBet_BalanceValidations extends Web_VirtualKeno_URL_CardLogin{
WebDriver driver;

	public Web_VirtualKeno_MultipleDraws_PlaceBet_CancelBet_BalanceValidations() throws Exception {
		driver =Web_VirtualKeno_URL_CardLogin();
	}

	@Given("^Launch chrome browser, Enter Keno valid Url and Card Login$")
	public void launch_chrome_browser_Enter_Keno_valid_Url_and_Card_Login() throws Throwable 
	{
		
	}

	@When("^PlaceBetforMultipleDraw, CancelAll BetForMultipleDraw, Balance validation Before & After cancel Multiple Draw Bet$")
	public void placebetformultipledraw_CancelAll_BetForMultipleDraw_Balance_validation_Before_After_cancel_Multiple_Draw_Bet() throws Throwable 
	{
		System.out.println("<----------PlaceBet for Multiple Draw, Cancel Bet for Multiple Draw & Verify Main Balance-------->");
		Thread.sleep(1000);
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");	
		System.out.println("Main Balance After Login:" + MainBal);
		double BefPlaceBetBal=Double.parseDouble(MainBal);
		System.out.println("Before placing Bet Account Balance is:" + BefPlaceBetBal);
		Thread.sleep(2000); 
		
		//Select the Multiple upcoming draw# and click Quick picks , Enter the common stake and confirm the bet
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
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

		String ActualMsg = driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println("Validation message is:" + ActualMsg);
		Thread.sleep(1000);
		
		//Calculate Balance deduction and compare with main balance
		double Actbalance = BefPlaceBetBal - BetAmount;
		System.out.println("After placing Bet Account Balance is:" + Actbalance);
		Thread.sleep(1000);

		String BalAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String expBalanc = BalAmount.replaceAll("[^0-9.]", "");
		//double ActualLastwin1 = Integer.parseInt(expBalanc);
		double ExpectedMainBalance = Double.parseDouble(expBalanc);

		Assert.assertEquals(ExpectedMainBalance, Actbalance, 0.00);
		System.out.println("Main Balance verified after place bet: success");
				
		//Open cancel Slip pop up window   
		driver.findElement(By.xpath("//button[@ng-show='vrKenoCtrl.ShowCancel']")).click();
		Thread.sleep(2000);
			    
		//UnCheck All the slip id and try to cancel and see the validation message
		driver.findElement(By.xpath("//div[@class='modal-content ng-scope']/div/div[1]/div/div/label[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='modal-content ng-scope']/div/div[2]/div/div/label[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='modal-content ng-scope']/div/div[3]/div/div/label[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(2000);

		// Verify slip id selection validation
		String selectdraw = driver.findElement(By.xpath("//div[@ng-show='cancelbutton']")).getText();
		String ExpMsg = "Please select the slipId to cancel";
		Assert.assertEquals(selectdraw, ExpMsg);
		System.out.println("Validation message is:" + selectdraw);
		Thread.sleep(2000);

		// Now select the first slip id and cancel the partial bet and click on cancel button
		driver.findElement(By.xpath("//div[@class='modal-content ng-scope']/div/div[1]/div/div/label[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(3000);

		String Actualresult = driver.findElement(By.xpath("//*[@id='modal-success']/form/div[1]/b")).getText();
		String ExpectedResult = "Slip Successfully Cancelled";
		Assert.assertEquals(ExpectedResult, Actualresult);
		System.out.println("Cancel Bet Validation message is: " + Actualresult);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(2000);
				
		//cancel Remaining pending bets from Bet details page.	
		driver.findElement(By.xpath("//span[@class='ac_id']")).click();
		Thread.sleep(7000);

		driver.findElement(By.xpath("//*[@id='tbodyBetDetials']/ul[1]/li[8]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(7000);
		
		driver.findElement(By.xpath("//*[@id='tbodyBetDetials']/ul[2]/li[8]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='danger-btn ng-scope']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@ng-click='globalbetDetailsCtrl.BetDetailsback()']")).click();
		Thread.sleep(2000);
		
		System.out.println("Remaining bet has been cancelled from bet details page successfully");
		Thread.sleep(2000);
		
		//Verify Main Balance after cancel the bet		
		String MBalance = driver.findElement(By.id("sn_after_login_info_Amount")).getText();
		String ExpMainBalance = MBalance.replaceAll("[^0-9.]", "");
		Assert.assertEquals(ExpMainBalance, MainBal);
		System.out.println("Before PlaceBet & After cancelbet, Main Balance verified: Success");
		
	}
	

	@Then("^PlaceBetforMultipleDraw, Cancel Individual Bet and verify the balance validation$")
	public void placebetformultipledraw_Cancel_Individual_Bet_and_verify_the_balance_validation() throws Throwable 
	{
	   
	}

	@Then("^Logout and close the browser$")
	public void logout_and_close_the_browser() throws Throwable 
	{
		driver.findElement(By.xpath("//span[@ng-click='frm.Logout()']/a[1]")).click();
		Thread.sleep(2000);
		driver.quit();
	}
}