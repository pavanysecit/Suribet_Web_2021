package Web_Skinfiri;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Skinfiri_OnlineLogin_HideAndUnhide_BettingSlip_GameSection_AccountOpenAndCloseBalance_Validations extends Web_Skinfiri_URL_OnlineLogin {
WebDriver driver;
	
	public Web_Skinfiri_OnlineLogin_HideAndUnhide_BettingSlip_GameSection_AccountOpenAndCloseBalance_Validations() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, skinfiri module,login via online method, betslip arrow, game section arrows, login balance and logout balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_skinfiri_module_login_via_online_method_betslip_arrow_game_section_arrows_login_balance_and_logout_balance() throws Throwable {
	    
	}

	@When("^Web: note the login balance, and click on the hide and un hide buttons located on all game section and betting slip columns and place bets and check the balance deductions and logout and relogin and verify the balance reflected to the user$")
	public void web_note_the_login_balance_and_click_on_the_hide_and_un_hide_buttons_located_on_all_game_section_and_betting_slip_columns_and_place_bets_and_check_the_balance_deductions_and_logout_and_relogin_and_verify_the_balance_reflected_to_the_user() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

		//Verify the class property when the game screen is loaded 
		//		WebElement classproperty = driver.findElement(By.xpath("//*[@class='middle_W middle_W_JS ng-scope']"));
		//		Assert.assertTrue(classproperty.isDisplayed());
		//		Thread.sleep(2000);

		//Click on first draw sliding button
		WebElement leftsliding = driver.findElement(By.xpath("(//i[@class='fa fa-minus-square Draw_minus_icon Draw_minus_icon_JS'])[1]"));
		leftsliding.click();
		Thread.sleep(2000);
		//After clicking on - icon, number 9 or any no should not visible
		WebElement no_9 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[1]"));
		Assert.assertFalse(no_9.isDisplayed());
		Thread.sleep(2000);

		//Click on second draw sliding button
		WebElement leftsliding1 = driver.findElement(By.xpath("(//i[@class='fa fa-minus-square Draw_minus_icon Draw_minus_icon_JS'])[1]"));
		leftsliding1.click();
		Thread.sleep(2000);
		WebElement no_9_1 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[2]"));
		Assert.assertFalse(no_9_1.isDisplayed());
		Thread.sleep(2000);

		//Click on third draw sliding button
		WebElement leftsliding2 = driver.findElement(By.xpath("(//i[@class='fa fa-minus-square Draw_minus_icon Draw_minus_icon_JS'])[1]"));
		leftsliding2.click();
		Thread.sleep(2000);
		WebElement no_9_2 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[3]"));
		Assert.assertFalse(no_9_2.isDisplayed());
		Thread.sleep(2000);
		
		//Click on fourth draw sliding button
		WebElement leftsliding3 = driver.findElement(By.xpath("(//i[@class='fa fa-minus-square Draw_minus_icon Draw_minus_icon_JS'])[1]"));
		leftsliding3.click();
		Thread.sleep(2000);
		WebElement no_9_3 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[4]"));
		Assert.assertFalse(no_9_3.isDisplayed());
		Thread.sleep(2000);
		
		//Expanding the draw no's by clicking on + icon
		WebElement expand = driver.findElement(By.xpath("(//i[@class='fa Draw_minus_icon Draw_minus_icon_JS fa-plus-square'])[1]"));
		expand.click();
		Thread.sleep(2000);
		//After clicking on + icon, number 9 or any no should be visible
		WebElement expandno_1 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[1]"));
		Assert.assertTrue(expandno_1.isDisplayed());
		Thread.sleep(2000);
		
		WebElement expand2 = driver.findElement(By.xpath("(//i[@class='fa Draw_minus_icon Draw_minus_icon_JS fa-plus-square'])[1]"));
		expand2.click();
		Thread.sleep(2000);
		//After clicking on + icon, number 9 or any no should be visible
		WebElement expandno_2 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[2]"));
		Assert.assertTrue(expandno_2.isDisplayed());
		Thread.sleep(2000);
		
		WebElement expand3 = driver.findElement(By.xpath("(//i[@class='fa Draw_minus_icon Draw_minus_icon_JS fa-plus-square'])[1]"));
		expand3.click();
		Thread.sleep(2000);
		//After clicking on + icon, number 9 or any no should be visible
		WebElement expandno_3 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[3]"));
		Assert.assertTrue(expandno_3.isDisplayed());
		Thread.sleep(2000);

		
		//clicking on Draw details to place bet for the current draw 
		no_9.click();
		Thread.sleep(2000);

		WebElement stake = driver.findElement(By.xpath("//input[@ng-change='DailyGameCtrl.changeAllStack()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("1");
		Thread.sleep(2000);

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");


		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivDgCartAlert")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);

		//Fetch the balance before logout 
		String Abal = bal.getText();
		String afterbal = Abal.replaceAll(",", "");
		double d1=Double.parseDouble(afterbal); 
		System.out.println("before logout account balance: "+ d1);

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
		Thread.sleep(2000);
		//Fetch the balance after re-login with the same account
		WebElement AFbal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String AFbal1 = AFbal.getText();
		String afterbal1 = AFbal1.replaceAll(",", "");
		double d11=Double.parseDouble(afterbal1); 
		Assert.assertEquals(d1, d11, 0.000);
		System.out.println("Validation of the balance before and after re-login is successfully validated");
		System.out.println("after re-login account balance: "+ d11);
	}

	@Then("^Web: Validate the hide and un-hide functionality under game section and betting slip sections and the balance validations after relogin to the suribet account$")
	public void web_Validate_the_hide_and_un_hide_functionality_under_game_section_and_betting_slip_sections_and_the_balance_validations_after_relogin_to_the_suribet_account() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}