package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class ConfigurationPage extends BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	public ConfigurationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void configureEmailSettings() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/listMailConfiguration");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String mailSentAs = config.getProperty("addconfiguration.mail");
		boolean sendTestMail = true;
		String testEmailAddress = config.getProperty("addconfiguration.testemail");

		WebElement mailSentAsField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input")));
		mailSentAsField.sendKeys(Keys.CONTROL + "a");
		mailSentAsField.sendKeys(Keys.DELETE);
		mailSentAsField.sendKeys(mailSentAs);

		WebElement sendTestMailToggle = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div/label/span")));
		if (sendTestMailToggle.isSelected() != sendTestMail) {
			sendTestMailToggle.click();
		}

		if (sendTestMail) {
			WebElement testEmailAddressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div[2]/input")));
			testEmailAddressField.sendKeys(testEmailAddress);
		}
		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]")));
		saveButton.click();

		Thread.sleep(2000);

	}

	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public WebElement gettoastmessage() {

		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}
}
