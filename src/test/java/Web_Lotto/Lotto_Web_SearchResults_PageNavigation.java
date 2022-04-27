package Web_Lotto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lotto_Web_SearchResults_PageNavigation extends Lotto_Web_URL{
WebDriver driver;

	@Given("^Web: Chrome browser, suribet website valid URL, lotto module, Lotto Results link, forward & backward button in the result page$")
	public void web_Chrome_browser_suribet_website_valid_URL_lotto_module_Lotto_Results_link_forward_backward_button_in_the_result_page() throws Throwable {
		driver = Lotto_Web_URL();
	    
	}

	@When("^Web: Open the lotto module, navigate to Lotto result page, search data by different criteria, cross check the data with search data and navigate to different pages$")
	public void web_Open_the_lotto_module_navigate_to_Lotto_result_page_search_data_by_different_criteria_cross_check_the_data_with_search_data_and_navigate_to_different_pages() throws Throwable {
	    WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));
		
		//clicking on Lotto Result 
		WebElement lresults = driver.findElement(By.xpath("//div[@class='in_lotto_button']"));
		lresults.click();
		Thread.sleep(15000);
		
		//Search by draw no
//		WebElement row = driver.findElement(By.xpath("//div[@class='in_middle_con latest_win_W resultLotto lotto-Result']"));
//		wait.until(ExpectedConditions.visibilityOf(row));
		
		WebElement drawno = driver.findElement(By.xpath("//input[@ng-model='globalLottoCtrl.resultDraw']"));  		
		drawno.sendKeys("1400");
		String dno = drawno.getAttribute("value");
		System.out.println("Drawno: " +dno);
		Thread.sleep(2000);
		
		WebElement search = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.GetLottoResult(1)']"));
		search.click();
		Thread.sleep(3000);
		
		WebElement retdrawno = driver.findElement(By.xpath("//div[@class='in_middle_con latest_win_W resultLotto lotto-Result']/div/label"));
		retdrawno.getText();
		System.out.println("Retrived Drawno: " +retdrawno.getText());
		Thread.sleep(1000); 
		String rrdrno = retdrawno.getText().substring(0, 4);
		System.out.println("Retrived Drawno: " +rrdrno);
		Assert.assertEquals(dno, rrdrno);
		drawno.clear();
		Thread.sleep(2000);
		
		//Search by From date and To date
		WebElement num = driver.findElement(By.xpath("//*[@ng-model='globalLottoCtrl.resultFrom']"));
		LocalDate date = LocalDate.now().minusDays(5);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        String output = dtf.format(date);
        System.out.println(output);
		num.sendKeys(output);
		
		WebElement num1 = driver.findElement(By.xpath("//*[@ng-model='globalLottoCtrl.resultTo']"));
		LocalDate date1 = LocalDate.now().minusDays(5);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        String output1 = dtf1.format(date1);
        System.out.println(output1);
		num1.sendKeys(output1);
		
		search.click();
		Thread.sleep(15000);
		
//		WebElement drow = driver.findElement(By.xpath("//*[@class='in_middle_con latest_win_W resultLotto lotto-Result']/div/label"));
//		drow.getText();
		DateTimeFormatter dtf11 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String output11 = dtf11.format(date);
        System.out.println("From Date selected for search: " +output11);
		DateTimeFormatter dtf111 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String output111 = dtf111.format(date1);
       System.out.println("Todate selected for search: " +output111);
		String actualString = driver.findElement(By.xpath("//*[@class='in_middle_con latest_win_W resultLotto lotto-Result']/div/label")).getText();
		System.out.println("Result retrieved for search result: " +actualString);
		Assert.assertTrue(actualString.contains(output11));
		Assert.assertTrue(actualString.contains(output111));
		num.clear();
		num1.clear();
		
		//Search by Letter M
		WebElement letter = driver.findElement(By.xpath("//*[@ng-model='globalLottoCtrl.resultDrawLetter']"));
		letter.sendKeys("M");
		search.click();
		Thread.sleep(15000);
		String ltr = letter.getAttribute("value");
		System.out.println("Letter entered for search: " +ltr);
		
		String retltr = driver.findElement(By.xpath("(//ul[@class='ul_clear']/li[7])[2]")).getText();
		System.out.println("Result retrieved after search for letter: " +retltr);
		Assert.assertEquals(ltr, retltr);
		letter.clear();
		
		//Search by Draw Number
		//*[@class='in_middle_con latest_win_W resultLotto lotto-Result']/ul
		
		//Records per page validation
		Select records = new Select(driver.findElement(By.xpath("//*[@ng-model='globalLottoCtrl.lottorecordsperpage']")));
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
		Thread.sleep(10000);
		WebElement page20 = driver.findElement(By.xpath("//div[@class='paginationGridRecordDisp ng-binding']"));
		System.out.println("Print" +page20);
		Assert.assertTrue(page20.getText().contains("Records: 1 - 20"));
		System.out.println("Verified on selecting 20 records drop down from the list it has displayed the exact list of items to the user");
		Thread.sleep(5000);

		records.selectByVisibleText("50");
		System.out.println("Selected 50 records fetched");
		Thread.sleep(20000);
		WebElement page50 = driver.findElement(By.xpath("//div[@class='paginationGridRecordDisp ng-binding']"));
		Assert.assertTrue(page50.getText().contains("Records: 1 - 50"));
		System.out.println("Verified on selecting 50 records drop down from the list it has displayed the exact list of items to the user");

		records.selectByVisibleText("30");
		System.out.println("Selected 30 records fetched");
		Thread.sleep(20000);
		WebElement page30 = driver.findElement(By.xpath("//div[@class='paginationGridRecordDisp ng-binding']"));
		Assert.assertTrue(page30.getText().contains("Records: 1 - 30"));
		System.out.println("Verified on selecting 30 records drop down from the list it has displayed the exact list of items to the user");
		
		records.selectByVisibleText("100");
		System.out.println("Selected 30 records fetched");
		Thread.sleep(20000);
		WebElement page100 = driver.findElement(By.xpath("//div[@class='paginationGridRecordDisp ng-binding']"));
		Assert.assertTrue(page100.getText().contains("Records: 1 - 100"));
		System.out.println("Verified on selecting 100 records drop down from the list it has displayed the exact list of items to the user");
		
//		System.out.println("**********************for 100 records only 61 records are listed which may in future so didnt verified******************");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", records);
		Thread.sleep(2000);
		records.selectByVisibleText("20");
		System.out.println("Selected 20 records fetched");
		Thread.sleep(20000);
		
		
	}

	@Then("^Web: Search result should match with the retrived data based on the search input$")
	public void web_Search_result_should_match_with_the_retrived_data_based_on_the_search_input() throws Throwable {
	    
	}
	
	@Then("^Web: User should be navigated to different page based on the user input$")
	public void web_User_should_be_navigated_to_different_page_based_on_the_user_input() throws Throwable {
	    Thread.sleep(2000);
	    driver.quit();
	}
}