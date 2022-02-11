package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	/**
	 * Finding Locators for userName, password, signIn, and forgotPassword
	 */
	By userName = By.id("inputUsername");
	By password = By.name("inputPassword");
	By signIn = By.className("signInBtn");
	By loginSuccess = By.xpath("//p[contains(text(), 'successfully')]");
	By loginFail = By.cssSelector("p.error");
	By forgotPassword = By.linkText("Forgot your password?");
	By logOut = By.xpath("//button[@class='logout-btn']");
	
	/**
	 * LoginPage constructor
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUserName() {
		return driver.findElement(userName);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getSignin() {
		return driver.findElement(signIn);
	}

	public WebElement getLoginSuccess() {
		return driver.findElement(loginSuccess);
	}
	
	public WebElement getLoginFail() {
		return driver.findElement(loginFail);
	}
	
	public ForgotPassword getForgotPassword() {
		driver.findElement(forgotPassword).click();
		return new ForgotPassword(driver);
	}
	
	public WebElement getLogOut() {
		return driver.findElement(logOut);
	}
}
