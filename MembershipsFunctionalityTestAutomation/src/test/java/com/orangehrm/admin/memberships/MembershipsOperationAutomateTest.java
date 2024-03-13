package com.orangehrm.admin.memberships;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.AddMembershipPage;
import com.pageobjectpattern.DeleteMembershipPage;
import com.pageobjectpattern.UpdateMembershipPage;

public class MembershipsOperationAutomateTest extends TestUtilsMethod {
	/*
	 *  This test case verifies the functionality of adding a new membership. 
	 *  It navigates to the membership page, clicks on the add button, fills in the required data, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void addMembershiptest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		AddMembershipPage.addDataToMembershipPage();

		WebElement toastMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='oxd-toast-container oxd-toast-container--bottom']")));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *  This test case verifies the functionality of editing an existing membership.
	 *  It navigates to the membership page, clicks on the edit button for a membership, edits the data, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void updateMembershiptest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		UpdateMembershipPage.editMembershipDataPage();

		WebElement toastMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='oxd-toast-container oxd-toast-container--bottom']")));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

	/*
	 *  This test case verifies the functionality of deleting an existing membership.  
	 *  It navigates to the membership page, clicks on the delete button for a membership, confirms the deletion, and then asserts that a success message (toast) is displayed.
	 */
	@Test
	private static void deleteMembershiptest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		DeleteMembershipPage.deleteMembership();

		WebElement toastMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='oxd-toast-container oxd-toast-container--bottom']")));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());
		Thread.sleep(3000);
	}

}
