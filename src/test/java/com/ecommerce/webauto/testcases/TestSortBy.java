package com.ecommerce.webauto.testcases;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ecommerce.webauto.page.SearchPage;

public class TestSortBy extends TestBase {
	private SearchPage searchPage;
	private static final String SEARCH_TEXT = "search_text";
	private static final String PRODUCT_ID_LIST = "product_id_list";
	private static final String EQUAL= "=";
	private static final String AND = "&";
	private static final String COMMA = ",";
	private static final String REF_HIGH_FIRST_SORT_VALUE = "reference:desc";
	private static final String ITEM_ATTRIBUTE_HREF = "href";
	
	@BeforeTest
	public void setUpSortBy(){
		// Load web page
		browserOpt.loadWebPage(prop);
		// Enter search text to get product items
		searchPage = new SearchPage(elementFinder, elementAction);
		searchPage.enterSearchText(prop.getProperty(SEARCH_TEXT));
		searchPage.clickSearchButton();
	}
	
	// Verify items sorted by Reference: Highest first
	@Test
	public void testRefHighFirst(){
		searchPage.selectSortByValue(REF_HIGH_FIRST_SORT_VALUE);
		verifyItemsSort();
	}
	
	private void verifyItemsSort(){
		int i = 0;
		List<WebElement> itemList = searchPage.getSearchedItemList();
		for (WebElement item : itemList){
			String actualProductID = item.getAttribute(ITEM_ATTRIBUTE_HREF).split(EQUAL)[1].split(AND)[0];
			String expectedProductID = prop.getProperty(PRODUCT_ID_LIST).split(COMMA)[i];
			Assert.assertEquals(actualProductID, expectedProductID);
			i++;
		}
	}
}
