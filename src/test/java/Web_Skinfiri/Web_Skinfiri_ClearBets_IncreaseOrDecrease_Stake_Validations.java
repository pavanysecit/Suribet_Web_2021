package Web_Skinfiri;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Skinfiri_ClearBets_IncreaseOrDecrease_Stake_Validations extends Web_Skinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	
	public Web_Skinfiri_ClearBets_IncreaseOrDecrease_Stake_Validations() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
		
	    WebDriverWait wait = new WebDriverWait(driver, 120);
 		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
 		wait.until(ExpectedConditions.visibilityOf(ACC));
 		Thread.sleep(3000);
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL,  Skinfiri module, place bet, Clear All, clear individually, stake increase or decrease individually$")
	public void web_Chrome_browser_suribet_website_valid_URL_Skinfiri_module_place_bet_Clear_All_clear_individually_stake_increase_or_decrease_individually() throws Throwable {
		
	}

	@When("^Web: Login to suribet website with valid login details, redirect to Skinfiri module link and place bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_redirect_to_Skinfiri_module_link_and_place_bet() throws Throwable {
		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("//ul[@class='ul_clear in_R_up_ul  skinfri_class']/li[3]"));
		WebElement upmatch1 = driver.findElement(By.xpath("//ul[@class='ul_clear in_R_up_ul  skinfri_class']/li[2]"));
		WebElement upmatch2 = driver.findElement(By.xpath("//ul[@class='ul_clear in_R_up_ul  skinfri_class']/li[1]"));

		upmatch.click();
		Thread.sleep(2000);
		upmatch1.click();
		Thread.sleep(2000);
		upmatch2.click();
		Thread.sleep(2000);

		WebElement stake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div[2]/span[2]/input"));
		

		// Verify the null value acceptations in stake amount
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));

		//FORWARD AND PLACE BET 
		Forward.click();
		Thread.sleep(2000);

		WebElement Validmsg = driver.findElement(By.id("DivDgCartAlert")); 
		String Vmsg = Validmsg.getText();
		System.out.println("User message : "+ Vmsg);
		Assert.assertEquals("you have not selected any bet", Vmsg);
		Thread.sleep(1500);

		WebElement Pick9 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_9  Soldout'])[1]"));
		WebElement Pick25 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_25  Soldout'])[1]"));
		WebElement Pick27 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_27  Soldout'])[1]"));
//		WebElement Pick7 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_24  Soldout'])[1]"));
		Pick9.click();
		Thread.sleep(1000);
		Pick25.click();
		Thread.sleep(1000);
		Pick27.click();
		Thread.sleep(1000);
//		Pick7.click();
//		Thread.sleep(1000);

		// Least bet amount 
		Forward.click();
		Thread.sleep(2000);
		
		String Vmsg1 = Validmsg.getText();
		System.out.println("User message for least bet amount : "+ Vmsg1);
		Assert.assertEquals("Minimum Bet should be atleastSRD 1!", Vmsg1);
		Thread.sleep(1500);

		stake.click();
		Thread.sleep(1000);
		stake.clear();
		stake.sendKeys("5");
		Thread.sleep(2000); 

		//Total stake amount placed for 3 bets 
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div[3]/span[2]"));
		Assert.assertEquals("15", stakeamount.getText());
		System.out.println("Stake amount for selected bet is verified");

		//		WebElement back = driver.findElement(By.xpath("//*[text()='BACK']"));
		//		back.click();
		//		Thread.sleep(2000);

		stake.clear();
		stake.sendKeys("20");
		Thread.sleep(2000); 

		Assert.assertEquals("60", stakeamount.getText());
		System.out.println("Stake amount for selected bet is verified");
		//		back.click();
		//		Thread.sleep(2000);

		//Stake amount after decreasing the stake amount
		stake.clear();
		Thread.sleep(1000); 
		stake.sendKeys("6");
		Thread.sleep(2000); 

		Assert.assertEquals("18", stakeamount.getText());
		System.out.println("Stake amount after decreasing bet is verified");

		//Verify the clear individually button functionality in the betting slip column
		WebElement stakerows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div[1]/span[2]")); 
		String stakrows = stakerows.getText();
		String stkrows = stakrows.replaceAll(",", "");
		Assert.assertEquals("3", stkrows);
		System.out.println("No of bet rows before removing: "+ stkrows);

		WebElement clearbet = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.removeSlip(slips)']"));
		clearbet.click();
		Thread.sleep(1000);
		String AftersStakRows = stakerows.getText();
		Assert.assertEquals("2", AftersStakRows);
		System.out.println("No of bet rows after removing: "+ AftersStakRows);

		//Clear all the stake
		WebElement Clear = driver.findElement(By.xpath("//*[@ng-click='DailyGameCtrl.db_cart_clear_all()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEARALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);
		Clear.click();
		Thread.sleep(3000);

		//check for the bets are still available after clearing all the bets 
		Assert.assertEquals("0", stakeamount.getText());
		System.out.println("Bet rows are with zero values when no bets selected in the betting slip");
	}

	@Then("^Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively in Skinfiri module$")
	public void web_Cancel_individually_and_clear_all_verify_the_stake_amount_and_verify_the_changes_respectively_in_Skinfiri_module() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}