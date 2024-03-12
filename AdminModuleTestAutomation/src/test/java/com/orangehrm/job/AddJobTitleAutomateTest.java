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
public class AddJobTitleAutomateTest extends TestUtilsMethod {
	@Test
	private static void addJobTitle() throws InterruptedException {
		NavigationTabMethods.navigateToJobTitlePage();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String jobTitle = "Prodct Intern";
		String jobDescription = "Working in the domain of testing, automation and so on.";

		WebElement addButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.oxd-button--medium")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Job Title page.");

		WebElement jobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
		jobTitleField.sendKeys(jobTitle);

		WebElement jobDescriptionField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea.oxd-textarea--active")));
		jobDescriptionField.sendKeys(jobDescription);

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")));
		saveButton.click();

		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		log.info("Job Title added successfully.");

	}

}
