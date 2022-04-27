package Web_Poker;

import java.awt.Robot;
import java.util.ArrayList;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.awt.event.KeyEvent;

public class Web_Poker_ChipDenominations_Addition_RefreshPage_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_ChipDenominations_Addition_RefreshPage_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, virtual poker module, place bet, chip denominations, lock and unlock state of chip denominations, refresh page, login and logout$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_poker_module_place_bet_chip_denominations_lock_and_unlock_state_of_chip_denominations_refresh_page_login_and_logout() throws Throwable {
	    
	}

	@When("^Web: Login to suribet website with valid login details, select VirtualPoker module link$")
	public void web_Login_to_suribet_website_with_valid_login_details_select_VirtualPoker_module_link() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

//		System.out.println(driver.getCurrentUrl());
//		System.out.println("Browser title: "+ driver.getTitle());
//		Assert.assertEquals("", driver.getTitle());
//
//		//clicking on Draw details to place bet for the current draw 
//		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
//		WebElement DNo = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]/div")); 
//		String Ddetails = DNo.getText();
//		System.out.println(Ddetails);
//		String Drawdetail = Ddetails.replaceAll("[^0-9]", "");
//		System.out.println("Selected draw number:"+ Drawdetail);
//
//		DrawNO.click();
//		Thread.sleep(2000);
//
//		/*
//		 * Chip increment and decrement at the betting tray 
//		 *  incremenet till the max limit and validate the message 
//		 *  decrement till the min limit and validate the user message 
//		 */
//
//		//Min bet validations
//		WebElement Chip1 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(1)']"));
//		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
//
//		Chip1.click();
//		Thread.sleep(2000);
//		hand1.click();
//		Thread.sleep(2000);
//
//		//decremental
//		WebElement dec = driver.findElement(By.xpath("//*[@class='fa fa-minus-circle slideMenuFalse']")); 
//		dec.click();
//		Thread.sleep(1500);
//
//		WebElement Validmsg = driver.findElement(By.className("EMessage")); 
//		wait.until(ExpectedConditions.visibilityOf(Validmsg));
//		String Vmsg = Validmsg.getText();
//		System.out.println("min bet validation message: "+ Vmsg);
//		//		Assert.assertEquals("Minimum Draw Limit is: undefined Please try with valid limit amount.", Vmsg);
//		WebElement clr = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.ClearFields()']")); 
//		clr.click();
//		Thread.sleep(2000);
//
//		//Max bet validations
//		WebElement Chip6 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(6)']"));
//		WebElement newhand = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
//
//		Chip6.click();
//		Thread.sleep(2000);
//		newhand.click();
//		Thread.sleep(2000);
//
//		//decremental
//		WebElement inc = driver.findElement(By.xpath("//*[@class='fa fa-plus-circle slideMenuFalse']")); 
//		inc.click();
//		Thread.sleep(1500);
//
//		WebElement Validmsg1 = driver.findElement(By.className("EMessage")); 
//		wait.until(ExpectedConditions.visibilityOf(Validmsg1));
//		String Vmsg1 = Validmsg1.getText();
//		System.out.println("min bet validation message: "+ Vmsg1);
//		//		Assert.assertEquals("Maximum Draw Limit is: 100 Please try with valid limit amount.", Vmsg1);
//		WebElement clr1 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.ClearFields()']"));
//		clr1.click();
//		Thread.sleep(2000);


		/*
		 * Check the chip lock before and after placing the bets on the winning combinations.
		 */

		WebElement dn = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[10]"));
		WebElement dno = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[10]/div")); 
		String drwdetails = dno.getText();
		System.out.println(drwdetails);

		dn.click();
		Thread.sleep(2000);

		WebElement Chip11 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(1)']"));
		Chip11.click();
		Thread.sleep(2000);

		// Selelct the Numbers from the win combination board and place the bet
		WebElement hand11 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));

		hand11.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		WebElement beforechiplock = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span"));
		String beforelock = beforechiplock.getText();
		Assert.assertEquals("1", beforelock);
		WebElement bechiplock = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span/span[2]"));
		String belock = bechiplock.getAttribute("class");
		Assert.assertEquals("poker-chip-1", belock);
		System.out.println("Chip lock before placing bet verified");

		//Validate the stake and rows before adding the adding additional denominations to the same chip 
		WebElement stk1 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[1]/span[2]"));
		String strow1 = stk1.getText();
		Assert.assertEquals("1", strow1);
		WebElement stkamt = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[3]/span[2]"));
		String stamt = stkamt.getText();
		Assert.assertEquals("1.00", stamt);
		System.out.println("Stake before the chip denomination adding"); 


		WebElement Chip21 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(2)']"));
		Chip21.click();
		Thread.sleep(2000);
//		WebElement hand14 = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span"));
		WebElement hand14 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		hand14.click();
		Thread.sleep(2000);
		System.out.println("Adding to same bet selected on the hands selections");

		WebElement beforechiplock1 = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span"));
		String beforelock1 = beforechiplock1.getText();
		Assert.assertEquals("3", beforelock1);
		WebElement bechiplock1 = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span/span[2]"));
		String belock1 = bechiplock1.getAttribute("class");
		Assert.assertEquals("poker-chip-2", belock1);
		System.out.println("Chip lock after adding chip to same bet is verified");

		//Validate the stake and rows before adding the adding additional denominations to the same chip 
		WebElement stk11 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[1]/span[2]"));
		String strow11 = stk11.getText();
		Assert.assertEquals("1", strow11);
		WebElement stkamt1 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[3]/span[2]"));
		String stamt1 = stkamt1.getText();
		Assert.assertEquals("3.00", stamt1);
		System.out.println("Stake after the chip denomination adding"); 

		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed");
		Thread.sleep(3000);

		//Open new tab and again redirect to old game tab and verify the chip lock is still active
		Robot robot = new Robot();                          
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL); 
		robot.keyRelease(KeyEvent.VK_T);
		Thread.sleep(3000);

		//Switch focus to new tab
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		System.out.println(tabs);
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));

		Assert.assertTrue(ACC.isDisplayed());
		WebElement NewDrawNo = driver.findElement(By.xpath("//div[text()='"+drwdetails+"']"));
		Thread.sleep(2000);
		NewDrawNo.click();
		Thread.sleep(3000);
		WebElement afterchiplock1 = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span"));
		Assert.assertTrue(afterchiplock1.isDisplayed());
		System.out.println("Chips are in lock with wincombination table and verified under navigating from tabs");
		Thread.sleep(2000);


		//Refresh the page and verify the chip lock conditions
		robot.keyRelease(KeyEvent.VK_F5);
		Thread.sleep(3000);
		System.out.println("Refresh page is performed");

		WebElement NewDrawNo1 = driver.findElement(By.xpath("//div[text()='"+drwdetails+"']"));
		Thread.sleep(2000);
		NewDrawNo1.click();
		Thread.sleep(7000);

		WebElement afterchiplock11 = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span"));
		Assert.assertTrue(afterchiplock11.isDisplayed());
		System.out.println("Chips are in lock with win combination table and verified under refreshing the screen");
		Thread.sleep(2000);

		//Logout and re login to the virtual roulette module and verification of chip denomination lock condition
		driver.findElement(By.cssSelector(".log_off.click_effect_JS")).click();
		Thread.sleep(5000);
		WebElement un =  driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		WebElement pwd =  driver.findElement(By.xpath("//input[@placeholder='Password / Card Pin']"));
		WebElement submit =  driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']")); 

		wait.until(ExpectedConditions.visibilityOf(un));
		Thread.sleep(2000);

		//Relogin
		un.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(2000);
		pwd.sendKeys("mans@123");
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(6000);
		WebElement acc = driver.findElement(By.className("ac_id"));
		wait.until(ExpectedConditions.visibilityOf(acc));

		//clicking on previously selected draw
		WebElement NewDrawNo11 = driver.findElement(By.xpath("//div[text()='"+drwdetails+"']"));
		Thread.sleep(2000);
		NewDrawNo11.click();
		Thread.sleep(7000);
		WebElement afterchiplock12 = driver.findElement(By.xpath("//div[@ng-repeat='b1 in pokerCtlr.PokerCart']/../span/../div/span"));
		Assert.assertTrue(afterchiplock12.isDisplayed());
		System.out.println("Chips are in lock with win combination table and verified under relogin to the suribet site and directing to poker module");
		Thread.sleep(2000);
	}

	@Then("^Web: Select some hands on bet option table and add the same table num with more chip denominations and place bet$")
	public void web_Select_some_hands_on_bet_option_table_and_add_the_same_table_num_with_more_chip_denominations_and_place_bet() throws Throwable {
	    
	}

	@Then("^Web: After placed bet check for the chips are still in lock condition for selected bets later navigate to other tabs and refresh virtual poker game page, logout and again login with same credentails$")
	public void web_After_placed_bet_check_for_the_chips_are_still_in_lock_condition_for_selected_bets_later_navigate_to_other_tabs_and_refresh_virtual_poker_game_page_logout_and_again_login_with_same_credentails() throws Throwable {
	    
	}

	@Then("^Web: Redirect to same draw num and verify whether previously placed bet are still in lock with selected numbers on poker table$")
	public void web_Redirect_to_same_draw_num_and_verify_whether_previously_placed_bet_are_still_in_lock_with_selected_numbers_on_poker_table() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}