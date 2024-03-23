package com.fnp;

import org.testng.annotations.Test;

import com.commonutils.BrowserController;
import com.pageobjectpattern.HomePage;

public class FnpAutomateTest extends BrowserController {

	@Test
	private static void addToCarttest() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.navigateTofnp();
		Thread.sleep(1500);

	}

}
