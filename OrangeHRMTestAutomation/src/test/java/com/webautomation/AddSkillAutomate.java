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
public class AddSkillAutomate {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Perform login, navigate to Skills page, and add a skill
		login(driver);
		navigateToSkillPage(driver);
		addSkill(driver, "CPP", "Programming Language");

		// Close the browser after a short delay (5 seconds)
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

	}

	private static void navigateToSkillPage(WebDriver driver) {
		// Navigate to the Skills page
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSkills");

		log.info("Navigated to the Skills page.");
	}

	private static void addSkill(WebDriver driver, String skillName, String skillDescription) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate elements using XPath
		WebElement addButton = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button/i")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Skill page.");

		WebElement skillNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
		skillNameField.sendKeys(skillName);

		WebElement skillDescriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/textarea")));
		skillDescriptionField.sendKeys(skillDescription);

		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")));
		saveButton.click();

		log.info("Skill added successfully.");
	}
}
