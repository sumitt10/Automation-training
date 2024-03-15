package com.testattendancetracking;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.BrowserUtils;
import com.commonutils.TestRetryAnalyzer;
import com.pageobjectpattern.AttendanceRecordsPage;
import com.pageobjectpattern.AttendanceReportPage;
import com.pageobjectpattern.MyAttendancePage;

import lombok.extern.slf4j.Slf4j;

/*
 * Section : OrangeHRM - Time/Reports/Attendance
 * Description :  This class contains test cases related to Users Attendance track.
 * 
 */
@Slf4j
public class AttendanceTrackAutomateTest extends BrowserUtils {
	/*  
	 *   Test to verify the process of entering employee details, date and displaying the reports. 
	 *   Asserts the presence of a toast message indicating a successful operation.
	 */
	@Test
	public void testToShowUserAttendanceReport() throws InterruptedException {
		AttendanceReportPage attendanceReportPage = new AttendanceReportPage(driver);
		attendanceReportPage.addDataToAttendanceReportTab();
		Thread.sleep(3000);
		WebElement toastMessage = attendanceReportPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	/*
	 *  Test to verify user's actual attendance (Total time hours) is Correctly reflected in Reports or not
	 *  Asserts the equality between the user's actual attendance and the displayed attendance in the report.
	 */
	@Test
	public void testToVerifyUserAttendanceInReports() throws InterruptedException {
		AttendanceReportPage attendanceReportPage = new AttendanceReportPage(driver);
		attendanceReportPage.addUsernameAndDate();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Thread.sleep(5000);
		WebElement userAttendanceReport = attendanceReportPage.getUserTimeInReport();
		String userTimeInReport = userAttendanceReport.getText();

		AttendanceRecordsPage attendanceRecordsPage = new AttendanceRecordsPage(driver);
		attendanceRecordsPage.addUserDataToAttendanceRecords();

		Thread.sleep(5000);

		WebElement userActualAttendance = null;
		String userActualTime = null;
		By userAttendanceLocator = attendanceRecordsPage.userAttendanceLocator;
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
		AttendanceReportPage attendanceReportPage = new AttendanceReportPage(driver);
		attendanceReportPage.addUsernameAndDate();

		Thread.sleep(5000);
		WebElement userNameAttendanceReport = attendanceReportPage.getUsername();
		String userNameInReport = userNameAttendanceReport.getText();

		AttendanceRecordsPage attendanceRecordsPage = new AttendanceRecordsPage(driver);
		attendanceRecordsPage.addUserDataToAttendanceRecords();

		String userNameActual = config.getProperty("addUser.username");
		Assert.assertEquals(userNameActual, userNameInReport);

	}

	/*
	 *  Test to verify the process of entering a date, clicking the View button will show the attendance history.
	 *  Asserts that a toast message is displayed, indicating a successful operation.
	 */
	@Test
	public void testToVerifyUserAttendanceHistory() throws InterruptedException {
		MyAttendancePage myattendancePage = new MyAttendancePage(driver);
		myattendancePage.addUserDate();
		Thread.sleep(1500);
		WebElement toastMessage = myattendancePage.gettoastmessage();
		assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);

	}

	/*
	 *  Test to verify the process of entering employee details, setting a date, will shows the user's attendance records.
	 *  Asserts that a toast message is displayed, indicating a successful operation.
	 */
	@Test
	public void testToShowUsersAttendanceHistory() throws InterruptedException {
		AttendanceRecordsPage attendanceRecordsPage = new AttendanceRecordsPage(driver);
		attendanceRecordsPage.addUserDataToAttendanceRecords();
		Thread.sleep(2000);
		WebElement viewMessage = attendanceRecordsPage.getViewMessage();
		assertTrue(viewMessage.isDisplayed());
		Thread.sleep(3000);

	}

	// Test to verify enable the view button with valid date
	@Test
	public void testEnablingViewButtonWithValidDate() throws InterruptedException {
		MyAttendancePage myAttendancePage = new MyAttendancePage(driver);
		myAttendancePage.addUserDate();

		WebElement viewButton = myAttendancePage.getViewButton();
		AssertJUnit.assertTrue(viewButton.isEnabled());

		Thread.sleep(5000);

	}

	// Test to verify invalid date disabled view button 
	@Test(retryAnalyzer = TestRetryAnalyzer.class)
	public void testInvalidDateDisablesViewButton() {
		AttendanceRecordsPage attendanceRecordsPage = new AttendanceRecordsPage(driver);
		attendanceRecordsPage.userDate("200-30-3");

		WebElement viewButton = attendanceRecordsPage.getSaveButton();
		assert !viewButton.isEnabled() : "View button is enabled for an invalid date";

	}

	// Test to verify valid date will displays attendance records
	@Test
	public void testViewWithValidDateDisplaysRecords() {
		String date = config.getProperty("addUser.date");
		AttendanceRecordsPage attendanceRecordsPage = new AttendanceRecordsPage(driver);
		attendanceRecordsPage.userDate(date);

		WebElement viewButton = attendanceRecordsPage.getSaveButton();
		viewButton.click();

		WebElement toastMessage = attendanceRecordsPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

}
