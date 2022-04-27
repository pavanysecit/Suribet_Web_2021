package Web_Lotto;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class language_integration{
	WebDriver driver;

	
	@Given("^Web: A$")
	public void web_A() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://bet.simbabet.com/VirtualKeno/VirtualKeno");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(8000);
		
	}

	@When("^Web: B$")
	public void web_B() throws Throwable {
	    
		Actions builder = new Actions(driver);
		WebElement flag = driver.findElement(By.xpath("//*[@class='flag-England']"));
        Action mouseOverHome = builder
                .moveToElement(flag).build(); 
//  
//    	Thread.sleep(3000);
//        WebElement flag2 = driver.findElement(By.xpath("//*[@class='flag-England']/../ul/li[2]")); 
//        Action mouseOverHome1 = builder
//                .moveToElement(flag2).click().build(); 
		WebElement lang = driver.findElement(By.xpath("//*[@class='LanguageSLanguagectBoxW LanguageSLanguagectBoxW_JS ']"));
		List<WebElement> language = lang.findElements(By.tagName("li"));
		System.out.println("list size: "+ language.size());
		
		
		for( WebElement la :language) {
			System.out.println(la.getText());
			if(la.getText().equalsIgnoreCase("Portuguese")) {
				la.click();
				break;
			}
		}
		Thread.sleep(10000);
        
		WebElement text = driver.findElement(By.xpath(" //*[@ng-controller='globalrController as globalCtlr'] "));
		String gff = text.getText();
		System.out.println(gff);
	}

	@Then("^Web:C$")
	public void web_C() throws Throwable {
	 
	}

	@Then("^Web: D$")
	public void web_D() throws Throwable {
	    
	}
	
}

