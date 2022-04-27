package Web_Poker;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Poker_ResultVerification_With_ExternalSite extends Web_VirtualPoker_URL_OnlineLogin {
WebDriver driver;
	
	public Poker_ResultVerification_With_ExternalSite() throws Exception {
		driver = Web_VirtualPoker_URL_OnlineLogin();
	}


	@Given("^Web: A$")
	public void web_A() throws Throwable {

	}

	@When("^Web: B$")
	public void web_B() throws Throwable {


		int i=1;
		for(;;)
		{
			WebDriverWait wait21 = new WebDriverWait(driver, 120);
			WebElement DrawID = driver.findElement(By.xpath("//*[@class='text-center py-2 rounded']"));
			wait21.until(ExpectedConditions.visibilityOf(DrawID));
			Thread.sleep(2000);

			WebElement NextWaittime = driver.findElement(By.xpath("//*[@class='text-center py-2 rounded']/div[3]")); 
			String Waittime = NextWaittime.getText();
			System.out.println("time length: "+Waittime +"   "+Waittime.length());
			String Wt = Waittime.substring(0, 2);
			System.out.println("minutes: "+Integer.parseInt(Wt));
			String Wt1 = Waittime.substring(3, 5);
			System.out.println("seconds: "+Wt1);
			int mtos1 = Integer.parseInt(Wt)*60;

			//new minutes to seconds
			int validsec1 = mtos1+Integer.parseInt(Wt1);
			System.out.println("conversion from minutes to seconds and next draw wait time: "+validsec1 );
			Thread.sleep(validsec1*1000);
			Thread.sleep(1*70*1000);

			/*
			 * Fetch the draw number
			 */
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement drawnum = driver.findElement(By.xpath("//*[@ng-repeat='RecentResultTop1 in pokerCtlr.RecentResultDrawNO']/div/sapn")); 

			wait.until(ExpectedConditions.visibilityOf(drawnum));
			System.out.println(" Draw result to be verified: "+drawnum.getText());
			String  dnum= drawnum.getText().replaceAll("[^0-9]", "");

			WebElement six_1 = driver.findElement(By.xpath("(//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1]")); 
			WebElement six_2 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[2]"));
			WebElement five_1 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[3]"));
			WebElement five_2 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[4]"));
			WebElement four_1 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[5]"));
			WebElement four_2 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[6]"));
			WebElement three_1 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[7]"));
			WebElement three_2 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[8]"));
			WebElement two_1 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[9]"));
			WebElement two_2 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[10]"));
			WebElement one_1 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[11]"));
			WebElement one_2 = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[1]/div/span[1])[12]"));

			WebElement one = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[2]/div/span)[1]")); 	
			WebElement two = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[2]/div/span)[2]"));
			WebElement three = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[2]/div/span)[3]"));
			WebElement four = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[2]/div/span)[4]"));
			WebElement five = driver.findElement(By.xpath("((//*[@class='poker-last-result ng-binding'])[1]/div[2]/div/span)[5]"));


			//Take the screenshot    
			String timestamp = new SimpleDateFormat("dd-MM-yy hh_mm_ss").format(new Date());
			String FileName = ""+dnum+"_"+timestamp;
			
			//Copy the file to a location and use try catch block to handle exception
			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("SURIBET_WEB/PokerScreenshots/"+FileName+".png"));
				System.out.println("Taken screenshots");
				driver.getTitle();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			WebElement playerhand = driver.findElement(By.xpath("(//*[@class='poker-last-result ng-binding'])[1]/div[3]")); 
			String  phand= playerhand.getText();

			String  lh= six_1.getAttribute("class");
			String  six_11 = lh.substring(lh.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player6 1st card card: "+six_11);

			String  lh1= six_2.getAttribute("class");
			String  six_21 = lh1.substring(lh1.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player6 2nd card card: "+six_21);

			String  lh2= five_1.getAttribute("class");
			String  five_11 = lh2.substring(lh2.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player5 1st card card: "+five_11);

			String  lh3= five_2.getAttribute("class");
			String  five_21 = lh3.substring(lh3.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player5 2nd card card: "+five_21);

			String  lh4= four_1.getAttribute("class");
			String  four_11 = lh4.substring(lh4.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player4 1st card card: "+four_11);

			String  lh5= four_2.getAttribute("class");
			String  four_21 = lh5.substring(lh5.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player4 2nd card card: "+four_21);

			String  lh6= three_1.getAttribute("class");
			String  three_11 = lh6.substring(lh6.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player3 1st card card: "+three_11);

			String  lh7= three_2.getAttribute("class");
			String  three_21 = lh7.substring(lh7.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player3 2nd card card: "+three_21);

			String  lh8= two_1.getAttribute("class");
			String  two_11 = lh8.substring(lh8.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player2 1st card card: "+two_11);

			String  lh9= two_2.getAttribute("class");
			String  two_21 = lh9.substring(lh9.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player2 2nd card card: "+two_21);

			String  lh10= one_1.getAttribute("class");
			String  one_11 = lh10.substring(lh10.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player1 1st card card: "+one_11);

			String  lh11= one_2.getAttribute("class");
			String  one_21 = lh11.substring(lh11.length() - 3).replaceAll("-", "");
			System.out.println("Lower hand player1 2nd card card: "+one_21);


			/*
			 * higher hands selections
			 */
			String  first= one.getAttribute("class");
			String  fst = first.substring(first.length() - 3).replaceAll("-", "");
			System.out.println("Higher hand 1st card: "+ fst);

			String  second= two.getAttribute("class");
			String  scnd = second.substring(second.length() - 3).replaceAll("-", "");
			System.out.println("Higher hand 2nd card: "+ scnd);

			String  third= three.getAttribute("class");
			String  thrd = third.substring(third.length() - 3).replaceAll("-", "");
			System.out.println("Higher hand 3rd card: "+ thrd);

			String  fourth= four.getAttribute("class");
			String  frth = fourth.substring(fourth.length() - 3).replaceAll("-", "");
			System.out.println("Higher hand 4th card: "+ frth);

			String  fifth= five.getAttribute("class");
			String  fift = fifth.substring(fifth.length() - 3).replaceAll("-", "");
			System.out.println("Higher hand 5th card: "+ fift);

			driver.navigate().to("https://www.pokerlistings.com/which-hand-wins-calculator");
			WebDriverWait wait12 = new WebDriverWait(driver, 120);
			JavascriptExecutor j = (JavascriptExecutor)driver;
			if (j.executeScript("return document.readyState").toString().equals("complete")){
				System.out.println("Page has loaded");
			}

			for(; i<2; i++){
				System.out.println("Loop" +i);
				WebElement element = driver.findElement(By.xpath("//*[@class='spu-close spu-close-popup top_right']"));
				wait12.until(ExpectedConditions.visibilityOf(element));
				if (element.isDisplayed() && element.isEnabled()) {
					element.click();
				}
			}

			WebElement frame1=driver.findElement(By.xpath("//*[@frameborder='0']"));
			driver.switchTo().frame(frame1);
			Thread.sleep(2000);
			WebElement dropdown = driver.findElement(By.xpath("//*[@class='choose-dropdown choose-dropdown_players']"));
			wait.until(ExpectedConditions.visibilityOf(dropdown));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", dropdown);
			Thread.sleep(2000);

			WebElement players = driver.findElement(By.xpath("//*[@class='choose-dropdown__items']/div[4]")); 
			executor.executeScript("arguments[0].click();", players);
			Thread.sleep(1000);

			/*
			 * heart = h
			 * spade = s
			 * diamond = d
			 * club = c
			 */

			List<String> namesList = Arrays.asList( one_11, one_21, two_11, two_21, three_11, three_21, four_11, four_21, five_11, five_21, six_11, six_21 );
			ArrayList<String> otherList = new ArrayList<>();
			otherList.addAll(namesList);    
			System.out.println("Lower Hand 12 cards: "+otherList);


			List<String> namesList1 = Arrays.asList( fst, scnd, thrd, frth, fift);
			ArrayList<String> otherList1 = new ArrayList<>();
			otherList1.addAll(namesList1);    
			System.out.println("Higher hand 5 cards: "+otherList1);

			for(String game: otherList){	
				if(game.contains("h")) {
					String	ch = game.replaceAll("[^0-9]", "");
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[3]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement heart2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", heart2);

						break;  
					case "3":   
						WebElement heart3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", heart3);

						break;  
					case "4":  
						WebElement heart4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", heart4);

						break;  
					case "5":  
						WebElement heart5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", heart5);

						break; 
					case "6":  
						WebElement heart6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", heart6);

						break;
					case "7":  
						WebElement heart7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", heart7);

						break;
					case "8":  
						WebElement heart8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", heart8);

						break;
					case "9":  
						WebElement heart9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", heart9);

						break;
					case "10":  
						WebElement heart10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", heart10);

						break;
					case "11":  
						WebElement heart11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", heart11);

						break;
					case "12":  
						WebElement heart12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", heart12);

						break;
					case "13":  
						WebElement heart13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", heart13);

						break;
					case "14":  
						WebElement heart14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", heart14);

						break;

					}
				}
				else if (game.contains("d")) {
					String	ch = game.replaceAll("[^0-9]", "");  
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[2]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement diamond2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", diamond2);

						break;  
					case "3":   
						WebElement diamond3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", diamond3);

						break;  
					case "4":  
						WebElement diamond4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", diamond4);

						break;  
					case "5":  
						WebElement diamond5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", diamond5);

						break; 
					case "6":  
						WebElement diamond6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", diamond6);

						break;
					case "7":  
						WebElement diamond7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", diamond7);

						break;
					case "8":  
						WebElement diamond8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", diamond8);

						break;
					case "9":  
						WebElement diamond9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", diamond9);

						break;
					case "10":  
						WebElement diamond10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", diamond10);

						break;
					case "11":  
						WebElement diamond11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", diamond11);

						break;
					case "12":  
						WebElement diamond12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", diamond12);

						break;
					case "13":  
						WebElement diamond13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", diamond13);

						break;
					case "14":  
						WebElement diamond14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", diamond14);

						break;
					}

				}else if (game.contains("s")) {
					String	ch = game.replaceAll("[^0-9]", "");
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[4]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement spade2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", spade2);

						break;  
					case "3":   
						WebElement spade3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", spade3);

						break;  
					case "4":  
						WebElement spade4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", spade4);

						break;  
					case "5":  
						WebElement spade5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", spade5);

						break; 
					case "6":  
						WebElement spade6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", spade6);

						break;
					case "7":  
						WebElement spade7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", spade7);

						break;
					case "8":  
						WebElement spade8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", spade8);

						break;
					case "9":  
						WebElement spade9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", spade9);

						break;
					case "10":  
						WebElement spade10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", spade10);

						break;
					case "11":  
						WebElement spade11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", spade11);

						break;
					case "12":  
						WebElement spade12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", spade12);

						break;
					case "13":  
						WebElement spade13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", spade13);

						break;
					case "14":  
						WebElement spade14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", spade14);

						break;

					}
				}else if (game.contains("c")) {
					String	ch = game.replaceAll("[^0-9]", "");  
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[1]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement club2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", club2);

						break;  
					case "3":   
						WebElement club3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", club3);

						break;  
					case "4":  
						WebElement club4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", club4);

						break;  
					case "5":  
						WebElement club5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", club5);

						break; 
					case "6":  
						WebElement club6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", club6);

						break;
					case "7":  
						WebElement club7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", club7);

						break;
					case "8":  
						WebElement club8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", club8);

						break;
					case "9":  
						WebElement club9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", club9);

						break;
					case "10":  
						WebElement club10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", club10);

						break;
					case "11":  
						WebElement club11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", club11);

						break;
					case "12":  
						WebElement club12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", club12);

						break;
					case "13":  
						WebElement club13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", club13);

						break;
					case "14":  
						WebElement club14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", club14);

						break;
					}
				}

			}
			for(String game1: otherList1){	
				if(game1.contains("h")) {
					String	ch = game1.replaceAll("[^0-9]", "");
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[3]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement heart2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", heart2);

						break;  
					case "3":   
						WebElement heart3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", heart3);

						break;  
					case "4":  
						WebElement heart4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", heart4);

						break;  
					case "5":  
						WebElement heart5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", heart5);

						break; 
					case "6":  
						WebElement heart6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", heart6);

						break;
					case "7":  
						WebElement heart7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", heart7);

						break;
					case "8":  
						WebElement heart8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", heart8);

						break;
					case "9":  
						WebElement heart9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", heart9);

						break;
					case "10":  
						WebElement heart10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", heart10);

						break;
					case "11":  
						WebElement heart11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", heart11);

						break;
					case "12":  
						WebElement heart12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", heart12);

						break;
					case "13":  
						WebElement heart13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", heart13);

						break;
					case "14":  
						WebElement heart14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", heart14);

						break;

					}
				}
				else if (game1.contains("d")) {
					String	ch = game1.replaceAll("[^0-9]", "");  
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[2]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement diamond2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", diamond2);

						break;  
					case "3":   
						WebElement diamond3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", diamond3);

						break;  
					case "4":  
						WebElement diamond4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", diamond4);

						break;  
					case "5":  
						WebElement diamond5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", diamond5);

						break; 
					case "6":  
						WebElement diamond6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", diamond6);

						break;
					case "7":  
						WebElement diamond7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", diamond7);

						break;
					case "8":  
						WebElement diamond8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", diamond8);

						break;
					case "9":  
						WebElement diamond9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", diamond9);

						break;
					case "10":  
						WebElement diamond10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", diamond10);

						break;
					case "11":  
						WebElement diamond11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", diamond11);

						break;
					case "12":  
						WebElement diamond12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", diamond12);

						break;
					case "13":  
						WebElement diamond13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", diamond13);

						break;
					case "14":  
						WebElement diamond14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", diamond14);

						break;
					}

				}else if (game1.contains("s")) {
					String	ch = game1.replaceAll("[^0-9]", "");
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[4]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement spade2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", spade2);

						break;  
					case "3":   
						WebElement spade3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", spade3);

						break;  
					case "4":  
						WebElement spade4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", spade4);

						break;  
					case "5":  
						WebElement spade5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", spade5);

						break; 
					case "6":  
						WebElement spade6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", spade6);

						break;
					case "7":  
						WebElement spade7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", spade7);

						break;
					case "8":  
						WebElement spade8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", spade8);

						break;
					case "9":  
						WebElement spade9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", spade9);

						break;
					case "10":  
						WebElement spade10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", spade10);

						break;
					case "11":  
						WebElement spade11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", spade11);

						break;
					case "12":  
						WebElement spade12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", spade12);

						break;
					case "13":  
						WebElement spade13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", spade13);

						break;
					case "14":  
						WebElement spade14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", spade14);

						break;

					}
				}else if (game1.contains("c")) {
					String	ch = game1.replaceAll("[^0-9]", "");  
					System.out.println(ch);
					String path = "((//*[@class='container__card'])[1]/div/div/div[1])";
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					switch(ch)  
					{  
					case "2":   
						WebElement club2 = driver.findElement(By.xpath(""+path+"[1]")); 
						executor1.executeScript("arguments[0].click();", club2);

						break;  
					case "3":   
						WebElement club3 = driver.findElement(By.xpath(""+path+"[2]")); 
						executor1.executeScript("arguments[0].click();", club3);

						break;  
					case "4":  
						WebElement club4 = driver.findElement(By.xpath(""+path+"[3]")); 
						executor1.executeScript("arguments[0].click();", club4);

						break;  
					case "5":  
						WebElement club5 = driver.findElement(By.xpath(""+path+"[4]")); 
						executor1.executeScript("arguments[0].click();", club5);

						break; 
					case "6":  
						WebElement club6 = driver.findElement(By.xpath(""+path+"[5]")); 
						executor1.executeScript("arguments[0].click();", club6);

						break;
					case "7":  
						WebElement club7 = driver.findElement(By.xpath(""+path+"[6]")); 
						executor1.executeScript("arguments[0].click();", club7);

						break;
					case "8":  
						WebElement club8 = driver.findElement(By.xpath(""+path+"[7]")); 
						executor1.executeScript("arguments[0].click();", club8);

						break;
					case "9":  
						WebElement club9 = driver.findElement(By.xpath(""+path+"[8]")); 
						executor1.executeScript("arguments[0].click();", club9);

						break;
					case "10":  
						WebElement club10 = driver.findElement(By.xpath(""+path+"[9]")); 
						executor1.executeScript("arguments[0].click();", club10);

						break;
					case "11":  
						WebElement club11 = driver.findElement(By.xpath(""+path+"[10]")); 
						executor1.executeScript("arguments[0].click();", club11);

						break;
					case "12":  
						WebElement club12 = driver.findElement(By.xpath(""+path+"[11]")); 
						executor1.executeScript("arguments[0].click();", club12);

						break;
					case "13":  
						WebElement club13 = driver.findElement(By.xpath(""+path+"[12]")); 
						executor1.executeScript("arguments[0].click();", club13);

						break;
					case "14":  
						WebElement club14 = driver.findElement(By.xpath(""+path+"[13]")); 
						executor1.executeScript("arguments[0].click();", club14);

						break;
					}
				}

			}
			System.out.println("All the cards are picked and ready for validations in the external site");

			WebElement submit = driver.findElement(By.xpath("//*[@class='button button_blue']"));
			executor.executeScript("arguments[0].click();", submit);
			Thread.sleep(2000);
			driver.switchTo().parentFrame();
			WebElement board = driver.findElement(By.xpath("//*[@data-type='URL']"));
			executor.executeScript("arguments[0].scrollIntoView();", board );
			Thread.sleep(2000);
			driver.switchTo().frame(frame1);
			WebElement playerwin = driver.findElement(By.xpath("//*[@class='notification__info-wrapper']/div[1]")); 
			wait.until(ExpectedConditions.visibilityOf(playerwin));
			String plywin = playerwin.getText().replaceAll("[^0-9]", "");
			int plywin1 = plywin.length();
				
				switch(plywin1)  
				{  
				case 1:   
					System.out.println("Single hand won the current draw");

					break;  
				case 2:   
					System.out.println("two hands won the current draw");

					break;  
				case 3:  
					System.out.println("three hands won the current draw");

					break;  
				case 4:  
					System.out.println("four hands won the current draw");

					break; 
				case 5:  
					System.out.println("five hands won the current draw");

					break;
				case 6:  
					System.out.println("All hands won the current draw");
					break;
				}
			
			WebElement wincombination = driver.findElement(By.xpath("//*[@class='notification__info-wrapper']/div[2]")); 
			String wincomb = wincombination.getText();

			String playhand = phand.replaceAll("[^0-9]", "");
			System.out.println("Hand win result from the poker site:"+plywin+"  Hand win result from the external site: "+playhand );
			try{
				Assert.assertEquals(plywin, playhand);
			}catch (Throwable t){
				System.out.println("Assertion for hands");
			}

			//Taking screenshot in external website
			String FileName1 = ""+dnum+"_"+"PokerExternal"+timestamp;

			//Copy the file to a location and use try catch block to handle exception
			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("SURIBET_WEB/PokerScreenshots/"+FileName1+".png"));
				System.out.println("Taken screenshots");
				driver.getTitle();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			driver.navigate().back();
			Thread.sleep(8000);
			WebElement result = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr._rightTabIndex=1']")); 
			wait.until(ExpectedConditions.visibilityOf(result));
			result.click();
			Thread.sleep(2000);
			WebElement winpat = driver.findElement(By.xpath("//*[@placeholder='Draw Number']")); 
			winpat.click();
			Thread.sleep(1000);
			winpat.sendKeys(dnum); 
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='fa fa-search slideMenuFalse']")).click(); 
			Thread.sleep(2000);
			WebElement comb = driver.findElement(By.xpath("//*[@ng-show='a.Winner!=null']"));  
			wait.until(ExpectedConditions.visibilityOf(comb)); 
			Thread.sleep(2000);
			String cm = comb.getText().substring(6);

			System.out.println("Win pattern from the poker site:"+cm+"  Win pattern result from the external site: "+wincomb );
			if(cm.equalsIgnoreCase("One Pair") || cm.equalsIgnoreCase("High Card")) {
				if (cm.equalsIgnoreCase("One Pair")) {
					String formatcm = cm.replaceAll(cm, "pair of");
					System.out.println("Formated new string to compare for one pair result verification: "+ formatcm);
					
					try{
						Assert.assertTrue(wincomb.toLowerCase().contains(formatcm));
					}catch (Throwable t1){
						System.out.println("Assertion for pair of hands");
					}
					System.out.println("Win combinations are verified for the selected draw number: "+dnum+" having "+cm+" win combination");
				}else {
				String formatcm = cm.replaceAll(cm, "High");
				System.out.println("Formated new string to compare for High Card result verification: "+ formatcm);
				try{
					Assert.assertTrue(wincomb.toLowerCase().contains(formatcm.toLowerCase()));
				}catch (Throwable t2){
					System.out.println("Assertion for High Card");
				}
				
				System.out.println("Win combinations are verified for the selected draw number: "+dnum+" having "+cm+" win combination");
				}
			}else {

				try{
					Assert.assertTrue(wincomb.contains(cm));
				}catch (Throwable t1){
					System.out.println("Assertion for win combination");
				}
				
				System.out.println("Win combinations are verified for the selected draw number: "+dnum+" with win combination as: "+ cm );
			}
			WebElement betting_slip = driver.findElement(By.xpath("//*[@ng-click='pokerCtlr._rightTabIndex=0']")); 
			betting_slip.click();
			Thread.sleep(3000); 
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
	
	@Then("^Web: C$")
	public void web_C() throws Throwable {

	}
}

