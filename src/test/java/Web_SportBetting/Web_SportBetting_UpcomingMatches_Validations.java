package Web_SportBetting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_SportBetting_UpcomingMatches_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_UpcomingMatches_Validations() throws Exception {
		driver = Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, sports betting module,login via online method, upcoming matches table with data and the details and statitcs link$")
	public void web_Chrome_browser_suribet_website_valid_URL_sports_betting_module_login_via_online_method_upcoming_matches_table_with_data_and_the_details_and_statitcs_link() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
	}

	@When("^Web: Login to sport betting and validate the table structure data are all present and the details and statitcs link and details dropdowns are available$")
	public void web_Login_to_sport_betting_and_validate_the_table_structure_data_are_all_present_and_the_details_and_statitcs_link_and_details_dropdowns_are_available() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement upmatches = driver.findElement(By.xpath("//*[@class='heading mr-auto ng-binding']"));
		Assert.assertEquals("UPCOMING MATCHES", upmatches.getText());
		Thread.sleep(2000); 

		WebElement topmatches = driver.findElement(By.xpath("(//*[@class='heading mr-auto ng-binding'])[2]"));
		Assert.assertEquals("TOP MATCH", topmatches.getText());
		Thread.sleep(2000); 

		//Verifying the table heading are avaialble for the user and the table structure
		WebElement tip = driver.findElement(By.xpath("//*[@ng-repeat='(key,bettype) in globalSportsCtlr.BetTypeTemplate[0].Betlist']"));
		Assert.assertEquals("Tip", tip.getText());
		Thread.sleep(2000);  

		WebElement Dchances = driver.findElement(By.xpath("//*[@ng-repeat='(key,bettype) in globalSportsCtlr.BetTypeTemplate[0].Betlist'][2]"));
		Assert.assertEquals("Double chance", Dchances.getText());
		Thread.sleep(2000); 

		WebElement Date = driver.findElement(By.xpath("//*[@class='matches-header px-0 py-1']/ul[2]/li[2]"));
		Assert.assertEquals("Date", Date.getText());
		Thread.sleep(2000); 

		WebElement Tmultiplier = driver.findElement(By.xpath("//*[@class='matches-header px-0 py-1']/ul[2]/li[3]/ul"));
		System.out.println("Tip multiplier data to be print: "+Tmultiplier.getText());
		Assert.assertEquals("1\nX\n2", Tmultiplier.getText());
		Thread.sleep(2000); 

		WebElement Dmultiplier = driver.findElement(By.xpath("//*[@class='matches-header px-0 py-1']/ul[2]/li[4]/ul"));
		System.out.println("Double Chances multiplier data to be print: "+Dmultiplier.getText());
		Assert.assertEquals("1X12X2", Dmultiplier.getText().replace("\n", ""));
		Thread.sleep(2000); 

		WebElement Details = driver.findElement(By.xpath("//*[@class='d-flex pt-1']/li[6]"));
		Assert.assertEquals("Details", Details.getText());
		Thread.sleep(2000);  

		//Statitcs link verifications
		WebElement Stats = driver.findElement(By.xpath("//*[@ng-if='upmatchs.stat']"));
		Assert.assertTrue(Stats.isDisplayed());
		Thread.sleep(2000);   
		Stats.click();
		Thread.sleep(7000);  
		WebElement CloseStat = driver.findElement(By.id("closeStatistics")); 
		CloseStat.click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(Stats));
		Assert.assertTrue(Stats.isDisplayed());

		// Details drop down verifications
		WebElement DetailsLink = driver.findElement(By.xpath("(//*[@class='viewMorbut fas fa-chevron-circle-down ng-binding'])[5]"));
		Assert.assertTrue(DetailsLink.isDisplayed());
		DetailsLink.click();
		Thread.sleep(7000);  
		WebElement DetailsLinkClose = driver.findElement(By.xpath("//*[@class='viewMorbut fas fa-chevron-circle-down ng-binding']"));
		Assert.assertTrue(DetailsLinkClose.isDisplayed());
		DetailsLink.click();
		Thread.sleep(7000); 
		Assert.assertTrue(DetailsLink.isDisplayed());
	}

	@Then("^Web: Validate the details and texts in the table with the links provided in the upcoming matches table$")
	public void web_Validate_the_details_and_texts_in_the_table_with_the_links_provided_in_the_upcoming_matches_table() throws Throwable {
		driver.quit();
	}
}