package com.webautomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddLocationAutomate {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		login(driver);
		navigateToLocations(driver);
		addLocation(driver, "Dummy Loca", "Dummy City", "Dummy State", "12345", "Australia", "1234567890",
				"Dummy Address");
		Thread.sleep(5000);
		driver.quit();
	}

	private static void login(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

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

		log.info("Successfully logged in.");
	}

	private static void navigateToLocations(WebDriver driver) {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewLocations");

		log.info("Navigated to the Locations page.");
	}

	private static void addLocation(WebDriver driver, String name, String city, String state, String postalCode,
			String country, String phoneNumber, String address) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//button[@class='oxd-button oxd-button--medium oxd-button--secondary' and @data-v-10d463b7='']")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Location page.");

		By nameFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input");
		WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(nameFieldLocator));

		By cityFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
		WebElement cityField = wait.until(ExpectedConditions.presenceOfElementLocated(cityFieldLocator));

		By stateFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
		WebElement stateField = wait.until(ExpectedConditions.presenceOfElementLocated(stateFieldLocator));

		By zipCodeFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[3]/div/div[2]/input");
		WebElement zipCodeField = wait.until(ExpectedConditions.presenceOfElementLocated(zipCodeFieldLocator));

		WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[4]/div/div[2]/div/div")));
		userRoleDropdown.click();
		WebElement userRoleOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + country + "')]")));
		userRoleOption.click();

		By phoneFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[5]/div/div[2]/input");
		WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(phoneFieldLocator));

		By addressFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[7]/div/div[2]/textarea");
		WebElement addressField = wait.until(ExpectedConditions.presenceOfElementLocated(addressFieldLocator));

		nameField.sendKeys(name);
		cityField.sendKeys(city);
		stateField.sendKeys(state);
		zipCodeField.sendKeys(postalCode);
		phoneField.sendKeys(phoneNumber);
		addressField.sendKeys(address);

		WebElement saveButton = driver
				.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		log.info("Location added successfully.");
	}
}