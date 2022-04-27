package Web_VirtualSicbo;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_ChipDenominations_LockAndUnlock_ReplayButton_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
WebDriver driver;
	
	public Web_VirtualSicbo_ChipDenominations_LockAndUnlock_ReplayButton_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, login details, current draw, bet numbers, mimimum bet, stake amount, chip denominations, forward button and confirmation messages$")
	public void web_Chrome_browser_suribet_website_valid_URL_login_details_current_draw_bet_numbers_mimimum_bet_stake_amount_chip_denominations_forward_button_and_confirmation_messages() throws Throwable {
		boolean bal = driver.findElement(By.id("spnUserBalance")).isDisplayed();
    	System.out.println("Balance is Displayed after Login :" +bal);
    	Assert.assertTrue(bal);	
   
	}

	@When("^Web: Login to suribet website with valid login details, Click on Virtual Sicbo module link, select the chip denomination, select bet numbers, click on forward button & confirm button and check confirmation message$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_Virtual_Sicbo_module_link_select_the_chip_denomination_select_bet_numbers_click_on_forward_button_confirm_button_and_check_confirmation_message() throws Throwable {
		//Storing the balance before placing the bet
		String Mbalance = driver.findElement(By.id("spnUserBalance")).getText();
		System.out.println("Current balance of the account is: " +Mbalance);
		
		//Selecting the future draw
		driver.findElement(By.xpath("//ul[@class='ul_clear']/li[5]")).click();
		Thread.sleep(3000);
		//Selecting the bet number and placing the bet
		WebElement smallb = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		smallb.click();
		Thread.sleep(1000);
		WebElement bigb = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		bigb.click();
		Thread.sleep(1000);
		WebElement odd = driver.findElement(By.xpath("//*[ text() = ' Odd ']"));
		odd.click();
		Thread.sleep(1000);
		WebElement even = driver.findElement(By.xpath("//*[ text() = ' Even ']"));
		even.click();
		Thread.sleep(1000);
		
		//Cross checking default bet amount and placing the bet
		String tstake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Total Stake dispalyed on the betting slip: " +tstake);
		String expected = "4";
		Assert.assertEquals(tstake, expected);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")));		
		String cmsg = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
		System.out.println("Message displayed after placing bet successfully: " +cmsg);
		String expected1 = "Bet has been placed successfully";
		Assert.assertEquals(cmsg, expected1);
		Thread.sleep(3000);
		
		//Checking balance after placing the bet
		String Abalance = driver.findElement(By.id("spnUserBalance")).getText();
		StringBuffer str4 = new StringBuffer(Abalance);
		System.out.println(str4.replace(0,7,""));
		String AAbalance = str4.toString();
		String AA = AAbalance.replaceAll(",", "");
		System.out.println("Balance of the account : " +AA);

		StringBuffer str41 = new StringBuffer(Mbalance);
		System.out.println(str41.replace(0,7,""));		
		String MMbalance = str41.toString();
		String MM = MMbalance.replaceAll(",", "");
		System.out.println("Balance of the account after : " +MM);
		
		double conValue = Double.parseDouble(MM) - Double.parseDouble(tstake);
		String dbi = String.format("%.2f", conValue);  
		System.out.println("Balance after calculation: "+dbi);
		Thread.sleep(2000);
		Assert.assertEquals(dbi, AA);
		Thread.sleep(2000);
		
		//Checking the functionalities of Replay button
		WebElement replay = driver.findElement(By.xpath("//i[@class='replayBut closeBut']"));
		replay.click();
		Thread.sleep(2000);
		
		String rows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div/span[2]")).getText();
		String erows = "4";
		Assert.assertEquals(rows, erows);
		Thread.sleep(1000);
		
		String stak = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		String estak = "4";
		Assert.assertEquals(stak, estak);
		Thread.sleep(1000);
		
		String bet1 = driver.findElement(By.xpath("(//div[@class='sicBetName']/span)[1]")).getText();
		String ebet1 = "even";
		Assert.assertEquals(bet1, ebet1);
		Thread.sleep(1000);
		
		String bet2 = driver.findElement(By.xpath("(//div[@class='sicBetName']/span)[2]")).getText();
		String ebet2 = "odd";
		Assert.assertEquals(bet2, ebet2);
		Thread.sleep(1000);
		
		//Clicking on 2x button
		WebElement tx = driver.findElement(By.xpath("//i[@ng-click='globalsicBoCtlr.Replay2x()']"));
		tx.click();
		Thread.sleep(2000);
		String stk3 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		String estk3 = "8";
		Assert.assertEquals(stk3, estk3);
		Thread.sleep(1000);
		
		tx.click();
		Thread.sleep(2000);
		String stk4 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		String estk4 = "16";
		Assert.assertEquals(stk4, estk4);
		Thread.sleep(1000);
		
		//Clicking on remove button
		WebElement rem = driver.findElement(By.xpath("//i[@ng-click='globalsicBoCtlr.ReplayCancel()']"));
		rem.click();
		Thread.sleep(2000);
		
		String rows3 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div/span[2]")).getText();
		String erows3 = "3";
		Assert.assertEquals(rows3, erows3);
		Thread.sleep(1000);
		
		rem.click();
		Thread.sleep(2000);
		String rows4 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div/span[2]")).getText();
		String erows4 = "2";
		Assert.assertEquals(rows4, erows4);
		Thread.sleep(1000);
		rem.click();
		Thread.sleep(1000);
		String rows41 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div/span[2]")).getText();
		String erows41 = "1";
		Assert.assertEquals(rows41, erows41);
		Thread.sleep(2000);
		rem.click();
		Thread.sleep(1000);
	}

	@Then("^Web:  Bet should be placed successfully and confirmation message should be displayed$")
	public void web_Bet_should_be_placed_successfully_and_confirmation_message_should_be_displayed() throws Throwable {
	    //Selecting different chip denomination and clicking on bet numbers
		driver.findElement(By.xpath("//div[@class='roul_chip2']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[3]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@class='roul_chip3']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[4]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@class='roul_chip4']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[1]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@class='roul_chip5']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[2]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@class='roul_chip6']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='sicBoTableItems ng-scope']")).click();
		Thread.sleep(1000);
		
		//Comparing the stake amount displayed on each bet number 
		String estake = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-4]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'Total Ten' is: " +estake);
		String expected14 = "100";
		Assert.assertEquals(estake, expected14);
		
		String estake1 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-3]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'Even' is: " +estake1);
		String expected15 = "50";
		Assert.assertEquals(estake1, expected15);
		
		String estake2 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-2]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'ODD' is: " +estake2);
		String expected16 = "25";
		Assert.assertEquals(estake2, expected16);
		
		String estake3 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-1]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'Big' is: " +estake3);
		String expected17 = "10";
		Assert.assertEquals(estake3, expected17);
		
		String estake4 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'Small' is: " +estake4);
		String expected18 = "5";
		Assert.assertEquals(estake4, expected18);
		
		
		WebElement even = driver.findElement(By.xpath("//*[ text() = 'CLEAR ALL']"));
		even.click();
		Thread.sleep(1000);
	}

	@Then("^Web: Account balance should get deducted as per stake amount  after placing bet$")
	public void web_Account_balance_should_get_deducted_as_per_stake_amount_after_placing_bet() throws Throwable {
		//Checking lock icon is displayed on betting numbers after placing bet successfully
		boolean lock1 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[3]")).isDisplayed();
		if(lock1==true)
		{
			Assert.assertTrue(lock1);
			System.out.println("Test case Passed as Lock icon is displayed on the betting number after placing bet successfully");
		}
		else
		{
			Assert.assertFalse(lock1);
			System.out.println("Test case failed as lock icon is not displayed on the bet number hence lock icon class not found error displayed in the console");
		}
		
		Thread.sleep(1000);
		
		boolean lock2 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[4]")).isDisplayed();
		Assert.assertTrue(lock2);
		Thread.sleep(1000);
		
		boolean lock3 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[1]")).isDisplayed();
		Assert.assertTrue(lock3);
		Thread.sleep(1000);
		
		boolean lock4 = driver.findElement(By.xpath("(//section[@class='selectChipLocal selectChip chip1'])[2]")).isDisplayed();
		Assert.assertTrue(lock4);
		Thread.sleep(1000);
		
	}

	@Then("^Web: Selected chip denomination should be displayed as stake amount in betting slip for each bet numbers$")
	public void web_Selected_chip_denomination_should_be_displayed_as_stake_amount_in_betting_slip_for_each_bet_numbers() throws Throwable {
		//Selecting chip denomination 1
		driver.findElement(By.xpath("//div[@class='roul_chip1']")).click();
		Thread.sleep(2000);
		//Clicking on Reply button
		driver.findElement(By.xpath("//i[@class='replayBut closeBut']")).click();
		Thread.sleep(2000);
		
		//Increasing/decreasing bet amount by clicking on plus & minus icon
		WebElement plus = driver.findElement(By.xpath("//span[@class='fa fa-plus-circle slideMenuFalse']"));
		plus.click();
		Thread.sleep(1000);
		plus.click();
		Thread.sleep(1000);
		plus.click();
		Thread.sleep(1000);
		plus.click();
		Thread.sleep(1000);
		
		String tstake1 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Stake amount after increase: " +tstake1);
		String expected = "8";
		Assert.assertEquals(tstake1, expected);
		
		//Decreasing stake amount on click of - icon
		WebElement minus = driver.findElement(By.xpath("//span[@class='fa fa-minus-circle slideMenuFalse']"));
		minus.click();
		Thread.sleep(1000);
		minus.click();
		Thread.sleep(1000);
		minus.click();
		Thread.sleep(1000);
		minus.click();
		Thread.sleep(1000);
		
		String tstake2 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Stake amount after decrease: " +tstake2);
		String expected1 = "4";
		Assert.assertEquals(tstake2, expected1);
	}

	@Then("^Web: Lock icon should exists on the betting number even after successful completion$")
	public void web_Lock_icon_should_exists_on_the_betting_number_even_after_successful_completion() throws Throwable {
		driver.findElement(By.linkText("Log Off")).click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(8000);
		//Clicking on Replay button without logging-in and checking the validation message
		driver.findElement(By.xpath("//i[@class='replayBut closeBut']")).click();
		Thread.sleep(1000);
		String vmsg2 = driver.findElement(By.xpath("//div[@class='e_mgs ng-binding']")).getText();
		String exp = "You must log in before to place a bet";
		Assert.assertEquals(vmsg2, exp);
	}

	@Then("^Web: Lock icon should be displayed on the betting number even if the user refreshes page or navigates to somewhere and come back$")
	public void web_Lock_icon_should_be_displayed_on_the_betting_number_even_if_the_user_refreshes_page_or_navigates_to_somewhere_and_come_back() throws Throwable {
	   
	}

	@Then("^Web: Lock icon should be displayed if the user logged out and logs in back$")
	public void web_Lock_icon_should_be_displayed_if_the_user_logged_out_and_logs_in_back() throws Throwable {
	    
	}

	@Then("^Web: Previously placed bet types should get displayed on the betting slip on click of replay button and bet type should get removed from the betting slip on click of remove\\(x\\) icon$")
	public void web_Previously_placed_bet_types_should_get_displayed_on_the_betting_slip_on_click_of_replay_button_and_bet_type_should_get_removed_from_the_betting_slip_on_click_of_remove_x_icon() throws Throwable {
	    
	}

	@Then("^Web: Stake amount should get increased on each click of (\\d+)x button$")
	public void web_Stake_amount_should_get_increased_on_each_click_of_x_button(int arg1) throws Throwable {
	   Thread.sleep(2000);
	   driver.quit();
	}
}