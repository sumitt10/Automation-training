package com.pageobjectpattern;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

/*
 * Section : OrangeHRM - Login
 * Description : This class includes all locators on the login page and facilitates automated login in orangeHRM Website.
 * 
 */

@Slf4j
public class OrangeHRMLoginPage {

	private WebDriver driver;
	private Properties config;

	private By usernameLocator = By.xpath("//input[contains(@class, 'oxd-input')][@name='username']");
	private By passwordLocator = By.xpath("//input[contains(@class, 'oxd-input')][@name='password']");
	private By loginButtonLocator = By.xpath("//button[contains(@class, 'orangehrm-login-button')]");

	public OrangeHRMLoginPage(WebDriver driver, Properties config) {
		this.driver = driver;
		this.config = config;
	}

	public WebElement getUsernameField() {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(usernameLocator));
	}

	public WebElement getPasswordField() {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(passwordLocator));
	}

	public WebElement getLoginButton() {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
	}

	public void login() throws InterruptedException {
		driver.get(config.getProperty("app.url"));
		driver.manage().window().maximize();

		String username = config.getProperty("login.username");
		String password = config.getProperty("login.password");

		WebElement usernameField = getUsernameField();
		WebElement passwordField = getPasswordField();
		WebElement loginButton = getLoginButton();

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

		log.info("Successfully logged in OrangeHRM webpage!!");
		Thread.sleep(2000);
	}
}