package com.pageobjectpattern;

import com.commonutils.TestUtilsMethod;

/*
 * Section : OrangeHRM (Navigation Tabs)
 * Description : This class facilitates for the Navigation to the different tabs on OrangeHRM website.
 * 
 */

public class NavigationTabMethods extends TestUtilsMethod {

	public static void navigationToMembershipPage() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/membership");

	}
}
