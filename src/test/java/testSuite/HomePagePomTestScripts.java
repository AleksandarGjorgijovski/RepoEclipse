package testSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.LoginPageObjects;
import pageObjectsRepository.RegisterPageObjects;
import testData.TestData;

public class HomePagePomTestScripts extends Base {
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	TestData userTestData;
	RegisterPageObjects registerPage;
	
	@BeforeMethod
	public void start() {
		//se zema metodot od Base za start na testiranje
		testSetup();
		// new Objects od site klasi sto ke se koristi vo ovaa klasa
		homePage = new HomePageObjects();
		loginPage = new LoginPageObjects();
		userTestData = new TestData();
		registerPage = new RegisterPageObjects();
	}
	@AfterMethod
	public void end() throws InterruptedException {
		Thread.sleep(2000);
		//driver.quit();
	}
	@Test	
	public void VerifyVoteByScrollingToElement() { 
		homePage.navigateLoginPage();
		homePage.scrollTo(loginPage.lpLoginBtn);
		loginPage.loginUser(userTestData.validEmail1, userTestData.validPassword);
		loginPage.lpLoginBtn.click();
		homePage.scrollTo(homePage.hpVoteBtn);
		homePage.hpVoteExellentRadioBtn.click();
		homePage.hpVoteBtn.click();
		homePage.waitElement(homePage.hpLogoutLink);
		homePage.verifyVoteIsSuccessful();
	}
	@Test	
	public void VerifyVoteByScrollingToElementWithRandomUserGenerated() { 
		registerPage.registerNewUserWithRandomEmail(userTestData.firstName, userTestData.lastName,userTestData.validPassword, userTestData.validConfrimPassword);
		homePage.scrollTo(homePage.hpVoteBtn);
		homePage.hpVoteExellentRadioBtn.click();
		homePage.hpVoteBtn.click();
		homePage.waitElement(homePage.hpLogoutLink);
		homePage.waitElement(homePage.hpPollResults);
		homePage.verifyVoteIsSuccessful();
	}
	
}