package com.orangehrm.job;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.NavigationTabMethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddPayGradeAutomateTest extends TestUtilsMethod {
	@Test
	private static void addPayGrade() throws InterruptedException {
		NavigationTabMethods.navigateToPayGradePage();
		String payGradeName = "Grade B";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement addButton = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Pay Grade page.");

		WebElement payGradeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input")));
		payGradeNameField.sendKeys(payGradeName);

		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
		saveButton.click();

		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		log.info("Pay Grade added successfully.");
	}
}
