package testSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.RegisterPageObjects;
import testData.TestData;

public class RegisterPomTestScripts extends Base {
	//se definiraat iminjata na Objectite sto ke se koristet za klasite
	HomePageObjects hp;
	RegisterPageObjects rp;
	TestData td;
	CommonPOM comm;
	
	@BeforeMethod
	public void start() {
		//se zema metodot od Base za start na testiranje
		testSetup();
		// new Objects od site klasi sto ke se koristi vo ovaa klasa
		hp = new HomePageObjects();
		rp = new RegisterPageObjects();
		td = new TestData();
		comm = new CommonPOM();
	}
	@AfterMethod
	public void end() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	@Test
	public void TC_REGISTER_002_RegisterUseOnlyMendatoryFields() {
		//comm.navigateTo(comm.hpRegisterLink);
		hp.hpRegisterLink.click();
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail1, td.validPassword, td.validConfrimPassword);
		rp.registerBtn.click();
		rp.regContinueBtn.click();
		rp.verifySuccessfulRegister();
	}
	@Test
	public void TC_REGISTER_002_RegisterUseAllFields() {
		hp.navigateRegisterPage();
		rp.registerUserOtherFields(td.companyName);
		rp.selectDateOfBirthDropBox(rp.regDayManu, td.validDay, rp.regMonthManu, td.validMonth, 
												rp.regYearManu, td.validYear);
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail6, td.validPassword, 
													td.validConfrimPassword);
		rp.verifySuccessfulRegister();
	}
	@Test
	public void TC_REGISTER_002_1_RegisterUseAllFieldsSingleSelect() {
		hp.navigateRegisterPage();
		rp.registerUserOtherFields(td.companyName);
		comm.selectFromDropManu(rp.regDayManu, td.validDay);
		comm.selectFromDropManu(rp.regMonthManu, td.validMonth);
		comm.selectFromDropManu(rp.regYearManu, td.validYear);
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail3, td.validPassword,	td.validConfrimPassword);
		rp.registerBtn.click();
		rp.regContinueBtn.click();
		rp.verifySuccessfulRegister();
	}
	@Test
	public void TC_REGISTER_004_RegisterNewUserWithExistingEmail() {
		hp.navigateRegisterPage();
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail4, td.validPassword, td.validConfrimPassword);
		rp.registerBtn.click();
		rp.verifyUnuccessfulRegisterExistingEmail();
	}
	
}


