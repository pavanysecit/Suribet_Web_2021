package Web_Lotto;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Lotto_Web_Bets_Selection_Qpick_5pick_10pick extends Lotto_Web_URL_OnlineLogin  {
	WebDriver driver;
	
	@Given("^Web: Web: Chrome browser, suribet website valid URL, lotto module, valid login,$")
	public void web_Web_Chrome_browser_suribet_website_valid_URL_lotto_module_valid_login() throws Throwable {
		driver = Lotto_URL_OnlineLogin();
	   
	    WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}

	@When("^Web: Login to lotto, select and click on Quickpick, (\\d+) pick, (\\d+) pick buttons$")
	public void web_Login_to_lotto_select_and_click_on_Quickpick_pick_pick_buttons(int arg1, int arg2) throws Throwable {
		
		//verifying Quick pick
		WebElement quick_pick = driver.findElement(By.xpath("(//i[@class='fa fa-hand-pointer-o'])[1]"));
		quick_pick.click();
		Thread.sleep(2000);
		
		//getting the value of rows
		WebElement row_count = driver.findElement(By.xpath("//label[text()='No Of Rows']/following-sibling::span"));
		boolean show = row_count.isDisplayed();
		Assert.assertTrue(show);
		String exp = "1";
		Assert.assertEquals(exp, row_count.getText());
		System.out.println("Verified the Quick Pick and the number of rows is "+row_count.getText());
		Thread.sleep(1000);
		
		WebElement clear_all = driver.findElement(By.xpath("//div[@ng-click='globalLottoCtrl.lottoCartClearAll()']"));
		clear_all.click();
		Thread.sleep(2000);
		
		//verifying 5pick
		WebElement _5pick = driver.findElement(By.xpath("(//i[@class='fa fa-hand-pointer-o'])[2]"));
		_5pick.click();
		Thread.sleep(2000);
		boolean show1 = row_count.isDisplayed();
		Assert.assertTrue(show1);
		String exp1 = "6";
		Assert.assertEquals(exp1, row_count.getText());
		System.out.println("Verified the _5Pick and the number of rows is "+row_count.getText());
		Thread.sleep(1000);
		
		//verifying 10pick
		WebElement _10pick = driver.findElement(By.xpath("(//i[@class='fa fa-hand-pointer-o'])[3]"));
		_10pick.click();
		Thread.sleep(2000);
		boolean show2 = row_count.isDisplayed();
		Assert.assertTrue(show2);
		String exp3 = "16";
		Assert.assertEquals(exp3, row_count.getText());
		System.out.println("Verified the _5Pick and the number of rows is "+row_count.getText());
		clear_all.click();
		Thread.sleep(3000);
		
		//check whether the given array list contains alphabets 
		quick_pick.click();
		Thread.sleep(2000);
		
		//list of elements
		List<String> str_qp_nums = new ArrayList<String>();
 		List<WebElement> qp_nums = driver.findElements(By.xpath("//div[@class='_lottoNumber slideMenuFalse ng-binding']"));
 		for(WebElement qp : qp_nums) {
 			str_qp_nums.add(qp.getText());
 		}
 		
 		String list_string="";
 		for(String s: str_qp_nums)
 		{
 			list_string += s + "\t";
 		}
 		
 		//verifying the alphabets present in the given list that is converted to string
 		boolean success = true;
 		for(char c = 'A';c <= 'Z'; ++c) {
 		    if(!list_string.contains(String.valueOf(c))) {
 		        success = false;
 		        break;
 		    }
 		}

 		if (success)
 		{
 		    System.out.println("String contains all alphabets");
 		}else{
 		    System.out.println("String DOESN'T contains all alphabets");
 		}
	}

	@Then("^Web: One row as one quick sequence of betting numbers should be displayed under betting slip$")
	public void web_One_row_as_one_quick_sequence_of_betting_numbers_should_be_displayed_under_betting_slip() throws Throwable {
	
	}

	@Then("^Web: Five rows as five sequence of betting numbers should be displayed under betting slip$")
	public void web_Five_rows_as_five_sequence_of_betting_numbers_should_be_displayed_under_betting_slip() throws Throwable {

	}

	@Then("^Web: Ten rows as ten sequence of betting numbers should be displayed under betting slip$")
	public void web_Ten_rows_as_ten_sequence_of_betting_numbers_should_be_displayed_under_betting_slip() throws Throwable {
	}

	@Then("^Web: On selecting quick, (\\d+) & (\\d+) pick, numbers should not be revealed in the betting slip$")
	public void web_On_selecting_quick_pick_numbers_should_not_be_revealed_in_the_betting_slip(int arg1, int arg2) throws Throwable {
	}

	@Then("^Web: Numbers should be revealed in the Print slip after placing the bet successfully$")
	public void web_Numbers_should_be_revealed_in_the_Print_slip_after_placing_the_bet_successfully() throws Throwable {
		driver.quit();
	}
}