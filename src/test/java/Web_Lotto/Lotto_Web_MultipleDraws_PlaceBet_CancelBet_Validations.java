package Web_Lotto;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Lotto_Web_MultipleDraws_PlaceBet_CancelBet_Validations extends Lotto_Web_URL_OnlineLogin{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, lotto module,login via online method, upcoming matches table place for multiple draws, cancel slip$")
	public void web_Chrome_browser_suribet_website_valid_URL_lotto_module_login_via_online_method_upcoming_matches_table_place_for_multiple_draws_cancel_slip() throws Throwable {
		driver = Lotto_URL_OnlineLogin();
		
	    WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
	}

	@When("^Web: Login to lotto and place bet for multiple draws and try cancelling each slips and multiple cancelling of slips$")
	public void web_Login_to_lotto_and_place_bet_for_multiple_draws_and_try_cancelling_each_slips_and_multiple_cancelling_of_slips() throws Throwable {
		//clicking on Draw details to place bet for the current draw and cancwl
		WebElement upmatch = driver.findElement(By.xpath("//*[@class='ul_clear in_R_up_ul']/child::li[1]"));
		WebElement upmatch1 = driver.findElement(By.xpath("//*[@class='ul_clear in_R_up_ul']/child::li[2]"));
		
		upmatch.click();
		Thread.sleep(2000);
		
		//Placing a bet and clicking on Replay button
		WebElement quick_pick = driver.findElement(By.xpath("(//i[@class='fa fa-hand-pointer-o'])[1]"));
		
		quick_pick.click();
		Thread.sleep(2000);
		
		upmatch1.click();
		Thread.sleep(2000);
		quick_pick.click();
		Thread.sleep(2000);
		System.out.println("Bets selected on the betting slip for different draws");

	
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.lottoCartSubmit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.lottoCartSubmit()']"));
		

		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(3000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.xpath("(//div[@class='ng-binding'])[3]")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		
		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()=' Cancel Slip ']"));
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement SlipCancel = driver.findElement(By.xpath("//div[@ng-if='cancelbutton']"));
		wait1.until(ExpectedConditions.visibilityOf(SlipCancel));

		//unselect the slips id and click on cancel button
		WebElement slipid1 = driver.findElement(By.xpath("(//span[@class='checkmark'])[1]"));
		WebElement slipid2 = driver.findElement(By.xpath("(//span[@class='checkmark'])[2]"));
		
		slipid1.click();
		Thread.sleep(1000);
		slipid2.click();
		Thread.sleep(1000);
		SlipCancel.click();
		Thread.sleep(3000);

		String cmsg = driver.findElement(By.xpath("(//div[@ng-show='cancelbutton'])[1]")).getText();
		String expected = "Please select the slipId to cancel";
		Assert.assertEquals(cmsg, expected);
		
		slipid1.click();
		Thread.sleep(1000);
		SlipCancel.click();
		Thread.sleep(3000);
		
		//Clicking on Close icon on the pop-up
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		WebElement SlipCancelMessage = driver.findElement(By.xpath("//form[@class='EmptyValidation_W_JS ng-pristine ng-valid']/div/b"));
		System.out.println("Cancel Slipmessage  text is verified: "+ SlipCancelMessage.getText());
		Assert.assertEquals("Slip Successfully Cancelled.", SlipCancelMessage.getText());
		Thread.sleep(2000);

		WebElement custompopup = driver.findElement(By.cssSelector(".PrintSlipDet.MsgPop_OK"));
		custompopup.click();
		Thread.sleep(3000);
		
		//navigated upon click on account number
		driver.findElement(By.xpath("//*[@class='ac_id']")).click();
		Thread.sleep(5000);
		WebElement summarypage = driver.findElement(By.xpath("//*[@class='in_middle_head searchButSB']"));
		Assert.assertTrue(summarypage.isDisplayed());
		System.out.println("Navigated to account summary page");
		
		String status = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']/li[4]")).getText();
		String expected1 = "Cancelled";
		Assert.assertEquals(status, expected1);
		System.out.println("Status of the bet placed should be cancelled: "+status);
	}

	@Then("^Web: Validate the multiple draws is available to user and user is able to cancel the individual placed bet from multiple cancel slip sheet$")
	public void web_Validate_the_multiple_draws_is_available_to_user_and_user_is_able_to_cancel_the_individual_placed_bet_from_multiple_cancel_slip_sheet() throws Throwable {
	   
	}

	@Then("^Web: Validate the multiple draws can be cancelling in a single click on cancel slip button$")
	public void web_Validate_the_multiple_draws_can_be_cancelling_in_a_single_click_on_cancel_slip_button() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}