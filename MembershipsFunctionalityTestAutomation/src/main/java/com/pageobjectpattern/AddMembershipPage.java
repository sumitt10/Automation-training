package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class AddMembershipPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	public AddMembershipPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private By addButtonLocator = By.xpath("//button[normalize-space()='Add']");
	private By nameFieldLocator = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By saveButtonLocator = By.xpath("//button[@type='submit']");
	private By toastMessageLocator = By.xpath("//div[@class='oxd-toast-container oxd-toast-container--bottom']");

	public WebElement getAddButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator));
	}

	public WebElement getNameField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(nameFieldLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public WebElement gettoastmessage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addMembership() throws InterruptedException {
		driver.get(config.getProperty("app.navigate_url"));

		WebElement addButton = getAddButton();
		addButton.click();

		String membershipName = config.getProperty("addName.membershipName");
		WebElement nameField = getNameField();
		nameField.sendKeys(membershipName);

		WebElement saveButton = getSaveButton();
		saveButton.click();
		Thread.sleep(3000);
	}
}
