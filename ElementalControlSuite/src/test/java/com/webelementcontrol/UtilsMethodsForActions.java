package com.webelementcontrol;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class containing methods to perform various actions on web elements.
 */
@Slf4j
public class UtilsMethodsForActions {

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Constructor to initialize the WebDriver, maximize the window, and set up WebDriverWait.
	 */
	public UtilsMethodsForActions() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public static void main(String[] args) {
		UtilsMethodsForActions utils = new UtilsMethodsForActions();

		// Open the Webpage for automate
		utils.openURL("https://testpages.eviltester.com/styled/basic-html-form-test.html");

		// Enter text in the username field
		utils.enterText(By.name("username"), "John");
		log.info("Entered the text in specied field.");

		// Check if the submit button is present
		boolean isSubmitButtonPresent = utils.isElementPresent(By.name("submitbutton"));
		log.info("Is Submit Button Present: {}", isSubmitButtonPresent);

		// Select a radio button
		utils.selectRadioButton(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[6]/td/input[3]"));
		log.info("Radio button selected successfully.");

		// Perform a mouse hover action
		utils.mouseHover(By.xpath("/html/body/div/div[1]/div[1]/a"));
		log.info("Mouse hover action performed successfully.");

		// Select an item from a dropdown
		utils.selectDropdownByVisibleText(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[8]/td/select"),
				"Drop Down Item 6");
		log.info("Dropdown item selected successfully.");

		// Click on a visible element using partial link text
		utils.clickOnVisibleElement(By.partialLinkText("Index"));
		log.info("Clicked on the element with partial link text.");

		utils.quit();
	}

	/**
	 * Opens the specified URL in the browser.
	 *
	 * @param url -URL to open webpag in browser.
	 */
	public void openURL(String url) {
		driver.get(url);
	}

	/**
	 * Clicks on an element only after it is visible.
	 *
	 * @param locator The locator of the element to click.
	 */
	public void clickOnVisibleElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
	}

	/**
	 * Enters text into the specified input field.
	 *
	 * @param locator The locator of the input field.
	 * @param text    The text to enter.
	 */
	public void enterText(By locator, String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(text);
	}

	/**
	 * Checks whether the specified element is present on the page.
	 *
	 * @param - locator The locator of the element to check.
	 * @return True if the element is present, false otherwise.
	 */
	public boolean isElementPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}

	/**
	 * Selects an item from a dropdown based on visible text.
	 *
	 * @param locator      - The locator of the dropdown.
	 * @param visibleText  - The visible text of the item to select.
	 */
	public void selectDropdownByVisibleText(By locator, String visibleText) {
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * Selects a radio button.
	 *
	 * @param locator - The locator of the radio button.
	 */
	public void selectRadioButton(By locator) {
		WebElement radioButton = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		radioButton.click();
	}

	/**
	 * Mouse hover on the required element.
	 *
	 * @param locator - The locator of the element to hover over.
	 */
	public void mouseHover(By locator) {
		WebElement elementToHover = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementToHover).perform();
	}

	public void quit() {
		driver.quit();
	}
}
