package Web_SportBetting;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_SportBetting_TopEvents_OutRight_Results_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_TopEvents_OutRight_Results_Validations() throws Exception {
		driver = Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, sports betting module,login via online method, Topevents, outright ,results page and scroll up$")
	public void web_Chrome_browser_suribet_website_valid_URL_sports_betting_module_login_via_online_method_Topevents_outright_results_page_and_scroll_up() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);

	}

	@When("^Web: Login to sport betting and click on top events and check any games are displayed, clicking on outright some matches are displayed and in result section results of different games are displayed to user$")
	public void web_Login_to_sport_betting_and_click_on_top_events_and_check_any_games_are_displayed_clicking_on_outright_some_matches_are_displayed_and_in_result_section_results_of_different_games_are_displayed_to_user() throws Throwable {

		// Fetch the account balance 
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualRoulette: "+ mbal);


		WebElement outright = driver.findElement(By.xpath("//*[@class='out-right']/ul/li[2]")); 
		Assert.assertEquals("OUT RIGHT", outright.getText());
		outright.click();
		Thread.sleep(7000);

		WebElement outright1 = driver.findElement(By.xpath("//*[@id='OutrightId']")); 
		Assert.assertTrue(outright1.isDisplayed());
		Thread.sleep(3000);
		System.out.println("selecting the outright from the menu section");

		WebElement outrightselect = driver.findElement(By.xpath("//*[@class='position-relative outright-left-section']/div/div[2]"));  
		outrightselect.click();
		Thread.sleep(2000);

		WebElement outgames = driver.findElement(By.xpath("//*[@class='position-relative outright-left-section']/div/div[2]/ul/li")); 
		Assert.assertTrue(outgames.isDisplayed());
		Thread.sleep(2000);
		outgames.click();
		Thread.sleep(4000);
		System.out.println("selecting the game from outright section");

		WebElement outgamestable = driver.findElement(By.xpath("//*[@class='match-toggle outright-details']")); 
		Assert.assertTrue(outgamestable.isDisplayed()); 
		Thread.sleep(2000);
		System.out.println("Available game details table is displayed to the user ");
		driver.findElement(By.xpath("//*[@class='fas fa-window-close min-close-btn']")).click(); 
		Thread.sleep(3000);
		System.out.println("Close the data table");

	}
	@Then("^Web: Validate the top events section selection results in top events displayed and out right selection too displays the games$")
	public void web_Validate_the_top_events_section_selection_results_in_top_events_displayed_and_out_right_selection_too_displays_the_games() throws Throwable {

		WebElement topevents = driver.findElement(By.xpath("//*[@class='out-right']/ul/li[1]")); 
		Assert.assertEquals("TOP EVENTS", topevents.getText());
		topevents.click();
		Thread.sleep(7000);

		WebElement countrygames = driver.findElement(By.xpath("//*[@class='country-name ng-scope']")); 
		Assert.assertTrue(countrygames.isDisplayed());
		Thread.sleep(3000);
		countrygames.click();
		Thread.sleep(2000);
		System.out.println("selecting the top events game from top event section");

		WebElement countrygamestable = driver.findElement(By.xpath("//*[@ng-repeat='evts in hevents.match']")); 
		Assert.assertTrue(countrygamestable.isDisplayed());
		Thread.sleep(3000); 
		System.out.println("Available game details table is displayed to the user ");

		driver.findElement(By.cssSelector(".fas.fa-window-close")).click(); 
		Thread.sleep(3000);
		System.out.println("Close the data table"); 
		Assert.assertTrue(topevents.isDisplayed());
		Thread.sleep(2000);
	}

	@Then("^Web: Validate the result section with game name event name and search the result with date picker and validate the result with pagenavigations and close button functionality$")
	public void web_Validate_the_result_section_with_game_name_event_name_and_search_the_result_with_date_picker_and_validate_the_result_with_pagenavigations_and_close_button_functionality() throws Throwable {

		//Search result validations
		WebElement Results = driver.findElement(By.xpath("//*[@class='out-right']/ul/li[3]")); 
		Assert.assertEquals("RESULTS", Results.getText());
		Results.click();
		Thread.sleep(7000);

		WebElement Resultssearch = driver.findElement(By.xpath("(//div[@class='sports-head position-relative text-center ng-binding'])[2]")); 
		Assert.assertEquals("RESULTS", Resultssearch.getText());
		WebElement search = driver.findElement(By.xpath("(//*[@placeholder='Search'])[2]"));
		search.click();
		Thread.sleep(2000);
		search.sendKeys("Darts");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@ng-hide='globalSportsCtlr.searchloader']")).click(); 
		Thread.sleep(5000);

		//Search table result 
		WebElement searchtable = driver.findElement(By.xpath("//*[@class='d-block nospoticon sports-sprite Darts_icon']")); 
		Assert.assertTrue(searchtable.isDisplayed());
		Thread.sleep(2000);

		WebElement filter = driver.findElement(By.cssSelector(".fa.fa-search.statistics-sign.position-absolute")); 
		filter.click();
		Thread.sleep(2000);

		WebElement eventsearch = driver.findElement(By.xpath("(//*[@class='match-toggle ng-scope'])[3]/ul/li/span")); 
		String esearch = eventsearch.getText();
		Thread.sleep(2000);
		WebElement eventmatch = driver.findElement(By.xpath("(//*[@class='match-toggle ng-scope'])[3]/ul/li[2]")); 
		String ematch = eventmatch.getText();
		Thread.sleep(2000);

		WebElement eventfield = driver.findElement(By.xpath("//*[@ng-model='globalSportsCtlr.EventNameKey']")); 
		eventfield.click();
		Thread.sleep(2000);
		eventfield.sendKeys(esearch);
		Thread.sleep(2000);
		WebElement matchfield = driver.findElement(By.xpath("//*[@ng-model='globalSportsCtlr.MatchNameKey']")); 
		matchfield.click();
		Thread.sleep(2000);
		matchfield.sendKeys(ematch);
		Thread.sleep(2000);

		WebElement searchfield = driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.resultsSearchFun(globalSportsCtlr)']")); 
		searchfield.click();
		Thread.sleep(4000);

		//Search result validations
		WebElement eventsearch1 = driver.findElement(By.xpath("(//*[@class='match-toggle ng-scope'])/ul/li/span")); 
		String esearch1 = eventsearch1.getText();
		Thread.sleep(2000);
		Assert.assertEquals(esearch, esearch1);
		WebElement eventmatch1 = driver.findElement(By.xpath("(//*[@class='match-toggle ng-scope'])/ul/li[2]")); 
		String ematch1 = eventmatch1.getText();
		Thread.sleep(2000);
		Assert.assertEquals(ematch, ematch1);
		System.out.println("Search result validations are verified and displayed as per search result");

		WebElement close = driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.resultsShow=false']")); 
		close.click();
		Thread.sleep(3000); 

		//Select the soccer game data for validations
		driver.findElement(By.xpath("//*[@class='d-inline-block nospoticon  sports-sprite Soccer_icon']")).click();
		Thread.sleep(4000); 

		//Records per page validation
		Select records = new Select(driver.findElement(By.xpath("//*[@ng-model='globalSportsCtlr.Recordspage']")));
		Thread.sleep(2000);

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", records);
		List actualvalues = new ArrayList();
		for(WebElement rows: records.getOptions()) {
			actualvalues.add(rows.getText());
		}
		List expectedvalues = new ArrayList();
		expectedvalues.add("20");
		expectedvalues.add("30");
		expectedvalues.add("50");
		expectedvalues.add("100");

		for(int i=0; i<actualvalues.size(); i++) {
			System.out.println("Actual :"+ actualvalues.get(i)+" Expected: "+ expectedvalues.get(i));
			Assert.assertTrue(actualvalues.get(i).equals(expectedvalues.get(i)));
		}
		Thread.sleep(2000);

		records.selectByVisibleText("20");
		System.out.println("Selected 20 records fetched");
		Thread.sleep(5000);
		WebElement page20 = driver.findElement(By.xpath("//*[@class='Page-navigation']/div/span"));
		Assert.assertTrue(page20.getText().contains("Records : 1 - 20"));
		System.out.println("Verified on selecting 20 records drop down from the list it has displayed the exact list of items to the user");
		Thread.sleep(5000);

		records.selectByVisibleText("50");
		System.out.println("Selected 50 records fetched");
		Thread.sleep(5000);
		WebElement page50 = driver.findElement(By.xpath("//*[@class='Page-navigation']/div/span"));
		Assert.assertTrue(page50.getText().contains("Records : 1 - 50"));
		System.out.println("Verified on selecting 50 records drop down from the list it has displayed the exact list of items to the user");

		records.selectByVisibleText("30");
		System.out.println("Selected 30 records fetched");
		Thread.sleep(5000);
		WebElement page30 = driver.findElement(By.xpath("//*[@class='Page-navigation']/div/span"));
		Assert.assertTrue(page30.getText().contains("Records : 1 - 30"));
		System.out.println("Verified on selecting 30 records drop down from the list");

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", records);
		Thread.sleep(2000);
		records.selectByVisibleText("100");
		System.out.println("Selected 100 records fetched");
		Thread.sleep(5000);
		WebElement page100 = driver.findElement(By.xpath("//*[@class='Page-navigation']/div/span"));
		Assert.assertTrue(page100.getText().contains("Records : 1 - 100"));
		System.out.println("Verified on selecting 100 records drop down from the list");

		/*
		 * When click on fast forward the page is navigated from 1st page to the last page 
		 * When click on simple fast forward 
		 */
		WebElement pagenationnumber = driver.findElement(By.cssSelector	(".page-link.ng-binding")); 
		String currentpage = pagenationnumber.getText();
		int num = Integer.parseInt(currentpage);
		int number = num+1;
		// When selecting the next page forward navigation it should show lists from page 2 sheet
		WebElement fwdpage = driver.findElement(By.cssSelector(".fas.fa-angle-right")); 
		WebElement bckpage = driver.findElement(By.cssSelector(".fas.fa-angle-left"));  

		fwdpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after forward navigation to the next page :"+ pagenationnumber.getText());
		String currentpage1 = pagenationnumber.getText();
		int num1 = Integer.parseInt(pagenationnumber.getText());
		Assert.assertEquals(number, num1);
		System.out.println("Forward pagenation is verified");

		System.out.println("Current page before back navigation to the next page :"+ currentpage1);
		bckpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after back navigation to the next page :"+ pagenationnumber.getText());
		Assert.assertEquals(currentpage, pagenationnumber.getText());
		System.out.println("backward pagenation is verified");

		//Again backward navigation
		bckpage.click();
		Thread.sleep(7000);
		Assert.assertEquals(currentpage, pagenationnumber.getText());
		System.out.println("backward pagenation is verified");

		// Fast forward navigation
		WebElement fastfwdpage = driver.findElement(By.cssSelector(".fas.fa-angle-double-right")); 
		WebElement fastbckpage = driver.findElement(By.cssSelector(".fas.fa-angle-double-left")); 

		System.out.println("Current page before fast forward navigation to the next page :"+ pagenationnumber.getText());
		fastfwdpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after forward navigation to the next page :"+ pagenationnumber.getText());
		String fastfwdpage1 = pagenationnumber.getText();
		String newpage = driver.findElement(By.xpath("//*[@class='Page-navigation']/div/span")).getText();  
		String totalpage = newpage.substring(33, 40);
		String totalpage1 = totalpage.replaceAll("[^0-9]", "");
		Assert.assertEquals(totalpage1, fastfwdpage1);
		System.out.println("fast forward pagenation is verified");

		fastbckpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after fast back navigation to the next page :"+ pagenationnumber.getText());
		Assert.assertEquals(currentpage, pagenationnumber.getText());
		System.out.println("fast backward pagenation is verified");


		WebElement filter1 = driver.findElement(By.cssSelector(".fa.fa-search.statistics-sign.position-absolute")); 
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", filter1);
		Thread.sleep(4000);
		filter1.click();
		Thread.sleep(2000);

		//Date pick selection validations
		System.out.println("With valid dates verify the valid result");
		WebElement fromdate = driver.findElement(By.xpath("(//*[@datepicker-options='dateOptions'])[1]"));
		fromdate.click();
		Thread.sleep(3000);
		WebElement monthpick = driver.findElement(By.xpath("//*[@aria-live='assertive']"));
		monthpick.click();
		Thread.sleep(3000);
		WebElement monthpick1 = driver.findElement(By.xpath("//*[text()='April']"));
		monthpick1.click();
		Thread.sleep(3000); 

		WebElement fromday = driver.findElement(By.xpath("//*[@type='button']/span [text() ='18']"));
		fromday.click();
		Thread.sleep(3000);

		WebElement todate = driver.findElement(By.xpath("(//*[@datepicker-options='dateOptions'])[2]"));
		todate.click();
		Thread.sleep(3000);
		WebElement to_day = driver.findElement(By.xpath("//*[@type='button']/span [text() ='23']"));
		to_day.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.resultsSearchFun(globalSportsCtlr)']")).click();
		Thread.sleep(7000);

		WebElement listdetails = driver.findElement(By.xpath("//*[@class='records ng-binding']"));
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", listdetails);
		Thread.sleep(4000);
		Boolean ldetails = listdetails.isDisplayed();
		Assert.assertTrue(ldetails);
		System.out.println("Date filtered details is displayed to the user");
	}

	@Then("^Web: Validate the scroll up button validation in the event section when scroll down is occured$")
	public void web_Validate_the_scroll_up_button_validation_in_the_event_section_when_scroll_down_is_occured() throws Throwable {

		driver.quit();
	}
}