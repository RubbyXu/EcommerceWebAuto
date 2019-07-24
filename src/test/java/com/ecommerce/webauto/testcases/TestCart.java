package com.ecommerce.webauto.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ecommerce.webauto.data.DataGenerator;
import com.ecommerce.webauto.page.CartPage;
import com.ecommerce.webauto.page.ItemPage;
import com.ecommerce.webauto.page.SearchPage;

public class TestCart extends TestBase {
	private SearchPage searchPage;
	private ItemPage itemPage;
	private CartPage cartPage;
	private List<WebElement> cartElementList;
	private static final String TESTDATA_FILE_PATH = "testdata_file_path";
	private static final String ITEMS_SHEET_NAME = "items_sheet_name";
	
	/* Get items to be added in cart from Excel file, including 
	 * item name, item quantity, item size and item color
	 */
	@DataProvider(name="ItemsTestData")
	public String[][] itemsTestData() throws Exception{
		DataGenerator dataGenerator = new DataGenerator();
		String testdataFilePath = prop.getProperty(TESTDATA_FILE_PATH);
		String itemsSheetName = prop.getProperty(ITEMS_SHEET_NAME);
		return dataGenerator.getDataFromExcel(testdataFilePath, itemsSheetName);
	}
	
	@BeforeTest
	public void setUpCart() throws Exception{
		// Load web page
		browserOpt.loadWebPage(prop);
		// Search items specified in testData.xls, and add them to shopping cart
		searchPage = new SearchPage(elementFinder, elementAction);
		itemPage = new ItemPage(elementFinder, elementAction);
		for (String[] item : itemsTestData()){
			String itemName = item[0];
			String itemQuantity = item[1];
			String itemSize = item[2];
			String itemColor = item[3];
			searchPage.enterSearchText(itemName);
			searchPage.clickSearchButton();
			searchPage.clickHoverImgLink();
			Thread.sleep(2000);
			itemPage.inputItemQuantity(itemQuantity);
			Thread.sleep(2000);
			itemPage.selectItemSize(itemSize);
			Thread.sleep(2000);
			itemPage.selectItemColor(itemColor);
			Thread.sleep(2000);
			itemPage.clickAddToCart();
			Thread.sleep(3000);
			itemPage.clickContShopping();
			Thread.sleep(2000);
		}
		searchPage.clickCart();
		Thread.sleep(5000);
		// Get shopping cart items list
		cartPage = new CartPage(elementFinder, elementAction);
		cartElementList = cartPage.getCartItemList();
	}
	
	// Verify items in cart
	@Test
	public void testCart() throws Exception{
		int i = 0;
		for (WebElement cartElement : cartElementList){
			// Verify item name
			String acturalItemName = cartPage.getItemName(cartElement);
			String expectedItemName = itemsTestData()[i][0];
			Assert.assertEquals(acturalItemName, expectedItemName);
			// Verify item quantity
			String actualItemQuantity = cartPage.getItemQuantity(cartElement, i + 1);
			String expectedItemQuantity = itemsTestData()[i][1];
			Assert.assertEquals(actualItemQuantity, expectedItemQuantity);
			// Verify item size
			String actualItemSize = cartPage.getItemSize(cartElement, i + 1);
			String expectedItemSize = itemsTestData()[i][2];
			Assert.assertEquals(actualItemSize, expectedItemSize);
			// Verify item color
			String actualItemColor = cartPage.getItemColor(cartElement, i + 1);
			String expectedItemColor = itemsTestData()[i][3];
			Assert.assertEquals(actualItemColor, expectedItemColor);
			i++;
		}
	}
	
	// Remove shopping car items
	@AfterTest
	public void tearDownCart(){
		int i = 0;
		for (WebElement cartElement : cartElementList){
			cartPage.clickItemDeleteIcon(cartElement, i + 1);
			i++;
		}
	}
}
