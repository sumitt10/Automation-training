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

public class LeaveReportPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	private By radioButtonLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div[2]/div[2]/div/label");
	private By employeeNameLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input");
	private By leavePeriodLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/div/div");
	private By leavePeriodOptionLocator = By.xpath(
			"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/div/div/div[3]");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/button");
	private By recordsTableLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");

	public LeaveReportPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getRadioButton() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(radioButtonLocator));
	}

	public WebElement getEmployeeNameField() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeNameLocator));
	}

	public WebElement getLeavePeriod() {
		return wait.until(ExpectedConditions.elementToBeClickable(leavePeriodLocator));
	}

	public WebElement getLeavePeriodOption() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(leavePeriodOptionLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public WebElement getrecordsTable() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(recordsTableLocator));
	}

	public void addDataToLeaveReport() throws InterruptedException {
		driver.get(config.getProperty("app.leavereportPage_url"));

		WebElement radioButton = getRadioButton();
		radioButton.click();

		WebElement employeeName = getEmployeeNameField();
		employeeName.sendKeys(config.getProperty("addUser.ename"));
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement leavePeriod = getLeavePeriod();
		leavePeriod.click();
		WebElement leavePeriodOption = getLeavePeriodOption();
		leavePeriodOption.click();

		WebElement saveButton = getSaveButton();
		saveButton.click();
		Thread.sleep(5000);
	}
}
