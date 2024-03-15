package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class UpdateMembershipPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	private By editButtonLocator = By.xpath("//i[@class='oxd-icon bi-pencil-fill']");
	private By nameFieldLocator = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By saveButtonLocator = By.xpath("//button[@type='submit']");

	public UpdateMembershipPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getEditButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(editButtonLocator));
	}

	public WebElement getNameField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(nameFieldLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public void updateMembership() throws InterruptedException {
		driver.get(config.getProperty("app.navigate_url"));

		WebElement editButton = getEditButton();
		editButton.click();

		String editName = config.getProperty("addName.editName");
		WebElement nameField = getNameField();
		nameField.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
		nameField.sendKeys(editName);

		WebElement saveButton = getSaveButton();
		saveButton.click();
		Thread.sleep(3000);
	}
}
