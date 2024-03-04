package com.testattendancetracking;

import static org.testng.Assert.assertTrue;

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

/*
 * Section : OrangeHRM - Time/Attendance
 * Description : This class contains test cases to verify that the user can view their attendance history.
 * 
 */

public class ViewAttendanceHistoryTest extends TestUtilsMethod {

	/*
	 * Test to verify successful navigation to the My Records section.
	 */
	@Test
	public void testNavigationToMyRecords() {
		NavigationTabMethods.navigationToMyRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement pageTitle = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/h5")));

		AssertJUnit.assertEquals(pageTitle.getText(), "My Attendance Records");

	}

	/*
	 *  Test to verify the process of entering a date, clicking the View button will show the attendance history.
	 *  Asserts that a toast message is displayed, indicating a successful operation.
	 */
	@Test
	public void testToVerifyUserAttendanceHistory() throws InterruptedException {
		NavigationTabMethods.navigationToMyRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");

		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		viewButton.click();
		Thread.sleep(1500);
		By toastLocator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));
		assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);

	}

	/*
	 *  Test to verify the process of entering employee details, setting a date, will shows the user's attendance records.
	 *  Asserts that a toast message is displayed, indicating a successful operation.
	 */
	@Test
	public void testToShowUsersAttendanceHistory() throws InterruptedException {
		NavigationTabMethods.navigationToUsersRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		employeeNameInput.sendKeys("Sam Karan");
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		saveButton.click();
		Thread.sleep(2000);
		By toastLocator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));
		assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);

	}

	// Test to verify the presence of view button which shows attendance records
	@Test
	public void testPresenceOfMyRecordsOption() {
		NavigationTabMethods.navigationToMyRecords();
		WebElement myRecordsOption = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		AssertJUnit.assertTrue(myRecordsOption.isDisplayed());

	}

	// Test to verify the presence of date picker on the attendance tab to enter
	@Test
	public void testPresenceOfDatePicker() {
		NavigationTabMethods.navigationToMyRecords();
		WebElement datePicker = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		AssertJUnit.assertTrue(datePicker.isDisplayed());
	}

	// Test to verify view button is initially Enabled
	@Test
	public void testViewButtonInitiallyEnabled() {
		NavigationTabMethods.navigationToMyRecords();
		WebElement ViewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		AssertJUnit.assertTrue(ViewButton.isDisplayed());
		AssertJUnit.assertTrue(ViewButton.isEnabled());
	}

	// Test to verify Date picker Field Input 
	@Test
	public void TestDatePicker() {
		NavigationTabMethods.navigationToMyRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");
		datePickerInput.sendKeys(Keys.ENTER);

	}

	// Test to verify enable the view button with valid date
	@Test
	public void testEnablingViewButtonWithValidDate() throws InterruptedException {
		NavigationTabMethods.navigationToMyRecords();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");

		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		AssertJUnit.assertTrue(viewButton.isEnabled());

		Thread.sleep(5000);

	}

	// Test to verify invalid date disabled view button 
	@Test
	public void testInvalidDateDisablesViewButton() {
		NavigationTabMethods.navigationToMyRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-00");

		WebElement viewButton = driver
				.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button"));
		assert !viewButton.isEnabled() : "View button is enabled for an invalid date";

	}

	// Test to verify encountering error without entering date 
	@Test
	public void testErrorOnViewWithoutDate() {
		NavigationTabMethods.navigationToMyRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		viewButton.click();

		WebElement errorMessage = driver.findElement(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/span"));
		assert errorMessage.isDisplayed() : "Error message not displayed for missing date";
	}

	// Test to verify valid date will displays attendance records
	@Test
	public void testViewWithValidDateDisplaysRecords() {
		NavigationTabMethods.navigationToMyRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-03");
		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		viewButton.click();
		By Locator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

}
