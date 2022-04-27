package Web_VirtualSicbo;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_OnlineLogin_HideAndUnhide_BettingSlip_AllDraws_AccountOpenAndCloseBalance_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSicbo_OnlineLogin_HideAndUnhide_BettingSlip_AllDraws_AccountOpenAndCloseBalance_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	} 
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module,login via online method, betslip arrow, all draws arrows, login balance and logout balance$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_login_via_online_method_betslip_arrow_all_draws_arrows_login_balance_and_logout_balance() throws Throwable {
	    
	}

	@When("^Web: note the login balance, and click on the hide and un hide buttons located on all draws and betting slip columns and place bets and check the balance deductions and logout and relogin and verify the balance reflected to the user$")
	public void web_note_the_login_balance_and_click_on_the_hide_and_un_hide_buttons_located_on_all_draws_and_betting_slip_columns_and_place_bets_and_check_the_balance_deductions_and_logout_and_relogin_and_verify_the_balance_reflected_to_the_user() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		//Fetching only the amount for calculations
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String Bbal = bal.getText();
		String beforebal = Bbal.replaceAll(",", "");
		double d=Double.parseDouble(beforebal); 

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
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[7]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the Numbers from the Sicbo Board to place the bet 
		WebElement betType1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		WebElement betType2 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		
		betType1.click();
		Thread.sleep(1000);
		betType2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Sicbo table");


		//FORWARD AND PLACE BET WITH LOGIN TO THE VIRTUAL SICBO
		WebElement Forward = driver.findElement(By.xpath("//*[ text() = 'FORWARD']"));
		Forward.click();
		Thread.sleep(2000);
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.confirm()']"));
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been placed successfully", Vmsg);

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
	
	@Then("^Web: Validate the hide and un-hide functionality under alldraws and betting slip sections and the balance validations after relogin to the suribet account$")
	public void web_Validate_the_hide_and_un_hide_functionality_under_alldraws_and_betting_slip_sections_and_the_balance_validations_after_relogin_to_the_suribet_account() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}