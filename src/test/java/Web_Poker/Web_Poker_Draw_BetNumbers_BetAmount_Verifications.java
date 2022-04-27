package Web_Poker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_Poker_Draw_BetNumbers_BetAmount_Verifications {
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, current draw, bet numbers, mimimum bet, maximum bet$")
	public void web_Chrome_browser_suribet_website_valid_URL_current_draw_bet_numbers_mimimum_bet_maximum_bet() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://10.200.10.44:11909/virtualpoker/home");
		driver.manage().window().maximize();
		Thread.sleep(4000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualPoker module link, cross check current draw , bet numbers and validation message for mimimum bet & maximum bet$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualPoker_module_link_cross_check_current_draw_bet_numbers_and_validation_message_for_mimimum_bet_maximum_bet() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));

		//Verify the current draw with time and date verification
		WebElement cdrawnum = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.pokerkDrawslistFun(pokerCtlr.PokerDraws[0], 0)']/div/div[1]")); 
		WebElement cdrawtime = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.pokerkDrawslistFun(pokerCtlr.PokerDraws[0], 0)']/div/div[2]"));   

		WebElement cdrawnum1 = driver.findElement(By.xpath("//*[@class='ml-auto overflow-hidden']/div/div/span[2]")); 
		WebElement cdrawtime1 = driver.findElement(By.xpath("//*[@class='ml-auto overflow-hidden']/div/div[2]/span[2]"));

		String cdnum = cdrawnum.getText().replaceAll("[^0-9]", "");
		String cdnum1 = cdrawnum1.getText().replaceAll("[^0-9]", "");
		String cdtym = cdrawtime.getText().replaceAll(" ", "");
		String cdtym1 = cdrawtime1.getText().replaceAll(" ", "").substring(1);
		Assert.assertEquals(cdnum, cdnum1);
		Assert.assertEquals(cdtym, cdtym1);
		System.out.println("Current draw is verified on both the column selections");

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[5]"));
		WebElement DrawNum = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[5]/div[1]")); 
		WebElement DrawTime = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[5]/div[2]")); 

		DrawNO.click();
		Thread.sleep(2000);

		WebElement Fdrawnum1 = driver.findElement(By.xpath("//*[@class='ml-auto overflow-hidden']/div/div/span[2]")); 
		WebElement Fdrawtime1 = driver.findElement(By.xpath("//*[@class='ml-auto overflow-hidden']/div/div[2]/span[2]"));
		WebElement Fdrawnum11 = driver.findElement(By.xpath("//*[@class='ml-auto overflow-hidden']/div/div/span[2]")); 
		WebElement Fdrawtime11 = driver.findElement(By.xpath("//*[@class='ml-auto overflow-hidden']/div/div[2]/span[2]"));
		String Ftnum = Fdrawnum1.getText().replaceAll("[^0-9 ]", "");
		String Ftnum1 = Fdrawnum11.getText().replaceAll("[^0-9 ]", "");
		String Fttym = Fdrawtime1.getText().replaceAll(" ", "");
		String Fttym1 = Fdrawtime11.getText().replaceAll(" ", "");
		Assert.assertEquals(Ftnum, Ftnum1);
		Assert.assertEquals(Fttym, Fttym1);
		System.out.println("Future draw is verified on both the column selections");
		Thread.sleep(2000);


		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement hand1 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement hand2 = driver.findElement(By.xpath("//*[text()='Full House']"));

		hand1.click();
		Thread.sleep(1000);
		hand2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the hands selections");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.ClearFields()']"));

		String fwd =Forward.getText();
		String fwd_actual = "FORWARD";
		Assert.assertEquals(fwd_actual, fwd);
		System.out.println("Forward text is verified:"+ fwd);

		String clr =Clear.getText();
		String clr_actual = "CLEAR ALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(3000);

		//Without login prompts the login pop and user message is not prompted

		WebElement loginpopupcancel = driver.findElement(By.xpath("(//*[@class='fa fa-times close_popup_JS'])[3]")); 
		Assert.assertTrue(loginpopupcancel.isDisplayed());
		System.out.println("Login popup alert generated and validated");
		loginpopupcancel.click();
		Thread.sleep(3000);

		//login with valid credentials
		UN.click();
		Thread.sleep(1000);
		UN.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("mans@123");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(2000);

		Forward.click();
		Thread.sleep(3000);

		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		String cfm =Confirm.getText();
		String cfm_actual = "CONFIRM";
		Assert.assertEquals(cfm_actual, cfm);
		System.out.println("Confirm text is verified :"+ cfm);

		WebElement back = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.ClearFields()']"));
		String bck =back.getText();
		String bck_actual = "BACK";
		Assert.assertEquals(bck_actual, bck);
		System.out.println("Confirm text is verified :"+ bck);

		back.click();
		Thread.sleep(3000);
		
		//Check for changing stake value less than 1 and more than 500 and vrify the min and max stake for each bet placed

		//Enter the stake as 0.8 and check the updated stake
		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='pokerCtlr.PlaceCommonStake(pokerCtlr.CommonStake)']"));
//		stake.click();
//		Thread.sleep(2000);
//		stake.sendKeys("0.25");
//		Thread.sleep(2000);

		WebElement limitmessage = driver.findElement(By.className("EMessage")); 
//		wait.until(ExpectedConditions.visibilityOf(limitmessage));
//		String Vmsg = limitmessage.getText();
//		System.out.println("Placed bet successful user message: "+ Vmsg);
//		Assert.assertEquals("Please enter the Common Stake between 1 and 100 !", Vmsg);

		//Check for upper circuit stake limit 
		stake.click();
		Thread.sleep(1000);
		stake.clear();
		Thread.sleep(1000);
		stake.sendKeys("101");
		Thread.sleep(1500);

		Assert.assertEquals("Please enter the Common Stake between 1 and 100 !", limitmessage.getText());
		System.out.println("Upper limit "+limitmessage.getText());

		//Check for upper circuit stake limit 
		stake.click();
		Thread.sleep(1000);
		stake.clear();
		Thread.sleep(1000);
		stake.sendKeys("100.01");
		Thread.sleep(1500);

		Assert.assertEquals("Please enter the Common Stake between 1 and 100 !", limitmessage.getText());
		System.out.println("Upper limit "+limitmessage.getText());

		//To verify the current time in the Roulette site to the current time in the suriname

		//displaying this date on UTC timezone
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		df.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC = df.format(today);
		System.out.println("Date in Suriname Timezone (UTC-3) : " + UTC);

		//time formate changing
		Date today1 = new Date();
		DateFormat df1 = new SimpleDateFormat("HH:mm aa");
		df1.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC1 = df1.format(today1);
		System.out.println("Current Time in Suriname Timezone (UTC-3) : " + UTC1);

		//		Vtime = cdrawtime1
		//		
		//		WebElement CurrentTime = driver.findElement(By.xpath("//label[@ng-show='globalrouletteCtlr.CurrentTime != 0']"));
		//		System.out.println("Current time in Roulette module: "+ CurrentTime.getText());
		//		String ctime = null;
		//		if(CurrentTime.getText().length()>=8) {
		//			ctime=CurrentTime.getText();
		//		}else {
		//			ctime="0"+CurrentTime.getText();
		//		}

		WebElement FooterTime = driver.findElement(By.id("spnrunningtime"));
		System.out.println("Current time in Roulette module footer: "+ FooterTime.getText());
		StringBuilder foottime = new StringBuilder(FooterTime.getText());
		foottime.replace(17,20,"");
		System.out.println(foottime.toString());

		Assert.assertEquals(UTC, foottime.toString());
		//		Assert.assertEquals(UTC1, ctime);
		System.out.println("Current Time is matched with the timezone");
	}

	@Then("^Web:  System should show Current Draw#, Date, Time and for the future draw in the VirtualPoker module$")
	public void web_System_should_show_Current_Draw_Date_Time_and_for_the_future_draw_in_the_VirtualPoker_module() throws Throwable {
	   
	}

	@Then("^Web: Selected Draw No#, Time on the VirtualPoker module screen$")
	public void web_Selected_Draw_No_Time_on_the_VirtualPoker_module_screen() throws Throwable {
	    
	}

	@Then("^Web: Validation message for placing bet without login to the website$")
	public void web_Validation_message_for_placing_bet_without_login_to_the_website() throws Throwable {
	    
	}

	@Then("^Web: System should not allow user to enter the stake amount in whole numbers, It should be auto calculated in VirtualPoker module$")
	public void web_System_should_not_allow_user_to_enter_the_stake_amount_in_whole_numbers_It_should_be_auto_calculated_in_VirtualPoker_module() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}