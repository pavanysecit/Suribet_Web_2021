package Web_VirtualKeno;

import java.util.List;
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


public class Web_VirtualKeno_Verify_All_validation_Messages extends Web_VirtualKeno_URL_CardLogin{
WebDriver driver;

	public Web_VirtualKeno_Verify_All_validation_Messages() throws Exception {
		driver = Web_VirtualKeno_URL_CardLogin();
	}

	@Given("^Open the chrome browser and Enter the valid website url link and login to online account$")
	public void open_the_chrome_browser_and_Enter_the_valid_website_url_link_and_login_to_online_account() throws Throwable 
	{
		//System.out.println("<----------------Place the bet when last 30 second left & Canceling the bet after result processed----------------->");
		WebElement drawtime = driver.findElement(By.xpath("//div[@class='counter']/span"));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(drawtime));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);

		//Converting the time period in seconds to for the wait time 
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		//System.out.println("Time duration for the draw to complete "+totaltime1);
		
		if(totaltime<=10)
		{
			TimeUnit.SECONDS.sleep(25);
			WebElement drawtime1 = driver.findElement(By.xpath("//div[@class='counter']/span"));
			String  drtime1 = drawtime1.getText();
			System.out.println("draw left timings1: "+drtime1);

			//Converting the time period in seconds to for the wait time 
			String min1 = drtime1.substring(0, 2);
			int minute1 = Integer.parseInt(min1);
			String sec1 = drtime1.substring(3, 5);
			int seconds1 = Integer.parseInt(sec1);
			int totaltime1 = (minute1*60)+seconds1;
			int wttime1= totaltime1-11;
			Thread.sleep(wttime1*1000);	
			//System.out.println("new wait time: "+ wttime1);
		}
		else
		{
			int wttime= totaltime-11;
			//System.out.println("Wait time duration to clock down till last 1 seconds: "+ wttime1);
			Thread.sleep(wttime*1000);
			//System.out.println("old wait time: "+ wttime);
		}
	
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();   //select Quick pick
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);

		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		//System.out.println("Bet confirmation:" + ActualMsg);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(6000);
		//Thread.sleep(59000);

		//cancel the bet
		driver.findElement(By.xpath("//button[@ng-if='cancelbutton']")).click();
		Thread.sleep(2000);
		String actual=driver.findElement(By.xpath(" //div[@ng-show='cancelbutton']")).getText();		
		String expected="Result Processed! Slip can not be cancelled!!";
		Assert.assertEquals(expected, actual);
		//System.out.println("Cancel Bet once draw start Validation: " + actual);
		Thread.sleep(2000);	
		System.out.println("1. Result Processed! Slip can not be cancelled!: Success");

		driver.findElement(By.xpath("//button[@class='close-btn']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(2000);
	}


	@When("^verify Result Processed! Slip can not be cancelled!$")
	public void verify_Result_Processed_Slip_can_not_be_cancelled() throws Throwable 
	{	
		//System.out.println("<----------------Verifying Invalid Search slip Validation----------------->");			
		driver.findElement(By.xpath("//input[@ng-model='$root.betSlipId']")).click();
		driver.findElement(By.xpath("//input[@ng-model='$root.betSlipId']")).sendKeys("111111111");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@ng-click='globalCtlr.searchSlip($root.betSlipId)']")).click();
		Thread.sleep(3000);

		String Norecords=driver.findElement(By.xpath("//div[@ng-show='elem_InvalidSearch']")).getText();
		String Expected="No Record Found";
		Assert.assertEquals(Norecords, Expected);
		//System.out.println("Invalid Search slip Validation: "+ Norecords);
		Thread.sleep(2000);
		System.out.println("2. Invalid Search slip Validation: No records Found : Success");

		//Validation: Duplicate Bet not allowed : Success
		//System.out.println("<----------------Verifying Duplicate Bet Numbers Validation----------------->");
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][10]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][20]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][30]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][10]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][20]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][30]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(2000);

		String ActDuplicatevalue=driver.findElement(By.xpath("//section[@class='d-flex flex-column flex-grow-1']/div[2]/div[1]")).getText();
		String ExpDuplicatevalue="Duplicate Bet Not Allowed";
		Assert.assertEquals(ExpDuplicatevalue, ActDuplicatevalue);
		//System.out.println("Invalid Search slip Validation: "+ ActDuplicatevalue);
		Thread.sleep(2000);
		System.out.println("3. Duplicate Bet Not Allowed : Success");

		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(2000);

		//System.out.println("<----------------Verifying Place bet without selecting bet number Validation----------------->");
		//Place bet without selecting bet numbers validation: Success
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);
		String notSelectedBet=driver.findElement(By.xpath("//div[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedResult="You have not selected any Bet.";
		Assert.assertEquals(ExpectedResult, notSelectedBet);
		//System.out.println("Validation message is: " + notSelectedBet);
		Thread.sleep(2000);
		System.out.println("4. You have not selected any bet: Success");

		//System.out.println("<----------------Verifying Number of rows in betting slip Validation----------------->");
		//Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[2]")).click();
		Thread.sleep(2000);
		//Choose Betting Numbers
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();   //select Quick pick
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();   //select Quick pick
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(10,DrawNo)']")).click();   //select Quick pick
		Thread.sleep(2000);

		String getNumofrows=driver.findElement(By.xpath("//div[@class='total-stake']/table/tbody/tr[2]/td[1]")).getText();
		String ExpectedRows="3";
		Assert.assertEquals(ExpectedRows, getNumofrows);
		System.out.println("5. The Number of rows are:"+ getNumofrows + ":Success");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(2000);
		
		
		// verify validation: Bet Not Allowed for the draw #106	 & Selected Draw Already Started! : Success
		//System.out.println("<----------------Verify: Bet Not Allowed for current drawID---------------->");
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.SelectedDraws(DrawNumber,StartTime,vrKenoCtrl.CurrentDrawID)']")).click();
		WebElement drawtime2= driver.findElement(By.xpath("//div[@class='counter']/span"));
		String  drtime2 = drawtime2.getText();
		//System.out.println("draw left timings: "+drtime2);

		String s2=driver.findElement(By.xpath("//div[1]/div[1][@class='flex-grow-1 px-1 py-1 py-md-2 text-center']/span")).getText();  //get current drawID (#123)

		//Converting the time period in seconds to for the wait time 
		String mins2 = drtime2.substring(0, 2);
		int minutes2 = Integer.parseInt(mins2);
		String secs2 = drtime2.substring(3, 5);
		int second2 = Integer.parseInt(secs2);
		int totaltime2 = (minutes2*60)+second2;
		//System.out.println("Time duration for the draw to complete "+totaltime1);
		int wttime2= totaltime2-3;
		//System.out.println("Wait time duration to clock down till last 1 seconds: "+ wttime1);
		Thread.sleep(wttime2*1000);

		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();   //select Quick pick
		Thread.sleep(1000);	

		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("5");
		Thread.sleep(12000);

		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);

		String getvalmsg=driver.findElement(By.xpath("//div[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();	//get text for Bet Not allowed for draw#.
		//validation:  get text for draw already started: //div[@ng-if='items.drawid < vrKenoCtrl.CurrentDrawID']
		//System.out.println("The validation message is:" + getvalmsg);
		Thread.sleep(1000);

		String s1="Bet Not Allowed For the Draw";

		String ExpectedRes=s1+s2;  //concatenation
		Thread.sleep(1000);

		Assert.assertEquals(ExpectedRes, getvalmsg);

		System.out.println("6. " + ExpectedRes + ": Success");
		Thread.sleep(2000);

		//click on Remove All to clear the betting slip
		driver.findElement(By.xpath("//span[@ng-click='vrKenoCtrl.RemoveExpireSelection(items.drawid)']")).click();
		Thread.sleep(2000);
		
		//Logout the Application & verify forget link displayed	: Success
		driver.findElement(By.xpath("//a[contains(text(), 'Log Off')]")).click();
		Thread.sleep(5000);
		boolean forgot=driver.findElement(By.xpath("//span[@class='resetBut']")).isDisplayed();
		Assert.assertTrue(forgot);
		Thread.sleep(2000);


		//Without Login Replay button validation	: Success
		// System.out.println("<-----------Verifying Without Login Replay validation-------->");
		driver.findElement(By.xpath("//div[@class='green']")).click();
		Thread.sleep(1000);
		String valmsg2=driver.findElement(By.xpath("//section[@class='d-flex flex-column flex-grow-1']/div[2]/div[1]")).getText();
		String expvalue2="Please Do Login";
		Assert.assertEquals(valmsg2, expvalue2);
		System.out.println("7. Without Login Replay validation: "+ expvalue2 + ": Success");
		Thread.sleep(2000);


		//You must login before place a bet! validation: Success
		//System.out.println("<-----------Verifying Without Login placing bet validation-------->");
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("10");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);

		String valmsg=driver.findElement(By.xpath("//section[@class='d-flex flex-column flex-grow-1']/div[2]/div[1]")).getText();
		String expvalue="You must log in before to place a bet";
		Assert.assertEquals(valmsg, expvalue);
		System.out.println("8. " + valmsg + ": Success");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(2000);	

		//Should not select more than 10 betting numbers: Success
		//System.out.println("<-------------------Select more than 10 betting numbers validation-------------------->");
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][1]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][3]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][4]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][5]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][6]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][7]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][8]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][9]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][10]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][20]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][20]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(2000);

		// get the total count of betting numbers from cart and compare with
		// expected count 10 number.
		List<WebElement> Numbers = driver.findElements(By.xpath("//div[@class='ball selected ng-scope']"));
		//System.out.println("Total Cart numbers are: " + Numbers.size());
		int Actualresultvalue = Numbers.size();
		int ExpectedResult1 = 10;
		Assert.assertEquals(ExpectedResult1, Actualresultvalue);
		System.out.println("9. Total added numbers in cart are: " + Actualresultvalue + ": Success");
		Thread.sleep(2000);

		//CurrentDrawNumber and Time matching: Success
		driver.findElement(By.xpath("//div[@class='d-flex align-items-center justify-content-around p-2']")).click();
		Thread.sleep(1000);
		String currentDrawNo=driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.SelectedDraws(DrawNumber,StartTime,vrKenoCtrl.CurrentDrawID)']/h6")).getText();
		String expectedcurrentdrawNo=currentDrawNo.replaceAll("[^0-9]", "");

		String drawNo=driver.findElement(By.xpath("//section[1]/div[1]/div[1][@class='flex-grow-1 px-1 py-1 py-md-2 text-center']/span")).getText();
		String ActualDrawNo=drawNo.replaceAll("[^0-9]", "");
		Assert.assertEquals(expectedcurrentdrawNo, ActualDrawNo);
		Thread.sleep(1000);

		//Time 
		String currentDrawTime=driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.SelectedDraws(DrawNumber,StartTime,vrKenoCtrl.CurrentDrawID)']/span")).getText();
		String actualCurrentDrawTime=driver.findElement(By.xpath("//section[1]/div[1]/div[2][@class='flex-grow-1 px-1 py-1 py-md-2 text-center']/span")).getText();
		Assert.assertEquals(currentDrawTime, actualCurrentDrawTime);
		//System.out.println("Current Draw Number and Time is:" + drawNo + " " + actualCurrentDrawTime);
		Thread.sleep(3000);
		System.out.println("10. Current Draw and Time verified: Success");

		driver.quit();	
	
	}	
//
//	private ArrayList<Integer> getIntegerArray(List<String> dateList) 
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}



	@Then("^Dublicate Bet Not Allowed$")
	public void dublicate_Bet_Not_Allowed() throws Throwable 
	{

	}

	@Then("^You have not selected any bet$")
	public void you_have_not_selected_any_bet() throws Throwable 
	{

	}

	@Then("^The Selected Bet Number & Number of rows in cart$")
	public void the_Selected_Bet_Number_Number_of_rows_in_cart() throws Throwable
	{

	}

	@Then("^Bet Not Allowed For the DrawID$")
	public void bet_Not_Allowed_For_the_DrawID() throws Throwable 
	{

	}

	@Then("^Without Login Replay validation Please Do Login$")
	public void without_Login_Replay_validation_Please_Do_Login() throws Throwable
	{

	}

	@Then("^You must login before place a bet$")
	public void you_must_login_before_place_a_bet() throws Throwable 
	{

	}

	@Then("^Bet number should not add more than Ten numbers$")
	public void bet_number_should_not_add_more_than_Ten_numbers() throws Throwable 
	{

	}

	@Then("^Current Draw and Time verified$")
	public void current_Draw_and_Time_verified() throws Throwable 
	{

	}
}