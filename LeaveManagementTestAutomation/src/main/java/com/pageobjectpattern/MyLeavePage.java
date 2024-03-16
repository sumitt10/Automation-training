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

public class MyLeavePage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	private By datePickerInputLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
	private By datePickerOutputLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private By subUnitLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]");
	private By subUnitOptionLocator = By.xpath(
			"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[4]");
	private By saveButtonLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
	private By recordTableLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div");

	public MyLeavePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getDatePickerInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerInputLocator));
	}

	public WebElement getDatePickerOutput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerOutputLocator));
	}

	public WebElement getSubUnit() {
		return wait.until(ExpectedConditions.elementToBeClickable(subUnitLocator));
	}

	public WebElement getSubUnitOption() {
		return wait.until(ExpectedConditions.elementToBeClickable(subUnitOptionLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public WebElement getrecordTable() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(recordTableLocator));
	}

	public void addDataToMyLeave() throws InterruptedException {
		driver.get(config.getProperty("app.myleavePage_url"));

		WebElement datePickerInput = getDatePickerInput();
		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();
		datePickerInput.sendKeys(config.getProperty("addUser.assigndate"));

		WebElement datePickerOutput = getDatePickerOutput();
		((JavascriptExecutor) driver).executeScript("arguments[0].value='2024-02-04';", datePickerOutput);

		WebElement subUnit = getSubUnit();
		subUnit.click();
		WebElement subUnitOption = getSubUnitOption();
		subUnitOption.click();

		WebElement saveButton = getSaveButton();
		saveButton.click();
		Thread.sleep(3000);
	}
}
