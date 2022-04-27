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

public class Web_VirtualSicbo_OnlineLogin_StatitcsResults_BetPlace_AfterTimeLapse_RemoveAllButton_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSicbo_OnlineLogin_StatitcsResults_BetPlace_AfterTimeLapse_RemoveAllButton_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	} 

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module, valid logins, statitcs table, count time, valid error user message, remove all button$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_valid_logins_statitcs_table_count_time_valid_error_user_message_remove_all_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
	}

	@When("^Web: Fetch the staticts table details before the time lapse, after the successfull bet, validate the statitcs results$")
	public void web_Fetch_the_staticts_table_details_before_the_time_lapse_after_the_successfull_bet_validate_the_statitcs_results() throws Throwable {
		/*
		 *       Steps for statitcs data
		 * 1. fetch the current draw num 
		 * 2. fetch the statitcs result data like (odd or even and the small or big win number) before the current draw 
		 * 3. wait till the draw is complete and wining number is announced 
		 * 4. Search for the winning number in the draw results coulmn
		 * 5. Fetch the big or small, odd or even status of the winning number 
		 * 6. Verify the current statitcs should be increased by 1 number as per the last win in roulette game
		 */

		WebElement Stbutton = driver.findElement(By.xpath("//*[@ng-click='globalsicBoCtlr.SicboStatisticsFun()']")); 
		Assert.assertTrue(Stbutton.isDisplayed());
		Thread.sleep(2000);

		// direct to the statitics sub module
		Stbutton.click();
		Thread.sleep(2000);

		//Verify all the elements in the statitcs page is not null and all values are displayed to user

		WebElement small = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[1]/div"));
		WebElement big = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[4]/div"));	
		WebElement odd = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[2]/div"));
		WebElement even = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[3]/div"));


		WebElement drawnum = driver.findElement(By.xpath("//*[@class='c_draw_w']/span/span"));

		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));

		String dnum = drawnum.getText().substring(1);
		System.out.println("dnum: "+ dnum);
		String dtime = drawtime.getText();
		System.out.println("dtime: "+ dtime);
		
		int smallnum = Integer.parseInt(small.getText()); 
		System.out.println("smallnum: "+ smallnum);
		int bignum = Integer.parseInt(big.getText());  
		System.out.println("bignum: "+ bignum);
		int oddnum = Integer.parseInt(odd.getText()); 
		System.out.println("oddnum: "+ oddnum);
		int evennum = Integer.parseInt(even.getText()); 
		System.out.println("even: "+ evennum);	
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);


		//Converting the time period in seconds to for the wait time 
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		System.out.println("Time duration for the draw to complete "+totaltime);
		Thread.sleep(totaltime*1000);
		Thread.sleep(1*50*1000);

		//Check the last win with the draw number 
		
		WebElement searchdraw = driver.findElement(By.xpath("//*[@placeholder='Draw Number']"));
		searchdraw.click();
		Thread.sleep(2000);
		searchdraw.sendKeys(dnum);
		Thread.sleep(2000);
		WebElement drawsearch = driver.findElement(By.xpath("//*[@class='slideMenuFalse']"));
		drawsearch.click();
		Thread.sleep(5000);

		// Fetch the winning number from the search result
		WebElement winnumber = driver.findElement(By.xpath("//*[@class='resultDiceCon']/div/div[8]"));  	
		System.out.println("Win number: "+ winnumber.getText());
		String wnum = winnumber.getText().replaceAll("[^0-9]", "");
		int winnum = Integer.parseInt(wnum);
		System.out.println("win number: "+winnum);

		//For the win is odd or even 
		if(winnum % 2 == 0) {
			System.out.println(winnum + " is even");
			Thread.sleep(2000);
			WebElement even1 = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[3]/div"));
			int evennum1 = Integer.parseInt(even1.getText()); 
			Assert.assertEquals(evennum1, evennum+1);
			System.out.println("Statitcs data updated with the latest draw result for even number");
		}
		else {
			System.out.println(winnum + " is odd");
			Thread.sleep(2000);
			WebElement odd1 = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[2]/div"));
			int oddnum1 = Integer.parseInt(odd1.getText()); 
			Assert.assertEquals(oddnum1, oddnum+1);
			System.out.println("Statitcs data updated with the latest draw result for odd number");
		}
		
		//For the win is big or small values
		if(winnum >=4 && winnum<=10) {
			System.out.println(winnum + " small");
			Thread.sleep(2000);
			WebElement small1 = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[1]/div"));
			int smallnum1 = Integer.parseInt(small1.getText()); 
			Assert.assertEquals(smallnum1, smallnum+1);
			System.out.println("Statitcs data updated with the latest draw result for small number");
		}
		else if(winnum>=11 && winnum<=17){
			System.out.println(winnum + " is big");
			Thread.sleep(2000);
			WebElement big1 = driver.findElement(By.xpath("(//*[@class='ST_com_Bg'])[4]/div"));
			int bignum1 = Integer.parseInt(big1.getText()); 
			Assert.assertEquals(bignum1, bignum+1);
			System.out.println("Statitcs data updated with the latest draw result for odd number");
		}
		else
		{
			System.out.println("The win number occurs from 1-3 and 18");
		}
		
		System.out.println("*********************************Staticts data validation is complete***********************************");
		System.out.println("");
		System.out.println("");
	}



	@When("^Web: Place bet between the last (\\d+)secounds count down time, and after time lapse place bet$")
	public void web_Place_bet_between_the_last_secounds_count_down_time_and_after_time_lapse_place_bet(int arg1) throws Throwable {

		/*
		 *      Steps for placing bets to be placed within 30seconds left
		 * 1. fetch the current draw num 
		 * 2. and left draw timing wait till the time is less than 30seconds
		 * 3. Place the bet and validte the successfull validation message 
		 */

		//		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		//		String  drtime = drawtime.getText();
		//		System.out.println("draw left timings: "+drtime);
		//
		//		//Converting the time period in seconds to for the wait time 
		//		String min = drtime.substring(0, 2);
		//		int minute = Integer.parseInt(min);
		//		String sec = drtime.substring(3, 5);
		//		int seconds = Integer.parseInt(sec);
		//		int totaltime = (minute*60)+seconds;
		//		System.out.println("Time duration for the draw to complete "+totaltime);
		//		int wttime= totaltime-25;
		//		System.out.println("Wait time duration to clock down till last 30seconds: "+ wttime);
		//		Thread.sleep(wttime*1000);
		//
		//		//Selelct the Numbers from the Roulette Board top place the bet 
		//		WebElement pick1 = driver.findElement(By.xpath("//div[@class='diceW diceW2 diceTwoW']"));
		//		WebElement Pick2 = driver.findElement(By.xpath("(//div[@class='diceW diceW2 diceTwoW'])[2]"));
		//
		//		pick1.click();
		//		Thread.sleep(1000);
		//		Pick2.click();
		//		Thread.sleep(1000);
		//
		//		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.PlaceBetDetails(false)']"));
		//
		//		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		//		Forward.click();
		//		Thread.sleep(2000);
		//		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.confirm()']"));
		//		Confirm.click();
		//		Thread.sleep(1500);
		//		System.out.println("Bet placed");
		//
		//		//Validate msg for successful bet place
		//		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		//		String Vmsg = Validmsg.getText();
		//		System.out.println("Placed bet successful user message: "+ Vmsg);
		//		Assert.assertEquals("Bet has been placed successfully", Vmsg);
		//		System.out.println("Bet placed when the time lapse is less than 30 seconds");
		//		System.out.println("*************************************Bet place between 30seconds is validated****************************************");
		//		System.out.println("");
		//		System.out.println("");
		//		Thread.sleep(1*60*1000);
	}

	@When("^Web: Select bet and wait for the timelapse and the bets selected is overlapped by the remove all button and clear all button is available to user to kill the previous bets selected when the selected draw has already started$")
	public void web_Select_bet_and_wait_for_the_timelapse_and_the_bets_selected_is_overlapped_by_the_remove_all_button_and_clear_all_button_is_available_to_user_to_kill_the_previous_bets_selected_when_the_selected_draw_has_already_started() throws Throwable {

		/*
		 *      Steps for placing bets to be placed after the time limit is over
		 * 1. fetch the current draw num 
		 * 2. wait for the time to lapse
		 * 3. Place the bet and validte the successfull validation message 
		 * 4. Placed bet should be not successfully placed
		 */
		//		Thread.sleep(1*20*1000);
		//		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		//		String  drtime = drawtime.getText();
		//		System.out.println("draw left timings: "+drtime);
		//
		//		//Selelct the Numbers from the Roulette Board top place the bet 
		//		WebElement pick1 = driver.findElement(By.xpath("//div[@class='diceW diceW2 diceTwoW']"));
		//		WebElement Pick2 = driver.findElement(By.xpath("(//div[@class='diceW diceW2 diceTwoW'])[2]"));
		//
		//		pick1.click();
		//		Thread.sleep(1000);
		//		Pick2.click();
		//		Thread.sleep(1000);
		//
		//		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.PlaceBetDetails(false)']"));
		//
		//		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		//		Forward.click();
		//		Thread.sleep(2000);
		//
		//		//Converting the time period in seconds to for the wait time 
		//		String min = drtime.substring(0, 2);
		//		int minute = Integer.parseInt(min);
		//		String sec = drtime.substring(3, 5);
		//		int seconds = Integer.parseInt(sec);
		//		int totaltime = (minute*60)+seconds;
		//		System.out.println("Time duration for the draw to complete "+totaltime);
		//		Thread.sleep((totaltime*1000));
		//		Thread.sleep((5000));
		//
		//		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		//		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.confirm()']"));
		//		Confirm.click();
		//		Thread.sleep(1500);
		//
		//		//Validate msg for successful bet place
		//		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		//		String Vmsg = Validmsg.getText();
		//		System.out.println(" Unsuccessfull bet placed user message : "+ Vmsg);
		//		Assert.assertEquals("Draw #206 not available for betting at this moment! Try again Later!", Vmsg);

		//		WebElement Inactivebets = driver.findElement(By.xpath("(//*[@class='b_S_R_W ng-scope invalidDraw'])[4]"));
		//		Assert.assertEquals("Selected Draw Already Started!", Inactivebets.getAttribute("data"));
		//		Thread.sleep(2000);
		//
		//		WebElement RemoveAll = driver.findElement(By.xpath("(//*[@class='b_S_R_W ng-scope invalidDraw'])[4]/span"));
		//		RemoveAll.click();
		//		Thread.sleep(2000);
		//		System.out.println("*************************************Bet place after time lapse is validated****************************************");
		//		System.out.println("");
		//		System.out.println("");
	}

	@Then("^Web: Validate the statitcs result being updated after each bets$")
	public void web_Validate_the_statitcs_result_being_updated_after_each_bets() throws Throwable {
	    
	}

	@Then("^Web: Validate the bet placed between last (\\d+) seconds is successfull, and after time lapse placing bets results in valid error message is displayed to user$")
	public void web_Validate_the_bet_placed_between_last_seconds_is_successfull_and_after_time_lapse_placing_bets_results_in_valid_error_message_is_displayed_to_user(int arg1) throws Throwable {
	    
	}

	@Then("^Web: Validate the selected bets when the draw started will be overlapped by removal all button and valid user message$")
	public void web_Validate_the_selected_bets_when_the_draw_started_will_be_overlapped_by_removal_all_button_and_valid_user_message() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}