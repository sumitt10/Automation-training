package com.pageobjectpattern;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCakeToCartPage {

	private WebDriver driver;
	@SuppressWarnings("unused")
	private WebDriverWait wait;

	public AddCakeToCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//div[@id='CAKE86093']")
	public WebElement selectCakeLocator;

	@FindBy(xpath = "//input[@id='autocomplete-input']")
	public WebElement addressLocator;

	@FindBy(xpath = "//div[@class='delivery-textbox_dateTimeLink__15UXd delivery-textbox_disabledInput__2Lw5Y']")
	public WebElement dateLocator;

	@FindBy(xpath = "//div[@class='DayPicker-Caption']")
	public WebElement monthCaptionLocator;

	@FindBy(xpath = "//button[@class='delivery-on-calender_calenderArrowButton__-HZCF delivery-on-calender_contentRight__2F_t0 delivery-on-calender_disableMonthArrow__3jLzb']")
	public WebElement nextMonthLocator;

	@FindBy(xpath = "//span[text()='11:00 - 12:00 hrs']")
	public WebElement selectTimeSlot;

	@FindBy(xpath = "//span[@class='cake-types_radioRed__17j0b cake-types_desktopRadioRed__2SkJg false']")
	public WebElement radioText;

	@FindBy(xpath = "//span[@class='cake-types_radioGreen__3-ueb cake-types_desktopRadioGreen__yZ2OI selected']")
	public WebElement egglessButton;

	@FindBy(xpath = "//textarea[@id='msgOnCake']")
	public WebElement msgOnCake;

	@FindBy(xpath = "//button[@data-testid='addToCartBtn']")
	public WebElement addButton;

	@FindBy(xpath = "//button[@id='add-on-cta']")
	public WebElement continueBtnElement;

	@FindBy(xpath = "//span[@class='cartButton_login_content__2j1f7']")
	public WebElement checkoutBtnElement;

	public void addCakeToCartMethod(String address, String radiobutton, String message, String addon, String dateString,
			String deliveryMethod, String timeSlot) throws InterruptedException {

		selectCakeLocator.click();
		Thread.sleep(4000);

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		@SuppressWarnings("unused")
		String parentid = (String) it.next();
		String childid = (String) it.next();
		driver.switchTo().window(childid);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();

		addressLocator.sendKeys(address);
		Thread.sleep(2000);
		actions.sendKeys(Keys.ENTER).perform();

		dateLocator.click();
		Thread.sleep(1500);

		String[] parts = dateString.split(" ");

		String day = parts[0];
		String month = parts[1];
		while (true) {
			String txtMonth = monthCaptionLocator.getText();
			if (txtMonth.equals(month)) {
				break;
			} else {
				nextMonthLocator.click();
			}
		}

		WebElement dynamicdateElement = driver
				.findElement(By.xpath("//div[contains(@class, 'date-block') and contains(text(),'" + day + "')]"));
		dynamicdateElement.click();
		Thread.sleep(1500);

		WebElement dynamicShipMethodElement = driver.findElement(
				By.xpath("//span[contains(@class, 'shipping-method_leftBoxText__2vVv9') and contains(text(),'"
						+ deliveryMethod + "')]"));
		dynamicShipMethodElement.click();
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
				driver.findElement(
						By.xpath("//span[contains(@class, 'time-slot_timeSlotRange__Jd7QR') and contains(text(),'"
								+ timeSlot + "')]")));

		WebElement dynamicTimeSlotElement = driver.findElement(By.xpath(
				"//span[contains(@class, 'time-slot_timeSlotRange__Jd7QR') and contains(text(),'" + timeSlot + "')]"));
		dynamicTimeSlotElement.click();

		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();

		String textradio = radioText.getText();

		if (textradio.equals(radiobutton)) {
			radioText.click();
		} else {
			egglessButton.click();
		}

		if (message != null) {
			msgOnCake.sendKeys(message);
		}
		Thread.sleep(2000);

		addButton.click();
		Thread.sleep(4000);

		try {
			WebElement dynamicTabElement = driver.findElement(By.xpath(
					"//div[@class='addon-category_addons-category__2c9vL']//button/span[text()='" + addon + "']"));
			dynamicTabElement.click();
			System.out.println("AddOn Found : " + addon);
		} catch (NoSuchElementException e) {
			System.out.println("Addon : '" + addon + "' Not found !!");
		}

		continueBtnElement.click();
		Thread.sleep(2000);

		try {
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_MINUS);

			robot.keyRelease(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);

		checkoutBtnElement.click();

	}
}
