package Web_VirtualSkinfiri;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_ClearBets_IncreaseOrDecrease_Stake_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	
	public Web_VirtualSkinfiri_ClearBets_IncreaseOrDecrease_Stake_Validations() throws Exception {
		driver =Web_VirtualSkinfiri_URL_OnlineLogin();
	} 

	
	@Given("^Web: Chrome browser, suribet website valid URL, skinfiri module, place bet, Clear All, clear individually, stake increase or decrease individually$")
	public void web_Chrome_browser_suribet_website_valid_URL_skinfiri_module_place_bet_Clear_All_clear_individually_stake_increase_or_decrease_individually() throws Throwable {
	   
	}

	@When("^Web: Login to suribet website with valid login details, redirect to VirtualSkinfiri module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_redirect_to_VirtualSkinfiri_module_link_and_place_bet() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[6]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers from the Skinfiri Board top place the bet 
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[4]"));
		WebElement Pick6 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[6]"));
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[1]"));

		Pick3.click();
		Thread.sleep(1000);
		Pick6.click();
		Thread.sleep(1000);
		Pick2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the number table");


//		stake input Field //*[@ng-change='VDGCtr.stakeValueFun()']
//
//		StackOverflowError multiplier //*[@ng-change='VDGCtr.stakeValueFun()']/../div[3]/span
//		//*[@ng-change='VDGCtr.stakeValueFun()']/../div[3]

		//Verify the stake multiplier before and after increasing the stake 
		WebElement stakemultiplier = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeValueFun()']/../div[3]")); 
		System.out.println("Stake Multiplier for straight bet placed is: "+stakemultiplier.getText());
		boolean stakecompare = stakemultiplier.getText().contains("0");
		System.out.println(stakecompare);
		Assert.assertTrue(stakecompare);
		System.out.println("Before Stake Multiplier verified");

		//Total stake amount placed for 4 bets 
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]"));
		Assert.assertEquals("0", stakeamount.getText());
		System.out.println("Stake amount after placing bet is verified");

		WebElement PStake = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeValueFun()']"));
		PStake.click();
		Thread.sleep(1000);
		PStake.sendKeys("4");
		Thread.sleep(2000);
		System.out.println("Stake Multiplier when stake is increased by 4 value: "+stakemultiplier.getText());
		boolean afterstakecompare = stakemultiplier.getText().contains("124");
		System.out.println(afterstakecompare);
		Assert.assertTrue(afterstakecompare);
		System.out.println("After Stake Multiplier verified");

		//Stake amount after increasing the stake amount
		Thread.sleep(1000);
		Assert.assertEquals("4", stakeamount.getText());
		System.out.println("Stake amount after increasing bet is verified");

		//Verify the stake multiplier before and after decreasing the stake 
		System.out.println("Stake Multiplier when stake is 4 value: "+stakemultiplier.getText());
		System.out.println("Before decreasing stake multiplier verified");

		PStake.clear();
		Thread.sleep(1000);
		PStake.click();
		Thread.sleep(1000);
		PStake.sendKeys("2");
		Thread.sleep(2000);

		System.out.println("Stake Multiplier when stake is 2 value: "+stakemultiplier.getText());
		boolean decstakecompare = stakemultiplier.getText().contains("62");
		System.out.println(decstakecompare);
		Assert.assertTrue(decstakecompare);
		System.out.println("After decreasing stake multiplier verified");

		//Stake amount after decreasing the stake amount
		Assert.assertEquals("2", stakeamount.getText());
		System.out.println("Stake amount after decreasing bet is verified");


		//Verify the clear individually button functionality in the betting slip column
		WebElement stakerows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[1]/span[2]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		Assert.assertEquals("3", stkrows);
		System.out.println("No of bet rows before removing: "+ stkrows);

		WebElement clearbet = driver.findElement(By.xpath("//*[@class='fa fa-times slip_close slideMenuFalse ']"));
		clearbet.click();
		Thread.sleep(1000);
		String AftersStakRows = stakerows.getText();
		Assert.assertEquals("2", AftersStakRows);
		System.out.println("No of bet rows after removing: "+ AftersStakRows);

		//Clear all the stake
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.fn_btnClear()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEARALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);
		Clear.click();
		Thread.sleep(3000);

		//check for the bets are still available after clearing all the bets 
		WebElement sk = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[1]/span[2]")); 
		String sk1 = sk.getText();
		String sk11 = sk1.replaceAll(",", "");
		Assert.assertEquals("0", sk11);
		System.out.println("No of bet rows after clearing all the bets from betting tray: "+ sk11);
		System.out.println("Bet rows are not vailable in betting slip column hence verified");
	}

	@Then("^Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively$")
	public void web_Cancel_individually_and_clear_all_verify_the_stake_amount_and_verify_the_changes_respectively() throws Throwable {
	    Thread.sleep(3000);
	    driver.quit();
	}
}