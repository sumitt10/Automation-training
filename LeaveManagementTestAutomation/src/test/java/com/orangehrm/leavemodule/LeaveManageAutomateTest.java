package com.orangehrm.leavemodule;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.BrowserUtils;
import com.pageobjectpattern.AssignLeavePage;
import com.pageobjectpattern.LeaveListPage;
import com.pageobjectpattern.LeaveReportPage;
import com.pageobjectpattern.MyLeavePage;
import com.pageobjectpattern.MyLeaveReportPage;

public class LeaveManageAutomateTest extends BrowserUtils {
	/*
	 *  Tests the functionality of assigning leave to an employee. 
	 *  It adds data to the leave assignment form, clicks the confirmation popup, waits for a toast message confirming the leave assignment, and asserts that the toast message is displayed.
	 */
	@Test
	private static void assignleavetest() throws InterruptedException {
		AssignLeavePage assignLeavePage = new AssignLeavePage(driver);
		assignLeavePage.addDataToAssignLeave();

		WebElement toastMessage = assignLeavePage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);

	}

	/*
	 * Tests the functionality of viewing the list of leaves. 
	 * It adds data to the leave list, waits for a toast message indicating successful loading of the leave list, and asserts that the toast message is displayed.
	 */
	@Test
	private static void leavelisttest() throws InterruptedException {
		LeaveListPage leaveListPage = new LeaveListPage(driver);
		leaveListPage.addDataToLeavelist();

		AssignLeavePage assignLeavePage = new AssignLeavePage(driver);
		WebElement toastMessage = assignLeavePage.gettoastmessage();

		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);

	}

	/*
	 * Tests the functionality of viewing an employee's own leave records. 
	 * It adds data to the "My Leave" section, waits for the records table to be displayed, and asserts that the records table is visible.
	 */
	@Test
	private static void myLeavetest() throws InterruptedException {
		MyLeavePage myLeavePage = new MyLeavePage(driver);
		myLeavePage.addDataToMyLeave();

		WebElement recordTable = myLeavePage.getrecordTable();
		AssertJUnit.assertTrue(recordTable.isDisplayed());
		Thread.sleep(3000);

	}

	/*
	 * Tests the functionality of generating a leave usage report. 
	 * It adds data to generate the report, waits for the report to be generated, and asserts that the report table is displayed.
	 */
	@Test
	private static void leaveUsageReport() throws InterruptedException {
		LeaveReportPage leaveReportPage = new LeaveReportPage(driver);
		leaveReportPage.addDataToLeaveReport();

		WebElement recordsTable = leaveReportPage.getrecordsTable();
		AssertJUnit.assertTrue(recordsTable.isDisplayed());

	}

	/*
	 * Tests the functionality of generating a leave usage report for the current employee. 
	 * It adds data to generate the report, waits for the report to be generated, and asserts that the report table is displayed.
	 */
	@Test
	private static void myLeaveUsageReport() throws InterruptedException {
		MyLeaveReportPage myLeaveReportPage = new MyLeaveReportPage(driver);
		myLeaveReportPage.addDataToMyLeaveReport();

		LeaveReportPage leaveReportPage = new LeaveReportPage(driver);
		WebElement recordsTable = leaveReportPage.getrecordsTable();

		AssertJUnit.assertTrue(recordsTable.isDisplayed());

	}

}
