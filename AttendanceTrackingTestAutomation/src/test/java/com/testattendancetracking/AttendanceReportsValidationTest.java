package com.testattendancetracking;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.NavigationTabMethods;

/*
 * Section : OrangeHRM - Time/Reports/AttendanceReportSummary
 * Description :  This class contains test cases to verify that the user's attendance is correctly reflected in reports.
 * 
 */

public class AttendanceReportsValidationTest extends TestUtilsMethod {
	private static final Logger log = Logger.getLogger(AttendanceReportsValidationTest.class.getName());

	/* 
	 * Test to verify successful navigation to the Attendance Report tab. 
	 */
	@Test
	public void testNavigationToAttendancReporttab() {
		NavigationTabMethods.navigationToAttendanceReporttab();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement pageTitle = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/h5")));

		AssertJUnit.assertEquals(pageTitle.getText(), "Attendance Total Summary Report");

	}

	/*  
	 *   Test to verify the process of entering employee details, date and displaying the reports. 
	 *   Asserts the presence of a toast message indicating a successful operation.
	 */
	@Test
	public void testToShowUserAttendanceReport() throws InterruptedException {
		NavigationTabMethods.navigationToAttendanceReporttab();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		employeeNameInput.sendKeys("Sam Karan");
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement jobTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div")));
		jobTitle.click();
		WebElement jobTitleOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + "HR Manager" + "')]")));
		jobTitleOption.click();

		WebElement subUnit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div")));
		subUnit.click();
		WebElement subUnitOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + "Development" + "')]")));
		subUnitOption.click();

		WebElement employeeStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div")));
		employeeStatus.click();
		WebElement employeeStatusOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[contains(text(), '" + "Full-Time Permanent" + "')]")));
		employeeStatusOption.click();

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[5]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");

		WebElement datePickerOutput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/input")));

		((JavascriptExecutor) driver).executeScript("arguments[0].value='2024-01-01';", datePickerOutput);

		datePickerOutput.sendKeys(Keys.ENTER);

		WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[2]/button")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
		saveButton.click();

		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	/*
	 *  Test to verify user's actual attendance (Total time hours) is Correctly reflected in Reports or not
	 *  Asserts the equality between the user's actual attendance and the displayed attendance in the report.
	 */
	@Test
	public void testToVerifyUserAttendanceInReports() throws InterruptedException {
		testToShowUserAttendanceReport();
		WebElement userAttendanceReport = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[2]")));
		String userTimeInReport = userAttendanceReport.getText();

		NavigationTabMethods.navigationToUsersRecords();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		employeeNameInput.sendKeys("Sam");
		Thread.sleep(4000);

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
		testToShowUserAttendanceReport();
		WebElement userNameAttendanceReport = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[1]")));
		String userNameInReport = userNameAttendanceReport.getText();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/attendance/viewAttendanceRecord");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		employeeNameInput.sendKeys("Sam Karan");
		Thread.sleep(4000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		String userNameActual = "Sam Karan";

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		saveButton.click();

		Assert.assertEquals(userNameActual, userNameInReport);

	}

}
