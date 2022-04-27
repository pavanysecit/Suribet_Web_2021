package Web_VirtualSicbo;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_VirtualSicbo_URL {

private static WebDriver driver;

	public WebDriver Web_VirtualSicbo_URL() throws InterruptedException, MalformedURLException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/VirtualSicBo/SicboHome");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		return driver;
	}
}