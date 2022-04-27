package Web_VirtualRoulette;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualRoulette_MulripleDraws_PlaceBet_CancelBet_Validations extends Web_VirtualRoulette_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualRoulette_MulripleDraws_PlaceBet_CancelBet_Validations() throws Exception {
		driver =Web_VirtualRoulette_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module, place bet selecting multiple draws$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_place_bet_selecting_multiple_draws() throws Throwable {

	}

	@When("^Web: Cancel slip according to draw numbers individually and validate the balance and cancel slip remaining draws$")
	public void web_Cancel_slip_according_to_draw_numbers_individually_and_validate_the_balance_and_cancel_slip_remaining_draws() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		//Fetch the main balance 
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String bbal = bal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));
		System.out.println("Main balance before bet placing: "+ bbal);

		//clicking on Draw details to place bet for the selected draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[8]"));
		String Ddetails = DrawNO.getText();
		System.out.println("First draw selected: "+ Ddetails);

		int draw1 = Ddetails.length();
		System.out.println("String length of draw details:"+ draw1);
		String dr1 = null; 
		if(draw1==16)
		{
			dr1= Ddetails.substring(7, 9);	
		}else if(draw1==17) {
			dr1= Ddetails.substring(7, 10);
		}else if(draw1==18) {
			dr1= Ddetails.substring(7, 10);
		}
		System.out.println("Extract the draw number: "+ dr1);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick2 = driver.findElement(By.xpath("//span[@bettypepickid='3']"));
		WebElement Pick28 = driver.findElement(By.xpath("//span[@bettypepickid='29']"));
		WebElement Pick13 = driver.findElement(By.xpath("//span[@bettypepickid='14']"));
		WebElement Pick19 = driver.findElement(By.xpath("//span[@bettypepickid='20']"));

		DrawNO.click();
		Thread.sleep(3000);
		Pick2.click();
		Thread.sleep(1000);
		Pick28.click();
		Thread.sleep(1000);
		Pick13.click();
		Thread.sleep(1000);
		Pick19.click();
		Thread.sleep(1000);
		WebElement t1stake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]"));
		System.out.println("Bets selected with"+dr1+" draw num with total stake is"+t1stake.getText());

		//clicking on Draw details to place bet for the selected draw 
		WebElement DrawNO1 = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[10]"));
		String Ddetails1 = DrawNO1.getText();
		System.out.println("Second draw selected: "+ Ddetails1);
		//		String d2 = Ddetails1.substring(7, 10);
		//		System.out.println("Extract the draw number: "+ d2);

		int draw2 = Ddetails1.length();
		System.out.println("String length of draw details: "+ draw2);
		String dr2 = null; 
		if(draw2==16)
		{
			dr2= Ddetails1.substring(7, 9);	
		}else if(draw2==17) {
			dr2= Ddetails1.substring(7, 10);
		}else if(draw2==18) {
			dr2= Ddetails1.substring(7, 10);
		}
		
		System.out.println("Extract the draw number: "+ dr2);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick5 = driver.findElement(By.xpath("//span[@bettypepickid='6']"));
		WebElement Pick14 = driver.findElement(By.xpath("//span[@bettypepickid='15']"));
		WebElement Pick29 = driver.findElement(By.xpath("//span[@bettypepickid='30']"));

		DrawNO1.click();
		Thread.sleep(3000);
		Pick5.click();
		Thread.sleep(1000);
		Pick14.click();
		Thread.sleep(1000);
		Pick29.click();
		Thread.sleep(1000);
		System.out.println("Total stakes with both draw numbers combined together: "+t1stake.getText());
		WebElement tstake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]"));
		System.out.println("Stake amount: "+ tstake.getText());
		double numtstake = Double.parseDouble(tstake.getText().replaceAll(",", ""));

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed with multiple draws");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Slips Generated Successfully", Vmsg);

		String abal = bal.getText();
		double afternumbal =Double.parseDouble(abal.replaceAll(",", ""));
		System.out.println("Main balance after bet placing: "+ abal);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-show='globalrouletteCtlr.CancelbtnShow']"));
		String cancel = Cancel.getText();
		Assert.assertEquals("Cancel Slip", cancel);
		System.out.println("Cancel Slip text button is verified: "+ cancel+ " "+ Cancel.isDisplayed());
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement CancelAll = driver.findElement(By.cssSelector(".vR_Slip_Cancel.vR_SlipAll_Cancel"));
		wait.until(ExpectedConditions.visibilityOf(CancelAll));
		Assert.assertEquals("Cancel All", CancelAll.getText());
		System.out.println("Cancel All slips button is displayed and the text is verified: "+ CancelAll.isDisplayed());

		//Verify the main balance and the balance after placing bet 
		Assert.assertEquals(numbal, (afternumbal+numtstake), 0.000);
		System.out.println("Balance after and before placing bet is verified");

		WebElement Draw1 = driver.findElement(By.xpath("(//div[@ng-repeat=\"(key, value) in globalrouletteCtlr.SlipDetailsInfoToCancel | groupBy: 'SlipID'\"])[1]/table/tbody/tr/td[2]")); 
		System.out.println(Draw1.getText());
		int d11 = Draw1.getText().length();
		System.out.println("Draw number length from cancel bet window: "+d11);
		String d111 = null; 
		if(d11==12)
		{
			d111= Draw1.getText().substring(10, 12);	
		}else if(d11==13) {
			d111= Draw1.getText().substring(10, 13);
		}
		System.out.println("Extract the draw number: "+ d111);
		Assert.assertEquals(dr1, d111);
		System.out.println("Draw number are verified from both bet selection and cancel slip window: "+ d111 );


		Thread.sleep(2000);
		WebElement Draw2 = driver.findElement(By.xpath("(//div[@ng-repeat=\"(key, value) in globalrouletteCtlr.SlipDetailsInfoToCancel | groupBy: 'SlipID'\"])[2]/table/tbody/tr/td[2]")); 
		System.out.println(Draw2.getText());
		int d22 = Draw1.getText().length();
		String d222 = null; 
		if(d22==12)
		{
			d222= Draw2.getText().substring(10, 12);	
		}else if(d22==13) {
			d222= Draw2.getText().substring(10, 13);
		}
		System.out.println("Extract the draw number: "+ d222);
		Assert.assertEquals(dr2, d222);
		System.out.println("Draw number are verified from both bet selection and cancel slip window: "+ d222 );
		Thread.sleep(2000);
		System.out.println("$$$$$$$$$$$$$$$$$$$");
		System.out.println("\n");

		//Fetch the stake for the draw numbers
		WebElement Stake1 = driver.findElement(By.xpath("(//table[@class='vR_Slip_table vR_SlipTextCenter '])[3]/tbody/tr/td[2]")); 
		String dr1stk = Stake1.getText().substring(13, 15);
		System.out.println("Extracting the total stake from draw: "+dr1stk);
		double vdr1stk = Double.parseDouble(dr1stk.replaceAll(",", ""));
		WebElement Stake2 = driver.findElement(By.xpath("(//table[@class='vR_Slip_table vR_SlipTextCenter '])[5]/tbody/tr/td[2]")); 

		String dr2stk = Stake2.getText().substring(13, 15);
		double vdr2stk = Double.parseDouble(dr2stk.replaceAll(",", ""));
		System.out.println("Extracting the total stake from draw: "+dr2stk);
		System.out.println("######################################");

		double verify = vdr1stk+vdr2stk;
		Assert.assertEquals((numbal-verify), (numbal-numtstake), 0.000);
		System.out.println("Verified the stakes from cancel slip and the balance deducted from the main: "+ (numbal-verify));

		//Cancel bet individually and verify the balance refresh is taken place and individual cancelled bet stake should add to main balance
		WebElement cancelDr1 = driver.findElement(By.xpath("(//span[@class='vR_Slip_Cancel'])[1]"));
		WebElement cancelDr2 = driver.findElement(By.xpath("(//span[@class='vR_Slip_Cancel'])[2]"));
		System.out.println("Balance Before cancelling the draw: "+afternumbal );
		//Cancel the draw1 
		cancelDr1.click();
		Thread.sleep(2000);

		//wait for the cancelled bet successfull valid user message
		WebElement validcancelmsg = driver.findElement(By.cssSelector(".vR_Can_Slip_txt.ng-binding"));
		wait.until(ExpectedConditions.visibilityOf(validcancelmsg));
		Assert.assertEquals("Successfully Cancelled", validcancelmsg.getText());
		System.out.println("Cancel bet succesfull message: "+ validcancelmsg.getText() +"and is displayed to user "+ validcancelmsg.isDisplayed());

		//Wait for the main balance to refresh
		Thread.sleep(5000);
		Assert.assertEquals(numbal-vdr2stk, (afternumbal+vdr1stk), 0.000);
		System.out.println("Verified the main balance refreshed after a draw is cancelled in betting slip: " + (afternumbal+vdr1stk));

		//Verify the cancel slip button is not displayed to user after cabcelling draw
		Assert.assertFalse(cancelDr1.isDisplayed());
		System.out.println("Cancel slip button is not displayed to user");

		//Try cancelling the draw after a minute to check for the time limit 
		Thread.sleep(1*60*1000);
		//Cancel the draw2
		cancelDr2.click();
		Thread.sleep(2000);
		WebElement validcancelmsg1 = driver.findElement(By.xpath("//td[@class='vR_Can_Slip_txt ng-binding']"));
		wait.until(ExpectedConditions.visibilityOf(validcancelmsg1));
		Assert.assertEquals("Time limit Passed! Slip(s) cannot be cancelled!", validcancelmsg1.getText());
		System.out.println("Cancel bet not succesfull message: "+ validcancelmsg1.getText() +"and is displayed to user "+ validcancelmsg1.isDisplayed());

		//Verify the balance is not updated after cancelling draw after exceeding time limit
		//Wait for the main balance to refresh
		Thread.sleep(5000);
		Assert.assertEquals(numbal-vdr2stk, (afternumbal+vdr1stk), 0.000);
		System.out.println("Time lapse user message: " + (afternumbal+vdr1stk));

		//Verify the cancel slip button is not displayed to user after cabcelling draw
		Assert.assertFalse(cancelDr1.isDisplayed());
		System.out.println("Cancel slip button is not displayed to user");

		//Close the cancel slip window
		driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]")).click();
		Thread.sleep(3000);
	}

	@Then("^Web: Validate balance refresh functionality and added to main balance after cancelling the slips$")
	public void web_Validate_balance_refresh_functionality_and_added_to_main_balance_after_cancelling_the_slips() throws Throwable {


		/*
		 * Verifying the objective of Cancel All button under multiple draws with multiple bets placed
		 * steps
		 * 1. Place multiple draws
		 * 2. Click on cancel slip
		 * 3. Click on Cancel All button 
		 * 4. Verify the draw is cancelled and amount is refund or refreshed to main balance
		 * 5. Cross Verify the draws is cancelled at account summary details
		 */

		//Fetch the main balance 
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String bbal = bal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));
		System.out.println("Main balance before bet placing: "+ bbal);

		//clicking on Draw details to place bet for the selected draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[12]"));
		String Ddetails = DrawNO.getText();
		System.out.println("First draw selected: "+ Ddetails);

		int draw1 = Ddetails.length();
		System.out.println("String length of draw details:"+ draw1);
		String dr1 = null; 
		if(draw1==16)
		{
			dr1= Ddetails.substring(7, 9);	
		}else if(draw1==17) {
			dr1= Ddetails.substring(7, 10);
		}
		System.out.println("Extract the draw number: "+ dr1);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick5 = driver.findElement(By.xpath("//span[@bettypepickid='6']"));
		WebElement Pick10 = driver.findElement(By.xpath("//span[@bettypepickid='11']"));
		WebElement Pick15 = driver.findElement(By.xpath("//span[@bettypepickid='16']"));

		DrawNO.click();
		Thread.sleep(3000);
		Pick5.click();
		Thread.sleep(1000);
		Pick10.click();
		Thread.sleep(1000);

		WebElement t1stake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]"));
		System.out.println("Bets selected with"+dr1+" draw num with total stake is"+t1stake.getText());

		//clicking on Draw details to place bet for the selected draw 
		WebElement DrawNO1 = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[10]"));
		String Ddetails1 = DrawNO1.getText();
		System.out.println("Second draw selected: "+ Ddetails1);

		int draw2 = Ddetails1.length();
		System.out.println("String length of draw details: "+ draw2);
		String dr2 = null; 
		if(draw2==16)
		{
			dr2= Ddetails1.substring(7, 9);	
		}else if(draw2==17) {
			dr2= Ddetails1.substring(7, 10);
		}
		System.out.println("Extract the draw number: "+ dr2);

		DrawNO1.click();
		Thread.sleep(3000);
		Pick15.click();
		Thread.sleep(1000);

		System.out.println("Total stakes with both draw numbers combined together: "+t1stake.getText());
		WebElement tstake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]"));
		System.out.println("Stake amount: "+ tstake.getText());
		double numtstake = Double.parseDouble(tstake.getText().replaceAll(",", ""));

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed with multiple draws");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Slips Generated Successfully", Vmsg);
		Thread.sleep(2000);

		String abal = bal.getText();
		double afternumbal =Double.parseDouble(abal.replaceAll(",", ""));
		System.out.println("Main balance after bet placing: "+ abal);
		Thread.sleep(2000);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-show='globalrouletteCtlr.CancelbtnShow']"));
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement CancelAll = driver.findElement(By.cssSelector(".vR_Slip_Cancel.vR_SlipAll_Cancel"));
		wait.until(ExpectedConditions.visibilityOf(CancelAll));
		Assert.assertEquals("Cancel All", CancelAll.getText());
		System.out.println("Cancel All slips button is displayed and the text is verified: "+ CancelAll.isDisplayed());
		CancelAll.click();
		Thread.sleep(5000);

		//Verify the main balance and the balance after placing bet 
		Assert.assertEquals(numbal, (afternumbal+numtstake), 0.000);
		System.out.println("Balance after cancelling all the draws and amount deposited to main balance");

		Assert.assertFalse(CancelAll.isDisplayed());
		System.out.println("CancelAll slip button is not displayed to user");
		//Close the cancel slip window
		driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]")).click();
		Thread.sleep(3000);

		//Cancel the slip from Account details page
		WebElement ACCID= driver.findElement(By.className("ac_id"));
		ACCID.click();
		Thread.sleep(5000);

		//Fetch the row 1st draw details is cancelled state
		WebElement row1 = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[4]"));
		System.out.println("1st row current bet status information: "+row1.getText());
		Assert.assertEquals("Cancelled", row1.getText());

		WebElement row2 = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows'][2]/li[4]"));
		System.out.println("2nd row current bet status information: "+row2.getText());
		Assert.assertEquals("Cancelled", row2.getText());
		System.out.println("Bets cancelled and reflected in account summary details");
		Thread.sleep(2000);
		driver.quit();
	}
}