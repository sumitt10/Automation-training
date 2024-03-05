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

import lombok.extern.slf4j.Slf4j;

/*
 * Section : OrangeHRM - Add User
 * Description : This class will add the user details for test cases in Orange HRM website.
 * 
 */

@Slf4j
public class AddUserDetails {

	private WebDriver driver;
	private Properties config;

	public AddUserDetails(WebDriver driver, Properties config) {
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

		driver.get(config.getProperty("app.addattendance_records_url"));

		WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")));
		String username = config.getProperty("addUser.username");
		employeeNameInput.sendKeys(username);

		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement datePickerInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")));

		new Actions(driver).click(datePickerInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).build().perform();

		String date = config.getProperty("addUser.date");
		datePickerInput.sendKeys(date);

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/button")));
		saveButton.click();

		Thread.sleep(4000);

		WebElement addButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[1]/button")));
		addButton.click();

		Thread.sleep(5000);

		WebElement punchInButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/form/div[4]/button")));
		punchInButton.click();

		Thread.sleep(5000);

		WebElement addnewButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[2]/div[1]/button")));
		addnewButton.click();
		Thread.sleep(5000);

		WebElement punchOutButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/form/div[4]/button")));
		punchOutButton.click();

		Thread.sleep(5000);

	}
}
