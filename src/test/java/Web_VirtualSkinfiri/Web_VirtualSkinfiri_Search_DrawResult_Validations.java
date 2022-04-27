package Web_VirtualSkinfiri;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_Search_DrawResult_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin {
WebDriver driver;
	
	public Web_VirtualSkinfiri_Search_DrawResult_Validations() throws Exception {
		driver =Web_VirtualSkinfiri_URL_OnlineLogin();
	} 

	
	@Given("^Web: Chrome browser, suribet website valid URL, VirtualSkinfiri module, click on search draw tab and verify the previous draw results are displayed to the user$")
	public void web_Chrome_browser_suribet_website_valid_URL_VirtualSkinfiri_module_click_on_search_draw_tab_and_verify_the_previous_draw_results_are_displayed_to_the_user() throws Throwable {
	    
	}

	@When("^Web: Change the dates and verify under search is selected, all draw results are displayed to user in draw result tabs with and without win combinations$")
	public void web_Change_the_dates_and_verify_under_search_is_selected_all_draw_results_are_displayed_to_user_in_draw_result_tabs_with_and_without_win_combinations() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
 
		 
		/*
		 * Verifying the draw results under various searching methods 
		 * 1. blank search
		 * 2. Verify the draw color
		 * 3. Verify the no-of draw results displayed to user under blank search 
		 * 4. Search with future valid draw numbers i.e between 1 and 479
		 * 5. Search with '0' draw result
		 * 6. Search with above '479' draw result and verify the validation message to the user
		 * 7. Future draw results and verify the valid status i.e open status validation
		 * 8. search for older draws with old dates
		 * 9. wait for no more bets validation during the current draw is being 
		 */

		WebElement DrawResult = driver.findElement(By.xpath("//*[@class='view_results_v']"));
		Assert.assertEquals("View Result", DrawResult.getText());
		System.out.println("Draw Results tab text is validated and displayed: "+DrawResult.getText()+ ",  "+DrawResult.isDisplayed());
		DrawResult.click();
		Thread.sleep(2000);
		
		//Search the draw results with null value and verify the valid result output
		WebElement Dinput = driver.findElement(By.xpath("//*[@placeholder='Draw #']")); 
		Assert.assertTrue(Dinput.isDisplayed());

		// Search with empty draw number 
		WebElement search = driver.findElement(By.xpath("//*[@value='Search']")); 
		search.click();
		Thread.sleep(2000);

		// fetch the current or next draw number 
		WebElement cdraw = driver.findElement(By.xpath("//*[@ng-repeat='a in VDGCtr.vsGetDraws']/span")); 
		System.out.println("Current draw num: "+cdraw.getText().replaceAll("[^0-9]", ""));
		int i=Integer.parseInt(cdraw.getText().replaceAll("[^0-9]", ""));  
	
		
		//pervious draw results 
		int prenum=i-1;
		WebElement previousdraw = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']["+prenum+"]/div/div[3]/span[2]")); 
		String presult = previousdraw.getText();
		int i1=Integer.parseInt(presult); 
		Assert.assertEquals(i-1, i1);
		System.out.println("Verified the pervious draw result is being displayed to the user, under blank search is invoked: "+presult );

		//Verify the number of previous results are displayed to the user
		int firstdraw = i1;
		System.out.println(firstdraw);
		WebElement lastdrawresult = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']/div/div[3]/span[2]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", lastdrawresult);
		Thread.sleep(2000);
		System.out.println("last draw num displayed to user: "+lastdrawresult.getText());
		int ilastresult=Integer.parseInt(lastdrawresult.getText()); 
		Assert.assertEquals(i-firstdraw, ilastresult);
		System.out.println("First draw result: "+presult+ "\nLast draw result in the display result: "+lastdrawresult.getText());

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DrawResult);
		Thread.sleep(2000);
		System.out.println("\n");
		System.out.println("\n");

		/* Search with valid draw results 
		 *  Fetch the current draw and the search the draw results between 1 and current draw-1 so that valid results are displayed */

		System.out.println("**********************************Validation for valid results***************************");
		System.out.println("Current Draw result: "+ i);
		System.out.println("Search the draw results within the valid parametes");
		int r = i;
		int r1=0;
		int r2=0;
		if(r==1) {
			r1=1;
		}if(r>=2) {
			r1= r/2;
			r2 = (int) r1;	
			System.out.println("Valid draw result: "+ r2);
		}

		WebElement input = driver.findElement(By.xpath("//*[@placeholder='Draw #']"));
		if(r==1) {
			input.click();
			Thread.sleep(2000);
			input.sendKeys(String.valueOf(r1));
			Thread.sleep(2000);
			search.click();
			Thread.sleep(2000);
			WebElement validresult = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']"));
			Assert.assertEquals(String.valueOf(r), validresult.getText());
			Assert.assertTrue(validresult.isDisplayed());
			WebElement validdate = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']/div/div"));
			String vdate = validdate.getText();
//			System.out.println(vdate);
//			String vd = vdate.substring(6, 17);
//			System.out.println(vd);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
			Date date = new Date();  
			System.out.println(formatter.format(date));  
			Assert.assertEquals(formatter.format(date), vdate);

		}
		else {
			input.click();
			Thread.sleep(2000);
			input.sendKeys(String.valueOf(r2));
			Thread.sleep(2000);
			search.click();
			Thread.sleep(2000);
			WebElement validresult = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']"));
			Assert.assertEquals(String.valueOf(r2), validresult.getText());
			Assert.assertTrue(validresult.isDisplayed());

			WebElement validdate = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']/div/div"));
			String vdate = validdate.getText();
//			System.out.println(vdate);
//			String vd = vdate.substring(6, 17);
//			System.out.println(vd);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
			Date date = new Date();  
			System.out.println(formatter.format(date));  
			Assert.assertEquals(formatter.format(date), vdate);

		}

		System.out.println("\n");
		System.out.println("\n");
		System.out.println("**********************************Validation for valid draws numbers with future bets***************************");

		/*
		 * Fetch the current draw number and search for draws above current draws and below or equal to 479th draw
		 * check the status validation for future draws
		 */

		System.out.println("Current draw num for future draw selection: "+cdraw.getText());
		int fdraw = Integer.parseInt(cdraw.getText());
		int fdraw1=0;
		if(fdraw<=470) {
			fdraw1 = fdraw+5;
		}else if(fdraw==478) {
			fdraw1=fdraw+1;
		}else {
			int fd= 479-fdraw;
			fdraw1= fdraw+(fd-1);
		}
		input.click();
		Thread.sleep(2000);
		input.clear();
		Thread.sleep(2000);
		input.sendKeys(String.valueOf(fdraw1));
		Thread.sleep(2000);
		System.out.println("Future draw for verification: "+ String.valueOf(fdraw1) );
		search.click();
		Thread.sleep(2000);
		WebElement futuredraw = driver.findElement(By.xpath("//*[@ng-show='VDGCtr.ResultStatusActive==true']"));
		Assert.assertEquals("No result found On this date/draw", futuredraw.getText());
		Assert.assertTrue(futuredraw.isDisplayed());
		System.out.println("Future valid draw status is verified: "+ futuredraw.isDisplayed());


		System.out.println("\n");
		System.out.println("\n");
		System.out.println("**********************************Validation for in-valid results***************************");


		/*
		 *  Search with in-valid draw results 
		 *  with 0 and above 479 
		 */

		//for 0 input
		input.click();
		Thread.sleep(2000);
		input.clear();
		Thread.sleep(2000);
		input.sendKeys("0");
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		WebElement draw1 = driver.findElement(By.xpath("//*[@ng-repeat='r in VDGCtr.vsResult']/div/div[3]/span[2]"));
		Assert.assertTrue(draw1.isDisplayed());
		Assert.assertEquals("1", draw1.getText());
		System.out.println("draw 1 status is verified when input search is '0' and displayed to user: "+ draw1.isDisplayed());


		//For input above 479
		input.click();
		Thread.sleep(2000);
		input.clear();
		Thread.sleep(2000);
		input.sendKeys("480");
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		WebElement noresult = driver.findElement(By.xpath("//*[@ng-show='VDGCtr.ResultStatusActive==true']"));
		Assert.assertTrue(noresult.isDisplayed());
		System.out.println("Past draw status is verified when input search is '480' and displayed to user: "+ noresult.isDisplayed());



		System.out.println("\n");
		System.out.println("\n");
		System.out.println("**********************************Validation for no more bets***************************");

		// this is for the no more bets validations
		/*
		 * Fetch the wait time to and wait for that particular duration 
		 * and search the current draw num and validate the result
		 */
		System.out.println("Current draw num for no more bets validation: "+cdraw.getText());
		String cd = cdraw.getText();
		WebElement waittime = driver.findElement(By.xpath("//*[@class='fa fa-clock-o fa-6 ng-binding ng-scope']")); 
		String time = waittime.getText();
		System.out.println("time length: "+time +"   "+time.length());
		String t = time.substring(0, 2);
		System.out.println("minutes: "+Integer.parseInt(t));
		String t1 = time.substring(3, 5);
		System.out.println("seconds: "+t1);
		int mtos = Integer.parseInt(t)*60;
		
		//new minutes to seconds
		int validsec = mtos+Integer.parseInt(t1);
		
//		long tm = Integer.parseInt(waittime.getText());
//		long sec = TimeUnit.MINUTES.toSeconds(tm);
		System.out.println("conversion from minutes to seconds: "+validsec );
		Thread.sleep(validsec*1000);
		Thread.sleep(4000);
		
		/*
		 * current draw num as input
		 * as the no more bets is within the canvas hence not able to verify the  "NO MORE BETS" validations
		 */
		
		input.click();
		Thread.sleep(2000);
		input.clear();
		Thread.sleep(2000);
		input.sendKeys(cd);
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		WebElement nobets = driver.findElement(By.xpath("//li[@ng-repeat ='res in globalrouletteCtlr.VrResults track by $index'][1]/div/span/span"));
		Assert.assertEquals("NO MORE BETS", nobets.getText());
		Assert.assertTrue(nobets.isDisplayed());
		System.out.println("No more bets when the bet is activeand valid message is verified: "+ nobets.isDisplayed());
	}

	@Then("^Web: Search with blank draw num and verify the pervious draw is displayed, with bet number and bet color$")
	public void web_Search_with_blank_draw_num_and_verify_the_pervious_draw_is_displayed_with_bet_number_and_bet_color() throws Throwable {
	    
	}

	@Then("^Web: Search with invalid draw numbers and verify the validation results for '(\\d+)' and above '(\\d+)' invalid draw results$")
	public void web_Search_with_invalid_draw_numbers_and_verify_the_validation_results_for_and_above_invalid_draw_results(int arg1, int arg2) throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}