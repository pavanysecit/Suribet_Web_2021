package Web_Poker;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Poker_StatitcsData_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;

	public Web_Poker_StatitcsData_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual poker module, valid logins, statitcs table, count time$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_poker_module_valid_logins_statitcs_table_count_time() throws Throwable {
	    
	}

	@When("^Web: Fetch the staticts table details before the time lapse, after the successfull bet, validate the statitcs results$")
	public void web_Fetch_the_staticts_table_details_before_the_time_lapse_after_the_successfull_bet_validate_the_statitcs_results() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);


		/*
		 *       Steps for statistics data
		 * 1. fetch the current draw num 
		 * 2. fetch the statistics result data like (winning combination) before the current draw 
		 * 3. wait till the draw is complete and wining combination is announced 
		 * 4. Search for the winning combination in the draw results column 
		 * 5. Verify the current statistics should be increased by 1 number as per the last win in poker game
		 */

		//Draw number

		WebElement stat = driver.findElement(By.xpath("//*[@class='d-flex justify-content-end']/a[3] ")); 
		Assert.assertEquals("Statistics", stat.getText());
		Assert.assertTrue(stat.isDisplayed());
		Thread.sleep(2000);
		stat.click();
		Thread.sleep(3000);

		//last win results to be displayed to user
		List<WebElement> statpercentage = driver.findElements(By.xpath("//*[@ng-repeat='a in pokerCtlr.WinningCombinationStatisticsData']"));
		System.out.println("Size of List: "+statpercentage.size());
		Assert.assertTrue(driver.findElement(By.xpath("//*[@ng-repeat='a in pokerCtlr.WinningCombinationStatisticsData']")).isDisplayed());
		Assert.assertTrue(!statpercentage.isEmpty());
		Assert.assertEquals(10, statpercentage.size() ,0.00);
		System.out.println("Statistics percentage result section not empty and displayed to user");

		//last win results to be displayed to user
		List<WebElement> statcombination = driver.findElements(By.xpath("//div[@class='text-center py-1 rounded']"));
		System.out.println("Size of List: "+statcombination.size());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text-center py-1 rounded']")).isDisplayed());
		Assert.assertTrue(!statcombination.isEmpty());
		Assert.assertEquals(10, statcombination.size() ,0.00);
		System.out.println("Statistics win combination result section not empty and displayed to user");


		WebElement drawnum = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.pokerkDrawslistFun(pokerCtlr.PokerDraws[0], 0)']/div/div"));
		WebElement drawtime = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.pokerkDrawslistFun(pokerCtlr.PokerDraws[0], 0)']/div/div[3]"));

		WebElement royalflush = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[1]/div/div[2]"));
		WebElement straightflush = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[2]/div/div[2]"));
		WebElement fourkind = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[3]/div/div[2]"));
		WebElement fullhouse = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[4]/div/div[2]"));	
		WebElement flush = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[5]/div/div[2]"));
		WebElement straight = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[1]/div/div[2]"));
		WebElement threekind = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[2]/div/div[2]"));
		WebElement twopair = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[3]/div/div[2]"));
		WebElement onepair = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[4]/div/div[2]"));	
		WebElement highcard = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[5]/div/div[2]"));

		String dnum = drawnum.getText().replaceAll("[^0-9]", "");
		System.out.println("dnum: "+ dnum);
		String dtime = drawtime.getText();
		System.out.println("dtime: "+ dtime);

		int royalflush1 = Integer.parseInt(royalflush.getText()); 
		System.out.println("royalflush: "+ royalflush1);
		int straightflush1 = Integer.parseInt(straightflush.getText());  
		System.out.println("straightflush: "+ straightflush1);
		int fourkind1 = Integer.parseInt(fourkind.getText()); 
		System.out.println("fourkind: "+ fourkind1);
		int fullhouse1 = Integer.parseInt(fullhouse.getText());  
		System.out.println("fullhouse: "+ fullhouse1);
		int flush1 = Integer.parseInt(flush.getText()); 
		System.out.println("flush: "+ flush1);
		int straight1 = Integer.parseInt(straight.getText());  
		System.out.println("straight: "+ straight1);
		int threekind1 = Integer.parseInt(threekind.getText()); 
		System.out.println("threekind: "+ threekind1);
		int twopair1 = Integer.parseInt(twopair.getText());  
		System.out.println("twopair: "+ twopair1);
		int onepair1 = Integer.parseInt(onepair.getText()); 
		System.out.println("onepair: "+ onepair1);
		int highcard1 = Integer.parseInt(highcard.getText());  
		System.out.println("highcard: "+ highcard1);

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
		Thread.sleep(3*30*1000);

		//Check the last win with the draw number 
		WebElement res = driver.findElement(By.xpath("//*[@class='d-flex justify-content-end']/a[2] "));
		res.click();
		Thread.sleep(3000);
		WebElement searchdraw = driver.findElement(By.xpath("//*[@placeholder='Draw Number']"));
		searchdraw.click();
		Thread.sleep(2000);
		searchdraw.sendKeys(dnum);
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.xpath("//*[@class='btn p-button']"));
		search.click();
		Thread.sleep(5000);
		WebElement drawsearch = driver.findElement(By.xpath("//*[@ng-show='a.Winner!=null']"));
		System.out.println("Draw win pattern: "+drawsearch.getText());
		String dwin = drawsearch.getText().substring(6);
		System.out.println("Search win text: "+dwin);
		Thread.sleep(5000);


		String win = dwin;

		switch(win) {

		case "Three Of A Kind":
			WebElement threekind11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[2]/div/div[2]"));
			int vthreekind = Integer.parseInt(threekind11.getText()); 
			Assert.assertEquals(threekind1+1, vthreekind);
			System.out.println("win pattern is three of a kind");
			break;

		case "Royal Flush":
			WebElement royalflush11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[1]/div/div[2]"));
			int vroyalflush = Integer.parseInt(royalflush11.getText()); 
			Assert.assertEquals(royalflush1+1, vroyalflush);
			System.out.println("win pattern is royalflush");
			break;

		case "Straight Flush":
			WebElement straightflush11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[2]/div/div[2]"));
			int vstraightflush = Integer.parseInt(straightflush11.getText()); 
			Assert.assertEquals(straightflush1+1, vstraightflush);
			System.out.println("win pattern is straight flush");
			break;

		case "Four Of A Kind":
			WebElement fourkind11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[3]/div/div[2]"));
			int vfourkind = Integer.parseInt(fourkind11.getText()); 
			Assert.assertEquals(fourkind1+1, vfourkind);
			System.out.println("win pattern is four of a kind");
			break;

		case "Full House":
			WebElement fullhouse11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[4]/div/div[2]"));
			int vfullhouse = Integer.parseInt(fullhouse11.getText()); 
			Assert.assertEquals(fullhouse1+1, vfullhouse);
			System.out.println("win pattern is full house");
			break;			

		case "Flush":
			WebElement flush11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[5]/div/div[2]"));
			int vflush = Integer.parseInt(flush11.getText()); 
			Assert.assertEquals(flush1+1, vflush);
			System.out.println("win pattern is flush");
			break;

		case "Straight":
			WebElement Straight = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[1]/div/div[2]"));
			int vStraight = Integer.parseInt(Straight.getText()); 
			Assert.assertEquals(straight1+1, vStraight);
			System.out.println("win pattern is Straight");
			break;

		case "Two Pair":
			WebElement twopair11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[3]/div/div[2]"));
			int vtwopair = Integer.parseInt(twopair11.getText()); 
			Assert.assertEquals(twopair1+1, vtwopair);
			System.out.println("win pattern is two pair");
			break;

		case "One Pair":
			WebElement onepair11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[4]/div/div[2]"));	
			int vonepair = Integer.parseInt(onepair11.getText()); 
			Assert.assertEquals(onepair1+1, vonepair);
			System.out.println("win pattern is one pair");
			break;

		case "High Card":
			WebElement highcard11 = driver.findElement(By.xpath("(//*[@ng-repeat='WinningCombi in pokerCtlr.WinningCombinationStatisticsData'])[5]/div/div[2]"));
			int vhighcard = Integer.parseInt(highcard11.getText()); 
			Assert.assertEquals(highcard1+1, vhighcard);
			System.out.println("win pattern is high card");
			break;

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