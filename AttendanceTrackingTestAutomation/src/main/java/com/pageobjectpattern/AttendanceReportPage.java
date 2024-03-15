package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

/*
 * Section : OrangeHRM - Attendance Report
 * Description : This class contains all the locators on the attendance report page and related methods to it.
 *  
 */

public class AttendanceReportPage extends BrowserUtils {
	private WebDriver driver;
	private WebDriverWait wait;

	private By employeeNameInputLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
	private By jobTitleLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div");
	private By subUnitLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div");
	private By employeeStatusLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div");
	private By datePickerInputLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[5]/div/div[2]/div/div/input");
	private By datePickerOutputLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[2]/button");
	private By usernameLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[1]");

	private By userAttendanceReportLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/revo-grid/div/div/revogr-viewport-scroll/div/div[2]/div/revogr-overlay-selection/revogr-data/div/div[2]");
	private By toastMessageLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");

	public AttendanceReportPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getEmployeeNameInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeNameInputLocator));
	}

	public WebElement getJobTitle() {
		return wait.until(ExpectedConditions.elementToBeClickable(jobTitleLocator));
	}

	public WebElement getSubUnit() {
		return wait.until(ExpectedConditions.elementToBeClickable(subUnitLocator));
	}

	public WebElement getEmployeeStatus() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeStatusLocator));
	}

	public WebElement getDatePickerInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerInputLocator));
	}

	public WebElement getDatePickerOutput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerOutputLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(saveButtonLocator));
	}

	public WebElement getUsername() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(usernameLocator));
	}

	public WebElement getUserTimeInReport() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(userAttendanceReportLocator));
	}

	public WebElement gettoastmessage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addDataToAttendanceReportTab() throws InterruptedException {
		driver.get(config.getProperty("app.attendance_report_url"));

		WebElement employeeNameInput = getEmployeeNameInput();
		employeeNameInput.sendKeys(config.getProperty("addUser.username"));
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement jobTitle = getJobTitle();
		jobTitle.click();
		WebElement jobTitleOption = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[contains(text(), '" + config.getProperty("addUser.jobtitle") + "')]")));
		jobTitleOption.click();

		WebElement subUnit = getSubUnit();
		subUnit.click();
		WebElement subUnitOption = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[contains(text(), '" + config.getProperty("addUser.subunitname") + "')]")));
		subUnitOption.click();

		WebElement employeeStatus = getEmployeeStatus();
		employeeStatus.click();
		WebElement employeeStatusOption = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[contains(text(), '" + config.getProperty("addUser.employeecontractstatus") + "')]")));
		employeeStatusOption.click();

		WebElement datePickerInput = getDatePickerInput();
		datePickerInput.click();
		datePickerInput.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
		datePickerInput.sendKeys("2024-01-01");

		WebElement datePickerOutput = getDatePickerOutput();
		((JavascriptExecutor) driver).executeScript("arguments[0].value='2024-01-01';", datePickerOutput);
		datePickerOutput.sendKeys(Keys.ENTER);

		WebElement saveButton = getSaveButton();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
		saveButton.click();
	}

	public void addUsernameAndDate() throws InterruptedException {
		driver.get(config.getProperty("app.attendance_report_url"));

		WebElement employeeNameInReport = getEmployeeNameInput();
		employeeNameInReport.sendKeys(config.getProperty("addUser.username"));
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerInput = getDatePickerInput();
		datePickerInput.click();
		datePickerInput.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
		datePickerInput.sendKeys("2024-01-01");
		datePickerInput.sendKeys(Keys.ENTER);
	}
}
