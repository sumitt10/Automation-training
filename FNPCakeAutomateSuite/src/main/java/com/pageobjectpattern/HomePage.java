package com.pageobjectpattern;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	@SuppressWarnings("unused")
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//a[@id='#cakesmenu']")
	public WebElement elementToHoverLocator;

	@FindBy(xpath = "//a[normalize-space()='Birthday Cakes']")
	public WebElement cakelinkLocator;

	public void navigateTofnp() throws InterruptedException {
		driver.get("https://www.fnp.com/");
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		actions.moveToElement(elementToHoverLocator).perform();
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
				String dateString = (String) data[4];
				String deliveryMethod = (String) data[5];
				String timeSlot = (String) data[6];
				if (!methodExecuted) {
					AddCakeToCartPage addCakeToCartPage = new AddCakeToCartPage(driver);
					addCakeToCartPage.addCakeToCartMethod(address, radiobutton, message, addon, dateString,
							deliveryMethod, timeSlot);
					methodExecuted = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
