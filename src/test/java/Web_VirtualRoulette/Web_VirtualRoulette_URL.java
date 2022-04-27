package Web_VirtualRoulette;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualRoulette_URL {

private static WebDriver driver;
	
	public WebDriver Web_VirtualRoulette_URL() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/VirtualRoulette/VirtualRoulette");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		return driver;
	}
}