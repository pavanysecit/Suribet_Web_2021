package Web_VirtualSkinfiri;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualSkinfiri_WithoutLoginPlaceBet_Validations extends Web_VirtualSkinfiri_URL{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, skinfiri module, place bet, rebet, and quickpick validations messages$")
	public void web_Chrome_browser_suribet_website_valid_URL_skinfiri_module_place_bet_rebet_and_quickpick_validations_messages() throws Throwable {
		driver = Web_VirtualSkinfiri_URL();
		Thread.sleep(2000);
	}

	@When("^Web: direct to the module and place bet in loogout condition with and without bets and try to rebet under logout conditions$")
	public void web_direct_to_the_module_and_place_bet_in_loogout_condition_with_and_without_bets_and_try_to_rebet_under_logout_conditions() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement UN = driver.findElement(By.xpath("//*[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));
		Thread.sleep(2000);

		/*
		 * 1. Forward and place bet without selecting single bet
		 * 2. select future bets and try to place bet
		 * 3. select the rebet option 
		 *    Validation message to be validated 
		 */

		//Place the bet without any draw selection
		WebElement Forward = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.PlaceBet(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);

		WebElement Validmsg = driver.findElement(By.xpath("//*[@id='DivAlerts_Cart']/div")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String Vmsg = Validmsg.getText();
		System.out.println("Bets not selected user message: "+ Vmsg);
		Assert.assertEquals("You must log in before to place a bet", Vmsg);


		//Select the future draws and place bet and validate the user message
		WebElement DrawNO = driver.findElement(By.xpath("//*[@ng-repeat='a in VDGCtr.vsGetDraws'][5]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the numbers from the players and dealers table 
		WebElement Dcomb1 = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][8]"));
		WebElement Dcomb2 = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][9]"));

		Dcomb1.click();
		Thread.sleep(1000);
		Dcomb2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		Forward.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String V1msg = Validmsg.getText();
		System.out.println("Login prompt user message: "+ V1msg);
		Assert.assertEquals("You must log in before to place a bet", V1msg);

		WebElement clr = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.fn_btnClear()']"));
		clr.click();
		Thread.sleep(2000);

		//Rebet button validations
		WebElement rebet = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.Replay()']")); 
		rebet.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String V2msg = Validmsg.getText();
		System.out.println("Login prompt user message for rebet button selecting: "+ V2msg);
		Assert.assertEquals("Your session is expired please login Again", V2msg);

		//quickpick5 button validations
		WebElement q5 = driver.findElement(By.xpath("//*[@ng-repeat='QData in VDGCtr.QuckpickDetails']")); 

		q5.click();
		Thread.sleep(2000);
		Forward.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String V3msg = Validmsg.getText();
		System.out.println("Login prompt user message for rebet button selecting: "+ V3msg);
		Assert.assertEquals("You must log in before to place a bet", V3msg);


		clr.click();
		Thread.sleep(2000);

		//quickpick10 button validations
		WebElement q10 = driver.findElement(By.xpath("//*[@ng-repeat='QData in VDGCtr.QuckpickDetails'][2]")); 

		q10.click();
		Thread.sleep(2000);
		Forward.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String V4msg = Validmsg.getText();
		System.out.println("Login prompt user message for rebet button selecting: "+ V4msg);
		Assert.assertEquals("You must log in before to place a bet", V4msg);


		clr.click();
		Thread.sleep(2000);

		//Next 10 draws validations
		WebElement num = driver.findElement(By.xpath("//*[@ng-repeat='Bet in VDGCtr.vsBetNumber'][5]"));
		WebElement nxt = driver.findElement(By.xpath("//*[@ng-repeat='QData in VDGCtr.QuckpickDetails'][2]")); 

		num.click();
		Thread.sleep(2000);
		nxt.click();
		Thread.sleep(2000);
		Forward.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String V5msg = Validmsg.getText();
		System.out.println("Login prompt user message for rebet button selecting: "+ V5msg);
		Assert.assertEquals("You must log in before to place a bet", V5msg);

		clr.click();
		Thread.sleep(2000);
	}

	@Then("^Web: Validate the appropriate user message displayed bet placing under logout conditions$")
	public void web_Validate_the_appropriate_user_message_displayed_bet_placing_under_logout_conditions() throws Throwable {
	    driver.quit();
	}
}