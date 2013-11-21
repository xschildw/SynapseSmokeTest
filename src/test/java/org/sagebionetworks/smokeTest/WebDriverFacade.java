package org.sagebionetworks.smokeTest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFacade implements WebDriver {
	
	private WebDriver driver;
	
	public WebDriverFacade(WebDriver driver) {
		this.driver = driver;
	}

	WebDriverFacade enterField(String cssSelector, String value) {
		driver.findElement(By.cssSelector(cssSelector)).sendKeys(value);
		return this;
	}
	
	void submit(String cssSelector) {
		WebElement form = driver.findElement(By.cssSelector(cssSelector));
		form.findElement(By.cssSelector("button[type=submit]")).click();
	}
	void click(String cssSelector) {
		driver.findElement(By.cssSelector(cssSelector)).click();
	}
	void assertErrorMessage(String cssSelector, String message) {
		waitUntil(cssSelector);
		WebElement errors = driver.findElement(By.cssSelector(cssSelector));
		Assert.assertTrue("Correct error ('"+message+"') in " + cssSelector, errors.getText().contains(message));		
	}
	void waitUntil(final String cssSelector) {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (d.findElement(By.cssSelector(cssSelector)) != null);
			}
		});	
	}
	void waitForTitle(final String title) {
		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().contains(title);
			}
		});
	}	
	
	void takeSnapshot(String path) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(path));
	}
	
	// Pass-throughs to the driver object.
	
	@Override
	public void close() {
		driver.close();
	}
	@Override
	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}
	@Override
	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}
	@Override
	public void get(String url) {
		driver.get(url);
	}
	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}
	@Override
	public String getTitle() {
		return driver.getTitle();
	}
	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}
	@Override
	public WebDriver.Options manage() {
		return driver.manage();
	}
	@Override
	public WebDriver.Navigation navigate() {
		return driver.navigate();
	}
	@Override
	public void quit() {
		driver.quit();
	}
	@Override
	public WebDriver.TargetLocator switchTo() {
		return driver.switchTo();
	}
}
