package org.sagebionetworks.smokeTest;

import org.openqa.selenium.support.PageFactory;

/**
 * A page external to Synapse
 * Need to be able to check the url and the title
 * 
 * @author xavier
 */
public class ExternalPage {
	
	private WebDriverFacade driver;
	
	public ExternalPage(WebDriverFacade driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
}
