package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class EmploymentStatusPage extends BrowserUtils {

	public void addEmploymentStatus() throws InterruptedException {
		EmploymentStatusPage employmentStatusPage = new EmploymentStatusPage();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/employmentStatus");

		String employmentStatus = config.getProperty("addjob.empstatus");
		employmentStatusPage.clickAddButton();

		employmentStatusPage.enterEmploymentStatus(employmentStatus);
		employmentStatusPage.clickSaveButton();

		Thread.sleep(3000);
	}

	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button");
	private By employmentStatusFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public void clickAddButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
		addButton.click();
	}

	public void enterEmploymentStatus(String employmentStatus) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement employmentStatusField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(employmentStatusFieldLocator));
		employmentStatusField.sendKeys(employmentStatus);
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
}
