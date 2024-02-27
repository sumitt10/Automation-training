package com.webelementcontrol;

import org.openqa.selenium.By;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoExample {

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
}
