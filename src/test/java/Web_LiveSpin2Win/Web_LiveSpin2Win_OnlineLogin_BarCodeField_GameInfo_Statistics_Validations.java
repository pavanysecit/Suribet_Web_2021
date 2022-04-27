package Web_LiveSpin2Win;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_LiveSpin2Win_OnlineLogin_BarCodeField_GameInfo_Statistics_Validations extends Web_LiveSpin2Win_URL_OnlineLogin {
WebDriver driver;
	
	
	public Web_LiveSpin2Win_OnlineLogin_BarCodeField_GameInfo_Statistics_Validations() throws Exception {
		driver =Web_LiveSpin2Win_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, LiveSpin(\\d+)Win module,login via online method, get the slip ID, valid and invalid input gameinfo button and statistics$")
	public void web_Chrome_browser_suribet_website_valid_URL_LiveSpin_Win_module_login_via_online_method_get_the_slip_ID_valid_and_invalid_input_gameinfo_button_and_statistics(int arg1) throws Throwable {
	    
	}

	@When("^Web: Get the slip id and search for the validation of the slip id$")
	public void web_Get_the_slip_id_and_search_for_the_validation_of_the_slip_id() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 240);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));


		/*
		 * Fetch the previous slip ID and validate the valid result 
		 */

		//Get the slip number and draw time 
		WebElement pslip = driver.findElement(By.xpath("//*[text()='View Last Slip Details']")); 
		pslip.click();
		Thread.sleep(2000);

		WebElement slipid = driver.findElement(By.xpath("//*[@class='v_slip_mobile']/table/tbody/tr/td"));  
		System.out.println("Slip number for: "+ slipid);
		String sid = slipid.getText().replaceAll("[^0-9]", "");

		driver.findElement(By.xpath("//*[@ng-click='popupCtr.cancel()']")).click(); 
		Thread.sleep(2000);


		// Search with no input and verify the status displayed to user
		WebElement SlipSearch = driver.findElement(By.xpath("//*[@ng-click='globalCtlr.searchSlip($root.betSlipId)']")); 
		SlipSearch.click();
		Thread.sleep(1000);

		//By-pass the search result 
		WebElement searchresult = driver.findElement(By.xpath("//div[@ng-show='elem_InvalidSearch']"));
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
		Searchinput.sendKeys("   "+sid);
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
		Searchinput.sendKeys(sid);
		Thread.sleep(1000);
		SlipSearch.click();
		Thread.sleep(5000);

		//Slip details tab available with valid data's
		WebElement SlipDetails = driver.findElement(By.xpath("//*[text()='Tropicana Slip Details [Live Spin2Win] ']"));  
		wait.until(ExpectedConditions.visibilityOf(SlipDetails));
		Assert.assertEquals("Tropicana Slip Details [Live Spin2Win] ", SlipDetails.getText());

		WebElement SD_slipnum = driver.findElement(By.xpath("//*[@class='vR_Slip_table']/tbody/tr/td"));    

		//Validating the slip search details 
		Assert.assertEquals(sid, SD_slipnum.getText().replaceAll("[^0-9]", ""));
		System.out.println("Verified all the slip search details screen and verified");

		//close the search slip page
		driver.findElement(By.cssSelector(".fa.fa-times.close_slip_JS")).click();
		Thread.sleep(2000);
	}

	@Then("^Web: verify the how to play link is active$")
	public void web_verify_the_how_to_play_link_is_active() throws Throwable {
		//Game info page validations
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.helpPopupflag=true']")).click(); 
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.helpPopupflag=true']")).click();
		Thread.sleep(3000);

		WebElement gameinfoimage = driver.findElement(By.xpath("//*[@class='helpWpopup']"));
		wait.until(ExpectedConditions.visibilityOf(gameinfoimage));
		Assert.assertTrue(gameinfoimage.isDisplayed());
		Thread.sleep(2000);
		System.out.println("gameinfo page is loading and the image is verified");
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//*[@class='helpWpopup']")).click();
		Thread.sleep(3000); 
		
		/*
		 * Statistics result validations
		 */
		driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.lvRouletteStatistics = true'] ")).click();
		Thread.sleep(3000); 
		WebElement stats = driver.findElement(By.xpath("//*[@class='lv_statitics']"));
		wait.until(ExpectedConditions.visibilityOf(stats));
		Assert.assertTrue(stats.isDisplayed());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='sidebar_close']")).click();
		Thread.sleep(3000); 
	}

	@Then("^Web: Verify the statistics window is displayed to user$")
	public void web_Verify_the_statistics_window_is_displayed_to_user() throws Throwable {
	    driver.quit();
	}
}