/**
 * 
 */
package com.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author anirb
 *
 */
public class ConfigManager {
	private Properties props;
	private FileInputStream fis;

	public ConfigManager(String path) throws IOException {
		props = new Properties();
		fis = new FileInputStream(path);
		props.load(fis);
	}

	public String getProperty(String key) {
		return props.getProperty(key);
	}

}
