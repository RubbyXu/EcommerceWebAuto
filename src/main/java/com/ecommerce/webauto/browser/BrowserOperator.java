package com.ecommerce.webauto.browser;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class BrowserOperator {
	private WebDriver webDriver;
	private static final String URL = "url";
	
	public BrowserOperator(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void loadWebPage(Properties prop) {
		webDriver.get(prop.getProperty(URL));
	}
	
	public void terminateBrowser(){
		webDriver.quit();
	}
}
