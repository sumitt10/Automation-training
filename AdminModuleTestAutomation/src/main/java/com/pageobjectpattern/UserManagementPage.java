package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class UserManagementPage extends BrowserUtils {

	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
	private By userRoleDropdownLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
	private By employeeNameFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private By statusDropdownLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");
	private By usernameFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
	private By passwordFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
	private By confirmPasswordFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");

	private By toastMessageLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div");

	private WebDriver driver;
	private WebDriverWait wait;

	public UserManagementPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public WebElement getAddButton() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
	}

	public WebElement getUserRoleDropdown() {
		return wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdownLocator));
	}

	public WebElement getEmployeeNameField() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeNameFieldLocator));
	}

	public WebElement getStatusDropdown() {
		return wait.until(ExpectedConditions.elementToBeClickable(statusDropdownLocator));
	}

	public WebElement getUsernameField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFieldLocator));
	}

	public WebElement getPasswordField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator));
	}

	public WebElement getConfirmPasswordField() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordFieldLocator));
	}

	public WebElement getSaveButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
	}

	public WebElement gettoastmessage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
	}

	public void addDetails() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");

		String userRole = config.getProperty("addUser.userRole");
		String partialEmployeeName = config.getProperty("addUser.partialEmployeeName");
		String status = config.getProperty("addUser.status");
		String username = config.getProperty("addUser.username");
		String password = config.getProperty("addUser.password");
		String confirmPassword = config.getProperty("addUser.confirmPassword");

		WebElement addButton = getAddButton();
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		WebElement userRoleDropdown = getUserRoleDropdown();
		userRoleDropdown.click();
		WebElement userRoleOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + userRole + "')]")));
		userRoleOption.click();

		WebElement employeeNameField = getEmployeeNameField();
		employeeNameField.sendKeys(partialEmployeeName);
		Thread.sleep(4000);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		WebElement statusDropdown = getStatusDropdown();
		statusDropdown.click();
		WebElement statusOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + status + "')]")));
		statusOption.click();

		WebElement usernameField = getUsernameField();
		usernameField.sendKeys(username);

		WebElement passwordField = getPasswordField();
		passwordField.sendKeys(password);

		WebElement confirmPasswordField = getConfirmPasswordField();
		confirmPasswordField.sendKeys(confirmPassword);

		WebElement saveButton = getSaveButton();
		saveButton.click();

		Thread.sleep(4000);
	}
}
