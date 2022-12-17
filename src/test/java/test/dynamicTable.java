package test;



import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
    import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class dynamicTable {
		WebDriver driver;
		
		@BeforeMethod
		public void testSetup() {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			this.driver = driver;
		
			
			driver.get("http://uitestingplayground.com/dynamictable");
			driver.manage().window().maximize();
		}
		
		@AfterMethod
		public void testTeardown() throws InterruptedException {
			Thread.sleep(3000);
			//driver.quit();
		}
		
		@Test
		public void dynamicTableUi() {
			
			String tableCPU = driver.findElement(By.xpath("//span[text()='Chrome']/following-sibling::span[contains(text(),'%')]")).getText();
			
			String tableText = "Chrome CPU: " + tableCPU;
			String result = driver.findElement(By.xpath("//p[@class='bg-warning']")).getText();
			
			Assert.assertEquals(tableText, result);
		
		}
		@Test
		public void dynamicTableUi1() {
			
			String tableCPU = driver.findElement(By.xpath("//span[text()='Chrome']/following-sibling::span[contains(text(),'%')]")).getText();
			
			String result = driver.findElement(By.xpath("//p[@class='bg-warning']")).getText().replace("Chrome CPU: ", "");
			
			Assert.assertEquals(tableCPU, result);
			
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	