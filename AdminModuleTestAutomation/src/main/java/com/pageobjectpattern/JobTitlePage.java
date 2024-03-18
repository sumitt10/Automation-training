package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class JobTitlePage extends BrowserUtils {

	public void addJobTitle() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JobTitlePage jobTitlePage = new JobTitlePage(wait);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList");

		String jobTitle = config.getProperty("addjob.jobtitle");
		String jobDescription = config.getProperty("addjob.jobdesc");

		jobTitlePage.clickAddButton();
		jobTitlePage.enterJobTitle(jobTitle);
		jobTitlePage.enterJobDescription(jobDescription);
		jobTitlePage.clickSaveButton();

		Thread.sleep(3000);
	}

	private WebDriverWait wait;

	private By addButtonLocator = By.cssSelector("button.oxd-button--medium");
	private By jobTitleFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	private By jobDescriptionFieldLocator = By.cssSelector("textarea.oxd-textarea--active");
	private By saveButtonLocator = By
			.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public JobTitlePage(WebDriverWait wait) {
		this.wait = wait;
	}

	public void clickAddButton() {
		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
	}

	public void enterJobTitle(String jobTitle) {
		WebElement jobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleFieldLocator));
		jobTitleField.sendKeys(jobTitle);
	}

	public void enterJobDescription(String jobDescription) {
		WebElement jobDescriptionField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(jobDescriptionFieldLocator));
		jobDescriptionField.sendKeys(jobDescription);
	}

	public void clickSaveButton() {
		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
		saveButton.click();
	}

	public boolean isToastMessageDisplayed() {
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
		return toastMessage.isDisplayed();
	}
}
