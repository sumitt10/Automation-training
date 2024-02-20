package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputValidationAutomation {

	public static void main(String[] args) {
		// Set the path for the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// Navigate to the input validation web page
		driver.get("https://testpages.eviltester.com/styled/validation/input-validation.html");

		// Locate web elements by its name 
		WebElement firstNameInput = driver.findElement(By.name("firstname"));
		WebElement lastNameInput = driver.findElement(By.name("surname"));
		WebElement ageInput = driver.findElement(By.name("age"));

		firstNameInput.sendKeys("Sumit");
		lastNameInput.sendKeys("SudhakarThigale");
		ageInput.sendKeys("22");

		// Locate the country dropdown by its name
		WebElement countryDropdown = driver.findElement(By.name("country"));
		Select countrySelect = new Select(countryDropdown);
		countrySelect.selectByVisibleText("India");

		WebElement noteInput = driver.findElement(By.name("notes"));
		noteInput.sendKeys("This is an input validation automation.");

		// Locate the submit button by XPath 
		WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
		submitButton.click();

		log.info("Input validation response submitted successfully!!");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.quit();
	}
}
