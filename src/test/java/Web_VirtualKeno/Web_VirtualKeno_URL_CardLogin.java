package Web_VirtualKeno;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualKeno_URL_CardLogin {

private static WebDriver driver;
	
	public WebDriver Web_VirtualKeno_URL_CardLogin() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://bet.simbabet.com/VirtualKeno/VirtualKeno");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.navigate().refresh();	//Refresh Browser to load draw#
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement NextDraw = driver.findElement(By.xpath("//p[@class='text-center mb-2']"));
		wait.until(ExpectedConditions.visibilityOf(NextDraw));
		
		boolean logoPresent = driver.findElement(By.xpath("//img[@class='suribet_logo']")).isDisplayed();	//Make sure that the logo image name is always the same.
		Assert.assertTrue(logoPresent);
		System.out.println("Verifid Suribet Logo: Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("txtUserName")).sendKeys("2111649489988826");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(8000);	
		
//		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
//		Thread.sleep(2000);
		

		WebElement ww = driver.findElement(By.xpath("//span[@class='ac_id']"));
		wait.until(ExpectedConditions.visibilityOf(ww));
		String AcId=driver.findElement(By.xpath("//span[@class='ac_id']")).getText();
		System.out.println("Account ID: "+ AcId);
		String ExpAcID="4024953509";
		Assert.assertEquals(ExpAcID, AcId);
		System.out.println("AccountID is Verified successfully and Ac_ID: "+ AcId);
		Thread.sleep(2000);
		return driver;
	}
}