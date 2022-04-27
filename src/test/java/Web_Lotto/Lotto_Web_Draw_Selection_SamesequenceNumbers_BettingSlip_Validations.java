package Web_Lotto;


import java.util.ArrayList;
import java.util.Collections;
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


public class Lotto_Web_Draw_Selection_SamesequenceNumbers_BettingSlip_Validations extends Lotto_Web_URL_OnlineLogin{
	WebDriver driver;

	@Given("^Web: Chrome browser, suribet website valid URL, lotto module, valid login, place the bet for current draw and validate the placed bets and select the same numbers for each row in current draw and verfiy$")
	public void web_Chrome_browser_suribet_website_valid_URL_lotto_module_valid_login_place_the_bet_for_current_draw_and_validate_the_placed_bets_and_select_the_same_numbers_for_each_row_in_current_draw_and_verfiy() throws Throwable {
		driver = Lotto_URL_OnlineLogin();
	    Thread.sleep(5000);
	    
	    WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement ACC = driver.findElement(By.xpath("//a[@ng-bind='AccountID']"));
		wait.until(ExpectedConditions.visibilityOf(ACC));
		Thread.sleep(5000);
	}
	
	@When("^Web: Login to lotto, select the current draw, select the betting numbers, select the same betting numbers again$")
	public void web_Login_to_lotto_select_the_current_draw_select_the_betting_numbers_select_the_same_betting_numbers_again() throws Throwable {
	    
		//selecting the current draw
		WebElement upmatch = driver.findElement(By.xpath("//*[@class='ul_clear in_R_up_ul']/child::li[1]"));
		upmatch.click();
		Thread.sleep(3000);
		
		//selecting of bet numbers
		WebElement stake6 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[6]"));
		stake6.click();
		Thread.sleep(1000);
		WebElement stake5 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[5]"));
		stake5.click();
		Thread.sleep(1000);
		WebElement stake4 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[4]"));
		stake4.click();
		Thread.sleep(1000);
		WebElement stake3 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[3]"));
		stake3.click();
		Thread.sleep(1000);
		WebElement stake2 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[2]"));
		stake2.click();
		Thread.sleep(1000);
		WebElement stake1 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[1]"));
		stake1.click();
		Thread.sleep(5000); 
	
		// verify the given list in ascending order
		ArrayList<String> obtained_list = new ArrayList<String>();
		List<WebElement> element_list = driver.findElements(By.xpath("//div[@class='_lottoNumber slideMenuFalse ng-binding']"));
		
		// printing the obtained elements in the list
		for (WebElement we : element_list) {
			obtained_list.add(we.getText());
			
		}
		
		//Adding string arraylist to Integer arraylist by converting  and printing
			ArrayList<Integer> obtained_int_list = new ArrayList<Integer>();
			for (String res_new : obtained_list) {
				obtained_int_list.add(Integer.valueOf(res_new));
				System.out.println("obtained List " + obtained_int_list);
			}
		
				
		// sorting the obtained_int_list and storing in sorted_list
		ArrayList<Integer> sorted_list = new ArrayList<Integer>();
		for (Integer ob : obtained_int_list) {
				sorted_list.add(ob);
		}
		
		// sorting the sorted list
		Collections.sort(sorted_list);
		
		System.out.println("--------------------------------------------------------");
		System.out.println("After Sorting the Numbers " +sorted_list);
		
		Assert.assertTrue(sorted_list.equals(obtained_int_list));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Verified the Bet Numbers is displaying in Ascending order");
		Thread.sleep(4000);	
		
		//Verifying the message on selecting the same sequence of numbers agian 
		stake1.click();
		Thread.sleep(2000);
		stake2.click();
		Thread.sleep(2000);
		stake3.click();
		Thread.sleep(2000);
		stake4.click();
		Thread.sleep(2000);
		stake5.click();
		Thread.sleep(2000);
		stake6.click();
		Thread.sleep(1500);
		
		//check both the numbers are same 
		 WebElement message = driver.findElement(By.xpath("//div[@ng-show='globalLottoCtrl.sliplimit']"));
		 boolean appeared = message.isDisplayed();
		 Assert.assertTrue(appeared);
		 Thread.sleep(2000);
		 System.out.println("The displayed message is "+message.getText());	
		 System.out.println("----------------------------------------------------------------------");
		 System.out.println("Successfully verified...");
		 System.out.println("You are placing the same numbers and the selected numbers are");
			
		 //Printing the numbers which was selected
		 System.out.println(obtained_list+"\t");
		 System.out.println("The series of Number already Exists ");		
	}
	
	@Then("^Web: Selected Bet Numbers should be displayed in Ascending Order in the betting slip$")
	public void web_Selected_Bet_Numbers_should_be_displayed_in_Ascending_Order_in_the_betting_slip() throws Throwable {
		
	}
	
	@Then("^Web: Validation message should be displayed in the betting slip for selecting same x`sequence of betting numbers for same draw$")
	public void web_Validation_message_should_be_displayed_in_the_betting_slip_for_selecting_same_x_sequence_of_betting_numbers_for_same_draw() throws Throwable {
		 Thread.sleep(2000);
		 driver.quit();
	}
}