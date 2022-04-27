package Web_Skinfiri;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_Skinfiri_URL {

private static WebDriver driver;
	
	public WebDriver Web_Skinfiri_URL() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/DailyGame/DailyGame");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		return driver;
	}
}