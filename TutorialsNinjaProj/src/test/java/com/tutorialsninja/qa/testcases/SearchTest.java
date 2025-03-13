package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorailsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setup() {
		driver =initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataprop.getProperty("validProduct"));
		searchPage=homePage.clickOnSearchButton();
		
       Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product HP is not displayed in search result");
}
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataprop.getProperty("invalidProduct"));
		searchPage=homePage.clickOnSearchButton();
		
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, "abc" ,"No Prodcut  ,message in search not displayed");
}
	
	@Test(priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage = new HomePage(driver);
		searchPage=homePage.clickOnSearchButton();
		
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataprop.getProperty("noProductSearchMessage") ,"No Prodcut  ,essage in search not displayed");
		
	}

}
//dataprop.getProperty("noProductSearchMessage") 