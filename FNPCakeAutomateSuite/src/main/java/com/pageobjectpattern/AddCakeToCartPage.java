package com.pageobjectpattern;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCakeToCartPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public AddCakeToCartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void addCakeToCartMethod(String address, String radiobutton, String message, String addon)
			throws InterruptedException {
		WebElement fnpcake = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[1]/main/div[3]/div/div/div[2]/div[1]/section/div[2]/div/div[1]/div/div[1]")));
		fnpcake.click();
		Thread.sleep(4000);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		@SuppressWarnings("unused")
		String parentid = (String) it.next();
		String childid = (String) it.next();
		driver.switchTo().window(childid);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();
		WebElement fnpaddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[7]/div[1]/div/div[1]/div[1]/input")));
		fnpaddress.sendKeys(address);
		Thread.sleep(3000);
		actions.sendKeys(Keys.ENTER).perform();

		WebElement SelectDateLocator = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[7]/div[2]/div/div")));
		SelectDateLocator.click();

		WebElement SelectDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div[7]/div[3]/div/div/div/div[2]/div/div/div/div[1]/div/div/div/div/div[2]/div[1]/div/div/div/div[2]/div/div[3]/div[5]/div[4]/div/div")));
		SelectDate.click();

		WebElement SelectMethod = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div[7]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div/ul/li[1]/label/span")));
		SelectMethod.click();

		WebElement SelectTimeSlot = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div[7]/div[3]/div/div/div/div[2]/div/div/div/div[3]/div/div/div/div/div[2]/div/ul/li/ul/li[1]/label/span")));
		SelectTimeSlot.click();

		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();

		WebElement radiotext = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[7]/div[3]/div/div/div/fieldset/div/span[1]"));

		String textradio = radiotext.getText();
		if (textradio.equals(radiobutton)) {
			WebElement radiotxt = driver.findElement(By.xpath(
					"/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[7]/div[3]/div/div/div/fieldset/div/span[1]"));
			radiotxt.click();
		} else {
			WebElement Egglessbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[7]/div[3]/div/div/div/fieldset/div/span[2]")));
			Egglessbutton.click();
		}

		WebElement MsgOnCake = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[7]/div[4]/div/div/textarea[1]")));
		MsgOnCake.sendKeys(message);
		Thread.sleep(2000);
		WebElement addbutton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[1]/main/div[2]/div/div/div[2]/div/div/div/div[2]/div[9]/button[1]")));
		addbutton.click();

		Thread.sleep(4000);
		WebElement tabElement = driver.findElement(
				By.xpath("//div[@class='addon-category_addons-category__2c9vL']//button/span[text()='" + addon + "']"));
		tabElement.click();

		WebElement addonElement = driver.findElement(
				By.xpath("(//li[@class='addon-item_add-on-item__2c12J addon-item_layout-desktop__2ioh2 '])[1]"));
		addonElement.click();

		WebElement continuebuttonElement = driver
				.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[2]/button"));
		continuebuttonElement.click();
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

		WebElement checkoutbuttonElement = driver.findElement(
				By.xpath("/html/body/div[1]/header/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/button"));
		checkoutbuttonElement.click();

	}
}
