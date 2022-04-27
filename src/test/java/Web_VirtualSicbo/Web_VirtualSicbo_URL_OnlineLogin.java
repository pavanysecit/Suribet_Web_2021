package Web_VirtualSicbo;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualSicbo_URL_OnlineLogin {
	private static WebDriver driver;

	public WebDriver Web_VirtualSicbo_URL_OnlineLogin() throws InterruptedException, MalformedURLException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/VirtualSicBo/SicboHome");
		driver.manage().window().maximize();
		
		//clicking on login button and entering Invalid credentials
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
		WebElement UN = driver.findElement(By.id("txtUserName"));
		UN.sendKeys("pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement PWD = driver.findElement(By.xpath("//input[contains(@class, 'Password_JS')]"));
		PWD.sendKeys("mansoor@123");
		Thread.sleep(1000);
		driver.findElement(By.className("loginActive")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log Off")));
		Thread.sleep(2000);
		return driver;
	}
}