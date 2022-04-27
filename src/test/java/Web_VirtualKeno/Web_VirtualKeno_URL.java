package Web_VirtualKeno;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualKeno_URL {

private static WebDriver driver;

	public WebDriver Web_VirtualKeno_URL() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://bet.simbabet.com/VirtualKeno/VirtualKeno");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.navigate().refresh(); // Refresh Browser to load draw#
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement NextDraw = driver.findElement(By.xpath("//p[@class='text-center mb-2']"));
		wait.until(ExpectedConditions.visibilityOf(NextDraw));
		return driver;
		
	}
}
