package org.sagebionetworks.smokeTest;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	private WebDriverFacade driver;
	
	private static final String URL_SUFFIX_LOGIN_PLACE = "#!LoginPlace:0";
	private static final String XPATH_PANEL_LOGIN_SYNAPSE = "//h2[contains(text(), 'Login to Synapse')]";
	private static final String XPATH_BTN_SIGNIN_GOOGLE = "//*[@id='login-via-gapp-google']";
	private static final String XPATH_BTN_LOGIN = "//div/div[2]/button[contains(text(), 'Sign in')]";
	private static final String XPATH_INPUT_EMAIL = "//input[@placeholder='Email Address']";
	private static final String XPATH_INPUT_PASSWORD = "//input[@placeholder='Password']";
	private static final String XPATH_TXT_INVALID_USER = "//h4[contains(text(), 'Invalid')]";

	/* Old version, need to set IDs again in SWC source
	private static final String synapseLoginInputXpath = "//input[@id='" + UiConstants.ID_INP_EMAIL_NAME + "-input']";
	private static final String synapseLoginInputId = UiConstants.ID_INP_EMAIL_NAME + "-input";
	private static final String synapsePasswordInputXpath = "//input[@id='" + UiConstants.ID_INP_EMAIL_PASSWORD + "-input']";
	private static final String synapsePasswordInputId = UiConstants.ID_INP_EMAIL_PASSWORD + "-input";
	private static final String synapseLoginButtonXpath = "//table[@id='" + UiConstants.ID_BTN_LOGIN2 + "']/tbody/tr[2]/td[2]/em/button";
	private static final String openIdLoginButtonXpath = "//form[@id='gapp-openid-form']//button[@id='"+ UiConstants.ID_BTN_GOOGLE_LOGIN + "'][@type='submit']";
	*/
	
	public LoginPage(WebDriverFacade driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath=XPATH_INPUT_EMAIL)
	private WebElement inputSynapseEmail;
	@FindBy(xpath=XPATH_INPUT_PASSWORD)
	private WebElement inputSynapsePassword;
	@FindBy(xpath=XPATH_BTN_LOGIN)
	private WebElement btnSynapseLogin;
	@FindBy(xpath=XPATH_BTN_SIGNIN_GOOGLE)
	private WebElement btnOpenIdLogin;
	@FindBy(xpath=XPATH_TXT_INVALID_USER)
	private WebElement txtInvalidUserMsg;

	public String getTitle() {
		return this.driver.getTitle();
	}
	
	public String getUrl() {
		return this.driver.getCurrentUrl();
	}
	
	public LoginPage doSynapseInvalidLogin() throws InterruptedException, IOException {
		inputSynapseEmail.clear();
		inputSynapseEmail.sendKeys("abc@def");
		inputSynapsePassword.clear();
		inputSynapsePassword.sendKeys("ghijk");
		btnSynapseLogin.click();
		Thread.sleep(1000);
		String s = this.getUrl();
		assertTrue(s.endsWith(this.URL_SUFFIX_LOGIN_PLACE));
		assertTrue(txtInvalidUserMsg.isDisplayed());
		
//		if (this.loggedIn()) {
//			UserHomePage p = PageFactory.initElements(driver, UserHomePage.class);
//			return p;
//		} else {
//			return null;
//		}
		return this;
		// TODO: Throw exception if login fails
	}
	/*
	public UserHomePage synapseLoginWithTOS(String user, String password) throws InterruptedException {
		inputSynapseLogin.clear();
		inputSynapseLogin.sendKeys(user);
		inputSynapsePassword.clear();
		inputSynapsePassword.sendKeys(password);
		Thread.sleep(500);
		btnSynapseLogin.click();
		Thread.sleep(2000);
		// The TOS should show up
		WebElement e = driver.findElement(By.xpath("//*[contains(text(),'I Agree')]"));
		e.click();
		UserHomePage p = PageFactory.initElements(driver, UserHomePage.class);
		return p;
	}
	
	public UserHomePage openIdLogin(String user, String password) throws InterruptedException {
		UserHomePage p;
		btnOpenIdLogin.click();
		// Handle logged in / logged out of Google cases
		Thread.sleep(2000);	// to handle indirections if already logged in
		if ("Google Accounts".equals(driver.getTitle().trim())) { // Not logged in
			// We need to log into Google
			WebElement el;
			el = driver.findElement(By.id("Email"));
			el.sendKeys(user);
			el = driver.findElement(By.id("Passwd"));
			el.sendKeys(password);
			el = driver.findElement(By.id("signIn"));
			el.click();
			Thread.sleep(2000);
		}
		// We should be back at user homepage
		// TODO: Check
		p = PageFactory.initElements(driver, UserHomePage.class);
		return p;
	}
	*/
}
