package Web_VirtualSkinfiri;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualSkinfiri_URL {

	private static WebDriver driver;
	
	public WebDriver Web_VirtualSkinfiri_URL() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/VirtualSkinfiri/VirtualSkinfiri");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		return driver;
	}
}