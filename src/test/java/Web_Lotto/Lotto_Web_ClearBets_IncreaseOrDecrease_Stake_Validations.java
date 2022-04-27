package Web_Lotto;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Lotto_Web_ClearBets_IncreaseOrDecrease_Stake_Validations extends Lotto_Web_URL_OnlineLogin{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, Lotto module, place bet, Clear All, clear individually$")
	public void web_Chrome_browser_suribet_website_valid_URL_Lotto_module_place_bet_Clear_All_clear_individually() throws Throwable {
		driver = Lotto_URL_OnlineLogin();
		Thread.sleep(2000);
		
	    WebDriverWait wait = new WebDriverWait(driver, 120);
 		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
 		wait.until(ExpectedConditions.visibilityOf(ACC));
 		Thread.sleep(5000);
	}

	@When("^Web: Login to suribet website with valid login details, redirect to Lotto module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_redirect_to_Lotto_module_link_and_place_bet() throws Throwable {
		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("//ul[@class='ul_clear in_R_up_ul']/li[1]"));

		upmatch.click();
		Thread.sleep(2000);
		
		// Verify the null value acceptations in stake amount
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.lottoCartSubmit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.lottoCartSubmit()']"));

		//FORWARD AND PLACE BET 
		Forward.click();
		Thread.sleep(2000);

		WebElement Validmsg = driver.findElement(By.xpath("(//div[@class='ng-binding'])[3]")); 
		String Vmsg = Validmsg.getText();
		System.out.println("User message : "+ Vmsg);
		Assert.assertEquals("please select a bet", Vmsg);
		Thread.sleep(1500);

		WebElement _1pick = driver.findElement(By.xpath("(//i[@class='fa fa-hand-pointer-o'])[1]"));
		_1pick.click();
		Thread.sleep(2000);
		
		//Fetch the stake amount for the selected bets
		WebElement stakeamount = driver.findElement(By.xpath("(//input[@type='text'])[14]")); 
		String stakeamt = stakeamount.getAttribute("value");
		//String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		System.out.println("Minimum stake amount should get displayed automatically for selected bet type: "+ stkamt );
		String expected = "2.5";
		String actual = stkamt;
		Assert.assertEquals(expected, actual);
		Thread.sleep(2000);
		
		if(stakeamount.isEnabled())
		{
			System.out.println("Field editable:" +stakeamount.isEnabled());
			System.out.println("Test case failed as Stake amount field is editable");
		}
		else {
			System.out.println("Field editable:" +stakeamount.isEnabled());
			System.out.println("Stake amount field is not editable. Hence testcase passed");
		}
	
		//increase the bet types and check the stake amount and no of rows
		_1pick.click();
		Thread.sleep(1000);
		_1pick.click();
		Thread.sleep(1000);
		
		WebElement stakeamount1 = driver.findElement(By.xpath("(//input[@type='text'])[14]")); 
		String stakeamt1 = stakeamount1.getAttribute("value");
		String stkamt1 = stakeamt1.replaceAll(",", "");
		String expected1 = "7.5";
		String actual1 = stkamt1;
		Assert.assertEquals(expected1, actual1);
		Thread.sleep(2000);
		
		//Verify the clear individually button functionality in the betting slip column
		WebElement stakerows = driver.findElement(By.xpath("(//span[@class='slideMenuFalse ng-binding'])[2]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		Assert.assertEquals("3", stkrows);
		System.out.println("No of bet rows before removing: "+ stkrows);

		WebElement clearbet = driver.findElement(By.xpath("(//span[@class='fa fa-times slideMenuFalse'])[1]"));
		clearbet.click();
		Thread.sleep(1000);
		String AftersStakRows = stakerows.getText();
		Assert.assertEquals("2", AftersStakRows);
		System.out.println("No of bet rows after removing: "+ AftersStakRows);

		//Clear all the stake
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.lottoCartClearAll()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEAR ALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);
//		driver.findElement(By.xpath("//div[text()='CLEAR ALL']")).click();
		Clear.click();
		Thread.sleep(3000);

		//check for the bets are still available after clearing all the bets 
		Assert.assertEquals("0", stakeamount.getText());
		System.out.println("Bet rows are with zero values when no bets selected in the betting slip");
	}

	@Then("^Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively in Lotto module$")
	public void web_Cancel_individually_and_clear_all_verify_the_stake_amount_and_verify_the_changes_respectively_in_Lotto_module() throws Throwable {
	    driver.close();
	}
}