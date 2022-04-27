package Web_Poker;

import java.text.DecimalFormat;
import java.util.List;
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

public class Web_Poker_MaxWinLimit_Last2Result_Validations extends Web_VirtualPoker_URL_OnlineLogin {
	WebDriver driver;
	
	public Web_Poker_MaxWinLimit_Last2Result_Validations() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}
	
	@Given("^Web: Chrome browser, suribet website valid URL, bet placing, all bet place combinations, user message and the last two draw results$")
	public void web_Chrome_browser_suribet_website_valid_URL_bet_placing_all_bet_place_combinations_user_message_and_the_last_two_draw_results() throws Throwable {
	    
	}

	@When("^Web: Login to suribet website with valid login details, Click on VirtualPoker module link, placed bet exceeding win amount more than (\\d+)$")
	public void web_Login_to_suribet_website_with_valid_login_details_Click_on_VirtualPoker_module_link_placed_bet_exceeding_win_amount_more_than(int arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
//		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement Mbal = driver.findElement(By.id("sn_after_login_info_Amount"));
		wait.until(ExpectedConditions.visibilityOf(Mbal));
		Thread.sleep(2000);

		//last win results to be displayed to user
		List<WebElement> RR = driver.findElements(By.xpath("//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO']"));
		System.out.println("Size of List: "+RR.size());
		Assert.assertTrue(driver.findElement(By.xpath("//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO']")).isDisplayed());
		Assert.assertTrue(!RR.isEmpty());
		Assert.assertEquals(2, RR.size() ,0.00);
		System.out.println("Recent draw results is not empty and displayed to user");

		WebElement cdraw = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.pokerkDrawslistFun(pokerCtlr.PokerDraws[0], 0)']/div/div")); 
		String cd = cdraw.getText().replaceAll("[^0-9]", "");
		int i=Integer.parseInt(cd); 
		System.out.println("current draw:"+cd);	
		WebElement past1 = driver.findElement(By.xpath("(//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO'])[1]/div/sapn")); 
		WebElement past2 = driver.findElement(By.xpath("(//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO'])[2]/div/sapn"));

		String pastdraw1 = past1.getText().replaceAll("[^0-9]", "");
		String pastdraw2 = past2.getText().replaceAll("[^0-9]", "");
		System.out.println("pastdraw1:"+pastdraw1);	
		System.out.println("pastdraw2:"+pastdraw2);	
		int i1=Integer.parseInt(pastdraw1);
		int i2=Integer.parseInt(pastdraw2);

		//Assert the last 2 draw numbers with the current draw and verify the only the last 2 draw results are displayed in the result section
		Assert.assertEquals(i, i1+1 , 0);
		Assert.assertEquals(i, i2+2 , 0);
		System.out.println("Verified the last two draw results are displayed to user");


		WebElement DrawNO = driver.findElement(By.xpath("(//*[@id='roul_drawsitem_w'])[9]"));
		DrawNO.click();
		Thread.sleep(2000);

		//Placed the bet where win exceeds more than 5000 SRD and verify if the bet can be placed
		WebElement Bet1 = driver.findElement(By.xpath("//*[text()='Royal Flush']"));
		WebElement Bet2 = driver.findElement(By.xpath("//*[text()='Straight Flush']"));
		WebElement Bet3 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']"));
		WebElement Bet4 = driver.findElement(By.xpath("//*[text()='Full House']"));
		WebElement Bet5 = driver.findElement(By.xpath("//*[text()='Flush']"));
		WebElement Bet6 = driver.findElement(By.xpath("//*[text()='Straight']"));
		WebElement Bet7 = driver.findElement(By.xpath("//*[text()='Three Of A Kind']"));
		WebElement Bet8 = driver.findElement(By.xpath("//*[text()='Two Pair']"));
		WebElement Bet9 = driver.findElement(By.xpath("//*[text()='One Pair']"));
		WebElement Bet10 = driver.findElement(By.xpath("//*[text()='High Card']"));

		//Win multiplier
		WebElement win1 = driver.findElement(By.xpath("//*[text()='Royal Flush']/../span[2]"));
		WebElement win2 = driver.findElement(By.xpath("//*[text()='Straight Flush']/../span[2]"));
		WebElement win3 = driver.findElement(By.xpath("//*[text()='Four Of A Kind']/../span[2]"));
		WebElement win4 = driver.findElement(By.xpath("//*[text()='Full House']/../span[2]"));
		WebElement win5 = driver.findElement(By.xpath("//*[text()='Flush']/../span[2]"));
		WebElement win6 = driver.findElement(By.xpath("//*[text()='Straight']/../span[2]"));
		WebElement win7 = driver.findElement(By.xpath("//*[text()='Three Of A Kind']/../span[2]"));
		WebElement win8 = driver.findElement(By.xpath("//*[text()='Two Pair']/../span[2]"));
		WebElement win9 = driver.findElement(By.xpath("//*[text()='One Pair']/../span[2]"));
		WebElement win10 = driver.findElement(By.xpath("//*[text()='High Card']/../span[2]/../span[2]")); 

		//Convert the win multiplier to decimal format and get the value above 5000
		double str = Double.parseDouble(win1.getText()); 
		double str1 = Double.parseDouble(win2.getText());
		double str2 = Double.parseDouble(win3.getText());
		double str3 = Double.parseDouble(win4.getText());
		double str4 = Double.parseDouble(win5.getText());
		double str5 = Double.parseDouble(win6.getText());
		double str6 = Double.parseDouble(win7.getText());
		double str7 = Double.parseDouble(win8.getText());
		double str8 = Double.parseDouble(win9.getText());
		double str9 = Double.parseDouble(win10.getText());

		double actwin = str+str1+str2+str3+str4+str5+str6+str7+str8+str9;
		// Stake is 100 hence multiplier is multiplied with the stake amount
		double actwin1= actwin*100;
		DecimalFormat f = new DecimalFormat("##.00");
		System.out.println(f.format(actwin1));

		WebElement Forward = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement chip = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr.selectChipFunData(6)']")); 
		WebElement Confirm = driver.findElement(By.xpath("//div[@ng-click='pokerCtlr.PlaceBetDetails(false)']"));
		WebElement Validmsg = driver.findElement(By.className("EMessage")); 

		JavascriptExecutor js = (JavascriptExecutor)driver; 
		double expwin = 5000.00;
		if(actwin1>=expwin) {

			System.out.println("Validate for the betlimit user message by placing bet");
			// select the chip denomination as "100" or the max set chip
			chip.click();
			Thread.sleep(2000);
			Bet1.click();
			Thread.sleep(2000);
			Bet2.click();
			Thread.sleep(2000);
			Bet3.click();
			Thread.sleep(2000);
			Bet4.click();
			Thread.sleep(2000);
			Bet5.click();
			Thread.sleep(2000);
			Bet6.click();
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", Bet7);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", Bet8);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", Bet9);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", Bet10);
			Thread.sleep(2000);

			//Place bet
			Forward.click();
			Thread.sleep(2000);
			Confirm.click();
			Thread.sleep(1500);
			wait.until(ExpectedConditions.visibilityOf(Validmsg));
			String Vmsg = Validmsg.getText();
			Assert.assertEquals("Bettype Limit not allowed", Vmsg);
			System.out.println("Bet type limit validation message: "+ Vmsg);
		} else
		{
			System.out.println("Bet can be placed as the win is lesser than the set max win limit");
			System.out.println("Recent result validations seuccessfull");
			driver.quit();
		}
	}

	@Then("^Web:  Validate the bet placing is not allowed where win amount exceeds '(\\d+)' and verify the last draw results are displayed to user$")
	public void web_Validate_the_bet_placing_is_not_allowed_where_win_amount_exceeds_and_verify_the_last_draw_results_are_displayed_to_user(int arg1) throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}