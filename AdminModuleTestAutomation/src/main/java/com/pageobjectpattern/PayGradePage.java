package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import com.commonutils.BrowserUtils;

public class PayGradePage extends BrowserUtils {

	public void navigateToPayGradePage() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewPayGrades");

	}

	public void enterPayGradeName(String payGradeName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addButton = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		WebElement payGradeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input")));
		payGradeNameField.sendKeys(payGradeName);
	}

	public void clickSaveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
		saveButton.click();
	}

	public boolean isToastMessageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return toastMessage.isDisplayed();
	}

	public void addPayGrade() throws InterruptedException {
		PayGradePage payGradePage = new PayGradePage();
		payGradePage.navigateToPayGradePage();

		String payGradeName = config.getProperty("addjob.paygrade");
		payGradePage.enterPayGradeName(payGradeName);
		payGradePage.clickSaveButton();

		Thread.sleep(3000);
		AssertJUnit.assertTrue(payGradePage.isToastMessageDisplayed());
	}
}