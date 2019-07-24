package com.ecommerce.webauto.page;

import org.openqa.selenium.WebElement;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class AccountPage extends PageBase {
	private static final String SIGN_OUT_CLASS = "logout";

	public AccountPage(ElementFinder elementFinder, ElementAction elementAction) {
		super(elementFinder, elementAction);
	}
	
	public void signOut(){
		WebElement signOutElement;
		try {
			signOutElement = elementFinder.getElementByClass(SIGN_OUT_CLASS);
			clickButton(signOutElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
