package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorailsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@BeforeMethod
	public void setup() {
		
	
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickonMyAccount();
	  registerPage = homePage.selectRegisterOption();
		
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithManadateFields() {
		
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.genearateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
	
		
	
	String actualSuccessfulHeading= accountSuccessPage.retrieveAccountSuccessPageHeading();
	
	Assert.assertEquals(actualSuccessfulHeading, dataprop.getProperty("accountSucessfullyCreatedHeading"),"Account SUccess Page is not displayed");
}
	
	@Test(priority=2)
	public void verifyRegistringAccountByProvidingAllFields() {
		
	
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.genearateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsLetter();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
		
		
		String actualSuccessfulHeading= accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessfulHeading,dataprop.getProperty("accountSucessfullyCreatedHeading"),"Account SUccess Page is not displayed");
}

	@Test(priority=3)
	public void verifyRegisteringAnAccountWIthExistingEmailAddress() {

	
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsLetter();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		
		String actualSuccessfulHeading= registerPage.retrieveDuplicateEmailWarningText();
		
		Assert.assertEquals(actualSuccessfulHeading,dataprop.getProperty("duplicateEmailWarning"),"Warning message regarding duplicate email is not displayed");
}
	
	@Test(priority=4)
	public void verifyRegisteringAnAccountWithoutFIllingAnyDetails() {
		
		
		registerPage.clickOnContinueButton();
	

		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataprop.getProperty("privacyPolicyWarning")), "PrivacyWarning Message is not dispalyed");
		
		String actualFirstNameWarning=registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,dataprop.getProperty("firstNameWarning") );
		

		String actualLastNameWarning=registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataprop.getProperty("lastNameWarning"));
		

		String actualEmailWarning=registerPage.retrieveEmailAddressWarning();
		Assert.assertEquals(actualEmailWarning,dataprop.getProperty("emailWarning"));
		

		String actualTelephoneWarning=registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning,dataprop.getProperty("telephoneWarning") );
		

		String actualPasswordWarning=registerPage.retrievepasswordWarning();
		Assert.assertEquals(actualPasswordWarning,dataprop.getProperty("passwordWarning") );
		}
}
