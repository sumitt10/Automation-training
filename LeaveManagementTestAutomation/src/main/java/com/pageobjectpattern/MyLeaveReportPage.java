package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class MyLeaveReportPage extends BrowserUtils {

	private WebDriver driver;

	private By leavePeriodLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div");
	private By leavePeriodOptionLocator = By.xpath(
			"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/div[1]");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button");

	private WebDriverWait wait;

	public MyLeaveReportPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

	public void addDataToMyLeaveReport() throws InterruptedException {
		driver.get(config.getProperty("app.myleavereportPage_url"));

		WebElement leavePeriod = getLeavePeriod();
		leavePeriod.click();

		WebElement leavePeriodOption = getLeavePeriodOption();
		leavePeriodOption.click();

		WebElement saveButton = getSaveButton();
		saveButton.click();

		Thread.sleep(5000);
	}
}
