package Web_VirtualKeno;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualKeno_URL_OnlineLogin {

private static WebDriver driver;

	public WebDriver Web_VirtualKeno_URL_OnlineLogin() throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://bet.simbabet.com/VirtualKeno/VirtualKeno");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.navigate().refresh(); // Refresh Browser to load draw#
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement NextDraw = driver.findElement(By.xpath("//p[@class='text-center mb-2']"));
		wait.until(ExpectedConditions.visibilityOf(NextDraw));
		
		boolean logoPresent = driver.findElement(By.xpath("//img[@class='suribet_logo']")).isDisplayed();	//Make sure that the logo image name is always the same.
		Assert.assertTrue(logoPresent);
		System.out.println("Verifid Suribet Logo: Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("txtUserName")).sendKeys("pmansoorktr@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).sendKeys("mansoor@123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(11000);
		
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(2000);
		
		WebElement acc = driver.findElement(By.xpath("//span[@class='ac_id']"));
		wait.until(ExpectedConditions.visibilityOf(acc));
		String AcId=driver.findElement(By.xpath("//span[@class='ac_id']")).getText();
		String ExpAcID="9308481390";
		Assert.assertEquals(AcId, ExpAcID);
		System.out.println("AccountID is Verified successfully and Ac_ID: "+ AcId);			//verify Account ID
		Thread.sleep(2000);
		return driver;
	}
}
