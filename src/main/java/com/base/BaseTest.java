/**
 * 
 */
package com.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.configuration.ConfigManager;
import com.utilities.SessionManager;

/**
 * @author Anirban Singha Roy (311641)
 * @implNote To debug local open session
 * @since 08 July, 2023
 *
 */
public class BaseTest {
	protected WebDriver driver;
	protected String appURL = "http://google.com";

	@SuppressWarnings("resource")
	@BeforeClass
	public void testSetUp() throws IOException {

		ConfigManager cm = new ConfigManager(
				System.getProperty("user.dir") + "/src/main/resources/configuration/config.properties");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + cm.getProperty("chrome.driver.path"));
		File file = new File(System.getProperty("user.dir") + cm.getProperty("session.adress.store"));
		if (file.exists()) {
			System.out.println("Session file exists!");
			ChromeOptions chromeOptions = new ChromeOptions();
			String sessionAdd = new BufferedReader(new FileReader(file)).readLine();
			chromeOptions.setExperimentalOption("debuggerAddress", sessionAdd);
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(dc);
			driver = new ChromeDriver(chromeOptions);
		} else {
			System.out.println("Session file does not exists!");
			driver = new ChromeDriver();
		}
		SessionManager.storeSessionAddress(driver, cm);
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

}
