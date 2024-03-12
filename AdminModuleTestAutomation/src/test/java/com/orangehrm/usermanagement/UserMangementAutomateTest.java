package com.orangehrm.usermanagement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.NavigationTabMethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMangementAutomateTest extends TestUtilsMethod {
	@Test
	private static void addUserDetails() throws InterruptedException {
		AddUser addUser = new AddUser(driver, config);
		addUser.addUser();

		NavigationTabMethods.navigationToAddUserPage();
		log.info("Navigated to the Add User page.");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String userRole = config.getProperty("addUser.userRole");
		String partialEmployeeName = config.getProperty("addUser.partialEmployeeName");
		String status = config.getProperty("addUser.status");
		String username = config.getProperty("addUser.username");
		String password = config.getProperty("addUser.password");
		String confirmPassword = config.getProperty("addUser.confirmPassword");

		WebElement addButton = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")));
		userRoleDropdown.click();
		WebElement userRoleOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + userRole + "')]")));
		userRoleOption.click();

		WebElement employeeNameField = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input")));
		employeeNameField.sendKeys(partialEmployeeName);
		Thread.sleep(4000);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")));
		statusDropdown.click();
		WebElement statusOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + status + "')]")));
		statusOption.click();

		WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")));
		usernameField.sendKeys(username);

		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")));
		passwordField.sendKeys(password);

		WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")));
		confirmPasswordField.sendKeys(confirmPassword);

		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")));
		saveButton.click();

		Thread.sleep(4000);
		By Locator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}
}
