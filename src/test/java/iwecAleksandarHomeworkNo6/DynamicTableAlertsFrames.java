package iwecAleksandarHomeworkNo6;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicTableAlertsFrames {
	WebDriver driver;
	String urlAlert = "https://the-internet.herokuapp.com/javascript_alerts";
	String urlDynamictable = "http://uitestingplayground.com/dynamictable";
	String urlFrames = "https://testpages.herokuapp.com/styled/frames/frames-test.html";

	@BeforeMethod
	public void testSetup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@AfterMethod
	public void testTeardown() {
		testTeardown();
	}
	@Test
	public void dynamicTableUi() {
		driver.get(urlDynamictable);
		String tableCPU = driver
				.findElement(By.xpath("//span[text()='Chrome']/following-sibling::span[contains(text(),'%')]"))
				.getText();
		String tableText = "Chrome CPU: " + tableCPU;
		String result = driver.findElement(By.xpath("//p[@class='bg-warning']")).getText();
		Assert.assertEquals(tableText, result);
	}
	@Test
	public void dynamicTableUi1() {
		driver.get(urlDynamictable);
		String tableCPU = driver
				.findElement(By.xpath("//span[text()='Chrome']/following-sibling::span[contains(text(),'%')]"))
				.getText();
		String result = driver.findElement(By.xpath("//p[@class='bg-warning']")).getText().replace("Chrome CPU: ", "");
		Assert.assertEquals(tableCPU, result);
	}
	@Test
	public void JSalerts() {
		driver.get(urlAlert);
		
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		String expAlertMsg = "I am a JS Alert";
		String actAlertMsg = driver.switchTo().alert().getText();
		Assert.assertEquals(actAlertMsg, expAlertMsg);
		
		driver.switchTo().alert().accept();
		boolean expResult = driver.findElement(By.xpath("//*[text()='You successfully clicked an alert']")).isDisplayed();
		Assert.assertTrue(expResult, "JS Alert is not accepted");
	}
	@Test
	public void JSConfirm() {
		driver.get(urlAlert);
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		driver.switchTo().alert().dismiss();
		
		boolean expResult = driver.findElement(By.xpath("//*[text()='You clicked: Cancel']")).isDisplayed();
		Assert.assertTrue(expResult, "Result displayed: You clicked OK");
	}
	@Test
	public void JSprompt(){
		driver.get(urlAlert);
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		Alert promptAlert  = driver.switchTo().alert();
		promptAlert.sendKeys("Aleksandar");
		promptAlert.accept();
		boolean expResult = driver.findElement(By.xpath("//h4/following-sibling::*[contains(text(),'You entered:')]")).isDisplayed();
		Assert.assertTrue(expResult, "Result displayed: You entered: null");
	}
	@Test
	public void nestedFrames() {
		driver.get(urlFrames);
		//Switch to Frame using id of the frame
        driver.switchTo().frame("left");
        //Identifying the heading in webelement
        WebElement frameLeft= driver.findElement(By.id("left13"));
        //Finding the text of the heading
        String frameLeftText=frameLeft.getText();
        //Print the heading text
        System.out.println(frameLeftText);
        
        driver.switchTo().defaultContent();

        //Switch to Frame using id of the frame
        driver.switchTo().frame("middle");
        //Identifying the heading in webelement
        WebElement frameMiddle= driver.findElement(By.id("middle18"));
        //Finding the text of the heading
        String frameMiddleText=frameMiddle.getText();
        //Print the heading text
        System.out.println(frameMiddleText);
        
        driver.switchTo().defaultContent();

        //Switch to Frame using id of the frame
        driver.switchTo().frame("right");
        //Identifying the heading in webelement
        WebElement frameRight= driver.findElement(By.id("right49"));
        //Finding the text of the heading
        String frameRightText=frameRight.getText();
        //Print the heading text
        System.out.println(frameRightText);
	}
}





















