package com.ecommerce.webauto.page;

import org.openqa.selenium.WebElement;

import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class HomePage extends PageBase {
	private static final String SIGN_IN_CLASS = "login";
	private static final String SHOPPING_CART_CLASS = "shopping_cart";
	
	public HomePage(ElementFinder elementFinder, ElementAction elementAction) {
		super(elementFinder, elementAction);
	}

	// Navigate to authentication page
	public void navigateToAuthentication(){
		navigateByClass(SIGN_IN_CLASS);
	}
	
	// Click Cart
	public void clickCart(){
		WebElement cartElement = elementFinder.getElementByClass(SHOPPING_CART_CLASS);
		clickButton(cartElement);
	}
}
