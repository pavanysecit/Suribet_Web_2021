package Web_LiveSpin2Win;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Web_LiveSpin2Win_URL_OnlineLogin {

private static WebDriver driver;

	public WebDriver Web_LiveSpin2Win_URL_OnlineLogin() throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/LiveSpin2Win/LiveSpin2Win");
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(4);

		WebDriverWait wait = new WebDriverWait(driver, 120); 
		WebElement close = driver.findElement(By.xpath("//*[@class='sidebar_close']"));
		wait.until(ExpectedConditions.visibilityOf(close));
		TimeUnit.SECONDS.sleep(4);
		close.click();
		TimeUnit.SECONDS.sleep(4);
		
		WebElement UN = driver.findElement(By.xpath("//*[@placeholder='Email / Mobile / CardNo']"));
		UN.click();
		Thread.sleep(1000);
		UN.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("mansoor@123");
		Thread.sleep(2000);
		WebElement submit = driver.findElement(By.className("loginActive"));
		submit.click();
		Thread.sleep(10000);
		System.out.println("Login to Live Spin2Win module");
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(3000);
		System.out.println("cancelling the pop-up");
		return driver;
	}
}