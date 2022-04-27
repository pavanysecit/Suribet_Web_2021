package Web_VirtualRoulette;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualRoulette_ClearBets_IncreaseOrDecrease_Stake_Validations extends Web_VirtualRoulette_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualRoulette_ClearBets_IncreaseOrDecrease_Stake_Validations() throws Exception {
		driver = Web_VirtualRoulette_URL_OnlineLogin();
	} 

	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module, place bet, Clear All, clear individually, stake increase or decrease individually$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_place_bet_Clear_All_clear_individually_stake_increase_or_decrease_individually() throws Throwable {

	}

	@When("^Web: Login to suribet website with valid login details, redirect to VirtualRoulette module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_redirect_to_VirtualRoulette_module_link_and_place_bet() throws Throwable {

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[7]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick10 = driver.findElement(By.xpath("//span[@bettypepickid='11']"));
		WebElement Pick9 = driver.findElement(By.xpath("//span[@bettypepickid='10']"));
		WebElement Pick13 = driver.findElement(By.xpath("//span[@bettypepickid='14']"));
		WebElement Pick14 = driver.findElement(By.xpath("//span[@bettypepickid='15']"));

		Pick10.click();
		Thread.sleep(1000);
		Pick9.click();
		Thread.sleep(1000);
		Pick13.click();
		Thread.sleep(1000);
		Pick14.click();
		Thread.sleep(1000);

		//Verify the increasing and decreasing the stake and verify the multiplier for each selection
		//		WebElement Drawnum = driver.findElement(By.xpath("(//div[@class='b_S_R_H']/span[1])[5]"));
		//		Assert.assertEquals("477", Drawnum.getText());
		//		System.out.println("DrawNum is verified: "+ Drawnum.getText());
		//		Thread.sleep(1000);

		WebElement betnum = driver.findElement(By.xpath("(//span[@class='BetNum14'])[2]"));
		Assert.assertEquals("14", betnum.getText());
		System.out.println("Betnum is verified: "+ betnum.getText());
		Thread.sleep(1000);

		//Verify the stake multiplier before and after increasing the stake 
		WebElement stakemultiplier = driver.findElement(By.xpath("(//label[@ng-show='cartD.splits.length==0'])[5]")); 
		System.out.println("Stake Multiplier for straight bet placed is: "+stakemultiplier.getText());
		Assert.assertEquals("36.00", stakemultiplier.getText());
		System.out.println("Before Stake Multiplier verified");

		//Total stake amount placed for 4 bets 
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]"));
		Assert.assertEquals("4.00", stakeamount.getText());
		System.out.println("Stake amount after placing bet is verified");

		WebElement PStake = driver.findElement(By.xpath("//input[@class='slideMenuFalse keypadpop ng-pristine ng-untouched ng-valid ng-not-empty']"));
		PStake.click();
		Thread.sleep(1000);
		PStake.sendKeys("4");
		Thread.sleep(2000);
		System.out.println("Stake Multiplier when stake is increased by 4 value: "+stakemultiplier.getText());
		Assert.assertEquals("144.00", stakemultiplier.getText());
		System.out.println("After Stake Multiplier verified");

		//Stake amount after increasing the stake amount
		stakeamount.click();
		Thread.sleep(1000);
		Assert.assertEquals("7.00", stakeamount.getText());
		System.out.println("Stake amount after increasing bet is verified");

		//Verify the stake multiplier before and after decreasing the stake 
		System.out.println("Stake Multiplier when stake is 4 value: "+stakemultiplier.getText());
		Assert.assertEquals("144.00", stakemultiplier.getText());
		System.out.println("Before decreasing stake multiplier verified");

		PStake.clear();
		Thread.sleep(1000);
		PStake.click();
		Thread.sleep(1000);
		PStake.sendKeys("2");
		Thread.sleep(2000);

		System.out.println("Stake Multiplier when stake is 2 value: "+stakemultiplier.getText());
		Assert.assertEquals("72.00", stakemultiplier.getText());
		System.out.println("After decreasing stake multiplier verified");

		//Stake amount after decreasing the stake amount
		stakeamount.click();
		Thread.sleep(1000);
		Assert.assertEquals("5.00", stakeamount.getText());
		System.out.println("Stake amount after decreasing bet is verified");

		//Verify the clear individually button functionality in the betting slip column

		WebElement stakerows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div/span[2]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		Assert.assertEquals("4", stkrows);
		System.out.println("No of bet rows before removing: "+ stkrows);

		WebElement clearbet = driver.findElement(By.xpath("//i[@class='fa fa-times slideMenuFalse']"));
		clearbet.click();
		Thread.sleep(1000);
		String AftersStakRows = stakerows.getText();
		Assert.assertEquals("3", AftersStakRows);
		System.out.println("No of bet rows after removing: "+ AftersStakRows);

		//Clear all the stake
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.ClearFields()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEAR ALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);
		Clear.click();
		Thread.sleep(3000);

		//check for the bets are still available after clearing all the bets 
		Assert.assertFalse(stakerows.isDisplayed());
		System.out.println("Bet rows are not vailable in betting slip column hence verified");
	}
	
	@Then("^Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively$")
	public void web_Cancel_individually_and_clear_all_verify_the_stake_amount_and_verify_the_changes_respectively() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}