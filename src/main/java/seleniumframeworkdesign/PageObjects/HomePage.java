package seleniumframeworkdesign.PageObjects;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframeworkdesign.AbstractComponents.AbstractMethods;

public class HomePage extends AbstractMethods {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void reachWebsite() {

		driver.get("https://automationexercise.com/");
	}

	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[4]")
	WebElement signUpButton;

	public void clickSignUp() {
		waitUntilElementAppears(signUpButton);
		signUpButton.click();
	}

	@FindBy(xpath = "//form[@action='/login']/input[2]")
	WebElement emailField;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordField;

	@FindBy(xpath = "//form[@action='/login']/button")
	WebElement logInButton;

	public void Login(String emailId, String password) {

		emailField.sendKeys(emailId);
		passwordField.sendKeys(password);
		logInButton.click();
	}

	@FindBy(xpath = "//a[@href='/logout']")
	WebElement logoutButton;

	public void Logout() {

		logoutButton.click();
	}

	@FindBy(xpath = "//div[@class='pull-right']/a")
	WebElement continueButton;

	public void clickContinue() {
		waitUntilElementAppears(continueButton);
		continueButton.click();
	}

	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[5]")
	WebElement deleteButtonElement;

	public void clickDeleteAccount() {
		waitUntilElementAppears(deleteButtonElement);
		deleteButtonElement.click();
	}

}
