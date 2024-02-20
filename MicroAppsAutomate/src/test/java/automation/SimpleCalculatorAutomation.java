package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleCalculatorAutomation {

	public static void main(String[] args) {
		// Set up the WebDriver for Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Navigate to the calculator web page
		driver.get("https://testpages.eviltester.com/styled/calculator");

		// Locate input elements and dropdown
		WebElement number1Input = driver.findElement(By.name("number1"));
		WebElement number2Input = driver.findElement(By.name("number2"));
		WebElement operationDropdown = driver.findElement(By.name("function"));
		Select operationSelect = new Select(operationDropdown);
		WebElement calculateButton = driver.findElement(By.xpath("/html/body/div/div[3]/form/div[2]/input"));

		// Perform addition, subtraction, multiplication, and division
		performCalculation(driver, number1Input, number2Input, operationSelect, calculateButton, "plus");
		performCalculation(driver, number1Input, number2Input, operationSelect, calculateButton, "minus");
		performCalculation(driver, number1Input, number2Input, operationSelect, calculateButton, "times");
		performCalculation(driver, number1Input, number2Input, operationSelect, calculateButton, "divide");

		try {
			// Add delay for visibility (in milliseconds)
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Quit the WebDriver
			driver.quit();
		}
	}

	/**
	 * @param driver           WebDriver instance
	 * @param number1Input     WebElement for the first number input
	 * @param number2Input     WebElement for the second number input
	 * @param operationSelect  Select for the operation dropdown
	 * @param calculateButton  WebElement for the calculate button
	 * @param operation        Operation to be performed (plus, minus, times, divide)
	 */
	private static void performCalculation(WebDriver driver, WebElement number1Input, WebElement number2Input,
			Select operationSelect, WebElement calculateButton, String operation) {

		number1Input.clear();
		number1Input.sendKeys("10");
		number2Input.clear();
		number2Input.sendKeys("5");

		operationSelect.selectByVisibleText("plus");

		calculateButton.click();

		log.info("Calculation performed for " + operation);
	}
}
