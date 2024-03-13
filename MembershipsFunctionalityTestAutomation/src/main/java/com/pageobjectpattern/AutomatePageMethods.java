package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.TestUtilsMethod;

/*
 * Section : OrangeHRM
 * Description : This class facilitates for the different Page methods required for the test cases in the OrangeHRM website.
 * 
 */

public class AutomatePageMethods extends TestUtilsMethod {

	public static void addDataToMembershipPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String membershipName = config.getProperty("addName.membershipName");
		WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
		nameField.sendKeys(membershipName);

		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
		saveButton.click();

		Thread.sleep(3000);

	}

	public static void editMembershipDataPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String editName = config.getProperty("addName.editName");

		WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
		nameField.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
		nameField.sendKeys(editName);

		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
		saveButton.click();

		Thread.sleep(3000);
	}

}
