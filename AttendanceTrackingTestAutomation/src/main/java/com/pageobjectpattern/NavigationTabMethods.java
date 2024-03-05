package com.pageobjectpattern;

import com.commonutils.TestUtilsMethod;

public class NavigationTabMethods extends TestUtilsMethod {

	public static void navigationToAttendanceReporttab() {
		driver.get(config.getProperty("app.attendance_report_url"));

	}

	public static void navigationToMyRecords() {
		driver.get(config.getProperty("app.my_records_url"));

	}

	public static void navigationToUsersRecords() {
		driver.get(config.getProperty("app.users_records_url"));

	}
}
