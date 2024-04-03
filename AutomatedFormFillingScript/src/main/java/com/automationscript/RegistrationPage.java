package com.automationscript;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RegistrationPage {

	private WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void registration(String firstName, String lastName, String address, String city, String state,
			String zipCode, String phoneNo, String ssn, String username, String password, String confirmPassword) {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.perform();
		driver.get("https://parabank.parasoft.com/parabank/register.htm");

		WebElement firstNameField = driver.findElement(By.id("customer.firstName"));
		WebElement lastNameField = driver.findElement(By.id("customer.lastName"));
		WebElement addressField = driver.findElement(By.id("customer.address.street"));
		WebElement cityField = driver.findElement(By.id("customer.address.city"));
		WebElement stateField = driver.findElement(By.id("customer.address.state"));
		WebElement zipCodeField = driver.findElement(By.id("customer.address.zipCode"));
		WebElement phoneNoField = driver.findElement(By.id("customer.phoneNumber"));
		WebElement ssnField = driver.findElement(By.id("customer.ssn"));
		WebElement usernameField = driver.findElement(By.id("customer.username"));
		WebElement passwordField = driver.findElement(By.id("customer.password"));
		WebElement confirmPasswordField = driver.findElement(By.id("repeatedPassword"));
		WebElement submitButton = driver.findElement(By.xpath("//input[@value='Register']"));

		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		addressField.sendKeys(address);
		cityField.sendKeys(city);
		stateField.sendKeys(state);
		zipCodeField.sendKeys(zipCode);
		phoneNoField.sendKeys(phoneNo);
		ssnField.sendKeys(ssn);
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);

		submitButton.click();
	}
}
