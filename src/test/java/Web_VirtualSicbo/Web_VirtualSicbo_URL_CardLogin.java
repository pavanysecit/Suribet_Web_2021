package Web_VirtualSicbo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualSicbo_URL_CardLogin {
private static WebDriver driver;
	
	public WebDriver Web_VirtualSicbo_URL_CardLogin() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/VirtualSicBo/SicboHome");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		
		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		UN.click();
		Thread.sleep(1000);
		UN.sendKeys("2111649489988826");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("1125");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(3000);
		return driver;
	}
}