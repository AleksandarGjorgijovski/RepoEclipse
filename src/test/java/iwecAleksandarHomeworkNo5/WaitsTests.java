package iwecAleksandarHomeworkNo5;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WaitsTests {
	
	WebDriver driver;
	String urlInternet = "https://the-internet.herokuapp.com/dynamic_loading/1";
	String urlBonigarcia = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";
	String urlTestpages = "https://testpages.herokuapp.com/styled/dynamic-buttons-disabled.html";
	String urlUitesting = "http://uitestingplayground.com/ajax";
	
	@BeforeMethod
	public void testSetup() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void internetWaitLoading() {
	
		driver.get(urlInternet);
		
		driver.findElement(By.xpath("//button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Hello World!']")));
		
		WebElement HelloWorld = driver.findElement(By.xpath("//*[text()='Hello World!']"));
		
		Assert.assertTrue(HelloWorld.isDisplayed(), "Text is not visible");
	}
	@Test
	public void bonigaciaLoadingImages() {
		driver.get(urlBonigarcia);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Done!']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Done!']")).isDisplayed(), "Images are not loaded");
	}
	@Test
	public void bonigaciaLoadingImages1() {
		driver.get(urlBonigarcia);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@id='landscape']")));
						
		List<WebElement> images = driver.findElements(By.xpath("//img[@id]"));
				
		for (int i = 1; i <= images.size(); i++) {
			WebElement img = driver.findElement(By.xpath("(//img[@id])["+i+"]"));
			Assert.assertTrue(img.isDisplayed(), "Image is not displayed");	
		}
		System.out.println("Number of images: " + images.size());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Done!']")).isDisplayed(), "Images are not loaded");
	}
	@Test
	public void bonigaciaLoadingImages2() {
		driver.get(urlBonigarcia);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='compass']")));
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='calendar']")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='award']")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='landscape']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Done!']")).isDisplayed(), "Images are not loaded");
	}
	
	@Test
	public void allButtonsClicked() {
		driver.get(urlTestpages);
		WebDriverWait wait = new WebDriverWait(driver,30);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button00']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button01']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button02']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button03']"))).click();
		
	
		WebElement expMsg = driver.findElement(By.xpath("//p[text()='All Buttons Clicked']"));
		Assert.assertTrue(expMsg.isDisplayed(), "Click all buttons in order");
	}
	@Test
	public void ajaxData() {
		driver.get(urlUitesting);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		String actTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='bg-success']"))).getText();
		String expTxt = "Data loaded with AJAX get request.";
		
		Assert.assertEquals(actTxt, expTxt);
		
		
	}
}






