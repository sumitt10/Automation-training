package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.TestUtilsMethod;

public class DeleteMembershipPage extends TestUtilsMethod {

	public static void deleteMembership() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(config.getProperty("app.navigate_url"));

		WebElement deleteButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='oxd-icon bi-trash']")));
		deleteButton.click();

		WebElement popUpElement = driver.findElement(By.xpath("//button[normalize-space()='Yes, Delete']"));
		popUpElement.click();

		Thread.sleep(3000);
	}

}
