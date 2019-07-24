package com.ecommerce.webauto.testcases;

//import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ecommerce.webauto.data.DataGenerator;
import com.ecommerce.webauto.page.AccountPage;
import com.ecommerce.webauto.page.Authentication;
import com.ecommerce.webauto.page.HomePage;

public class TestSignIn extends TestBase{
	private Authentication authPage;
	private HomePage homePage;
	private AccountPage accountPage;
	private static final String TESTDATA_FILE_PATH = "testdata_file_path";
	private static final String SIGNIN_SHEET_NAME = "signin_sheet_name";
	private static final String EXPECTED_INVALID_EMAIL_ERR = "expected_invalid_email_err";
	private static final String EXPECTED_INVALID_PASSWD_ERR = "expected_invalid_passwd_err";	
	private static final String EXPECTED_ACCOUNT_TITLE = "expected_account_title";
	
	// Get sign in test data from Excel file, including email and password
	@DataProvider(name="SignInTestData")
	public String[][] signInTestData() throws Exception{
		DataGenerator dataGenerator = new DataGenerator();
		String testdataFilePath = prop.getProperty(TESTDATA_FILE_PATH);
		String signInSheetName = prop.getProperty(SIGNIN_SHEET_NAME);
		return dataGenerator.getDataFromExcel(testdataFilePath, signInSheetName);
	}

	@BeforeTest
	public void setUpSignIn(){
		// Load web page
		browserOpt.loadWebPage(prop);
		authPage = new Authentication(elementFinder, elementAction);
		homePage = new HomePage(elementFinder, elementAction);
		accountPage = new AccountPage(elementFinder, elementAction);
		// Navigate to authentication page from home page
		homePage.navigateToAuthentication();
	}
	
	/* TC1: Enter valid email & password
	 * 	- Expected: Sign in successful, my account page shown
	 * TC2: Enter invalid email & valid password
	 *  - Expected: Authentication error page shown, error message "Invalid email address."
	 * TC3: Enter valid email & invalid password
	 *  - Expected: Authentication error page shown, error message: "Authentication failed."
	 * TC4: Enter invalid email & invalid password 
	 * 	- Expected: Authentication error page shown, error message "Invalid email address."
	 */
	@Test(dataProvider="SignInTestData")
	public void testSignIn(String email, String password) throws Exception{
		String validEmail = signInTestData()[0][0];
		String validPasswd = signInTestData()[0][1];
		
		// Enter email
		authPage.enterEmail(email);
		// Enter password
		authPage.enterPassword(password);
		// Click sign in button
		authPage.clickSignInButton();
		
		if (email.equals(validEmail) && password.equals(validPasswd)){
			Assert.assertEquals(accountPage.getPageTitle(), prop.get(EXPECTED_ACCOUNT_TITLE));
			accountPage.signOut();
		} else if (!email.equals(validEmail) && password.equals(validPasswd)){
			Assert.assertEquals(authPage.getAuthError(), prop.get(EXPECTED_INVALID_EMAIL_ERR));
		} else if (email.equals(validEmail) && !password.equals(validPasswd)){
			Assert.assertEquals(authPage.getAuthError(), prop.get(EXPECTED_INVALID_PASSWD_ERR));
		} else {
			Assert.assertEquals(authPage.getAuthError(), prop.get(EXPECTED_INVALID_EMAIL_ERR));
		}
	}
}
