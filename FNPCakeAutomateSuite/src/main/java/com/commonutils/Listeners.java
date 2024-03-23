package com.commonutils;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Listeners extends BrowserController implements ITestListener {

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case passed: " + result.getName());
		log.info("Test Success - Screenshot capturing !!");
		try {
			ScreenShotUtility.getScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case failed: " + result.getName());
		log.info("Test Fails - Screenshot capturing !!");
		try {
			ScreenShotUtility.getScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
