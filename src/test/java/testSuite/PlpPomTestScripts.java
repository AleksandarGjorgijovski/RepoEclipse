package testSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.ComparelistPageObjects;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.SearchPageObjects;
import testData.TestData;

public class PlpPomTestScripts extends Base {
	// new Objects od site klasi sto ke se koristi vo ovaa klasa
	HomePageObjects hp;
	SearchPageObjects sp;
	TestData td;
	PlpObjects plp;
	ComparelistPageObjects compareListPage;
	CommonPOM comm;
	
	@BeforeMethod
	public void start() {
		//se zema metodot od Base za start na testiranje
		testSetup();
		hp = new HomePageObjects();
		sp = new SearchPageObjects();
		td = new TestData();
		plp = new PlpObjects();
		compareListPage = new ComparelistPageObjects();
		comm = new CommonPOM();
	}
	@AfterMethod
	public void end() throws InterruptedException  {
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void TC_PRODUCT_016_CheckIfBreadcrumbsMenuNavigateToCorespondingPage() {
		hp.hpComputersBanner.click();
		plp.plpSoftwareSubCategory.click();
		plp.plpWindows8.click();
		plp.verifyWindows8PageisDisplayed();
		plp.plpComputerBreadcrumbs.click();
		plp.verifyComputerPageisDisplayed();
		plp.plpHomeBreadcrumbs.click();
		plp.verifyHomePageisDisplayed();
	}
	@Test
	public void TC_PRODUCT_011_VerifyClearListOnComperasonPage(){
		comm.mouseOverAndClickAction(plp.hpElectronicsBanner, plp.plpCellPhones);
		comm.waitElement(plp.plpCompareHtcOne);
		plp.plpCompareHtcOne.click();
		comm.waitElement(plp.plpCompareNokia);
		plp.plpCompareNokia.click();
		plp.plpCompareProductList.click();
		compareListPage.waitClearCompareList();
		compareListPage.compListClearListBtn.click();
		compareListPage.verifyEmptyCompareListPage();
	}
	@Test
	public void TC_PRODUCT_002_VerifyFilterOptionIsWorking() {
		comm.waitElement(hp.hpComputersBanner);
		comm.mouseOverAndClickAction(hp.hpComputersBanner, plp.plpNotebooksSubCategory);
		comm.waitElement(plp.plpFilterByIntelI5ChBox);
		plp.plpFilterByIntelI5ChBox.click();
		comm.waitElement(plp.plpFilterByMemory8GB);
		plp.plpFilterByMemory8GB.click();
		comm.waitElement(plp.listViewbtn);
		plp.listViewbtn.click();
		comm.waitElement(plp.plpItemDescription);
		plp.verifyWithForLoopDescription(td.filterItemByCpu5, td.filterByMemory8);
	}
	@Test
	public void TC_PRODUCT_002_VerifyFilterOptionIsWorkingForEachLoop() {
		comm.mouseOverAndClickAction(hp.hpComputersBanner, plp.plpNotebooksSubCategory);
		plp.plpFilterByIntelI5ChBox.click();
		plp.plpFilterByMemory8GB.click();
		plp.listViewbtn.click();
		comm.waitElement(plp.plpItemDescription);
		plp.verifyWithForEachLoopDescription(td.filterItemByCpu5, td.filterByMemory8);
	}
}
