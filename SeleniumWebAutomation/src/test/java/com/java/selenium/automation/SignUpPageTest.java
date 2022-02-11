package com.java.selenium.automation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobject.SignUpPage;
import resources.Base;

public class SignUpPageTest extends Base{
	public WebDriver driver;
	public SignUpPage signUp;
	/**
	 * Initialize() method to select the appropriate WebDriver
	 * @throws IOException
	 */
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeWebDriver();
	}
	
	/**
	 * Testcase 4: SignUp using valid entry
	 * @param firstName
	 * @param lastName
	 * @param jobTitle
	 * @throws Exception
	 */
	@Test(dataProvider="getValidSignUpData")
	public void signUpWithValidEntry(String firstName, String lastName, String jobTitle) throws Exception 
	{
		driver.get(prop.getProperty("url2"));
		signUp = new SignUpPage(driver);
		signUp.getFirstName().sendKeys(firstName);
		signUp.getLastName().sendKeys(lastName);
		signUp.getJobTitle().sendKeys(jobTitle);
		signUp.getEducationLevel().click();
		signUp.getGender().click();
		Select experience = new Select(signUp.getYearsOfExperience());
		experience.selectByIndex(1);
		
		signUp.getDate().click();
		while(!signUp.getMonth().getText().contains("May")) {
			signUp.getNextDate().click();
		}
		List<WebElement> days = signUp.getDays();
		for(int i=0; i<days.size(); i++) {
			String day = signUp.getDays().get(i).getText();
			if(day.equals("20")) {
				signUp.getDays().get(i).click();
				break;
			}
		}
		signUp.getSubmit().click();
		Assert.assertEquals(signUp.getSuccessMessage().getText().trim(), "The form was successfully submitted!");
		Assert.assertNotSame(signUp.getSuccessMessage().getText(), "The form was submitted!");
	}
	
	/**
	 * Valid firstname, lastname, and job title for signup
	 * Method: getValidSignUpData()
	 * @return Object[][] data
	 */
	@DataProvider
	public Object[][] getValidSignUpData() {
		Object[][] data = new Object[1][3];
		//0th row
		data[0][0]="firstName";
		data[0][1]="lastName";
		data[0][2]="Student";
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
