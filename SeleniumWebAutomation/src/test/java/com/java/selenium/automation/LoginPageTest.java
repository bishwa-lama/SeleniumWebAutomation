package com.java.selenium.automation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobject.ForgotPassword;
import pageobject.LoginPage;
import resources.Base;

public class LoginPageTest extends Base{
	public WebDriver driver;
	public LoginPage login;
	/**
	 * Initialize() method to select the appropriate WebDriver
	 * @throws IOException
	 */
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeWebDriver();
	}
	
	/**
	 * TestCase 1: Validate signIn with valid username and password
	 * Method: loginWithValidUser 
	 * @param userName
	 * @param password
	 * @throws IOException
	 * @throws Exception
	 */
	@Test(dataProvider="getValidUserData")
	public void loginWithValidUser(String userName, String password) throws Exception 
	{
		driver.get(prop.getProperty("url"));
		login = new LoginPage(driver);
		login.getUserName().sendKeys(userName);
		login.getPassword().sendKeys(password);
		login.getSignin().click();
		Assert.assertEquals(login.getLoginSuccess().getText(), "You are successfully logged in."); 
		//Just added to slowdown the process
		Thread.sleep(1000);
		login.getLogOut().click();
	}
	
	/**
	 * TestCase 2: Validate signIn with invalid username and password
	 * Method: loginWithInvalidUser
	 * @param userName
	 * @param password
	 */
	@Test(dependsOnMethods="loginWithValidUser", dataProvider="getInValidUserData")
	public void loginWithInvalidUser(String userName, String password) {
		driver.get(prop.getProperty("url"));
		login = new LoginPage(driver);
		login.getUserName().sendKeys(userName);
		login.getPassword().sendKeys(password);
		login.getSignin().click();
		Assert.assertEquals(login.getLoginFail().getText(), "* Incorrect username or password");
		Assert.assertNotSame(login.getLoginFail().getText(), "Incorrect username....");
		//Assert.assertEquals(login.getLoginFail().getText(), "Incorrect username or pass");
	}
	
	/**
	 * TestCase 3: Retrieve temporary password
	 * Method: retrievePassword
	 * @param userName
	 * @param email
	 * @param phoneNumber
	 * @throws Exception
	 */
	@Test(dependsOnMethods="loginWithValidUser", dataProvider="resetPassword")
	public void retrievePassword(String userName, String email, String phoneNumber) throws Exception {
		driver.get(prop.getProperty("url"));
		login = new LoginPage(driver);
		login.getSignin().click();
		if(login.getLoginFail().getText().equals("* Incorrect username or password")) {
			ForgotPassword forgotPassword = login.getForgotPassword();
			forgotPassword.getUserName().sendKeys(userName);
			forgotPassword.getEmail().sendKeys(email);
			forgotPassword.getPhoneNumber().sendKeys(phoneNumber);
			forgotPassword.resetLogin().click();
			//Extracting a temporary password
			String extractedPassword = forgotPassword.getPasswordMessage().getText();
			String[] arrayPassword = extractedPassword.split("'");
			String password = arrayPassword[1].split("'")[0];
			Assert.assertEquals(password, "rahulshettyacademy");
			Assert.assertNotSame(password, "wrongpassword");
		}
	}
	
	/**
	 * Valid username and password stored in two dimensional int array
	 * Method: getValidUserData
	 * @return Object[][] data
	 */
	@DataProvider
	public Object[][] getValidUserData() {
		Object[][] data = new Object[1][2];
		//0th row
		data[0][0]="testUser";
		data[0][1]="rahulshettyacademy";
		return data;
	}
	
	/**
	 * InValid username and password stored in two dimensional int array
	 * Method: getInValidUserData
	 * @return Object[][] data
	 */
	@DataProvider
	public Object[][] getInValidUserData() {
		Object[][] data = new Object[1][2];
		//0th row
		data[0][0]="testUser";
		data[0][1]="testPassword";
		return data;
	}
	
	/**
	 * Reset password with username, email, and phoneNumber
	 * Method: resetPassword()
	 * @return Object[][] data
	 */
	@DataProvider
	public Object[][] resetPassword(){
		Object[][] data = new Object[1][3];
		//0th row
		data[0][0]="testUser";
		data[0][1]="test@gmail.com";
		data[0][2]="123456789";
		return data;
	}
	
	/**
	 * Close all the windows
	 */
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
