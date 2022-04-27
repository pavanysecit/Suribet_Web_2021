package Web_SportBetting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_SportBetting_SaveETicket_PreviewButton_Validations extends Web_SportBetting_URL{
	WebDriver driver;

	@Given("^Web: Chrome browser, suribet website valid URL, virtual SportsBetting module, Save-T-ticket, Preview button and print button$")
	public void web_Chrome_browser_suribet_website_valid_URL_virtual_SportsBetting_module_Save_T_ticket_Preview_button_and_print_button() throws Throwable {
		driver = Web_SportBetting_URL();
		Thread.sleep(2000);
	}

	@When("^Web: Select bet without login and check the functionalities of the above mentioned buttons$")
	public void web_Select_bet_without_login_and_check_the_functionalities_of_the_above_mentioned_buttons() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));
		
		//clicking on Draw details to place bet for the current draw 
		WebElement upmatch = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span"));
		upmatch.click();
		Thread.sleep(3000);
		WebElement gamename = driver.findElement(By.xpath("(//*[@class='position-relative d-flex flex-row ng-scope'])[9]/span[3]/parent::li/parent::ul/li/span"));  
		System.out.println(gamename.getText());
		WebElement stake = driver.findElement(By.xpath("//*[@ng-change='globalSportsCtlr.stakeTotalFun()']"));
		stake.click();
		Thread.sleep(1000); 
		stake.sendKeys("5");
		Thread.sleep(2000);

		WebElement SaveTicket = driver.findElement(By.xpath("//*[text()='Save T-Ticket']"));
		WebElement preview = driver.findElement(By.xpath("//*[text()='PREVIEW']"));
		Assert.assertEquals("Save T-Ticket", SaveTicket.getText());
		Assert.assertEquals("PREVIEW", preview.getText());

		SaveTicket.click();
		Thread.sleep(3000);

		WebElement ticket_popup = driver.findElement(By.cssSelector(".ticket_popup.displayblock")); 
		Assert.assertTrue(ticket_popup.isDisplayed());
		WebElement t_ticket = driver.findElement(By.cssSelector(".barcode.ng-binding.ng-scope")); 
		Assert.assertTrue(t_ticket.isDisplayed());
		driver.findElement(By.xpath("//*[@class='ticket_closebtn']")).click();
		Thread.sleep(2000);
		preview.click();
		Thread.sleep(3000);

		//Verifying the slip details
		WebElement gname = driver.findElement(By.xpath("(//*[@class='bslp_teams ng-binding'])[2]"));  
		System.out.println(gname.getText());
		boolean result1 = gamename.getText().equalsIgnoreCase(gname.getText());
		Assert.assertTrue(result1);

		WebElement printbutton = driver.findElement(By.xpath("(//*[@id='Single_BtnCartSubmit'])[2]")); 
		Assert.assertEquals("Save & Print T-Ticket", printbutton.getText());

		
		System.out.println("Verified the Save t ticket and preview button and the preview slip details");
	}

	@Then("^Web: Validate the functionalities of the above buttons$")
	public void web_Validate_the_functionalities_of_the_above_buttons() throws Throwable {
		driver.quit();
	}
}