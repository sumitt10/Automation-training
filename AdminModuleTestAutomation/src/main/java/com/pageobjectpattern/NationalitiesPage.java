package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NationalitiesPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;
	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button");
	private By nationalityNameFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");

	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public NationalitiesPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public WebElement getAddButton() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
	}

	public WebElement getNationalityNameField() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(nationalityNameFieldLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(saveButtonLocator));
	}

	public WebElement gettoastmessage() {

		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addNationality() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/nationality");

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String nationalityName = config.getProperty("addnationality.name");

		WebElement addButton = getAddButton();
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		log.info("Navigated to the Add Nationality page.");

		WebElement nationalityNameField = getNationalityNameField();
		nationalityNameField.sendKeys(nationalityName);

		WebElement saveButton = getSaveButton();
		saveButton.click();
		Thread.sleep(3000);
		log.info("Nationality added successfully.");
	}

}
