package com.ecommerce.webauto.testcases;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ecommerce.webauto.browser.BrowserOperator;
import com.ecommerce.webauto.driver.DriverManager;
import com.ecommerce.webauto.element.ElementAction;
import com.ecommerce.webauto.element.ElementFinder;

public class TestBase {
	protected static Properties prop;
	protected static BrowserOperator browserOpt;
	protected static WebDriver webDriver;
	protected static ElementFinder elementFinder;
	protected static ElementAction elementAction;
	private static final String PROP_FILE = "config/config.properties";
	
	@BeforeSuite
	public void setUpSuite(){
		// Get test.properties file
		prop = getProperties(PROP_FILE);
		// Get web driver
		webDriver = DriverManager.getWebDriver(prop);
		browserOpt = new BrowserOperator(webDriver);
		elementFinder = new ElementFinder(webDriver);
		elementAction = new ElementAction(webDriver);
	}
	
	@AfterSuite
	public void tearDownSuite(){
		browserOpt.terminateBrowser();
	}
	
	private Properties getProperties(String fileName){
		Properties prop = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}