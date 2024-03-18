package com.pageobjectpattern;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LanguagePage extends BrowserUtils {

	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button");
	private By languageNameFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public void clickAddButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
	}

	public void enterLanguageName(String languageName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement languageNameField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(languageNameFieldLocator));
		languageNameField.sendKeys(languageName);
	}

	public void clickSaveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
		saveButton.click();
	}

	public boolean isToastMessageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(toastMessageLocator));
		return toastMessage.isDisplayed();
	}

	public void addLanguage() throws InterruptedException {
		LanguagePage languagePage = new LanguagePage();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewLanguages");

		String languageName = config.getProperty("addlanguage.language");

		languagePage.clickAddButton();
		languagePage.enterLanguageName(languageName);
		languagePage.clickSaveButton();

		Thread.sleep(3000);
	}

	final static String LANGUAGE_TABLE_XPATH = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div";
	final static String LANGUAGE_ROWS_XPATH = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]";
	final static String LANGUAGE_NAME_CELL_XPATH = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]";

	public String expectedvalue;
	public List<String> actualLanguageNames;

	public String getExpectedvalue() {
		return expectedvalue;
	}

	public void setExpectedvalue(String expectedvalue) {
		this.expectedvalue = expectedvalue;
	}

	public List<String> getActualLanguageNames() {
		return actualLanguageNames;
	}

	public void setActualLanguageNames(List<String> actualLanguageNames) {
		this.actualLanguageNames = actualLanguageNames;
	}

	public void getDataAndCompare() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewLanguages");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String expectedValue = config.getProperty("addassertion.langexpvalue");
		LanguagePage obj1 = new LanguagePage();
		obj1.setExpectedvalue(expectedValue);

		By languageTableLocator = By.xpath(LANGUAGE_TABLE_XPATH);

		WebElement languageTable = wait.until(ExpectedConditions.presenceOfElementLocated(languageTableLocator));

		List<WebElement> languageRows = languageTable.findElements(By.xpath(LANGUAGE_ROWS_XPATH));

		if (languageRows.isEmpty()) {
			log.error("No language rows found on the page.");
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
		}
		obj1.setActualLanguageNames(actualLanguageNames);

		Thread.sleep(4000);

	}
}
