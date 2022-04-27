package Web_SportBetting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_SportBetting_BetTableDetails_BetSlipCrossVerification_Language_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_BetTableDetails_BetSlipCrossVerification_Language_Validations() throws Exception {
		driver = Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, sports betting module, valid logins, bet details table, bet place$")
	public void web_Chrome_browser_suribet_website_valid_URL_sports_betting_module_valid_logins_bet_details_table_bet_place() throws Throwable {

	}

	@When("^Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details$")
	public void web_Place_bet_and_verify_the_details_updated_in_the_bet_table_and_wait_till_the_current_bet_is_completed_and_bet_table_is_updated_with_last_bet_details() throws Throwable {

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		WebElement upmatch1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]"));

		upmatch.click();
		Thread.sleep(2000);
		upmatch1.click();
		Thread.sleep(2000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		String up = upmatch.getText();
		System.out.println(up);
		String up1 = upmatch1.getText();
		System.out.println(up1);

		WebElement gamename = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[3]/parent::li/parent::ul/li/span")); 
		WebElement gamename1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]/parent::li/parent::ul/li/span"));
		System.out.println(gamename.getText());
		System.out.println(gamename1.getText());

		WebElement bsgamename = driver.findElement(By.xpath("//*[@class='name heigth-outright ng-binding']")); 
		WebElement bsgamename1 = driver.findElement(By.xpath("(//*[@class='name heigth-outright ng-binding'])[2]"));
		System.out.println(bsgamename.getText());
		System.out.println(bsgamename1.getText());

		WebElement bswinning = driver.findElement(By.xpath("//*[@class='name heigth-outright ng-binding']/parent::div/div[2]")); 
		WebElement bswinning1 = driver.findElement(By.xpath("(//*[@class='name heigth-outright ng-binding'])[2]/parent::div/div[2]"));
		System.out.println(bswinning.getText());
		System.out.println(bswinning1.getText());

		//Verify the selected bet is same as the betting slip bet details

		boolean result1 = gamename.getText().equalsIgnoreCase(bsgamename.getText());
		boolean result2 = gamename1.getText().equalsIgnoreCase(bsgamename1.getText());

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		System.out.println("game selction validation is verified with betting slip");

		//Bet winning number details
		Assert.assertEquals(bswinning1.getText(), up);
		Assert.assertEquals(bswinning.getText(), up1);
		System.out.println(" winning multiplier validation is verified with betting slip");
	}

	@Then("^Web: Verify the bet details table before place bet$")
	public void web_Verify_the_bet_details_table_before_place_bet() throws Throwable {

		//Language Change in the web Page
		//		Actions actions = new Actions(driver);
		//		WebElement mainMenu = driver.findElement(By.xpath("//*[@class='flag-England active']"));
		//		actions.moveToElement(mainMenu);
		//		actions.click().build().perform();
		//		Thread.sleep(3000);
		//		WebElement subMenu = driver.findElement(By.xpath("//*[@class='flag-Portugal']"));
		//		actions.moveToElement(subMenu);
		//		actions.click().build().perform();
		//		Thread.sleep(5000);
		//		WebElement logout = driver.findElement(By.xpath("(//span[@ng-click='frm.Logout()'])[2]")); 
		//		Assert.assertEquals("Sair", logout.getText());
		//		System.out.println("Verified with the bet table after completion of the bet");
		//*[@class='flag-England']
		//*[@class='flag-England active']
		//*[@class='flag-Portugal']
	}

	@Then("^Web: Cross verification of betting slip with the sports table bet selection$")
	public void web_Cross_verification_of_betting_slip_with_the_sports_table_bet_selection() throws Throwable {

	}

	@Then("^Web: Verify the language change reflects on the web page$")
	public void web_Verify_the_language_change_reflects_on_the_web_page() throws Throwable {
		driver.quit();
	}
}