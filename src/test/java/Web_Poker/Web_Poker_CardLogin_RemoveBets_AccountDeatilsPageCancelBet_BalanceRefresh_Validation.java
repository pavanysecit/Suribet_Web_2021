package Web_Poker;

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

public class Web_Poker_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, hands and win pattern, stake amount, place bet, balance, no of rows, account details page, cancel bet alert, cancel the bet$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_hands_and_win_pattern_stake_amount_place_bet_balance_no_of_rows_account_details_page_cancel_bet_alert_cancel_the_bet() throws Throwable {

	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualPoker module link, change to card mode of payment$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualPoker_module_link_change_to_card_mode_of_payment() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement UN = driver.findElement(By.cssSelector(".fa.fa-angle-down.nav-dropbtn"));
		wait.until(ExpectedConditions.visibilityOf(UN));

		Actions actions = new Actions(driver);
		WebElement dropdown = driver.findElement(By.cssSelector(".fa.fa-angle-down.nav-dropbtn"));
		actions.moveToElement(dropdown).perform();
		Thread.sleep(3000);
		WebElement card = driver.findElement(By.xpath("//input[@ng-checked='PayMode==cardMode']"));
		System.out.println("Dropdown card mode selecting visible: " + card.isDisplayed());

		actions.moveToElement(card).click().build().perform();
		Thread.sleep(2000);

		// fill the card details
		driver.findElement(By.name("CustmerCardNumber")).sendKeys("2111649489988826");
		Thread.sleep(1000);
		driver.findElement(By.name("CustmerCardCardPin")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@ng-click='CustomerCard.$valid&&ValidateCard()']")).click();
		Thread.sleep(5000);

		WebElement ACCID = driver.findElement(By.xpath("//*[@class='ac_id']/a[3]"));
		Assert.assertEquals("4024953509", ACCID.getText());
		Thread.sleep(2000);
		System.out.println("Account login with assigned ACC-ID and verified: " + ACCID.getText());
		System.out.println("Login via Card is active");

		// Fetch the account balance
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualRoulette: " + mbal);

		// Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='CardBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll("[^0-9]", "");
		double d = Double.parseDouble(beforebal);

		// clicking on Draw details to place bet for the current draw
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
		String Ddetails = DrawNO.getText();
		System.out.println("Draw bid number with time details selected  " + Ddetails);
		DrawNO.click();
		Thread.sleep(2000);

		WebElement ChipDenomination5 = driver.findElement(By.xpath("//*[@class='bet-chip p-chip-3 p-chip']/span"));
		ChipDenomination5.click();
		Thread.sleep(2000);

		// Select the Numbers from the win Board top place the bet
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));
		WebElement hand3 = driver.findElement(By.xpath("//*[text()='1ST HAND']"));
		WebElement hand4 = driver.findElement(By.xpath("//*[text()='2ND HAND']"));
		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		hand3.click();
		Thread.sleep(1000);
		hand4.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		// Fetch the stake amount for the selected bets
		WebElement stakerows = driver
				.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div/span[2]"));
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		System.out.println("No of bet rows before removing: " + stakrows);

		// Remove 2 rows from selected bet
		WebElement remove = driver.findElement(By.xpath("//*[@ng-click='globalrouletteCtlr.Undo()']"));
		remove.click();
		Thread.sleep(1000);
		remove.click();
		Thread.sleep(1000);

		String Arows = stakerows.getText();
		String arows = Arows.replaceAll(",", "");
		System.out.println("No of bet rows after removing two bets: " + Arows);

		// To Validate the remove button functionality
		int num = 2;
		int num1 = Integer.parseInt(arows);
		int num2 = Integer.parseInt(stkrows);
		int res = num2 - num1;
		Assert.assertEquals(num, res);

		System.out.println("Validation is successfull and 2 rows have been removed from selected bets");

		WebElement Forward = driver
				.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.ClearFields()']"));
		WebElement Confirm = driver
				.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement back = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.ClearFields()']"));

		// FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		// Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding"));
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: " + Vmsg);
		Assert.assertEquals("Slips Generated Successfully", Vmsg);
		Thread.sleep(1500);

		// Cancel the slip from Account details page
		ACCID.click();
		Thread.sleep(5000);

		WebElement row1 = driver
				.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']"));
		System.out.println("1st row current bet placed information: " + row1);
		Date today = new Date();

		// displaying this date on UTC timezone
		DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm");
		df.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC = df.format(today);
		System.out.println("Date in Suriname Timezone (UTC-3) : " + UTC);

		WebElement rowcancel = driver.findElement(By.xpath("//i[@class ='fa fa-minus-circle']"));
		if (row1.getText().contains(UTC) && row1.getText().contains("Open")
				&& row1.getText().contains(String.valueOf(res))) {
			rowcancel.click();
			System.out.println("Data matching and clicked to cancel the bets");
			Thread.sleep(5000);
		} else {
			System.out.println("Data are mismatching");
			Thread.sleep(5000);
			// driver.quit();
		}

		// Validate the draw num selected for the rows to cancel the bet
		WebElement slipdate = driver.findElement(By.xpath(" //li[@class ='v_slip_mobile']/table/tbody/tr/td[2]"));
		System.out.println("Draw from from the Cancel Slip details: " + slipdate.getText());
		// Assert.assertEquals(alterDraw, slipdate.getText());
		System.out.println("Validation is successfull for cancelling the draw");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span[@ng-show='cancelbutton']")).click();
		Thread.sleep(1500);
		Alert alt = driver.switchTo().alert();
		alt.accept();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(3000);

		// Balance Verification upon cancellation of bet
		String abal = Mbal.getText();
		System.out.println("Main balance after cancelling the bet: " + abal);
		Assert.assertEquals(abal, mbal);
		System.out.println("Balance is refreshed and verified");
	}

	@Then("^Web:  Place as many as bets and check the bet rows before and after selecting the remove options$")
	public void web_Place_as_many_as_bets_and_check_the_bet_rows_before_and_after_selecting_the_remove_options(){

	}

	@Then("^Web: Forward and confirm the bets and validate the user message and direct to account details page$")
	public void web_Forward_and_confirm_the_bets_and_validate_the_user_message_and_direct_to_account_details_page() {

	}

	@Then("^Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in Virtual Poker module$")
	public void web_Cancel_the_placed_bet_and_accept_all_the_alerts_with_validation_messages_and_verify_the_amount_being_deposited_and_refreshed_after_cancelling_the_bets_in_Virtual_Poker_module(){
		driver.quit();
	}
}