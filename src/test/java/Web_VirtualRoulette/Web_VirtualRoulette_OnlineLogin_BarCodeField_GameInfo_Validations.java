package Web_VirtualRoulette;

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

public class Web_VirtualRoulette_OnlineLogin_BarCodeField_GameInfo_Validations extends Web_VirtualRoulette_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualRoulette_OnlineLogin_BarCodeField_GameInfo_Validations() throws Exception {
		driver = Web_VirtualRoulette_URL_OnlineLogin();
	}

	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module,login via online method, place bet, get the slip ID, valid and invalid input$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_login_via_online_method_place_bet_get_the_slip_ID_valid_and_invalid_input() throws Throwable {


	}

	@When("^Web: Place bet and get the slip id and cancel the bet and input the same slip id in barcode field and verify the status and the fileds in the slip generator$")
	public void web_Place_bet_and_get_the_slip_id_and_cancel_the_bet_and_input_the_same_slip_id_in_barcode_field_and_verify_the_status_and_the_fileds_in_the_slip_generator() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));

		//Fetch the main balance 
		WebElement bal = driver.findElement(By.xpath("//span[@ng-bind='AccountBalance|number:2']"));
		String bbal = bal.getText();
		double numbal =Double.parseDouble(bbal.replaceAll(",", ""));
		System.out.println("Main balance before bet placing: "+ bbal);

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear']//li[8]/span"));
		String Dnum = DrawNO.getText();
		System.out.println("Bet places for current draw: "+ Dnum);
		String dnum = Dnum.substring(7);
		DrawNO.click();
		Thread.sleep(2000);

		//Selelct the Numbers from the Roulette Board top place the bet 
		WebElement Pick2 = driver.findElement(By.xpath("//span[@bettypepickid='3']"));
		WebElement Pick28 = driver.findElement(By.xpath("//span[@bettypepickid='29']"));
		WebElement Pick13 = driver.findElement(By.xpath("//span[@bettypepickid='14']"));

		Pick2.click();
		Thread.sleep(1000);
		Pick28.click();
		Thread.sleep(1000);
		Pick13.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the Roulette table");

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='globalrouletteCtlr.PlaceBetDetails(false,false)']"));

		//FORWARD AND PLACE BET WITHOUT LOGIN TO THE VIRTUALROULETTE 
		Forward.click();
		Thread.sleep(2000);
		Confirm.click();
		Thread.sleep(2000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//button[@ng-show='globalrouletteCtlr.CancelbtnShow']"));
		wait.until(ExpectedConditions.visibilityOf(Cancel));
		String cancel = Cancel.getText();
		System.out.println("Cancel Slip text button is verified: "+ cancel);
		Assert.assertEquals("Cancel Slip", cancel);
		Thread.sleep(2000);
		Cancel.click();
		Thread.sleep(4000);

		//Cancel alert popup generated with the slip details and cancel the bet
		WebElement SlipCancel = driver.findElement(By.xpath("//span[@class='vR_Slip_Cancel']"));
		wait.until(ExpectedConditions.visibilityOf(SlipCancel));

		//Get the slip number and draw time 
		WebElement CurrentSlip = driver.findElement(By.xpath("(//td[contains(text(),\"Slip No : \")])[2]")); 
		String slip = CurrentSlip.getText();
		String cslip = slip.replaceAll("Slip No : ", "");
		System.out.println("Slip number for: "+ cslip);
		WebElement cdate = driver.findElement(By.xpath("//table[@class='vR_Slip_table']/tbody/tr[2]/td[3]")); 
		String canceldate = cdate.getText();

		SlipCancel.click();
		Thread.sleep(2000);

		//Clicking on Close icon on the pop-up
		WebElement cls = driver.findElement(By.xpath("(//span[@class='fa fa-times close_slip_JS'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", cls);
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

		//		WebElement searchnum = driver.findElement(By.xpath("//input[@placeholder='Draw #']"));  
		//		WebElement search = driver.findElement(By.xpath("//span[@ng-click='globalrouletteCtlr.GetFilterResults()']"));   
		//		searchnum.click();
		//		Thread.sleep(1000);
		//		searchnum.sendKeys(dnum);
		//		Thread.sleep(1000);
		//		search.click();
		//		Thread.sleep(2000);
		//		WebElement date = driver.findElement(By.xpath("//h6[@class='ng-binding']")); 
		//		System.out.println(date.getText());

		//Search draw number and fetch necessary details
		WebElement SD_drawtime = driver.findElement(By.xpath("//table[@class='vR_Slip_table']/tbody/tr[2]/td[3]"));    
		WebElement SD_slipnum = driver.findElement(By.xpath(" //table[@class='vR_Slip_table']/tbody/tr/td[1]"));    
		WebElement SD_drawnum = driver.findElement(By.xpath(" //table[@class='vR_Slip_table']/tbody/tr/td[2]"));  
		WebElement SD_print = driver.findElement(By.xpath("//div[@class='vR_Slip_Print']"));   

		//Validating the slip search details 
		Assert.assertEquals(slip, SD_slipnum.getText());
		Assert.assertEquals(SD_drawnum.getText().substring(10), dnum);
		String rdtime = SD_drawtime.getText().replaceAll("\\s", "");
		System.out.println(rdtime);
		String rdtime1 = canceldate.replaceAll("\\s", "");
		System.out.println(rdtime1);
		Assert.assertEquals(rdtime, rdtime1);
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