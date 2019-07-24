package com.ecommerce.webauto.driver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.ecommerce.webauto.browser.BrowserType;

public class DriverManager {
	private static WebDriver webDriver;
	private static final String DRIVER_APPENDIX = "_driver";
	private static final String DRIVER_PATH_APPENDIX = "_driver_path";
	private static final String BROWSER_TYPE = "browser_type";
	private static final String IMPLICITLY_WAIT = "implicitly_wait";
	private static final String PAGELOAD_TIMEOUT = "pageload_timeout";

	public static WebDriver getWebDriver(Properties prop){
		if (webDriver == null){
			String browserType = prop.getProperty(BROWSER_TYPE);
			String driver = prop.getProperty(browserType.toLowerCase() + DRIVER_APPENDIX);
			String driverPath = prop.getProperty(browserType.toLowerCase() + DRIVER_PATH_APPENDIX);
			System.setProperty(driver, driverPath);
			
			switch(BrowserType.valueOf(browserType)){
				case FireFox:
					webDriver = new FirefoxDriver();
					break;
				case Chrome:
					webDriver = new ChromeDriver();
					break;
				case IE:
					webDriver = getIEWebDriver();
					break;
				default:
					webDriver = new FirefoxDriver();
			}
			maximizeWindow();
			setImplicitelyWait(Integer.valueOf(prop.getProperty(IMPLICITLY_WAIT)));
			setPageLoadTimeout(Integer.valueOf(prop.getProperty(PAGELOAD_TIMEOUT)));
		}
		return webDriver;
	}
	
	private static void maximizeWindow(){
		webDriver.manage().window().maximize();
	}
	
	private static void setImplicitelyWait(int implicitlyWait){
		webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
	}
	
	private static void setPageLoadTimeout(int pageloadTimeout){
		webDriver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);
	}
	
	private static WebDriver getIEWebDriver() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		options.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
		return new InternetExplorerDriver(options);
	}
}
