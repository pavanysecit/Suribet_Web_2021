package Web_Poker;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_Poker_OnlineLogin_BarCodeField_GameInfo_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_OnlineLogin_BarCodeField_GameInfo_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual poker module,login via online method, place bet, get the slip ID, valid and invalid input$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_poker_module_login_via_online_method_place_bet_get_the_slip_ID_valid_and_invalid_input() throws Throwable {
	    
	}

	@When("^Web: Place bet and get the slip id and cancel the bet and input the same slip id in barcode field and verify the status and the fileds in the slip generator$")
	public void web_Place_bet_and_get_the_slip_id_and_cancel_the_bet_and_input_the_same_slip_id_in_barcode_field_and_verify_the_status_and_the_fileds_in_the_slip_generator() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		Thread.sleep(2000);

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[8]"));
		String Ddetails = DrawNO.getText();
		System.out.println("Draw bid number with time details selected  "+Ddetails);
		DrawNO.click();
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
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//span[@ng-click='pokerCtlr.CancelSlip()']"));
		wait.until(ExpectedConditions.visibilityOf(Cancel));
		Cancel.click();
		Thread.sleep(4000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("*[@ng-click='pokerCtlr.CancelConfirmed(pokerCtlr.CancelSlipData[0].Barcode)']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));

		//Get the slip number and draw time 
		WebElement CurrentSlip = driver.findElement(By.xpath("//*[@ng-repeat='a in pokerCtlr.CancelSlipData']/td")); 
		String slip = CurrentSlip.getText();
		String cslip = slip.replaceAll("[^0-9]","");
		System.out.println("Slip number for: "+ cslip);
		//		WebElement cdate = driver.findElement(By.xpath("//table[@class='vR_Slip_table']/tbody/tr[2]/td[3]")); 
		//		String canceldate = cdate.getText();

		SlipCancel.click();
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
		Thread.sleep(4000);

		//Validate the hide and unhide slip search box
		WebElement searchboxattribute = driver.findElement(By.xpath("//span[@class='fa fa-barcode ']")); 
		Assert.assertEquals(searchboxattribute.getAttribute("class"), "float_left fa_barcode_JS toggleParentclass");
		System.out.println("Slip search box is visible to user");

		searchboxattribute.click();
		Thread.sleep(2000);
		Assert.assertEquals(searchboxattribute.getAttribute("class"), "float_left fa_barcode_JS");
		System.out.println("Slip search box is not visible to user");
		System.out.println("Search box hide and un-hide functionaity is verified");
		searchboxattribute.click();
		Thread.sleep(2000);

		// Search with no input and verify the status displayed to user
		WebElement SlipSearch = driver.findElement(By.xpath("//*[@ng-click='globalCtlr.searchSlip($root.betSlipId)']")); 
		SlipSearch.click();
		Thread.sleep(1000);
		//By-pass the search result 
		WebElement searchresult = driver.findElement(By.xpath("//div[@ng-show='elem_InvalidSearch']"));
		Assert.assertEquals("No Record Found", searchresult.getText());
		SlipSearch.click();
		Thread.sleep(1000);
		Assert.assertEquals("Please enter barCode/slipId to search", searchresult.getText());
		// Verify the message is displayed to user 
		Assert.assertEquals(searchresult.getAttribute("class"), "ng-binding");
		System.out.println("Blank search result validation message and the message is displayed to user: "+ searchresult.getText());

		//Search result for invalid inputs
		/*
		 * input values= 0
		 *               852741963741852
		 *               slip num
		 * As arithmetic expressions will be considered not validated here
		 * string input length is not restricted
		 * spaces is considered at the starting the valid slip
		 */

		WebElement Searchinput = driver.findElement(By.xpath("//*[@placeholder='Enter Barcode/SlipId']")); 
		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys("0");
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(1000);
		Assert.assertEquals("No Record Found", searchresult.getText());
		Thread.sleep(5000);

		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys("85274196374185");
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(1000);
		Assert.assertEquals("No Record Found", searchresult.getText());
		Thread.sleep(5000);

		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys("slip num");
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(5000);
		Assert.assertEquals("No Record Found", searchresult.getText());
		Thread.sleep(1000);


		//with spaces no validation messages is yielding 
		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys("   "+cslip);
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(5000);
		//		Assert.assertEquals("No Record Found", searchresult.getText());
		//		Thread.sleep(1000);
		System.out.println("Invalid search result validation message and the message is displayed to user: "+ searchresult.getText());
		System.out.println("Invalid search results yield user message to be displayed");

		/*
		 * Verify for valid slip numbers
		 * 1. search the previously placed bet with slip num 
		 * 2. Verify the slip generate validations
		 * 3. Verify the print button is available to user to print the generated slip
		 * 4.  
		 */
		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys(cslip);
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(5000);

		//Slip details tab available with valid datas
		WebElement SlipDetails = driver.findElement(By.xpath("//div[@ng-show='BetSlipShow']/h3"));  
		wait.until(ExpectedConditions.visibilityOf(SlipDetails));
		Assert.assertEquals("Slip Details [Virtual Roulette]", SlipDetails.getText());

		//Search draw number and fetch necessary details
		WebElement SD_drawtime = driver.findElement(By.xpath("//table[@class='vR_Slip_table']/tbody/tr[2]/td[3]"));    
		WebElement SD_slipnum = driver.findElement(By.xpath(" //table[@class='vR_Slip_table']/tbody/tr/td[1]"));    
		WebElement SD_drawnum = driver.findElement(By.xpath(" //table[@class='vR_Slip_table']/tbody/tr/td[2]"));  
		WebElement SD_print = driver.findElement(By.xpath("//div[@class='vR_Slip_Print']"));   

		//Validating the slip search details 
		Assert.assertEquals(slip, SD_slipnum.getText());
		//		Assert.assertEquals(SD_drawnum.getText().substring(10), dnum);
		String rdtime = SD_drawtime.getText().replaceAll("\\s", "");
		System.out.println(rdtime);
		//		String rdtime1 = canceldate.replaceAll("\\s", "");
		//		System.out.println(rdtime1);
		//		Assert.assertEquals(rdtime, rdtime1);
		Assert.assertTrue(SD_print.isDisplayed());
		System.out.println("Verified all the slip search details screen and verified");

		//close the search slip page
		driver.findElement(By.cssSelector(".fa.fa-times.close_slip_JS")).click();
		Thread.sleep(2000);
	}

	@Then("^Web: Verify the field with valid slip id while the bets are not placed for specific slip id by the user and verify the result$")
	public void web_Verify_the_field_with_valid_slip_id_while_the_bets_are_not_placed_for_specific_slip_id_by_the_user_and_verify_the_result() throws Throwable {
		//Gameinfo page validations
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(By.className("helpBut")).click(); 
		Thread.sleep(5000);

		WebElement gameinfoimage = driver.findElement(By.xpath("//img[@src='https://cdn2-5e15.kxcdn.com/betting/webportal/images/VirtualRoulette/helpbg_1.png']"));
		wait.until(ExpectedConditions.visibilityOf(gameinfoimage));
		Assert.assertTrue(gameinfoimage.isDisplayed());
		Thread.sleep(2000);
		System.out.println("gameinfo page is loading and the image is verified");
		Thread.sleep(2000);
	}

	@Then("^Web: Verify the field with invalid slip id and verify the validation message is displayed to user$")
	public void web_Verify_the_field_with_invalid_slip_id_and_verify_the_validation_message_is_displayed_to_user() throws Throwable {
	   
	}

	@Then("^Web: Verify the game info page is been loading$")
	public void web_Verify_the_game_info_page_is_been_loading() throws Throwable {
	   
	}

	@Then("^Web: Verify the search slip after logout and search for valid and invalid inputs$")
	public void web_Verify_the_search_slip_after_logout_and_search_for_valid_and_invalid_inputs() throws Throwable {
		driver.quit();
	}
}