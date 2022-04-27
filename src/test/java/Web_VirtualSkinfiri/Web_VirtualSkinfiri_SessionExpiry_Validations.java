package Web_VirtualSkinfiri;

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


/*
 * Sesssion Expiry not implemented
 * Hence script skeleton is pepared but not validated the whole scenario
 * Once done will dry run and verify
 */

public class Web_VirtualSkinfiri_SessionExpiry_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin{
	WebDriver driver;

	public Web_VirtualSkinfiri_SessionExpiry_Validations() throws Exception {
		driver = Web_VirtualSkinfiri_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, idel time duration$")
	public void web_Chrome_browser_suribet_website_valid_URL_idel_time_duration() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, be ideal for about (\\d+) minutes$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSkinfiri_module_link_be_ideal_for_about_minutes(int arg1) throws Throwable {
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[10]"));
		String Ddetails = DrawNO.getText();
		System.out.println("Draw bid number with time details selected  "+Ddetails);
		DrawNO.click();
		Thread.sleep(2000);

		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[4]"));
		WebElement Pick6 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[6]"));
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[1]"));

		Pick3.click();
		Thread.sleep(1000);
		Pick6.click();
		Thread.sleep(1000);
		Pick2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the number table");

		//Input the stake amount 
		WebElement stkfield = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeTotalFun()']")); 
		stkfield.click();
		Thread.sleep(1000);
		stkfield.sendKeys("1");
		Thread.sleep(2000);

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
		System.out.println(driver.getTitle());

		//Validation for tab 
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.PlaceBet(false)']"));
		//		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.PlaceBet(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		//		Confirm.click();
		//		Thread.sleep(3000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

//		driver.findElement(By.xpath("//*[@ng-click='frm.sessionLoginCancel()']")).click();
//		Thread.sleep(2000);

		//Validate msg for session expiry user message while bet placing
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Session Expired due to inactivity please login again!", Vmsg);
	}

	@Then("^Web:  Place bet after the time duration, and validate the the session is expired and the user message is prompted and displayed to user to re login and establish new session$")
	public void web_Place_bet_after_the_time_duration_and_validate_the_the_session_is_expired_and_the_user_message_is_prompted_and_displayed_to_user_to_re_login_and_establish_new_session() throws Throwable {
	    Thread.sleep(3000);
	    driver.quit();
	}
}