package pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {
	public WebDriver driver;
	public WebDriverWait wait;
	
	/**
	 * Finding Locators for userName, email, phoneNumber
	 */
	By userName = By.xpath("//input[@placeholder='Name']");
	By email = By.cssSelector("input[placeholder='Email']");
	By phoneNumber = By.xpath("//form/input[3]");
	By resetLogin = By.cssSelector(".reset-pwd-btn");
	By passwordMessage = By.cssSelector("form p");
	
	/**
	 * ForgotPassword constructor
	 * @param driver
	 */
	public ForgotPassword(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUserName() {
		return driver.findElement(userName);
	}
	
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPhoneNumber() {
		return driver.findElement(phoneNumber);
	}
	
	public WebElement resetLogin() {
		//Explicit wait condition
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.elementToBeClickable(resetLogin));
	}
	
	public WebElement getPasswordMessage() {
		return driver.findElement(passwordMessage);
	}
}
