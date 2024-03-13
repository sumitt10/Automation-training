package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.TestUtilsMethod;

public class AddMembershipPage extends TestUtilsMethod {

	public static void addDataToMembershipPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(config.getProperty("app.navigate_url"));

		WebElement addButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']")));
		addButton.click();
		String membershipName = config.getProperty("addName.membershipName");
		WebElement nameField = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")));
		nameField.sendKeys(membershipName);

		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		saveButton.click();

		Thread.sleep(3000);

	}

}
