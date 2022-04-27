package Web_Poker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualPoker_URL_OnlineLogin {
private static WebDriver driver;

	public WebDriver Web_VirtualPoker_URL_OnlineLogin() throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://10.200.10.44:11909/virtualpoker/home");
		driver.manage().window().maximize();
		Thread.sleep(4000);

		WebElement UN = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		UN.click();
		Thread.sleep(1000);
		UN.sendKeys("Pmansoorktr@gmail.com");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		password.click();
		Thread.sleep(1000);
		password.sendKeys("mans@123");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(10000);
		System.out.println("Login to Poker module");
		driver.findElement(By.cssSelector(".fa.fa-times-circle-o")).click();
		Thread.sleep(3000);
		System.out.println("cancelling the pop-up");
		return driver;

	}
}