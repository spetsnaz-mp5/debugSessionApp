package com.debugsessionapp.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;

public class GoogleHomePageTest extends BaseTest {
	@Test
	public void verifyGooglePageTittle() {
		driver.navigate().to(appURL);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Google");
	}
}
