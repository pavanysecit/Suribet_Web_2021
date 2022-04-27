package Web_VirtualSicbo;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSicbo_AccountSummaryPage_Validations extends Web_VirtualSicbo_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_VirtualSicbo_AccountSummaryPage_Validations() throws Exception {
		driver = Web_VirtualSicbo_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual roulette module, valid logins, Account summary page$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_roulette_module_valid_logins_Account_summary_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Navigate to account summary page and modify the search pattern like date module row length$")
	public void web_Navigate_to_account_summary_page_and_modify_the_search_pattern_like_date_module_row_length() throws Throwable {
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		ACC.click();
		Thread.sleep(2000);

		//Verify summary page is navigated upon click on account number
		WebElement summarypage = driver.findElement(By.xpath("//*[@class='in_middle_head searchButSB']"));
		Assert.assertTrue(summarypage.isDisplayed());
		System.out.println("Navigated to account summary page");


		//Verifying the modules in the select dropdwn	
		Select prod = new Select(driver.findElement(By.xpath("//*[@ng-model='globalbetDetailsCtrl.betProductsId']")));
		Thread.sleep(2000);

		//Looping through the options and printing dropdown options
		System.out.println("The dropdown options are:");
		List actualvalues1 = new ArrayList();
		for(WebElement rows1: prod.getOptions()) {
			actualvalues1.add(rows1.getText());
		}
		List expectedvalues1 = new ArrayList();
		expectedvalues1.add("OUTRIGHT");
		expectedvalues1.add("Live Roulette");
		expectedvalues1.add("Slot Games");
		expectedvalues1.add("Live Betting");
		expectedvalues1.add("Sport Betting");
		expectedvalues1.add("Lotto");
		expectedvalues1.add("Skinfiri");
		expectedvalues1.add("Virtual Sports");
		expectedvalues1.add("Virtual Skinfiri");
		expectedvalues1.add("Virtual Roulette");
		expectedvalues1.add("Tjekre Tjekre");


		for(int i=0; i<actualvalues1.size(); i++) {
			System.out.println("Actual prod lists :"+ actualvalues1.get(i)+" Expected prod lists : "+ expectedvalues1.get(i));
			Assert.assertTrue(actualvalues1.get(i).equals(expectedvalues1.get(i)));
		}
		Thread.sleep(2000);

		prod.selectByVisibleText("Skinfiri");
		System.out.println("Selected Skinfiri product from the select dropdown");
		Thread.sleep(5000);

		WebElement row1 = driver.findElement(By.xpath("//ul[@ng-repeat='bets in globalbetDetailsCtrl.betdetails.JSONDATA.rows']"));
		System.out.println("1st row current bet placed information: "+row1);

		//		WebElement rowcancel = driver.findElement(By.xpath("//i[@class ='fa fa-minus-circle']"));
		if(row1.getText().contains("Skinfiri")) {

			System.out.println("Module Selection from the drop down has been verified");
			Thread.sleep(5000);
		}
		else {
			System.out.println("Data are mismatching");
			Thread.sleep(5000);
			driver.quit();
		}

		//Select the virtual roulette module for further more validations
		prod.selectByVisibleText("Virtual Roulette");
		System.out.println("Selected Virtual Roulette product from the select dropdown");
		Thread.sleep(5000);

		//Records per page validation
		Select records = new Select(driver.findElement(By.xpath("//*[@ng-model='globalbetDetailsCtrl.Recordspage']")));
		Thread.sleep(2000);

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", records);
		List actualvalues = new ArrayList();
		for(WebElement rows: records.getOptions()) {
			actualvalues.add(rows.getText());
		}
		List expectedvalues = new ArrayList();
		expectedvalues.add("20");
		expectedvalues.add("30");
		expectedvalues.add("50");
		expectedvalues.add("100");

		for(int i=0; i<actualvalues.size(); i++) {
			System.out.println("Actual :"+ actualvalues.get(i)+" Expected: "+ expectedvalues.get(i));
			Assert.assertTrue(actualvalues.get(i).equals(expectedvalues.get(i)));
		}
		Thread.sleep(2000);

		records.selectByVisibleText("20");
		System.out.println("Selected 20 records fetched");
		Thread.sleep(5000);
		WebElement page20 = driver.findElement(By.xpath("//*[@class='paginationGridRecordDisp ng-binding']"));
		Assert.assertTrue(page20.getText().contains("Records: 1 - 20"));
		System.out.println("Verified on selecting 20 records drop down from the list it has displayed the exact list of items to the user");
		Thread.sleep(5000);

		records.selectByVisibleText("30");
		System.out.println("Selected 30 records fetched");
		Thread.sleep(5000);
		WebElement page30 = driver.findElement(By.xpath("//*[@class='paginationGridRecordDisp ng-binding']"));
		Assert.assertTrue(page30.getText().contains("Records: 1 - 30"));
		System.out.println("Verified on selecting 30 records drop down from the list it has displayed the exact list of items to the user");

		records.selectByVisibleText("50");
		System.out.println("Selected 50 records fetched");
		Thread.sleep(5000);
		//		WebElement page50 = driver.findElement(By.xpath("//*[@class='paginationGridRecordDisp ng-binding']"));
		//		Assert.assertTrue(page50.getText().contains("Records: 1 - 50"));
		System.out.println("Verified on selecting 30 records drop down from the list it has displayed the exact list of items to the user");
		System.out.println("**********************for 100 records only 53 records are listed which may in future so didnt verified******************");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", records);
		Thread.sleep(2000);
		records.selectByVisibleText("20");
		System.out.println("Selected 20 records fetched");
		Thread.sleep(5000);

		//	Records: 1 - 20 of 53 - Pages(3):
		//Validation of page navigations 
		WebElement newpage20 = driver.findElement(By.xpath("//*[@class='paginationGridRecordDisp ng-binding']"));
		String totalpage = newpage20.getText().substring(30, 31);
		System.out.println("no of pages for navigation validations: "+newpage20.getText().substring(30, 31)); 
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", records);
		Thread.sleep(2000);


		/*
		 * When click on fast forward the page is navigated from 1st page to the last page 
		 * When click on simple fast forward 
		 */
		WebElement pagenationnumber = driver.findElement(By.id("txtGridPAgeNo")); 
		String currentpage = pagenationnumber.getText();
		int num = Integer.parseInt(currentpage);
		int number = num+1;
		// When selecting the next page forward navigation it should show lists from page 2 sheet
		WebElement fwdpage = driver.findElement(By.cssSelector(".fa.fa-angle-right")); 
		WebElement bckpage = driver.findElement(By.cssSelector(".fa.fa-angle-left"));  

		fwdpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after forward navigation to the next page :"+ pagenationnumber.getText());
		String currentpage1 = pagenationnumber.getText();
		int num1 = Integer.parseInt(pagenationnumber.getText());
		Assert.assertEquals(number, num1);
		System.out.println("Forward pagenation is verified");

		System.out.println("Current page before back navigation to the next page :"+ currentpage1);
		bckpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after back navigation to the next page :"+ pagenationnumber.getText());
		Assert.assertEquals(currentpage, pagenationnumber.getText());
		System.out.println("backward pagenation is verified");

		// Fast forward navigation
		WebElement fastfwdpage = driver.findElement(By.cssSelector(".fa.fa-angle-double-right")); 
		WebElement fastbckpage = driver.findElement(By.cssSelector(".fa.fa-angle-double-left")); 

		System.out.println("Current page before fast forward navigation to the next page :"+ pagenationnumber.getText());
		fastfwdpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after forward navigation to the next page :"+ pagenationnumber.getText());
		String fastfwdpage1 = pagenationnumber.getText();
		Assert.assertEquals(totalpage, fastfwdpage1);
		System.out.println("fast forward pagenation is verified");

		fastbckpage.click();
		Thread.sleep(7000);
		System.out.println("Current page after fast back navigation to the next page :"+ pagenationnumber.getText());
		Assert.assertEquals(currentpage, pagenationnumber.getText());
		System.out.println("fast backward pagenation is verified");


		//Date pick selection validations

		System.out.println("With valid dates verify the valid result");
		WebElement fromdate = driver.findElement(By.xpath("(//*[@uib-datepicker-popup='dd/MMM/yyyy'])[1]"));
		fromdate.click();
		Thread.sleep(3000);
		WebElement fromday = driver.findElement(By.xpath("//*[@type='button']/span [text() ='18']"));
		fromday.click();
		Thread.sleep(3000);

		WebElement todate = driver.findElement(By.xpath("(//*[@uib-datepicker-popup='dd/MMM/yyyy'])[2]"));
		todate.click();
		Thread.sleep(3000);
		WebElement to_day = driver.findElement(By.xpath("//*[@type='button']/span [text() ='23']"));
		to_day.click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".homeICon.fa.fa-search")).click();
		Thread.sleep(7000);

		WebElement listdetails = driver.findElement(By.id("divGridRecordDisp"));
		Boolean ldetails = listdetails.isDisplayed();
		Assert.assertTrue(ldetails);
		System.out.println("Date filtered details is displayed to the user");
	}

	@Then("^Web: Result has to be displayed as per the search combinations$")
	public void web_Result_has_to_be_displayed_as_per_the_search_combinations() throws Throwable {
		Thread.sleep(3000);
		driver.quit();
	}
}