package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class EducationPage extends BrowserUtils {

	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button");
	private By educationNameFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public void clickAddButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
	}

	public void enterEducationName(String educationName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement educationNameField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(educationNameFieldLocator));
		educationNameField.sendKeys(educationName);
	}

	public void clickSaveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
		saveButton.click();
	}

	public boolean isToastMessageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
		return toastMessage.isDisplayed();
	}

	public void addEducation() throws InterruptedException {
		EducationPage educationPage = new EducationPage();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewEducation");
		String educationName = config.getProperty("addEducation.education");

		educationPage.clickAddButton();
		educationPage.enterEducationName(educationName);
		educationPage.clickSaveButton();
		Thread.sleep(3000);
	}
}
