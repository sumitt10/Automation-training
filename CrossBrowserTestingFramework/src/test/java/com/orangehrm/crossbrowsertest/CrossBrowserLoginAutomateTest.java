package com.orangehrm.crossbrowsertest;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.BrowserUtils;
import com.pageobjectpattern.OrangeHRMLoginPage;

public class CrossBrowserLoginAutomateTest extends BrowserUtils {

	@Test
	private static void crossBrowsertest() throws InterruptedException {
		OrangeHRMLoginPage orangeHRMLoginPage = new OrangeHRMLoginPage(driver, config);
		orangeHRMLoginPage.login();

		AssertJUnit.assertTrue(orangeHRMLoginPage.getHeader().isDisplayed());
		Thread.sleep(3000);
	}

}
