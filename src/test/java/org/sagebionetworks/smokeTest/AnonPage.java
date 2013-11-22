package org.sagebionetworks.smokeTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * AnonPage provides the functionality of the anonymous header:
 * - Register
 * - Login
 * 
 */
public class AnonPage  {
	private WebDriverFacade driver;
	
	private static final String XPATH_LINK_REGISTER = "//a[contains(text(), 'Register')]";
	private static final String XPATH_LINK_LOGIN = "//a[contains(text(), 'Login')]";
	
	@FindBy(xpath=XPATH_LINK_LOGIN)
	private WebElement linkLogin;
	@FindBy(xpath=XPATH_LINK_REGISTER)
	private WebElement linkRegister;

	public AnonPage(WebDriverFacade driver) throws RuntimeException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public LoginPage doLogin() {
		linkLogin.click();
		LoginPage p = new LoginPage(driver);
		return p;
	}
	
	public RegisterPage doRegister() {
		linkRegister.click();
		RegisterPage p = new RegisterPage(driver);
		return p;
	}

}
