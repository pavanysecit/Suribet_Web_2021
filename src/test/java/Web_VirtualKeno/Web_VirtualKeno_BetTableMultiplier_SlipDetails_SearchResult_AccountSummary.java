package Web_VirtualKeno;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_BetTableMultiplier_SlipDetails_SearchResult_AccountSummary extends Web_VirtualKeno_URL_OnlineLogin {
WebDriver driver;

	public Web_VirtualKeno_BetTableMultiplier_SlipDetails_SearchResult_AccountSummary() throws Exception {
		driver =Web_VirtualKeno_URL_OnlineLogin();
	}
	
	
	@Given("^launch the chrome browserr, Enter the valid url and login to online account$")
	public void launch_the_chrome_browserr_Enter_the_valid_url_and_login_to_online_account() throws Throwable 
	{
		System.out.println("<---------------------Place the bet and verify the slip details----------------------->");
		// Select the upcoming draw#
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[1]")).click();
		Thread.sleep(1000);
		// Choose Betting Numbers manually
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][10]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][30]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][50]")).click();
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][70]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@ng-click='vrKenoCtrl.addBetSlip(x)'][80]")).click();
		Thread.sleep(2000);
		
		boolean ConfirmBtnEnabled = driver.findElement(By.xpath("//div[@class='confirm-btn']")).isEnabled();	
		Assert.assertTrue(ConfirmBtnEnabled);
		System.out.println("Verified Confirm button is Enabled");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@class='confirm-btn']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).clear();
		driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl._BetAmount']")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'FORWARD')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'Confirm')]")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//button[contains(text(), 'Cancel Slip')]")).click();
		Thread.sleep(3000);
	//Get Draw Number
		String ActualDrawID = driver.findElement(By.xpath("//div[@class='modal-body p-3']/div/div/table[1]/tbody/tr/td[2]")).getText();
		//driver.findElement(By.xpath("//button[@class='close-btn']")).click();
	 	String SelectedDrawID=driver.findElement(By.xpath("//div[1]/div[1][@class='flex-grow-1 px-1 py-1 py-md-2 text-center']/span")).getText();
	 //Replace pre (#) from DrawId
	 	String SelectedDrawId=SelectedDrawID.replaceAll("#","");
	 	Assert.assertEquals(SelectedDrawId, ActualDrawID);
	 	System.out.println("Selected Draw ID# is:" + SelectedDrawId);
	 	System.out.println("Actual Draw ID# from slip is : "+ ActualDrawID);
	 	Thread.sleep(1000);
		 	
	 //Get Stake amount	
		String Expectedstake="2";
		String actualStake=driver.findElement(By.xpath("//table[@class='bet-data']/tbody/tr/td[3]")).getText();
		Assert.assertEquals(Expectedstake, actualStake);
	 	System.out.println("Expected Stake amount is:" + Expectedstake);
	 	System.out.println("Actual Stake Amount is: "+ actualStake);
	 	Thread.sleep(1000);
	 	
	 //Verify Multiplier	
	 	String ExpectedMultiplier="175";
		String actualMultiplier=driver.findElement(By.xpath("//table[@class='bet-data']/tbody/tr/td[4]")).getText();
		Assert.assertEquals(ExpectedMultiplier, actualMultiplier);
	 	System.out.println("Expected Multiplier is:" + ExpectedMultiplier);
	 	System.out.println("Actual Multiplier is: "+ actualMultiplier);
	 	Thread.sleep(1000);
	 	
	 //Verify Expected Winning Amount	
	 	String ExpectedWin="350";
		String actualWin=driver.findElement(By.xpath("//table[@class='bet-data']/tbody/tr/td[5]")).getText();
		Assert.assertEquals(ExpectedWin, actualWin);
	 	System.out.println("Expected Win amount is:" + ExpectedWin);
	 	System.out.println("Actual Win Amount is: "+ actualWin);
	 	Thread.sleep(1000);	
	 	
	 	//Verify Total Bet Amount	
	 	String ExpTotalBet="SRD 2";
		String actualTotalBetAmount=driver.findElement(By.xpath("//table[@class='text-center foot']/tbody/tr/td[2]")).getText();
		Assert.assertEquals(actualTotalBetAmount, ExpTotalBet);
	 	System.out.println("Expected Total Bet Amount is:" + ExpTotalBet);
	 	System.out.println("Actual Total Bet Amount is: "+ actualTotalBetAmount);
	 	Thread.sleep(1000);	
	 	
	 		
	 //Get Slip Status	
	 	String SlipStatus=driver.findElement(By.xpath("//table[@class='text-center foot']/tbody/tr/td[3]")).getText();
	 	System.out.println("Slip Status is: " + SlipStatus);
		Thread.sleep(1000);
	 	
	 	driver.findElement(By.xpath("//button[@class='close-btn']")).click();  //close pop up window
	 	Thread.sleep(2000);	
	 
	 	
	 	
		driver.findElement(By.xpath("//a[@ng-click='vrKenoCtrl.rightTabId = 2']")).click();        //click on more results
	  // driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.rightTabId = 2']")).click();     //click on Results section
	   Thread.sleep(3000);	 	
	}

	@When("^Verify BetTableMultiplier, SlipDetails, SearchDrawResult$")
	public void verify_BetTableMultiplier_SlipDetails_SearchDrawResult() throws Throwable 
	{
		
		driver.findElement(By.xpath("//*[@id='upcomingList']/div[2]")).click();
		Thread.sleep(2000);
		
		//Choose Betting Numbers
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(3,DrawNo)']")).click();   //select Quick pick 3
		Thread.sleep(6000);
		
		// For Quick pick 3 bet type, validating multipliers
		WebElement BetTable = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/span"));
		// Instantiating Actions class
		Actions actions = new Actions(driver);
		// Mouse Hovering on Bet Table
		actions.moveToElement(BetTable);
		actions.click().build().perform();
		Thread.sleep(1000);

		// Locating the element from Bet Table
		System.out.println("Bet Table: Multipler for 3 betting numbers selected are: ");
		String Multiplierval1 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[2]/td[2]")).getText();
		String Hits2 = "2";
		Assert.assertEquals(Multiplierval1, Hits2);
		System.out.println("If 2 Numbers matched, Multiplier is: " + Multiplierval1);

		String Multiplierval2 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[3]/td[2]")).getText();
		String Hits3 = "48";
		Assert.assertEquals(Hits3, Multiplierval2);
		System.out.println("If 3 Numbers matched, Multiplier is: " + Multiplierval2);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();
		Thread.sleep(1000);
				 
				
	}

	@Then("^verify the AccountSummary page details$")
	public void verify_the_AccountSummary_page_details() throws Throwable 
	{
		
		//Validating bet table multiplier for Quick pick 5
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(5,DrawNo)']")).click();   
		Thread.sleep(6000);
		
		//For Quick pick 5 bet type, validating multipliers
		WebElement BetTable2=driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/span"));
		//Instantiating Actions class
		Actions actions = new Actions(driver);
		//Mouse Hovering on Bet Table
		actions.moveToElement(BetTable2);
		actions.click().build().perform();
		Thread.sleep(1000);
		
		// Locating the element from Bet Table
		System.out.println("Bet Table: Multipler for 5 betting numbers selected are: ");
		 String FirstMultival1 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[2]/td[2]")).getText();
		 String Hits2="1";
		 Assert.assertEquals(Hits2, FirstMultival1);
		 System.out.println("If 2 Numbers matched, Multiplier is: "+ FirstMultival1);
		 
		 String SecondMultival2 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[3]/td[2]")).getText();
		 String Hits3="4";
		 Assert.assertEquals(Hits3, SecondMultival2);
		 System.out.println("If 3 Numbers matched, Multiplier is: "+ SecondMultival2);
		 
		 String ThirdMultival3 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[4]/td[2]")).getText();
		 String Hits4="20";
		 Assert.assertEquals(Hits4, ThirdMultival3);
		 System.out.println("If 4 Numbers matched, Multiplier is: "+ ThirdMultival3);
		 
		 String FourthMultival4 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[5]/td[2]")).getText();
		 String Hits5="175";
		 Assert.assertEquals(Hits5, FourthMultival4);
		 System.out.println("If 5 Numbers matched, Multiplier is: "+ FourthMultival4);
		 Thread.sleep(1000);
		 
		 driver.findElement(By.xpath("//button[@ng-click='vrKenoCtrl.db_cart_clear_all()']")).click();
		 Thread.sleep(2000);	
		
			   
	}

	@Then("^Close the Browser$")
	public void close_the_Browser() throws Throwable 
	{
		
		System.out.println("<------------------Verify Bet Table Multipliers---------------------->");
		//For Quick pick 10 bet type, validating multipliers
		driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.QuickpickSelection(10,DrawNo)']")).click();   //select Quick pick 10
		Thread.sleep(6000);
		
		WebElement BetTable3=driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/span"));
		//Instantiating Actions class
		Actions action = new Actions(driver);
		//Mouse Hovering on Bet Table
		action.moveToElement(BetTable3);
		action.click().build().perform();
		Thread.sleep(2000);
		
		// Locating the element from Bet Table
		System.out.println("Bet Table: Multipler for 10 betting numbers selected are: ");
		 String Multipliervalue1 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[2]/td[2]")).getText();
		 String Hit4="2";
		 Assert.assertEquals(Hit4, Multipliervalue1);
		 System.out.println("If 4 Numbers matched, Multiplier is: "+ Multipliervalue1);
		 
		 String Multipliervalue2 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[3]/td[2]")).getText();
		 String Hit5="7";
		 Assert.assertEquals(Hit5, Multipliervalue2);
		 System.out.println("If 5 Numbers matched, Multiplier is: "+ Multipliervalue2);
		 
		 String Multipliervalue3 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[4]/td[2]")).getText();
		 String Hits6="18";
		 Assert.assertEquals(Hits6, Multipliervalue3);
		 System.out.println("If 6 Numbers matched, Multiplier is: "+ Multipliervalue3);
	
		 String Multipliervalue4 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[5]/td[2]")).getText();
		 String Hits7="50";
		 Assert.assertEquals(Hits7, Multipliervalue4);
		 System.out.println("If 7 Numbers matched, Multiplier is: "+ Multipliervalue4);
	
		 String Multipliervalue5 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[6]/td[2]")).getText();
		 String Hits8="100";
		 Assert.assertEquals(Hits8, Multipliervalue5);
		 System.out.println("If 8 Numbers matched, Multiplier is: "+ Multipliervalue5);
		 
		 String Multipliervalue6 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[7]/td[2]")).getText();
		 String Hits9="800";
		 Assert.assertEquals(Hits9, Multipliervalue6);
		 System.out.println("If 9 Numbers matched, Multiplier is: "+ Multipliervalue6);
		 
		 String Multipliervalue7 = driver.findElement(By.xpath("//figure[@class='bet-table mb-0 active']/figcaption/table/tbody/tr[8]/td[2]")).getText();
		 String Hits10="10000";
		 Assert.assertEquals(Hits10, Multipliervalue7);
		 System.out.println("If 10 Numbers matched, Multiplier is: "+ Multipliervalue7);
		Thread.sleep(2000);
		
		
	
		System.out.println("<------------Click on Accounts and verify Account Summary----------->");
		// Locating the Main Menu (Parent element)
		WebElement AccountsMenu=driver.findElement(By.xpath("//nav[@class='nav_menu_w']/ul/li[8]/a"));   //(for Suribet)
		//	WebElement AccountsMenu=driver.findElement(By.xpath("//nav[@class='nav_menu_w']/ul/li[4]/a"));   //For Superbet
		Thread.sleep(2000);
		//Instantiating Actions class
		Actions actions = new Actions(driver);
		//Hovering on main menu
		actions.moveToElement(AccountsMenu);
		Thread.sleep(3000);
		
		// Locating the element from Sub Menu
		WebElement subMenuAccount = driver.findElement(By.xpath("//ul[@ng-if='x.subMenu.length>0&&x.groupid<=0&&logoutdisplay==true']/li[2]/a"));
		//To mouse over on sub menu
		actions.moveToElement(subMenuAccount);
		//build()- used to compile all the actions into a single step
		actions.click().build().perform();
		Thread.sleep(6000);
			
		String ActSummary=driver.findElement(By.xpath("//div[@ng-controller='globalAccountController as AccountCtrl']/div[2]")).getText();
		String Expected="Account Summary";
		Assert.assertEquals(Expected, ActSummary);
		Thread.sleep(2000);
		
		String BalanceAmount = driver.findElement(By.xpath("//span[@id='sn_after_login_info_Amount']")).getText();
		String MainBal = BalanceAmount.replaceAll("[^0-9.]", "");
		String ActSummaryBalance=driver.findElement(By.xpath("//section[@ng-if='BetDetailsShow']/div[1]/div[3]/ul/li[4]/span[2]")).getText();
		String ActSumBal=ActSummaryBalance.replaceAll("[^0-9.]", "");
		Assert.assertEquals(MainBal, ActSumBal);
		System.out.println("Main Balance & Account Summary Balance verified and Account Summary Balance is:"+ ActSumBal );
		Thread.sleep(2000);
		
		String Summarydetails=driver.findElement(By.xpath("//div[@ng-controller='globalAccountController as AccountCtrl']/div[3]")).getText();
		System.out.println("Account Summary is: "+ Summarydetails);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@ng-click='AccountCtrl.Accountback()']")).click();
		Thread.sleep(2000);
				
		System.out.println("<--------------View More Result-------------------->");
	    //String recentResult=driver.findElement(By.xpath("//div[@class='p-1']")).getText();			//Get Most recent results
		//System.out.println("Recent Results Numbers are: "+ recentResult);
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@ng-click='vrKenoCtrl.rightTabId = 2']")).click();        //click on more results
		// driver.findElement(By.xpath("//div[@ng-click='vrKenoCtrl.rightTabId = 2']")).click();     //click on Results section
	   Thread.sleep(3000);
	   
	   //Search valid draw id result 
	   driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl.SearchDraw']")).sendKeys("1");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//span[@class='search-button']")).click();
	   Thread.sleep(2000);
	   
	   //Display searched results in console  
	   String getdrawResult=driver.findElement(By.xpath("//div[@id='resultList']/div[1]/div[2]")).getText();
	   System.out.println(getdrawResult);
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//span[@class='clear-button ng-scope']")).click();
	   Thread.sleep(3000);
	   
	   //Search In-valid draw id result 
	   driver.findElement(By.xpath("//input[@ng-model='vrKenoCtrl.SearchDraw']")).sendKeys("999");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//span[@class='search-button']")).click();
	   Thread.sleep(2000);
	   
	   String actualText=driver.findElement(By.xpath("//div[@ng-class='{show:vrKenoCtrl.DivVkResultAlert}']")).getText();
	   String expectedText="Please enter Valid DrawNumber !";
	   Assert.assertEquals(actualText, expectedText);
	   System.out.println("Invalid DrawId Message is:" +actualText );
	   
	   driver.findElement(By.xpath("//span[@class='clear-button ng-scope']")).click();
	   Thread.sleep(2000);
		
		driver.quit();
	}
}