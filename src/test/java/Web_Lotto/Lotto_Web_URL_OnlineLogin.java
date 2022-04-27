package Web_Lotto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lotto_Web_URL_OnlineLogin {

	private static WebDriver driver;
	
	public WebDriver Lotto_URL_OnlineLogin() throws InterruptedException {	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/LottoGame/LottoGame");
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
		password.sendKeys("mansoor@123");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(3000);
		WebElement welcome_popup = driver.findElement(By.xpath("//h4[@class='ng-binding']/descendant::i"));
		welcome_popup.click();
		Thread.sleep(2000);
		return driver;
	}	
}