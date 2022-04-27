package Web_Poker;

import java.text.DecimalFormat;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Poker_BettingSlip_BettingTray_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_BettingSlip_BettingTray_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual poker module, place bet, betting slip and betting tray$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_poker_module_place_bet_betting_slip_and_betting_tray() throws Throwable {
	    
	}

	@When("^Web: place bet and fetch the selected bets details from betting tray and from the betting slip details$")
	public void web_place_bet_and_fetch_the_selected_bets_details_from_betting_tray_and_from_the_betting_slip_details() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		Thread.sleep(2000);

		// Validate without selecting the draw number try to place bet 
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		Forward.click();
		Thread.sleep(1500);

		WebElement Validmsg1 = driver.findElement(By.className("EMessage")); 
		wait.until(ExpectedConditions.visibilityOf(Validmsg1));
		String Vmsg1 = Validmsg1.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg1);
		Assert.assertEquals("You have not selected any bet", Vmsg1);
		System.out.println("Null bets selction and trying to click on forward button user message validated");

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[5]"));
		String Ddetails = DrawNO.getText();
		WebElement DNo = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]/div")); 
		System.out.println("First draw selected number: "+ DNo.getText());

		DrawNO.click();
		Thread.sleep(2000);

		System.out.println(Ddetails);
		String Drnum = DNo.getText().replaceAll("[^0-9]", "");
		System.out.println("Extract the draw number: "+ Drnum);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath(" "));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");


		// Get the selected bet details
		WebElement bet1 = driver.findElement(By.xpath("//*[@ng-repeat='a in pokerCtlr.PokerCart']"));
		WebElement bet2 = driver.findElement(By.xpath("(//*[@ng-repeat='a in pokerCtlr.PokerCart'])[2]"));
		System.out.println("bet1 details: "+bet1.getText());
		System.out.println("bet2 details: "+bet2.getText());

		//Fetch the stake amount for the selected bets
		WebElement stakerows = driver.findElement(By.xpath("(//*[@class='px-0 col-4 text-center d-flex flex-column'])[3]/span[2]")); 
		String stakrow = stakerows.getText();
		String stkrw = stakrow.replaceAll(",", "");
		double betstk =Double.parseDouble(stkrw); 

		//Fetch the stake amount for the selected bets
		WebElement stakeamt = driver.findElement(By.xpath("(//*[@class='px-0 col-4 text-center d-flex flex-column'])[3]/span[2]")); 
		String stakamount = stakeamt.getText();
		String stkamt = stakamount.replaceAll(",", "");
		double betamt =Double.parseDouble(stkamt); 

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

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//span[@ng-click='pokerCtlr.CancelSlip()']"));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(3000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("(//*[text()='Cancel'])[3]"));
		wait1.until(ExpectedConditions.visibilityOf(SlipCancel));

		WebElement bet1slip = driver.findElement(By.xpath("(//*[@ng-repeat='a in pokerCtlr.CancelSlipData'])[1]")); 
		WebElement bet2slip = driver.findElement(By.xpath("(//*[@ng-repeat='a in pokerCtlr.CancelSlipData'])[2]")); 
		System.out.println(bet1slip.getText()); 
		System.out.println(bet2slip.getText()); 

		WebElement betDrawnum = driver.findElement(By.xpath("//*[@class='d-flex flex-wrap pb-3']/div[3]/span")); 
		System.out.println(betDrawnum.getText()); 
//		Assert.assertEquals(DNo.getText(), betDrawnum.getText());

		WebElement bettotalamount = driver.findElement(By.xpath("//*[@class='table-responsive']//following-sibling::div/div/span")); 
		String bamt = bettotalamount.getText().replaceAll("^0-9", "");
//		double str1 = Double.parseDouble(bamt);
		DecimalFormat f = new DecimalFormat("##.00");
//		System.out.println(f.format(str1));
		System.out.println();
//		Assert.assertEquals(betamt, f.format(str1));

		//Validation of the betting slip with the betting tray
	}

	@Then("^Web: Validate the details from the collected datas and validate any mis matching from the selected bets from the displayed bets$")
	public void web_Validate_the_details_from_the_collected_datas_and_validate_any_mis_matching_from_the_selected_bets_from_the_displayed_bets() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}