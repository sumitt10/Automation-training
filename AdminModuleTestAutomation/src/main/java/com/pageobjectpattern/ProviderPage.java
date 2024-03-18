package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class ProviderPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	public ProviderPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void addProvider() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String name = config.getProperty("addconfiguration.name");
		String providerURL = config.getProperty("addconfiguration.url");
		String clientID = config.getProperty("addconfiguration.id");
		String clientSecret = config.getProperty("addconfiguration.secret");

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/openIdProvider");

		WebElement addButton = getAddButton();
		addButton.click();

		WebElement nameField = getNameField();
		nameField.sendKeys(name);

		WebElement providerURLField = getProviderURLField();
		providerURLField.sendKeys(providerURL);

		WebElement clientIDField = getClientIDField();
		clientIDField.sendKeys(clientID);

		WebElement clientSecretField = getClientSecretField();
		clientSecretField.sendKeys(clientSecret);

		WebElement saveButton = getSaveButton();
		saveButton.click();

		Thread.sleep(3000);

	}

	By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public WebElement gettoastmessage() {

		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public WebElement getAddButton() {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button")));
	}

	public WebElement getNameField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input")));
	}

	public WebElement getProviderURLField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/input")));
	}

	public WebElement getClientIDField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/input")));
	}

	public WebElement getClientSecretField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")));
	}
}
