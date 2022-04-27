package Web_VirtualRoulette;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualRoulette_ChipDenominations_LockAndUnlock_ReplayButton_Validations extends Web_VirtualRoulette_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualRoulette_ChipDenominations_LockAndUnlock_ReplayButton_Validations() throws Exception {
		driver =Web_VirtualRoulette_URL_OnlineLogin();
	}
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module, place bet, chip denominations, place bet, cancel bet, lock and unlock state of chip denominations, replay button$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_place_bet_chip_denominations_place_bet_cancel_bet_lock_and_unlock_state_of_chip_denominations_replay_button() throws Throwable {

	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualRoulette module link$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualRoulette_module_link() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[7]"));
		DrawNO.click();
		Thread.sleep(2000);


		//Select the chip denominations as 2 from the available chips
		WebElement Chip2 = driver.findElement(By.className("roul_chip2"));
		WebElement Chip1 = driver.findElement(By.className("roul_chip1"));
		Assert.assertEquals("roul_chip1 active", Chip1.getAttribute("class"));
		Assert.assertEquals("1", Chip1.getText());
		System.out.println("Chip 1 is selected and verified");
		Thread.sleep(2000);

		Assert.assertNotEquals("roul_chip2 active", Chip2.getAttribute("class"));
		System.out.println("Chip 2 is not selected");
		Thread.sleep(2000);
		Chip2.click();
		Thread.sleep(2000);
		Assert.assertEquals("roul_chip2 active", Chip2.getAttribute("class"));
		Assert.assertEquals("2", Chip2.getText());
		System.out.println("Chip 2 is selected and verified");
		Thread.sleep(2000);

		//Select the Numbers from the Roulette Board top place the bet 
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

		WebElement tablechip9 = driver.findElement(By.xpath("//label[@class='chipSelected chipSelected2 roul_chip2']"));
		Assert.assertEquals("10", tablechip9.getAttribute("bettypepickid"));
		WebElement tablechip10 = driver.findElement(By.xpath("(//label[@class='chipSelected chipSelected2 roul_chip2'])[3]"));
		Assert.assertEquals("11", tablechip10.getAttribute("bettypepickid"));
		WebElement tablechip13 = driver.findElement(By.xpath("(//label[@class='chipSelected chipSelected2 roul_chip2'])[4]"));
		Assert.assertEquals("14", tablechip13.getAttribute("bettypepickid"));
		WebElement tablechip14 = driver.findElement(By.xpath("(//label[@class='chipSelected chipSelected2 roul_chip2'])[2]"));
		Assert.assertEquals("15", tablechip14.getAttribute("bettypepickid"));
		System.out.println("All four selected chips are in lock on the selected bets having denominations: "+ tablechip9.getText());


		//Chip unlock by deleting the placed bet and verify whether is it still on the roulette table
		WebElement cancelbet = driver.findElement(By.cssSelector(".close_but.slideMenuFalse"));
		cancelbet.click();
		Thread.sleep(2000);
		WebElement unlockchip14 = driver.findElement(By.xpath("//label[@class='chipSelected chipSelected2 ng-hide' and @bettypepickid='15']"));
		Assert.assertEquals("",unlockchip14.getText());
		System.out.println("Unlock is working fine and the chip denomination is not assigned for selected bet num in roulette table");

		// Fetch the stake amount and stake rows before placing the bet
		WebElement stakeamount = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div[3]/span[2]")); 
		String stakeamt = stakeamount.getText();
		String stkamt = stakeamt.replaceAll(",", "");
		double betamount =Double.parseDouble(stkamt); 
		WebElement stakerows = driver.findElement(By.xpath("//div[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div/span[2]"));
		String stkerows = stakerows.getText();

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement replay = driver.findElement(By.xpath("//img[@ng-click='globalrouletteCtlr.Replay()']"));
		
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(3000);
		
		//Verify the number are in lock state after bet is placed successfully 
		WebElement print = driver.findElement(By.cssSelector(".PrintBUT.ng-scope"));
		wait.until(ExpectedConditions.visibilityOf(print));
		Thread.sleep(4000);
//		Assert.assertTrue(tablechip9.isDisplayed());
//		Assert.assertTrue(tablechip9.isDisplayed());
//		Assert.assertTrue(tablechip9.isDisplayed());
		
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
		driver.getTitle();

		//Select another Draw to place the previously placed successfull bets 
		WebElement NewDrawNo = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[9]"));
		NewDrawNo.click();
		String NewDdetails = NewDrawNo.getText();
		System.out.println(NewDdetails);

		replay.click();
		Thread.sleep(3000);

		//Verify the previously successfully placed bets are again placed
		Assert.assertEquals("10", tablechip9.getAttribute("bettypepickid"));
		WebElement Newtablechip10 = driver.findElement(By.xpath("(//label[@class='chipSelected chipSelected2 roul_chip2'])[2]"));
		Assert.assertEquals("11", Newtablechip10.getAttribute("bettypepickid"));
		WebElement Newtablechip13 = driver.findElement(By.xpath("(//label[@class='chipSelected chipSelected2 roul_chip2'])[3]"));
		Assert.assertEquals("14", Newtablechip13.getAttribute("bettypepickid"));
		System.out.println("Replay button initiates the old bets to repeat and working fine");
		System.out.println("All three selected chips are in lock on the selected bets having denominations: "+ Newtablechip13.getText());

		//Verify the stake amount and stake rows after replay button is 
		String newstakeamt = stakeamount.getText();
		String newstkerows = stakerows.getText();

		Assert.assertEquals(stakeamt, newstakeamt);
		Assert.assertEquals(stkerows, newstkerows);
		System.out.println("Before and after stake rows and stake amounts are matching and verified");
	}

	@Then("^Web: Select some numbers on roulette table and vary the chip denominations too, verify the current chip denomination bet is being placed as bet over the table$")
	public void web_Select_some_numbers_on_roulette_table_and_vary_the_chip_denominations_too_verify_the_current_chip_denomination_bet_is_being_placed_as_bet_over_the_table() throws Throwable {

	}

	@Then("^Web: Cancel the individual bet and check the lock and unlock property of chip denominations on table$")
	public void web_Cancel_the_individual_bet_and_check_the_lock_and_unlock_property_of_chip_denominations_on_table() throws Throwable {

	}

	@Then("^Web: Select another draw and select the replay button, and verify perviously successfully placed bets should be selected on the table for current selected draw$")
	public void web_Select_another_draw_and_select_the_replay_button_and_verify_perviously_successfully_placed_bets_should_be_selected_on_the_table_for_current_selected_draw() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}