package com.orangehrm.organization;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.commonutils.TestUtilsMethod;
import com.pageobjectpattern.NavigationTabMethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddLocationAutomateTest extends TestUtilsMethod {

	@Test
	private static void addLocation() throws InterruptedException {
		NavigationTabMethods.navigateToLocations();

		String name = "Dummy Loca";
		String city = "Dummy city";
		String state = "Dummy State";
		String postalCode = "12345";
		String country = "Australia";
		String phoneNumber = "1234567890";
		String address = "Dummy Address";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//button[@class='oxd-button oxd-button--medium oxd-button--secondary' and @data-v-10d463b7='']")));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		log.info("Navigated to the Add Location page.");

		By nameFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input");
		WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(nameFieldLocator));

		By cityFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
		WebElement cityField = wait.until(ExpectedConditions.presenceOfElementLocated(cityFieldLocator));

		By stateFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
		WebElement stateField = wait.until(ExpectedConditions.presenceOfElementLocated(stateFieldLocator));

		By zipCodeFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[3]/div/div[2]/input");
		WebElement zipCodeField = wait.until(ExpectedConditions.presenceOfElementLocated(zipCodeFieldLocator));

		WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[4]/div/div[2]/div/div")));
		userRoleDropdown.click();
		WebElement userRoleOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + country + "')]")));
		userRoleOption.click();

		By phoneFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[5]/div/div[2]/input");
		WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(phoneFieldLocator));

		By addressFieldLocator = By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[7]/div/div[2]/textarea");
		WebElement addressField = wait.until(ExpectedConditions.presenceOfElementLocated(addressFieldLocator));

		nameField.sendKeys(name);
		cityField.sendKeys(city);
		stateField.sendKeys(state);
		zipCodeField.sendKeys(postalCode);
		phoneField.sendKeys(phoneNumber);
		addressField.sendKeys(address);

		WebElement saveButton = driver
				.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		Thread.sleep(3000);
		By Locator = By.xpath("/html/body/div/div[2]");
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		AssertJUnit.assertTrue(toastMessage.isDisplayed());

		log.info("Location added successfully.");
	}
}
