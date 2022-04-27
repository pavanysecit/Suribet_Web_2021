package Web_Poker;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Poker_OnlineLogin_HideAndUnhide_BettingSlip_AllDraws_AccountOpenAndCloseBalance_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_OnlineLogin_HideAndUnhide_BettingSlip_AllDraws_AccountOpenAndCloseBalance_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module,login via online method, betslip arrow, all draws arrows, login balance and logout balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_login_via_online_method_betslip_arrow_all_draws_arrows_login_balance_and_logout_balance() throws Throwable {
	    
	}

	@When("^Web: note the login balance, and click on the hide and un hide buttons located on all draws and betting slip columns and place bets and check the balance deductions and logout and relogin and verify the balance reflected to the user$")
	public void web_note_the_login_balance_and_click_on_the_hide_and_un_hide_buttons_located_on_all_draws_and_betting_slip_columns_and_place_bets_and_check_the_balance_deductions_and_logout_and_relogin_and_verify_the_balance_reflected_to_the_user() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
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

		//Verify the class property when the game screen is loaded 
		WebElement classproperty = driver.findElement(By.xpath("//*[@class='middle_W middle_W_JS ng-scope']"));
		Assert.assertTrue(classproperty.isDisplayed());
		Thread.sleep(2000);

		//Left All draws sliding button
		WebElement leftsliding = driver.findElement(By.xpath("//label[@class='left_slideBUt fa fa-arrow-left left_slideBUt_JS']"));
		leftsliding.click();
		Thread.sleep(2000);
		WebElement classproperty_left = driver.findElement(By.xpath("//*[@class='middle_W middle_W_JS ng-scope left_slideBUtAdd']"));
		Assert.assertTrue(classproperty_left.isDisplayed());
		Thread.sleep(2000);

		//Right sliding betting slips
		WebElement rightsliding = driver.findElement(By.xpath("//*[@class='left_slideBUt fa fa-arrow-right float_rightimportant right_slideBUt_JS']"));
		rightsliding.click();
		Thread.sleep(2000);
		WebElement classproperty_right = driver.findElement(By.xpath("//*[@class='middle_W middle_W_JS ng-scope right_slideBUtAdd']"));
		Assert.assertTrue(classproperty_right.isDisplayed());
		Thread.sleep(2000);

		//Re-sliding back
		rightsliding.click();
		Thread.sleep(2000);

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
		String Ddetails = DrawNO.getText();
		System.out.println("Draw bid number with time details selected  "+Ddetails);
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));	
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
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

		//Cancel the placed bet
		WebElement logout  = driver.findElement(By.xpath("(//*[@ng-click='frm.Logout()'])[2]"));
		logout.click();
		Thread.sleep(3000);

		//login with valid credentials
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));
		UN.click();
		Thread.sleep(1000);
		UN.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("mansoor@123");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(8000);

		//Fetch the balance after re-login with the same account
		WebElement AFbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(AFbal));
		String AFbal1 = AFbal.getText();
		String afterbal1 = AFbal1.replaceAll("[^0-9.]", "");
		double d1=Double.parseDouble(afterbal1); 
		Assert.assertEquals(d1, d11, 0.000);
		System.out.println("Validation of the balance before and after re-login is successfully validated");
		System.out.println("after re-login account balance: "+ d1);
	}

	@Then("^Web: Validate the hide and un-hide functionality under alldraws and betting slip sections and the balance validations after relogin to the suribet account$")
	public void web_Validate_the_hide_and_un_hide_functionality_under_alldraws_and_betting_slip_sections_and_the_balance_validations_after_relogin_to_the_suribet_account() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}