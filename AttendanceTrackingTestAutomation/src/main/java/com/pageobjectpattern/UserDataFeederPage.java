package com.pageobjectpattern;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Section : OrangeHRM - Add User
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
	private By employeeNameInputLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
	private By datePickerInputLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button");
	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[1]/button");
	private By punchInButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/form/div[4]/button");
	private By addnewButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[1]/button");
	private By punchOutButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/form/div[4]/button");

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

	public WebElement getEmployeeNameInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeNameInputLocator));
	}

	public WebElement getDatePickerInput() {
		return wait.until(ExpectedConditions.elementToBeClickable(datePickerInputLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public WebElement getAddButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator));
	}

	public WebElement getPunchInButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(punchInButtonLocator));
	}

	public WebElement getAddNewButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(addnewButtonLocator));
	}

	public WebElement getPunchOutButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(punchOutButtonLocator));
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

		driver.get(config.getProperty("app.addattendance_records_url"));

		WebElement employeeNameInput = getEmployeeNameInput();
		String username = config.getProperty("addUser.username");
		employeeNameInput.sendKeys(username);
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerInput = getDatePickerInput();
		datePickerInput.click();
		new Actions(driver).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
				.perform();
		datePickerInput.sendKeys(config.getProperty("addUser.date"));

		WebElement saveButton = getSaveButton();
		saveButton.click();

		Thread.sleep(4000);

		WebElement addButton = getAddButton();
		addButton.click();

		Thread.sleep(5000);

		WebElement punchInButton = getPunchInButton();
		punchInButton.click();

		Thread.sleep(5000);

		WebElement addnewButton = getAddNewButton();
		addnewButton.click();
		Thread.sleep(5000);

		WebElement punchOutButton = getPunchOutButton();
		punchOutButton.click();

		Thread.sleep(5000);
	}
}