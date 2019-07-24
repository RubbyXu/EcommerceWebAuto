package com.ecommerce.webauto.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class Authentication extends PageBase {
	private static final String EMAIL_ID = "email";
	private static final String PASSWORD_NAME = "passwd";
	private static final String SIGN_IN_BUTTON_NAME = "SubmitLogin";
	private static final String AUTH_ERR_CLASS = "alert-danger";
	private static final String AUTH_ERR_TAG = "li";

	public Authentication(ElementFinder elementFinder, ElementAction elementAction) {
		super(elementFinder, elementAction);
	}

	public void enterEmail(String email){
		WebElement emailElement = elementFinder.getElementById(EMAIL_ID);
		emailElement.clear();
		inputValue(emailElement, email);
	}
	
	public void enterPassword(String password){
		WebElement passwordElement = elementFinder.getElementByName(PASSWORD_NAME);
		passwordElement.clear();
		inputValue(passwordElement, password);
	}
	
	public void clickSignInButton(){
		WebElement signInButton = elementFinder.getElementByName(SIGN_IN_BUTTON_NAME);
		clickButton(signInButton);
	}
	
	// Get authentication error message
	public String getAuthError(){
		return elementFinder.getElementByClass(AUTH_ERR_CLASS)
				.findElement(By.tagName(AUTH_ERR_TAG)).getText();
	}
}
