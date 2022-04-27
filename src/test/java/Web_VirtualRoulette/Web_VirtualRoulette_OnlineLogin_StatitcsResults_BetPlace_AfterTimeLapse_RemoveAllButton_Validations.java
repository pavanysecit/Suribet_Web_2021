package Web_VirtualRoulette;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualRoulette_OnlineLogin_StatitcsResults_BetPlace_AfterTimeLapse_RemoveAllButton_Validations extends Web_VirtualRoulette_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualRoulette_OnlineLogin_StatitcsResults_BetPlace_AfterTimeLapse_RemoveAllButton_Validations() throws Exception {
		driver =Web_VirtualRoulette_URL_OnlineLogin();
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
		 * 2. fetch the statitcs result data like (odd or even and the color of winning number) before the current draw 
		 * 3. wait till the draw is complete and wining number is announced 
		 * 4. Search for the winning number in the draw results coulmn
		 * 5. Fetch the color, odd or even status of the winning number 
		 * 6. Verify the current statitcs should be increased by 1 number as per the last win in roulette game
		 */

		//Draw num

		WebElement drawnum = driver.findElement(By.xpath("//*[@class='c_draw_w']/span/span"));
		//timing
		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		//odd
		WebElement odd = driver.findElement(By.xpath("//*[@class='ul_clear itemC row2 odd_even']/li/span"));
		//even 
		WebElement even = driver.findElement(By.xpath("//*[@class='ul_clear itemC row2 odd_even']/li[2]/span"));
		//black
		WebElement black = driver.findElement(By.xpath("//*[@class='ul_clear itemC row3']/li[1]/span"));
		//green
		WebElement green = driver.findElement(By.xpath("//*[@class='ul_clear itemC row3']/li[2]/span"));	
		//red
		WebElement red = driver.findElement(By.xpath("//*[@class='ul_clear itemC row3']/li[3]/span"));

		String dnum = drawnum.getText().substring(1);
		System.out.println("dnum: "+ dnum);
		String dtime = drawtime.getText();
		System.out.println("dtime: "+ dtime);
		int oddnum = Integer.parseInt(odd.getText()); 
		System.out.println("oddnum: "+ oddnum);
		int evennum = Integer.parseInt(even.getText());  
		System.out.println("evennum: "+ evennum);
		int rednum = Integer.parseInt(red.getText()); 
		System.out.println("rednum: "+ rednum);
		int blacknum = Integer.parseInt(black.getText()); 
		System.out.println("blacknum: "+ blacknum);
		int greennum = Integer.parseInt(green.getText());  
		System.out.println("greennum: "+ greennum);
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
		WebElement searchdraw = driver.findElement(By.xpath("//*[@placeholder='Draw #']"));
		searchdraw.click();
		Thread.sleep(2000);
		searchdraw.sendKeys(dnum);
		Thread.sleep(2000);
		WebElement drawsearch = driver.findElement(By.xpath("//*[@ng-click='globalrouletteCtlr.GetFilterResults()']"));
		drawsearch.click();
		Thread.sleep(5000);

		// Fetch the winning number from the search result
		WebElement winnumber = driver.findElement(By.xpath("//li[@ng-repeat ='res in globalrouletteCtlr.VrResults track by $index']/div/span[2]"));  	
		System.out.println("Win number: "+ winnumber.getText());
		//String winnum =  winnumber.getText().substring(3);
		int winnum = Integer.parseInt(winnumber.getText().substring(4));
		System.out.println("win number: "+winnum);

		//Verify the color win 
		WebElement color = driver.findElement(By.xpath("//li[@ng-repeat ='res in globalrouletteCtlr.VrResults track by $index']/div/span[2]")); 
		String drawcolor = color.getAttribute("style");
		if(drawcolor.equalsIgnoreCase("background-color: rgb(218, 0, 0);"))
		{
			Boolean numred =drawcolor.equalsIgnoreCase("background-color: rgb(218, 0, 0);");
			System.out.println(" win color is red: "+numred );
			WebElement red1 = driver.findElement(By.xpath("//*[@class='ul_clear itemC row3']/li[3]/span"));
			int rednum1 = Integer.parseInt(red1.getText()); 
			Assert.assertEquals(rednum1, rednum+1);
			System.out.println("Statitcs data is been updated for red color win");
		}
		else if(drawcolor.equalsIgnoreCase("background-color: rgb(0, 0, 0);"))
		{
			Boolean numblack =drawcolor.equalsIgnoreCase("background-color: rgb(0, 0, 0);");
			System.out.println(" win color is black: "+numblack);
			WebElement black1 = driver.findElement(By.xpath("//*[@class='ul_clear itemC row3']/li[1]/span"));
			int blacknum1 = Integer.parseInt(black1.getText()); 
			Assert.assertEquals(blacknum1, blacknum+1);
			System.out.println("Statitcs data is been updated for black color win");
		}
		else if(drawcolor.equalsIgnoreCase("background-color: rgb(0, 153, 72);"))
		{
			Boolean numgreen =drawcolor.equalsIgnoreCase("background-color: rgb(0, 0, 0);");
			System.out.println(" win color is green: "+numgreen);
			WebElement green1 = driver.findElement(By.xpath("//*[@class='ul_clear itemC row3']/li[2]/span"));
			int greennum1 = Integer.parseInt(green1.getText()); 
			Assert.assertEquals(greennum1, greennum+1);
			System.out.println("Statitcs data is been updated for black color win");
		}

		//Verify the whether the win number is odd or even 
		if(winnum % 2 == 0) {
			System.out.println(winnum + " is even");
			Thread.sleep(2000);
			WebElement even1 = driver.findElement(By.xpath("//*[@class='ul_clear itemC row2 odd_even']/li[2]/span"));
			int evennum1 = Integer.parseInt(even1.getText()); 
			Assert.assertEquals(evennum1, evennum+1);
			System.out.println("Statitcs data updated with the latest draw result for even number");
		}
		else {
			System.out.println(winnum + " is odd");
			Thread.sleep(2000);
			WebElement odd1 = driver.findElement(By.xpath("//*[@class='ul_clear itemC row2 odd_even']/li/span"));
			int oddnum1 = Integer.parseInt(odd1.getText()); 
			Assert.assertEquals(oddnum1, oddnum+1);
			System.out.println("Statitcs data updated with the latest draw result for odd number");
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

		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);

		//Converting the time period in seconds to for the wait time 
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		System.out.println("Time duration for the draw to complete "+totaltime);
		int wttime= totaltime-25;
		System.out.println("Wait time duration to clock down till last 30seconds: "+ wttime);
		Thread.sleep(wttime*1000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick2 = driver.findElement(By.xpath("//span[@bettypepickid='3']"));
		WebElement Pick28 = driver.findElement(By.xpath("//span[@bettypepickid='29']"));
		WebElement Pick13 = driver.findElement(By.xpath("//span[@bettypepickid='14']"));

		Pick2.click();
		Thread.sleep(1000);
		Pick28.click();
		Thread.sleep(1000);
		Pick13.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Roulette table");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Slips Generated Successfully", Vmsg);
		System.out.println("Bet placed when the time lapse is less than 30 seconds");
		System.out.println("*************************************Bet place between 30seconds is validated****************************************");
		System.out.println("");
		System.out.println("");
		Thread.sleep(1*60*1000);
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
		Thread.sleep(1*20*1000);
		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);

		//Select the Numbers from the Roulette Board top place the bet 
		WebElement Pick2 = driver.findElement(By.xpath("//span[@bettypepickid='3']"));
		WebElement Pick28 = driver.findElement(By.xpath("//span[@bettypepickid='29']"));
		WebElement Pick13 = driver.findElement(By.xpath("//span[@bettypepickid='14']"));

		Pick2.click();
		Thread.sleep(1000);
		Pick28.click();
		Thread.sleep(1000);
		Pick13.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Roulette table");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//Converting the time period in seconds to for the wait time 
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		System.out.println("Time duration for the draw to complete "+totaltime);
		Thread.sleep((totaltime*1000));
		Thread.sleep((5000));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.cssSelector(".e_mgs.ng-binding")); 
		String Vmsg = Validmsg.getText();
		System.out.println(" Unsuccessfull bet placed user message : "+ Vmsg);
		Assert.assertEquals("No Bets from valid Draws. Clear selections", Vmsg);

		WebElement Inactivebets = driver.findElement(By.xpath("(//*[@class='b_S_R_W ng-scope invalidDraw'])[4]"));
		Assert.assertEquals("Selected Draw Already Started!", Inactivebets.getAttribute("data"));
		Thread.sleep(2000);

		WebElement RemoveAll = driver.findElement(By.xpath("(//*[@class='b_S_R_W ng-scope invalidDraw'])[4]/span"));
		RemoveAll.click();
		Thread.sleep(2000);
		System.out.println("*************************************Bet place after time lapse is validated****************************************");
		System.out.println("");
		System.out.println("");
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