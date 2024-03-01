package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	private FileInputStream fis;
	private XSSFWorkbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;

	public XLUtility(String filePath) {
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getLastRowNum() - sheet.getFirstRowNum();
	}

	public int getCellCount(String sheetName, int rowNum) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		return row.getLastCellNum() - row.getFirstCellNum();
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);

		if (row == null) {
			// If the row is null, create a new row
			row = sheet.createRow(rowNum);
		}

		cell = row.getCell(colNum);

		if (cell == null) {
			// If the cell is null, create a new cell
			cell = row.createCell(colNum);
		}

		if (cell.getCellType() == CellType.NUMERIC) {
			// If the cell is numeric, return it as a string
			return String.valueOf(cell.getNumericCellValue());
		} else {
			// If the cell is not numeric, return it as a string
			return cell.getStringCellValue();
		}
	}

	public void closeWorkbook() {
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
