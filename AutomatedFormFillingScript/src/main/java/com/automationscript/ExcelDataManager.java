package com.automationscript;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class ExcelDataManager {
	private WebDriver driver;

	public ExcelDataManager(WebDriver driver) {
		this.driver = driver;
	}

	public void getData() {
		try {
			FileInputStream file = new FileInputStream(
					new File("C:\\Users\\LENOVO\\Downloads\\Excel\\Form-Filling-Data.xlsx"));
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);

				String firstName = getStringValue(row.getCell(0));
				String lastName = getStringValue(row.getCell(1));
				String address = getStringValue(row.getCell(2));
				String city = getStringValue(row.getCell(3));
				String state = getStringValue(row.getCell(4));
				String zipCode = getStringValue(row.getCell(5));
				String phoneNo = getStringValue(row.getCell(6));
				String ssn = getStringValue(row.getCell(7));
				String username = getStringValue(row.getCell(8));
				String password = getStringValue(row.getCell(9));
				String confirmPassword = getStringValue(row.getCell(10));

				RegistrationPage registrationPage = new RegistrationPage(driver);
				registrationPage.registration(firstName, lastName, address, city, state, zipCode, phoneNo, ssn,
						username, password, confirmPassword);
				Thread.sleep(3000);
			}

			file.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static String getStringValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString(); // Handle date cells if needed
			} else {
				return String.valueOf((int) cell.getNumericCellValue()); // Convert numeric value to string
			}
		default:
			return "";
		}
	}
}
