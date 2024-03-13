package com.orangehrm.admin.memberships;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.AutomatePageMethods;
import com.pageobjectpattern.NavigationTabMethods;

public class MembershipsOperationAutomateTest extends TestUtilsMethod {
	/*
	 *  This test case verifies the functionality of adding a new membership. 
	 *  It navigates to the membership page, clicks on the add button, fills in the required data, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void addMembershiptest() throws InterruptedException {
		NavigationTabMethods.navigationToMembershipPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement addButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button")));
		addButton.click();

		AutomatePageMethods.addDataToMembershipPage();
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *  This test case verifies the functionality of editing an existing membership.
	 *  It navigates to the membership page, clicks on the edit button for a membership, edits the data, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void editMembershiptest() throws InterruptedException {
		NavigationTabMethods.navigationToMembershipPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[2]")));
		editButton.click();
		AutomatePageMethods.editMembershipDataPage();

		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *  This test case verifies the functionality of deleting an existing membership.  
	 *  It navigates to the membership page, clicks on the delete button for a membership, confirms the deletion, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void deleteMembershiptest() throws InterruptedException {
		NavigationTabMethods.navigationToMembershipPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[1]")));
		deleteButton.click();

		WebElement popUpElement = driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]"));
		popUpElement.click();
		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *   This test case verifies that an error message is displayed when trying to save a membership with an empty name. 
	 */
	@Test
	private static void testToShowErrorWithEmptyName() throws InterruptedException {
		NavigationTabMethods.navigationToMembershipPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement addButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button")));
		addButton.click();
		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
		saveButton.click();
		WebElement ErrorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/span")));
		AssertJUnit.assertTrue(ErrorMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *   This test case verifies that an error prompt is displayed when trying to save an edited membership without making any changes.
	 */
	@Test
	private static void TestErrorPromptForNoUpdation() throws InterruptedException {
		NavigationTabMethods.navigationToMembershipPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[2]")));
		editButton.click();

		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
		saveButton.click();
		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/span");
		WebElement promptMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(promptMessage.isDisplayed());
		Thread.sleep(3000);
	}
}
