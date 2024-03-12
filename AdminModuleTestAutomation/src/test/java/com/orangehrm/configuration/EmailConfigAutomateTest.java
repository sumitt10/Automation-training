package com.orangehrm.configuration;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.NavigationTabMethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailConfigAutomateTest extends TestUtilsMethod {

	@Test
	private static void configureEmailSettings() throws InterruptedException {
		NavigationTabMethods.navigateToEmailConfigPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String mailSentAs = "info@orangehrm.com";
		boolean sendTestMail = true;
		String testEmailAddress = "test.email@example.com";

		WebElement mailSentAsField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input")));
		mailSentAsField.sendKeys(Keys.CONTROL + "a");
		mailSentAsField.sendKeys(Keys.DELETE);
		mailSentAsField.sendKeys(mailSentAs);

		WebElement sendTestMailToggle = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div/label/span")));
		if (sendTestMailToggle.isSelected() != sendTestMail) {
			sendTestMailToggle.click();
		}

		if (sendTestMail) {
			WebElement testEmailAddressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div[2]/input")));
			testEmailAddressField.sendKeys(testEmailAddress);
		}
		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]")));
		saveButton.click();

		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		log.info("Email Configuration saved successfully.");
	}
}
