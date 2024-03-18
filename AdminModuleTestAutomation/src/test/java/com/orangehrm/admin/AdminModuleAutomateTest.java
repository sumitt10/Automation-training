package com.orangehrm.admin;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.BrowserUtils;
import com.pageobjectpattern.ConfigurationPage;
import com.pageobjectpattern.EducationPage;
import com.pageobjectpattern.EmploymentStatusPage;
import com.pageobjectpattern.JobCategoryPage;
import com.pageobjectpattern.JobTitlePage;
import com.pageobjectpattern.LanguagePage;
import com.pageobjectpattern.LicensePage;
import com.pageobjectpattern.NationalitiesPage;
import com.pageobjectpattern.OraganizationPage;
import com.pageobjectpattern.PayGradePage;
import com.pageobjectpattern.ProviderPage;
import com.pageobjectpattern.SkillPage;
import com.pageobjectpattern.UserManagementPage;

public class AdminModuleAutomateTest extends BrowserUtils {
	@Test
	public void testToAddUser() throws InterruptedException {
		UserManagementPage userManagementPage = new UserManagementPage(driver);
		userManagementPage.addDetails();
		WebElement toastMessage = userManagementPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	@Test
	public void testToAddLocation() throws InterruptedException {
		OraganizationPage oraganizationPage = new OraganizationPage(driver);
		oraganizationPage.addLocation();
		UserManagementPage userManagementPage = new UserManagementPage(driver);
		WebElement toastMessage = userManagementPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	@Test
	public void testToAddNationalities() throws InterruptedException {
		NationalitiesPage nationalitiesPage = new NationalitiesPage(driver);
		nationalitiesPage.addNationality();
		WebElement toastMessage = nationalitiesPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	@Test
	public void testToAddProvider() throws InterruptedException {
		ProviderPage providerPage = new ProviderPage(driver);
		providerPage.addProvider();
		WebElement toastMessage = providerPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	@Test
	public void testToAddEmailConfiguration() throws InterruptedException {
		ConfigurationPage configurationPage = new ConfigurationPage(driver);
		configurationPage.configureEmailSettings();
		WebElement toastMessage = configurationPage.gettoastmessage();
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

	}

	@Test
	public void testToAddPayGrade() throws InterruptedException {
		PayGradePage payGradePage = new PayGradePage();
		payGradePage.addPayGrade();
		AssertJUnit.assertTrue(payGradePage.isToastMessageDisplayed());

	}

	@Test
	public void testToAddJobTitle() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JobTitlePage jobTitlePage = new JobTitlePage(wait);
		jobTitlePage.addJobTitle();
		AssertJUnit.assertTrue(jobTitlePage.isToastMessageDisplayed());

	}

	@Test
	public void testToEmploymentStatus() throws InterruptedException {
		EmploymentStatusPage employmentStatusPage = new EmploymentStatusPage();
		employmentStatusPage.addEmploymentStatus();
		AssertJUnit.assertTrue(employmentStatusPage.isToastMessageDisplayed());

	}

	@Test
	public void testToAddJobCategory() throws InterruptedException {
		JobCategoryPage jobCategoryPage = new JobCategoryPage();
		jobCategoryPage.addJobCategory();
		AssertJUnit.assertTrue(jobCategoryPage.isToastMessageDisplayed());

	}

	@Test
	public void testToAddEducation() throws InterruptedException {
		EducationPage educationPage = new EducationPage();
		educationPage.addEducation();
		AssertJUnit.assertTrue(educationPage.isToastMessageDisplayed());

	}

	@Test
	public void testToAddSkill() throws InterruptedException {
		SkillPage skillPage = new SkillPage();
		skillPage.addSkill();
		AssertJUnit.assertTrue(skillPage.isToastMessageDisplayed());

	}

	@Test
	public void testToAddLicense() throws InterruptedException {
		LicensePage licensePage = new LicensePage();
		licensePage.addLicense();
		AssertJUnit.assertTrue(licensePage.isToastMessageDisplayed());

	}

	@Test
	public void testToAddLanguage() throws InterruptedException {
		LanguagePage languagePage = new LanguagePage();
		languagePage.addLanguage();
		AssertJUnit.assertTrue(languagePage.isToastMessageDisplayed());

	}

	@Test
	public void testLanguagenameAssertion() throws InterruptedException {
		LanguagePage languagePage = new LanguagePage();
		languagePage.getDataAndCompare();
		List<String> actualLanguageNames = languagePage.getActualLanguageNames();
		String expectedValue = languagePage.getExpectedvalue();
		Assert.assertEquals(actualLanguageNames, expectedValue, "Actual Language is not equal to expected language");

	}

}