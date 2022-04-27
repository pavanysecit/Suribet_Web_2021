package Web_SportBetting;

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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_SportBetting_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation() throws Exception {
		driver =Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, Roulette table, stake amount, place bet, balance, no of rows, account details page, cancel bet alert, cancel the bet$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_Roulette_table_stake_amount_place_bet_balance_no_of_rows_account_details_page_cancel_bet_alert_cancel_the_bet() throws Throwable {

	}

	@When("^Web: Login to suribet website with valid login details, Click on SportsBetting module link, change to card mode of payment$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_SportsBetting_module_link_change_to_card_mode_of_payment() throws Throwable {

		Actions actions = new Actions(driver);
		WebElement dropdown = driver.findElement(By.cssSelector(".fa.fa-angle-down.nav-dropbtn"));
		actions.moveToElement(dropdown).perform();
		Thread.sleep(2000);
		WebElement card = driver.findElement(By.xpath("//input[@ng-checked='PayMode==cardMode']")); 
		System.out.println("Dropdown card mode selecting visible: "+card.isDisplayed());

		//Mouse hover menuOption 'Sports Betting'
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
		System.out.println("Main balance after login to the sportbetting: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		WebElement upmatch1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]"));
		WebElement upmatch2 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[3]"));

		upmatch.click();
		Thread.sleep(2000);
		upmatch1.click();
		Thread.sleep(2000);
		upmatch2.click();
		Thread.sleep(2000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		//Fetch the stake amount for the selected bets
		WebElement stakerows = driver.findElement(By.xpath("//*[@class='bet-wrapper']/ul[2]/li[1]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		System.out.println("No of bet rows before removing: "+ stakrows);

		//Remove 2 rows from selected bet
		WebElement remove = driver.findElement(By.xpath("//*[@class='fa fa-close']")); 
		WebElement remove1 = driver.findElement(By.xpath("(//*[@class='fa fa-close'])[2]")); 
		remove.click();
		Thread.sleep(2000);
		remove1.click();
		Thread.sleep(2000);

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

		WebElement Forward = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));

		//FORWARD AND PLACE BET 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		Thread.sleep(1500);

		//Verify the amount being deducted after placing bet
		double rsbal = d-5;
		String Bbal1 = bal.getText();
		String beforebal1 = Bbal1.replaceAll(",", "");
		double rbal =Double.parseDouble(beforebal1);
		Assert.assertEquals(rbal, rsbal , 0.00);
		System.out.println("balance after in the main balance after placing bet is:" + rbal);

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

		driver.findElement(By.xpath("//div[@ng-if='cancelbutton']")).click();
		Thread.sleep(1500);
		Alert alt = driver.switchTo().alert();
		alt.accept();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".PrintSlipDet.MsgPop_OK")).click();
		Thread.sleep(4000);

		//Balance Verification upon cancellation of bet
		String abal = Mbal.getText();
		System.out.println("Main balance after cancelling the bet: "+ abal);
		Assert.assertEquals(abal, mbal);
		System.out.println("Card Balance is refreshed and verified");
	}

	@Then("^Web:  Place as many as bets and check the bet rows before and after selecting the remove options$")
	public void web_Place_as_many_as_bets_and_check_the_bet_rows_before_and_after_selecting_the_remove_options() throws Throwable {

	}

	@Then("^Web: Forward and confirm the bets and validate the user message and direct to account details page$")
	public void web_Forward_and_confirm_the_bets_and_validate_the_user_message_and_direct_to_account_details_page() throws Throwable {

	}

	@Then("^Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in Sports Betting module$")
	public void web_Cancel_the_placed_bet_and_accept_all_the_alerts_with_validation_messages_and_verify_the_amount_being_deposited_and_refreshed_after_cancelling_the_bets_in_Sports_Betting_module() throws Throwable {
		driver.quit();
	}
}