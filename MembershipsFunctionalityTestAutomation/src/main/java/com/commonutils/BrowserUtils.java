package com.commonutils;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.pageobjectpattern.OrangeHRMLoginPage;

import lombok.extern.slf4j.Slf4j;

/**
 * BrowserUtils class provides methods to manage the WebDriver instance and perform setup and teardown actions
 * before and after the test suite.
 */

@Slf4j
public class BrowserUtils {
	public static WebDriver driver;
	private OrangeHRMLoginPage orangeHRMLoginPage;
	public static Properties config;

	static {
		config = new Properties();
		try {
			config.load(BrowserUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Setup method to launch the browser before the test suite
	@BeforeSuite
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", config.getProperty("webdriver.chrome.driver"));
		driver = new ChromeDriver();
		orangeHRMLoginPage = new OrangeHRMLoginPage(driver, config);
		log.info("Launching the browser...");
		orangeHRMLoginPage.login();
		log.info("Login To Webpage...");

	}

	// Teardown method to close the browser after the test suite
	@AfterSuite
	public void closingBrowser() {
		driver.quit();
		log.info("Closing the browser...");
	}

}
