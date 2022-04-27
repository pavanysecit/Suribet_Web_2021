package Web_Poker;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Poker_PlaceBet_CancelBet_BalanceRefresh_Validations extends Web_VirtualPoker_URL_OnlineLogin {
WebDriver driver;
	
	public Web_Poker_PlaceBet_CancelBet_BalanceRefresh_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, Draw details, hands, stake amount, place bet, balance, cancel bet and print slip$")
	public void web_Chrome_browser_suribet_website_valid_URL_Draw_details_hands_stake_amount_place_bet_balance_cancel_bet_and_print_slip() throws Throwable {
	    
	}
	
	@When("^Web: Login to suribet website with valid login details, Click on VirtualPoker module link, place bet with selecting the stake amount by selecting the hands$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualPoker_module_link_place_bet_with_selecting_the_stake_amount_by_selecting_the_hands() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		Thread.sleep(2000);

		// Fetch the account balance 
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualPoker: "+ mbal);

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.id("sn_after_login_info_Amount"));
		String Bbal = bal.getText();
		String str = Bbal.replaceAll("[^0-9.]", "");
		double d =Double.parseDouble(str);

		//SRD validations
		String Vstring ="SRD";
		Boolean vstr = Bbal.contains(Vstring);
		Assert.assertTrue(vstr);
		System.out.println("SRD contains in the balance currency section: "+ vstr);

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
		String Ddetails = DrawNO.getText();
		System.out.println("Draw bid number with time details selected  "+Ddetails);
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers from the Roulette Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("(//*[@class='px-0 col-4 text-center d-flex flex-column'])[3]/span[2]")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 

		double rbal = d-betamount;
		System.out.println("Main balance amount after deductions: "+ rbal );

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.ClearFields()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement back = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.ClearFields()']"));

		//Verify the text on the button 
		Assert.assertEquals("FORWARD", Forward.getText());
		Assert.assertEquals("CLEAR ALL", Clear.getText());
		System.out.println("Text button validation is successfull");

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.className("EMessage")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);

		//Fetch the main balance after placing bet
		String Bbal1 = bal.getText();
		String beforebal1 = Bbal1.replaceAll("[^0-9.]", "");
		double d11=Double.parseDouble(beforebal1); 
		Assert.assertEquals(rbal, d11, 0.00);
		System.out.println("Balance deduction validation successfull");

		//Cancel via account as the cancel button functionality is not working
		WebElement acc = driver.findElement(By.className("ac_id"));
		acc.click();
		Thread.sleep(4000);

		WebElement row1 = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']"));
		System.out.println("1st row current bet placed information: "+row1);
		Date today = new Date();

		//displaying this date on UTC timezone
		DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm");
		df.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC = df.format(today);
		System.out.println("Date in Suriname Timezone (UTC-3) : " + UTC);

		WebElement rowcancel = driver.findElement(By.xpath("//li[@class ='fa fa-print ng-scope']/parent::ul/li[8]"));
		if(row1.getText().contains(UTC)  && row1.getText().contains("InProgress")) {
			rowcancel.click();
			System.out.println("Data matching and clicked to cancel the bets");
			Thread.sleep(5000);
		}
		else {
			System.out.println("Data are mismatching");
			Thread.sleep(5000);
			//driver.quit();
		}

		//Validate the draw num selected for the rows to cancel the bet
		driver.findElement(By.xpath("//div[@ng-if='cancelbutton']")).click();
		Thread.sleep(1500);
		Alert alt = driver.switchTo().alert();
		alt.accept();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(3000);

		//Cancel the placed bet
		//		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-show='globalrouletteCtlr.CancelbtnShow']"));
		//		String cancel = Cancel.getText();
		//		System.out.println("Cancel Slip text button is verified: "+ cancel);
		//		Assert.assertEquals("Cancel Slip", cancel);
		//		Thread.sleep(2000);
		//		Cancel.click();
		//		Thread.sleep(3000);
		//
		//		//Cancel alert popup generated with the slip details and cancel the bet
		//		WebElement SlipCancel = driver.findElement(By.xpath("//span[@class='vR_Slip_Cancel']"));
		//		wait1.until(ExpectedConditions.visibilityOf(SlipCancel));
		//
		//		SlipCancel.click();
		//		Thread.sleep(2000);
		//		String Cmsg = Validmsg.getText();
		//		System.out.println("Cancelled Slip user message: "+ Cmsg);
		//		Assert.assertEquals("Successfully Cancelled", Cmsg);
		//		Thread.sleep(2000);
		//
		//		//driver.findElement(By.id("/html/body/div[3]/div[1]/ui-view/section/section[2]/div[3]/div/h3/span[1]")).click(); 
		//		Thread.sleep(2000);

		//Fetch the main balance after cancelling bet
		WebElement Mbal1 = driver.findElement(By.id("sn_after_login_info_Amount"));
		System.out.println("Main balance after cancelling the placed bet: "+ Mbal1.getText());
		Assert.assertEquals(Mbal1.getText(), mbal);
		System.out.println("Before and after cancelling the bet and the main balance is same and verified");
	}
	
	@Then("^Web:  Balance has to decuted respective with bet placed, and print slip has to be geneated$")
	public void web_Balance_has_to_decuted_respective_with_bet_placed_and_print_slip_has_to_be_geneated() throws Throwable {
	    
	}
	
	@Then("^Web: Cancel the bet placed and check with the same amount being updated to the main balance$")
	public void web_Cancel_the_bet_placed_and_check_with_the_same_amount_being_updated_to_the_main_balance() throws Throwable {
	    
	}
	
	@Then("^Web: Validation successful message for placing bet should be generated$")
	public void web_Validation_successful_message_for_placing_bet_should_be_generated() throws Throwable {
	    
	}
	
	@Then("^Web: After cancelling the placed bet validation message has to be generated and stake balance has be added to main account balance$")
	public void web_After_cancelling_the_placed_bet_validation_message_has_to_be_generated_and_stake_balance_has_be_added_to_main_account_balance() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}