package com.pageobjectpattern;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonutils.BrowserUtils;

public class SkillPage extends BrowserUtils {

	private By addButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/button/i");
	private By skillNameFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	private By skillDescriptionFieldLocator = By
			.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/textarea");
	private By saveButtonLocator = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
	private By toastMessageLocator = By.xpath("/html/body/div/div[2]");

	public void clickAddButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
	}

	public void enterSkillName(String skillName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement skillNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(skillNameFieldLocator));
		skillNameField.sendKeys(skillName);
	}

	public void enterSkillDescription(String skillDescription) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement skillDescriptionField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(skillDescriptionFieldLocator));
		skillDescriptionField.sendKeys(skillDescription);
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

	public void addSkill() throws InterruptedException {
		SkillPage skillPage = new SkillPage();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSkills");

		String skillName = config.getProperty("addskill.name");
		String skillDescription = config.getProperty("addskill.desc");

		skillPage.clickAddButton();
		skillPage.enterSkillName(skillName);
		skillPage.enterSkillDescription(skillDescription);
		skillPage.clickSaveButton();

		Thread.sleep(3000);
	}
}
