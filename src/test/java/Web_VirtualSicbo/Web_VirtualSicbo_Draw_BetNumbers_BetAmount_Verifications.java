package Web_VirtualSicbo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualSicbo_Draw_BetNumbers_BetAmount_Verifications extends Web_VirtualSicbo_URL{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, current draw, bet numbers, mimimum bet, stake amount and validation messages$")
	public void web_Chrome_browser_suribet_website_valid_URL_current_draw_bet_numbers_mimimum_bet_stake_amount_and_validation_messages() throws Throwable {
		driver = Web_VirtualSicbo_URL();
		Thread.sleep(2000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSicbo module link, cross check current draw , bet numbers and validation message for mimimum bet & stake amount calculations$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSicbo_module_link_cross_check_current_draw_bet_numbers_and_validation_message_for_mimimum_bet_stake_amount_calculations() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));

		//Verify the current draw with time and date verification
		WebElement cdrawnum = driver.findElement(By.xpath("//div[@class='c_draw_w']/span/span")); 
		WebElement cdrawtime = driver.findElement(By.xpath("//div[@class='c_draw_w']/span[2]"));   
		
		WebElement cdrawnum1 = driver.findElement(By.xpath("//div[@class='row3']/div[1]/span[2]")); 
		WebElement cdrawtime1 = driver.findElement(By.xpath("//div[@class='row3']/div[2]/span[2]"));
		
		Assert.assertEquals(cdrawnum.getText().substring(1), cdrawnum1.getText());
		Assert.assertEquals(cdrawtime.getText() , cdrawtime1.getText());
		System.out.println("Current draw is verified on both the column selections");
		
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]"));
		WebElement DrawNum = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]/span")); 
		WebElement DrawTime = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]/span[2]")); 
		
		DrawNO.click();
		Thread.sleep(2000);
		String Ddetails = DrawNum.getText();
		System.out.println(Ddetails);
		String ddetails = Ddetails.replaceAll("\\s", "");
		
		Assert.assertEquals(ddetails.substring(6), cdrawnum1.getText());
		//Verify the selected draw is updated in the 
		Assert.assertEquals(DrawTime.getText(), cdrawtime1.getText());
		System.out.println("Next draw is updated in the draw selection column");
		//		
		//		WebElement DrawDetails = driver.findElement(By.className("c_draw_idTime_W"));
		//		String Drwdetails = DrawDetails.getText();
		//		String myString = Drwdetails.replaceAll("\n","");
		//		System.out.println(myString);
		//		Assert.assertEquals("Draw No163Draw Time8:10AMCurrent Time7:34 AM", Ddetails);
		//		System.out.println(Drwdetails);

		Thread.sleep(2000);

		//Selelct the Numbers from the Sicbo Board to place the bet 
		WebElement betType1 = driver.findElement(By.xpath("//*[ text() = 'Small 4-10']"));
		WebElement betType2 = driver.findElement(By.xpath("//*[ text() = 'Big 11-17']"));
		
		betType1.click();
		Thread.sleep(1000);
		betType2.click();
		Thread.sleep(1000);


		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.PlaceBetDetails(false)']"));
		String fwd =Forward.getText();
		String fwd_actual = "FORWARD";
		Assert.assertEquals(fwd_actual, fwd);
		System.out.println("Forward text is verified:"+ fwd);

		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.cartClearFun()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEAR ALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);




		//login with valid credentials
		UN.click();
		Thread.sleep(1000);
		UN.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("mansoor@123");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(2000);

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALSICBO
		Forward.click();
		//driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.PlaceBetDetails(false)']")).click();
		Thread.sleep(2000);

		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.confirm()']"));
		String cfm =Confirm.getText();
		String cfm_actual = "CONFIRM";
		Assert.assertEquals(cfm_actual, cfm);
		System.out.println("Confirm text is verified :"+ cfm);

		WebElement back = driver.findElement(By.xpath("//div[@ng-click='globalsicBoCtlr.goBack()']"));
		String bck =back.getText();
		String bck_actual = "BACK";
		Assert.assertEquals(bck_actual, bck);
		System.out.println("Confirm text is verified :"+ bck);

		back.click();
		Thread.sleep(2000);
		
		//Check for changing stake value less than 1 and more than 500 and vrify the min and max stake for each bet placed
		//Enter the stake as 0.8 and check the updated stake
		WebElement stake = driver.findElement(By.xpath("//input[@ng-change='globalsicBoCtlr.PlaceCommonStake(globalsicBoCtlr.CommonStake)']"));
		stake.click();
		Thread.sleep(1000);
		
		WebElement zero = driver.findElement(By.xpath("(//li[@ng-click='globalsicBoCtlr.numkeypadfn(num)'])[10]"));
		zero.click();
		Thread.sleep(1000);
//		WebElement dot = driver.findElement(By.xpath("(//li[@ng-click='globalsicBoCtlr.numkeypadfn(num)'])[11]"));
//		dot.click();
//		Thread.sleep(1000);
//		WebElement two = driver.findElement(By.xpath("(//li[@ng-click='globalsicBoCtlr.numkeypadfn(num)'])[12]"));
//		two.click();
//		Thread.sleep(1000);

		driver.findElement(By.xpath("//i[@ng-click='globalsicBoCtlr.closeKeypad()']")).click();	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[ text() = 'FORWARD']")).click();	
		Thread.sleep(1000);
		
		WebElement limitmessage = driver.findElement(By.xpath("//div[@class='e_mgs ng-binding']"));
		System.out.println("lower limit "+limitmessage.getText());
		Assert.assertEquals("Stake should be greater than minimum bet amount.", limitmessage.getText());

		//To verify the current time in the Sicbo site to the current time in the suriname

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

		WebElement CurrentTime = driver.findElement(By.xpath("//div[@class='row3']/div[3]/span[2]"));
		System.out.println("Current time in Sicbo module: "+ CurrentTime.getText());
		String ctime = null;
		if(CurrentTime.getText().length()>=8) {
			ctime=CurrentTime.getText();
		}else {
			ctime="0"+CurrentTime.getText();
		}

		WebElement FooterTime = driver.findElement(By.id("spnrunningtime"));
		System.out.println("Current time in Sicbo module footer: "+ FooterTime.getText());
		StringBuilder foottime = new StringBuilder(FooterTime.getText());
		foottime.replace(17,20,"");
		System.out.println(foottime.toString());

		Assert.assertEquals(UTC, foottime.toString());
		Assert.assertEquals(UTC1, ctime);
		System.out.println("Current Time is matched with the timezone");
	}

	@Then("^Web:  System should show Current Draw#, Date, Time in the VirtualSicbo module$")
	public void web_System_should_show_Current_Draw_Date_Time_in_the_VirtualSicbo_module() throws Throwable {
	    
	}

	@Then("^Web: Selected Draw No#, Time on the VirtualSicbo module screen$")
	public void web_Selected_Draw_No_Time_on_the_VirtualSicbo_module_screen() throws Throwable {
	   
	}

	@Then("^Web: Validation message for placing bet without login to the website$")
	public void web_Validation_message_for_placing_bet_without_login_to_the_website() throws Throwable {
	   
	}

	@Then("^Web: System should not allow user to enter the stake amount in whole numbers, It should be autocalculated in VirtualSicbo module$")
	public void web_System_should_not_allow_user_to_enter_the_stake_amount_in_whole_numbers_It_should_be_autocalculated_in_VirtualSicbo_module() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}