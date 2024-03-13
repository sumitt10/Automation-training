package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.TestUtilsMethod;

public class UpdateMembershipPage extends TestUtilsMethod {

	public static void editMembershipDataPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(config.getProperty("app.navigate_url"));

		WebElement editButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")));
		editButton.click();
		String editName = config.getProperty("addName.editName");

		WebElement nameField = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")));
		nameField.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
		nameField.sendKeys(editName);

		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		saveButton.click();

		Thread.sleep(3000);
	}

}
