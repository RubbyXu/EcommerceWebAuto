package com.ecommerce.webauto.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertPage {
	private WebDriver webDriver;
	private Alert alert;
	
	public AlertPage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}
	
	public String getAlertMsg(){
		alert = webDriver.switchTo().alert();
		return alert.getText();
	}
	
	public void acceptAlert(){
		alert.accept();
	}
}
