package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

/*
 * Section : OrangeHRM - Attendance Records
 * Description : This class contains all the locators on the attendance records page and related methods to it.
 *  
 */

public class AttendanceRecordsPage extends BrowserUtils {
	private WebDriver driver;
	private WebDriverWait wait;

	private By employeeNameInputLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
	private By datePickerOutputLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button");
	public By userAttendanceLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[2]/div/div/div[6]");
	private By viewMessageLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[1]");
	private By toastMessageLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");

	public AttendanceRecordsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getEmployeeNameInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeNameInputLocator));
	}

	public WebElement getDatePickerOutput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerOutputLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public WebElement getViewMessage() {
		return wait.until(ExpectedConditions.elementToBeClickable(viewMessageLocator));
	}

	public WebElement gettoastmessage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addUserDataToAttendanceRecords() throws InterruptedException {
		driver.get(config.getProperty("app.users_records_url"));

		WebElement employeeNameInput = getEmployeeNameInput();
		String username = config.getProperty("addUser.username");
		employeeNameInput.sendKeys(username);
		Thread.sleep(4000);

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerOutput = getDatePickerOutput();
		datePickerOutput.click();
		new Actions(driver).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
				.perform();
		datePickerOutput.sendKeys("2024-01-01");
		Thread.sleep(5000);

		WebElement saveButton = getSaveButton();
		saveButton.click();
	}

	public void userDate(String date) {
		driver.get(config.getProperty("app.users_records_url"));

		WebElement datePickerInput = getEmployeeNameInput();
		datePickerInput.click();
		datePickerInput.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
		datePickerInput.sendKeys(date);

	}
}
