package Web_VirtualKeno;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_MinMaxBet_ClearBet_Undo_QuickPicks_NextDrawNumTime_SearchSlip_LastWin_Timelapse extends Web_VirtualKeno_URL_OnlineLogin{
WebDriver driver;
	
	public Web_VirtualKeno_MinMaxBet_ClearBet_Undo_QuickPicks_NextDrawNumTime_SearchSlip_LastWin_Timelapse() throws Exception {
		driver =Web_VirtualKeno_URL_OnlineLogin();
	}

	@Given("^Open the chrome browser, Enter the valid website url and login to online account$")
	public void open_the_chrome_browser_Enter_the_valid_website_url_and_login_to_online_account() throws Throwable 
	{

	}

	@When("^Verify MinMaxBet, ClearBet, Undo, QuickPicks, NextDrawNumberTime$")
	public void verify_MinMaxBet_ClearBet_Undo_QuickPicks_NextDrawNumberTime() throws Throwable 
	{
		System.out.println("<------------Verify Mute Button, MinMaxBet, ClearBet, Undo, QuickPicks, NextDrawNumber and Time------------>");	
		driver.findElement(By.xpath("//section[@class='action-section d-flex justify-content-center']/div/span/i")).click();  //Mute button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='fa  fa-volume-off text-danger']")).isSelected();
		System.out.println("Audio/Volume is muted successfully");
		Thread.sleep(1000);
		
		// Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		// Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][58]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][59]")).click();
		//driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][60]")).click();
		Thread.sleep(1000);
		
		boolean ConfirmBtnEnabled = driver.findElement(By.xpath("//div[@class='confirm-btn']")).isEnabled();	
		Assert.assertTrue(ConfirmBtnEnabled);
		System.out.println("Verified Confirm button is Enabled");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(1000);
	
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("0");
		Thread.sleep(1000);
		String actualminStake = driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
				
				
		//Min Bet validation	
		String ExpectedMinBet=driver.findElement(By.xpath("//div[@class='d-flex justify-content-around title col-sm col-12 order-3 mr-sm-4']/div[1]/span")).getText();
		String expResult=ExpectedMinBet.replaceAll("[^0-9]", "");
			   
		String ActualMsg = driver.findElement(By.xpath("//div[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		 // String ActualMsg="Minimum bet amount per slip is 5 SRD";
		String ActualMinBet = ActualMsg.replaceAll("[^0-9]", "");
		Assert.assertEquals(ActualMinBet, expResult);
		System.out.println(ActualMsg);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click(); // clear	the betting screen																									
		Thread.sleep(2000);
			   
		//Maximum Bet Validation
				
		driver.findElement(By.xpath("//*[@id='clBetSection']/section[2]/div/div[2]/div/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("101");
		Thread.sleep(1000);
		String actualMaxStake = driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).getText();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
				
		String MaxBetExpected =driver.findElement(By.xpath("//div[@class='d-flex justify-content-around title col-sm col-12 order-3 mr-sm-4']/div[2]/span")).getText();
		String expectedResult=MaxBetExpected.replaceAll("[^0-9]", "");
				
		String actualMsg=driver.findElement(By.xpath("//div[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		//String actualMsg="Maximum bet amount per Selection is 100 SRD";
		String ActualMaxBetResult=actualMsg.replaceAll("[^0-9]", "");
		Assert.assertEquals(ActualMaxBetResult, expectedResult);
		System.out.println(actualMsg);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click(); 			//clear the selected bets
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();   //clear the betting screen
		Thread.sleep(1000);
			
		/* clear the betting table */
		// Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(10,DrawNo)']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click(); // clear	the selected bet numbers																					
		Thread.sleep(2000);
		
		boolean Disabled = driver.findElement(By.xpath("//div[@class='confirm-btn disabled']")).isDisplayed();	 //Once clear the Bet screen, confirm button will disable verified
		Assert.assertTrue(Disabled);
		System.out.println("Verified Bet Table is clear");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();
		Thread.sleep(2000);
			
		/* Undo selected betting number from the table one by one */			
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Undo()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Undo()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Undo()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Undo()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Undo()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();	
	
		/* Verify Quick picks 3 */
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
	    Thread.sleep(3000);
	    List <WebElement> Quickpick3 = driver.findElements(By.xpath("//div[@class='ball selected ng-scope']"));
		System.out.println("Total Cart numbers are: "+ Quickpick3.size());
		int Actualresultvalue=Quickpick3.size();
		int ExpectedResult=3;
		Assert.assertEquals(ExpectedResult, Actualresultvalue);
		System.out.println("Total added numbers in cart are: "+ Actualresultvalue);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='gm-danger-btn main ng-binding']")).click();
		Thread.sleep(1000);
		
		//verify Quick picks 5
	    driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
	    Thread.sleep(2000);
	    List <WebElement> Quickpick5 = driver.findElements(By.xpath("//div[@class='ball selected ng-scope']"));
		System.out.println("Total Cart numbers are: "+ Quickpick5.size());
		int Actualresultval=Quickpick5.size();
		int ExpectedResul=5;
		Assert.assertEquals(ExpectedResul, Actualresultval);
		System.out.println("Total added numbers in cart are: "+ Actualresultval);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='gm-danger-btn main ng-binding']")).click();
		Thread.sleep(1000);
	    
		//verify Quick picks 10
	    driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(10,DrawNo)']")).click();
	    Thread.sleep(2000);
	    List <WebElement> Quickpick10 = driver.findElements(By.xpath("//div[@class='ball selected ng-scope']"));
		System.out.println("Total Cart numbers are: "+ Quickpick10.size());
		int ActuaResultval=Quickpick10.size();
		int ExpResult=10;
		Assert.assertEquals(ExpResult, ActuaResultval);
		System.out.println("Total added numbers in cart are: "+ ActuaResultval);
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click(); 			//clear the selected bets
		Thread.sleep(2000);
	
	    driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();	//clear the cart
	    Thread.sleep(2000);
	    
	    //Pattern Quick pick selection	
  		WebElement Pattern=driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure"));   //(for Suribet)
  		//Instantiating Actions class
  		Actions actions = new Actions(driver);
  		actions.moveToElement(Pattern);
  		actions.click().build().perform();
  		Thread.sleep(3000);
  		
  		System.out.println("<----------Pattern Selection Bet------------->");
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[1]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[2]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[3]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[4]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[5]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[6]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[7]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[8]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[9]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//div[@class='d-flex flex-column justify-content-center align-items-center']/div/figure/figcaption/div[10]")).click();
  		Thread.sleep(1000);
  		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(4000);
		
		String ActualMg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMg, ExpectedMsg);
		System.out.println("Pattern Selection Bet:" + ActualMg );
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.Clear()']")).click();
		Thread.sleep(2000);	
	        
		//Get Draw Id
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]/h6/span")).click();
	 	String ActualDraw = driver.findElement(By.xpath("//div[1]/div[1][@class='flex-grow-1 px-1 py-1 py-md-2 text-center']")).getText();
	 	String SelectedDrawID=driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]/h6")).getText();
	 	Assert.assertEquals(SelectedDrawID, ActualDraw);
	 	System.out.println("Selected Draw ID# is:" + SelectedDrawID);
	 	System.out.println("Actual Draw ID# is : "+ ActualDraw);
	 	Thread.sleep(2000);
	 	
	 	//Get Draw Time	
	 	String actualTime=driver.findElement(By.xpath("//div[1]/div[2][@class='flex-grow-1 px-1 py-1 py-md-2 text-center']/span")).getText();
		Thread.sleep(2000);
	 	String expectedTime=driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]/span")).getText();
	 	Assert.assertEquals(expectedTime, actualTime);
	 	System.out.println("Selected Draw# Time is:" + expectedTime);
	 	System.out.println("Actual Draw# Time is : "+ actualTime);
	 	Thread.sleep(2000); 
				
	}

	@Then("^verify Search slip, Last win, online login cancel bet Time lapse, Game info$")
	public void verify_Search_slip_Last_win_online_login_cancel_bet_Time_lapse_Game_info() throws Throwable 
	{
		
		System.out.println("<------------verify Search slip, online login cancel bet Time lapse, Game info, verify Last win,------------>");	
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[3]/h6/span")).click();
		Thread.sleep(2000);
		// Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][76]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][77]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][78]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][79]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][80]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("5");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		String ActualMsg=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println("Bet Confirmation:" + ActualMsg);
		Thread.sleep(2000);
	
		
		driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);
		
		String SlipID = driver.findElement(By.xpath("//div[@class='modal-body p-3']/div/div/table[1]/tbody/tr/td[1]")).getText();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='close-btn']")).click();
		Thread.sleep(1000);
		
		//search slip id
		 driver.findElement(By.xpath("//input[@ng-model='$root.betSlipId']")).click();
		 driver.findElement(By.xpath("//input[@ng-model='$root.betSlipId']")).sendKeys(SlipID);
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//button[@ng-click='globalCtlr.searchSlip($root.betSlipId)']")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[@class='modal-content ng-scope']/div/h3")).isDisplayed();
		 System.out.println("Search Slip verified successfully");
		 Thread.sleep(1000);	 
		 driver.findElement(By.xpath("//span[@ng-click='popupCtr.cancel()']")).click();		//close slip detail pop-up window
		 Thread.sleep(3000);
	  
		 
		//Open the Game info page and close
		driver.findElement(By.linkText("How to Play?")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='fa fa-times-circle helpWpopupclose']")).click();
		System.out.println("Game info page opened and closed successfully");
		Thread.sleep(2000);		
			
		//Verify last win validation.					
		WebElement drawtime = driver.findElement(By.xpath("//div[@class='counter']/span"));
		String  drtime = drawtime.getText();
		System.out.println("draw left timings: "+drtime);

		//Converting the time period in seconds to for the wait time 
		String min = drtime.substring(0, 2);
		int minute = Integer.parseInt(min);
		String sec = drtime.substring(3, 5);
		int seconds = Integer.parseInt(sec);
		int totaltime = (minute*60)+seconds;
		//System.out.println("Time duration for the draw to complete "+totaltime1);
			
		if(totaltime<=12)
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
			System.out.println("new wait time: "+ wttime1);
		}
		else
		{
			int wttime= totaltime-11;
			//System.out.println("Wait time duration to clock down till last 1 seconds: "+ wttime1);
			Thread.sleep(wttime*1000);
			System.out.println("old wait time: "+ wttime);
		}
			
		driver.findElement(By.xpath("//div[@class='d-flex align-items-center justify-content-around p-2']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();
		Thread.sleep(1000);	
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("1");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		
		//Get Total Stake Amount
		WebElement TotalStake=driver.findElement(By.xpath("//section[@ng-show='vrKenoCtrl.rightTabId == 1']/section[1]/div/table/tbody/tr[2]/td[3]"));
		String stake=TotalStake.getText();
		double BetAmount=Double.parseDouble(stake); 
		
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(3000);
		
		String ActualMsg1=driver.findElement(By.xpath("//*[@ng-class='{show:vrKenoCtrl.DivVkCartAlert}']")).getText();
		String ExpectedMsg1 = "Bet has been Placed Successfully";
		Assert.assertEquals(ActualMsg1, ExpectedMsg1);
		System.out.println("Bet Confirmation:" + ActualMsg1);
		Thread.sleep(2000);
		
		//Get the Main balance after placing bet
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");	
		//System.out.println("Main Balance After Placing bet:" + MainBal);
		double AftPlaceBetBal=Double.parseDouble(MainBal);
		System.out.println("After placing Bet Main Balance is:" + AftPlaceBetBal);
		Thread.sleep(2000); 
		
		Thread.sleep(30000);	//waiting for result to announce and update the win status in bet details page
				
		driver.findElement(By.xpath("//span[@class='ac_id']")).click();
		Thread.sleep(8000);
					  	
		driver.findElement(By.xpath("//*[@id='tbodyBetDetials']/ul[1]/li[9]")).click();
		Thread.sleep(2000);
		
		//Wait until current draw result announce and then open the bet details page and addition only "win" status "Exp amount" & and compare with "last win" displayed.
		 List <WebElement> ExpWinAmt = driver.findElements(By.xpath("//td[@class='font-weight-bold ng-binding' and text()='Win']/preceding-sibling::td[1]"));
		 System.out.println("Number of win rows are: "+ ExpWinAmt.size());
		
		int temp = 0;
		for(int i=0; i<ExpWinAmt.size(); i++)
		{
			temp += Integer.parseInt(ExpWinAmt.get(i).getText());	
		}
		System.out.println("Total Winning Amount is: " +temp);		 
		Thread.sleep(2000);
		 
		driver.findElement(By.xpath("//span[@ng-click='popupCtr.cancel()']")).click();			//close  
		Thread.sleep(2000);
		 
		driver.findElement(By.xpath("//div/span[@ng-click='globalbetDetailsCtrl.BetDetailsback()']")).click();			//close betting details page
		Thread.sleep(2000);
		 
		String ActualWin=driver.findElement(By.xpath("//*[@id='clBetSection']/section[2]/div/div[4]/h6/span")).getText();  //get last win
		String ActualLastwin=ActualWin.replaceAll("[^0-9]", "");		//remove $ symbol from get text
		int ActualLastwin1 = Integer.parseInt(ActualLastwin);
		System.out.println(ActualLastwin1);
		Assert.assertEquals(ActualLastwin1, temp);		

		System.out.println("Verified Last Winning Amount and Win Amount is: " +ActualLastwin);
		Thread.sleep(3000);
		
		
		double TotalMainBal = AftPlaceBetBal + temp;
		
		String Actbal = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String ActMainBal = Actbal.replaceAll("[^0-9.]", "");	
		//System.out.println("Main Balance After Placing bet:" + ActMainBal);
		double AftPlaceBetActBal=Double.parseDouble(ActMainBal);
		System.out.println("After win account balance is:" + AftPlaceBetActBal);
		Thread.sleep(2000); 
		
		Assert.assertEquals(AftPlaceBetActBal, TotalMainBal);		
		System.out.println("Winning amount is adding to main balance successfully");
		Thread.sleep(2000);	
		 
	}

	@Then("^logout and close the opened browser$")
	public void logout_and_close_the_opened_browser() throws Throwable 
	{
		driver.quit();
	}
}