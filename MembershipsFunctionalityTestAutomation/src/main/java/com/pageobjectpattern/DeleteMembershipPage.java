package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class DeleteMembershipPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	private By deleteButtonLocator = By.xpath("//i[@class='oxd-icon bi-trash']");
	private By popUpButtonLocator = By.xpath("//button[normalize-space()='Yes, Delete']");

	public DeleteMembershipPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getDeleteButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
	}

	public WebElement getPopUpButton() {
		return driver.findElement(popUpButtonLocator);
	}

	public void deleteMembership() throws InterruptedException {
		driver.get(config.getProperty("app.navigate_url"));

		WebElement deleteButton = getDeleteButton();
		deleteButton.click();

		WebElement popUpButton = getPopUpButton();
		popUpButton.click();

		Thread.sleep(3000);
	}
}
