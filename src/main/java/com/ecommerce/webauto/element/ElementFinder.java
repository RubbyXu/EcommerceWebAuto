package com.ecommerce.webauto.element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFinder {
	
	private WebDriver webDriver;
	
	public ElementFinder(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public WebElement getElementByName(String name){
		return webDriver.findElement(By.name(name));
	}
	
	public WebElement getElementById(String id){
		return webDriver.findElement(By.id(id));
	}
	
	public WebElement getElementByClass(String className){
		return webDriver.findElement(By.className(className));
	}
	
	public WebElement getElementByTagName(String tagName){
		return webDriver.findElement(By.tagName(tagName));
	}
	
	public WebElement getElementByLinkText(String linkText){
		return webDriver.findElement(By.linkText(linkText));
	}
	
	public WebElement getElementByPartialLinkText(String partialLinkText){
		return webDriver.findElement(By.partialLinkText(partialLinkText));
	} 
	
	public WebElement getElementByXPath(String xPath){
		return webDriver.findElement(By.xpath(xPath));
	}
	
	public WebElement getElementByCssSelector(String cssSelector){
		return webDriver.findElement(By.cssSelector(cssSelector));
	}
	
	public List<WebElement> getElementListByXPath(String xPath){
		return webDriver.findElements(By.xpath(xPath));
	}
	
	public WebElement getHoverElementByClass(String className){
		return webDriver.findElement(By.className(className));
	}
	
	public String getPageTitle(){
		return webDriver.getTitle();
	}
}
