package com.testfailsnap;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScreenshotOnFailLogin {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		login(driver);

		driver.quit();
	}

	/**
	 * Performing login on the Practice test automation web page.
	 * This is the sample test.
	 * @param driver WebDriver instance
	 */
	private static void login(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize();

		String username = "student";
		// Trying with wrong password so that test get fails 
		String password = "abc";

		WebElement usernameField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div/section/section/div[1]/div[1]/input")));
		WebElement passwordField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div/section/section/div[1]/div[2]/input")));
		WebElement loginButton = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/section/section/div[1]/button")));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

		// Checking if login successful or not 
		if (!driver.getCurrentUrl().contains("dashboard")) {
			log.error("Login failed. Capturing screenshot...");
			captureScreenshot(driver, "login_failure");
		} else {
			log.info("Successfully logged in!!");
		}
	}

	/**
	 * Captures a screenshot of the current WebDriver instance.
	 *
	 * @param driver          - WebDriver instance
	 * @param screenshotName  - Name to be used for the screenshot file
	 */
	private static void captureScreenshot(WebDriver driver, String screenshotName) {
		if (driver instanceof TakesScreenshot) {
			TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
			File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

			// Specified the path for storing the failure screenshot
			String screenshotPath = "C:\\Users\\LENOVO\\Downloads\\" + screenshotName + ".png";

			try {
				FileUtils.copyFile(screenshot, new File(screenshotPath));
				log.info("Screenshot captured for test: " + screenshotName);
				log.info("Screenshot saved at: " + screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
