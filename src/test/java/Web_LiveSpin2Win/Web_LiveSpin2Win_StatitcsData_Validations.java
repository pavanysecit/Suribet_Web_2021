package Web_LiveSpin2Win;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_LiveSpin2Win_StatitcsData_Validations extends Web_LiveSpin2Win_URL_OnlineLogin{
WebDriver driver;
	
	public Web_LiveSpin2Win_StatitcsData_Validations() throws Exception {
		driver = Web_LiveSpin2Win_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, live Spin(\\d+)Win module, valid logins, statitcs table, count time$")
	public void web_Chrome_browser_suribet_website_valid_URL_live_Spin_Win_module_valid_logins_statitcs_table_count_time(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//*[@class='ac_id']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Fetch the staticts table details before the time lapse, after the successfull bet, validate the statitcs results$")
	public void web_Fetch_the_staticts_table_details_before_the_time_lapse_after_the_successfull_bet_validate_the_statitcs_results() throws Throwable {
		/*
		 *       Steps for statitcs data
		 * 1. fetch the current draw number
		 * 2. fetch the statitcs result data like (odd or even and the 1st 12 and next 12 number) before the current draw 
		 * 3. wait till the draw is complete and wining number is announced 
		 * 4. Search for the winning number in the draw results coulmn
		 * 5. Fetch the color, odd or even status of the winning number 
		 * 6. Verify the current statitcs should be increased by 1 number as per the last win in live roulette game
		 */

		//Draw num

		WebElement drawnum = driver.findElement(By.xpath("//*[@class='current_draw m-1 mt-2']/div/h6/span"));
	
		//*[@class='status-message p-1 rounded text-center w-100 yellow']
		
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

	@Then("^Web: Validate the win combination count is updated in the statitcs table after each round$")
	public void web_Validate_the_win_combination_count_is_updated_in_the_statitcs_table_after_each_round() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}
