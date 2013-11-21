package org.sagebionetworks.smokeTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
	Implements the elements found on every page (i.e. header and footer)
*/
public class BasePage {
	private static final String XPATH_LINK_HOMEPAGE = "//a[@href='#']";
	private static final String XPATH_LINK_LSDF = "//a[@href='http://www.lsdfa.org/'";
	private static final String XPATH_LINK_SAGEBASE = "//p[@class='small']/a[@href='http://www.sagebase.org']";
	
	@FindBy(xpath=XPATH_LINK_HOMEPAGE)
	private WebElement linkHomepage;
	@FindBy(xpath=XPATH_LINK_LSDF)
	private WebElement linkLSDF;
	@FindBy(xpath=XPATH_LINK_SAGEBASE)
	private WebElement linkSagebase;
	
	protected WebDriverFacade driver;
	protected String baseUrl;
	protected boolean loggedIn;
	
	public BasePage(WebDriverFacade driver) {
		this.driver = driver;
	}
	
	public void setUrl(String url) {
		baseUrl = url;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
	
	public HomePage goHome() {
		linkHomepage.click();
		HomePage p = PageFactory.initElements(driver, HomePage.class);
		return p;
	}
}
