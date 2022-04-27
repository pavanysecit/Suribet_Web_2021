package Web_SportBetting;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_SportBetting_SessionExpiry_Gamelist_Resultlist_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_SessionExpiry_Gamelist_Resultlist_Validations() throws Exception {
		driver =Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, sports betting module,login via online method, SessionExpiry timeduration, gamelist and result list$")
	public void web_Chrome_browser_suribet_website_valid_URL_sports_betting_module_login_via_online_method_SessionExpiry_timeduration_gamelist_and_result_list() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Login to sport betting, gamelist and result list button validations, and place bet and wait for the session expiry and try placing bet$")
	public void web_Login_to_sport_betting_gamelist_and_result_list_button_validations_and_place_bet_and_wait_for_the_session_expiry_and_try_placing_bet() throws Throwable {


		//Validations the buttons are active and displayed to user 
		WebElement gamelist = driver.findElement(By.xpath("//*[@ng-class='{sb_cart_disable:disablePosGamelist}']"));
		Assert.assertEquals("POS Game List", gamelist.getText());
		Assert.assertTrue(gamelist.isDisplayed());
		System.out.println("Game list download button is validated and diaplayed to user");
		WebElement resultlist = driver.findElement(By.xpath("//*[@ng-class='{sb_cart_disable:disableresGamelist}']"));
		Assert.assertEquals("Result List", resultlist.getText());
		Assert.assertTrue(resultlist.isDisplayed());
		System.out.println("Result list download button is validated and diaplayed to user");


		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		String MainWindow = driver.getWindowHandle();
		System.out.println("Main window handle is " + MainWindow);

		Robot rob =new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_T);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_T);
		Thread.sleep(1*30*1000);

		// To handle all new opened window
		Set<String> s1 = driver.getWindowHandles();
		System.out.println("Child window handle is" + s1);
		driver.switchTo().window(MainWindow);
		
		//Wait for thesession to be expired
		Thread.sleep(7*60*1000); 
		driver.getTitle();
		Thread.sleep(8*60*1000); 
		driver.getTitle();
		Thread.sleep(6*60*1000);
		driver.getTitle();

		//Validation for tab 
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(3000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		driver.findElement(By.xpath("//*[@ng-click='frm.sessionLoginCancel()']")).click();
		Thread.sleep(2000);

		//Validate msg for session expiry user message while bet placing
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Your session is expired please login Again", Vmsg);

	}

	@Then("^Web: Validate the valid user message displayed to user and the button validations$")
	public void web_Validate_the_valid_user_message_displayed_to_user_and_the_button_validations() throws Throwable {
		Thread.sleep(3000);
		driver.quit();
	}
}