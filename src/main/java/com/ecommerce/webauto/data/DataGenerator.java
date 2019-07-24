package com.ecommerce.webauto.data;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;

public class DataGenerator {
	
	public String[][] getDataFromExcel(String testdataFilePath, 
			String signInSheetName) throws Exception{
		// Get the workbook file
		Workbook workbook = Workbook.getWorkbook(new File(testdataFilePath));
		
		// Get the sheet name
		Sheet sheet = workbook.getSheet(signInSheetName);
		
		// Get row and column counts
		int rowCount = sheet.getRows();
		int columnCount = sheet.getColumns();
		
		// Get test data array
		String[][] dataArray = new String[rowCount - 1][columnCount];
		for(int i = 0; i < rowCount - 1; i++){
			for(int j = 0; j < columnCount; j++){
				dataArray[i][j] = sheet.getCell(j, i + 1).getContents();
			}
		}
		return dataArray;
	}
}