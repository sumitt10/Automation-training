package com.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserController {
	public static WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\LENOVO\\Downloads\\browserDriver\\browser\\chromedriver.exe");
		driver = new ChromeDriver();
		log.info("Launching the browser...");

	}

	@AfterTest
	public void closingBrowser() {
		driver.quit();
		log.info("Closing the browser...");
	}

}
