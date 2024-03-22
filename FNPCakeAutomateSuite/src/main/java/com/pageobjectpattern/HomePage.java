package com.pageobjectpattern;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	@SuppressWarnings("unused")
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void navigateTofnp() throws InterruptedException {
		driver.get("https://www.fnp.com/");
		driver.manage().window().maximize();
		WebElement elementToHover = driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/nav/div/ul/li[2]/a"));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementToHover).perform();
		WebElement cakelinkLocator = driver
				.findElement(By.xpath("/html/body/div[1]/header/div[3]/nav/div/div[3]/div/section[2]/ul/li[1]/a"));
		cakelinkLocator.click();
		Thread.sleep(2000);
		excelDrivenDataMethod();

	}

	public void excelDrivenDataMethod() throws InterruptedException {

		try {
			Object[][] testData = ExcelDataManager.provideTestData();
			boolean methodExecuted = false;

			for (Object[] data : testData) {
				String address = (String) data[0];
				String radiobutton = (String) data[1];
				String message = (String) data[2];
				String addon = (String) data[3];
				if (!methodExecuted) {
					AddCakeToCartPage addCakeToCartPage = new AddCakeToCartPage(driver);
					addCakeToCartPage.addCakeToCartMethod(address, radiobutton, message, addon);
					methodExecuted = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
