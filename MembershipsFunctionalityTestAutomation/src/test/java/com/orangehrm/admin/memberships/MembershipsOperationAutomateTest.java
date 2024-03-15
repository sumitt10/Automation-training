package com.orangehrm.admin.memberships;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.BrowserUtils;
import com.pageobjectpattern.AddMembershipPage;
import com.pageobjectpattern.DeleteMembershipPage;
import com.pageobjectpattern.UpdateMembershipPage;

public class MembershipsOperationAutomateTest extends BrowserUtils {
	/*
	 *  This test case verifies the functionality of adding a new membership. 
	 *  It navigates to the membership page, clicks on the add button, fills in the required data, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void addMembershiptest() throws InterruptedException {
		AddMembershipPage addMembershipPage = new AddMembershipPage(driver);
		addMembershipPage.addMembership();

		WebElement toastMessage = addMembershipPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *  This test case verifies the functionality of editing an existing membership.
	 *  It navigates to the membership page, clicks on the edit button for a membership, edits the data, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void updateMembershiptest() throws InterruptedException {
		UpdateMembershipPage updateMembershipPage = new UpdateMembershipPage(driver);
		updateMembershipPage.updateMembership();
		AddMembershipPage addMembershipPage = new AddMembershipPage(driver);

		WebElement toastMessage = addMembershipPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *  This test case verifies the functionality of deleting an existing membership.  
	 *  It navigates to the membership page, clicks on the delete button for a membership, confirms the deletion, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void deleteMembershiptest() throws InterruptedException {
		DeleteMembershipPage deleteMembershipPage = new DeleteMembershipPage(driver);
		deleteMembershipPage.deleteMembership();
		AddMembershipPage addMembershipPage = new AddMembershipPage(driver);

		WebElement toastMessage = addMembershipPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

}
