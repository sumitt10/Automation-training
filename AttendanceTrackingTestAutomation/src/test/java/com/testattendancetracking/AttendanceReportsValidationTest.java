package com.testattendancetracking;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.IndividualTabMethods;
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
		IndividualTabMethods.addDataToAttendancReportTab();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		NavigationTabMethods.navigationToAttendanceReporttab();
		IndividualTabMethods.addUsernameAndDate();

		Thread.sleep(5000);
		WebElement userAttendanceReport = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[2]")));
		String userTimeInReport = userAttendanceReport.getText();

		NavigationTabMethods.navigationToUsersRecords();
		IndividualTabMethods.addUserDataToAttendanceRecords();

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
		NavigationTabMethods.navigationToAttendanceReporttab();
		IndividualTabMethods.addUsernameAndDate();

		Thread.sleep(5000);
		WebElement userNameAttendanceReport = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[1]")));
		String userNameInReport = userNameAttendanceReport.getText();

		NavigationTabMethods.navigationToUsersRecords();
		IndividualTabMethods.addUserDataToAttendanceRecords();

		String userNameActual = config.getProperty("addUser.username");
		Assert.assertEquals(userNameActual, userNameInReport);

	}

}
