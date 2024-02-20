package com.webautomation;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddJobTitleAutomate {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		// Perform login, navigate to Job Titles page, and add a job title
		login(driver);
		navigateToJobTitlePage(driver);
		addJobTitle(driver, "Product Intern", "Working in the domain of testing, automation and so on.t  ");

		// Close the browser after a short delay (5 seconds)
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	private static void login(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Navigate to the OrangeHRM login page
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login ");

		driver.manage().window().maximize();

		// Input-login credentials
		String username = "Admin";
		String password = "admin123";

		// Locate web-elements through xpath
		WebElement usernameField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input')][@name='username']")));
		WebElement passwordField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input')][@name='password']")));
		WebElement loginButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@class, 'orangehrm-login-button')]")));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		log.info("Successfully logged in OrangeORM webpage !!");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void navigateToJobTitlePage(WebDriver driver) {
		// Navigate to the Job Title page
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList");

		log.info("Navigated to the Job Titles page.");
	}

	private static void addJobTitle(WebDriver driver, String jobTitle, String jobDescription) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate elements using CSS selector and XPath
		WebElement addButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.oxd-button--medium")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Job Title page.");

		WebElement jobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
		jobTitleField.sendKeys(jobTitle);

		WebElement jobDescriptionField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea.oxd-textarea--active")));
		jobDescriptionField.sendKeys(jobDescription);

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")));
		saveButton.click();

		log.info("Job Title added successfully.");

	}

}
