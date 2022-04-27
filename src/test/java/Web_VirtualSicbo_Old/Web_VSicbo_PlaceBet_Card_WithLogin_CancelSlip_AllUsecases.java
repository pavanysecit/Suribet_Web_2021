package Web_VirtualSicbo_Old;

import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VSicbo_PlaceBet_Card_WithLogin_CancelSlip_AllUsecases extends Web_VSicbo_URL_Login_Card {
	private static WebDriver driver;
	
	public  Web_VSicbo_PlaceBet_Card_WithLogin_CancelSlip_AllUsecases() throws InterruptedException, MalformedURLException {
				driver = Web_VSicbo_URL_Login_Card();
		}
	
	@Given("^Mobile: Chrome browser, suribet website valid URL, Card login details, VS module, current draw, bet numbers, Forward, Confirn, ClearAll, Cancel Slip button, Balance and confirmation message on cancel$")
	public void mobile_Chrome_browser_suribet_website_valid_URL_Card_login_details_VS_module_current_draw_bet_numbers_Forward_Confirn_ClearAll_Cancel_Slip_button_Balance_and_confirmation_message_on_cancel() throws Throwable {

	}

	@When("^Mobile: Login to suribet website with valid login details by card, Click on Virtual Sicbo module link, select the bet numbers, place the bet, cancel the bet within (\\d+) min, again place a bet and check the visibility of cancel bet$")
	public void mobile_Login_to_suribet_website_with_valid_login_details_by_card_Click_on_Virtual_Sicbo_module_link_select_the_bet_numbers_place_the_bet_cancel_the_bet_within_min_again_place_a_bet_and_check_the_visibility_of_cancel_bet(int arg1) throws Throwable {
		//Storing the balance before placing the bet
		String Mbalance = driver.findElement(By.id("spnUserBalance")).getText();
		System.out.println("Balance of the card before placing the bet: " +Mbalance);
		
		//Selecting the future draw
		String d = "//ul[@class='ul_clear']//li[6]";
		String dd =d+"/span";
		WebElement DrawNO = driver.findElement(By.xpath(d));
		DrawNO.click();
		Thread.sleep(2000);

		System.out.println(DrawNO.getText());
		
		//Selecting the bet number and placing the bet
		WebElement smallb = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		smallb.click();
		Thread.sleep(1000);
		WebElement bigb = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		bigb.click();
		Thread.sleep(1000);
		
		//Cross checking default bet amount and placing the bet
		String tstake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Total Stake dispalyed on the betting slip: " +tstake);
		String expected = "2";
		Assert.assertEquals(tstake, expected);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")));		
		String cmsg = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
		System.out.println("Message displayed after placing bet successfully: " +cmsg);
		String expected1 = "Bet has been placed successfully";
		Assert.assertEquals(cmsg, expected1);
		Thread.sleep(2000);
		
		//Cancelling the slip within 1 min
		driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]")).click();
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mgs.ng-binding")));		
		String Validmsg = driver.findElement(By.cssSelector(".mgs.ng-binding")).getText();
		String expectedMsg = "Successfully Cancelled";
		Assert.assertEquals(Validmsg, expectedMsg);
		Thread.sleep(5000);
		
		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);

		
		//Storing the current balance and comparing with previous balance and it should be same
		String Cbalance = driver.findElement(By.id("spnUserBalance")).getText();
		System.out.println("Current balance of the Card after cancelling the slip is: " +Cbalance);
		Assert.assertEquals(Mbalance, Cbalance);
		System.out.println("Test case passed as Current balance after cancelling slip and previous balance before placing the bet are matching");
		
		//Cancel slip button should not be visible once it is already cancelled
		driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']")).click();
		Thread.sleep(3000);
		boolean canc = driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]")).isDisplayed();
		Assert.assertFalse(canc);
		Thread.sleep(2000);
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(2000);
		
		//Cancel slip button should not be visible after 1 min of placing bet successfully 
		WebElement NewDrawNo = driver.findElement(By.xpath(dd));
		NewDrawNo.click();
		Thread.sleep(4000);
		WebElement smallb1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		smallb1.click();
		Thread.sleep(1000);
		WebElement bigb1 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		bigb1.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
		Thread.sleep(3000);
		WebElement button1 = driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']"));
		Thread.sleep(62000);
		Assert.assertFalse(button1.isDisplayed());
		System.out.println("Cancel slip button is not visible after crossing 1 min of placing bet");
		
		
//		//Checking validation message on clicking of cancel button after exceeding 1 min time 
//		NewDrawNo.click();
//		Thread.sleep(4000);
//		WebElement smallb2 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[1]"));
//		smallb2.click();
//		Thread.sleep(1000);
//		WebElement bigb2 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[2]"));
//		bigb2.click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
//		Thread.sleep(3000);
//		
//		driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']")).click();
//		Thread.sleep(69000);
//		driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]")).click();
//		Thread.sleep(1000);
//		String Validmsg1 = driver.findElement(By.cssSelector(".mgs.ng-binding")).getText();
//		String expectedMsg1 = "Time limit Passed! Slip(s) cannot be cancelled!";
//		Assert.assertEquals(Validmsg1, expectedMsg1);
//		Thread.sleep(5000);
		
		WebElement cls1 = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", cls1);
		
		
		//Selecting the bet number and placing the bet and cancel the bet from account details page
		NewDrawNo.click();
		Thread.sleep(7000);
		WebElement smallb3 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[1]"));
		executor1.executeScript("arguments[0].click();", smallb3);
		Thread.sleep(1000);
		WebElement bigb3 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[2]"));
		bigb3.click();
		Thread.sleep(1000);
	
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@class='ac_id']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//i[@class='fa fa-minus-circle']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='submit_but EmptyValidationBut_JS click_effect_JS ng-scope']")).click();
		Thread.sleep(5000);
		
		
		
		Alert alert = driver.switchTo().alert();
		driver.switchTo().alert().accept();
		Thread.sleep(4000);
		String Validmsg2 = driver.findElement(By.xpath("//div[@id='modal-success']/form[1]/div[1]/b")).getText();
		String expectedMsg2 = "Successfully Cancelled";
		Assert.assertEquals(Validmsg2, expectedMsg2);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='PrintSlipDet MsgPop_OK']")).click();
		Thread.sleep(3000);
		
		//Placing the bet, navigating to account page after 62sec and checking CancelSlip popup is not displayed
		NewDrawNo.click();
		Thread.sleep(4000);
		WebElement smallb4 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		smallb4.click();
		Thread.sleep(1000);
		WebElement bigb4 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		bigb4.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
		

		//Verifying the recently placed bet is in open state in account summary tab section
		WebDriverWait wait1 = new WebDriverWait(driver, 120);

		WebElement acc = driver.findElement(By.className("ac_id"));
		acc.click();
		Thread.sleep(2000);
		WebElement BetDetails = driver.findElement(By.cssSelector(".in_middle_head.searchButSB")); 
		wait1.until(ExpectedConditions.visibilityOf(BetDetails));

		//Fetch the row 1st draw details is open state
		WebElement row1open = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[4]"));
		System.out.println("1st row current bet status information: "+row1open.getText());
		Assert.assertEquals("Open", row1open.getText());
		Thread.sleep(2000);
		WebElement row1cancel = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[8]"));
		Assert.assertEquals(row1cancel.getAttribute("class"), "ng-scope sb_cart_disable");
		
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='sicbobetslip ng-scope']")).isDisplayed());
		Thread.sleep(3000);
	}

	@Then("^Mobile:  Bet should be placed successfully from Card account and Canncel slip button should be displayed in VS\\.$")
	public void mobile_Bet_should_be_placed_successfully_from_Card_account_and_Canncel_slip_button_should_be_displayed_in_VS() throws Throwable {
	    
	}

	@Then("^Mobile: Clicking on Cancel before (\\d+) min using card login, bet should get cancelled and confirmation message should be displayed in VS$")
	public void mobile_Clicking_on_Cancel_before_min_using_card_login_bet_should_get_cancelled_and_confirmation_message_should_be_displayed_in_VS(int arg1) throws Throwable {
	    
	}

	@Then("^Mobile: Balance should get refunded to Card account if the bet is cancelled within (\\d+) min in VS$")
	public void mobile_Balance_should_get_refunded_to_Card_account_if_the_bet_is_cancelled_within_min_in_VS(int arg1) throws Throwable {
	   
	}

	@Then("^Mobile: Cancel slip button should get disappeared from the betting slip after (\\d+)min and user should not be able to cancel the bet in VS using card details$")
	public void mobile_Cancel_slip_button_should_get_disappeared_from_the_betting_slip_after_min_and_user_should_not_be_able_to_cancel_the_bet_in_VS_using_card_details(int arg1) throws Throwable {
	    
	}

	@Then("^Mobile: Validation message should get displayed when user clicked on Cancel Slip button and then try to click on Cancel Slip button after (\\d+) min in PopUp window and amount should not get refunded to card account in VS$")
	public void mobile_Validation_message_should_get_displayed_when_user_clicked_on_Cancel_Slip_button_and_then_try_to_click_on_Cancel_Slip_button_after_min_in_PopUp_window_and_amount_should_not_get_refunded_to_card_account_in_VS(int arg1) throws Throwable {
	     
	}

	@Then("^Mobile:  Cancel slip button should be displayed in Account details of the Card and Slip should get cancelled successfully within (\\d+) minute in VS$")
	public void mobile_Cancel_slip_button_should_be_displayed_in_Account_details_of_the_Card_and_Slip_should_get_cancelled_successfully_within_minute_in_VS(int arg1) throws Throwable {
	    
	}

	@Then("^Mobile: Cancel slip button should get disappeared in Account details of the Card account after (\\d+) min in VS$")
	public void mobile_Cancel_slip_button_should_get_disappeared_in_Account_details_of_the_Card_account_after_min_in_VS(int arg1) throws Throwable {
	    Thread.sleep(3000);
	    driver.quit();
	}
}
