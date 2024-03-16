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

public class AssignLeavePage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	private By employeeNameLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div/div/input");
	private By leaveTypeLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div");
	private By startDateLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/div/div/input");
	private By endDateLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div/input");
	private By noteFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div[2]/textarea");

	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public AssignLeavePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getEmployeeNameField() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeNameLocator));
	}

	public WebElement getLeaveTypeField() {
		return wait.until(ExpectedConditions.elementToBeClickable(leaveTypeLocator));
	}

	public WebElement getStartDateField() {
		return wait.until(ExpectedConditions.elementToBeClickable(startDateLocator));
	}

	public WebElement getEndDateField() {
		return wait.until(ExpectedConditions.elementToBeClickable(endDateLocator));
	}

	public WebElement getNoteField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(noteFieldLocator));
	}

	public WebElement gettoastmessage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addDataToAssignLeave() throws InterruptedException {
		driver.get(config.getProperty("app.assignLeave_url"));

		getEmployeeNameField().sendKeys(config.getProperty("addUser.ename"));
		Thread.sleep(2000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		getLeaveTypeField().click();
		Thread.sleep(2000);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement startDate = getStartDateField();
		startDate.clear();
		startDate.sendKeys(config.getProperty("addUser.assigndate"));

		WebElement endDate = getEndDateField();
		((JavascriptExecutor) driver).executeScript("arguments[0].value='2024-02-04';", endDate);

		getNoteField().sendKeys(config.getProperty("addUser.noteField"));

		endDate.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		WebElement popUpElement = driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]"));
		popUpElement.click();
		Thread.sleep(2000);
	}
}
