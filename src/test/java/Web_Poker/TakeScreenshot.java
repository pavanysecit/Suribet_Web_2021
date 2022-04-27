package Web_Poker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TakeScreenshot {
	private static WebDriver driver;

	@Given("^Web: Chrome browser, poker module, win combinations and screenshots$")
	public void web_Chrome_browser_poker_module_win_combinations_and_screenshots() throws Throwable {
//				WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://10.200.10.44:11909/virtualpoker/home");
		driver.manage().window().maximize();
		Thread.sleep(4000);
	}

	@When("^Web: wait for river block to be active$")
	public void web_wait_for_river_block_to_be_active() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebDriverWait wait1 = new WebDriverWait(driver, 500);
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		wait.until(ExpectedConditions.visibilityOf(UN));
//		UN.click();
//		Thread.sleep(1000);
//		un.sendkeys("pmansoorktr@gmail.com");
//		Thread.sleep(1000);
//		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
//		password.click();
//		Thread.sleep(1000);
//		password.sendKeys("mans@123");
//		Thread.sleep(1000);
//		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
//		submit.click();
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
//		Thread.sleep(3000);

		//Take the screenshot    
		for(int i=0; i<=470; i++) {
			WebElement res = driver.findElement(By.xpath("//span[@class='p-button px-3 py-1 rounded']"));
			wait1.until(ExpectedConditions.visibilityOf(res));
			Thread.sleep(3000);
			driver.getTitle();
			String timestamp = new SimpleDateFormat("dd-MM-yy hh_mm_ss").format(new Date());
			WebElement drawnum = driver.findElement(By.xpath("//*[@class='p-slipScroll']/div/div/div/div[1]")); 
			String dnum= drawnum.getText().replace("ID:", "");
			System.out.println(drawnum.getText());
			String FileName = "Poker_"+dnum+"_"+timestamp;
			//Copy the file to a location and use try catch block to handle exception
			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("SURIBET_WEB/PokerScreenshots/"+FileName+".png"));
				System.out.println("Taken screenshots");
				Thread.sleep(1*40*1000);
				driver.getTitle();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println(i);
			System.out.print("\n");
			//driver.findElement(By.xpath("//*[@class='fa fa-barcode ']")).click();
			driver.navigate().refresh();
		}
	}

	@Then("^Web: take screenshots after each win for win combination matching$")
	public void web_take_screenshots_after_each_win_for_win_combination_matching() throws Throwable {

	}
}
