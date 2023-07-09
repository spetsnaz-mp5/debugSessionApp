/**
 * 
 */
package com.debugsessionapp.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.BaseTest;

/**
 * @author Anirban Singha Roy (311641)
 * @implNote To debug local open session
 * @since 08 July, 2023
 *
 */
public class DebugTest extends BaseTest {

	@Test
	public void verifyGooglePageTittle() {
		driver.findElements(By.xpath("//input")).forEach((element) -> {
			System.out.println(element.getTagName());
		});

	}

}
