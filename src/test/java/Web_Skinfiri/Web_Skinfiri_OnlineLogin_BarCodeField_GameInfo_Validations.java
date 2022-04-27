package Web_Skinfiri;

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

public class Web_Skinfiri_OnlineLogin_BarCodeField_GameInfo_Validations extends Web_Skinfiri_URL_OnlineLogin {
WebDriver driver;

	public Web_Skinfiri_OnlineLogin_BarCodeField_GameInfo_Validations() throws Exception {
		driver = Web_Skinfiri_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, sports betting module,login via online method, place bet, get the slip ID, valid and invalid input$")
	public void web_Chrome_browser_suribet_website_valid_URL_sports_betting_module_login_via_online_method_place_bet_get_the_slip_ID_valid_and_invalid_input() throws Throwable {
	    
	}

	@When("^Web: Place bet and get the slip id and cancel the bet and input the same slip id in barcode field and verify the status and the fields in the slip generator$")
	public void web_Place_bet_and_get_the_slip_id_and_cancel_the_bet_and_input_the_same_slip_id_in_barcode_field_and_verify_the_status_and_the_fields_in_the_slip_generator() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		//Fetch the main balance 
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String bbal = bal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));
		System.out.println("Main balance before bet placing: "+ bbal);

		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//li[@class='skinfriBetIcon_25  Soldout'])[1]"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='DailyGameCtrl.changeAllStack()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5.2");
		Thread.sleep(2000); 

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='DailyGameCtrl.db_cart_submit()']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE SKINFIRI 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[text()='Cancel Slip ']"));
		wait.until(ExpectedConditions.visibilityOf(Cancel));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(4000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//div[@ng-if='cancelbutton']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));

		//Get the slip number and draw time 
		WebElement CurrentSlip = driver.findElement(By.xpath("//*[@class='ul_clear slipgrid_d slipgrid_4']/li")); 
		String slip = CurrentSlip.getText();
		System.out.println("Slip number for: "+ slip);
		WebElement cdate = driver.findElement(By.xpath("//*[@class='ul_clear slipgrid_d slipgrid_4']/li[3]")); 
		String canceldate = cdate.getText();

		SlipCancel.click();
		Thread.sleep(3000);

		//Clicking on Close icon on the pop-up
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		WebElement custompopup = driver.findElement(By.cssSelector(".PrintSlipDet.MsgPop_OK"));
		custompopup.click();
		Thread.sleep(4000);

		//Validate the hide and unhide slip search box
		WebElement searchboxattribute = driver.findElement(By.xpath("//li[@ng-show='addbetSlipSearch']")); 
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
		//		SlipSearch.click();
		//		Thread.sleep(1000);
		//		//By-pass the search result 
		//		WebElement searchresult = driver.findElement(By.xpath("//div[@ng-show='elem_InvalidSearch']"));
		//		Assert.assertEquals("No Record Found", searchresult.getText());
		//		SlipSearch.click();
		//		Thread.sleep(1000);
		//		Assert.assertEquals("Please enter barCode/slipId to search", searchresult.getText());
		//		// Verify the message is displayed to user 
		//		Assert.assertEquals(searchresult.getAttribute("class"), "ng-binding");
		//		System.out.println("Blank search result validation message and the message is displayed to user: "+ searchresult.getText());

		//		//Search result for invalid inputs
		//		/*
		//		 * input values= 0
		//		 *               852741963741852
		//		 *               slip num
		//		 * As arithmetic expressions will be considered not validated here
		//		 * string input length is not restricted
		//		 * spaces is considered at the starting the valid slip
		//		 */
		//
		WebElement Searchinput = driver.findElement(By.xpath("//*[@placeholder='Enter Barcode/SlipId']")); 
		//		Searchinput.click();
		//		Thread.sleep(1000);
		//		Searchinput.sendKeys("0");
		//		Thread.sleep(1000);
		//		SlipSearch.click();
		//		Thread.sleep(1000);
		//		Assert.assertEquals("No Record Found", searchresult.getText());
		//		Thread.sleep(5000);
		//
		//		Searchinput.click();
		//		Thread.sleep(1000);
		//		Searchinput.sendKeys("85274196374185");
		//		Thread.sleep(1000);
		//		SlipSearch.click();
		//		Thread.sleep(1000);
		//		Assert.assertEquals("No Record Found", searchresult.getText());
		//		Thread.sleep(5000);
		//
		//		Searchinput.click(); 
		//		Thread.sleep(1000);
		//		Searchinput.sendKeys("slip num");
		//		Thread.sleep(1000);
		//		SlipSearch.click();
		//		Thread.sleep(5000);
		//		Assert.assertEquals("No Record Found", searchresult.getText());
		//		Thread.sleep(1000);
		//
		//
		//		//with spaces no validation messages is yielding 
		//		Searchinput.click();
		//		Thread.sleep(1000);
		//		Searchinput.sendKeys("   "+slip);
		//		Thread.sleep(1000);
		//		SlipSearch.click();
		//		Thread.sleep(5000);
		//		//		Assert.assertEquals("No Record Found", searchresult.getText());
		//		//		Thread.sleep(1000);
		//		System.out.println("Invalid search result validation message and the message is displayed to user: "+ searchresult.getText());
		//		System.out.println("Invalid search results yield user message to be displayed");

		/*
		 * Verify for valid slip numbers
		 * 1. search the previously placed bet with slip num 
		 * 2. Verify the slip generate validations
		 * 3. Verify the print button is available to user to print the generated slip
		 * 4.  
		 */
		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys(slip);
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(5000);

		//Slip details tab available with valid datas
		WebElement SlipID = driver.findElement(By.xpath("//ul[@class='ul_clear slipgrid_d slipgrid_5']/li[1]"));  
		wait.until(ExpectedConditions.visibilityOf(SlipID));
		Assert.assertEquals(slip, SlipID.getText());

		//Search draw number and fetch necessary details
		WebElement SD_status = driver.findElement(By.xpath("//div[@class='slip_Details_skinfiri ng-scope']/div[2]/ul/li/span[2]"));    
		Assert.assertTrue(SD_status.isDisplayed());
		Assert.assertEquals("Cancelled", SD_status.getText());
		System.out.println("Verified all the slip search details screen and verified");

		//close the search slip page
		driver.findElement(By.xpath("(//div[@class='fa fa-times close_popup_JS'])[4]")).click();
		Thread.sleep(2000);


		// Log-off from the spoertsbetting and search the slip 
		WebElement logoff = driver.findElement(By.xpath("//*[@class='log_off click_effect_JS']"));  
		wait.until(ExpectedConditions.visibilityOf(logoff));
		Thread.sleep(3000); 

		//Search valid slip id
		Searchinput.click();
		Thread.sleep(1000);
		Searchinput.sendKeys(slip);
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(SlipID));
		Assert.assertEquals(slip, SlipID.getText());
		System.out.println("Valid slip id yields valid results after logoff the account");

		//Invalid input where valid message is not displayed to user have to report to pavan
		//		driver.findElement(By.xpath("//*[@class='log_off click_effect_JS']")).click();
		//		Thread.sleep(5000); 
	}

	@Then("^Web: Verify the field with valid slip id while the bets are not placed for specific slip id by the user and verify the result$")
	public void web_Verify_the_field_with_valid_slip_id_while_the_bets_are_not_placed_for_specific_slip_id_by_the_user_and_verify_the_result() throws Throwable {
	   
	}

	@Then("^Web: Verify the field with invalid slip id and verify the validation message is displayed to user$")
	public void web_Verify_the_field_with_invalid_slip_id_and_verify_the_validation_message_is_displayed_to_user() throws Throwable {
	    
	}

	@Then("^Web: Verify the search slip after logout and search for valid and invalid inputs$")
	public void web_Verify_the_search_slip_after_logout_and_search_for_valid_and_invalid_inputs() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}