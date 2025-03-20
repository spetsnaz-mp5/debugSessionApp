/**
 * 
 */
package com.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
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
		File file = new File(cm.getProperty("session.adress.store"));
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.addArguments("start-maximized");
		if (file.exists()) {
			System.out.println("Session file exists!");
			String sessionAdd = new BufferedReader(new FileReader(file)).readLine();
			chromeOptions.setExperimentalOption("debuggerAddress", sessionAdd);
		} else {
			System.out.println("Session file does not exists!");
		//	chromeOptions.setExperimentalOption("detach", "true");
		}

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		chromeOptions.merge(dc);
		driver = WebDriverManager.chromedriver().capabilities(chromeOptions).avoidShutdownHook().create();
		SessionManager.storeSessionAddress(driver, cm);
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

}
