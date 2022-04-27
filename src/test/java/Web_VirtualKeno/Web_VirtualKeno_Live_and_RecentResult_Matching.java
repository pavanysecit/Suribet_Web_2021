package Web_VirtualKeno;

import java.util.ArrayList;
import java.util.Arrays;
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


public class Web_VirtualKeno_Live_and_RecentResult_Matching extends Web_VirtualKeno_URL_CardLogin{
WebDriver driver;

	public Web_VirtualKeno_Live_and_RecentResult_Matching() throws Exception {
		driver =Web_VirtualKeno_URL_CardLogin();
	}

	@Given("^Open the Chrome browser and Enter the valid url$")
	public void open_the_Chrome_browser_and_Enter_the_valid_url() throws Throwable 
	{
	
	}

	@When("^Enter the valid account id and password and login to online account$")
	public void enter_the_valid_account_id_and_password_and_login_to_online_account() throws Throwable
	{
		WebElement drawtime = driver.findElement(By.xpath("//div[@class='counter']/span"));
		String  drtime = drawtime.getText();
		
		WebDriverWait wait=new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='draw-result justify-content-sm-center d-flex order-1 order-sm-2 ready']")));
	
		String LiveResult = driver.findElement(By.xpath("//div[@class='sc-result-cube justify-content-sm-center d-flex']")).getText();
		List<String> list = Arrays.asList(LiveResult.split("\n"));
		for (String res_new : list)
		{
		}

	    List<Integer> listIntegers = new ArrayList<Integer>(list.size());
	    for(String current:list)
	    {
	     listIntegers.add(Integer.valueOf(current));
	    }
	    System.out.println("Live Result list Before sorting: "+ listIntegers);
	    Collections.sort(listIntegers); 
	    System.out.println("Live Result list After sorting: "+ listIntegers);
	    Thread.sleep(20000);
	    
		String RecentResult = driver.findElement(By.xpath("//div[@class='p-1']/div[1]/div[2]")).getText();
		List<String> list1 = Arrays.asList(RecentResult.split("\n"));
		for (String res_new1 : list1)
		{
		}
		List<Integer> listIntegers1 = new ArrayList<Integer>(list1.size());
	    for(String current1:list1)
	    {
	    listIntegers1.add(Integer.valueOf(current1));
	    }
	    
	    Collections.sort(listIntegers1); 
	    System.out.println("Most Recent Results winning Numbers are: " + listIntegers1);
	    Thread.sleep(1000);
		Assert.assertEquals(listIntegers, listIntegers1);		
		System.out.println("Live &  Recent result numbers are matching successfully");
		Thread.sleep(2000);	
	}

	@Then("^Compare the live result winning numbers with Recent result winning numbers$")
	public void compare_the_live_result_winning_numbers_with_Recent_result_winning_numbers() throws Throwable
	{
		
	}

	@Then("^Close the Keno browser application$")
	public void close_the_Keno_browser_application() throws Throwable
	{
	    driver.quit();
	}
}
