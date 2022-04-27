package Web_VirtualSicbo_Old;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.NgWebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VSicbo_Login_Draw_Verifications extends Web_VSicbo_URL_Login {
	private static NgWebDriver ngDriver;
	private static WebDriver driver;
	
	public  Web_VSicbo_Login_Draw_Verifications() throws InterruptedException, MalformedURLException {
		driver = Web_VSicbo_URL_Login();
	}
	
	@Given("^Mobile: Chrome browser, suribet website valid URL, login details, current draw, bet numbers, mimimum bet, stake amount and validation messages$")
	public void mobile_Chrome_browser_suribet_website_valid_URL_login_details_current_draw_bet_numbers_mimimum_bet_stake_amount_and_validation_messages() throws Throwable {
	    boolean bal = driver.findElement(By.id("spnUserBalance")).isDisplayed();
	    System.out.println("Balance is Displayed after Login :" +bal);
		Assert.assertTrue(bal);
		
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
	}

	@When("^Mobile: Login to suribet website with valid login details, Click on Virtual Sicbo module link, cross check current draw , bet numbers and validation message for mimimum bet & stake amount calculations$")
	public void mobile_Login_to_suribet_website_with_valid_login_details_Click_on_Virtual_Sicbo_module_link_cross_check_current_draw_bet_numbers_and_validation_message_for_mimimum_bet_stake_amount_calculations() throws Throwable {
		//comparing current draw No & Time with top table on the right hand side
		String draw = driver.findElement(By.xpath("//div[@class='c_draw_w']/span[1]")).getText();
		System.out.println("Draw No in the left panel:"+draw);
		
		
		String draw1 = driver.findElement(By.xpath("//div[@class='c_draw_w']/span[2]")).getText();
		System.out.println("Draw Time in the left panel:" +draw1);
		
		
		String draw2 = driver.findElement(By.xpath("//div[@class='row3']/div[1]/span[2]")).getText();
		String hash = "#";
		String str3 = hash + draw2;
		System.out.println("Draw No from the header table:"+str3);
				
		String draw3 = driver.findElement(By.xpath("//div[@class='row3']/div[2]/span[2]")).getText();
		System.out.println("Draw Time from the header table:" +draw3);
		
		Assert.assertEquals(draw, str3);
		System.out.println("Current Draw No is matching in both the tables and draw No is :" +draw);
		Assert.assertEquals(draw1, draw3);
		System.out.println("Current Draw Time is matching in both the tables and draw Time is :" +draw3);
		
		//Comparing current time 
		String ctimeT = driver.findElement(By.xpath("//div[@class='row3']/div[3]/span[1]")).getText();
		System.out.println("Time text:" +ctimeT);
		String text = "Current Time";
		Assert.assertEquals(ctimeT, text);
		Thread.sleep(2000);
		String ctime = driver.findElement(By.xpath("//div[@class='row3']/div[3]/span[2]")).getText();
		String ct = ctime.replaceAll(" AM", "");
		String pf = "0";
		String tt = pf+ct;
		//System.out.println("Current Time from the header table:" +ct);
		
		//String ctime1 = driver.findElement(By.xpath("//div[@class='row3']/div[2]/span[3]")).getText();
		System.out.println("Current Time from the header table after split:" +tt);
		
		Actions builder = new Actions(driver);  
		WebElement Element = driver.findElement(By.id("spnrunningtime")); 
		builder.moveToElement(Element).click().build().perform();
		String str90 = Element.getText();
		str90 = str90.replaceAll(".+ ", "");
		str90 = str90.substring(0, str90.length()-3);
		System.out.println("Current Time from the bottom table:" +str90);
		
		Assert.assertEquals(tt, str90);
	}

	@Then("^Mobile:  User should logged-in successfully to Virtual Sicbo module$")
	public void mobile_User_should_logged_in_successfully_to_Virtual_Sicbo_module() throws Throwable {
		WebElement smallb = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		smallb.click();
		Thread.sleep(1000);
		WebElement bigb = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		bigb.click();
		Thread.sleep(1000);
		WebElement odd = driver.findElement(By.xpath("//*[ text() = ' Odd ']"));
		odd.click();
		Thread.sleep(1000);
		WebElement even = driver.findElement(By.xpath("//*[ text() = ' Even ']"));
		even.click();
		Thread.sleep(1000);
	}

	@Then("^Mobile: System should show Current Draw#, Date, Time in the Virtual Sicbo module$")
	public void mobile_System_should_show_Current_Draw_Date_Time_in_the_Virtual_Sicbo_module() throws Throwable {
		String bet1 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 1st row of betting slip: "+bet1);
		String expected1 = "even";
		Assert.assertEquals(bet1, expected1);
		Thread.sleep(1000);

		String bet2 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[2]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 2nd row of betting slip: "+bet2);
		String expected2 = "odd";
		Assert.assertEquals(bet2, expected2);
		Thread.sleep(1000);
		
		String bet3 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[3]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 3rd row of betting slip: "+bet3);
		String expected3 = "big";
		Assert.assertEquals(bet3, expected3);
		Thread.sleep(1000);
		
		String bet4 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[4]/div[1]/span[1]")).getText();
		System.out.println("Selected bet type available in the 4th row of betting slip: "+bet4);
		String expected4 = "small";
		Assert.assertEquals(bet4, expected4);
		Thread.sleep(1000);
	}

	@Then("^Mobile: Selected Draw No#, Time and betting numbers should be display on the Virtual Sicbo module screen$")
	public void mobile_Selected_Draw_No_Time_and_betting_numbers_should_be_display_on_the_Virtual_Sicbo_module_screen() throws Throwable {
		//checking validation message for placing a bet without minimum bet
		WebElement stake = driver.findElement(By.xpath("//input[@type='number']"));
		stake.sendKeys("0");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(1000);
				
		String vmsg = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
		System.out.println("Message displayed for entering the stake amount as 0: " +vmsg);
		String expected5 = "Stake should be greater than minimum bet amount.";
		Assert.assertEquals(vmsg, expected5);
	}

	@Then("^Mobile: Validation message for placing bet without login to the website$")
	public void mobile_Validation_message_for_placing_bet_without_login_to_the_website() throws Throwable {
		//checking validation message for placing a bet without login
	   driver.findElement(By.linkText("Log Off")).click();
	   Thread.sleep(3000);
	   
	   WebElement stake1 = driver.findElement(By.xpath("//input[@type='number']"));
	   stake1.sendKeys("1");
	   Thread.sleep(1000);
	   driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
	   Thread.sleep(1000);
	   
	   String vmsg1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
	   System.out.println("Message displayed for placing bet without login: " +vmsg1);
	   String expected7 = "Please login";
	   Assert.assertEquals(vmsg1, expected7);
	   
	  //Comparing the expected winning amount on each bet number
		String ewin = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[1]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'even' is: " +ewin);
		String expected10 = "Win : 2";
		Assert.assertEquals(ewin, expected10);
		
		String ewin1 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[2]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'odd' is: " +ewin1);
		String expected11 = "Win : 2";
		Assert.assertEquals(ewin1, expected11);
		
		String ewin2 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[3]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'big' is: " +ewin2);
		String expected12 = "Win : 2";
		Assert.assertEquals(ewin2, expected12);
		
		String ewin3 = driver.findElement(By.xpath("//div[@class='sicboSlips']/div[4]/div[1]/div[3]")).getText();
		System.out.println("Expected win amount for betting number 'small' is: " +ewin3);
		String expected13 = "Win : 2";
		Assert.assertEquals(ewin3, expected13);
		
		//Comparing the default minimum stake amount displayed on each bet number 
		String estake = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-3]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'even' is: " +estake);
		String expected14 = "1";
		Assert.assertEquals(estake, expected14);
		
		String estake1 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-2]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'odd' is: " +estake1);
		String expected15 = "1";
		Assert.assertEquals(estake1, expected15);
		
		String estake2 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()-1]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'big' is: " +estake2);
		String expected16 = "1";
		Assert.assertEquals(estake2, expected16);
		
		String estake3 = driver.findElement(By.xpath("(//input[@ng-model='a.totalA'])[last()]")).getAttribute("value");
		System.out.println("Default Stake amount displayed on the bet type 'small' is: " +estake3);
		String expected17 = "1";
		Assert.assertEquals(estake3, expected17);
		
		//Checking validation message for entering max bet
		WebElement stake11 = driver.findElement(By.xpath("//input[@type='number']"));
		stake11.clear();
		stake11.sendKeys("999");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();
		Thread.sleep(1000);
		String maxbet = driver.findElement(By.xpath("/html/body/div[3]/div[1]/ui-view/section/aside[2]/div/div/div[2]")).getText();
		System.out.println("Validation message for entering maximum bet amount: " +maxbet);
		String expected18 = "Stake should be less than maximum bet amount.";
		Assert.assertEquals(maxbet, expected18);
		
	}

	@Then("^Mobile: Stake amount should automatically displayed on the Virtual Sicbo betting slip$")
	public void mobile_Stake_amount_should_automatically_displayed_on_the_Virtual_Sicbo_betting_slip() throws Throwable {
	   Thread.sleep(2000);
	   driver.quit();
	}
}