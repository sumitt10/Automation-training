package com.orangehrm.utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilityGetDataAndCompare {

	final static String LANGUAGE_TABLE_XPATH = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div";
	final static String LANGUAGE_ROWS_XPATH = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]";
	final static String LANGUAGE_NAME_CELL_XPATH = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]";

	static boolean getDataAndCompare(WebDriver driver, String expectedValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By languageTableLocator = By.xpath(LANGUAGE_TABLE_XPATH);

		WebElement languageTable = wait.until(ExpectedConditions.presenceOfElementLocated(languageTableLocator));

		List<WebElement> languageRows = languageTable.findElements(By.xpath(LANGUAGE_ROWS_XPATH));

		if (languageRows.isEmpty()) {
			log.error("No language rows found on the page.");
			return false;
		}

		List<String> actualLanguageNames = new ArrayList<>();

		for (WebElement row : languageRows) {
			WebElement languageNameCell = row.findElement(By.xpath(LANGUAGE_NAME_CELL_XPATH));
			String actualLanguageName = languageNameCell.getText().trim();
			actualLanguageNames.add(actualLanguageName);
			log.info("Actual Language Name: " + actualLanguageName);
		}

		log.info("Expected Language Name: " + expectedValue);

		if (actualLanguageNames.isEmpty()) {
			log.error("No actual language names captured.");
			return false;
		}

		return actualLanguageNames.contains(expectedValue);
	}

}
