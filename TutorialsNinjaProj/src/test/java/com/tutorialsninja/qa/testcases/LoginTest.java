package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorailsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	//super class constructor
	public LoginTest() {
		super();
	}
	public WebDriver driver;
	LoginPage loginPage;
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage=homePage.navigateToLoginPage();
	
	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage=loginPage.login(email,password);
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information is not displayed");
}
	@DataProvider(name="validCredentialsSupplier")
	public Object supplyTestData() {
		Object[][] data= Utilities.getTestDataFromExcel("Login");
			
		return data;
		
	}
	@Test(priority=2)
	public void verifyLoginInvalidCredentials() {
	
		loginPage.login(Utilities.genearateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));
		
		String actualWarningMessage= loginPage.retrieveEMailPasswordNotMatchingMessageText();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not matched");
}
	@Test(priority=3)
	public void verifyWithInalidEmailAndValidPassword() {
	
		loginPage.login(Utilities.genearateEmailWithTimeStamp(), prop.getProperty("validPassword"));

		String actualWarningMessage= loginPage.retrieveEMailPasswordNotMatchingMessageText();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not matched");
}
	@Test(priority=4)
	public void verifyWithValidEMailAndInvalidPassword() {
	
		loginPage.login(prop.getProperty("validEmail"),dataprop.getProperty("invalidPassword"));
	

		String actualWarningMessage= loginPage.retrieveEMailPasswordNotMatchingMessageText();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not matched");
}
	@Test(priority=5)
	public void verifyWithoutProvidingCredentials() {
	

		loginPage.loginButton();

		String actualWarningMessage= loginPage.retrieveEMailPasswordNotMatchingMessageText();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not matched");
	}
}
