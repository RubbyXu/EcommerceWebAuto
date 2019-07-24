package com.ecommerce.webauto.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ecommerce.webauto.data.DataGenerator;
import com.ecommerce.webauto.page.SearchPage;

public class TestSearch extends TestBase {
	private SearchPage searchPage;

	private static final String TESTDATA_FILE_PATH = "testdata_file_path";
	private static final String SEARCH_SHEET_NAME = "search_sheet_name";
	private static final String SEARCH_WARNING_MSG = "search_warning_msg";
	private static final String ITEMS_COUNT = "items_count";
	private static final String DOUBLE_QUOTES = "\"";
	private static final String SPACE = " ";
	
	// Get search text data from Excel file
	@DataProvider(name="SearchTestData")
	public String[][] searchTestData() throws Exception{
		DataGenerator dataGenerator = new DataGenerator();
		String testdataFilePath = prop.getProperty(TESTDATA_FILE_PATH);
		String searchSheetName = prop.getProperty(SEARCH_SHEET_NAME);
		return dataGenerator.getDataFromExcel(testdataFilePath, searchSheetName);
	}
	
	@BeforeTest
	public void setUpSearch(){
		// Load web page
		browserOpt.loadWebPage(prop);
		searchPage = new SearchPage(elementFinder, elementAction);
	}
	
	/* Current search text: "dress" and "invalid"
	 * Easy to expand TCs to cover other search text, no code change needed
	 * How to cover other search text:
	 *  - change valid search text in SearchData sheet of testData.xls
	 *  - change valid search text and expected items count in test.properties
	 * 
	 * TC1: Enter valid search text
	 * 	- Expected: Item/Items shown up
	 * TC2: Enter invalid search text
	 *  - Expected: Warning message shown up: No results were found
	 */
	@Test(dataProvider="SearchTestData")
	public void testSearch(String searchText) throws Exception{
		String validSearchText = searchTestData()[0][0];
		
		// Enter search text
		searchPage.enterSearchText(searchText);
		searchPage.clickSearchButton();
		
		if (searchText.equals(validSearchText)){
			Assert.assertEquals(searchPage.getProductCount(), prop.getProperty(ITEMS_COUNT));
		} else {
			String expectedWarningMsg = prop.getProperty(SEARCH_WARNING_MSG) 
					+ SPACE + DOUBLE_QUOTES + searchText + DOUBLE_QUOTES;
			Assert.assertEquals(searchPage.getWarningMsg(), expectedWarningMsg);
		}
	}
}
