package Web_Skinfiri;

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

public class Web_Skinfiri_SessionExpiry_Resultlist_Validations extends Web_Skinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Skinfiri_SessionExpiry_Resultlist_Validations() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, skinfiri module,login via online method, Session Expiry timeduration and result list search validations$")
	public void web_Chrome_browser_suribet_website_valid_URL_skinfiri_module_login_via_online_method_Session_Expiry_timeduration_and_result_list_search_validations() throws Throwable {
	   
	}

	@When("^Web: Login to skinfiri, gamelist and result list button validations, and place bet and wait for the session expiry and try placing bet  and verify the result search validations$")
	public void web_Login_to_skinfiri_gamelist_and_result_list_button_validations_and_place_bet_and_wait_for_the_session_expiry_and_try_placing_bet_and_verify_the_result_search_validations() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		
		//Clicking on date to fetch the respective results
		WebElement resdate = driver.findElement(By.xpath("(//li[@ng-repeat='resultDate in DailyGameCtrl._resultDates'])[2]"));
		resdate.click();
		Thread.sleep(3000);
		System.out.println("Selected date for fetching the result: " +resdate.getText());
		
		//compare the date in the result page
		WebElement rdate = driver.findElement(By.xpath("(//ul[@class='ul_clear in_middle_result_ul']/li/div/span)[1]"));	
		System.out.println("Date displayed on the result page: " +rdate.getText());
		Assert.assertEquals(resdate.getText(), rdate.getText());
		
		//Search criteria
		WebElement Search = driver.findElement(By.xpath("//div[@class='Search_result fa fa-search EmptyValidationBut_JS']"));
		WebElement WinNo = driver.findElement(By.xpath("//input[@ng-model='DailyGameCtrl.resultWinNo']"));
		WebElement DrawNo = driver.findElement(By.xpath("//input[@ng-model='DailyGameCtrl.resultDrawNo']"));
		WebElement Fdate = driver.findElement(By.xpath("//input[@ng-model='DailyGameCtrl.resultFrom']"));
		WebElement Tdate = driver.findElement(By.xpath("//input[@ng-model='DailyGameCtrl.resultTo']"));
		
		//Search by winning number and click on search button
		WinNo.click();
		Thread.sleep(1000);
		WinNo.sendKeys("26");
		String Wno = WinNo.getAttribute("value");
		System.out.println("Search input for Win Number: " +Wno);
		Thread.sleep(1000);
		Search.click();
		Thread.sleep(5000);
		
		WebElement DNoRes = driver.findElement(By.xpath("//div[@class='ball_skinfiri']"));
		String DNo = DNoRes.getText();
		System.out.println("Output of the result for Win Number: " +DNo);
		Assert.assertEquals(Wno, DNo);
		Thread.sleep(2000);
		WinNo.clear();
		
		//Search by the draw number 
		DrawNo.click();
		Thread.sleep(1000);
		DrawNo.sendKeys("18990");
		String Dno = DrawNo.getAttribute("value");
		System.out.println("Search input for Draw Number: " +Dno);
		Thread.sleep(1000);
		Search.click();
		Thread.sleep(5000);
		
		WebElement DRNoRes = driver.findElement(By.xpath("//ul[@class='ul_clear in_middle_result_ul']/li/div[3]/span"));
		String DRNo = DRNoRes.getText();
		System.out.println("Output of the result for Draw Number: " +DRNo);
		Assert.assertEquals(Wno, DNo);
		Thread.sleep(2000);
		DrawNo.clear();
		
		//Search by the From date and To date
		Fdate.sendKeys("27/Aug/2021");
		String fdate1 = Fdate.getAttribute("value");
		String fdate2=fdate1.replace('/','-');
		System.out.println("Search input for From date: " +fdate2);
		Thread.sleep(1000);
		
		Tdate.sendKeys("27/Aug/2021");		
		String Tdate1 = Tdate.getAttribute("value");
		String Tdate2=Tdate1.replace('/','-');
		System.out.println("Search input for To date: " +Tdate2);
		Thread.sleep(1000);
		
		Search.click();
		Thread.sleep(5000);
		WebElement rdate11 = driver.findElement(By.xpath("(//ul[@class='ul_clear in_middle_result_ul']/li/div/span)[1]"));
		Assert.assertEquals(rdate11.getText(), fdate2);
		Assert.assertEquals(rdate11.getText(), Tdate2);


		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("//ul[@class='ul_clear in_R_up_ul  skinfri_class']/li[2]"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement Pick1 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[1]"));

		Pick1.click();
		Thread.sleep(1000);
		
		WebElement stakeamount = driver.findElement(By.xpath("//input[@ng-change='DailyGameCtrl.changeAllStack()']")); 
		stakeamount.sendKeys("1");
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
		
		//Wait for the session to be expired
		Thread.sleep(7*60*1000); 
		driver.getTitle();
		Thread.sleep(8*60*1000); 
		driver.getTitle();
		Thread.sleep(6*60*1000);
		driver.getTitle();

		//Validation for tab 
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));

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

	@Then("^Web: Navigate to the result page and verify the result based on different criterias$")
	public void web_Navigate_to_the_result_page_and_verify_the_result_based_on_different_criterias() throws Throwable {
	   
	}

	@Then("^Web: Web: Validate the valid user message displayed to user and the button validations$")
	public void web_Web_Validate_the_valid_user_message_displayed_to_user_and_the_button_validations() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}