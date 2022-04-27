package Web_Skinfiri;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Skinfiri_Bets_Selection_5pick_10pick_Replay_Next10Draws extends Web_Skinfiri_URL_OnlineLogin {
	WebDriver driver;

	public Web_Skinfiri_Bets_Selection_5pick_10pick_Replay_Next10Draws() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, Skinfiri module, valid login,$")
	public void web_Chrome_browser_suribet_website_valid_URL_Skinfiri_module_valid_login() throws Throwable {
	    
	}

	@When("^Web: Login to Skinfiri, select and click on Quickpick, (\\d+) pick, (\\d+) pick buttons$")
	public void web_Login_to_Skinfiri_select_and_click_on_Quickpick_pick_pick_buttons(int arg1, int arg2) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		
		//Locating 5 pick, 10 pick, replay and next upcoming draws
		WebElement _5pick = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.QuickpickSelection(5)']"));
		WebElement _10pick = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.QuickpickSelection(10)']"));
		WebElement replay = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.Replay()']"));
		WebElement nextdraws = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.NextUpComingDraws()']"));
		
		
		//Clicking on 5 pick button to select 5 random numbers
		_5pick.click();
		Thread.sleep(2000);
		//getting the value of rows
		WebElement row_count = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div/span[2]"));
		boolean show = row_count.isDisplayed();
		Assert.assertTrue(show);
		String exp = "5";
		Assert.assertEquals(exp, row_count.getText());
		System.out.println("Verified the 5 Pick and the number of rows displayed is "+row_count.getText());
		Thread.sleep(1000);
		
		WebElement clear_all = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_clear_all()']"));
		clear_all.click();
		Thread.sleep(2000);
		
		//Clicking on 10 pick button to select 5 random numbers
		_10pick.click();
		Thread.sleep(2000);
		//getting the value of rows
		WebElement row_count1 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div/span[2]"));
		boolean show1 = row_count1.isDisplayed();
		Assert.assertTrue(show1);
		String exp1 = "10";
		Assert.assertEquals(exp1, row_count1.getText());
		System.out.println("Verified the 10 Pick and the number of rows displayed is "+row_count1.getText());
		Thread.sleep(1000);
		
		clear_all.click();
		Thread.sleep(2000);
		
		//Placing a bet and clicking on Replay button
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_25  Soldout'])[1]"));
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_27  Soldout'])[1]"));

		Pick2.click();
		Thread.sleep(1000);
		Pick3.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the betting slip");

	
		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));		
		
		//Enter the stake amount
		WebElement stake = driver.findElement(By.xpath("//input[@ng-change='DailyGameCtrl.changeAllStack()']")); 
		stake.sendKeys("1");
		
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(3000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivDgCartAlert")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);
		Thread.sleep(3000);
		
		//Clicking on Replay button and verifying the betting numbers and number of rows
		replay.click();
		Thread.sleep(2000);
		WebElement row_count2 = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w']/div/span[2]"));
		String exp2 = "2";
		Assert.assertEquals(exp2, row_count2.getText());
		System.out.println("Rows count should be 2 and row count displayed on the betting slip: "+row_count2.getText());
		Thread.sleep(1000);
		
		WebElement rno1 = driver.findElement(By.xpath("(//div[@class='slideMenuFalse'])[1]"));
		WebElement rno2 = driver.findElement(By.xpath("(//div[@class='slideMenuFalse'])[2]"));
		
		Assert.assertEquals(Pick2.getText(), rno1.getText());
		Assert.assertEquals(Pick3.getText(), rno2.getText());
		
		clear_all.click();
		Thread.sleep(1000);
		
		//Selecting number and clicking on Next Upcoming draws link 
		Pick3.click();
		Thread.sleep(1000);
		
		//First row -1 is equals to 2nd row and compare the 2nd row value with substracted value of firstrow-1
		nextdraws.click();
		Thread.sleep(2000);
		WebElement _1strow = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[1]"));
		String _1row = _1strow.getText();
		System.out.println("Draw no fetched from first row is: " +_1row);
		
		WebElement _2ndrow = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[3]"));
		String _2row = _2ndrow.getText();
		System.out.println("Draw no fetched from second row is: " +_2row);
		int secsrow = Integer.parseInt(_2row);
		int scrow = Integer.parseInt(_1row) - 1;
		Assert.assertEquals(scrow, secsrow);

		WebElement _3rdrow = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[5]"));
		String _3row = _3rdrow.getText();
		System.out.println("Draw no fetched from second row is: " +_3row);
		int thirow = Integer.parseInt(_3row);
		int thrirdrow = Integer.parseInt(_2row) - 1;
		Assert.assertEquals(thrirdrow, thirow);
		
		WebElement _4throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[7]"));
		String _4row = _4throw.getText();
		System.out.println("Draw no fetched from second row is: " +_4row);
		int fourthrow = Integer.parseInt(_4row);
		int frrow = Integer.parseInt(_3row) - 1;
		Assert.assertEquals(frrow, fourthrow);
		
		WebElement _5throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[9]"));
		String _5row = _5throw.getText();
		System.out.println("Draw no fetched from second row is: " +_5row);
		int fifrow = Integer.parseInt(_5row);
		int fifthrow = Integer.parseInt(_4row) - 1;
		Assert.assertEquals(fifthrow, fifrow);
		
		WebElement _6throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[11]"));
		String _6row = _6throw.getText();
		System.out.println("Draw no fetched from second row is: " +_6row);
		int sixrow = Integer.parseInt(_6row);
		int sixth = Integer.parseInt(_5row) - 1;
		Assert.assertEquals(sixth, sixrow);
		
		
		WebElement _7throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[13]"));
		WebElement element = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[19]"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		String _7row = _7throw.getText();
		System.out.println("Draw no fetched from second row is: " +_7row);
		int sevenrow = Integer.parseInt(_7row);
		int sevrow = Integer.parseInt(_6row) - 1;
		Assert.assertEquals(sevrow, sevenrow);
		
		WebElement _8throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[15]"));
		String _8row = _8throw.getText();
		System.out.println("Draw no fetched from second row is: " +_8row);
		int eghtrow = Integer.parseInt(_8row);
		int erow = Integer.parseInt(_7row) - 1;
		Assert.assertEquals(erow, eghtrow);
		
		WebElement _9throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[17]"));
		String _9row = _9throw.getText();
		System.out.println("Draw no fetched from second row is: " +_9row);
		int ninthrow = Integer.parseInt(_9row);
		int ninrow = Integer.parseInt(_8row) - 1;
		Assert.assertEquals(ninrow, ninthrow);
		
		WebElement _10throw = driver.findElement(By.xpath("(//div[@class='slideMenuFalse ng-binding'])[19]"));
		String _10row = _10throw.getText();
		System.out.println("Draw no fetched from second row is: " +_10row);
		int tenthrow = Integer.parseInt(_10row);
		int tenrow = Integer.parseInt(_9row) - 1;
		Assert.assertEquals(tenrow, tenthrow);
		System.out.println("All 10 draws are verified and all are having different draw no: " +_1row +"," +_2row +"," +_3row +"," +_4row +"," +_5row +"," +_6row +"," +_7row +"," +_8row+"," +_9row +"," +_10row);
		
		clear_all.click();
		Thread.sleep(1000);
		
		//Checking Next Upcoming Draws button is not clickable
		boolean buttonvisible = driver.findElement(By.xpath("//div[@class='box-pick disableClass']")).isDisplayed();
		Assert.assertTrue(buttonvisible);
		
		//Checking Next Reply button is not clickable when user is not logged-in
		driver.findElement(By.xpath("//span[@class='log_off click_effect_JS']")).click();
		Thread.sleep(5000);
		boolean replybutt = driver.findElement(By.xpath("(//div[@class='box-pick disableClass'])[1]")).isDisplayed();
		Assert.assertTrue(replybutt);
	}

	@Then("^Web: Five rows as five betting numbers should be displayed under betting slip$")
	public void web_Five_rows_as_five_betting_numbers_should_be_displayed_under_betting_slip() throws Throwable {
	   
	}

	@Then("^Web: Ten rows as ten betting numbers should be displayed under betting slip$")
	public void web_Ten_rows_as_ten_betting_numbers_should_be_displayed_under_betting_slip() throws Throwable {
	   
	}

	@Then("^Web: Previously placed betting numbers should get displayed in the betting slip on click of Replay button$")
	public void web_Previously_placed_betting_numbers_should_get_displayed_in_the_betting_slip_on_click_of_Replay_button() throws Throwable {
	    
	}

	@Then("^Web: Selected betting number should get selected for next  upcoming draws for the selected day$")
	public void web_Selected_betting_number_should_get_selected_for_next_upcoming_draws_for_the_selected_day() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}