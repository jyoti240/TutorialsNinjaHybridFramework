package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public  LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retrieveEMailPasswordNotMatchingMessageText() {
		String WarningText = emailPasswordNotMatchingWarning.getText();
		return WarningText;
		
	}
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
		
	}
	
	public void enterPassword(String passwordtext) {
		passwordField.sendKeys(passwordtext);
		
	}
	
	public AccountPage loginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage login(String emailText,String passwordtext) {
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordtext);
		loginButton.click();
		return new AccountPage(driver);
	}
}
