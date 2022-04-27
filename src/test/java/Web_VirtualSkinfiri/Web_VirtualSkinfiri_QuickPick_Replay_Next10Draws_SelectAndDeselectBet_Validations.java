package Web_VirtualSkinfiri;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_VirtualSkinfiri_QuickPick_Replay_Next10Draws_SelectAndDeselectBet_Validations extends Web_VirtualSkinfiri_URL_OnlineLogin {
WebDriver driver;

	public Web_VirtualSkinfiri_QuickPick_Replay_Next10Draws_SelectAndDeselectBet_Validations() throws Exception {
		driver =Web_VirtualSkinfiri_URL_OnlineLogin();
	} 
	/*
	 * Verify all the 32 draw number are present
	 */

	
	@Given("^Web: Chrome browser, suribet website valid URL, virtual skinfiri module,login via online method, place bet, quickpick (\\d+) and (\\d+), replay button and next (\\d+) draws$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_skinfiri_module_login_via_online_method_place_bet_quickpick_and_replay_button_and_next_draws(int arg1, int arg2, int arg3) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);

		// Fetch the account balance 
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
	}

	@When("^Web: Select the quick pick options like (\\d+) and (\\d+) and the next (\\d+) draws, later select some bet numbers and again click on the same to deselect from the betting tray, finally place bet and select the replay button$")
	public void web_Select_the_quick_pick_options_like_and_and_the_next_draws_later_select_some_bet_numbers_and_again_click_on_the_same_to_deselect_from_the_betting_tray_finally_place_bet_and_select_the_replay_button(int arg1, int arg2, int arg3) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);

		/*
		 * Verify all the 32 draw number are present for selection
		 * Verify the quick pick option
		 * Select and deselct the bets options
		 */

		List<WebElement> myElements = driver.findElements(By.xpath("//li[@ng-repeat='Bet in VDGCtr.vsBetNumber']"));
		System.out.println("Size of List: "+myElements.size()); 
		Assert.assertEquals(33, myElements.size() , 0);
		wait.until(ExpectedConditions.visibilityOfAllElements(myElements));
		Assert.assertTrue(!myElements.isEmpty());
		System.out.println("Total numbers for bet selection is available to user and displayed");
		Thread.sleep(3000);

		WebElement Quick5 = driver.findElement(By.xpath("(//*[@class='box-pick pick-value ng-scope'])[1]"));
		WebElement Quick10 = driver.findElement(By.xpath("(//*[@class='box-pick pick-value ng-scope'])[2]"));  

		Quick5.click();
		Thread.sleep(4000);

		List<WebElement> QuickPick5 = driver.findElements(By.xpath("//*[@class='bet_slip_Sport_w vs_bet_slip_w slideMenuFalse  ng-scope']"));
		System.out.println("Size of QuickPick 5 : "+QuickPick5.size()); 
		Assert.assertEquals(5, QuickPick5.size() , 0);
		System.out.println("QuickPick 5 bets selected and is available to user and displayed");
		Thread.sleep(3000);

		//Clear the quick pick selection
		WebElement Clear = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.fn_btnClear()']")); 
		Clear.click();
		Thread.sleep(3000);

		Quick10.click();
		Thread.sleep(4000);
		List<WebElement> QuickPick10 = driver.findElements(By.xpath("//*[@class='bet_slip_Sport_w vs_bet_slip_w slideMenuFalse  ng-scope']"));
		System.out.println("Size of QuickPick 10 : "+QuickPick10.size()); 
		Assert.assertEquals(10, QuickPick10.size(), 0);
		System.out.println("QuickPick 10 bets selected and is is available to user and displayed");
		Clear.click();
		Thread.sleep(4000);

		//Select and deselect the on click of same bets

		//Select the Numbers 
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[4]"));
		WebElement Pick6 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[6]"));
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[1]"));

		Pick3.click();
		Thread.sleep(1000);
		Pick6.click();
		Thread.sleep(1000);
		Pick2.click();
		Thread.sleep(2000);

		WebElement TotalRows = driver.findElement(By.xpath("//*[@class= 'skinfri_stake_bwt_w sport_stake_bwt_w']/div/span[2]")); 
		int beforerows = Integer.parseInt(TotalRows.getText());

		//Deselect the numbers
		Pick6.click();
		Thread.sleep(1000);
		Pick2.click();
		Thread.sleep(2000);

		int afterrows = Integer.parseInt(TotalRows.getText());
		Assert.assertEquals(beforerows, afterrows+2, 0);
		System.out.println("Selection and deslection the numbers from the number table is validated");

		Clear.click();
		Thread.sleep(3000);
	}

	@Then("^Web: Validate the quick pick options as same random bet numbers are selected at the betting tray$")
	public void web_Validate_the_quick_pick_options_as_same_random_bet_numbers_are_selected_at_the_betting_tray() throws Throwable {
		/*
		 * Verify the next 10 draws selected to place bet
		 */

		WebElement next10draws = driver.findElement(By.xpath("//*[@ng-click= 'VDGCtr.SelectNthDraw(VDGCtr.NextNthDraw)']"));

		next10draws.click();
		Thread.sleep(2000);

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		boolean Vmsg = Validmsg.getText().contains("Please Select Bet Number");
		System.out.println("Bet selection prompt user message: "+ Vmsg);
		Assert.assertTrue(Vmsg);

		//Select the Numbers 
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[4]"));

		Pick3.click();
		Thread.sleep(2000);
		next10draws.click();
		Thread.sleep(3000);

		WebElement cdraw = driver.findElement(By.xpath("//*[@class='c_draweno_W']/span")); 
		String sd = cdraw.getText().replaceAll("[^0-9]", "");
		int sd1 = Integer.parseInt(sd);  

		List<WebElement> draws = driver.findElements(By.xpath("//*[@class='bet_slip_Sport_w vs_bet_slip_w slideMenuFalse  ng-scope']"));
		System.out.println("Size of Next 10 draws : "+draws.size()); 
		Assert.assertEquals(10, draws.size() , 0);
		System.out.println("Next 10 draws bets selected and is available to user and displayed");
		Thread.sleep(3000);

		WebElement firstrawnum = driver.findElement(By.xpath("(//*[@class='bet_slip_Sport_w vs_bet_slip_w slideMenuFalse  ng-scope'])[10]/div[2]"));  
		WebElement lastdrawnum = driver.findElement(By.xpath("//*[@class='bet_slip_Sport_w vs_bet_slip_w slideMenuFalse  ng-scope']/div[2]")); 

		String exp = firstrawnum.getText().replaceAll("[^0-9]", "");
		Assert.assertEquals(sd, exp);

		int exp1 = Integer.parseInt(lastdrawnum.getText().replaceAll("[^0-9]", "")); 
		System.out.println("First draw num: "+sd1 + "\t" +"Last daw number: "+ exp1);

		//Verify the validation
		int validate;
		System.out.println(validate = (exp1-sd1)-9);
		Assert.assertTrue(validate==0);
		Assert.assertEquals(sd1+9 , exp1 , 0);
		System.out.println("Next 10 draws selection is verified ");

		WebElement Clear = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.fn_btnClear()']")); 
		Clear.click();
		Thread.sleep(4000);
	}

	@Then("^Web: Validate the next (\\d+) draws as the next draws are selected to place bet at the betting tray$")
	public void web_Validate_the_next_draws_as_the_next_draws_are_selected_to_place_bet_at_the_betting_tray(int arg1) throws Throwable {
		/*
		 * Verify the replay button validations
		 */

		//clicking on Draw details to place bet for the current draw 
		WebElement DrawNO = driver.findElement(By.xpath("//ul[@class='ul_clear  v_s_match_list']/li[9]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Select the Numbers
		WebElement Pick2 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[1]"));
		WebElement Pick3 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[2]"));
		
		Pick2.click();
		Thread.sleep(1000);
		Pick3.click();
		Thread.sleep(1000);
		System.out.println("Bets selected on the number table");

		String attribute = Pick2.getAttribute("class");
		String attribute1 = Pick3.getAttribute("class");
		String text = Pick2.getText();
		String text1 = Pick3.getText();
		
		WebElement Bsnum1 = driver.findElement(By.xpath("//*[@class='vs_s_bet_no_slip ng-binding']")); 
		WebElement Bsnum = driver.findElement(By.xpath("(//*[@class='vs_s_bet_no_slip ng-binding'])[2]")); 
		String bsnum = Bsnum.getText();
		String bsnum1 = Bsnum1.getText();
		
		//Serial numbers validations
		String snum1 = driver.findElement(By.xpath("(//*[@class='fa fa-times slip_close slideMenuFalse ']/../span[2])[1]")).getText();	
		String snum = driver.findElement(By.xpath("(//*[@class='fa fa-times slip_close slideMenuFalse ']/../span[2])[2]")).getText();	
		
		//Input the stake amount 
		WebElement stkfield = driver.findElement(By.xpath("//*[@ng-change='VDGCtr.stakeTotalFun()']")); 
		stkfield.click();
		Thread.sleep(1000);
		stkfield.sendKeys("1");
		Thread.sleep(2000);

		WebElement Bsstk = driver.findElement(By.xpath("//*[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div/span[2]")); 
		String bsstk = Bsstk.getText(); 

		//Place the bet with the default stake
		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='VDGCtr.PlaceBet(false)']"));

		//FORWARD AND PLACE BET 
		Forward.click();
		Thread.sleep(5000);
		System.out.println("Bet placed and waiting for validation message displayed to the user");

		//Validate msg for successful bet place
		WebElement Validmsg = driver.findElement(By.id("DivAlerts_Cart")); 
		boolean Vmsg = Validmsg.getText().contains("Bet has been Placed Successfully");
		System.out.println("Placed bet successful user message displayed to user: "+ Vmsg);
		Assert.assertTrue(Vmsg);
		Thread.sleep(3000);

		WebElement replay = driver.findElement(By.xpath("//*[@ng-click='VDGCtr.Replay()']"));  
		replay.click();
		Thread.sleep(3000);

		WebElement Pick02 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[1]"));
		String attribute11 = Pick02.getAttribute("class");
		WebElement Pick01 = driver.findElement(By.xpath("(//li[@ng-repeat='Bet in VDGCtr.vsBetNumber'])[2]"));
		String attribute22 = Pick01.getAttribute("class");
		
		String text11 = Pick02.getText();
		String text22 = Pick01.getText();
		
		WebElement Bsnum11 = driver.findElement(By.xpath("(//*[@class='vs_s_bet_no_slip ng-binding'])[2]")); 
		String bsnum11 = Bsnum11.getText();
		WebElement Bsnum22 = driver.findElement(By.xpath("//*[@class='vs_s_bet_no_slip ng-binding']")); 
		String bsnum22 = Bsnum22.getText();
		String Snum1 = driver.findElement(By.xpath("(//*[@class='fa fa-times slip_close slideMenuFalse ']/../span[2])[1]")).getText();	
		String Snum = driver.findElement(By.xpath("(//*[@class='fa fa-times slip_close slideMenuFalse ']/../span[2])[2]")).getText();	
		
		WebElement Bsstk1 = driver.findElement(By.xpath("//*[@class='skinfri_stake_bwt_w sport_stake_bwt_w']/div/span[2]")); 
		String bsstk1 = Bsstk1.getText(); 


		//Validation on the same number is selected from the table upon replay button and same is displayed over the betting tray
		Assert.assertEquals(attribute, attribute11);
		Assert.assertEquals(attribute1, attribute22);
		Assert.assertEquals(text, text11);
		Assert.assertEquals(text1, text22);
		Assert.assertEquals(bsnum, bsnum11);
		Assert.assertEquals(bsnum1, bsnum22);
		Assert.assertEquals(snum1, Snum1);
		Assert.assertEquals(snum, Snum);
		
		
		Assert.assertEquals(bsstk, bsstk1);
		System.out.println("Validation on the replay button number selection is successfull");
		Thread.sleep(3000);
	}

	@Then("^Web: Verify the select and deselect options and place bet successfully and validate the replay button option$")
	public void web_Verify_the_select_and_deselect_options_and_place_bet_successfully_and_validate_the_replay_button_option() throws Throwable {
	    driver.quit();
	}
}