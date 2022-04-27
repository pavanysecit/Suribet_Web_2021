package Web_LiveSpin2Win;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Web_LiveSpin2Win_BetCombination_Validations extends Web_LiveSpin2Win_URL_OnlineLogin {
	WebDriver driver;
	
	WebDriverWait wait = new WebDriverWait(driver, 120); 
	
	public Web_LiveSpin2Win_BetCombination_Validations() throws Exception {
		driver =Web_LiveSpin2Win_URL_OnlineLogin();
	}

	
	@Given("^Web: Chrome browser suribet website valid URL LiveSpin(\\d+)Win module tropicana bet table red split, black split, complete bet and neighbourings$")
	public void web_Chrome_browser_suribet_website_valid_URL_LiveSpin_Win_module_tropicana_bet_table_red_split_black_split_complete_bet_and_neighbourings(int arg1) throws Throwable {
		/*
		 * Complete bet is selected and all the combinations 
		 */

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		WebElement active = driver.findElement(By.xpath("//*[@class='lv-mn-sc-table']/div")); 
		String activeid = active.getAttribute("id");
		Assert.assertEquals("w-100 h-100 d-flex justify-content-center m-lg-1 p-1", activeid);
		Thread.sleep(2000); 

		WebElement Complete = driver.findElement(By.xpath("//*[text()='Complete Bet']")); 
		Complete.click();
		Thread.sleep(2000);


		String inactiveid = active.getAttribute("id");
		Assert.assertEquals("w-100 h-100 d-flex justify-content-center m-lg-1 p-1 ready-complete-bet", inactiveid);
		Thread.sleep(2000); 

		/*
		 * Place the bet as it selects all the bet combination for the particular number
		 */


		WebElement Pick8 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='8']"));

		Pick8.click();
		Thread.sleep(1000);
		System.out.println("Bet selected on the live Roulette table for complete bet");

		WebElement cbet = driver.findElement(By.xpath("//*[text()='Complete Bet']")); 
		String compbet8 = cbet.getText();
		Assert.assertEquals("Complete Bet - 8", compbet8);
		Thread.sleep(2000);

		WebElement crows = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));   
		WebElement cstake = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span")); 
		Assert.assertEquals("1", crows.getText());
		Assert.assertEquals("40.00", cstake.getText());

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)']"));
		Cancel.click();
		Thread.sleep(2000);

		/*
		 *  Selecting multiple bets for complete bet selection to verify the stake and rows selection and the different numbers have different sequence of bet around it
		 */

		WebElement Pick0 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='0']"));
		WebElement Pick34 = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='34']"));

		Pick0.click();
		Thread.sleep(1000);
		Pick34.click();
		Thread.sleep(1000);

		Assert.assertEquals("2", crows.getText());
		Assert.assertEquals("35.00", cstake.getText());

		Cancel.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);
		/*
		 * Verifying the black and red split button validation
		 * red split has 4SRD stake 
		 * black split has 7SRD stake
		 */


		WebElement redsplit = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='0']"));
		WebElement blacksplit = driver.findElement(By.xpath("//*[@ng-repeat= 'b in grLiveCtlr.numMiddle_1']/div[text() ='34']"));

		redsplit.click();
		Thread.sleep(1000);

		Assert.assertEquals("1", crows.getText());
		Assert.assertEquals("4.00", cstake.getText());

		blacksplit.click();
		Thread.sleep(1000);

		Assert.assertEquals("2", crows.getText());
		Assert.assertEquals("11.00", cstake.getText());

		WebElement redsplitcancel = driver.findElement(By.xpath("(//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)'])[2]")); 
		redsplitcancel.click();
		Thread.sleep(1000);

		Assert.assertEquals("1", crows.getText());
		Assert.assertEquals("7.00", cstake.getText());
		Cancel.click();
		Thread.sleep(2000);

		/*
		 * Neighbouring validation for the new table as selecting from 1 to 9  neighbours for validations
		 */
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		WebElement ngh = driver.findElement(By.xpath("//*[@class='cls-22 ng-binding']")); 
		Assert.assertEquals("1", ngh.getText());

		WebElement c3 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,3)']")); 
		c3.click();
		Thread.sleep(2000);

		//Select the number 3 with selecting other 2 neighbouring bets
		WebElement t1num = driver.findElement(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)[1]")); 
		WebElement t2num = driver.findElement(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)[2]")); 
		WebElement t3num = driver.findElement(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)[3]")); 

		Assert.assertEquals("chip_selected chip0 chip_selected", t1num.getAttribute("class"));
		Assert.assertEquals("chip_selected chip0 chip_selected", t2num.getAttribute("class"));
		Assert.assertEquals("chip_selected chip0 chip_selected", t3num.getAttribute("class"));

		WebElement betrows = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));
		WebElement betamount = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span"));

		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("3.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 1 is verified"); 
		Cancel.click();
		Thread.sleep(2000);

		// Selecting the neighbouring from 2 to 9

		WebElement inc = driver.findElement(By.xpath("//*[@class='cls-21']")); 
		WebElement dec = driver.findElement(By.xpath("//*[@class='cls-20']"));   

		/*
		 * 2 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("2", ngh.getText());

		WebElement c20 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,20)']")); 
		c20.click();
		Thread.sleep(2000);

		List<WebElement> t2 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t2size = t2.size();
		Assert.assertEquals(5, t2size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("5.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 2 is verified"); 

		Cancel.click();
		Thread.sleep(2000);

		/*
		 * 3 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("3", ngh.getText());

		WebElement c0 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,0)']")); 
		c0.click();
		Thread.sleep(2000);

		List<WebElement> t3 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t3size = t3.size();
		Assert.assertEquals(7, t3size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("7.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 3 is verified"); 	

		Cancel.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);


		/*
		 * 4 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("4", ngh.getText());

		WebElement c9 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,9)']")); 
		c9.click();
		Thread.sleep(2000);

		List<WebElement> t4 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t4size = t4.size();
		Assert.assertEquals(5, t4size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("9.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 4 is verified"); 

		Cancel.click();
		Thread.sleep(2000);

		/*
		 * 5 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("5", ngh.getText());

		WebElement c23 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,23)']")); 
		c23.click();
		Thread.sleep(2000);

		List<WebElement> t5 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t5size = t5.size();
		Assert.assertEquals(7, t5size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("11.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 5 is verified"); 	

		Cancel.click();
		Thread.sleep(2000);

		/*
		 * 6 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("6", ngh.getText());

		WebElement c11 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,11)']")); 
		c11.click();
		Thread.sleep(2000);

		List<WebElement> t6 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t6size = t6.size();
		Assert.assertEquals(6, t6size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("13.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 6 is verified"); 

		Cancel.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);

		/*
		 * 7 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("7", ngh.getText());

		WebElement c12 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,12)']")); 
		c12.click();
		Thread.sleep(2000);

		List<WebElement> t7 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t7size = t7.size();
		Assert.assertEquals(7, t7size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("15.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 7 is verified"); 
		Cancel.click();
		Thread.sleep(2000);



		/*
		 * 8 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("8", ngh.getText());

		WebElement c19 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,19)']")); 
		c19.click();
		Thread.sleep(2000);

		List<WebElement> t8 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t8size = t8.size();
		Assert.assertEquals(8, t8size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("17.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 8 is verified"); 
		Cancel.click();
		Thread.sleep(2000);

		/*
		 * 9 neighbours
		 */
		inc.click();
		Thread.sleep(2000);
		Assert.assertEquals("9", ngh.getText());

		WebElement c1 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,1)']")); 
		c1.click();
		Thread.sleep(2000);

		List<WebElement> t9 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t9size = t9.size();
		Assert.assertEquals(9, t9size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("19.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 9 is verified"); 


		Cancel.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Place your bets please']"))));
		TimeUnit.SECONDS.sleep(2);



		/* decrease from 9 to 6 and verify the decreasing neighbouring are working as exxpected
		 * 6 neighbours
		 */
		dec.click();
		Thread.sleep(2000);
		dec.click();
		Thread.sleep(2000);
		dec.click();
		Thread.sleep(2000);
		Assert.assertEquals("06", ngh.getText());

		WebElement c36 = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.addSliprouletteNeighbour(163,36)']")); 
		c36.click();
		Thread.sleep(2000);

		List<WebElement> t06 = driver.findElements(By.xpath("(//*[@ng-repeat='Bp in b.BpIds']/label)")); 
		int t06size = t06.size();
		Assert.assertEquals(6, t06size, 0);
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("13.00", betamount.getText());
		System.out.println("Neighbouring bet selection for neighbouring 6 is verified"); 
		Cancel.click();
		Thread.sleep(2000);
	}

	@When("^Web: login to tropicana table place bets on all available combination bets available to the user$")
	public void web_login_to_tropicana_table_place_bets_on_all_available_combination_bets_available_to_the_user() throws Throwable {
		/*
		 * Selecting the given Tiers, orphelins, voisins and zero spiel 
		 * for the validation of bets beting selected on the table and are active
		 * 
		 */


		WebElement Tiers = driver.findElement(By.xpath("(//*[@class='cls-4 svg-spl-path ng-scope'])[1]")); 
		WebElement orphelins = driver.findElement(By.xpath("(//*[@class='cls-4 svg-spl-path ng-scope'])[2]")); 
		WebElement voisins = driver.findElement(By.xpath("(//*[@class='cls-4 svg-spl-path ng-scope'])[3]")); 
		WebElement spiel = driver.findElement(By.xpath("(//*[@class='cls-4 svg-spl-path ng-scope'])[4]")); 

		Tiers.click();
		Thread.sleep(2000); 

		WebElement betrows = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div/span"));
		WebElement betamount = driver.findElement(By.xpath("//*[@class='table-responsive']/following-sibling::div/div[2]/span"));
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("6.00", betamount.getText());

		//Cancel the placed bet
		WebElement Cancel = driver.findElement(By.xpath("//*[@ng-click='grLiveCtlr.RemoveBetPickFromCartDetails(cartD.bpId,$index)']"));
		Cancel.click();
		Thread.sleep(2000);


		orphelins.click();
		Thread.sleep(2000); 
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("5.00", betamount.getText());
		Cancel.click();
		Thread.sleep(2000);

		voisins.click();
		Thread.sleep(2000); 
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("9.00", betamount.getText());
		Cancel.click();
		Thread.sleep(2000);

		spiel.click();
		Thread.sleep(2000); 
		Assert.assertEquals("1", betrows.getText());
		Assert.assertEquals("4.00", betamount.getText());
		Cancel.click();
		Thread.sleep(2000);
	}

	@Then("^Web: Validate the appropriate combinations are selected on the betting tray for the user to place bets$")
	public void web_Validate_the_appropriate_combinations_are_selected_on_the_betting_tray_for_the_user_to_place_bets() throws Throwable {
	   driver.quit();
	}
}
