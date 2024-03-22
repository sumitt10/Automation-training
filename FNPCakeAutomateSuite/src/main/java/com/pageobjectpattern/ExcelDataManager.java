package com.pageobjectpattern;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataManager {
	public static Object[][] provideTestData() throws IOException {
		String excelPath = "C:\\Users\\LENOVO\\Downloads\\Excel\\fnpcakes.xlsx";
		FileInputStream fileInputStream = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fileInputStream);

		Sheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] dataArray = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							dataArray[i - 1][j] = cell.getStringCellValue();
							break;
						case NUMERIC:
							dataArray[i - 1][j] = cell.getNumericCellValue();
							break;
						default:
							break;
						}
					}
				}
			}
		}

		workbook.close();
		fileInputStream.close();

		return dataArray;
	}
}
