package com.orangehrm.usermanagement;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

/*
 * Section : OrangeHRM - Add User
 * Description : This class will add the user details for test cases in Orange HRM website.
 * 
 */

@Slf4j
public class AddUser {

	private WebDriver driver;
	private Properties config;

	public AddUser(WebDriver driver, Properties config) {
		this.driver = driver;
		this.config = config;

	}

	public void addUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get(config.getProperty("app.adduser_records_url"));

		driver.manage().window().maximize();

		String firstname = config.getProperty("addUser.firstname");
		String lastname = config.getProperty("addUser.lastname");
		String empid = config.getProperty("addUser.employeeId");

		WebElement firstnameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input")));
		WebElement lastnameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input")));
		WebElement empidField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")));
		new Actions(driver).click(empidField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		WebElement loginButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));

		firstnameField.sendKeys(firstname);
		lastnameField.sendKeys(lastname);
		empidField.sendKeys(empid);
		loginButton.click();
		log.info("Successfully added user details in OrangeHRM webpage!!");

		Thread.sleep(5000);

	}
}
