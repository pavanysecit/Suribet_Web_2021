package Web_SportBetting;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_SportBetting_Reply_ReadyMadeBets_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_Reply_ReadyMadeBets_Validations() throws Exception {
		driver =Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, Sport Betting module,login via online method, replay button using replay code and ready made bets button$")
	public void web_Chrome_browser_suribet_website_valid_URL_Sport_Betting_module_login_via_online_method_replay_button_using_replay_code_and_ready_made_bets_button() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Place bet via ready made bets$")
	public void web_Place_bet_via_ready_made_bets() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000); 

		//*[@class='name heigth-outright ng-binding']
		WebElement bettray = driver.findElement(By.xpath("//*[@class='tip-pick tip-pick d-flex justify-content-between']"));
		String btray = bettray.getText();
		System.out.println("Bet tray bet details: "+ btray);

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//button[@ng-click='globalSportsCtlr.CartSubmit_Submit()']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2500);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message: "+ Vmsg);
		Assert.assertEquals("Bet has been Placed Successfully", Vmsg);

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()='Cancel Slip']"));
		wait.until(ExpectedConditions.visibilityOf(Cancel));
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(4000);

		WebElement replaycode = driver.findElement(By.xpath("//*[@class='ul_clear slipgrid_d slipgrid_3']/li[4]")); 
		String rcode = replaycode.getText();

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//div[@ng-if='cancelbutton']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));

		SlipCancel.click();
		Thread.sleep(3000);

		//Clicking on Close icon on the pop-up
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		WebElement SlipCancelMessage = driver.findElement(By.xpath("//form[@class='EmptyValidation_W_JS ng-pristine ng-valid']/div/b"));
		System.out.println("Cancel Slipmessage  text is verified: "+ SlipCancelMessage.getText());
		Assert.assertEquals("Slip Successfully Cancelled.", SlipCancelMessage.getText());
		Thread.sleep(2000);

		WebElement custompopup = driver.findElement(By.cssSelector(".PrintSlipDet.MsgPop_OK"));
		custompopup.click();
		Thread.sleep(4000);

		WebElement replayfield = driver.findElement(By.xpath("//*[@ng-model='globalSportsCtlr.ReplayCode']")); 
		replayfield.click();
		Thread.sleep(2000);
		replayfield.sendKeys(rcode);
		Thread.sleep(2000);
		WebElement replaybutton = driver.findElement(By.xpath("//*[@ng-click='ReplaySlipfun()']"));   
		replaybutton.click();
		Thread.sleep(4000);

		WebElement bettray1 = driver.findElement(By.xpath("//*[@class='tip-pick tip-pick d-flex justify-content-between']")); 
		System.out.println("Bet tray bet details: "+ bettray1.getText());
		Assert.assertEquals(btray, bettray1.getText());
		System.out.println("Replay button validations is successfull and verified as same bet is picked after applying the replay code from reviously placed bet");

		//Clear the bet tray before next series of validations
		driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.fn_btnClear()']")).click();
		Thread.sleep(4000);
	}

	@Then("^Web: Place bet and fetch the replay code and fill the code at replay code and validate the same old bet values are entered in the betting tray for the new bet to be placed$")
	public void web_Place_bet_and_fetch_the_replay_code_and_fill_the_code_at_replay_code_and_validate_the_same_old_bet_values_are_entered_in_the_betting_tray_for_the_new_bet_to_be_placed() throws Throwable {

		//Readymade bets
		WebElement readymade = driver.findElement(By.xpath("//*[@class='ready-made-bets']")); 
		Assert.assertTrue(readymade.isDisplayed());
		System.out.println("Ready made bets are available to user on the screen for selections");

		//Verifying the modules in the select dropdwn	
		Select prod = new Select(driver.findElement(By.xpath("//*[@ng-model='ReadyStake']")));
		Thread.sleep(2000);

		//Looping through the options and printing dropdown options
		System.out.println("The Ready made stake dropdown options are:");
		List actualvalues1 = new ArrayList();
		for(WebElement rows1: prod.getOptions()) {
			actualvalues1.add(rows1.getText());
		}
		List expectedvalues1 = new ArrayList();
		expectedvalues1.add("Your Stake : SRD 5");
		expectedvalues1.add("Your Stake : SRD 20");
		expectedvalues1.add("Your Stake : SRD 50");
		expectedvalues1.add("Your Stake : SRD 100");

		for(int i=0; i<actualvalues1.size(); i++) {
			System.out.println("Actual prod lists :"+ actualvalues1.get(i)+" Expected prod lists : "+ expectedvalues1.get(i));
			Assert.assertTrue(actualvalues1.get(i).equals(expectedvalues1.get(i)));
		}
		Thread.sleep(2000);

		prod.selectByVisibleText("Your Stake : SRD 5");
		System.out.println("Selected 5 SRD as ready made bets");
		Thread.sleep(5000);

		WebElement readybets = driver.findElement(By.xpath("//*[@class='odd-winnings']"));
		Assert.assertTrue(readybets.isDisplayed());

		//Select the ready made bets from available bet selection
		WebElement fovourite = driver.findElement(By.xpath("//*[@ng-repeat='readyodds in readyMadeOdds']")); 
		System.out.println("Favourite bet selection: "+fovourite.getText());
		WebElement fovouriteodds = driver.findElement(By.xpath("//*[@ng-repeat='readyodds in readyMadeOdds']/span[2]"));
		String fodds= fovouriteodds.getText();
		WebElement fovouritestake = driver.findElement(By.xpath("//*[@ng-repeat='readyodds in readyMadeOdds']/span[3]"));
		String fstake= fovouritestake.getText();
		fovouritestake.click();
		Thread.sleep(3000);

		//Validate the same odds and stake amount has been displayed to the user upon selection from the ready made bets 
		WebElement stakeodds = driver.findElement(By.xpath("//*[@class='bet-wrapper']/ul[2]/li[2]"));
		String sodds= stakeodds.getText();
		WebElement stakewin = driver.findElement(By.xpath("//*[@class='bet-wrapper']/ul[2]/li[3]"));
		String swin= stakewin.getText(); 

		//Assert the odds and stake winning from ready made bet a and bet tray details
		Assert.assertEquals(fodds, sodds);
		Assert.assertEquals(fstake, swin);
		System.out.println("Verified the ready made bets options with the stake winning and odds field validations");

	}

	@Then("^Web: Validate the successfull bet placed via ready made bets$")
	public void web_Validate_the_successfull_bet_placed_via_ready_made_bets() throws Throwable {

		Thread.sleep(2000);
		driver.quit();
	}
}