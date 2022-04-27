package Web_VirtualSicbo_Old;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VSicbo_PlaceBet_Card_WithoutLogin_CancelSlip_AllUsecases {
private static WebDriver driver;
	
	
	@Given("^Mobile: Chrome browser, suribet website valid URL, Card details without login, VS module, current draw, bet numbers, Forward, Confirn, ClearAll, Cancel Slip button, Balance and confirmation message on cancel$")
	public void mobile_Chrome_browser_suribet_website_valid_URL_Card_details_without_login_VS_module_current_draw_bet_numbers_Forward_Confirn_ClearAll_Cancel_Slip_button_Balance_and_confirmation_message_on_cancel() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/VirtualSicBo/SicboHome");
		driver.manage().window().maximize();
		
		//clicking on login button and entering Invalid credentials
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
	}

	@When("^Mobile: Load suribet website with valid login details by card without login, Click on Virtual Sicbo module link, select the bet numbers, place the bet, cancel the bet within (\\d+) min, again place a bet and check the visibility of cancel bet$")
	public void mobile_Load_suribet_website_with_valid_login_details_by_card_without_login_Click_on_Virtual_Sicbo_module_link_select_the_bet_numbers_place_the_bet_cancel_the_bet_within_min_again_place_a_bet_and_check_the_visibility_of_cancel_bet(int arg1) throws Throwable {
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
		Thread.sleep(2000);
		
		//Entering Invalid Card details and checking the validation message
		WebElement PbyCard = driver.findElement(By.xpath("(//*[ text() = 'Pay By Card'])[1]"));
		PbyCard.click();
		Thread.sleep(3000);
		WebElement cnumber = driver.findElement(By.name("CustmerCardNumber"));
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(2000);
		WebElement cpin = driver.findElement(By.name("CustmerCardCardPin"));
		cpin.sendKeys("1111");
		Thread.sleep(2000);
		WebElement pbet = driver.findElement(By.xpath("//*[ text() = 'Proceed  to Bet']"));
		pbet.click();
		Thread.sleep(3000);
		
		//Checking validation message for invalid pin
		String actual = driver.findElement(By.xpath("(//b[@class='err_msg1 ng-binding'])[1]")).getText();
		String expected = "Incorrect Pin";
		Assert.assertEquals(actual, expected);
			
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489987776");
		Thread.sleep(1000);
		
		cpin.clear();
		Thread.sleep(1000);
		cpin.sendKeys("1125");
		Thread.sleep(1000);
		pbet.click();
		Thread.sleep(3000);
		
		//Checking validation message for invalid card number
		String actual1 = driver.findElement(By.xpath("(//b[@class='err_msg1 ng-binding'])[1]")).getText();
		String expected1 = "Please Enter a Valid Card Number and Pin-6 !";
		Assert.assertEquals(actual1, expected1);
		
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		
		//Checking the validation message for card number length and card pin length
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("211164948776");
		Thread.sleep(1000);
		pbet.click();
		Thread.sleep(3000);
		String actual13 = driver.findElement(By.xpath("(//b[@class='err_msg1 ng-binding'])[1]")).getText();
		String expected13 = "Minimum length of a card number is 16";
		Assert.assertEquals(actual13, expected13);
		
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		
		cpin.clear();
		Thread.sleep(1000);
		cpin.sendKeys("11");
		Thread.sleep(1000);
		pbet.click();
		Thread.sleep(3000);
		
		String actual14 = driver.findElement(By.xpath("(//b[@class='err_msg1 ng-binding'])[1]")).getText();
		String expected14 = "Minimum length of a card pin is 4";
		Assert.assertEquals(actual14, expected14);
		
		//Placing the bet with valid card details
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		cpin.clear();
		Thread.sleep(1000);
		cpin.sendKeys("1125");
		Thread.sleep(1000);
		pbet.click();
		Thread.sleep(3000);
		WebElement confbet = driver.findElement(By.xpath("(//div[@class='w-50'])[1]"));
		confbet.click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")));		
		String cmsg = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
		System.out.println("Message displayed after placing bet successfully: " +cmsg);
		String expected2 = "Bet has been placed successfully";
		Assert.assertEquals(cmsg, expected2);
		Thread.sleep(2000);
		
		//Cancelling the slip within 2 min by entering card number and card pin
		driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]")).click();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		cpin.sendKeys("1122");
		Thread.sleep(1000);
		WebElement pbet1 = driver.findElement(By.xpath("//center[@class='w-100']"));
		pbet1.click();
		Thread.sleep(3000);
		
		//Checking validation message for invalid card pin while cancelling the slip
		String actual11 = driver.findElement(By.xpath("(//b[@class='err_msg1 ng-binding'])[1]")).getText();
		String expected11 = "Please Enter a Valid Card Number and Pin-7 !";
		Assert.assertEquals(actual11, expected11);
	
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489987776");
		Thread.sleep(1000);
		cpin.clear();
		Thread.sleep(1000);
		cpin.sendKeys("1125");
		Thread.sleep(1000);
		pbet1.click();
		Thread.sleep(3000);
		
		//Checking validation message for invalid card number while cancelling the slip
		String actual12 = driver.findElement(By.xpath("(//b[@class='err_msg1 ng-binding'])[1]")).getText();
		String expected12 = "Please Enter a Valid Card Number and Pin-6 !";
		Assert.assertEquals(actual12, expected12);

		
		cnumber.clear();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		
		cpin.clear();
		Thread.sleep(1000);
		cpin.sendKeys("1125");
		Thread.sleep(1000);
		pbet1.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")));		
		String cmsg1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
		System.out.println("Message displayed after cancelling the slip: " +cmsg1);
		String expected22 = "Successfully Cancelled";
		Assert.assertEquals(cmsg1, expected22);
		Thread.sleep(2000);
		
//		//Checking CancelSlip button should get disappear after 2minutes
//		WebElement NewDrawNo = driver.findElement(By.xpath(dd));
//		NewDrawNo.click();
//		Thread.sleep(4000);
//		WebElement smallb1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
//		smallb1.click();
//		Thread.sleep(1000);
//		WebElement bigb1 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
//		bigb1.click();
//		Thread.sleep(2000);
//		PbyCard.click();
//		Thread.sleep(2000);
//		cnumber.sendKeys("2111649489988826");
//		Thread.sleep(1000);
//		cpin.sendKeys("1125");
//		Thread.sleep(1000);
//		pbet1.click();
//		Thread.sleep(2000);
//		confbet.click();
//		Thread.sleep(2000);
//		
//		WebElement button1 = driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']"));
//		Thread.sleep(128000);
//		Assert.assertFalse(button1.isDisplayed());
//		System.out.println("Cancel slip button is not visible after crossing 2 min of placing bet");
		
		//Clicking on CancelSlip button before 2 min and then clicking on CancelSlip button on pop-up to verify validation msg 'Time limit crossed'
		WebElement NewDrawNo1 = driver.findElement(By.xpath(dd));
		NewDrawNo1.click();
		Thread.sleep(4000);
		smallb.click();
		Thread.sleep(2000);
		bigb.click();
		Thread.sleep(2000);
		PbyCard.click();
		Thread.sleep(2000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		cpin.sendKeys("1125");
		Thread.sleep(1000);
		pbet1.click();
		Thread.sleep(2000);
		confbet.click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_right']")).click();
		Thread.sleep(128000);
		driver.findElement(By.xpath("//div[@class='slip_Details_w slip_Details_w_sicbo ng-scope']/div[2]")).click();
		Thread.sleep(1000);
		cnumber.sendKeys("2111649489988826");
		Thread.sleep(1000);
		cpin.sendKeys("1125");
		Thread.sleep(1000);
		pbet1.click();
		Thread.sleep(2000);
		confbet.click();
		Thread.sleep(2000);
		String Validmsg1 = driver.findElement(By.cssSelector(".mgs.ng-binding")).getText();
		String expectedMsg1 = "Time limit Passed! Slip(s) cannot be cancelled!";
		Assert.assertEquals(Validmsg1, expectedMsg1);
		Thread.sleep(5000);
	}

	@Then("^Mobile:  Bet should be placed successfully from Card account without login and Canncel slip button should be displayed in VS\\.$")
	public void mobile_Bet_should_be_placed_successfully_from_Card_account_without_login_and_Canncel_slip_button_should_be_displayed_in_VS() throws Throwable {
	   
	}

	@Then("^Mobile:  Bet should be not placed successfully if the invalid card details provided during placing bet in VS\\.$")
	public void mobile_Bet_should_be_not_placed_successfully_if_the_invalid_card_details_provided_during_placing_bet_in_VS() throws Throwable {
	    
	}
	
	@Then("^Mobile: Bet should not be placed successfully if the card details should not met the length of card number and card pin in VS$")
	public void mobile_Bet_should_not_be_placed_successfully_if_the_card_details_should_not_met_the_length_of_card_number_and_card_pin_in_VS() throws Throwable {
	    
	}

	@Then("^Mobile: Clicking on Cancel before two min using card without login, bet should get cancelled and confirmation message should be displayed in VS$")
	public void mobile_Clicking_on_Cancel_before_two_min_using_card_without_login_bet_should_get_cancelled_and_confirmation_message_should_be_displayed_in_VS() throws Throwable {
	    
	}

	@Then("^Mobile: Bet should not get cancelled if the invalid card details provided while cancelling in VS$")
	public void mobile_Bet_should_not_get_cancelled_if_the_invalid_card_details_provided_while_cancelling_in_VS() throws Throwable {
	   
	}

	@Then("^Mobile: Balance should get refunded to Card account without login if the bet is cancelled within two min in VS$")
	public void mobile_Balance_should_get_refunded_to_Card_account_without_login_if_the_bet_is_cancelled_within_two_min_in_VS() throws Throwable {
	    
	}

	@Then("^Mobile: Cancel slip button should get disappeared from the betting slip after (\\d+) min and user should not be able to cancel the bet in VS using card details without login$")
	public void mobile_Cancel_slip_button_should_get_disappeared_from_the_betting_slip_after_min_and_user_should_not_be_able_to_cancel_the_bet_in_VS_using_card_details_without_login(int arg1) throws Throwable {
	    
	}

	@Then("^Mobile: Validation message should get displayed when user clicked on Cancel Slip button and then try to click on Cancel Slip button after (\\d+) min in PopUp window and amount should not get refunded to card account without login in VS$")
	public void mobile_Validation_message_should_get_displayed_when_user_clicked_on_Cancel_Slip_button_and_then_try_to_click_on_Cancel_Slip_button_after_min_in_PopUp_window_and_amount_should_not_get_refunded_to_card_account_without_login_in_VS(int arg1) throws Throwable {
	    Thread.sleep(3000);
	    driver.quit();
	}
}
