package Web_VirtualSkinfiri;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_PreviousResult_Validations extends Web_VirtualSkinfiri_URL_CardLogin {
WebDriver driver;
	
	public Web_VirtualSkinfiri_PreviousResult_Validations() throws Exception {
		driver =Web_VirtualSkinfiri_URL_CardLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, bet placing, last ten draw results$")
	public void web_Chrome_browser_suribet_website_valid_URL_bet_placing_last_ten_draw_results() throws Throwable {
	    
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, check the recent results section$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSkinfiri_module_link_check_the_recent_results_section() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement Mbal = driver.findElement(By.xpath("//*[@ng-bind='cardAccountId']"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		Thread.sleep(2000);

		//last win results to be displayed to user
		List<WebElement> RR = driver.findElements(By.xpath("//li[@ng-repeat='r in VDGCtr.vsGettopresult']"));
		System.out.println("Size of List: "+RR.size());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@ng-repeat='r in VDGCtr.vsGettopresult']")).isDisplayed());
		Assert.assertTrue(!RR.isEmpty());
		Assert.assertEquals(28, RR.size() ,0.00);
		System.out.println("Recent draw results is not empty and displayed to user");

		WebElement cdraw = driver.findElement(By.xpath("//*[@class='ul_clear  v_s_match_list']/li/span")); 
		String cd = cdraw.getText().replaceAll("[^0-9]", "");
		int i=Integer.parseInt(cd); 
		System.out.println("current draw:"+cd);	
		WebElement past1 = driver.findElement(By.xpath("(//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO'])[1]/div/sapn")); 
		WebElement past2 = driver.findElement(By.xpath("(//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO'])[2]/div/sapn"));

		String pastdraw1 = past1.getText().replaceAll("[^0-9]", "");
		String pastdraw2 = past2.getText().replaceAll("[^0-9]", "");
		System.out.println("pastdraw1:"+pastdraw1);	
		System.out.println("pastdraw2:"+pastdraw2);	
		int i1=Integer.parseInt(pastdraw1);
		int i2=Integer.parseInt(pastdraw2);

		//Assert the last 2 draw numbers with the current draw and verify the only the last 2 draw results are displayed in the result section
		Assert.assertEquals(i, i1+1 , 0);
		Assert.assertEquals(i, i2+2 , 0);
		System.out.println("Verified the last two draw results are displayed to user");
	}

	@Then("^Web:  Validate the current draw and the results shows the previous (\\d+) draw results with time id and draw num being displayed to user$")
	public void web_Validate_the_current_draw_and_the_results_shows_the_previous_draw_results_with_time_id_and_draw_num_being_displayed_to_user(int arg1) throws Throwable {
		/*
		 * Remaining draws for the current day validations
		 */
		
		WebElement cdraw = driver.findElement(By.xpath("//*[@ng-repeat='a in VDGCtr.vsGetDraws']/span"));  
		String cd = cdraw.getText().replaceAll("[^0-9]", "");
		
		/*
		 * Total draws for the day is 478
		 * Current draw minus total draws provide the remaining draws for the day
		 * 
		 */
		int i = Integer.parseInt(cd)-478;
		WebElement cdraw1 = driver.findElement(By.xpath("(//*[@ng-repeat='a in VDGCtr.vsGetDraws'])["+i+"]/span"));  
		String cd1 = cdraw1.getText().replaceAll("[^0-9]", "");
		Assert.assertEquals("478", cd1);
		System.out.println("Verified the total draws for the current day");
		driver.quit();
	}
}