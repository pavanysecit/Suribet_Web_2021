package Web_VirtualSkinfiri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualSkinfiri_Draw_BetNumbers_BetAmount_Verifications extends Web_VirtualSkinfiri_URL{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, current draw, bet numbers, mimimum bet, stake amount and validation messages$")
	public void web_Chrome_browser_suribet_website_valid_URL_current_draw_bet_numbers_mimimum_bet_stake_amount_and_validation_messages() throws Throwable {
		driver = Web_VirtualSkinfiri_URL();
		Thread.sleep(2000);
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, cross check current draw , bet numbers and validation message for mimimum bet & stake amount calculations$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualSkinfiri_module_link_cross_check_current_draw_bet_numbers_and_validation_message_for_mimimum_bet_stake_amount_calculations() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));

		/*
		 * As the draw num and draw time is binded within the canvas 
		 * from the automation inspection it is not able to inspect the elements within the canvas frame
		 * Once the legacy design is upgraded to latest build will verify the following below scenarios
		 * 
		 *  after login and place bet 
		 *  other msg is prompted to user 
		 *  "Another product betting is under progress, please try after few mins!"
		 *   Once resolved have to verify 
		 */

		//Verify the current draw with time and date verification
		//		WebElement cdrawnum = driver.findElement(By.xpath("//div[@class='c_draw_w']/span/span")); 
		//		WebElement cdrawtime = driver.findElement(By.xpath("//div[@class='c_draw_w']/span[2]"));   
		//		
		//		WebElement cdrawnum1 = driver.findElement(By.xpath("//div[@class='c_draw_idTime_W']/div/span/label")); 
		//		WebElement cdrawtime1 = driver.findElement(By.xpath("//div[@class='c_draw_idTime_W']/div/span[2]/label"));
		//		
		//		Assert.assertEquals(cdrawnum.getText().substring(1), cdrawnum1.getText());
		//		Assert.assertEquals(cdrawtime.getText() , cdrawtime1.getText());
		//		System.out.println("Current draw is verified on both the column selections");

		//clicking on Draw details to place bet for the current draw 
		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[6]"));
		DrawNO.click();
		Thread.sleep(2000);

		//		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]"));
		//		WebElement DrawNum = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]/span")); 
		//		WebElement DrawTime = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[6]/span[2]")); 

		//		DrawNO.click();
		//		Thread.sleep(2000);
		//		String Ddetails = DrawNum.getText();
		//		System.out.println(Ddetails);
		//		String ddetails = Ddetails.replaceAll("\\s", "");

		//		Assert.assertEquals(ddetails.substring(5), cdrawnum1.getText());
		//		//Verify the selected draw is updated in the 
		//		Assert.assertEquals(DrawTime.getText(), cdrawtime1.getText());
		//		System.out.println("Next draw is updated in the draw selection column");
		//		Thread.sleep(2000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[4]"));
		WebElement Pick6 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[6]"));
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[1]"));

		Pick3.click();
		Thread.sleep(1000);
		Pick6.click();
		Thread.sleep(1000);
		Pick2.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the number table");

		//Input the stake amount 
		WebElement stkfield = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeTotalFun()']")); 
		stkfield.click();
		Thread.sleep(1000);
		stkfield.sendKeys("0.9");
		Thread.sleep(2000);

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.PlaceBet(false)']"));
		String fwd =Forward.getText();
		String fwd_actual = "FORWARD";
		Assert.assertEquals(fwd_actual, fwd);
		System.out.println("Forward text is verified:"+ fwd);

		WebElement Clear = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.fn_btnClear()']"));
		String clr =Clear.getText();
		String clr_actual = "CLEARALL";
		Assert.assertEquals(clr_actual, clr);
		System.out.println("Clear text is verified:"+ clr);

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		String Vmsg = Validmsg.getText();
		System.out.println("Placed bet successful user message displayed to user: "+ Vmsg);
		Assert.assertEquals("You must log in before to place a bet", Vmsg);

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
		Thread.sleep(7000);

		Forward.click();
		Thread.sleep(2000);

//		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.PlaceBet(false)']"));
//		String cfm =Confirm.getText();
//		String cfm_actual = "CONFIRM";
//		Assert.assertEquals(cfm_actual, cfm);
//		System.out.println("Confirm text is verified :"+ cfm);
//
//		WebElement back = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.fn_btnClear()']"));
//		String bck =back.getText();
//		String bck_actual = "BACK";
//		Assert.assertEquals(bck_actual, bck);
//		System.out.println("Confirm text is verified :"+ bck);
//
//		Confirm.click();
//		Thread.sleep(3000);

		//Check for changing stake value less than 1 and more than 500 and vrify the min and max stake for each bet placed
		WebElement limitmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		boolean lmsg = limitmsg.getText().contains("Stakes with cents are not allowed");
		System.out.println("Lower stake less than 1SRD: "+ lmsg);
		Assert.assertTrue(lmsg);
		System.out.println("lower limit "+limitmsg.getText());


		//Input the stake amount 
		WebElement stkfield1 = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeTotalFun()']")); 
		stkfield1.click();
		Thread.sleep(1000);
		//		stkfield.sendKeys("0.9");
		//		Thread.sleep(2000);

		//Enter the stake as 501 and check the updated stake
		WebElement five = driver.findElement(By.xpath("(//li[@ng-click='VDGCtr.numkeypadfn(num)'])[5]"));
		five.click();
		Thread.sleep(1000);
		WebElement zero = driver.findElement(By.xpath("(//li[@ng-click='VDGCtr.numkeypadfn(num)'])[10]"));
		WebElement dot = driver.findElement(By.xpath("(//li[@ng-click='VDGCtr.numkeypadfn(num)'])[11]"));
		WebElement one = driver.findElement(By.xpath("(//li[@ng-click='VDGCtr.numkeypadfn(num)'])[1]"));

		//Check for upper circuit stake limit 
		five.click();
		Thread.sleep(1000);
		zero.click();
		Thread.sleep(1000);
		zero.click();
		Thread.sleep(1000);
		dot.click();
		Thread.sleep(1000);
		one.click();
		Thread.sleep(1000);

		boolean upperlimit = limitmsg.getText().contains("The bet amount exceeds the maximum limit of SRD 500!");
		System.out.println("Upper stake limit validation message is displayed to user: "+ upperlimit);
		Assert.assertTrue(upperlimit);
		System.out.println("upper limit "+limitmsg.getText());

		//To verify the current time in the skinfiri site to the current time in the suriname
		//displaying this date on UTC timezone
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		df.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC = df.format(today);
		System.out.println("Date in Suriname Timezone (UTC-3) : " + UTC);

		//time format changing
		Date today1 = new Date();
		DateFormat df1 = new SimpleDateFormat("HH:mm aa");
		df1.setTimeZone(TimeZone.getTimeZone("America/Paramaribo"));
		String UTC1 = df1.format(today1);
		System.out.println("Current Time in Suriname Timezone (UTC-3) : " + UTC1);

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

	@Then("^Web:  System should show Current Draw#, Date, Time in the VirtualSkinfiri module$")
	public void web_System_should_show_Current_Draw_Date_Time_in_the_VirtualSkinfiri_module() throws Throwable {
	    
	}

	@Then("^Web: Selected Draw No#, Time on the VirtualSkinfiri module screen$")
	public void web_Selected_Draw_No_Time_on_the_VirtualSkinfiri_module_screen() throws Throwable {
	    
	}

	@Then("^Web: Validation message for placing bet without login to the website$")
	public void web_Validation_message_for_placing_bet_without_login_to_the_website() throws Throwable {
	    
	}

	@Then("^Web: System should not allow user to enter the stake amount in whole numbers, It should be autocalculated in VirtualSkinfiri module$")
	public void web_System_should_not_allow_user_to_enter_the_stake_amount_in_whole_numbers_It_should_be_autocalculated_in_VirtualSkinfiri_module() throws Throwable {
	   Thread.sleep(3000);
	   driver.quit();
	}
}