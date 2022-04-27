package Web_VirtualKeno;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Web_VirtualKeno_OnlineLogin_CardLogin_Validations extends Web_VirtualKeno_URL_CardLogin{
WebDriver driver;

	public Web_VirtualKeno_OnlineLogin_CardLogin_Validations() throws Exception {
		driver = Web_VirtualKeno_URL_CardLogin();
	}

	@Given("^Email Login: Launch the chrome browser, Enter valid Url, Enter Valid & InValid login credentials and verify the validation$")
	public void email_Login_Launch_the_chrome_browser_Enter_valid_Url_Enter_Valid_InValid_login_credentials_and_verify_the_validation() throws Throwable 
	{
		driver.findElement(By.xpath("//a[contains(text(), 'Log Off')]")).click();
		Thread.sleep(5000);
		System.out.println("Logoff successfully");
		
		
		//Verifying In-valid login credentials for online account	
		driver.findElement(By.id("txtUserName")).click();
		driver.findElement(By.id("txtUserName")).sendKeys("maheshjutti@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).sendKeys("mans@123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(2000);	
	
		driver.findElement(By.xpath("//*[text()='Please Enter a valid Email/Mobile and Password']")).isDisplayed();
		String actualMsg=driver.findElement(By.xpath("//*[text()='Please Enter a valid Email/Mobile and Password']")).getText();
		String expectedMsg = "Please Enter a valid Email/Mobile and Password";
		Assert.assertEquals(actualMsg, expectedMsg);
		System.out.println("Email Login: Failed, In-Valid Email Login Validation message is:" + actualMsg);
		Thread.sleep(2000);	
	
		//Enter only password and click on login button
		driver.findElement(By.id("txtUserName")).clear();
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).sendKeys("mans@123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//*[text()='Please Enter valid Email Id']")).isDisplayed();
		String actualMsg1=driver.findElement(By.xpath("//*[text()='Please Enter valid Email Id']")).getText();
		String expectedMsg1 = "Please Enter valid Email Id";
		Assert.assertEquals(actualMsg1, expectedMsg1);
		System.out.println("Blank Password Field Verified");
		Thread.sleep(2000);	
	
		//Enter only Email and click on login button
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("txtUserName")).sendKeys("maheshjutti@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//*[text()='Please Enter The Password']")).isDisplayed();
		String actualMsg2=driver.findElement(By.xpath("//*[text()='Please Enter The Password']")).getText();
		String expectedMsg2 = "Please Enter The Password";
		Assert.assertEquals(actualMsg2, expectedMsg2);
		System.out.println("Blank Email Field Verified");
		Thread.sleep(2000);	
			
	}

	@When("^Card Login: Launch the chrome browser, Enter valid Url, Enter Valid & InValid card login credentials and verify the validation$")
	public void card_Login_Launch_the_chrome_browser_Enter_valid_Url_Enter_Valid_InValid_card_login_credentials_and_verify_the_validation() throws Throwable 
	{
		//Verifying valid card Login credentials
		driver.findElement(By.id("txtUserName")).clear();
		driver.findElement(By.id("txtUserName")).sendKeys("2111649489988826");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).clear();
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).sendKeys("1125");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(8000);
		
		WebElement AccountId=driver.findElement(By.xpath("//span[@class='ac_id']/a[3]"));
		String cardNum=AccountId.getText();
		String ExpectedResult= "4024953509";
		Assert.assertEquals(ExpectedResult, cardNum);
		System.out.println("Card Login Success and Card login Account Id is:"+ cardNum );
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[contains(text(), 'Log Off')]")).click();
		Thread.sleep(5000);
				
				
		//Verifying In-valid Card login credentials for card account	
		driver.findElement(By.id("txtUserName")).sendKeys("2111649489988830");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@ng-if='IsCardEnabled==1' and @placeholder='Password / Card Pin']")).sendKeys("1111");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='loginActive']")).click();
		Thread.sleep(3000);	
	
		String actualMsg=driver.findElement(By.xpath("//*[text()='Please Enter a Valid Card Number and Pin-6 !']")).getText();
		String expectedMsg = "Please Enter a Valid Card Number and Pin-6 !";
		Assert.assertEquals(actualMsg, expectedMsg);
		System.out.println("Card Login: Failed, Validation message is:" + actualMsg);
		Thread.sleep(2000);
				
	}

	@Then("^User should be login successfully with valid Email and password credential$")
	public void user_should_be_login_successfully_with_valid_Email_and_password_credential() throws Throwable
	{
	    
	}

	@Then("^User should NOT be login successfully with Invalid Email and password credentials$")
	public void user_should_NOT_be_login_successfully_with_Invalid_Email_and_password_credentials() throws Throwable 
	{
	}

	@Then("^ser should be login successfully with valid CardNumber and Pin Number$")
	public void ser_should_be_login_successfully_with_valid_CardNumber_and_Pin_Number() throws Throwable 
	{
	   
	}

	@Then("^User should NOT be login by entering invalid card details and display Invalid validation message$")
	public void user_should_NOT_be_login_by_entering_invalid_card_details_and_display_Invalid_validation_message() throws Throwable 
	{
	    
	}
	
	@Then("^Close the virtual Keno Application successfully$")
	public void close_the_virtual_Keno_Application_successfully() throws Throwable
	{
	  driver.quit();
	}
}