package com.commonutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import lombok.extern.slf4j.Slf4j;

/**
*  TestRetryAnalyzer is a TestNG Retry Analyzer implementation that automatically retries failed test cases
*  up to a specified maximum number of retry attempts. 
*  It logs information about retry attempts,including the test case name and the current retry count.
*/
@Slf4j
public class TestRetryAnalyzer implements IRetryAnalyzer {
	private int retryCount = 0;
	private static final int maxRetryCount = 2; // Maximum number of retry attempts

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			log.info("Retrying test case " + result.getName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for " + (retryCount + 1) + " time.");
			retryCount++;
			return true;
		}
		return false;
	}

	private String getResultStatusName(int status) {
		String resultName;
		switch (status) {
		case ITestResult.FAILURE:
			resultName = "FAILURE";
			break;
		case ITestResult.SUCCESS:
			resultName = "SUCCESS";
			break;
		case ITestResult.SKIP:
			resultName = "SKIP";
			break;
		default:
			resultName = "UNKNOWN";
			break;
		}
		return resultName;
	}
}
