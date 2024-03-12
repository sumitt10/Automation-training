package com.orangehrm.configuration;

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
public class AddProviderAutomateTest extends TestUtilsMethod {

	@Test
	private static void addProvider() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String name = "ExampleProvider";
		String providerURL = "https://provider.example.com";
		String clientID = "123456";
		String clientSecret = "secret123";

		NavigationTabMethods.navigateToProviderPage();

		WebElement addButton = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Provider page.");

		WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input")));
		nameField.sendKeys(name);

		WebElement providerURLField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/input")));
		providerURLField.sendKeys(providerURL);

		WebElement clientIDField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/input")));
		clientIDField.sendKeys(clientID);

		WebElement clientSecretField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")));
		clientSecretField.sendKeys(clientSecret);

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")));
		saveButton.click();

		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		log.info("Provider added successfully.");
	}
}
