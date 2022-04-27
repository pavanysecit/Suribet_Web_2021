package Web_SportBetting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_SportBetting_ClearBets_IncreaseOrDecrease_Stake_Validations extends Web_SportBetting_URL_OnlineLogin {
WebDriver driver;
	
	public Web_SportBetting_ClearBets_IncreaseOrDecrease_Stake_Validations() throws Exception {
		driver = Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, Sports Betting module, place bet, Clear All, clear individually, stake increase or decrease individually$")
	public void web_Chrome_browser_suribet_website_valid_URL_Sports_Betting_module_place_bet_Clear_All_clear_individually_stake_increase_or_decrease_individually() throws Throwable {

	}

	@When("^Web: Login to suribet website with valid login details, redirect to SportsBetting module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_redirect_to_SportsBetting_module_link_and_place_bet() throws Throwable {

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		WebElement upmatch1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]"));
		WebElement upmatch2 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[3]"));

		upmatch.click();
		Thread.sleep(2000);
		upmatch1.click();
		Thread.sleep(2000);
		upmatch2.click();
		Thread.sleep(2000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));

		// Verify the null value acceptations in stake amount
		WebElement Forward = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));

		//FORWARD AND PLACE BET 
		Forward.click();
		Thread.sleep(2000);

		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("User message : "+ Vmsg);
		Assert.assertEquals("Please Enter bet amount", Vmsg);
		Thread.sleep(1500);

		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("4");
		Thread.sleep(2000); 
		Forward.click();
		Thread.sleep(2000);

		// Least bet amount 
		String Vmsg1 = Validmsg.getText();
		System.out.println("User message for least bet amount : "+ Vmsg1);
		Assert.assertEquals("Minimum Bet should be atleast 5 SRD.Verify!", Vmsg1);
		Thread.sleep(1500);

		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		//Total stake amount placed for 3 bets 
		WebElement stakeamount = driver.findElement(By.xpath("//*[@class='bet-wrapper']/ul[2]/li[2]"));
		Assert.assertEquals("15.00", stakeamount.getText());
		System.out.println("Stake amount after placing bet is verified");

		//		WebElement back = driver.findElement(By.xpath("//*[text()='BACK']"));
		//		back.click();
		//		Thread.sleep(2000);

		WebElement PStake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		PStake.click();
		Thread.sleep(1000);
		PStake.sendKeys("8");
		Thread.sleep(2000);

		Assert.assertEquals("24.00", stakeamount.getText());
		System.out.println("Stake amount after placing bet is verified");
		//		back.click();
		//		Thread.sleep(2000);

		//Stake amount after decreasing the stake amount
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("6");
		Thread.sleep(2000); 

		Assert.assertEquals("18.00", stakeamount.getText());
		System.out.println("Stake amount after decreasing bet is verified");

		//Verify the clear individually button functionality in the betting slip column
		WebElement stakerows = driver.findElement(By.xpath("//*[@class='bet-wrapper']/ul[2]/li[1]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		Assert.assertEquals("3", stkrows);
		System.out.println("No of bet rows before removing: "+ stkrows);

		WebElement clearbet = driver.findElement(By.cssSelector(".fa.fa-close"));
		clearbet.click();
		Thread.sleep(1000);
		String AftersStakRows = stakerows.getText();
		Assert.assertEquals("2", AftersStakRows);
		System.out.println("No of bet rows after removing: "+ AftersStakRows);

		//Clear all the stake
		WebElement Clear = driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.fn_btnClear()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEAR ALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);
		Clear.click();
		Thread.sleep(3000);

		//check for the bets are still available after clearing all the bets 
		Assert.assertEquals("0.00", stakeamount.getText());
		System.out.println("Bet rows are with zero values when no bets selected in the betting slip");
	}

	@Then("^Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively in sport betting module$")
	public void web_Cancel_individually_and_clear_all_verify_the_stake_amount_and_verify_the_changes_respectively_in_sport_betting_module() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}