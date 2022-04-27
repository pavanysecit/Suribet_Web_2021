package Web_Lotto;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lotto_Web_InvalidLogin_Validations extends Lotto_Web_URL{
	WebDriver driver;
	
	@Given("^Web: Chrome browser, suribet website valid URL, Lotto module, invalid login details$")
	public void web_Chrome_browser_suribet_website_valid_URL_Lotto_module_invalid_login_details() throws Throwable {
		driver = Lotto_Web_URL();
	    Thread.sleep(5000);
	}

	@When("^Web: Try to login with invalid different combinations under all modes of login methods$")
	public void web_Try_to_login_with_invalid_different_combinations_under_all_modes_of_login_methods() throws Throwable {
		//clicking on login button and entering valid credentials
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email / Mobile / CardNo']")));

		WebElement login = driver.findElement(By.xpath("//input[@placeholder='Email / Mobile / CardNo']"));
		login.sendKeys("");
		Thread.sleep(1000);
		WebElement login1 = driver.findElement(By.xpath("//input[@placeholder ='Password / Card Pin']"));
		login1.sendKeys("");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//input[@ng-click='frm.loginFun()']"));
		submit.click();
		Thread.sleep(1000);

		WebElement message = driver.findElement(By.xpath("//*[@ng-bind='frm.ValidationMsg']"));
		Assert.assertEquals("Please Enter valid Email Id", message.getText());
		System.out.println("Valid user message is displayed to user for Empty login details: "+  message.getText());
		Thread.sleep(3000);

		//Verify the invalid email format
		login.clear();
		login.sendKeys("pmansoorktr@gmail.com.");
		login1.clear();
		login1.sendKeys("mansoor@123");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);
		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", driver.findElement(By.xpath("//*[@ng-bind='frm.ValidationMsg']")).getText());	
		System.out.println("Valid user message is displayed for user for invalid UN & valid PWd: "+message.getText() );
		Thread.sleep(3000);

		//Verify the valid email and with invalid password format
		login.clear();
		login.sendKeys("pmansoorktr@gmail.com");
		login1.clear();
		login1.sendKeys("mansoor@1234");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);
		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", message.getText());	
		System.out.println("Valid user message1 is displayed for user for valid UN & invalid PWd: "+message.getText() );
		Thread.sleep(3000);


		//Invalid acc number login with different combinations
		login.clear();
		Thread.sleep(1000);
		login.sendKeys("*9308481390");
		Thread.sleep(1000);
		login1.clear();
		login1.sendKeys("mansoor@123");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);

		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", message.getText());	
		System.out.println("Valid user message is displayed for user for invalid ACC-number & valid PWd: "+message.getText()); 
		Thread.sleep(3000);


		//Invalid acc number login with different combinations
		login.clear();
		Thread.sleep(1000);
		login.sendKeys("9308481390");
		Thread.sleep(1000);
		login1.clear();
		login1.sendKeys("*mansoor@123");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);

		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", message.getText());	
		System.out.println("Valid user message is displayed for user for valid ACC-number & invalid PWd: "+message.getText());
		Thread.sleep(3000);

		//Invalid card number login with different combinations
		login.clear();
		Thread.sleep(1000);
		login.sendKeys("2111649489988826");
		Thread.sleep(1000);
		login1.clear();
		login1.sendKeys("11258");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);

		Assert.assertEquals("Incorrect Pin", message.getText());	
		System.out.println("Valid user message is displayed for user for valid card-number & invalid PWd: "+message.getText());
		Thread.sleep(3000);

		//Valid card number and invalid pin login with different combinations
		login.clear();
		Thread.sleep(1000);
		login.sendKeys("211164948998882611");
		Thread.sleep(1000);
		login1.clear();
		login1.sendKeys("1125");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);

		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", message.getText());	
		System.out.println("Valid user message is displayed for user for invalid card-number & valid PWd: "+message.getText());
		Thread.sleep(3000);


		//Valid card number and invalid pin login with different combinations in card account num logins
		login.clear();
		Thread.sleep(1000);
		login.sendKeys("4024953509");
		Thread.sleep(1000);
		login1.clear();
		login1.sendKeys("1125000");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);

		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", message.getText());	
		System.out.println("Valid user message is displayed for user for invalid card-number & valid PWd: "+message.getText());
		Thread.sleep(3000);

		//Valid card number and invalid pin login with different combinations in card account num logins
		login.clear();
		Thread.sleep(1000);
		login.sendKeys("4024953509");
		Thread.sleep(1000);
		login1.clear();
		login1.sendKeys("11");
		Thread.sleep(1000);
		submit.click();
		Thread.sleep(1200);

		Assert.assertEquals("Please Enter a valid Email/Mobile and Password", message.getText());	
		System.out.println("Valid user message is displayed for user for invalid card-number & valid PWd: "+message.getText());
		Thread.sleep(3000);


		//Pay by card details 
		//Card and pin restrictions are in-corporated hence have to check the length calculations

		//clicking on upcoming draw and place bet
		WebElement upmatch = driver.findElement(By.xpath("//*[@class='ul_clear in_R_up_ul']/child::li[2]"));
		upmatch.click();
		Thread.sleep(3000);

		WebElement stake6 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[6]"));
		stake6.click();
		Thread.sleep(1000);
		WebElement stake5 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[5]"));
		stake5.click();
		Thread.sleep(1000);
		WebElement stake4 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[4]"));
		stake4.click();
		Thread.sleep(1000);
		WebElement stake3 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[3]"));
		stake3.click();
		Thread.sleep(1000);
		WebElement stake2 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[2]"));
		stake2.click();
		Thread.sleep(1000);
		WebElement stake1 = driver.findElement(By.xpath("//ul[@class='ul_clear lottoBAll_w_ul']/child::li[1]"));
		stake1.click();
//		Thread.sleep(1000); 
//		stake.sendKeys("5");
		Thread.sleep(5000); 

		//Selecting the pay by card for placing the bet
		WebElement cardpay = driver.findElement(By.xpath("(//*[text()='Pay By Card'])[1]"));
		Assert.assertEquals("PAY BY CARD", cardpay.getText());
		System.out.println("Pay by card text is validated :"+cardpay.getText());
		Thread.sleep(1000);
		cardpay.click();
		Thread.sleep(2000);

		// fill the card details
		WebElement cardnum = driver.findElement(By.name("CustmerCardNumber"));
		cardnum.sendKeys("21116");
		Thread.sleep(1000);
		WebElement cardpin = driver.findElement(By.name("CustmerCardCardPin"));
		cardpin.sendKeys("1125");
		Thread.sleep(1000);
		WebElement Submit = driver.findElement(By.xpath("//button[@class='btn btn-orange text-white px-5 shadow-none ng-scope']"));
		Submit.click();
		Thread.sleep(2000);
		WebElement cardmessage = driver.findElement(By.xpath("//*[@class='err_msg1 ng-binding']")); 

		Assert.assertEquals("Minimum length of a card number is 16", cardmessage.getText());	
		System.out.println("Valid user message is displayed for user in card pay mode of transactions for invalid card-number & valid PWd: "+cardmessage.getText());

		// invalid combinations
		cardnum.clear();
		Thread.sleep(1000);
		cardnum.sendKeys("2111649489988826");
		Thread.sleep(1000);
		cardpin.clear();
		Thread.sleep(1000);
		cardpin.sendKeys("1");
		Thread.sleep(1000);
		Submit.click();
		Thread.sleep(2000);

		Assert.assertEquals("Minimum length of a card pin is 4", cardmessage.getText());	
		System.out.println("Valid user message is displayed for user in card pay mode of transactions for valid card-number & invalid PWd: "+cardmessage.getText());

		// invalid combinations
		cardnum.clear();
		Thread.sleep(1000);
		cardnum.sendKeys("2111649489988826");
		Thread.sleep(1000);
		cardpin.clear();
		Thread.sleep(1000);
		cardpin.sendKeys("1120");
		Thread.sleep(1000);
		Submit.click();
		Thread.sleep(2000);

		Assert.assertEquals("Incorrect Pin", cardmessage.getText());	
		System.out.println("Valid user message is displayed for user in card pay mode of transactions for valid card-number & invalid PWd: "+cardmessage.getText());

		// invalid combinations
		cardnum.clear();
		Thread.sleep(1000);
		cardnum.sendKeys("2111649489988855");
		Thread.sleep(1000);
		cardpin.clear();
		Thread.sleep(1000);
		cardpin.sendKeys("1120");
		Thread.sleep(1000);
		Submit.click();
		Thread.sleep(4000);

		Assert.assertEquals("Please Enter a Valid Card Number and Pin-6 !", cardmessage.getText());	
		System.out.println("Valid user message is displayed for user in card pay mode of transactions for invalid card-number & invalid PWd: "+cardmessage.getText());
	
	}

	@Then("^Web: Validate the account shouldn't login and valid user message is displayed to user under different login modes$")
	public void web_Validate_the_account_shouldn_t_login_and_valid_user_message_is_displayed_to_user_under_different_login_modes() throws Throwable {
	   driver.close();
	}
}