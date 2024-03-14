package com.testattendancetracking;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.PageObjectMethods;

import lombok.extern.slf4j.Slf4j;

/*
 * Section : OrangeHRM - Time/Reports/Attendance
 * Description :  This class contains test cases related to Users Attendance track.
 * 
 */
@Slf4j
public class AttendanceTrackAutomateTest extends TestUtilsMethod {
	/*  
	 *   Test to verify the process of entering employee details, date and displaying the reports. 
	 *   Asserts the presence of a toast message indicating a successful operation.
	 */
	@Test
	public void testToShowUserAttendanceReport() throws InterruptedException {
		PageObjectMethods.addDataToAttendancReportTab();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Thread.sleep(3000);
		WebElement toastMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div")));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	/*
	 *  Test to verify user's actual attendance (Total time hours) is Correctly reflected in Reports or not
	 *  Asserts the equality between the user's actual attendance and the displayed attendance in the report.
	 */
	@Test
	public void testToVerifyUserAttendanceInReports() throws InterruptedException {
		PageObjectMethods.addUsernameAndDate();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Thread.sleep(5000);
		WebElement userAttendanceReport = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[2]")));
		String userTimeInReport = userAttendanceReport.getText();

		PageObjectMethods.addUserDataToAttendanceRecords();

		Thread.sleep(5000);

		WebElement userActualAttendance = null;
		String userActualTime = null;
		By userAttendanceLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[2]/div/div/div[6]");

		List<WebElement> elements = driver.findElements(userAttendanceLocator);

		if (!elements.isEmpty()) {
			userActualAttendance = wait.until(ExpectedConditions.presenceOfElementLocated(userAttendanceLocator));
			userActualTime = userActualAttendance.getText();
			Assert.assertEquals(userActualTime, userTimeInReport);
		} else {
			log.info("User actual attendance is not present there.");
		}

	}

	/*
	 *  Test to verify that the user's name in the report matches the actual user name.
	 *  Asserts the equality between the actual user name and the displayed user name in the report..
	 */
	@Test
	public void testToVerifyUserNameInReports() throws InterruptedException {
		PageObjectMethods.addUsernameAndDate();

		Thread.sleep(5000);
		WebElement userNameAttendanceReport = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[1]")));
		String userNameInReport = userNameAttendanceReport.getText();

		PageObjectMethods.addUserDataToAttendanceRecords();

		String userNameActual = config.getProperty("addUser.username");
		Assert.assertEquals(userNameActual, userNameInReport);

	}

	/*
	 *  Test to verify the process of entering a date, clicking the View button will show the attendance history.
	 *  Asserts that a toast message is displayed, indicating a successful operation.
	 */
	@Test
	public void testToVerifyUserAttendanceHistory() throws InterruptedException {
		PageObjectMethods.addUserDate();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
		PageObjectMethods.addUserDataToAttendanceRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Thread.sleep(2000);
		By Locator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[1]");
		WebElement viewMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		assertTrue(viewMessage.isDisplayed());
		Thread.sleep(3000);

	}

	// Test to verify the presence of view button which shows attendance records
	@Test
	public void testPresenceOfMyRecordsOption() {
		driver.get(config.getProperty("app.my_records_url"));
		WebElement myRecordsOption = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		AssertJUnit.assertTrue(myRecordsOption.isDisplayed());

	}

	// Test to verify the presence of date picker on the attendance tab to enter
	@Test
	public void testPresenceOfDatePicker() {
		driver.get(config.getProperty("app.my_records_url"));
		WebElement datePicker = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		AssertJUnit.assertTrue(datePicker.isDisplayed());
	}

	// Test to verify view button is initially Enabled
	@Test
	public void testViewButtonInitiallyEnabled() {
		driver.get(config.getProperty("app.my_records_url"));
		WebElement ViewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		AssertJUnit.assertTrue(ViewButton.isDisplayed());
		AssertJUnit.assertTrue(ViewButton.isEnabled());
	}

	// Test to verify Date picker Field Input 
	@Test
	public void TestDatePicker() {
		driver.get(config.getProperty("app.my_records_url"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		String date = config.getProperty("addUser.date");
		datePickerInput.sendKeys(date);
		datePickerInput.sendKeys(Keys.ENTER);

	}

	// Test to verify enable the view button with valid date
	@Test
	public void testEnablingViewButtonWithValidDate() throws InterruptedException {
		PageObjectMethods.addUserDate();

		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		AssertJUnit.assertTrue(viewButton.isEnabled());

		Thread.sleep(5000);

	}

	// Test to verify invalid date disabled view button 
	@Test
	public void testInvalidDateDisablesViewButton() {
		PageObjectMethods.userDate("200-30-3");

		WebElement viewButton = driver
				.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button"));
		assert !viewButton.isEnabled() : "View button is enabled for an invalid date";

	}

	// Test to verify encountering error without entering date 
	@Test
	public void testErrorOnViewWithoutDate() {
		PageObjectMethods.userDate(" ");

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String date = config.getProperty("addUser.date");
		PageObjectMethods.userDate(date);

		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		viewButton.click();
		WebElement toastMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div")));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

}
