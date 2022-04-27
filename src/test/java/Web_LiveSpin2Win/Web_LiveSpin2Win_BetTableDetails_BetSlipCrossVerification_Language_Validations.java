package Web_LiveSpin2Win;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_LiveSpin2Win_BetTableDetails_BetSlipCrossVerification_Language_Validations extends Web_LiveSpin2Win_URL_OnlineLogin {
WebDriver driver;
	
	public Web_LiveSpin2Win_BetTableDetails_BetSlipCrossVerification_Language_Validations() throws Exception {
		driver =Web_LiveSpin2Win_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, live Spin(\\d+)Win module, valid logins, bet details table, bet place and bet won and updated bet tables$")
	public void web_Chrome_browser_suribet_website_valid_URL_live_Spin_Win_module_valid_logins_bet_details_table_bet_place_and_bet_won_and_updated_bet_tables(int arg1) throws Throwable {
	    
	}

	@When("^Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details$")
	public void web_Place_bet_and_verify_the_details_updated_in_the_bet_table_and_wait_till_the_current_bet_is_completed_and_bet_table_is_updated_with_last_bet_details() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 240);
		WebDriverWait wait1 = new WebDriverWait(driver, 120);
		
		wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[@class='current_draw m-1 mt-2']/div/h6/span)[1]"))));
		TimeUnit.SECONDS.sleep(2);
		
		WebElement bettable = driver.findElement(By.xpath("//*[@class='current_draw m-1 mt-2']")); 
		Assert.assertTrue(bettable.isDisplayed());
		Assert.assertNotNull(bettable);
		System.out.println("Verified with the bet table is displayed to user and not null");
		
		WebElement drawnum = driver.findElement(By.xpath("(//*[@class='current_draw m-1 mt-2']/div/h6/span)[1]"));
		WebElement balanceamt = driver.findElement(By.xpath("(//*[@class='current_draw m-1 mt-2']/div/h6/span)[2]"));
		WebElement betrows = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));
		WebElement betamount = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span"));
		
		int b1 = Integer.parseInt(balanceamt.getText());
		System.out.println("Current draw number: "+ drawnum.getText());
		System.out.println("balance amount : "+ balanceamt.getText());
		System.out.println("bet rows: "+ betrows.getText());
		System.out.println("bet amount: "+ betamount.getText());
		
		Assert.assertEquals("0", betrows.getText());
		Assert.assertEquals("0.00", betamount.getText());
		System.out.println("Verified with the bet table before placing the bet");

		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);
		
		WebElement Pick8 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_2']/div[text() ='8']"));
		Pick8.click();
		TimeUnit.SECONDS.sleep(2);
		
		WebElement drawnum1 = driver.findElement(By.xpath("(//*[@class='current_draw m-1 mt-2']/div/h6/span)[1]"));
		WebElement balanceamt1 = driver.findElement(By.xpath("(//*[@class='current_draw m-1 mt-2']/div/h6/span)[2]"));
		
		System.out.println("Current draw number after placing bet: "+ drawnum1.getText());
		System.out.println("balance amount after placing bet : "+ balanceamt1.getText());
		System.out.println("bet rows after placing bet: "+ betrows.getText());
		System.out.println("bet amount after placing bet: "+ betamount.getText());
		

		WebElement stakenum = driver.findElement(By.xpath("//*[@ng-model='cartD.value']"));
		WebElement multiplier = driver.findElement(By.xpath("//*[@ng-hide='cartD.completeBet']/../td[4]/label")); 
		int b2 = Integer.parseInt(balanceamt1.getText());
		
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("1.00", betamount.getText());
		Assert.assertEquals("1", stakenum.getText());
		Assert.assertEquals("36.00", multiplier.getText());
		Assert.assertEquals(b1-1, b2);
		
		System.out.println("Verified with the bet table after placing the bet");
		System.out.println("Wait till the current draw is complete and the slip details are generated");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);
		
		
		WebElement lastSlip = driver.findElement(By.xpath("//*[@class='rounded p-1 last-slip-details color-secondary']")); 
		lastSlip.click();
		TimeUnit.SECONDS.sleep(3);
		
		/*
		 * Verifying the betting details with the last draw slip details
		 */
		
		WebElement lsdnum = driver.findElement(By.xpath("//*[@class='v_slip_mobile']/table/tbody/tr/td[2]"));  
		WebElement lsstk = driver.findElement(By.xpath("//ul[@class='v_slip_mobileW']/li[3]/table/tbody/tr/td[2]"));  
		WebElement lsbetnum = driver.findElement(By.xpath("//ul[@class='v_slip_mobileW']/li[2]/ul/li[3]/table/tbody/tr/td/span"));  
		WebElement lswinmul = driver.findElement(By.xpath("//ul[@class='v_slip_mobileW']/li[2]/ul/li[3]/table/tbody/tr/td[4]  "));
		
		/*
		 * Assert all the values in the betting slip 
		 */
		
		Assert.assertEquals(drawnum1.getText(), lsdnum.getText());
		Assert.assertEquals(stakenum.getText(), lsstk.getText());
		Assert.assertEquals(betamount.getText(), lsbetnum.getText());
		Assert.assertEquals(multiplier.getText(), lswinmul.getText());
		System.out.println("All the slip details values are verified with the bet table details and as expected");
		
		WebElement print = driver.findElement(By.xpath("//*[@class='print-last-slip ng-scope']"));  
		Assert.assertTrue(print.isDisplayed());
		System.out.println("Print slip button is displayed in the slip details and verified");
		
		

		//Verification of the bet table after placing bet
		WebElement bettableafter = driver.findElement(By.xpath("//*[@class='maxBetWinW']"));
		System.out.println("List of bet details: "+ bettableafter.getText());
		String[] expected11 = {"Game Info" +"\n"+ "Bet"  +"\n"+  "0.00"  +"\n"+  "Won" +"\n"+  "0.00" +"\n"+ "Max Win" +"\n"+ 
				"0.00" +"\n"+ "Last Win"  +"\n"+  "0.00"  +"\n"+  "Last Bet" +"\n"+  "0.00" +"\n"+ "Min Bet" +"\n"+ "1.00" +"\n"+ "Max Bet" +"\n"+ "500.00"};

		StringBuffer sb11 = new StringBuffer();
		for(int i = 0; i < expected11.length; i++) {
			sb11.append(expected11[i]);
		}
		String str12 = sb11.toString();
		System.out.println("string buffer type: "+str12);

		Assert.assertEquals(str12, bettableafter.getText());
		System.out.println("Verified with the bet table after placing the bet");



		//Verification of the bet combinations available to user to place bet
		
		WebElement lowertable = driver.findElement(By.xpath("//*[@ng-mouseover='grLiveCtlr.SetBetNumbersOnHover(Bp.BpId)']"));
		System.out.println("List of bet placing table is displyed to user : "+ lowertable.isDisplayed());
		Assert.assertEquals("148", lowertable.getSize());
		System.out.println("Verified with the lower bet table placing combinations");	
	}

	@Then("^Web: Verify the bet details table before place bet, after bet placed and once after current bet is completed$")
	public void web_Verify_the_bet_details_table_before_place_bet_after_bet_placed_and_once_after_current_bet_is_completed() throws Throwable {
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

	@Then("^Web: Cross verification of betting slip with the live spin table bet selection$")
	public void web_Cross_verification_of_betting_slip_with_the_live_spin_table_bet_selection() throws Throwable {
	    
	}

	@Then("^Web: Verify the language change reflects on the web page$")
	public void web_Verify_the_language_change_reflects_on_the_web_page() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}