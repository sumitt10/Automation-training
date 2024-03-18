package com.pageobjectpattern;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Section : OrangeHRM - Add Employee
 * Description : This class contains all the locators on add user page and add the dummy user data 
 * before running the test cases in the Orange HRM website.
 */

public class UserDataFeederPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties config;

	private By firstnameLocator = By.xpath(
			"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input");
	private By lastnameLocator = By.xpath(
			"/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input");
	private By empidLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
	private By addUserButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");

	public UserDataFeederPage(WebDriver driver, Properties config) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.config = config;

	}

	public WebElement getFirstNameField() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(firstnameLocator));
	}

	public WebElement getLastNameField() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(lastnameLocator));
	}

	public WebElement getEmpIdField() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(empidLocator));
	}

	public WebElement getAddUserButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(addUserButtonLocator));
	}

	public void addUser() throws InterruptedException {
		driver.get(config.getProperty("app.adduser_records_url"));
		driver.manage().window().maximize();

		WebElement firstnameField = getFirstNameField();
		WebElement lastnameField = getLastNameField();
		WebElement empidField = getEmpIdField();
		WebElement addUserButton = getAddUserButton();

		String firstname = config.getProperty("addUser.firstname");
		String lastname = config.getProperty("addUser.lastname");
		String empid = config.getProperty("addUser.employeeId");

		firstnameField.sendKeys(firstname);
		lastnameField.sendKeys(lastname);
		empidField.sendKeys(empid);
		addUserButton.click();

		Thread.sleep(5000);

	}
}