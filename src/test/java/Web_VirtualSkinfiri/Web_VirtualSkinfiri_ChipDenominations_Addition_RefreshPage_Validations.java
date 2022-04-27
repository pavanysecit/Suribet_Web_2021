package Web_VirtualSkinfiri;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_ChipDenominations_Addition_RefreshPage_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSkinfiri_ChipDenominations_Addition_RefreshPage_Validations() throws Exception {
		driver =Web_VirtualSkinfiri_URL_OnlineLogin();
	} 

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual skinfiri module, place bet, chip denominations, lock and unlock state of chip denominations, refresh page, login and logout$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_skinfiri_module_place_bet_chip_denominations_lock_and_unlock_state_of_chip_denominations_refresh_page_login_and_logout() throws Throwable {
	    
	}

	@When("^Web: Login to suribet website with valid login details, select virtual skinfiri module link$")
	public void web_Login_to_suribet_website_with_valid_login_details_select_virtual_skinfiri_module_link() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		System.out.println(driver.getCurrentUrl());
		System.out.println("Browser title: "+ driver.getTitle());
		
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[6]"));
		String Ddetails = DrawNO.getText();
		WebElement DNo = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[6]/span")); 
		System.out.println(Ddetails);
		String Drawdetail = DNo.getText().replaceAll("[^0-9]", "");
		System.out.println(Drawdetail);

		DrawNO.click();
		Thread.sleep(2000);

		//Select the chip denominations as 2 from the available chips
		WebElement Chip3 = driver.findElement(By.className("roul_chip3"));
//		WebElement Chip1 = driver.findElement(By.className("roul_chip1"));

//		Chip1.click();
//		Thread.sleep(1000);
		
		//Select the Numbers from the Skinfiri Board top place the bet 
		WebElement Pick10 = driver.findElement(By.xpath("//span[@bettypepickid='11']"));
		WebElement Pick9 = driver.findElement(By.xpath("//span[@bettypepickid='10']"));
		WebElement Pick13 = driver.findElement(By.xpath("//span[@bettypepickid='14']"));
		WebElement Pick14 = driver.findElement(By.xpath("//span[@bettypepickid='15']"));

		Pick10.click();
		Thread.sleep(1000);
		Pick9.click();
		Thread.sleep(1000);
		Pick13.click();
		Thread.sleep(1000);
		Pick14.click();
		Thread.sleep(1000);

		WebElement expectedwin = driver.findElement(By.xpath("(//label[@ng-show='cartD.splits.length==0'])[7]"));
		WebElement expectedwin1 = driver.findElement(By.xpath("(//label[@ng-show='cartD.splits.length==0'])[8]"));
		System.out.println("Exp win with 1 chip denominations: "+ expectedwin.getText());
		Assert.assertEquals("36.00", expectedwin.getText());
		System.out.println("Exp win with 1 chip denominations: "+ expectedwin1.getText());
		Assert.assertEquals("36.00", expectedwin.getText());

		Chip3.click();
		Thread.sleep(1000);
		Pick10.click();
		Thread.sleep(1000);
		Pick9.click();
		Thread.sleep(1000);

		//Verify the expected win after adding additional 5 chip denominations
		System.out.println("Exp win by adding 5 chip denominations to existing 1 chip denominations: "+ expectedwin.getText());
		Assert.assertEquals("216.00", expectedwin.getText());
		System.out.println("Exp win by adding 5 chip denominations to existing 1 chip denominations: "+ expectedwin1.getText());
		Assert.assertEquals("216.00", expectedwin1.getText());

		//Before placing bet chips are in lock with numbers at roulette table
		WebElement tablechip9 = driver.findElement(By.xpath("//label[@class='chipSelected chipSelected2 roul_chip3']"));
		Assert.assertEquals("10", tablechip9.getAttribute("bettypepickid"));
		WebElement tablechip10 = driver.findElement(By.xpath("(//label[@class='chipSelected chipSelected2 roul_chip3'])[2]"));
		Assert.assertEquals("11", tablechip10.getAttribute("bettypepickid"));
		System.out.println("Chip lock before placing bet");

		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//FORWARD AND PLACE BET 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(1500);
		System.out.println("Bet placed");
		Thread.sleep(10000);

		Assert.assertEquals("10", tablechip9.getAttribute("bettypepickid"));
		Assert.assertEquals("11", tablechip10.getAttribute("bettypepickid"));
		System.out.println("Chip lock after placing bet");

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
		WebElement newtablechip9 = driver.findElement(By.xpath("//label[@class='chipSelected roul_chip3']//span"));
		WebElement newtablechip10 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3']//span)[2]"));
		Assert.assertTrue(newtablechip9.isDisplayed());
		Assert.assertTrue(newtablechip10.isDisplayed());
		System.out.println("Chips are in lock with roulette table numbers and verified under navigating from tabs");
		Thread.sleep(2000);

		//Refresh the page and verify the chip lock conditions
		//		driver.navigate().refresh();
		//		Thread.sleep(7000);
		robot.keyRelease(KeyEvent.VK_F5);
		Thread.sleep(3000);
		System.out.println("Refresh page is performed");
		//span[text()='Draw # 203']
		WebElement NewDrawNo = driver.findElement(By.xpath("//span[text()='"+Drawdetail+"']"));
		Thread.sleep(2000);
		NewDrawNo.click();
		Thread.sleep(7000);
		//		driver.navigate().refresh();
		//		Thread.sleep(7000);

		WebElement new1tablechip9 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3']//span)[1]"));
		WebElement new1tablechip10 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3']//span)[2]"));
		WebElement new1tablechip13 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip1']//span)[1]"));
		WebElement new1tablechip14 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip1']//span)[2]"));
		Assert.assertEquals("6" ,new1tablechip9.getText());
		Assert.assertEquals("6" ,new1tablechip10.getText());
		Assert.assertEquals("1" ,new1tablechip13.getText());
		Assert.assertEquals("1" ,new1tablechip14.getText());

		//Verify the bet numbers attached to the roulette table	
		WebElement BetId9 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3'])[1]"));
		WebElement BetId10 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3'])[2]"));
		WebElement BetId14 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip1'])[1]"));
		WebElement BetId13 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip1'])[2]"));
		Assert.assertEquals("10" ,BetId9.getAttribute("bettypepickid"));
		Assert.assertEquals("11" ,BetId10.getAttribute("bettypepickid"));
		Assert.assertEquals("14" ,BetId13.getAttribute("bettypepickid"));
		Assert.assertEquals("15" ,BetId14.getAttribute("bettypepickid"));

		System.out.println("Chips are in lock with roulette table numbers and verified after page is refreshed");
		Thread.sleep(2000);

		//Logout and re login to the virtual skinfiri module and verification of chip denomination lock condition
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
		pwd.sendKeys("mansoor@123");
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(10000);
		//		wait.until(ExpectedConditions.visibilityOf(ACC));

		//clicking on previously selected draw
		System.out.println("Verification and selecting the draw num: "+ NewDrawNo.getText() );
		NewDrawNo.click();
		Thread.sleep(2000);
		WebElement new2tablechip09 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3'])[1]"));
		WebElement new2tablechip10 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip3'])[2]"));
		WebElement new2tablechip13 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip1'])[2]"));
		WebElement new2tablechip14 = driver.findElement(By.xpath("(//label[@class='chipSelected roul_chip1'])[1]"));
		Assert.assertTrue(new2tablechip09.isDisplayed());
		Assert.assertTrue(new2tablechip10.isDisplayed());
		Assert.assertTrue(new2tablechip13.isDisplayed());
		Assert.assertTrue(new2tablechip14.isDisplayed());
		System.out.println("Chips are in lock with numbers and verified after again relogin condition");
		Thread.sleep(2000);
	}

	@Then("^Web: Select some numbers on number table and add the same chip denominations individually and the total stake input and place bet$")
	public void web_Select_some_numbers_on_number_table_and_add_the_same_chip_denominations_individually_and_the_total_stake_input_and_place_bet() throws Throwable {
	    
	}

	@Then("^Web: After placed bet check for the chips are still in lock condition for selected bets later navigate to other tabs and refresh virtual skinfiri game page, logout and again login with same credentails$")
	public void web_After_placed_bet_check_for_the_chips_are_still_in_lock_condition_for_selected_bets_later_navigate_to_other_tabs_and_refresh_virtual_skinfiri_game_page_logout_and_again_login_with_same_credentails() throws Throwable {
	    
	}

	@Then("^Web: Redirect to same draw num and verify whether previously placed bet are still in lock with selected numbers on virtual skinfiri number table$")
	public void web_Redirect_to_same_draw_num_and_verify_whether_previously_placed_bet_are_still_in_lock_with_selected_numbers_on_virtual_skinfiri_number_table() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}