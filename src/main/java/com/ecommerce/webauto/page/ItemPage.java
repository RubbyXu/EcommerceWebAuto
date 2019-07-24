package com.ecommerce.webauto.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class ItemPage extends PageBase {
	private static final String ITEM_QUANTITY_ID = "quantity_wanted";
	private static final String ITEM_SIZE_ID = "group_1";
	private static final String ADD_TO_CART_NAME = "Submit";
	private static final String CONT_SHOPPING_XPATH = "//span[@title='Continue shopping']";

	public ItemPage(ElementFinder elementFinder, ElementAction elementAction) {
		super(elementFinder, elementAction);
	}
	
	public void inputItemQuantity(String itemQuantity){
		WebElement quantityElement = elementFinder.getElementById(ITEM_QUANTITY_ID);
		inputValue(quantityElement, itemQuantity);
	}
	
	public void selectItemSize(String itemSize){
		Select sizeElement = new Select(elementFinder.getElementById(ITEM_SIZE_ID));
		sizeElement.selectByVisibleText(itemSize);
	}
	
	public void selectItemColor(String itemColor){
		WebElement colorElement = elementFinder.getElementByName(itemColor);
		clickButton(colorElement);
	}
	
	// Click Add to cart button of the first item from the home page
	public void clickAddToCart(){
		WebElement addToCartElement = elementFinder.getElementByName(ADD_TO_CART_NAME);
		clickButton(addToCartElement);
	}
	
	// Click Continue shopping
	public void clickContShopping(){
		WebElement contShoppingElement = elementFinder.getElementByXPath(CONT_SHOPPING_XPATH);
		clickButton(contShoppingElement);
	}
}
