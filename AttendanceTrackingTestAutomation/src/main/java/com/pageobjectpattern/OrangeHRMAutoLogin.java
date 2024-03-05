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
 * Description : This class facilitates the automated login process for the OrangeHRM website.
 * 
 */

@Slf4j
public class OrangeHRMAutoLogin {

	private WebDriver driver;
	private Properties config;

	public OrangeHRMAutoLogin(WebDriver driver, Properties config) {
		this.driver = driver;
		this.config = config;

	}

	public void login() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get(config.getProperty("app.url"));

		driver.manage().window().maximize();

		String username = config.getProperty("login.username");
		String password = config.getProperty("login.password");

		WebElement usernameField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input')][@name='username']")));
		WebElement passwordField = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input')][@name='password']")));
		WebElement loginButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@class, 'orangehrm-login-button')]")));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		log.info("Successfully logged in OrangeHRM webpage!!");
		Thread.sleep(2000);
	}
}
