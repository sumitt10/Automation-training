package com.automationscript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataEntryAutomation {

	@SuppressWarnings("unused")
	private WebDriver driver;

	public DataEntryAutomation(WebDriver driver) {
		this.driver = driver;
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\LENOVO\\Downloads\\browserDriver\\browser\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		ExcelDataManager excelDataManager = new ExcelDataManager(driver);
		excelDataManager.getData();
		driver.quit();
	}

}
