package Web_Poker;

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

public class Web_Poker_SessionExpiry_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;

	public Web_Poker_SessionExpiry_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, idel time duration$")
	public void web_Chrome_browser_suribet_website_valid_URL_idel_time_duration() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualPoker module link, be ideal for about (\\d+) minutes$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualPoker_module_link_be_ideal_for_about_minutes(int arg1) throws Throwable {
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

		String MainWindow = driver.getWindowHandle();
		System.out.println("Main window handle is " + MainWindow);

		Robot rob =new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_T);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_T);
		Thread.sleep(1*40*1000);

		// To handle all new opened window
		Set<String> s1 = driver.getWindowHandles();
		System.out.println("Child window handle is" + s1);
		driver.switchTo().window(MainWindow);

		//Wait for the session to be expired
		Thread.sleep(7*60*1000); 
		driver.getTitle();
		Thread.sleep(8*60*1000); 
		driver.getTitle();
		Thread.sleep(6*60*1000);
		driver.getTitle();

		//Validation for tab 
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)'] "));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(3000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		driver.findElement(By.xpath("//*[@ng-click='frm.sessionLoginCancel()']")).click();
		Thread.sleep(2000);

		//Validate message for session expiry user message while bet placing
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Your session is expired please login Again", Vmsg);
	}

	@Then("^Web:  Place bet after the time duration, and validate the the session is expired and the user message is prompted and displayed to user to re login and establish new session$")
	public void web_Place_bet_after_the_time_duration_and_validate_the_the_session_is_expired_and_the_user_message_is_prompted_and_displayed_to_user_to_re_login_and_establish_new_session() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}