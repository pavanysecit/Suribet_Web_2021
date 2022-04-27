package Web_SportBetting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_SportBetting_URL {

private static WebDriver driver;
	
	public WebDriver Web_SportBetting_URL() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/SportBetting/SportBetting");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		return driver;
	}
}