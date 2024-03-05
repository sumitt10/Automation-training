package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.TestUtilsMethod;

public class IndividualTabMethods extends TestUtilsMethod {

	public static void addDataToAttendancReportTab() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		String username = config.getProperty("addUser.username");
		employeeNameInput.sendKeys(username);
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		String jobrole = config.getProperty("addUser.jobtitle");
		String subunit = config.getProperty("addUser.subunitname");
		String empstatus = config.getProperty("addUser.employeecontractstatus");

		WebElement jobTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div")));
		jobTitle.click();
		WebElement jobTitleOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + jobrole + "')]")));
		jobTitleOption.click();

		WebElement subUnit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div")));
		subUnit.click();
		WebElement subUnitOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + subunit + "')]")));
		subUnitOption.click();

		WebElement employeeStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div")));
		employeeStatus.click();
		WebElement employeeStatusOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + empstatus + "')]")));
		employeeStatusOption.click();

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[5]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");

		WebElement datePickerOutput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/input")));

		((JavascriptExecutor) driver).executeScript("arguments[0].value='2024-01-01';", datePickerOutput);

		datePickerOutput.sendKeys(Keys.ENTER);

		WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[2]/button")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
		saveButton.click();
	}

	public static void addUsernameAndDate() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement employeeNameInReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		String userInname = config.getProperty("addUser.username");
		employeeNameInReport.sendKeys(userInname);
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/form/div[1]/div/div[5]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerInput.sendKeys("2024-01-01");
		datePickerInput.sendKeys(Keys.ENTER);

	}

	public static void addUserDataToAttendanceRecords() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		String username = config.getProperty("addUser.username");
		employeeNameInput.sendKeys(username);
		Thread.sleep(4000);

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerOutput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerOutput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		datePickerOutput.sendKeys("2024-01-01");
		Thread.sleep(5000);

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		saveButton.click();
	}

	public static void addUserDate() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		String date = config.getProperty("addUser.date");
		datePickerInput.sendKeys(date);

		WebElement viewButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		viewButton.click();
	}

	public static void userDate(String date) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		final WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();
		datePickerInput.sendKeys(date);

	}

}
