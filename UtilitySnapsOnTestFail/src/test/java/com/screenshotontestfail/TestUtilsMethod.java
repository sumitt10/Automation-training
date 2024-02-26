package com.screenshotontestfail;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUtilsMethod {
	public static WebDriver driver;

	// Setup method to launch the browser before the test suite
	@BeforeSuite
	public void lauchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		log.info("Launching the browser...");

	}

	// Teardown method to close the browser after the test suite
	@AfterSuite
	public void closingBrowser() {
		driver.quit();
		log.info("Closing the browser...");

	}

	// Method to capture a screenshot
	public void getScreenshot() throws IOException {

		Date currentdate = new Date();

		// Format the date and time to create a unique filename (converted Date object to string)
		String screenshotfilename = currentdate.toString().replace(" ", "_").replace(":", "-");

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("C:\\Users\\LENOVO\\Downloads\\" + screenshotfilename + ".png"));
		log.info("Screenshot captured and saved at: C:\\Users\\LENOVO\\Downloads\\" + screenshotfilename + ".png");

	}
}
