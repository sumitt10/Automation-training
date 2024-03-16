package com.commonutils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Listeners extends BrowserUtils implements ITestListener {

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
			ScreenShotUtility screenShotUtility = new ScreenShotUtility();
			screenShotUtility.getScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	  * This method will be invoked by TestNG to give you a chance to modify a TestNG annotation read
	  * from your test classes. You can change the values you need by calling any of the setters on the
	  * ITest interface.
	  *
	  * <p>Note that only one of the three parameters testClass, testConstructor and testMethod will be
	  * non-null.
	  *
	  * @param annotation The annotation that was read from your test class.
	  * @param testClass If the annotation was found on a class, this parameter represents this class
	  *     (null otherwise).
	  * @param testConstructor If the annotation was found on a constructor, this parameter represents
	  *     this constructor (null otherwise).
	  * @param testMethod If the annotation was found on a method, this parameter represents this
	  *     method (null otherwise).
	  */
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,
			@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(TestRetryAnalyzer.class);
	}
}
