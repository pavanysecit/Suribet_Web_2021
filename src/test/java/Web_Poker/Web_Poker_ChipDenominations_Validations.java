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

public class Web_Poker_ChipDenominations_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_ChipDenominations_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual poker module,login via online method, chips, double, stake and undo$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_poker_module_login_via_online_method_chips_double_stake_and_undo() throws Throwable {
	    
	}

	@When("^Web: Select the future draw and selecting chip and place on any winning combinations and add addidtional denomation to the same and select the (\\d+)X button and at last undo the chips$")
	public void web_Select_the_future_draw_and_selecting_chip_and_place_on_any_winning_combinations_and_add_addidtional_denomation_to_the_same_and_select_the_X_button_and_at_last_undo_the_chips(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement acc = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(acc));

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
		WebElement DNo = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]/div")); 
		String Drawdetail = DNo.getText().replaceAll("[^0-9]", "");
		System.out.println("Selected draw number:"+ Drawdetail);

		DrawNO.click();
		Thread.sleep(2000);

		//Select the chip denominations as 2 from the available chips
		WebElement Chip5 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(5)']"));

		Chip5.click();
		Thread.sleep(2000);

		// Select the Numbers from the win combination board and place the bet
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Royal Flush']/.."));

		hand1.click();
		Thread.sleep(2000);

		//Stake verification for one bet place 
		WebElement stake = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[3]/span[2]")); 
		Assert.assertEquals("50.00", stake.getText());

		//Adding the chip on the same win combination 
		WebElement Chip51 = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(5)']"));
		WebElement hand11 = driver.findElement(By.xpath("//*[text()='Straight Flush']/.."));
		Chip51.click();
		Thread.sleep(2000);
		hand11.click();
		Thread.sleep(2000);

		//Verify the stake and the chip denomination updation after the addition
		WebElement stake1 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[3]/span[2]"));
		Assert.assertEquals("100.00", stake1.getText());
		WebElement rows = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[1]/span[2]")); 
		Assert.assertEquals("2", rows.getText());
		System.out.println("another new bet addition is verified");

		WebElement oldchipval = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[1]/span/span[1]"));
		String oldcval = oldchipval.getText();
		Assert.assertEquals("50", oldcval);
		WebElement oldchipval1 = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[2]/span/span[1]"));
		String oldcval1 = oldchipval1.getText();
		Assert.assertEquals("50", oldcval1);

		WebElement oldchiptype = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[1]/span/span[2]"));
		String oldchiptype1 = oldchiptype.getAttribute("class");
		Assert.assertEquals("poker-chip-5", oldchiptype1);
		WebElement oldchiptype11 = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[2]/span/span[2]"));
		String oldchiptype12 = oldchiptype11.getAttribute("class");
		Assert.assertEquals("poker-chip-5", oldchiptype12);
		System.out.println("chip attribute and value before doubling the bet");
		Thread.sleep(3000);

		//Doubling the bets using 2X button
		WebElement doublebet = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.Replay2x()']"));
		doublebet.click();
		Thread.sleep(2000); 

		//Verify the stake and the chip denomination updation on doubling
		WebElement stake11 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[3]/span[2]"));
		Assert.assertEquals("200.00", stake11.getText());
		WebElement rows1 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[1]/span[2]")); 
		Assert.assertEquals("2", rows1.getText());
		System.out.println("doubling the bet has reflected on the betting tray");

		WebElement chipval = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[1]/span/span[1]"));
		String cval = chipval.getText();
		Assert.assertEquals("100", cval);
		WebElement chipval1 = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[2]/span/span[1]"));
		String cval1 = chipval1.getText();
		Assert.assertEquals("100", cval1);

		WebElement chiptype = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[1]/span/span[2]"));
		String chiptype1 = chiptype.getAttribute("class");
		Assert.assertEquals("poker-chip-6", chiptype1);
		WebElement chiptype11 = driver.findElement(By.xpath("(//*[@ng-repeat='b1 in pokerCtlr.PokerCart'])[2]/span/span[2]"));
		String chiptype12 = chiptype11.getAttribute("class");
		Assert.assertEquals("poker-chip-6", chiptype12);
		System.out.println("chip attribute and value doubled after doubling the bet");


		//Verify the undo button validations
		WebElement undo = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.UndoButton()']"));
		undo.click();
		Thread.sleep(2000);

		//Verify the stake and the chip denomination updation on undo
		WebElement stake12 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[3]/span[2]"));
		Assert.assertEquals("100.00", stake12.getText());
		WebElement rows12 = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap mt-3']/div[1]/span[2]")); 
		Assert.assertEquals("1", rows12.getText());
		System.out.println("undo the bet has reflected on the betting tray");

		System.out.println("As undo delets the single bet from the betting tray");
	}

	@Then("^Web: Validate the chip deomination additions on top of same chip and the double and undo functionalities\\.$")
	public void web_Validate_the_chip_deomination_additions_on_top_of_same_chip_and_the_double_and_undo_functionalities() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}