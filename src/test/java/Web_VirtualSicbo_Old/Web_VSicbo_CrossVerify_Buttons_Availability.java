package Web_VirtualSicbo_Old;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.NgWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VSicbo_CrossVerify_Buttons_Availability extends Web_VSicbo_URL_Login{
private static NgWebDriver ngDriver;
private static WebDriver driver;
		
	public  Web_VSicbo_CrossVerify_Buttons_Availability() throws InterruptedException, MalformedURLException {
			driver = Web_VSicbo_URL_Login();
	}
	
	@Given("^Mobile: Chrome browser, suribet website valid URL, login details, current draw, bet numbers, Forward, Confirn, ClearAll, Stake Amount, No of rows, Bet types and confirmation message$")
	public void mobile_Chrome_browser_suribet_website_valid_URL_login_details_current_draw_bet_numbers_Forward_Confirn_ClearAll_Stake_Amount_No_of_rows_Bet_types_and_confirmation_message() throws Throwable {
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(2000);
	}

	@When("^Mobile: Login to suribet website with valid login details, Click on Virtual Sicbo module link, select the bet numbers, check stake amount, check no of rows count, click on clearall button then select bet numbers again, click on forward button & confirm button and check confirmation message$")
	public void mobile_Login_to_suribet_website_with_valid_login_details_Click_on_Virtual_Sicbo_module_link_select_the_bet_numbers_check_stake_amount_check_no_of_rows_count_click_on_clearall_button_then_select_bet_numbers_again_click_on_forward_button_confirm_button_and_check_confirmation_message() throws Throwable {
		//Selecting the future draw
		driver.findElement(By.xpath("//ul[@class='ul_clear']/li[5]")).click();
		Thread.sleep(3000);
		//Selecting the bet number 
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
		//Selecting each double betting type
		WebElement double1 = driver.findElement(By.xpath("//div[@class='sicBoTableItems sicBoTableItem2']/div[2]"));
		double1.click();
		Thread.sleep(1000);
		WebElement double2 = driver.findElement(By.xpath("//div[@class='sicBoTableItems sicBoTableItem2']/div[3]"));
		double2.click();
		Thread.sleep(1000);
		WebElement double3 = driver.findElement(By.xpath("//div[@class='sicBoTableItems sicBoTableItem2']/div[4]"));
		double3.click();
		Thread.sleep(1000);
		//Selecting each triple betting type
		WebElement double11 = driver.findElement(By.xpath("//div[@class='sicBoTableItems sicBoTableItem3']/div[2]"));
		double11.click();
		Thread.sleep(1000);
		WebElement double22 = driver.findElement(By.xpath("//div[@class='sicBoTableItems sicBoTableItem3']/div[3]"));
		double22.click();
		Thread.sleep(1000);
		WebElement double33 = driver.findElement(By.xpath("//div[@class='sicBoTableItems sicBoTableItem3']/div[4]"));
		double33.click();
		Thread.sleep(1000);
	}

	@Then("^Mobile:  Bet should be placed successfully and Print slip button should be displayed$")
	public void mobile_Bet_should_be_placed_successfully_and_Print_slip_button_should_be_displayed() throws Throwable {
	   //Verify stake amount and no rows count across all the bet types
		String estake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Total Stake amount displayed on the betting slip: " +estake);
		String expected = "10";
		Assert.assertEquals(estake, expected);
		
		String norows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[1]/span[2]")).getText();
		System.out.println("Total No of Rows count displayed on the betting slip: " +norows);
		String expected1 = "10";
		Assert.assertEquals(norows, expected1);
		
		String norows1 = driver.findElement(By.xpath("//div[@class='bet_slip_Sport_w sicBoCart_W ng-scope']/span[1]")).getText();
		System.out.println("No of Rows count on the top of the betting slip displayed: " +norows1);
		String expected2 = "10";
		Assert.assertEquals(norows1, expected2);
	}

	@Then("^Mobile: Selected bet types should be displayed in betting slip$")
	public void mobile_Selected_bet_types_should_be_displayed_in_betting_slip() throws Throwable {
	    //Checking selected bey types displayed on the betting slip
		String bet1 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 10th/top row of betting slip: "+bet1);
		String expected1 = "triple 3";
		Assert.assertEquals(bet1, expected1);
		Thread.sleep(1000);

		String bet2 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[2]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 9th row of betting slip: "+bet2);
		String expected2 = "triple 2";
		Assert.assertEquals(bet2, expected2);
		Thread.sleep(1000);
		
		String bet3 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[3]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 8th row of betting slip: "+bet3);
		String expected3 = "triple 1";
		Assert.assertEquals(bet3, expected3);
		Thread.sleep(1000);
		
		String bet4 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[4]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 7th row of betting slip: "+bet4);
		String expected4 = "double 3";
		Assert.assertEquals(bet4, expected4);
		Thread.sleep(1000);
		
		String bet5 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[5]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 6th row of betting slip: "+bet5);
		String expected5 = "double 2";
		Assert.assertEquals(bet5, expected5);
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[6]/div[1]/span[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);		
		String bet6 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[6]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 5th row of betting slip: "+bet6);
		String expected6 = "double 1";
		Assert.assertEquals(bet6, expected6);
		Thread.sleep(1000);
		
//		WebElement element1 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[7]/div[1]/span[1]"));
//		js.executeScript("arguments[0].scrollIntoView(true);", element1);		
		String bet7 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[7]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 4th row of betting slip: "+bet7);
		String expected7 = "even";
		Assert.assertEquals(bet7, expected7);
		Thread.sleep(1000);
		
//		WebElement element2 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[8]/div[1]/span[1]"));
//		js.executeScript("arguments[0].scrollIntoView(true);", element2);		
		String bet8 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[8]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 3rd row of betting slip: "+bet8);
		String expected8 = "odd";
		Assert.assertEquals(bet8, expected8);
		Thread.sleep(1000);
		
//		WebElement element3 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[9]/div[1]/span[1]"));
//		js.executeScript("arguments[0].scrollIntoView(true);", element3);		
		String bet9 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[9]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 2nd row of betting slip: "+bet9);
		String expected9 = "big";
		Assert.assertEquals(bet9, expected9);
		Thread.sleep(1000);
		
//		WebElement element4 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[10]/div[1]/span[1]"));
//		js.executeScript("arguments[0].scrollIntoView(true);", element4);	
		String bet10 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[10]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 1st row of betting slip: "+bet10);
		String expected10 = "small";
		Assert.assertEquals(bet10, expected10);
		Thread.sleep(1000);
	}

	@Then("^Mobile: Stake amount in betting slip for each bet numbers should be displayed$")
	public void mobile_Stake_amount_in_betting_slip_for_each_bet_numbers_should_be_displayed() throws Throwable {
	    //Comparing the expected winning amount on each bet number
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/div[1]/div[3]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);	
		String ewin = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Triple 3' is: " +ewin);
		String expected10 = "Win : 200";
		Assert.assertEquals(ewin, expected10);
		

		String ewin1 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[2]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Triple 2' is: " +ewin1);
		String expected11 = "Win : 200";
		Assert.assertEquals(ewin1, expected11);
		

		String ewin2 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[3]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Triple 1' is: " +ewin2);
		String expected12 = "Win : 200";
		Assert.assertEquals(ewin2, expected12);
		

		String ewin3 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[4]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Double 3' is: " +ewin3);
		String expected13 = "Win : 12";
		Assert.assertEquals(ewin3, expected13);
		

		String ewin4 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[5]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Double 2' is: " +ewin4);
		String expected14 = "Win : 12";
		Assert.assertEquals(ewin4, expected14);
		
		WebElement element1 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[10]/div[1]/div[3]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element1);	
		String ewin5 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[6]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Double 1' is: " +ewin5);
		String expected15 = "Win : 12";
		Assert.assertEquals(ewin5, expected15);
		
		String ewin6 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[7]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Even' is: " +ewin6);
		String expected16 = "Win : 2";
		Assert.assertEquals(ewin6, expected16);
		
		String ewin7 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[8]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Big' is: " +ewin7);
		String expected17 = "Win : 2";
		Assert.assertEquals(ewin7, expected17);
		
		String ewin8 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[9]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Odd' is: " +ewin8);
		String expected18 = "Win : 2";
		Assert.assertEquals(ewin8, expected18);
		
		String ewin9 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[10]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'Small' is: " +ewin9);
		String expected19 = "Win : 2";
		Assert.assertEquals(ewin9, expected19);
	}

	@Then("^Mobile: On click of ClearAll button, selected bet types should get removed from betting slip and No of Rows value should be displayed as (\\d+)$")
	public void mobile_On_click_of_ClearAll_button_selected_bet_types_should_get_removed_from_betting_slip_and_No_of_Rows_value_should_be_displayed_as(int arg1) throws Throwable {
	   //Removing each number from betting slip, checking no of rows count & total stake value
	    //Comparing the expected winning amount on each bet number
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/span[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);	
		WebElement close = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/span[2]"));
		close.click();
		Thread.sleep(2000);
		
		String estake = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Total Stake amount after removing one bet type from betting slip: " +estake);
		String expected = "9";
		Assert.assertEquals(estake, expected);

		String norows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[1]/span[2]")).getText();
		System.out.println("Total No of Rows count after removing one bet type from betting slip: " +norows);
		String expected1 = "9";
		Assert.assertEquals(norows, expected1);
		
		
		driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/span[2]")).click();
		Thread.sleep(2000);
		
		String estake1 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Total Stake amount after removing 2nd bet type from betting slip: " +estake1);
		String expected2 = "8";
		Assert.assertEquals(estake1, expected2);

		String norows1 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[1]/span[2]")).getText();
		System.out.println("Total No of Rows count after removing 2nd bet type from betting slip: " +norows1);
		String expected3 = "8";
		Assert.assertEquals(norows1, expected3);
		
		driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/span[2]")).click();
		Thread.sleep(2000);
		
		String estake2 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).getText();
		System.out.println("Total Stake amount after removing rd bet type from betting slip: " +estake2);
		String expected4 = "7";
		Assert.assertEquals(estake2, expected4);

		String norows2 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[1]/span[2]")).getText();
		System.out.println("Total No of Rows count after removing 3rd bet type from betting slip: " +norows2);
		String expected5 = "7";
		Assert.assertEquals(norows2, expected5);
		
		//Clicking on clear all button and checking no of rows count & total stake value
		WebElement even = driver.findElement(By.xpath("//*[ text() = 'CLEAR ALL']"));
		even.click();
		Thread.sleep(2000);
		
		boolean estake3 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[3]/span[2]")).isDisplayed();
		Assert.assertFalse(estake3);
		System.out.println("Total Stake amount field should not be displayed: " +estake3);
		
		boolean norows3 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sicBoTotalstake']/div[1]/span[2]")).isDisplayed();
		Assert.assertFalse(norows3);
		System.out.println("Total No of Rows count field should not be displayed: " +norows3);
	}

	@Then("^Mobile: No of rows values should get decreased by (\\d+) whenever user clicks on X icon on each number in betting slip$")
	public void mobile_No_of_rows_values_should_get_decreased_by_whenever_user_clicks_on_X_icon_on_each_number_in_betting_slip(int arg1) throws Throwable {
		//Selecting the bet number and placing the bet
		WebElement smallb = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		smallb.click();
		Thread.sleep(1000);
		WebElement bigb = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		bigb.click();
		Thread.sleep(1000);
		
		//Check whether Forward button is displayed
		boolean frw = driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).isDisplayed();
		Assert.assertTrue(frw);
		System.out.println("Forward button is displayed after selecting bet types: " +frw);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(2000);
		
		//Check whether Confirm button is displayed
		boolean cfr = driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).isDisplayed();
		Assert.assertTrue(cfr);
		System.out.println("Confirm button is displayed after clicking on Forward button: " +cfr);
		driver.findElement(By.xpath("//div[@class='slip_but_w']/div[1]")).click();
		Thread.sleep(2000);
		
		//Check Print Slip button is visible after placing the bet
		boolean printsl = driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_left']")).isDisplayed();
		Assert.assertTrue(printsl);
		System.out.println("Print Slip button is visible after placing bet: " +printsl);
		driver.findElement(By.xpath("//button[@class='PrintBUT slideMenuFalse float_left']")).click();
		Thread.sleep(2000);
	}

	@Then("^Mobile:  Expected winning amount should be displayed on each betting number as per the bet type selected$")
	public void mobile_Expected_winning_amount_should_be_displayed_on_each_betting_number_as_per_the_bet_type_selected() throws Throwable {
	    
	}

	@Then("^Mobile: On selecting betting types, Forward button should be displayed\\. After clicking on forward button, Confirm button should be displayed\\. After confirm, Print Slip button should be displayed$")
	public void mobile_On_selecting_betting_types_Forward_button_should_be_displayed_After_clicking_on_forward_button_Confirm_button_should_be_displayed_After_confirm_Print_Slip_button_should_be_displayed() throws Throwable {
	   Thread.sleep(2000);
	   driver.quit();
	}
}
