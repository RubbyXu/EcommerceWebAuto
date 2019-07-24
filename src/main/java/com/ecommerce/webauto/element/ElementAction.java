package com.ecommerce.webauto.element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementAction {
	private WebDriver webDriver;
	private static final String JS_SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true);";
	private static final String JS_CLICK = "arguments[0].click();";
	
	public ElementAction(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	public void inputValue(WebElement webElement, String value){
		webElement.clear();
		webElement.sendKeys(value);
	}
	
	public void clickButton(WebElement webElement){
		webElement.click();
	}
	
	public void clickHover(WebElement webElement){
		jsExecuteScript(JS_SCROLL_INTO_VIEW, webElement);
		Actions actions = new Actions(webDriver);
		actions.moveToElement(webElement).perform();
		jsExecuteScript(JS_CLICK, webElement);
	}
	
	private void jsExecuteScript(String script, WebElement webElement){
		((JavascriptExecutor)webDriver).executeScript(script, webElement);
	}
}
