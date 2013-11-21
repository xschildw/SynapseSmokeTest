package org.sagebionetworks.smokeTest;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

/*
 *	This is the page we land on originally 
*/
public class HomePage extends BasePage {
	private static final String XPATH_LINK_STARTING_GUIDE = "";
	
	@FindBy(xpath=XPATH_LINK_STARTING_GUIDE)
	private WebElement linkStartingGuide;
	
	public HomePage(WebDriverFacade driver) throws RuntimeException {
		super(driver);
	}
	
	public EntityPage gotoStartingGuide() throws InterruptedException {
		EntityPage p;
		WebElement el;
		// TODO: Fix id and change xpath
		el = driver.findElement(By.xpath("id('rootPanel')/div/div/div[3]/div[2]/div[3]/div/ul/li[1]/a"));
		el.click();
		Thread.sleep(5000L);
		p = PageFactory.initElements(driver, EntityPage.class);
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
