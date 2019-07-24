package com.ecommerce.webauto.page;

import org.openqa.selenium.WebElement;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class PageBase {
	protected ElementFinder elementFinder;
	protected ElementAction elementAction;
	
	public PageBase(ElementFinder elementFinder, ElementAction elementAction) {
		this.elementFinder = elementFinder;
		this.elementAction = elementAction;
	}

	public void navigateByClass(String className){
		WebElement classElement = elementFinder.getElementByClass(className);
		clickButton(classElement);
	}
	
	public String getPageTitle(){
		return elementFinder.getPageTitle();
	}
	
	public void clickButton(WebElement webElement){
		elementAction.clickButton(webElement);
	}
	
	public void inputValue(WebElement webElement, String value){
		elementAction.inputValue(webElement, value);
	}
	
	public void clickHover(WebElement webElement){
		elementAction.clickHover(webElement);
	}
}
