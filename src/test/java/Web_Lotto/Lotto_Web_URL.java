package Web_Lotto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lotto_Web_URL {
	
private static WebDriver driver;
	
	public WebDriver Lotto_Web_URL() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://suribet.sr/LottoGame/LottoGame");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		return driver;
	}
}