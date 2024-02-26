package com.screenshotontestfail;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTestCase extends TestUtilsMethod {
	// This is a sample test case
	@Test(testName = "MyTest")
	public void lauchApp() {
		driver.get("https://ebay.com");

		// Simulating a failure for demonstration purposes
		Assert.assertTrue(false);
	}
}
