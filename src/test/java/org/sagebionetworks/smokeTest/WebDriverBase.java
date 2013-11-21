// TODO: Change to factory

package org.sagebionetworks.smokeTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverBase {

	static protected WebDriverFacade _driver;

	static protected WebDriverFacade initDriver() {
		// Because system properties with periods in them are not portable to Bash.
		if (System.getProperty("phantomjs.binary.path") == null && 
			System.getProperty("PHANTOMJS_BINARY_PATH") != null) {
			System.setProperty("phantomjs.binary.path", System.getProperty("PHANTOMJS_BINARY_PATH"));
		}
		
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takescreenshot", true);
		// TODO: Move to property
		dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/local/bin/phantomjs");
		_driver = new WebDriverFacade(new PhantomJSDriver(dCaps));
		WebDriver.Window window = _driver.manage().window();
		window.setSize(new Dimension(1650,900));
		_driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		return _driver;
	}
	
	static public void closeDriver() {
		_driver.close();
		_driver.quit();
	}
}
