package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	private By emailInputLocator = By.name("email");
	private By passwordInputLocator = By.name("password");
	private By loginButtonLocator = By.xpath("//input[@type='submit']");
	private By forgottenPasswordLinkLocator = By.linkText("Forgotten Password");
	private By dashboardLocator = By.xpath("(//a[@class='list-group-item'])[1]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterEmail(String email) {
		WebElement emailInput = driver.findElement(emailInputLocator);
		emailInput.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordInput = driver.findElement(passwordInputLocator);
		passwordInput.sendKeys(password);
	}

	public void clickLoginButton() {
		WebElement loginButton = driver.findElement(loginButtonLocator);
		loginButton.click();
	}

	public void clickForgottenPasswordLink() {
		WebElement forgottenPasswordLink = driver.findElement(forgottenPasswordLinkLocator);
		forgottenPasswordLink.click();
	}

	public boolean checkForgotPwdLink() {
		return driver.findElement(forgottenPasswordLinkLocator).isDisplayed();
	}

	public boolean checkLogoutLink() {
		return driver.findElement(dashboardLocator).isDisplayed();
	}

	public void login(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickLoginButton();
	}

	public String getForgotPwdPageUrl() {
		return driver.getCurrentUrl();
	}

}
