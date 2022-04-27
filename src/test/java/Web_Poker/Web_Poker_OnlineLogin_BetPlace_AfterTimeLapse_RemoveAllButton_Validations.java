package Web_Poker;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Poker_OnlineLogin_BetPlace_AfterTimeLapse_RemoveAllButton_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_OnlineLogin_BetPlace_AfterTimeLapse_RemoveAllButton_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual poker module, valid logins, statitcs table, count time, valid error user message, remove all button$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_poker_module_valid_logins_statitcs_table_count_time_valid_error_user_message_remove_all_button() throws Throwable {
	   
	}

	@When("^Web: Place bet between the last (\\d+)secounds count down time, and after time lapse place bet$")
	public void web_Place_bet_between_the_last_secounds_count_down_time_and_after_time_lapse_place_bet(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);

		/*
		 *      Steps for placing bets to be placed within 30seconds left
		 * 1. fetch the current draw num 
		 * 2. and left draw timing wait till the time is less than 30seconds
		 * 3. Place the bet and validte the successfull validation message 
		 */

		WebElement drawtime = driver.findElement(By.xpath("//*[@class='text-center py-2 rounded']/div[3]"));
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

		//Selelct the Numbers from the win combination Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));	
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.className("EMessage")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		System.out.println("Bet placed when the time lapse is less than 30 seconds");
		System.out.println("*************************************Bet place between 30seconds is validated****************************************");
		System.out.println("");
		System.out.println("");
		Thread.sleep(1*60*1000);
	}

	@When("^Web: Select bet and wait for the timelapse and the bets selected is overlapped by the remove all button and clear all button is available to user to kill the previous bets selected when the selected draw has already started$")
	public void web_Select_bet_and_wait_for_the_timelapse_and_the_bets_selected_is_overlapped_by_the_remove_all_button_and_clear_all_button_is_available_to_user_to_kill_the_previous_bets_selected_when_the_selected_draw_has_already_started() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		/*
		 *      Steps for placing bets to be placed after the time limit is over
		 * 1. fetch the current draw num 
		 * 2. wait for the time to lapse
		 * 3. Place the bet and validte the successfull validation message 
		 * 4. Placed bet should be not successfully placed
		 */
		
//		wait till the visibility of the element //*[@class='poker-countdown rounded position-absolute overflow-hidden']
		
		Thread.sleep(1*20*1000);
		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);

		//Selelct the Numbers from the win combination Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));	
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

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
		WebElement Validmsg = driver.findElement(By.className("EMessage")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
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

	@Then("^Web: Validate the bet placed between last (\\d+) seconds is successfull, and after time lapse placing bets results in valid error message is displayed to user$")
	public void web_Validate_the_bet_placed_between_last_seconds_is_successfull_and_after_time_lapse_placing_bets_results_in_valid_error_message_is_displayed_to_user(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);

		/*
		 *      Steps for placing bets to be placed after the completing the current bet and new bet being started
		 * 1. fetch the current draw num 
		 * 2. wait for the time to lapse
		 * 3. Place the bet and validate the successfull validation message 
		 * 4. Placed bet should be not successfully placed
		 */ 
		
		
		Thread.sleep(1*40*1000);
		WebElement drawtime = driver.findElement(By.xpath("//*[@class='c_draw_w']/span[3]/span"));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);

		//Selelct the Numbers from the win combination Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));	
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		//Converting the time period in seconds to for the wait time 
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		System.out.println("Time duration for the draw to complete "+totaltime);
		Thread.sleep((totaltime*1000));
		//Again time duration to wait for the next draw to start
		Thread.sleep((2*40*1000));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.className("EMessage")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg));
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

	@Then("^Web: Validate the selected bets when the draw started will be overlapped by removal all button and valid user message$")
	public void web_Validate_the_selected_bets_when_the_draw_started_will_be_overlapped_by_removal_all_button_and_valid_user_message() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}	
}