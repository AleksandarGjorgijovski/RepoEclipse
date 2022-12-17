package pageObjectsRepository;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class RegisterPageObjects extends Base {
	// Object od TestData klasata sto ke se koristi vo ovaa klasa
	TestData userTestData = new TestData();
	HomePageObjects homePage = new HomePageObjects();
	LoginPageObjects loginPage = new LoginPageObjects();
	
	
	//Defining WebElements
	@FindBy(xpath = "//input[@id='gender-male']")
	WebElement regGenderMale;
	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement regFirstNameField;
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement regLastNameField;
	@FindBy(xpath = "//select[@name='DateOfBirthDay']")
	public WebElement regDayManu;
	@FindBy(xpath = "//select[@name='DateOfBirthMonth']")
	public WebElement regMonthManu;
	@FindBy(xpath = "//select[@name='DateOfBirthYear']")
	public WebElement regYearManu;
	@FindBy(xpath = "//input[@id='Email']")
	WebElement regEmailField;
	@FindBy(xpath = "//input[@id='Company']")
	WebElement regCompanyField;
	@FindBy(xpath = "//input[@id='Password']")
	WebElement regPasswordField;
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	WebElement regConfirmPasswordField;
	@FindBy(xpath = "//button[text()='Register']")
	public WebElement registerBtn;
	@FindBy(xpath = "//div[text()='Your registration completed']")
	WebElement registerSuccMsg;
	@FindBy(xpath = "//a[@class='button-1 register-continue-button']")
	public WebElement regContinueBtn;
	@FindBy(xpath = "//a[@class='ico-account']")
	WebElement hpAccountLink;
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement regNewsletterChB;
	@FindBy(xpath = "//*[text()='The specified email already exists']")
	WebElement regErrExistingEmail;
	
	//Initiation na WebDriver (NE MOZI DA E VOID)
	public RegisterPageObjects() {
		PageFactory.initElements(driver, this);
	}
	//Methods related to Register page
	public void registerUserMandatoryFields(String firstName, String lastName, String email, String Password, String confirmPassword) {
		regFirstNameField.sendKeys(firstName);
		regLastNameField.sendKeys(lastName);
		regEmailField.sendKeys(email);
		regPasswordField.sendKeys(Password);
		regConfirmPasswordField.sendKeys(confirmPassword);
	}
	public void registerUserOtherFields(String companyName) {
		regGenderMale.click();
		regCompanyField.sendKeys(companyName);
		regNewsletterChB.click();
	}
//	public void selectFromDropManu(WebElement elementDropDown, String valueDropDown ) {
//		Select select = new Select(elementDropDown);
//		select.selectByValue(valueDropDown);	
//	}
	public void selectDateOfBirthDropBox(WebElement dayElement, String day, WebElement monthElement, String month, WebElement yearElement, String year ) {
		Select dayManu = new Select(dayElement);
		dayManu.selectByValue(day);
				
		Select monthManu = new Select(monthElement);
		monthManu.selectByValue(month);
		
		Select yearManu = new Select(yearElement);
		yearManu.selectByValue(year);
	}
	public void registerNewUserWithRandomEmail(String firstName, String lastName, String Password, String confirmPassword) {
		homePage.hpRegisterLink.click();
		regFirstNameField.sendKeys(firstName);
		regLastNameField.sendKeys(lastName);
		
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);  
		regEmailField.sendKeys(firstName.toLowerCase() + randomInt +"@hotmail.com");   
				
		regPasswordField.sendKeys(Password);
		regConfirmPasswordField.sendKeys(confirmPassword);
		registerBtn.click();
		regContinueBtn.click();
	}
	public void registerUserForCheckoutTestsBeforeMethod(String firstName, String lastName, String email, String Password, String confirmPassword) {
		homePage.navigateRegisterPage();
		regFirstNameField.sendKeys(firstName);
		regLastNameField.sendKeys(lastName);
		regEmailField.sendKeys(email);
		regPasswordField.sendKeys(Password);
		regConfirmPasswordField.sendKeys(confirmPassword);
		registerBtn.click();
		regContinueBtn.click();
		homePage.hpLogoutLink.click();
	}
	public void registerUserFromLoginPage(String firstName, String lastName, String email, String Password, String confirmPassword) {
		loginPage.lpRegisterLoginBtn.click();
		regFirstNameField.sendKeys(firstName);
		regLastNameField.sendKeys(lastName);
		regEmailField.sendKeys(email);
		regPasswordField.sendKeys(Password);
		regConfirmPasswordField.sendKeys(confirmPassword);
		registerBtn.click();
		regContinueBtn.click();
	}
	public void registerUserIfNotAlreadyRegistered(String firstName, String lastName, String email, String password, String confrimPassword) {
		int userIsNotRegister = driver.findElements(By.xpath("//a[@class='ico-login']")).size();
		if (userIsNotRegister > 0) {
			registerUserFromLoginPage(firstName, lastName, email, password, confrimPassword);
		}else if (userIsNotRegister == 0){
			System.out.println("user " + email + " is alrady Registerd");;
		}
	}
	
	//verifications
	public void verifySuccessfulRegister() {
		Assert.assertEquals(driver.getTitle(), userTestData.homePageTitle);
		//proverka
		//Assert.assertTrue(hpAccountLink.isDisplayed());
	}
	public void verifyUnuccessfulRegister() {
		Assert.assertEquals(driver.getTitle(), userTestData.registerPageTitle);
	}
	public void verifyUnuccessfulRegisterExistingEmail() {
		Assert.assertTrue(regErrExistingEmail.isDisplayed());
		System.out.println(regErrExistingEmail.getText());
		
	}
}
