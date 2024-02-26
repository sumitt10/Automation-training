package com.screenshotontestfail;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Listeners extends TestUtilsMethod implements ITestListener {

	/**
	 * Called when a test case is starting.
	 * @param result Information about the test result.
	 */
	public void onTestStart(ITestResult result) {
		log.info("Test case is starting");
	}

	/**
	* Called when a test case gets fails.
	* Capture a screenshot upon test failure.
	* @param result Information about the failed test result.
	*/
	public void onTestFailure(ITestResult result) {
		log.info("Test Fails - Screenshot capturing !!");
		try {
			getScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
