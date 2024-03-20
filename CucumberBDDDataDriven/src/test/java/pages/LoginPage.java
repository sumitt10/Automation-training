package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	By usernameLocator = By.xpath("//input[@placeholder='Username']");
	By passwordLocator = By.xpath("//input[@placeholder='Password']");
	By buttonLocator = By.xpath("//button[@type='submit']");
	By homepageheaderLocator = By.xpath("//header[@class='oxd-topbar']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		driver.findElement(usernameLocator).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordLocator).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(buttonLocator).click();
	}

	public void homePageHeaderDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(homepageheaderLocator));
		@SuppressWarnings("unused")
		boolean isDisplayed = element.isDisplayed();
	}

}
