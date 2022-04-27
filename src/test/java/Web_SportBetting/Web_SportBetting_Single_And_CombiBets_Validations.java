package Web_SportBetting;

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


public class Web_SportBetting_Single_And_CombiBets_Validations extends Web_SportBetting_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_SportBetting_Single_And_CombiBets_Validations() throws Exception {
		driver = Web_SportBetting_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, sports betting module,login via online method, upcoming matches, bets, single and combi bet options, stake fields$")
	public void web_Chrome_browser_suribet_website_valid_URL_sports_betting_module_login_via_online_method_upcoming_matches_bets_single_and_combi_bet_options_stake_fields() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Place bet from single game and verify the stake field can be updated for each bet placed$")
	public void web_Place_bet_from_single_game_and_verify_the_stake_field_can_be_updated_for_each_bet_placed() throws Throwable {

		// Fetch the account balance 
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		String mbal = Mbal.getText();
		System.out.println("Main balance after login to the VirtualRoulette: "+ mbal);

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		WebElement upmatch1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]"));
		WebElement upmatch2 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[3]"));

		upmatch.click();
		Thread.sleep(2000);
		upmatch1.click();
		Thread.sleep(2000);
		upmatch2.click();
		Thread.sleep(2000);

		WebElement stake1 = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeValueFun()']")); 
		WebElement stake2 = driver.findElement(By.xpath("(//*[@ng-change='globalSportsCtlr.stakeValueFun()'])[2]")); 
		WebElement stake3 = driver.findElement(By.xpath("(//*[@ng-change='globalSportsCtlr.stakeValueFun()'])[3]")); 

		stake1.click();
		Thread.sleep(1000);
		stake1.sendKeys("5");
		Thread.sleep(2000);
		stake2.click();
		Thread.sleep(1000);
		stake2.sendKeys("7");
		Thread.sleep(2000);
		stake3.click();
		Thread.sleep(1000);
		stake3.sendKeys("5");
		Thread.sleep(2000);

		WebElement stakefield = driver.findElement(By.xpath("(//*[@ng-show='globalSportsCtlr.singlebetshow'])[4]/ul[2]/li[2]")); 
		Assert.assertEquals("17.00", stakefield.getText());
		System.out.println("SIngle type bet placing and individual bet placing");
		WebElement Singlebet = driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.singlebetshowFun()']")); 
		Assert.assertEquals("bet-btn btn-combi-bet btn-single-bet position-relative w-100 font-weight-bold ng-binding sb_cart_selected", Singlebet.getAttribute("class")); 
		System.out.println("Single bet validations verified");
		driver.findElement(By.xpath("//*[@ng-class='{disableClass:globalSportsCtlr.disableforward}']")).click();
		Thread.sleep(2000);


		//Verification for combibet placing 
		WebElement cupmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[25]/span[2]"));
		WebElement cupmatch1 = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[2]"));

		cupmatch.click();
		Thread.sleep(2000);
		cupmatch1.click();
		Thread.sleep(2000);

		WebElement combibet = driver.findElement(By.xpath("//*[@ng-click='globalSportsCtlr.combibetshowFun()']")); 
		Assert.assertEquals("bet-btn btn-combi-bet position-relative w-100 font-weight-bold ng-binding sb_cart_selected", combibet.getAttribute("class")); 
		System.out.println("Combi bet validations verified");

		DecimalFormat df = new DecimalFormat("#.##");

		//Total odds calculations
		WebElement odd1 = driver.findElement(By.xpath("(//*[@class='points heigth-outright ng-binding'])[1]"));  
		WebElement odd2 = driver.findElement(By.xpath("(//*[@class='points heigth-outright ng-binding'])[2]"));
		String odds1 = odd1.getText().replaceAll(",", "");
		double od1 =Double.parseDouble(odds1);       
		double oddss1 = Double.valueOf(df.format(od1));

		String odds2 = odd2.getText().replaceAll(",", "");
		double od2 =Double.parseDouble(odds2); 
		double oddss2 = Double.valueOf(df.format(od2));
		double Totalodds= oddss1*oddss2;
		double Totalodds1 = Double.valueOf(df.format(Totalodds));
		System.out.println("Total odds while placing combi bet: "+ Totalodds1);


		WebElement todds = driver.findElement(By.xpath("(//ul[@class='header-rt d-flex'])[2]/../ul[2]/li[2]"));
		String todds1 = todds.getText().replaceAll(",", "");
		double tod1 =Double.parseDouble(todds1); 
		double tood1 = Double.valueOf(df.format(tod1));
		System.out.println("Total odd generated from the system: "+ tood1);
		Assert.assertEquals(Totalodds1, tood1, 0.00);
		System.out.println("Total odds calculation is verified");

		WebElement combistake = driver.findElement(By.xpath("//*[@ng-model='globalSportsCtlr.combistakeTotal']"));
		combistake.click();
		Thread.sleep(1000); 
		combistake.sendKeys("7");
		Thread.sleep(2000);  

		WebElement winningamount = driver.findElement(By.xpath("(//ul[@class='header-rt d-flex'])[2]/../ul[2]/li[3]"));
		String winexp = winningamount.getText().replaceAll(",", "");
		double wexp =Double.parseDouble(winexp); 
		double exwinn = Double.valueOf(df.format(wexp));
		System.out.println("Total expected win amount: "+ exwinn);

		double  Dou = Double.parseDouble("7");
		double exwin= Totalodds*Dou;
		double exwinning = Double.valueOf(df.format(exwin));

		Assert.assertEquals(exwinning, exwinn, 0.00);
		System.out.println("Total odds calculation is verified");

		
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
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(5000);

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
	}

	@When("^Web: Place bet from different game and verify the stake field can be updated in single fields for all game selection$")
	public void web_Place_bet_from_different_game_and_verify_the_stake_field_can_be_updated_in_single_fields_for_all_game_selection() throws Throwable {

	}

	@Then("^Web: Validate the placing bet for single and combi bet and stake field updating in Sport betting module$")
	public void web_Validate_the_placing_bet_for_single_and_combi_bet_and_stake_field_updating_in_Sport_betting_module() throws Throwable {
		driver.quit();
	}
}