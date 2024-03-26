package com.commonutils;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import lombok.extern.slf4j.Slf4j;

/**
 * BrowserUtils class provides methods to manage the WebDriver instance and perform setup and teardown actions
 * before and after the test suite.
 */

@Slf4j
public class BrowserUtils {
	public static WebDriver driver;
	public static Properties config;
	ChromeOptions chromeoptions = new ChromeOptions();
	EdgeOptions edgeoptions = new EdgeOptions();

	static {
		config = new Properties();
		try {
			config.load(BrowserUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Setup method to launch the browser before the test suite
	@Parameters("browser")
	@BeforeTest
	public void launchBrowser(String browser) throws InterruptedException {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\LENOVO\\Downloads\\browserDriver\\browser\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("chrome is launched !!");
		}

		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\LENOVO\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
			log.info("Edge is launched !!");
		}

	}

	// Teardown method to close the browser after the test suite
	@AfterTest
	public void closingBrowser() {
		driver.quit();
		log.info("Closing the browser...");
	}

}
