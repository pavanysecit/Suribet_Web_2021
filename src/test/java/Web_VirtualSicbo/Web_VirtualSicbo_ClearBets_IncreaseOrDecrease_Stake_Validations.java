package Web_VirtualSicbo;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Web_VirtualRoulette.Web_VirtualRoulette_URL_OnlineLogin;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_ClearBets_IncreaseOrDecrease_Stake_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSicbo_ClearBets_IncreaseOrDecrease_Stake_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	} 
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module, place bet, Clear All, clear individually, stake increase or decrease individually$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_place_bet_Clear_All_clear_individually_stake_increase_or_decrease_individually() throws Throwable {
		
	}

	@When("^Web: Login to suribet website with valid login details, redirect to VirtualSicbo module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_redirect_to_VirtualSicbo_module_link_and_place_bet() throws Throwable {
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[7]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		//Selecting the bet number and placing the bet
		WebElement betType1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		WebElement betType2 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		WebElement betType3 = driver.findElement(By.xpath("//*[ text() = ' Odd ']"));
		WebElement betType4 = driver.findElement(By.xpath("//*[ text() = ' Even ']"));
		
		betType1.click();
		Thread.sleep(1000);
		betType2.click();
		Thread.sleep(1000);
		betType3.click();
		Thread.sleep(1000);
		betType4.click();
		Thread.sleep(1000);

		//Verify the increasing and decreasing the stake and verify the multiplier for each selection
		//		WebElement Drawnum = driver.findElement(By.xpath("(//div[@class='b_S_R_H']/span[1])[5]"));
		//		Assert.assertEquals("477", Drawnum.getText());
		//		System.out.println("DrawNum is verified: "+ Drawnum.getText());
		//		Thread.sleep(1000);

		WebElement betnum = driver.findElement(By.xpath("(//div[@class='sicBetName']/span[1])[1]"));
		Assert.assertEquals("even", betnum.getText());
		System.out.println("Betnumber is verified: "+ betnum.getText());
		Thread.sleep(1000);

		//Verify the stake multiplier before and after increasing the stake 
		WebElement stakemultiplier = driver.findElement(By.xpath("(//div[@class='sicBetName']/div[3])[1]")); 
		System.out.println("Stake Multiplier for straight bet placed is: "+stakemultiplier.getText());
		Assert.assertEquals("Win : 2", stakemultiplier.getText());
		System.out.println("Stake Multiplier verified");

		//Total stake amount placed for 4 bets 
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]"));
		Assert.assertEquals("4", stakeamount.getText());
		System.out.println("Stake amount after placing bet is verified");

		WebElement PStake = driver.findElement(By.xpath("(//span[@ng-click='globalsicBoCtlr.addChipeFun(a,1)'])[1]"));
		WebElement PStake1 = driver.findElement(By.xpath("(//span[@ng-click='globalsicBoCtlr.addChipeFun(a,-1)'])[1]"));

		PStake.click();
		Thread.sleep(1000);
		PStake.click();
		Thread.sleep(1000);
		PStake.click();
		Thread.sleep(2000);
		System.out.println("Stake Multiplier when stake is increased by 4 value: "+stakemultiplier.getText());
		Assert.assertEquals("Win : 8", stakemultiplier.getText());
		System.out.println("After Stake Multiplier verified");

		//Stake amount after increasing the stake amount
		stakeamount.click();
		Thread.sleep(1000);
		Assert.assertEquals("7", stakeamount.getText());
		System.out.println("Stake amount after increasing bet is verified");

		//Verify the stake multiplier before and after decreasing the stake 
		System.out.println("Stake Multiplier when stake is 4 value: "+stakemultiplier.getText());
		Assert.assertEquals("Win : 8", stakemultiplier.getText());
		System.out.println("Before decreasing stake multiplier verified");

		PStake1.click();
		Thread.sleep(1000);
		PStake1.click();
		Thread.sleep(2000);

		System.out.println("Stake Multiplier when stake is 2 value: "+stakemultiplier.getText());
		Assert.assertEquals("Win : 4", stakemultiplier.getText());
		System.out.println("After decreasing stake multiplier verified");

		//Stake amount after decreasing the stake amount
		stakeamount.click();
		Thread.sleep(1000);
		Assert.assertEquals("5", stakeamount.getText());
		System.out.println("Stake amount after decreasing bet is verified");

		//Verify the clear individually button functionality in the betting slip column

		WebElement stakerows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div/span[2]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		Assert.assertEquals("4", stkrows);
		System.out.println("No of bet rows before removing: "+ stkrows);

		WebElement clearbet = driver.findElement(By.xpath("//span[@class='fa fa-times slideMenuFalse']"));
		clearbet.click();
		Thread.sleep(1000);
		String AftersStakRows = stakerows.getText();
		Assert.assertEquals("3", AftersStakRows);
		System.out.println("No of bet rows after removing: "+ AftersStakRows);

		//Clear all the stake
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.cartClearFun()']"));
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
