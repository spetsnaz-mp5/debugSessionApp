/**
 * 
 */
package com.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.configuration.ConfigManager;

/**
 * @author Anirban Singha Roy (311641)
 * @implNote To store local open session address
 * @since 08 July, 2023
 *
 */
public class SessionManager {
	private static List<String> writeableList = new ArrayList<String>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void storeSessionAddress(WebDriver driver, ConfigManager cm) throws IOException {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		Map<String, Object> cMAP = capabilities.asMap();
		cMAP.forEach((key, value) -> {
			try {
				if (key.contains("chromeOptions")) {
					((Map) value).forEach((k, v) -> {
						if (((String) k).contains("debuggerAddress")) {
							writeableList.add(v.toString());
						}
					});
					Files.write(Paths.get(System.getProperty("user.dir") + cm.getProperty("session.adress.store")),
							writeableList, StandardOpenOption.CREATE);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println("Key is:" + key+ ", Value is:"+value);
		});
	}
}
