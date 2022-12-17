package test;



import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class testAce {
		WebDriver driver;
		
		@BeforeMethod
		public void testSetup() {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			this.driver = driver;
		
			
			driver.get("https://demo.nopcommerce.com/");
			driver.manage().window().maximize();
		}
		
		@AfterMethod
		public void testTeardown() throws InterruptedException {
			Thread.sleep(3000);
			//driver.quit();
		}
		
		
		
		@Test
		public void Testing() {
		
		WebElement ele = driver.findElement(By.xpath("(//a[@href='/apparel'])[1]"));
		Actions actionHover = new Actions(driver);

		actionHover.moveToElement(ele).perform();
		
		driver.findElement(By.xpath("//a[@href='/clothing']")).click();
		driver.findElement(By.xpath("(//h2[@class='product-title'])[3]")).click();
		
		WebElement size = driver.findElement(By.xpath("//select[@id='product_attribute_11']"));
		Select select = new Select(size);
		select.selectByValue("31");	
		
		WebElement quantity =driver.findElement(By.xpath("//input[@class='qty-input']"));
		quantity.clear();
		quantity.sendKeys("4");
		driver.findElement(By.xpath("//button[@id='add-to-cart-button-27']")).click();
		String expSKU = driver.findElement(By.xpath("//span[@id='sku-27']")).getText();
		driver.findElement(By.xpath("//a[@href='/cart']")).click();
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='sku-27']")));
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='sku-number']")));

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='sku-number']")).getText(), expSKU);
		
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	