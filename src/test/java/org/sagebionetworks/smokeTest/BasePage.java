package org.sagebionetworks.smokeTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
	Implements the elements found on every page (i.e. header and footer)
*/
public class BasePage {
	private static final String XPATH_LINK_HOMEPAGE = "//a[@href='#']";
	private static final String XPATH_LINK_LSDF = "//a[@href='http://www.lsdfa.org/']";
	private static final String XPATH_LINK_SAGEBASE = "//p[@class='small']/a[@href='http://www.sagebase.org']";
	private static final String XPATH_LINK_FORUM = "//a[@href='http://support.sagebase.org']";
	private static final String XPATH_SEARCH_BOX_TOP = "//input[@type='text' and @placeholder='Search']";
	
	@FindBy(xpath=XPATH_LINK_HOMEPAGE)
	private WebElement linkHomepage;
	@FindBy(xpath=XPATH_LINK_LSDF)
	private WebElement linkLSDF;
	@FindBy(xpath=XPATH_LINK_SAGEBASE)
	private WebElement linkSagebase;
	@FindBy(xpath=XPATH_LINK_FORUM)
	private WebElement linkForum;
	@FindBy(xpath=XPATH_SEARCH_BOX_TOP)
	private WebElement inputSearchBoxTop;
	
	protected WebDriverFacade driver;
	protected String baseUrl;
	protected boolean loggedIn;
	
	public BasePage(WebDriverFacade driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
//	public void setBaseUrl(String url) {
//		baseUrl = url;
//	}
//	
	public String getBaseUrl() {
		return driver.getCurrentUrl();
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
	
	public HomePage doGoHome() {
		linkHomepage.click();
		HomePage p = new HomePage(driver);
		return p;
	}
	
	// TODO: Does not work for whatever reason
	public ExternalPage doGotoLSDF() throws IOException {
		linkLSDF.click();
		ExternalPage p = new ExternalPage(driver);
		return p;
	}
	
	public ExternalPage doGotoSagebase() {
		linkSagebase.click();
		ExternalPage p = new ExternalPage(driver);
		return p;
	}

		// Can't get the xpath to work...
	public SearchResultsPage doSearch(String searchTerm) {
		WebElement btn = driver.findElement(By.xpath("//a[contains(text(), 'Search')]"));
		WebElement inp = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[3]/div[4]/div/div/div/table/tbody/tr/td[2]/input"));
		SearchResultsPage p;
		inp.sendKeys(searchTerm);
		btn.click();
		p = PageFactory.initElements(driver, SearchResultsPage.class);
		return p;
	}

}
