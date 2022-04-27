package Web_VirtualSicbo;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualSicbo_Login_Home_Validations {
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, valid & invalid login details and home button$")
	public void web_Chrome_browser_suribet_website_valid_URL_valid_invalid_login_details_and_home_button() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://suribet.sr");
		driver.manage().window().maximize();
		Thread.sleep(4000);

		//clicking on login button and entering Invalid credentials
//		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-scope.active")));
		WebElement login = driver.findElement(By.cssSelector(".fa.fa-sign-in"));
		login.click();
		Thread.sleep(2000);
		WebElement UN = driver.findElement(By.id("txtUserName"));
		UN.sendKeys("pmansoorktr1@gmail.com");
		Thread.sleep(1000);
		WebElement PWD = driver.findElement(By.xpath("(//*[@type='password'])[7]"));
		PWD.sendKeys("mansoor@12341");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.className("re_submit_but"));
		submit.click();
		Thread.sleep(2000);

		String actual= driver.findElement(By.xpath("//*[contains(text(), 'Please Enter a valid Email/Mobile and Password')]")).getText();

		//		WebElement element = driver.findElement(By.xpath("//div[ends-with(@class, 'ng-binding')] and [contains(@ng-bind, 'frm.ValidationMsg')]"));
		//		System.out.println(element.getText());
		//		String actual = driver.findElement(By.cssSelector("*.validation_div.ng-binding:nth-child(5)")).getText();
		System.out.println("What display msg: " +actual);
		String expected = "Please Enter a valid Email/Mobile and Password";
		Assert.assertEquals(actual, expected);

		WebElement VirtaulGames1 = driver.findElement((By.xpath("(//a[contains(@ng-class, \"{'newProduct':x.newicon}\")])[3]")));
		String Vgames1 = VirtaulGames1.getText();
		System.out.println("Virtual Game dropdown: " +Vgames1);	

		/*
		 * For Validation lets launch the Virtual Roulette game and verify the account is not visiable and validate the logout state in the roullete game for invalid login credentials
		 */
		Actions actions = new Actions(driver);
		//		WebElement VirtaulGames = driver.findElement(By.xpath("//*[contains(text(), 'Virtual Games')]"));
		//		String Vgames = VirtaulGames.getText();
		//		System.out.println("Virtual Game dropdown: " +Vgames);
		//		String expected1 = "Virtual Games";
		//		Assert.assertEquals(Vgames, expected1);
		//		Thread.sleep(2000);

		//Mouse over on the dropdown
		actions.moveToElement(VirtaulGames1).perform();
		Thread.sleep(2000);
		// Selecting the VirtualSicbo game from the dropdown
		WebElement subMenuOption = driver.findElement(By.xpath("(//a[(@href= '/Betting/VirtualSicbo/')])[3]")); 
		System.out.println("Dropdown visible: "+subMenuOption.isDisplayed());
		//Mouse hover menuOption 'VIRTUAL ROULETTE'
		actions.moveToElement(subMenuOption).click().build().perform();
		Thread.sleep(2000);
		System.out.println("Done Mouse hover and selecting 'VIRTUAL SICBO' from Menu");

		/*
		 * Landed to the Virtual Roulette game page
		 * Wait till the page loads completely
		 */
		WebElement barcode = driver.findElement(By.xpath("//input[@placeholder='Enter Barcode/SlipId']")); 
		wait1.until(ExpectedConditions.visibilityOf(barcode));
		Thread.sleep(5000);

		//Verify for the state of account for invalid credentials 
		WebElement UID = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']")); 
		Assert.assertTrue(UID.isDisplayed());
		System.out.println("After Virtual Sicbo game loaded, account state is in logoff condition and verified: "+ UID.isDisplayed());

		//Click on Suribet home link to navigate back to home screen
		WebElement SuribetHome = driver.findElement(By.cssSelector(".logo-w-innerpge.ng-binding"));
		SuribetHome.click();

		WebElement login1 = driver.findElement(By.xpath("//span[@ng-click='toggleFun(2)']"));
		wait1.until(ExpectedConditions.visibilityOf(login1));
		Thread.sleep(2000);
		Assert.assertTrue(login1.isDisplayed());
		System.out.println("Redirected back to Suribet home page");

		//Login with valid credentials and verify the state of the VirtualSicbo Game
		login1.click();
		Thread.sleep(2000);
		WebElement UN1 = driver.findElement(By.id("txtUserName"));
		UN1.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement PWD1 = driver.findElement(By.xpath("(//*[@type='password'])[7]"));
		PWD1.sendKeys("mansoor@123");
		Thread.sleep(1000);
		WebElement submit1 = driver.findElement(By.className("re_submit_but"));
		submit1.click();
		Thread.sleep(2000);
		System.out.println("Login with valid credentials");

		WebElement VirtaulGames = driver.findElement((By.xpath("(//a[contains(@ng-class, \"{'newProduct':x.newicon}\")])[3]")));
		actions.moveToElement(VirtaulGames).perform();
		Thread.sleep(2000);
		WebElement subMenuOption1 = driver.findElement(By.xpath("(//a[(@href= '/Betting/VirtualSicbo/')])[3]")); 
		actions.moveToElement(subMenuOption1).click().build().perform();
		Thread.sleep(10000);
		System.out.println("Virtual Sicbo is selected from the dropdowns");

		//wait till the account ID is  displayed
		WebElement ID = driver.findElement(By.className("ac_id")); 
		wait1.until(ExpectedConditions.visibilityOf(ID));
		Thread.sleep(5000);

		//Assert the same account is being login for the credntials
		String AccNum = "9308481390";
		Assert.assertEquals(AccNum, ID.getText());
		System.out.println("For valid credentials user is login state and with valid assigned Account ID: "+ ID.getText());

		//Click on suribet home page to redirectd to the home page with the account being in login state
		WebElement SuriHome = driver.findElement(By.cssSelector(".logo-w-innerpge.ng-binding"));
		SuriHome.click();
		Thread.sleep(5000);
		WebElement logoff = driver.findElement(By.xpath("//span[@ng-click='frm.Logout()']"));
		wait1.until(ExpectedConditions.visibilityOf(logoff));
		Thread.sleep(2000);
		Assert.assertTrue(logoff.isDisplayed());
		System.out.println("Redirected back to Suribet home page and the Account is in login state: "+ logoff.isDisplayed());
	}

	@When("^Web: Open the chrome browser, Enter the valid suribet URL, Click on VirtualSicbo module link, enter valid and invalid login details and click on home button$")
	public void web_Open_the_chrome_browser_Enter_the_valid_suribet_URL_Click_on_VirtualSicbo_module_link_enter_valid_and_invalid_login_details_and_click_on_home_button() throws Throwable {
	   
	}

	@Then("^Web: User should be blocked from logging to the suribet website$")
	public void web_User_should_be_blocked_from_logging_to_the_suribet_website() throws Throwable {
	   
	}

	@Then("^Web: User should able to see the VirtualSicbo module link in the home page and should see VirtualSicbo module home page after login & clicking on VirtualSicbo module$")
	public void web_User_should_able_to_see_the_VirtualSicbo_module_link_in_the_home_page_and_should_see_VirtualSicbo_module_home_page_after_login_clicking_on_VirtualSicbo_module() throws Throwable {
	   
	}

	@Then("^Web: User should be navigated to the Home page of suribet website after clicking on home link$")
	public void web_User_should_be_navigated_to_the_Home_page_of_suribet_website_after_clicking_on_home_link() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}
