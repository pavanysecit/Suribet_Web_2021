package Web_VirtualSicbo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation extends Web_VirtualSicbo_URL_OnlineLogin {
WebDriver driver;
	
	public Web_VirtualSicbo_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	} 
	
	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, Sicbo table, stake amount, place bet, balance, no of rows, account details page, cancel bet alert, cancel the bet$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_Sicbo_table_stake_amount_place_bet_balance_no_of_rows_account_details_page_cancel_bet_alert_cancel_the_bet() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
//		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement dropdown = driver.findElement(By.cssSelector(".fa.fa-angle-down.nav-dropbtn"));
		wait.until(ExpectedConditions.visibilityOf(dropdown));

		Actions actions = new Actions(driver);
		actions.moveToElement(dropdown).perform();
		Thread.sleep(2000);
		WebElement card = driver.findElement(By.xpath("//input[@ng-checked='PayMode==cardMode']")); 
		System.out.println("Dropdown card mode selecting visible: "+card.isDisplayed());
		//Mouse hover menuOption 'VIRTUAL SICBO'
		actions.moveToElement(card).click().build().perform();
		Thread.sleep(2000);

		// fill the card details
		driver.findElement(By.name("CustmerCardNumber")).
		sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.name("CustmerCardCardPin")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@ng-click='CustomerCard.$valid&&ValidateCard()']")).click();
		Thread.sleep(5000);

		WebElement ACCID= driver.findElement(By.xpath("//a[@ng-bind='cardAccountId']"));
		Assert.assertEquals("4024953509", ACCID.getText());
		Thread.sleep(2000);
		System.out.println("Account login with assigned ACC-ID and verified: "+ ACCID.getText());
		System.out.println("Login via Card is active");

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualRoulette: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[7]"));
		String Ddetails = DrawNO.getText();
		String alterStr=new StringBuilder(Ddetails).replace(10,18,"").toString();
		System.out.println("New Draw number format: "+ alterStr);
		//		String alterDraw = alterStr.replaceAll("#", "No :");
		String alterDraw = alterStr.replaceAll("[^0-9]","");
		System.out.println("Draw bid number with time details selected  "+Ddetails);
		//		Assert.assertEquals("Draw # 163 8:10AM", Ddetails);
		DrawNO.click();
		Thread.sleep(2000);

		//Selecting the bet number and placing the bet
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

		//Fetch the stake amount for the selected bets
		WebElement stakerows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div/span[2]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		System.out.println("No of bet rows before removing: "+ stakrows);

		//Remove 2 rows from selected bet
		WebElement remove = driver.findElement(By.xpath("//*[@ng-click='globalsicBoCtlr.ReplayCancel()']")); 
		remove.click();
		Thread.sleep(1000);
		remove.click();
		Thread.sleep(1000);

		String Arows = stakerows.getText();
		String arows = Arows.replaceAll(",", "");
		System.out.println("No of bet rows after removing two bets: "+ Arows);

		//To Validate the remove button functionality
		int num =2;
		int num1= Integer.parseInt(arows);
		int num2= Integer.parseInt(stkrows);
		int res = num2-num1;
		Assert.assertEquals(num, res);

		System.out.println("Validation is successfull and 2 rows have been removed from selected bets");


		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.confirm()']")).click();
		Thread.sleep(4000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been placed successfully", Vmsg);
		Thread.sleep(1500);

		//Cancel the slip from Account details page
		ACCID.click();
		Thread.sleep(5000);

		WebElement row1 = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']"));
		System.out.println("1st row current bet placed information: "+row1.getText());
		Date today = new Date();

		//displaying this date on UTC timezone
		DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm");
		df.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC = df.format(today);
		System.out.println("Date in Suriname Timezone (UTC-3) : " + UTC);

		WebElement rowcancel = driver.findElement(By.xpath("//i[@class ='fa fa-minus-circle']"));
		if(row1.getText().contains(UTC)  && row1.getText().contains("Open")  && row1.getText().contains(String.valueOf(res)) ) {
			rowcancel.click();
			System.out.println("Data matching and clicked to cancel the bets");
			Thread.sleep(5000);
		}
		else {
			System.out.println("Data are mismatching");
			Thread.sleep(5000);
			driver.quit();
		}

		//Validate the draw num selected for the rows to cancel the bet
		WebElement slipdate = driver.findElement(By.xpath("(//div[@class='slip_Details_w ng-scope']/table/tbody/tr/td[2])[1]"));
		System.out.println("Draw from from the Cancel Slip details: "+slipdate.getText());
		String alterDrawSlip = slipdate.getText().replaceAll("[^0-9]","");
		Assert.assertEquals(alterDraw, alterDrawSlip);
		System.out.println("Validation is successfull for cancelling the draw");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@ng-show='cancelbutton']")).click();
		Thread.sleep(1500);
		Alert alt = driver.switchTo().alert();
		alt.accept();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(3000);

		//Balance Verification upon cancellation of bet
		String abal = Mbal.getText();
		System.out.println("Main balance after cancelling the bet: "+ abal);
		Assert.assertEquals(abal, mbal);
		System.out.println("Balance is refreshed and verified");
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSicbo module link, change to card mode of payment$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSicbo_module_link_change_to_card_mode_of_payment() throws Throwable {
	   
	}

	@Then("^Web:  Place as many as bets and check the bet rows before and after selecting the remove options$")
	public void web_Place_as_many_as_bets_and_check_the_bet_rows_before_and_after_selecting_the_remove_options() throws Throwable {
	    
	}

	@Then("^Web: Forward and confirm the bets and validate the user message and direct to account details page$")
	public void web_Forward_and_confirm_the_bets_and_validate_the_user_message_and_direct_to_account_details_page() throws Throwable {
	    
	}

	@Then("^Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in Virtual Sicbo module$")
	public void web_Cancel_the_placed_bet_and_accept_all_the_alerts_with_validation_messages_and_verify_the_amount_being_deposited_and_refreshed_after_cancelling_the_bets_in_Virtual_Sicbo_module() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}
