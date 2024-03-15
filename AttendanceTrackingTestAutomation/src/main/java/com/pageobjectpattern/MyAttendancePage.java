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
 * Section : OrangeHRM - My Attendance Records
 * Description : This class contains all the locators on the my attendance records page and related methods to it.
 *  
 */

public class MyAttendancePage extends BrowserUtils {
	private WebDriver driver;
	private WebDriverWait wait;

	private By datePickerInputLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/input");

	private By viewButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button");

	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public MyAttendancePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getDatePickerInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerInputLocator));
	}

	public WebElement getViewButton() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(viewButtonLocator));
	}

	public WebElement gettoastmessage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addUserDate() {
		driver.get(config.getProperty("app.my_records_url"));

		String date = config.getProperty("addUser.date");
		WebElement datePickerInput = getDatePickerInput();
		datePickerInput.click();
		new Actions(driver).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
				.perform();
		datePickerInput.sendKeys(date);

		WebElement viewButton = getViewButton();
		viewButton.click();
	}
}
