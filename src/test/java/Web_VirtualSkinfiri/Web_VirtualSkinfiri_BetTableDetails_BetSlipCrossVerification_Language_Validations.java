package Web_VirtualSkinfiri;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_BetTableDetails_BetSlipCrossVerification_Language_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin {
WebDriver driver;

	public Web_VirtualSkinfiri_BetTableDetails_BetSlipCrossVerification_Language_Validations() throws Exception {
		driver = Web_VirtualSkinfiri_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual skinfiri module, valid logins, bet details table, bet place and bet won and updated bet tables$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_skinfiri_module_valid_logins_bet_details_table_bet_place_and_bet_won_and_updated_bet_tables() throws Throwable {
	    
	}

	@When("^Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details$")
	public void web_Place_bet_and_verify_the_details_updated_in_the_bet_table_and_wait_till_the_current_bet_is_completed_and_bet_table_is_updated_with_last_bet_details() throws Throwable {
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]"));
		DrawNO.click();
		Thread.sleep(2000);

		WebElement bettable = driver.findElement(By.xpath("//*[@class='poker-bet-info-w d-flex align-items-center justify-content-center font-weight-bold']/div/marquee/div"));
		System.out.println("List of bet details: "+ bettable.getText());

		String[] expected = {"Bet " +"\n"+ " : 0.00"  +"\n"+  "Last Draw Bet"  +"\n"+  " : " +"\n"+  "Last Draw Win" +"\n"+ " : " +"\n"+ 
				"Min bet" +"\n"+ " : "  +"\n"+  "Max Bet"  +"\n"+  " : " +"\n"+  "Max Win" +"\n"+ " :  0.00" };
		//						+"\n"+ "1.00" +"\n"+ "Max Bet" +"\n"+ "500.00"};

		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < expected.length; i++) {
			sb.append(expected[i]);
		}
		String str1 = sb.toString();
		System.out.println("string buffer type: "+str1);

		Assert.assertEquals(str1, bettable.getText());
		System.out.println("Verified with the bet table before placing the bet");

		//Selecting the first 4 numbers and verifying the in the betting slip v/s the selected number at roulette table
		WebElement Pick8 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[9]"));
		WebElement Pick14 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[15]"));
		WebElement Pick25 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[26]"));

		Pick8.click();
		Thread.sleep(1000);
		Pick14.click();
		Thread.sleep(1000);
		Pick25.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the number table");


		//Verify the same number on the betting slip too
		String actual1 = Pick8.getText();
		String actual2 = Pick14.getText();
		String actual3 = Pick25.getText();		

		String exp1 = driver.findElement(By.xpath("(//*[@class='vs_s_bet_no_slip ng-binding'])[1]")).getText();
		String exp2 = driver.findElement(By.xpath("(//*[@class='vs_s_bet_no_slip ng-binding'])[2]")).getText();
		String exp3 = driver.findElement(By.xpath("(//*[@class='vs_s_bet_no_slip ng-binding'])[3]")).getText();

		//Assert the selected and expected values at the betting slip
		Assert.assertEquals(actual1, exp1);
		Assert.assertEquals(actual2, exp2);
		Assert.assertEquals(actual3, exp3);
		System.out.println("Both selected bet and the betting tray numbers are validated");

		WebElement First12 = driver.findElement(By.xpath("//*[text()='1ST 12']"));
		First12.click();
		Thread.sleep(2000);
		WebElement BettingSlipNumbers = driver.findElement(By.xpath("(//*[@class='b_S_R_C'])[2]"));
		System.out.println(BettingSlipNumbers.getText());
		String[] expected1 = {"1" +"\n"+ "2"  +"\n"+  "3"  +"\n"+  "4" +"\n"+  "5" +"\n"+ "6" +"\n"+ 
				"7" +"\n"+ "8"  +"\n"+  "9"  +"\n"+  "10" +"\n"+  "11" +"\n"+ "12"};

		StringBuffer sb1 = new StringBuffer();
		for(int i = 0; i < expected1.length; i++) {
			sb1.append(expected1[i]);
		}
		String str11 = sb1.toString();
		Assert.assertEquals(str11, BettingSlipNumbers.getText());
		System.out.println("Betnumber verifications in betslip");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed ");

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


		//Verification after the bet is complete
		//Converting the time period in seconds to for the wait time 
		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		System.out.println("Time duration for the draw to complete "+totaltime);
		Thread.sleep(totaltime*1000);
		Thread.sleep(1*40*1000);

		//Verification of the bet table after completion of bet
		WebElement betcomplet = driver.findElement(By.xpath("//*[@class='maxBetWinW active']"));
		System.out.println("List of bet details: "+ betcomplet.getText());
		String[] expected12 = {"Game Info" +"\n"+ "Bet"  +"\n"+  "0.00"  +"\n"+  "Won" +"\n"+  "0.00" +"\n"+ "Max Win" +"\n"+ 
				"3.00" +"\n"+ "Last Win"  +"\n"+  "0.00"  +"\n"+  "Last Bet" +"\n"+  "1.00" +"\n"+ "Min Bet" +"\n"+ "1.00" +"\n"+ "Max Bet" +"\n"+ "500.00"};

		StringBuffer sb12 = new StringBuffer();
		for(int i = 0; i < expected12.length; i++) {
			sb12.append(expected12[i]);
		}
		String str13 = sb12.toString();
		System.out.println("string buffer type: "+str13);

		Assert.assertEquals(str13, betcomplet.getText());
		System.out.println("Verified with the bet table after completion of the bet");
	}

	@Then("^Web: Verify the bet details table before place bet, after bet placed and once after current bet is completed$")
	public void web_Verify_the_bet_details_table_before_place_bet_after_bet_placed_and_once_after_current_bet_is_completed() throws Throwable {
		//Language Change in the web Page
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath("//*[@class='flag-England active']"));
		actions.moveToElement(mainMenu);
		actions.click().build().perform();
		Thread.sleep(3000);
		WebElement subMenu = driver.findElement(By.xpath("//*[@class='flag-Portugal']"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(5000);
		WebElement logout = driver.findElement(By.xpath("(//span[@ng-click='frm.Logout()'])[2]")); 
		Assert.assertEquals("Sair", logout.getText());
		System.out.println("Verified with the bet table after completion of the bet");
		//		//*[@class='flag-England']
		//		??*[@class='flag-England active']
		//		*[@class='flag-Portugal']
	}

	@Then("^Web: Cross verification of betting slip with the number table bet selection$")
	public void web_Cross_verification_of_betting_slip_with_the_number_table_bet_selection() throws Throwable {
	    
	}

	@Then("^Web: Verify the language change reflects on the web page$")
	public void web_Verify_the_language_change_reflects_on_the_web_page() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}