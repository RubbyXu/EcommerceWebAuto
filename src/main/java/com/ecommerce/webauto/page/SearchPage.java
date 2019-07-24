package com.ecommerce.webauto.page;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class SearchPage extends PageBase {
	private static final String SEARCH_TEXT_ID = "search_query_top";
	private static final String SEARCH_BUTTON_CLASS = "button-search";
	private static final String WARNING_CLASS = "alert-warning";
	private static final String PRODUCT_COUNT_CLASS = "product-count";
	private static final String SELECT_PRODUCT_SORT_ID = "selectProductSort";
	private static final String SEARCHED_ITEM_XPATH = "//h5[@itemprop='name']/a";
	private static final String HOVER_IMG_LINK_CLASS = "product_img_link";
	private static final String SHOPPING_CART_XPATH = "//div[@class='shopping_cart']/a";
	
	public SearchPage(ElementFinder elementFinder, ElementAction elementAction) {
		super(elementFinder, elementAction);
	}
	
	public void enterSearchText(String searchText){
		WebElement searchTextElement = elementFinder.getElementById(SEARCH_TEXT_ID);
		searchTextElement.clear();
		inputValue(searchTextElement, searchText);
	}
	
	public void clickSearchButton(){
		WebElement searchBtnElement = elementFinder.getElementByClass(SEARCH_BUTTON_CLASS);
		clickButton(searchBtnElement);
	}
	
	public String getWarningMsg(){
		WebElement warningElement = elementFinder.getElementByClass(WARNING_CLASS);
		return warningElement.getText();
	}
	
	public String getProductCount(){
		WebElement productCountElement = elementFinder.getElementByClass(PRODUCT_COUNT_CLASS);
		return productCountElement.getText();
	}
	
	public void selectSortByValue(String optionValue){
		Select productSort = new Select(elementFinder.getElementById(SELECT_PRODUCT_SORT_ID));
		productSort.selectByValue(optionValue);
	}
	
	public List<WebElement> getSearchedItemList(){
		return elementFinder.getElementListByXPath(SEARCHED_ITEM_XPATH);
	}
	
	// Click img link of the first item
	public void clickHoverImgLink(){
		WebElement imgLinkElement = elementFinder.getHoverElementByClass(HOVER_IMG_LINK_CLASS);
		clickHover(imgLinkElement);
	}
	
	public void clickCart(){
		WebElement cartElement = elementFinder.getElementByXPath(SHOPPING_CART_XPATH);
		clickButton(cartElement);
	}
}
