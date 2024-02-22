package com.orangehrm.utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrangeHRMUtility {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		login(driver);
		navigateToLanguagePage(driver);

		String expectedLanguageName = "Arabic";
		boolean isDataMatching = getDataAndCompare(driver, expectedLanguageName);

		if (isDataMatching) {
			log.info("Actual data matches the expected value: " + expectedLanguageName);
		} else {
			log.info("Actual data does not match the expected value: " + expectedLanguageName);
		}

		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	private static void login(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login ");
		driver.manage().window().maximize();

		String username = "Admin";
		String password = "admin123";

		WebElement usernameField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input')][@name='username']")));
		WebElement passwordField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input')][@name='password']")));
		WebElement loginButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@class, 'orangehrm-login-button')]")));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		log.info("Successfully logged in OrangeHRM webpage !!");
	}

	private static void navigateToLanguagePage(WebDriver driver) {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewLanguages");

		log.info("Navigated to the Languages page.");
	}

	private static boolean getDataAndCompare(WebDriver driver, String expectedValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By languageTableLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div");

		WebElement languageTable = wait.until(ExpectedConditions.presenceOfElementLocated(languageTableLocator));

		List<WebElement> languageRows = languageTable
				.findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]"));

		if (languageRows.isEmpty()) {
			log.error("No language rows found on the page.");
			return false;
		}

		List<String> actualLanguageNames = new ArrayList<>();

		for (WebElement row : languageRows) {
			WebElement languageNameCell = row
					.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]"));
			String actualLanguageName = languageNameCell.getText().trim();
			actualLanguageNames.add(actualLanguageName);
			log.info("Actual Language Name: " + actualLanguageName);
		}

		log.info("Expected Language Name: " + expectedValue);

		if (actualLanguageNames.isEmpty()) {
			log.error("No actual language names captured.");
			return false;
		}

		return actualLanguageNames.contains(expectedValue);
	}

}
