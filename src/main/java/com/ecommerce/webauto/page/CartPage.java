package com.ecommerce.webauto.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class CartPage extends PageBase {
	private static final String ITEM_XPATH = "//td[@class='cart_description']";
	private static final String ITEM_CLASS = "product-name";
	private static final String ITEM_XPATH_PART_1 = "//div[@id='order-detail-content']/table[1]/tbody/tr[";
	private static final String ITEM_QUANTITY_XPATH_PART_2 = "]/td[5]/input[2]";
	private static final String ITEM_AUANTITY_VALUE = "value";
	private static final String ITEM_SIZE_COLOR_XPATH_PART_2 = "]/td[2]/small[2]/a";
	private static final String ITEM_DELETE_XPATH_PART_2 = "]/td[7]/div/a/i";
	private static final String COLON= ":";
	private static final String COMMA = ",";

	public CartPage(ElementFinder elementFinder, ElementAction elementAction) {
		super(elementFinder, elementAction);
	}

	public List<WebElement> getCartItemList(){
		return elementFinder.getElementListByXPath(ITEM_XPATH);
	}
	
	public String getItemName(WebElement webElement){
		return webElement.findElement(By.className(ITEM_CLASS)).getText();
	}
	
	public String getItemQuantity(WebElement webElement, int itemNum){
		return webElement.findElement(By.xpath( ITEM_XPATH_PART_1 + itemNum 
				+ ITEM_QUANTITY_XPATH_PART_2)).getAttribute(ITEM_AUANTITY_VALUE);
	}
	
	public String getItemSize(WebElement webElement, int itemNum){
		String itemSizeAndColor = getItemSizeAndColor(webElement, itemNum);
		return itemSizeAndColor.replace(COMMA, COLON).split(COLON)[3].trim();
	}
	
	public String getItemColor(WebElement webElement, int itemNum){
		String itemSizeAndColor = getItemSizeAndColor(webElement, itemNum);
		return itemSizeAndColor.replace(COMMA, COLON).split(COLON)[1].trim();
	}

	private String getItemSizeAndColor(WebElement webElement, int itemNum){
		return webElement.findElement(By.xpath(ITEM_XPATH_PART_1 + itemNum 
				+ ITEM_SIZE_COLOR_XPATH_PART_2)).getText();
	}
	
	public void clickItemDeleteIcon(WebElement webElement, int itemNum){
		WebElement deleteIconElement = webElement.findElement(By.xpath(ITEM_XPATH_PART_1 
				+ itemNum + ITEM_DELETE_XPATH_PART_2));
		elementAction.clickButton(deleteIconElement);
	}
}
