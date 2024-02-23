package com.datadriventest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataDrivenTest {

	private static WebDriver driver;

	@Test
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		try {
			// Get test data from Excel file
			Object[][] testData = provideTestData();

			// Iterate through the test data
			for (Object[] data : testData) {
				String username = (String) data[0];
				String password = (String) data[1];
				String expectedMessage = (String) data[2];

				loginTest(username, password, expectedMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		tearDown();
	}

	/**
	 * Perform login test with given credentials and expected message.
	 *
	 * @param username          -username for login.
	 * @param password          -Password for login.
	 * @param expectedMessage   -Expected message after login attempt.
	 */

	public static void loginTest(String username, String password, String expectedMessage) {
		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize();

		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("submit"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			// Check for error message element on the webpage
			WebElement actualMessageElement = driver.findElement(By.id("error"));
			String actualMessage = actualMessageElement.getText();
			log.info("Actual Message : " + actualMessage);
			log.info("Expected Message: " + expectedMessage);
		} catch (NoSuchElementException e) {
			/// If no error message element found, then show successful login message
			log.info("Login successful !! Please use Credentials as : " + username + "," + password);
		}

		log.info("Login test completed successfully.\n");

	}

	/**
	 * Provide test data from an Excel file.
	 *
	 * @return - 2D array of test data.
	 * @throws - IOException if an I/O exception occurs.
	 */

	public static Object[][] provideTestData() throws IOException {
		String excelPath = "C:\\Users\\LENOVO\\Downloads\\Excel\\DataDrivenExcelsheet.xlsx";
		FileInputStream fileInputStream = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fileInputStream);

		Sheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] dataArray = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);

			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				dataArray[i - 1][j] = cell.getStringCellValue();
			}
		}

		workbook.close();
		fileInputStream.close();

		return dataArray;
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
