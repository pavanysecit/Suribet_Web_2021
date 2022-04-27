package Web_VirtualSicbo_Old;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VSicbo_URL_Login {
	private static WebDriver driver;

	public WebDriver Web_VSicbo_URL_Login() throws InterruptedException, MalformedURLException {
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
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log Off")));
		Thread.sleep(2000);
		return driver;
	}
}