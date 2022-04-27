package Web_LiveSpin2Win;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_LiveSpin2Win_SessionExpiry_Validations extends Web_LiveSpin2Win_URL_OnlineLogin {
WebDriver driver;

	public Web_LiveSpin2Win_SessionExpiry_Validations() throws Exception {
		driver = Web_LiveSpin2Win_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, idle time duration$")
	public void web_Chrome_browser_suribet_website_valid_URL_idle_time_duration() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on live Spin(\\d+)Win module link, be idle for about (\\d+) minutes$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_live_Spin_Win_module_link_be_idle_for_about_minutes(int arg1, int arg2) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);

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

		//Select the Numbers from the player hands top place the bet 
		WebElement bet17 = driver.findElement(By.xpath("//*[@bettypepickid='18']"));

		bet17.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the roulette table");

		//Validate msg for session expiry user message while bet placing
		WebElement Validmsg = driver.findElement(By.xpath("//*[@ng-show='paiGow.IsEMessage']")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String Vmsg = Validmsg.getText();
		System.out.println("Session expiry user message: "+ Vmsg);
		Assert.assertEquals("Session Expired due to inactivity. Please login again!", Vmsg);
	}

	@Then("^Web: Place bet after the time duration, and validate the the session is expired and the user message is prompted and displayed to user to re login and establish new session$")
	public void web_Place_bet_after_the_time_duration_and_validate_the_the_session_is_expired_and_the_user_message_is_prompted_and_displayed_to_user_to_re_login_and_establish_new_session() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}