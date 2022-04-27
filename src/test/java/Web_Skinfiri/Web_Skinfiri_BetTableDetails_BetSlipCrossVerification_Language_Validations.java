package Web_Skinfiri;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Web_SportBetting.Web_SportBetting_URL_OnlineLogin;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Skinfiri_BetTableDetails_BetSlipCrossVerification_Language_Validations extends Web_Skinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	
	public Web_Skinfiri_BetTableDetails_BetSlipCrossVerification_Language_Validations() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, skinfiri module, valid logins, bet details table, bet place$")
	public void web_Chrome_browser_suribet_website_valid_URL_skinfiri_module_valid_logins_bet_details_table_bet_place() throws Throwable {
	    
	}

	@When("^Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details$")
	public void web_Place_bet_and_verify_the_details_updated_in_the_bet_table_and_wait_till_the_current_bet_is_completed_and_bet_table_is_updated_with_last_bet_details() throws Throwable {
		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("//ul[@class='ul_clear in_R_up_ul  skinfri_class']/li[1]"));
		
		upmatch.click();
		Thread.sleep(2000);

		WebElement drawno = driver.findElement(By.xpath("(//div[@class='in_middle_head skinfri_class']/span[4])[1]")); 
		WebElement drawno1 = driver.findElement(By.xpath("(//div[@class='in_middle_head skinfri_class']/span[4])[2]"));


		WebElement Pick9 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[1]"));
		WebElement Pick25 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_25  Soldout'])[1]"));
		WebElement Pick9_1 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[2]"));
		WebElement Pick25_1 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_25  Soldout'])[2]"));

		Pick9.click();
		Thread.sleep(1000);
		Pick25.click();
		Thread.sleep(1000);
		Pick9_1.click();
		Thread.sleep(1000);
		Pick25_1.click();
		Thread.sleep(2000);


		System.out.println("Selected draw no in selection screen: " +drawno.getText());
		System.out.println("Selected draw no in selection screen: " +drawno1.getText());

		//Adding # as prefix to the drawno in the betting slip
		WebElement drnobs = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[5]")); 
		WebElement drnobs1 = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[1]"));
		String drno = "#" + drnobs.getText();
		String drno1 = "#" + drnobs1.getText();
		System.out.println("Drawno displayed in the betting slip: "+drno);
		System.out.println("Drawno displayed in the betting slip: "+drno1);


		//Compating the drawno 
		Assert.assertEquals(drawno.getText(), drno);
		Assert.assertEquals(drawno1.getText(), drno1);
		System.out.println("Draw No comparision successful. Testcase passed");
	}


	@Then("^Web: Verify the bet details table before place bet$")
	public void web_Verify_the_bet_details_table_before_place_bet() throws Throwable {

	}

	@Then("^Web: Cross verification of betting slip with the skinfiri table bet selection$")
	public void web_Cross_verification_of_betting_slip_with_the_skinfiri_table_bet_selection() throws Throwable {
	    
	}

	@Then("^Web: Verify the language change reflects on the web page$")
	public void web_Verify_the_language_change_reflects_on_the_web_page() throws Throwable {
		driver.quit();
	}
}
