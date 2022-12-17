package testSuite;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.PdpObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.SearchPageObjects;
import pageObjectsRepository.ShoppingCartPageObjects;
import pageObjectsRepository.WishlistPageObjects;
import testData.TestData;

public class ShoppingTestScripts extends Base {
	TestData td;
	HomePageObjects hp;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects sh;
	WishlistPageObjects wh;
	SearchPageObjects sp;
	CommonPOM comm;
	
	@BeforeMethod
	public void start() {
		testSetup();
		td = new TestData();
		hp = new HomePageObjects();
		plp = new PlpObjects();
		pdp = new PdpObjects();
		sh = new ShoppingCartPageObjects();
		wh = new WishlistPageObjects();
		sp = new SearchPageObjects();
		comm = new CommonPOM();
				
	}
	@AfterMethod
	public void end() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void TC_CART_022_CheckIfThePriceChangesAccordingTheDiscount() {
		comm.mouseOverAndClickAction(hp.hpApparelBanner, hp.hpClothingBanner);
		pdp.levisLink.click();
		hp.waitElement(pdp.pdpSkuCode);
		
		String originalPrice435Txt= pdp.levisTabelPrice435.getText().replace("$","").replace(".", ""); 
		Integer originalPriceprice435Num = Integer.valueOf(originalPrice435Txt);
		System.out.println("Original single Price: "+ originalPriceprice435Num);
		
		String tabelPrice40Txt= pdp.levisTabelPrice40.getText().replace("$","").replace(".", ""); 
		Integer tabelPriceprice40Num = Integer.valueOf(tabelPrice40Txt);
		System.out.println("Table 3+ Price: "+ tabelPriceprice40Num);
		
		String tabelPrice38Txt= pdp.levisTabelPrice38.getText().replace("$","").replace(".", ""); 
		Integer tabelPriceprice38Num = Integer.valueOf(tabelPrice38Txt);
		System.out.println("Table 6+ Price: "+ tabelPriceprice38Num);
		
		String tabelPrice35Txt= pdp.levisTabelPrice35.getText().replace("$","").replace(".", ""); 
		Integer tabelPriceprice35Num = Integer.valueOf(tabelPrice35Txt);
		System.out.println("Table 10+ Price: "+ tabelPriceprice35Num);
		
		pdp.addCartBtn.click();
		hp.hpShoppingCartLink.click();
		sh.shLevisQuantityField.clear();
		
		Random randomGenerator = new Random();  
		int randomQuantity = randomGenerator.nextInt(15);  
		sh.shLevisQuantityField.sendKeys("" + randomQuantity);
		System.out.println("Random generated quantity: " + randomQuantity);
		
		sh.shUpdateCartBtn.click();

		hp.waitElement(wh.wSinglePrice);
		
		String priceShopingCart= wh.wSinglePrice.getText().replace("$","").replace(".", ""); 
		Integer priceShopingCartNum = Integer.valueOf(priceShopingCart);
		System.out.println("Shopping cart single Price: "+ priceShopingCartNum);
		
		if(randomQuantity == 1 && randomQuantity == 2)
		  { Assert.assertEquals(priceShopingCartNum, originalPriceprice435Num);	  
		  
		  }else if (randomQuantity >= 3 && randomQuantity <= 5){
			  Assert.assertEquals(priceShopingCartNum, tabelPriceprice40Num);
			  
		  }else if (randomQuantity >= 6 && randomQuantity <=9){
			  Assert.assertEquals(priceShopingCartNum, tabelPriceprice38Num);

		  }else if (randomQuantity >= 10){
			  Assert.assertEquals(priceShopingCartNum, tabelPriceprice35Num);
		  }
		
	}
	@Test
	public void TC_CART_022_CheckIfThePriceChangesAccordingTheDiscountSimplified() {
		hp.waitElement(hp.hpApparelBanner);
		comm.mouseOverAndClickAction(hp.hpApparelBanner, hp.hpClothingBanner);
		pdp.levisLink.click();
		hp.waitElement(pdp.pdpSkuCode);
		
		Integer originalPriceprice435Num = sh.convertTablePriceToInteger(pdp.levisTabelPrice435);
		Integer tabelPriceprice40Num = sh.convertTablePriceToInteger(pdp.levisTabelPrice40);
		Integer tabelPriceprice38Num = sh.convertTablePriceToInteger(pdp.levisTabelPrice38);
		Integer tabelPriceprice35Num = sh.convertTablePriceToInteger(pdp.levisTabelPrice35);
	
		pdp.addCartBtn.click();
		hp.hpShoppingCartLink.click();
		sh.shLevisQuantityField.clear();
		
		int randomQuantity = sh.generateRandomAndUpdateShoppingCart(15, sh.shLevisQuantityField);
		
		hp.waitElement(wh.wSinglePrice);
		
		Integer singlePriceShoppingCart = sh.convertPriceShoppingCartToInteger(wh.wSinglePrice);
		
		if(randomQuantity == 1 && randomQuantity == 2)
		{ Assert.assertEquals(singlePriceShoppingCart, originalPriceprice435Num);	  
		
		}else if (randomQuantity >= 3 && randomQuantity <= 5){
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice40Num);
			
		}else if (randomQuantity >= 6 && randomQuantity <=9){
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice38Num);
			
		}else if (randomQuantity >= 10){
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice35Num);
		}
	}
}
	

