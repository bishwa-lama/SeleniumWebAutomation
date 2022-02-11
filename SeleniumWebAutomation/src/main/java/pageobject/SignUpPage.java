package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage {
	public WebDriver driver;
	
	/**
	 * Identified elements using specific locators
	 */
	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By jobTitle = By.id("job-title");
	By educationLevel = By.cssSelector("#radio-button-2");
	By gender = By.cssSelector("#checkbox-2");
	By yearsOfExperience = By.id("select-menu");
	By date = By.id("datepicker");
	By month = By.xpath("//div[@class='datepicker-days'] //th[@class='datepicker-switch']");
	By day = By.className("day");
	By nextDate = By.xpath("//div[@class='datepicker-days'] //th[@class='next']");
	By submit = By.cssSelector("[class='btn btn-lg btn-primary']");
	By successMessage = By.cssSelector("div[class='alert alert-success']");
	
	
	/**
	 * SignUpPage constructor
	 * @param driver
	 */
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getFirstName() {
		return driver.findElement(firstName);
	}

	public WebElement getLastName() {
		return driver.findElement(lastName);
	}

	public WebElement getJobTitle() {
		return driver.findElement(jobTitle);
	}

	public WebElement getEducationLevel() {
		return driver.findElement(educationLevel);
	}

	public WebElement getGender() {
		return driver.findElement(gender);
	}

	public WebElement getYearsOfExperience() {
		return driver.findElement(yearsOfExperience);
	}

	public WebElement getDate() {
		return driver.findElement(date);
	}

	public WebElement getMonth() {
		return driver.findElement(month);
	}

	public List<WebElement> getDays() {
		return driver.findElements(day);
	}
	
	public WebElement getNextDate() {
		return driver.findElement(nextDate);
	}

	public WebElement getSubmit() {
		return driver.findElement(submit);
	}

	public WebElement getSuccessMessage() {
		return driver.findElement(successMessage);
	}
}
